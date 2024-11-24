<%@ page import="principal.demo.model.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Guido
  Date: 04/11/2024
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Usuario</title>
</head>
<body>
<div class="container">
    <div class="col-lg-6">

        <h1>Crear un Usuario: </h1>


        <form action="crearUsuario?accion=FormulariocrearUsuario" method="post">
            Nombre:<br>
            <input class="form-control" type="text" name="txtName" required><br>

            Introduce tu Email:<br>
            <input class="form-control" type="email" name="txtEmail" required><br>

            Introduce tu Contraseña:<br>
            <input class="form-control" type="password" name="txtPassword" required><br>

            <!-- Mostrar mensaje de error si existe No ha seleccionado un atributo,
            En teoria no pasara porque el select esta puesto como required-->
            <%
                String errorRol = (String) request.getAttribute("errorRol");

                if (errorRol != null) {
            %>
            <div style="color: red;">
                <%= errorRol %>
            </div>
            <%
                }
            %>
            Selecciona tu Rol:<br>
            <select class="form-control" name="txtRol" required>
                <option value="OBSERVADOR">Observador</option>
                <option value="ASTRONOMO">Astrónomo</option>
            </select>

            <br>



            <input class="btn btn-primary" type="submit" name="accion" value="crear">
            <a href="/nasa">Regresar al index</a>
        </form>
    </div>

</div>
</body>

</html>
