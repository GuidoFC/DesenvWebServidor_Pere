<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Estamos en index.jsp" %>
</h1>
<br/>

<div>
    <%--    tengo q mirar el patron de diseño Interceptor--%>
    <br>
    <a href="litarTodasAsteroides">Listar Asteroides</a>
    <br>
    <br>
    <%--TODO Recuerda: Si quiero solo puedo poner  -> href="crearUsuario" como lo he puesto abajo en el href="usuarioLogin"
    pero lo pongo asi para que recuerdes que le puedes pasar parametros en un link--%>
    <a href="crearUsuario?accion=crearUsuarioNuevo">Crear un usuario</a>
    <br>
    <br>
    <a href="usuarioLogin">Iniciar Sesión</a>
    <br>
    <br>
</div>
</body>
</html>
