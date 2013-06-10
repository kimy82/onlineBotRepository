<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/welcome.min.css' />" />	
	<title> <s:text name="txt.welcome.principal" />	</title>
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');

</script>	
</head>
<body id=indexPor>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
	<div id="content">
			<c:import url="/pages/includes/menuHeaderNoConfirm.jsp" />
			<c:import url="/pages/includes/divLanguage.jsp" />
			<div class="poli">


<s:text name="txt.funciona1" />

</div>

</div>
<c:import url="/pages/includes/endPage.jsp" />
<script src="https://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
<script src="<c:url value='/js/jsdivlogin.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/jsauxiliars.min.js' />" type="text/javascript"></script>

</body>
</html>