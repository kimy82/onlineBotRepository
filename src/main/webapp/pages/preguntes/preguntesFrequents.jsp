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
	<style>
	.imgfaq{
		vertical-align:middle;
	}
	
	.petit{
		font-size:11px;
	}
	</style>
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
FAQ
</span></br></br>

<span class="negrete">
Com es fa una comanda?
</span></br></br>
<span class="negrete">
1 Registra’t o entra amb el teu compte d'’usuari.</span></br>
<span class="negrete">
2 Tria el restaurant i afegeix els plats que vulguis demanar al carro.</span></br>
<span class="negrete">
3 Afegeix begudes a la comanda.</span></br>
<span class="negrete">
4 Digue’ns a quina hora vols que et portem la comanda (o quan la vols passar a recollir pel restaurant)</span></br>
<span class="negrete">
5 Confirma la comanda</span></br>
<span class="negrete">
Es pot fer una comanda per telèfon?</span></br>

-No. El telèfon és unicament pe donar atenció al client. I si te problemes amb els sistema web.</br></br>
<span class="negrete">
Quines son les despeses d’enviament?</span></br>

-4,90 euros.</br></br>
<span class="negrete">
Puc anar a recollir jo la comanda al restaurant?</span></br>

-Sí. T’estalviaràs les despeses d’enviament.</br></br>
<span class="negrete">
Puc demanar de més d’un restaurant?</span></br>

-Pots demanar com a màxim de dos restaurants en una sola comanda. En aquest cas les despeses d’enviament s’incrementen 2,45 euros (total = 7,35 euros)</br></br>
<span class="negrete">
Com funcionen les franges d’entrega?</span></br>

-Quan estiguis fent la comanda veuràs les franges d’entrega que estàn disponibles. Les que no ho estàn no les podràs triar. Una franja no està disponible per dos motius: perquè el restaurant no serveix comandes a aquella hora o perquè tots els nostres repartidors estàn ocupats. Com més aviat facis la comanda més fàcil serà que puguis triar la franja.</br></br>
<span class="negrete">
Puc fer una comanda per un altre dia?</span></br>

-Sí. Quan triïs la franja podràs canviar el dia i veure els següents dies del calendari disponibles.</br></br>
<span class="negrete">
Puc fer una comanda per dinar al migdia?</span></br>

-No. De moment només treballem a les nits.</br></br>
<span class="negrete">
Puc modificar o cancel·lar una comanda després de confirmarla?</span></br>

-No. El nostre sistema transmet automàticament les comandes rebudes cap als restaurants i nosaltres planifiquem totes les nostres rutes d’acord a les comandes que tenim. Contacta amb atenció al client per trobar una solució.</br></br>
<span class="negrete">
Els preus dels plats a Portamu són els mateixos que al restaurant?</span></br>

-Sí, els preus a Portamu són exactament els mateixos que trobaràs a les cartes dels restaurants.</br></br>
<span class="negrete">
Com pago?</span></br>

-Per comandes a domicili pots pagar amb targeta de crèdit a través de la web o en efectiu quan el repartidor el porti la comanda. En cas de que la comanda sigui a recollir al restaurant, només podràs pagar amb targeta a la web quan facis la comanda.</br></br>
<span class="negrete">
Com es fan servir els codis de promoció/oferta?</span></br>

-Abans de confirmar la comanda veuràs les promocions de les que disposes. Per activar-les només has de clicar la promoció que tens disponible o introduïr el codi.</br></br>
<span class="negrete">
Què signifiquen els símbols que hi ha sota cada plat?</span></br>

<img class="imgfaq" src="<c:url value='/img/elements/vegetaria.png' />">
-Logo vegetarià: plat apte per vegetarians.</br></br>

<img class="imgfaq" src="<c:url value='/img/elements/celiac.png' />">
-Logo celíacs: plat apte per celíacs.</br></br>

<img class="imgfaq" src="<c:url value='/img/elements/llet.png' />">
-Logo llet: plat apte per al·lèrgics a la llet.</br></br>

<img class="imgfaq" src="<c:url value='/img/elements/ous.png' />">
-Logo ou: plat apte per al·lèrgics a l’'ou.</br></br>

<img class="imgfaq" src="<c:url value='/img/elements/fruits.png' />">
-Logo fruits secs: plat apte per al·lèrgics als fruits secs.</br></br>

Degut a la logística dels restaurants, no es pot garantitzar la ausencia de traces d'algun dels ingredients alergens.</br></br>

<span class="petit">
(*) los ingredientes mencionados en cada uno de los platos han sido proporcionados a PORTAMU por cada uno de los restaurantes por lo que PORTAMU no se hace responsable de la posible variación sin prèvio aviso de alguno de los ingredientes por parte de los restaurantes.</span></br></br>

<span class="negrete">
Puc fer modificacions dels plats?</span></br>

-Disposes d’un camp al que pots afegir comentaris que s’enviaràn al restaurant. Aquest camp és per peticions raonables com per exemple demanar que es posi la salsa part.</br></br>
<span class="negrete">
Com puc contactar amb Portamu si tinc dubtes sobre la meva comanda o si hi ha una incidència?</span></br>

-Contacta amb el telèfon d’atenció al client.</br></br>

Per qualsevol suggerència o comentari per millorar el nostre servei pots enviar un mail a <a class="more"href="mailto:hola@portamu.com">hola@portamu.com</a></br> 

</div>
</div>
<c:import url="/pages/includes/endPage.jsp" />
</div>
<script src="<c:url value='/js/jsdivlogin.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/auxiliars/jsauxiliars.js' />" type="text/javascript"></script>
</body>
</html>