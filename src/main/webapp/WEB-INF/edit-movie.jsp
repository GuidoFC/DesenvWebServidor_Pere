<%@ page import="principal.demo.model.Asteroide" %><%--
  Created by IntelliJ IDEA.
  User: Guido
  Date: 04/11/2024
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-lg-6">
    <%
        // Obtenemos el objeto Asteroide desde el request
        Asteroide asteroide = (Asteroide) request.getAttribute("AsteroideEncontrado");
    %>
    <h1>Editar Asteroide</h1>

    <!-- Llamamos al servlet con el método POST para editar el asteroide -->
    <form action="editar?accion=actualizarAsteroide" method="post">
        <!-- Campo para editar el nombre -->
        Nombre:<br>
        <input class="form-control" type="text" name="txtNombre" value="<%= asteroide.getNombre() %>"><br>

        <!-- Campo para editar la magnitud -->
        Magnitud absoluta: <br>
        <input class="form-control" type="number" name="txtMagnitud" value="<%= asteroide.getMagnitud() %>"><br>

        <!-- Campo para editar el diámetro promedio -->
        Diámetro promedio (km): <br>
        <input class="form-control" type="number" name="txtDiameterKmAverage"
               value="<%= asteroide.getDiameter_km_average() %>"><br>

        <!-- Campo booleano para editar si es potencialmente peligroso -->
        ¿Es peligroso?: <br>
        <input type="checkbox" name="txtPeligroso" value="true" <%= asteroide.isEsPeligroso() ? "checked" : "" %>><br>

        <!-- Campo oculto para el ID del asteroide -->
        <input type="hidden" name="txtId" value="<%= asteroide.getId() %>">

        <!-- Botón para actualizar -->
        <br>
        <input class="btn btn-primary" type="submit" value="Actualizar">
        <br><br>

        <!-- Enlace para regresar al listado -->
        <a href="litarTodasAsteroides">Regresar al listado de asteroides</a>
    </form>
</div>
</div>
</body>

</html>
