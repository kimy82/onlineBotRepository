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
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<c:import url="/pages/includes/homeSlider.jsp" />

<div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
<s:iterator value="restaurantList" var="restaurant">
	<div class="selector" id="${restaurant.id}" >
		<table>
			<tr><td><img id="imageRestaurant" width="50px"  src="/${initParam.app}/images/star${restaurant.votacio.punctuacio}.jpg" /></td></tr>
			<c:if test="${not empty restaurant.configRestaurants}">
				<c:set var="doneLoop" value="false"/>
				
				<c:set var="configs" value="${restaurant.configRestaurants}" ></c:set>
				
				
				<c:forEach items="${configs}" var="config" >
					
					<c:if test="${config.obert==true && not doneLoop}">
					
						<c:if test="${config.data eq dataAvui }">
							<tr><td rowspan="5" ><a href="#"><s:text name="txt.inicia.comanda" /></a></td></tr>
							<input type="hidden" id="dataObert_${restaurant.id}" value="${dataAvui}" />
						</c:if>
						<c:if test="${config.data ne dataAvui }">					
							<tr><td rowspan="5" ><a href="#"><s:text name="txt.inicia.comanda.tal.dia" />&nbsp; ${config.data}</a></td></tr>
							<input type="hidden" id="dataObert_${restaurant.id}" value="${config.data}" />
						</c:if>
						<c:set var="doneLoop" value="true"/>
						
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty restaurant.configRestaurants}">
				<tr><td rowspan="5" ><a href="#"><s:text name="txt.inicia.comanda" /></a></td></tr>
				<input type="hidden" id="dataObert_${restaurant.id}" value="${dataAvui}" />
			</c:if>			
			<tr>
				<td>${restaurant.nom}</td>			
				<td rowspan="2" ><img id="imageRestaurant" width="200px"  src="/${initParam.app}/comanda/ImageAction.action?imageId=${restaurant.foto.id}" /></td> 
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
	<script src="<c:url value='/js/jquery/jquery.ui.dialog.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>

	<script src="<c:url value='/jswelcome.js'/>" type="text/javascript"></script>
<script type="text/javascript" >

var initParams = new InitParams("<s:text name='txt.comanda.existeix.vol.continuar' />");

</script>
<c:import url="/pages/includes/confirmOnline.jsp" />
</body>
</html>