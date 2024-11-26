package principal.demo.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import principal.demo.DAO.AsteroideDAOAntiguo;
import principal.demo.model.Aproach;
import principal.demo.model.Asteroide;
import principal.demo.service.AproachService;
import principal.demo.service.AsteroideService;

import java.io.IOException;
import java.util.List;

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
//        Nombre del Servlet
        name = "AsteroideServlet",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/litarTodasAsteroides")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class AsteroideServletListar extends HttpServlet {


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

        if ("verAproach".equals(action)){
            System.out.println("Wow 1");
            Long id = Long.parseLong(request.getParameter("idAsteroide"));
            System.out.println("Obtener Asteroide con id " + id);

            // TODO tengo que usar este ID del asteroide para filtrar solo los aproach de dicho Asteroide

            AproachService aproachService = new AproachService();
            List<Aproach> aproachlist = aproachService.getListAproachFromService(id);

            request.setAttribute("aproachlist", aproachlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/aproach-record.jsp");
            System.out.println("processRequest de MovieSerlvet 23");
            dispatcher.forward(request, response);
        }else {
            processRequest(request, response); // Mostrar la lista de Asteroides
        }


    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Asteroide> listaAsteroide = asteroideService.getListAsteroideFromService();

        request.setAttribute("listaAsteroide", listaAsteroide);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asteroide-record.jsp");
        System.out.println("processRequest de MovieSerlvet 13");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

    }


}
