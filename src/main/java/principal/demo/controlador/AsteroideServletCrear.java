package principal.demo.controlador;

import ObtenerJSonNasa.ChatGept;
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

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
//        Nombre del Servlet
        name = "AsteroideServletCrear",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/crear")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class AsteroideServletCrear extends HttpServlet {
    private AsteroideDAOAntiguo asteroideDAOAntiguo = new AsteroideDAOAntiguo();
    private AsteroideService asteroideService = new AsteroideService(asteroideDAOAntiguo);

    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en AsteroideServletCrear CREAR!!! DoGet");

        if ("crearAsteroide".equals(action)) {
            System.out.println("Estoy en MovieServletCrear CREAR!!! DoGet dentro del IF");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crear-asteroide.jsp");
            dispatcher.forward(request, response);


        } else if ("ApiNasa".equals(action)) {
            ChatGept chatGept = new ChatGept();
            chatGept.procesarDatosNASA();
            processRequest(request, response);
        } else {
            processRequest(request, response); // Mostrar la lista de películas
        }
    }

    // TODO: Para que sirve el processRequest?????
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Asteroide> listaAsteroide = asteroideService.getListAsteroideFromService();
        //
        request.setAttribute("listaAsteroide", listaAsteroide);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asteroide-record.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en MovieSerlet CREAR!!! DoPost");

        if ("crearAsteroide".equals(action)) {


            String nombre = request.getParameter("txtNombre");
            Double magnitud = Double.parseDouble(request.getParameter("txtMagnitud"));
            Double diameterKmAverage = Double.parseDouble(request.getParameter("txtDiameterKmAverage"));
            Boolean esPeligros = Boolean.parseBoolean(request.getParameter("txtPeligroso"));

            Asteroide crearAsteroide = new Asteroide(null, nombre, magnitud, diameterKmAverage, esPeligros);
            asteroideService.crearAsteroide(crearAsteroide);
            response.sendRedirect("litarTodasAsteroides"); // Redirigir a la lista de películas
        }
    }


}
