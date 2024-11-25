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
<div class="container">
    <div class="col-lg-6">

        <%--        No se puede acceder a la carperta WEB-INF, solo se puede acceder aqui con un Serlet--%>
        <h1>Crear un Asteroide</h1>

        <form action="crear?accion=crearAsteroide" method="post">

            Id_nasa: <br>
            <input class="form-control" type="number"  name="txtId_nasa" placeholder="2000719" required><br>

            <!-- Campo para ingresar el nombre -->
            Nombre:<br>
            <input class="form-control" type="text" name="txtNombre" placeholder="Ingrese el nombre del asteroide" required><br>

            <!-- Campo para ingresar la magnitud -->
<%--             step="0.01" significa que permite 2 decimales--%>
            Magnitud absoluta: <br>
            <input class="form-control" type="number" step="0.01" name="txtMagnitud" placeholder="12.03" required><br>

            <!-- Campo para ingresar el diámetro promedio -->
            Diámetro promedio (km): <br>
            <input class="form-control" type="number" step="0.01" name="txtDiameterKmAverage"
                   placeholder="Ingrese el diámetro promedio en km" required><br>

            <!-- Campo booleano para indicar si es potencialmente peligroso -->
            ¿Es peligroso?: <br>
            <input type="checkbox" name="txtPeligroso" value="true"><br>

            <!-- Botón para crear -->
            <br>
            <input class="btn btn-success" type="submit" value="Crear">
            <br><br>

            <!-- Enlace para regresar al listado -->
            <a href="litarTodasAsteroides">Regresar al listado de asteroides</a>
        </form>
    </div>
</div>
</body>

</html>
