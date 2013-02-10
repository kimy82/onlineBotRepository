<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
<title><s:text name="txt.welcome.principal" /></title>
</head>
<body onload='document.f.j_username.focus();'>

<div id="light">
	<div id="light_int">
		<div class="light_top">
			<s:text name="txt.login.title" />
		</div>
		<hr class="sep5">
		<div class="light_body">
			<div id="form_loguin" class="form">
				<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'   >
				<s:text name="txt.user.usuario" />*:<br> <input class="inputs" type="text" name='j_username' value="" ><br>
				<s:text name="txt.user.password" />*:<br> <input type='password' class="inputs" name='j_password' /> <br>
				<input class="boton" type="submit" value="ENTRAR">
				</form>
			</div>
			<a name="register" type="button" href="<c:url value='/preRecoverAcount.action'/>" ><s:text name="txt.user.forgot.password" /></a>	
					
		</div>
	</div>
</dvi>
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
</body>
</html>