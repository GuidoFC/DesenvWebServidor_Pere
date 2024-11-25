<%@ page import="principal.demo.model.Asteroide" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>ListarAsteroideTitulo</title>
</head>
<body>

<%
    String name = (String) request.getSession().getAttribute("name");
    String email = (String) request.getSession().getAttribute("email");


    if (name != null) {
%>
<div style="color: blue;">
    <p>Nombre:  <%= name %>
    </p>
    <p>Email: <%= email %>
    </p>

</div>
<div>
    <a href="cerrarSesion"> Cerrar Session</a>
    <br>
    <br>

</div>
<%
    }
%>

<br>
<br>


<h1><%= "Estamos en Asteroide-recod.jsp" %>
</h1>
<br/>

<div>
    <h1>Estoy en asteroide-record!!</h1>
    <br>
    <a href="/nasa">Regresar al index</a>
    <br>
    <a href="crear?accion=crearAsteroide">Añadir Asteroide</a>
    <br>
    <br>

    <a href="crear?accion=ApiNasa">Añadir Asteroide Desde la Api</a>
    <br>
    <br>

    <a href="eliminar?accion=eliminarTodo">Eliminar Todos Los Asteroide</a>
    <br>
    <br>
    <table border="1">
        <thead>
        <tr>

            <th>ID_Nasa</th>
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
            <td><%= asteroide.getId_Nasa() %>
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
                <a href="litarTodasAsteroides?accion=verAproach&idAsteroide=<%= asteroide.getId() %>">Ver Avistamiento</a>
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
