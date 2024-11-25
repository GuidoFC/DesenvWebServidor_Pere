package principal.demo.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(servletNames = {"UsuarioServletCrearUsuario", "UsuarioServletLogin"})
public class AstronomoInterceptor implements Filter{

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("Estoy en Usuario Interceptor");
        System.out.println("Impedimos que si ha iniciado sesion, vuelva a iniciar sesion o cree un nuevo usuario");


        HttpSession session = httpRequest.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("email") != null);
        String emailCapturado = (String) session.getAttribute("email");
        System.out.println("El email capturado es " + emailCapturado);

        // Si no está autenticado, redirigir a la página de inicio de sesión
        if (loggedIn) {
            // TODO cuando uso el sendRedirect() tengo que poner la ruta sin la "/"
            httpResponse.sendRedirect("/nasa");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}