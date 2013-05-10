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
	<script src="<c:url value='/js/jswelcome.ini.first.min.js'/>" type="text/javascript"></script>
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
			<c:import url="/pages/includes/menuHeader.jsp" />
			<c:import url="/pages/includes/divLanguage.jsp" />
			<c:import url="/pages/includes/homeSlider.jsp" />
			
		<div id="content_seccion">			
		
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
											<a href="#" id="${restaurant.id}" class="entrar_in selector_jq" ><s:text name="txt.inicia.comanda" /></a>
											<input type="hidden" id="dataObert_${restaurant.id}" value="${dataAvui}" />
										</c:if>
										<c:if test="${config.data ne dataAvui }">	
											<c:set var="close" value="true"/>				
											<a href="#" id="${restaurant.id}" class="entrar_CLOSE selector_jq" ><s:text name="txt.inicia.comanda.tal.dia" />&nbsp; ${fn:substring(config.data, 5, 10)}</a>
											<input type="hidden" id="dataObert_${restaurant.id}" value="${config.data}" />
										</c:if> 
										<c:set var="doneLoop" value="true"/>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty restaurant.configRestaurants}">
								<a href="#" id="${restaurant.id}" class="entrar_in selector_jq"><s:text name="txt.inicia.comanda" /></a>
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
	  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
	  							<p>${restaurant.descripcio}</p>
	  						</c:if>
	  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
	  							<p>${restaurant.descripcioES}</p>
	  						</c:if>
	  					</div>
	  				</c:if>
	  				<c:if test="${close==false}">
	  					<div class="titol_Rest">
	  						<h1>${restaurant.nom}</h2>
	  					</div>
	  					<div class="cos_Rest">
	  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
	  							<p>${restaurant.descripcio}</p>
	  						</c:if>
	  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
	  							<p>${restaurant.descripcioES}</p>
	  						</c:if>
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
					<div id="form_arribem">
						<h2><s:text name="txt.welcome.arribem.acasa" /></h2><br>
						<form class="form" action="#">
						<s:text name="adreca.carrer" />:<br> <input class="inputs" type="text" name="FirstName" id="carrer" ><br>
						<s:text name="adreca.codipostal" />:<br> <input class="inputs" type="text" name="LastName" id="codi" ><br>
						<input class="boton" id="checkAdd" type="button" value="<s:text name="txt.comprobaho.in" />"><br>
						<label id="addressOK"></label>
						<input type="hidden" id="poble" disabled="disabled" value="Girona"  />						
						</form>
					</div>
					<div id="map_canvas" style="float: left; height: 200px; width: 200px; display:none;"></div>	
					<img src="img/elements/bar5.png">
					<div id="newsletter">
						<h2><s:text name="txt.welcome.newsletter.title" /></h2><br>
						<form class="form" action="#">
						<s:text name="txt.welcome.newsletter.title2" /><br> <br>
						<s:text name="txt.welcome.mail" />:<br>
						<input class="newsin" type="text" id="email" /><br>
						<input class="boton" type="button" onclick="saveEmail()" value="<s:text name="txt.welcome.newsletter.ins" />" />
						<label id="error"></label>
						</form>
					</div>
				</div>			
			</div>
		</div>		
	</div>
</div>	
<c:import url="/pages/includes/endPage.jsp" />
	<script src="https://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script src="<c:url value='/js/jswelcome.ini.second.min.js'/>" type="text/javascript"></script>	
<script type="text/javascript" >
new Address.addressValidation("<s:text name='txt.addressOK' />","<s:text name='txt.addressKO' />");
var initParams = new InitParams("<s:text name='txt.comanda.existeix.vol.continuar' />","<s:text name='txt.welcome.confirmar' />",
								"<s:text name='txt.welcome.productes' />","<s:text name='txt.welcome.producte' />",
								"<s:text name='txt.comment.saved' />","<s:text name='txt.email.wrong' />","<s:text name='txt.avis.restaurant.tancat' />");
</script>
<c:import url="/pages/includes/confirmOnline.jsp" />
<c:import url="/pages/includes/errorAjax.jsp" />
<c:import url="/pages/includes/alertOnline.jsp" />
<script type="text/javascript" >
	var userExist="${requestScope.userExist}";
	if(userExist=='true'){
		alertOnline.alertes("<s:text name='txt.user.exist' />");		
	}
</script>
</body>
</html>