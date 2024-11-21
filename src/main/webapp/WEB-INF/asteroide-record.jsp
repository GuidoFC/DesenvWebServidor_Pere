<%@ page import="java.util.List" %>
<%@ page import="principal.demo.model.Asteroide" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Estoy en asteroide-record!!</h1>
<a href="crear?accion=crearAsteroide">Añadir Asteroid</a>
<br>
<br>

<!-- Mostrar mensaje de error si existe de Email-->

<%--<%--%>
<%--    String name = (String) request.getSession().getAttribute("name");--%>
<%--    String email = (String) request.getSession().getAttribute("email");--%>


<%--    if (name != null) {--%>
<%--%>--%>
<%--<div style="color: blue;">--%>
<%--    <p>Nombre:  <%= name %>--%>
<%--    </p>--%>
<%--    <p>Email: <%= email %>--%>
<%--    </p>--%>

<%--</div>--%>
<%--<div>--%>
<%--    <a href="cerrarSesion"> Cerrar Session</a>--%>
<%--    <br>--%>
<%--    <br>--%>

<%--</div>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

<br>
<br>
<a href="/nasa">Regresar al index</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>absolute_magnitude</th>
        <th>diameter_km_average</th>
        <th>is_potentially_hazardous</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (request.getAttribute("listaAsteroide") != null) {
            List<Asteroide> listaAsteroide = (List<Asteroide>) request.getAttribute("listaAsteroide");
            for (Asteroide asteroide : listaAsteroide) {
    %>
    <tr>

        <td><%= asteroide.getId() %>
        </td>
        <td><%= asteroide.getNombre() %>
        </td>
        <td><%= asteroide.getMagnitud() %>
        </td>
        <td><%= asteroide.getDiameter_km_average() %>
        </td>
        <td><%= asteroide.isEsPeligroso() %>
        </td>
        <td>
<%--            TODO falta hacer la parte de editar--%>
            <a href="editar?accion=editarPelicula&id=<%= asteroide.getId() %>">Editar</a>

            <a href="eliminar?id=<%= asteroide.getId() %>">Remove</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

</body>
</html>
