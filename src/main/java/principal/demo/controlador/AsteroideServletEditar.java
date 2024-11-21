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
import java.util.Optional;

//Esta es una anotación para decirle a Java que esta clase será un "Servlet".
// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
@WebServlet(
        name = "AsteroideServletEditar",
        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
        urlPatterns = "/editar")

//
// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
public class AsteroideServletEditar extends HttpServlet {
    private AsteroideDAOAntiguo asteroideDAOAntiguo = new AsteroideDAOAntiguo();
    private AsteroideService asteroideService = new AsteroideService(asteroideDAOAntiguo);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        System.out.println("Estoy en AsteroideSerlet Editar doGet!!!!");

        if ("editarAsteroide".equals(action)) {
            // TODO
            // 1 Obtener el ID de la película a editar
            // 2 Creo un formulario con los valores de la pelicula
            // 3 REcibir esos valores del formulario
            // 4 editarlo en la base de datos
            Long id = Long.parseLong(request.getParameter("id"));
            System.out.println("Editar Asteroide " + id);
            Asteroide asteroide = asteroideService.getAsteroideById(id);

            if (asteroide != null) {
                request.setAttribute("AsteroideEncontrado", asteroide);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/edit-asteroide.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("litarTodasAsteroides"); // Redirigir a la lista si Asteroide no existe
            }
        }

    }

    // TODO porque tenemos que crear este método processRequest y no lo hacemos
    // direcamente desde el método doGet?
    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        System.out.println("Estoy en editar serlet en el metodo doPost");
        if ("actualizarAsteroide".equals(action)) {
            System.out.println("Editar Asteroide Actualizada dentro del if");


            Long id = Long.valueOf(request.getParameter("txtId"));
            String nombre = request.getParameter("txtNombre");
            Double magnitud = Double.parseDouble(request.getParameter("txtMagnitud"));
            Double diameterKmAverage = Double.parseDouble(request.getParameter("txtDiameterKmAverage"));
            Boolean esPeligros = Boolean.parseBoolean(request.getParameter("txtPeligroso"));


            Asteroide updatedAsteroide = new Asteroide(id, nombre, magnitud, diameterKmAverage, esPeligros);
            asteroideService.updateAsteroide(updatedAsteroide);

            response.sendRedirect("litarTodasAsteroides"); // Redirigir a la lista de películas
        }
    }


}
