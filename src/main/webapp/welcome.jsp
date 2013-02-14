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
	<title> <s:text name="txt.welcome.principal" /></title>	
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">

	<div id="content">
		<!-- menu -->
			<c:import url="/pages/includes/menuHeader.jsp" />
		<!-- END menu -->
		<!-- Language -->
			<c:import url="/pages/includes/divLanguage.jsp" />
		<!-- END language -->
		<c:import url="/pages/includes/homeSlider.jsp" />
		<!-- Content seccio-->
		<div id="content_seccion">
			<!-- seccion Restaurants-->
			<div id="seccion">
			
				<div id="rest">
					<s:text name="txt.welcome.title.restaurants" />				
				</div>
				<s:iterator value="restaurantList" var="restaurant">
				<c:set var="close" value="false"/>
				<div class="iterate_Rest"  >
					<div class="img_Rest" style="background-image: url(/${initParam.app}/comanda/ImageAction.action?imageId=${restaurant.foto.id});" >
						<div class="enter">
							<c:if test="${not empty restaurant.configRestaurants}">
								<c:set var="doneLoop" value="false"/>
								
								<c:set var="configs" value="${restaurant.configRestaurants}" ></c:set>
				
				
								<c:forEach items="${configs}" var="config" >
					
									<c:if test="${config.obert==true && not doneLoop}">
					
										<c:if test="${config.data eq dataAvui }">
											<a href="#" id="${restaurant.id}" class="entrar selector_jq" ><s:text name="txt.inicia.comanda" /></a>
											<input type="hidden" id="dataObert_${restaurant.id}" value="${dataAvui}" />
										</c:if>
										<c:if test="${config.data ne dataAvui }">	
											<c:set var="close" value="true"/>				
											<a href="#" id="${restaurant.id}" class="entrar_CLOSE selector_jq" ><s:text name="txt.inicia.comanda.tal.dia" />&nbsp; ${config.data}</a>
											<input type="hidden" id="dataObert_${restaurant.id}" value="${config.data}" />
										</c:if> 
										<c:set var="doneLoop" value="true"/>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty restaurant.configRestaurants}">
								<a href="#" id="${restaurant.id}" class="entrar selector_jq"><s:text name="txt.inicia.comanda" /></a>
								<input type="hidden" id="dataObert_${restaurant.id}" value="${dataAvui}" />
							</c:if>	
							<br>
							<img class="estrelles_rest" src="/${initParam.app}/img/elements/estrelles${restaurant.votacio.punctuacio}.jpg">						
						</div>
					</div>
	  			
	  				<c:if test="${close==true}">
		  				<div class="titol_Rest_CLOSE">
		  					<h1>${restaurant.nom}</h2>
		  				</div>
	  					<div class="cos_Rest_CLOSE">
	  						<p>${restaurant.descripcio}</p>
	  					</div>
	  				</c:if>
	  				<c:if test="${close==false}">
	  					<div class="titol_Rest">
	  						<h1>${restaurant.nom}</h2>
	  					</div>
	  					<div class="cos_Rest">
	  						<p>${restaurant.descripcio}</p>
	  					</div>
	  				</c:if>
	  				
				</div>
				</s:iterator>	
				<c:import url="/pages/includes/pagination.jsp" />						
		</div>
		
		<div id="left">
				<div id="bar_left">
					<img src="img/elements/bar3.png">
				</div>
				<div id="bar_right">
					<!--Form-->
					<div id="form_arribem">
						<h2><s:text name="txt.welcome.arribem.acasa" /></h2><br>
						<form class="form" action="#">
						<s:text name="adreca.carrer" />:<br> <input class="inputs" type="text" name="FirstName" id="carrer" ><br>
						<s:text name="adreca.codipostal" />:<br> <input class="inputs" type="text" name="LastName" id="codi" ><br>
						<input class="boton" id="checkAdd" type="button" value="COMPROVA-HO">
						<input type="hidden" id="poble" disabled="disabled" value="Girona"  />
						</form>
					</div>
					<div id="map_canvas" style="float: right; height: 200px; width: 200px; display:none;"></div>	
					<!--End Form-->
					<img src="img/elements/bar5.png">
					<!--Newssletter-->
					<div id="newsletter">
						<h2><s:text name="txt.welcome.newsletter.title" /></h2><br>
						<form class="form" action="#">
						<s:text name="txt.welcome.newsletter.title2" /><br> <input class="newsin" type="text" value="<s:text name="txt.welcome.newsletter.value" />" id="email" >
						<input class="news" type="button" onclick="saveEmail()" value="">
						</form>
					</div>
					<!--End RIght-->
				</div>
			
			</div>
		</div>
		<!-- END Content seccio-->
	</div>
	<!-- END Content-->
</div>	
<!-- END container -->
<div id="footer">
	<div id="footer_int">
		<div id="footer_rigth">
		
		</div>
	</div>
</div>
<div id="credits">
	<div id="int_credits">
		<s:text name="txt.footer.reserved" />
	</div>
</div>

	<!-- CSS portamu --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/prova.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<!-- CSS portamu -->
	
	<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />
	<!--  link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /-->
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.dialog.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.js' />"></script>
	<!-- adress validation -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/js/address/addressValidationFormbis.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>

	<script src="<c:url value='/jswelcome.js'/>" type="text/javascript"></script>
<script type="text/javascript" >
new Address.addressValidation();
var initParams = new InitParams("<s:text name='txt.comanda.existeix.vol.continuar' />","<s:text name='txt.welcome.confirmar' />",
								"<s:text name='txt.welcome.productes' />","<s:text name='txt.welcome.productes' />",
								"<s:text name='txt.comment.saved' />","<s:text name='txt.email.wrong' />");
</script>
<c:import url="/pages/includes/confirmOnline.jsp" />
<c:import url="/pages/includes/errorAjax.jsp" />
<c:import url="/pages/includes/alertOnline.jsp" />
</body>
</html>