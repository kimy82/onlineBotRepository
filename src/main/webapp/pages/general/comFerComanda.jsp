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
</head>
<body id=indexPor>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
	<div id="content">
			<c:import url="/pages/includes/menuHeaderNoConfirm.jsp" />
			<c:import url="/pages/includes/divLanguage.jsp" />
			<div class="poli">

<span class="negrete">
Com funciona?
</span></br></br>

<span class="negrete">
Tria restaurant
</span></br></br>
Disposem d’una selecció de restaurants de Girona que no ofereixen servei d’entrega a domicili. La nostra voluntat es tenir una oferta àmplia i de qualitat. Periòdicament anirem incorporant nous restaurants per ampliar la nostra oferta.</br></br>

<span class="negrete">
Tria els plats
</span></br></br>
Les fotos dels plats són reals. T’informem sobre els al·lèrgens (llet, ous i fruits secs) així com si son aptes per a celíacs i vegetarians. Els preus dels plats són els mateixos a les cartes dels restaurants.
</br></br>
<span class="negrete">
Tria les begudes
</span></br></br>
Pots escollir entre una varietat de vins, cerveses i altres begudes. Tenim vins de la DO Empordà, Rueda, Ribera, també Lambrusco i cerveses artesanes. </br></br>

<span class="negrete">
Tria l’hora a la que vols que t’ho portem
</span></br></br>
T’ho posem molt fàcil: pots escollir l’hora a la que vulguis que t’ho portem. Et recomanem fer la comanda amb previsió perquè la disponibilitat horària va en funció del número de comandes que tenim. Si vols també tens l’opció d’anar a recollir la comanda al restaurant.</br></br>

Aprofita els avantatges de fer la comanda abans de les 16h del mateix dia!</br></br>

<span class="negrete">
Confirma la comanda.
</span></br></br>
Pots pagar amb targeta de crèdit a la web o en efectiu quan el repartidor et porti la comanda. Inmediatament després de confirmar la comanda nosaltres la transmetem al restaurant i planifiquem les rutes per tal d’entregar-te-la a l’hora que has triat.



</div>

</div>
<c:import url="/pages/includes/endPage.jsp" />
<script src="<c:url value='/js/jsdivlogin.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/auxiliars/jsauxiliars.js' />" type="text/javascript"></script>

</body>
</html>