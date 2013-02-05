<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<title><s:text name="txt.welcome.principal" /></title>	
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
			<!-- Content seccio-->
		<div id="content_seccion">
			<!-- seccion Restaurants-->
			<div id="seccion">
				<div id="rest">
					${restaurant.nom }
				</div>
				<div class="selector">
					<ul>
					<li><a class="selec" href="#" onclick="filterPlats('primers')" ><s:text name="txt.plat.primer" /></a></li>
					<li><a href="#" onclick="filterPlats('segons')" ><s:text name="txt.plat.segon" /></a></li>
					<li><a href="#" onclick="filterPlats('postres')" ><s:text name="txt.plat.postre" /></a></li>
					</ul>
				</div>
				<hr class="sep2">
				<s:iterator value="platList" var="plat">
					<c:if test="${plat.actiu == true }" >
						<div class="selector ui-widget-content iterate_Rest" id="draggable_${plat.id}" >
						<div class="img_Rest">
	  						<img  src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}">
	  					</div>
	  					<div class="format">
			  				<div class="titol_Rest">
			  					<h1>${plat.nom}</h2>
			  				</div>
			  				<div class="cos_Rest">
			  					<p id="p_desc_${plat.id}"  >${plat.descripcio}</p><br><br>
			  				</div>
	  					</div>
	  					<div id="afegir">
							<div class="left_price">
								<a href="#" class="entrar"><s:text name="txt.plat.afegir" /></a>
							</div>
							<div class="right_price">
								 <span class="price">${plat.preu} &euro; </span><br>
							</div>
							<hr class="sep">
						</div>
					</c:if>
					<c:if test="${plat.actiu == false }" >
						<div class="selector ui-widget-content iterate_Rest" id="draggable_${plat.id}" title="Plat no actiu per avui" >
						<div class="img_Rest_CLOSE">
	  						<img class="img_CLOSE" src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}">
	  					</div>
	  					<div class="format">
			  				<div class="titol_Rest_CLOSE">
			  					<h1>${plat.nom}</h2>
			  				</div>
			  				<div class="cos_Rest_CLOSE">
			  					<p id="p_desc_${plat.id}" >${plat.descripcio}</p><br><br>
			  				</div>
		  				</div>
		  				<div id="afegir">
							<div class="left_price">
								<a href="#" class="entrar_CLOSE"><s:text name="txt.plat.afegir" /></a>
							</div>
							<div class="right_price">
								 <span class="price">${plat.preu} &euro;</span><br>
							</div>
							<hr class="sep">
						</div>
					</c:if>	
						<div class="coments">
							<img class="estrelles_rest" src="/${initParam.app}/img/elements/estrelles${plat.votacio.punctuacio}.jpg">	
							<br>
							<span class="coments_titol"><s:text name="txt.coments.valoracions" /></span><br>
							<p>								
									<c:set var="comments" value="${plat.comments}" ></c:set>
									<c:forEach items="${comments}" var="comt" begin="0" end="2" >																									
										${fn:substring(comt.comment, 0, 25)}.																																															
									</c:forEach>	
							
							<a href="#" class="more" onclick="goToInfoPlat(${plat.id})" ><s:text name="txt.leer.mas" /></a></p>
						</div>
					</div>				
				</s:iterator>
				<c:import url="/pages/includes/pagination.jsp" />
					<!-- begudes-->
				<div id="begudes">
					<div id="begudes_titol">
						<s:text name="txt.afegir.vi" />
					</div>
					<s:iterator value="begudaList" var="beguda" begin="0" end="2" >
						<hr class="sep4">
						<div class="selectorBeg ui-widget-content iterate_ref" id="draggable_${beguda.id}" >
							<div class="img_Rest">
	  							<img src="/${initParam.app}/comanda/ImageAction.action?imageId=${beguda.foto.id}">
	  						</div>
							<div class="format">
					  				<div class="titol_Rest">
					  					<h1>${beguda.nom}</h2>
					  				</div>
					  				<div class="cos_Rest">
					  					<p>${beguda.descripcio}</p><br><br>
					  				</div>
	  						</div>
							<div id="afegir">
								<div class="left_price">
									<a href="#" class="entrar"><s:text name="txt.plat.afegir" /></a>
								</div>
								<div class="right_price">
									 <span class="price">${beguda.preu} &euro;</span><br>
								</div>
								<hr class="sep">
							</div>							
						</div>
					</s:iterator>
				</div>
			</div>
			
			<!-- Dreta-->			
			<div id="droppable"  class="ui-widget-header">
				<div id="left">
					<div id="bar_left">
						<img src="img/elements/bar3.png">
					</div>
					<div id="bar_right">
						<div id="carreta">
							<div class="car_int">
								<s:text name="txt.carreta" /><label id="numComanda"></label>
							</div>
							<hr class="sep3">
							<div class="car_pro">
								<s:text name="comanda.num.plats" />:<label id="numplats" ></label>
								<div id="disp_plate">
									
								</div>															
								<s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>
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
						
						<!--End RIght-->
					</div>
				
				</div>
			</div>
		</div>
	</div>
</div>

<!-- div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
	<s:iterator value="platList" var="plat">
		<c:if test="${plat.actiu == true }" >
			<div class="selector ui-widget-content" id="draggable_${plat.id}" >
		</c:if>
		<c:if test="${plat.actiu == false }" >
			<div class="selector ui-widget-content" style="background-color: grey;" id="draggable_${plat.id}" title="Plat no actiu per avui" >
		</c:if>
		
			<table>
				<tr><td rowspan="4" ><a href="#"><image src="<c:url value='/images/shopping_cart.png' />" ></image></a></td>
				
					<td><a href="#" onclick="goToInfoPlat(${plat.id})" ><img  src="<c:url value='/images/info.gif' />" /></a></td>
				</tr>
				<tr>
					<td>${plat.nom}</td>
					<td>${plat.preu}</td>
					<td>${plat.tipus}</td>
					<td rowspan="2" ><img id="imageRestaurant" width="200px"  src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}" /></td> 
				</tr>
				<tr>
					<td colspan="3" >${plat.descripcio}</td>
				</tr>
			</table>
		</div>
	</s:iterator>
</div -->


<!-- div id="droppable"  class="ui-widget-header abs">
  <a href="#" onclick="goToComandaPas1();" >  <image src="<c:url value='/images/shopping_cart.png' />" ></image></a>
    	<br>
	   <s:text name="comanda.num.id" />:<label id="numComanda"></label>
	    <br>
	   <s:text name="comanda.num.plats" />:<label id="numplats" ></label>
	   <br>
	   <s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>
	    <br>
	   <s:text name="comanda.preu" />:<label id="preu" ></label>
</div -->


</div>
<div id="infoPlat_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  

<!-- Scripts --> 
	<!-- CSS portamu --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<!-- CSS portamu -->
	<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
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
	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>
	<script type="text/javascript" >
		//Carrega del cistell de compra 
			$("#numComanda").text('${requestScope.idComanda}');
			$("#numplats").text('${requestScope.numPlats}');
			$("#preu").text('${requestScope.comanda.preu}');   
			var dataActual = '${requestScope.dataActual}';
			var idRestaurant = '${requestScope.restaurant.id}';
	</script>
	<script src="<c:url value='/pages/comanda/jswelcome.js'/>" type="text/javascript"></script>
	
	
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>
