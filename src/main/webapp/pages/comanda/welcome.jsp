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
	<title><s:text name="txt.welcome.comanda.principal" /></title>	
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<c:import url="/pages/includes/goHome.jsp" />
<c:import url="/pages/includes/homeSlider.jsp" />

<div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
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
</div>


<div id="droppable"  class="ui-widget-header abs">
  <a href="#" onclick="goToComandaPas1();" >  <image src="<c:url value='/images/shopping_cart.png' />" ></image></a>
    	<br>
	   <s:text name="comanda.num.id" />:<label id="numComanda"></label>
	    <br>
	   <s:text name="comanda.num.plats" />:<label id="numplats" ></label>
	   <br>
	   <s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>
	    <br>
	   <s:text name="comanda.preu" />:<label id="preu" ></label>
</div>

<c:import url="/pages/includes/goLookComanda.jsp" />

</div>
<div id="infoPlat_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  

<!-- Scripts --> 
	
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
	</script>
	<script src="<c:url value='/pages/comanda/jswelcome.js'/>" type="text/javascript"></script>
	
	
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>
