//package principal.demo.controlador;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//import java.io.IOException;
//import java.util.List;
//
////Esta es una anotación para decirle a Java que esta clase será un "Servlet".
//// Un servlet es básicamente una pequeña aplicación que responde a solicitudes en la web.
//@WebServlet(
////        Nombre del Servlet
//        name = "MovieServletCrear",
//        // indica que este servlet responderá cuando alguien visite la URL /movie en el servidor.
//        urlPatterns = "/crear")
//
////
//// Declara la clase MovieServlet, que extiende HttpServlet, lo que significa que esta clase puede manejar solicitudes HTTP, como GET o POST.
//public class MovieServletCrear extends HttpServlet {
//    private MovieService movieService = new MovieService();
//
//    //    Este méto_do doGet se ejecuta automáticamente cuando alguien hace una solicitud GET a la URL /movie
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("accion");
//        System.out.println("Estoy en MovieServletCrear CREAR!!! DoGet");
//
//        if ("crearPelicula".equals(action)) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crear-movie.jsp");
//            dispatcher.forward(request, response);
//            System.out.println("Estoy en MovieServletCrear CREAR!!! DoGet dentro del IF");
//
//        } else {
//            processRequest(request, response); // Mostrar la lista de películas
//        }
//    }
//
// // TODO: Para que sirve el processRequest?????
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        List<Movie> listaPeliculas = movieService.getListMovieFromService();
//        //
//        request.setAttribute("listaPeliculas", listaPeliculas);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/asteroide-record.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("accion");
//        System.out.println("Estoy en MovieSerlet CREAR!!! DoPost");
//        if ("crearPeliculaNueva".equals(action)) {
//            System.out.println("Estoy en MovieSerlet CREAR!!! DoPost dentro del IF");
//            String title = request.getParameter("txtTitle");
//            String description = request.getParameter("txtDescription");
//            int year = Integer.parseInt(request.getParameter("txtYear"));
//
//            Movie crearPelicula = new Movie(null, title, description, year);
//            movieService.crearMovie(crearPelicula);
//            response.sendRedirect("litarTodasPeliculas"); // Redirigir a la lista de películas
//        }
//    }
//
//
//}
