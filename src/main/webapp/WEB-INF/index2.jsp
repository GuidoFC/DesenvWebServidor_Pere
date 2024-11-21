<%@ page import="principal.demo.model.Asteroide" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>ListarAsteroideTitulo</title>
</head>
<body>
<h1><%= "Estamos en movie-recod.jsp" %>
</h1>
<br/>

<div>
    <h1>Estoy en asteroide-record!!</h1>
    <br>
    <a href="/nasa">Regresar al index</a>
    <br>
    <a href="crear?accion=crearAsteroide">Añadir Asteroid</a>
    <br>
    <br>
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
                <a href="editar?accion=editarAsteroide&id=<%= asteroide.getId() %>">Editar</a>

                <a href="eliminar?id=<%= asteroide.getId() %>">Remove</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>

    </table>

    <br>
    <br>

    <br>
    <br>
</div>
</body>
</html>
