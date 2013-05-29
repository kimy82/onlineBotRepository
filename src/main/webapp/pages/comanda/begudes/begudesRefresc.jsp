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
	<title><s:text name="txt.welcome.principal" /></title>	
	<link rel="stylesheet" href="<c:url value='/css/begudesRefresc.min.css' />" type="text/css"   media="screen" />
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');

</script>
</head>
<body id="refresc" >
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
	<div id="content">
				<c:import url="/pages/includes/menuHeader.jsp" />		
				<c:import url="/pages/includes/divLanguage.jsp" />
		<div id="content_seccion">
			<div id="seccion">
				<div id="rest">
					<s:text name="txt.begudes.title.altres" />
				</div>
				<hr class="sep2">
				<s:iterator value="begudaList" var="beguda">
					<div class="iterate_ref" >
							<div class="img_Rest ui-widget-content selectorBeg" id="draggable_${beguda.id}" >
	  							<img height="115" src="/${initParam.app}/comanda/ImageAction.action?imageId=${beguda.foto.id}">
	  						</div>
							<div class="format">
					  				<div class="titol_Rest">
					  					<h1 id="p_desc_beg_${beguda.id}" >
					  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
			  									${beguda.nom}
			  								</c:if>
			  								<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
			  									${beguda.nom}
			  								</c:if>
					  					</h2>
					  				</div>
	  						</div>
							<div id="afegir">
								<div class="left_price">
									<a href="#" class="entrar" onclick="welcomeAction.addProduct('draggable_${beguda.id}')" ><s:text name="txt.plat.afegir" /></a>
								</div>
								<div class="right_price">
									 <span class="price">${beguda.preu} &euro;</span><br>
								</div>								
							</div>											
						</div>
				</s:iterator>
			</div>						
				<div id="left">
					<div id="bar_left">
						<img src="<c:url value='/img/elements/bar3.png' />">
					</div>
					<div id="bar_right">
						<div id="droppable"  class="ui-widget-header">
							<div id="carreta">
								<div class="car_int">
									<s:text name="txt.carreta" /><input type="hidden" id="numComanda"></input>
								</div>
								<hr class="sep3">
								<div class="car_pro">
									<s:text name="comanda.num.plats" />:<label id="numplats" ></label><br></br>
									<s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>									 
									<div id="disp_plate">										
									</div>																								
									<div id="disp_beguda">										
									</div>
								</div>
								<hr class="sep3">
								<div class="car_sub">
									<s:text name="txt.subtotal" /> <br>
									<label id="preu" ></label> &euro; <br><br>
									<a href="#" class="btn_conf" onclick="menuRestaurantAction.goToComandaPas1();"  ><s:text name="txt.comanda.comfirmar" /></a>
								</div>
								<div class="hora">
									<s:text name="txt.propera.hora" />: <br><br>
									<span class="hora_int" id="hora_int" ></span>
								</div>
							</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</div>
<div id="infoBeguda_dialog" class="filtres filtres-oberts">	 		
</div>  
<c:import url="/pages/includes/endPage.jsp" /> 
	<script src="<c:url value='/js/jsbegudesRefresc.min.js'/>" type="text/javascript"></script>
	<script type="text/javascript" >
		var initParams = new InitParams("<s:text name='txt.welcome.confirmar' />","<s:text name='txt.welcome.productes' />",
										"<s:text name='txt.welcome.producte' />","<s:text name='txt.avis.restaurant.tancat' />",
										"<s:text name='txt.comanda.existeix.vol.continuar' />");
	</script>
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
</body>
</html>