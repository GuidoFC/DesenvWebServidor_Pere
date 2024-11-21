//package principal.demo.controlador;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import principal.hellodia24.Importante.modelo.Usuario;
//import principal.hellodia24.Importante.service.UsuarioService;
//
//import java.io.IOException;
//
//@WebServlet(
//        name = "UsuarioServletCrearUsuario",
//        urlPatterns = "/crearUsuario"
//)
//public class UsuarioServletCrearUsuario extends HttpServlet {
//    private UsuarioService usuarioService = new UsuarioService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("accion");
//
//        if ("crearUsuarioNuevo".equals(action)) {
//            // Muestra el JSP donde hay un formulario para crear usuario
//            RequestDispatcher dispatcher = request.getRequestDispatcher(
//                    "/WEB-INF/create-user.jsp");
//            dispatcher.forward(request, response);
//        }
//
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("accion");
//
//        if ("FormulariocrearUsuario".equals(action)) {
//            String name = request.getParameter("txtName");
//            String email = request.getParameter("txtEmail");
//            String password = request.getParameter("txtPassword");
//
//            Usuario usuario = new Usuario();
//
//            usuario.setNombre(name);
//            usuario.setEmail(email);
//            // cirframos la contraseña
//            String contrasenaCifrada = UsuarioService.hashPassword(password);
//            usuario.setContrasena(contrasenaCifrada); // Asegúrate de cifrar la contraseña antes de guardarla
//
//            usuarioService.crearUsuario(usuario);
//            // Guarda los datos en la sesión en lugar de la solicitud
//            // de esta forma los datos
//
//            request.getSession().setAttribute("email", email);
//            request.getSession().setAttribute("name", usuario.getNombre());
//
//
//            response.sendRedirect("litarTodasPeliculas"); // Redirige al listado de Peliculas
//        }
//    }
//}
