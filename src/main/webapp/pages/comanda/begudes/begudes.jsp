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
	<title><s:text name="txt.welcome.principal" /></title>	
	<link rel="stylesheet" href="<c:url value='/css/begudes.min.css' />" type="text/css"   media="screen" />
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
</head>
<body id="restaurants" >
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />

<div id="container">

	<div id="content">
				<c:import url="/pages/includes/menuHeader.jsp" />
				<c:import url="/pages/includes/divLanguage.jsp" />
		<div id="content_seccion">
			<div id="seccion">
				<div id="rest">
					<s:text name="txt.begudes.title" />
				</div>
				<hr class="sep2">
				<s:iterator value="begudaList" var="beguda">
					<div class="iterate_Rest_r" >
							<div class="img_Rest ui-widget-content selectorBeg" id="draggable_${beguda.id}" >
	  							<img height="152" width="230" src="/${initParam.app}/comanda/ImageAction.action?imageId=${beguda.foto.id}">
	  						</div>
							<div class="format">
					  				<div class="titol_Rest">
					  					<h1 id="p_desc_beg_${beguda.id}" >${beguda.nom}</h2>
					  				</div>
					  				<div class="cos_Rest">
					  					<p>${beguda.descripcio}</p><br><br>
					  				</div>
	  						</div>
							<div id="afegir">
								<div class="left_price">
									<a href="#" class="entrar" onclick="addProduct('draggable_${beguda.id}')" ><s:text name="txt.plat.afegir" /></a>
								</div>
								<div class="right_price">
									 <span class="price">${beguda.preu} &euro;</span><br>
								</div>
								<hr class="sep">
							</div>
							<div class="comentss">
								<img class="estrelles_rest" src="/${initParam.app}/img/elements/estrelles${beguda.votacio.punctuacio}.jpg">	
								<br>
								<span class="coments_titol"><s:text name="txt.coments.valoracions" /></span><br>
								<p>								
										<c:set var="comments" value="${beguda.comments}" ></c:set>
										<c:forEach items="${comments}" var="comt" begin="0" end="2" >																									
											${fn:substring(comt.comment, 0, 25)}.																																															
										</c:forEach>									
								<a href="#" class="more" onclick="goToInfoBeguda(${beguda.id})" ><s:text name="txt.leer.mas" /></a></p>
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
									<a href="#" class="btn_conf" onclick="goToComandaPas1();"  ><s:text name="txt.comanda.comfirmar" /></a>
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
<script src="<c:url value='/js/jsbegudes.min.js'/>" type="text/javascript"></script>	
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
