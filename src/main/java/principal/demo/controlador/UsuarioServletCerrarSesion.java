package principal.demo.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "CerrarSesionServlet",
        urlPatterns = "/cerrarSesion"
)
public class UsuarioServletCerrarSesion extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidar la sesión actual
        request.getSession().invalidate();

        // Redirigir al usuario a la página de inicio de sesión u otra página
        response.sendRedirect("/nasa");


    }


}
