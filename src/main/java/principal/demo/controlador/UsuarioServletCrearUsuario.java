package principal.demo.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import principal.demo.model.Rol;
import principal.demo.model.Usuario;
import principal.demo.service.UsuarioService;

import java.io.IOException;

@WebServlet(
        name = "UsuarioServletCrearUsuario",
        urlPatterns = "/crearUsuario"
)
public class UsuarioServletCrearUsuario extends HttpServlet {
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("accion");

        if ("crearUsuarioNuevo".equals(action)) {
            // Muestra el JSP donde hay un formulario para crear usuario
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/create-user.jsp");
            dispatcher.forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if ("FormulariocrearUsuario".equals(action)) {
            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String rolSeleccionado = request.getParameter("txtRol");


            // Validar que el rol no sea nulo o vacío
            if (rolSeleccionado == null || rolSeleccionado.isEmpty()) {
                request.setAttribute("errorRol", "Debe seleccionar un rol válido.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crear-usuario.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Si usas un enum para los roles, conviértelo
            Rol rol = Rol.valueOf(rolSeleccionado);

            Usuario usuario = new Usuario();

            usuario.setNombre(name);
            usuario.setEmail(email);
            usuario.setRol(rol);
            // cirframos la contraseña
            String contrasenaCifrada = UsuarioService.hashPassword(password);
            usuario.setContrasena(contrasenaCifrada); // Asegúrate de cifrar la contraseña antes de guardarla

            usuarioService.crearUsuario(usuario);
            // Guarda los datos en la sesión en lugar de la solicitud
            // de esta forma los datos

            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("name", usuario.getNombre());


            response.sendRedirect("litarTodasAsteroides"); // Redirige al listado de Peliculas
        }
    }
}
