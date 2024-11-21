package principal.demo.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import principal.demo.DAO.AsteroideDAOAntiguo;
import principal.demo.model.Asteroide;
import principal.demo.service.AsteroideService;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
//        Nombre del Servlet
        name = "AsteroideServletEliminar",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/eliminar")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class AseroideServletEliminar extends HttpServlet {

    private AsteroideDAOAntiguo asteroideDAOAntiguo = new AsteroideDAOAntiguo();
    private AsteroideService asteroideService = new AsteroideService(asteroideDAOAntiguo);

    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dentro del Servlet de eliminar do Get");
        // Cogemos el parametro del id
        int id = Integer.parseInt(request.getParameter("id"));
        Asteroide asteroide = asteroideService.getAsteroideById(id);

        if (asteroide != null) {
            asteroideService.removeAsteroide(asteroide);
            System.out.println("Hemos elimanado la pelicula con id: " + id);
            response.sendRedirect("litarTodasAsteroides"); // Refrescamos la pantalla para ver todas las pelis sin la peli eliminada
        } else {
            processRequest(request, response); // Mostrar la lista de películas
        }
    }

    // TODO porque tenemos que crear este método processRequest y no lo hacemos
    // direcamente desde el método doGet?
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Asteroide> listaAsteroide = asteroideService.getListAsteroideFromService();

        request.setAttribute("listaAsteroide", listaAsteroide);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asteroide-record.jsp");
        System.out.println("processRequest de MovieSerlvet Eliminar 23");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

    }


}
