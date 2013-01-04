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
	<title> <s:text name="txt.welcome.principal" /></title>	
</head>
<body>

<c:import url="/pages/includes/divLogin.jsp" />
<c:import url="/pages/includes/homeSlider.jsp" />

<div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
<s:iterator value="restaurantList" var="restaurant">
<div class="selector" id="${restaurant.id}" >
	<table>
		<tr><td rowspan="5" ><a href="#">Go to view plats</a></td></tr>
		<tr>
			<td>${restaurant.nom}</td>			
			<td rowspan="2" ><img id="imageRestaurant" width="200px"  src="/onlineBot/comanda/ImageAction.action?imageId=${restaurant.foto.id}" /></td> 
		</tr>
		<tr>
			<td colspan="3" >${restaurant.descripcio}</td>
		</tr>
	</table>
</div>
</s:iterator>
<c:import url="/pages/includes/goLookComanda.jsp" />
</div>

<!-- Scripts --> 

	<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>

	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>

<script type="text/javascript" >
$(function() {

$( ".selector" ).click(function() {
	var id = $(this).attr("id");
	var comanda = window.localStorage.getItem("comanda");
	if(comanda != 'undefined' && comanda != null){
		if (confirm(" <s:text name='txt.comanda.existeix.vol.continuar' />")) {
			window.location.href="/onlineBot/comanda/Welcome.action?restaurantId="+id+"&idComanda="+comanda;		
		}else{
			window.localStorage.removeItem("comanda");
			window.localStorage.removeItem("comanda.numplats");
			window.localStorage.removeItem("comanda.preu");
			window.localStorage.removeItem("comanda.numbegudes");
			window.localStorage.clear();
			
			window.location.href="/onlineBot/comanda/Welcome.action?restaurantId="+id;
		}
	}else{
		window.location.href="/onlineBot/comanda/Welcome.action?restaurantId="+id;
	}
	
	  	
	});
});

$(document).ready(function() {
    $('#coin-slider').coinslider();
});
</script>

</body>
</html>