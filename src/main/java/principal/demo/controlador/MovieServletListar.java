package principal.demo.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import principal.demo.DAO.AsteroideDAOAntiguo;
import principal.demo.DAO.AsteroideDaoImpl;
import principal.demo.model.Asteroide;
import principal.demo.service.AsteroideService;

import java.io.IOException;
import java.util.List;

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
//        Nombre del Servlet
        name = "MovieServlet",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/litarTodasAsteroides")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class MovieServletListar extends HttpServlet {


    private AsteroideDAOAntiguo asteroideDAOAntiguo = new AsteroideDAOAntiguo();
    // TODO mirar si funciona, por ahora no funciona
//    private AsteroideDaoImpl asteroideDaoImpl = new AsteroideDaoImpl();


    // TODO hacemos injeccion de indepencia
    private AsteroideService asteroideService = new AsteroideService(asteroideDAOAntiguo);

    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en AsteroideSerlet Listar doGet");


        processRequest(request, response); // Mostrar la lista de películas

    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Asteroide> listaAsteroide = asteroideService.getListAsteroideFromService();

        for (int i = 0; i < listaAsteroide.size(); i++) {
            System.out.println(listaAsteroide.get(i).toString());
        }
        System.out.println("Fin del bucle");
        request.setAttribute("listaAsteroide", listaAsteroide);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index2.jsp");
        System.out.println("processRequest de MovieSerlvet 13");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

    }


}
