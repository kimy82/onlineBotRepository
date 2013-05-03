<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
	<!--  link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/loguin.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" /-->
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/loguin.on.min.css' />" />	
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<title><s:text name="txt.welcome.principal" /></title>
</head>
<body onload='document.f.j_username.focus();'>

<div id="light">
	<div id="light_int">
		<c:if test="${dialog eq 'true'}">
			<div id="move" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix addmove">
			</div>
		</c:if>
		<div class="light_top">
			<s:text name="txt.login.mailo" />
			<c:if test="${dialog eq 'true'}">
					&nbsp;<input class="tancarBot" style="text-align: right;" type="button" onclick="closeLoguin()" value="X" />
			</c:if>
		</div>
		<hr class="sep5">
		<div class="light_body">
			<div id="form_loguin" class="form">
				<form name='f' action="<c:url value='/j_spring_security_check' />" method='POST'   >
				<s:text name="txt.user.usuario" />*:<br> <input class="inputs" type="text" name='j_username' onclick="focus()" ><br>
				<s:text name="txt.user.password" />*:<br> <input type='password' class="inputs" name='j_password' onclick="focus()" /> <br>
				
				<input class="boton" type="submit" value="ENTRAR" />
				
				</form>
			</div>
			<a class="forgot" name="register" type="button" href="<c:url value='/preRecoverAcount.action'/>" ><s:text name="txt.user.forgot.password" /></a>
				
			<br>	
					
		</div>
	</div>
</div>
	
</body>
</html>