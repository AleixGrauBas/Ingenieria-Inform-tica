<%--
  Created by IntelliJ IDEA.
  User: Alex Ioanesi
  Date: 24/12/2022
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="Modelo.Viaje" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="UTF-8">
    <title>BlablaUji</title>
    <div id="div1">
        <img src="coches2.jpg" alt="coches" width="100%" >
    </div>
</head>
<link rel="stylesheet" href="estilo.css">
<body>
<h1 id="tituloIndice">BlaBla UJI</h1>
<div id="subtituloIndice">

    <h2> Viajes disponibles desde <%=request.getParameter("codviaje")%></h2>
    <%
        JSONArray res = (JSONArray) request.getAttribute("res");
    %>
    <% if (res.isEmpty()){ %>
    <b> Lo sentimos, no se ha encontrado ningún viaje con ese origen :</b>
    <% }
    else{ %>
    <table>
        <tr>
            <th>Código viaje</th>
            <th>Código propietario</th>
            <th>Destino</th>
            <th>Fecha</th>
            <th>Precio</th>
            <th>Plazas</th>
            <th>Pasajeros</th>
        </tr>
        <%
            for (Object re : res) {
                Viaje viaje = (Viaje) re;
        %>
        <tr>
            <td><%= viaje.getCodviaje()%>
            </td>
            <td><%= viaje.getCodprop()%>
            </td>
            <td><%= viaje.getDestino()%>
            </td>
            <td><%= viaje.getFecha()%>
            </td>
            <td><%= viaje.getPrecio()%>
            </td>
            <td><%= viaje.getNumplazas()%>
            </td>
            <td><%= viaje.getPasajeros()%>
            </td>
        </tr>
        <% }
    } %>
    </table>
    <a href="menu.jsp">Menú</a>
</div>
</body>
</html>
