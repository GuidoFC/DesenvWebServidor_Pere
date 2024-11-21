<%@ page import="principal.hellodia24.Importante.modelo.Movie" %><%--
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
<div class="container">
    <div class="col-lg-6">

        <%--        No se puede acceder a la carperta WEB-INF, solo se puede acceder aqui con un Serlet--%>
        <h1>Crear una Película</h1>

        <form action="crear?accion=crearPeliculaNueva" method="post">
            Título:<br>
            <input class="form-control" type="text" name="txtTitle" required><br>

            Descripción: <br>
            <input class="form-control" type="text" name="txtDescription" required><br>

            Año: <br>
            <input class="form-control" type="number" name="txtYear" required><br>


            <input class="btn btn-primary" type="submit" name="accion" value="crear">
            <a href="litarTodasPeliculas">Regresar al listado de peliculas</a>
        </form>
    </div>
</div>
</body>

</html>
