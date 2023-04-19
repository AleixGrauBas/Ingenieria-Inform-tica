<%--
  Created by IntelliJ IDEA.
  User: Alex Ioanesi
  Date: 24/12/2022
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="estilo.css">
  <meta charset="UTF-8">
  <title>BlaBlaUji</title>
</head>
<body>
<%
  JSONObject res = (JSONObject) request.getAttribute("res");
  if (res.isEmpty()){%>
<h1> Lo sentimos, no se ha podido efectuar correctamente</h1>
<%} else{%>

<h1>Enhorabuena. Tu propuesta de viaje ha sido aceptada.</h1>
<br>
<h1>El viaje tiene el siguiente estado:</h1>
<br>
<div id="divIndice">
<ul>
  <li>Código del viaje: <%= res.get("codviaje").toString()%></li>
  <li>Código del propietario: <%=res.get("codprop").toString()%></li>
  <li>Origen: <%=res.get("origen").toString()%></li>
  <li>Destino: <%=res.get("destino").toString()%></li>
  <li>Fecha: <%=res.get("fecha").toString()%></li>
  <li>Pasajeros: <%=res.get("pasajeros").toString()%></li>
  <li>Precio: <%=res.get("precio").toString()%></li>
  <li>Plazas disponibles: <%=res.get("numplazas").toString()%></li>
</ul>
<%}%>
<h3><a href="menu.jsp">Menú</a></h3>
</div>
</body>
</html>
