<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title>Welcome</title>	
	<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
</head>
<body>
<div class="content">
	<div class="container">
		<div class="topadmin">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		</div>
		<iframe src="${requestScope.urlTPV}" frameborder="0" width="100%" height="800">Si ves este mensaje, significa que tu navegador no tiene soporte para marcos o el mismo está deshabilitado. Puedes acceder a la información mostrada en este marco aquí: <a href="http://www.htmlquick.com/es/tutorials.html">Tutoriales HTML</a>.
		</iframe>
		<form target="iframe" method="POST" action="recogida.php"">
			<input name="texto" type="text" value="">
			<input name="boton" type="submit" value="enviar">
		</form>
		<iframe name="iframe" src="recogida.php"></iframe>
	</div>
</div>
</body>
</html>
