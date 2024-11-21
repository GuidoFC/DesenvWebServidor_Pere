//package principal.demo.controlador;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import principal.hellodia24.Importante.modelo.Movie;
//import principal.hellodia24.Importante.service.MovieService;
//
//import java.io.IOException;
//import java.util.Optional;
//
////Esta es una anotación para decirle a Java que esta clase será un "Servlet".
//// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
//@WebServlet(
////        Nombre del Servlet
//        name = "MovieServletEditar",
//        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
//        urlPatterns = "/editar" )
//
////
//// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
//public class MovieServletEditar extends HttpServlet {
//    private MovieService movieService = new MovieService();
//
//    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("accion");
//        System.out.println("Estoy en MovieSerlet Editar doGet!!!!");
//
//        if ("editarPelicula".equals(action)) {
//            // TODO
//            // 1 Obtener el ID de la película a editar
//            // 2 Creo un formulario con los valores de la pelicula
//            // 3 REcibir esos valores del formulario
//            // 4 editarlo en la base de datos
//            int id = Integer.parseInt(request.getParameter("id"));
//            System.out.println("Editar Pelicula " + id);
//            Optional<Movie> movie = movieService.getMovieById(id);
//
//            if (movie.isPresent()) {
//                request.setAttribute("peliculaEncontrada", movie.get());
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/edit-movie.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                response.sendRedirect("litarTodasPeliculas"); // Redirigir a la lista si la película no existe
//            }
//        }
//
//    }
//
//    // TODO porque tenemos que crear este método processRequest y no lo hacemos
//    // direcamente desde el método doGet?
//    private void processRequest(
//            HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("accion");
//
//        System.out.println("Estoy en editar serlet en el metodo doPost");
//        if ("actualizarPelicula".equals(action)) {
//            System.out.println("Editar Pelicula Actualizada dentro del if");
//            Long id = Long.valueOf(request.getParameter("txtId"));
//            String title = request.getParameter("txtTitle");
//            String description = request.getParameter("txtDescription");
//            int year = Integer.parseInt(request.getParameter("txtYear"));
//
//            Movie updatedMovie = new Movie(id, title, description, year);
//            movieService.updateMovie(updatedMovie);
//
//            response.sendRedirect("litarTodasPeliculas"); // Redirigir a la lista de películas
//        }
//    }
//
//
//}
