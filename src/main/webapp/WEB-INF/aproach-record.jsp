<%@ page import="principal.demo.model.Aproach" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Guido
  Date: 24/11/2024
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Fecha de Avistamiento</th>
    <th>Velocidad (km/s)</th>
    <th>Distancia (km)</th>
    <th>Cuerpo Orbitante</th>
  </tr>
  </thead>
  <tbody>
  <%
    if (request.getAttribute("aproachlist") != null) {
      List<Aproach> listaAproach = (List<Aproach>) request.getAttribute("aproachlist");
      for (Aproach aproach : listaAproach) {
  %>
  <tr>
    <td><%= aproach.getId() %></td>
    <td><%= aproach.getApproach_date() %></td>
    <td><%= aproach.getVelocidad() %></td>
    <td><%= aproach.getDistance() %></td>
    <td><%= aproach.getOrbiting_body() %></td>
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>

</body>
</html>
