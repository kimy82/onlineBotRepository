<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Welcome</title>
  
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

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<style>
.selector {
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
    border-radius: 10px;
    border: blue 2px solid;
    width: 500px;
}
.abs{
position: absolute;
top: 20px;
right: 50px;
width: 400px;
height: 200px;
}
</style>
<script>
$(function() {
    $( ".selector" ).draggable();
    $( "#droppable" ).droppable({
        drop: function( event, ui ) {
            $( this ).addClass( "ui-state-highlight" );
              
              alert( "Dropped!" );
        }
    });
});
function change(){
	 $( "#droppable" ).addClass( "ui-state-highlight" );
}

</script>
</head>
<body>

<s:iterator value="platList" var="plat">
<div class="selector ui-widget-content" id="draggable${plat.id}" >
	<table>
		<tr>
			<td>${plat.nom}</td>
			<td>${plat.preu}</td>
			<td>${plat.tipus}</td>
			<td rowspan="2" ><img id="imageRestaurant" width="200px"  src="/onlineBot/comanda/ImageAction.action?imageId=${plat.foto.id}" /></td> 
		</tr>
		<tr>
			<td colspan="3" >${plat.descripcio}</td>
		</tr>
	</table>
</div>
</s:iterator>
<div id="droppable" onmouseover="change();" class="ui-widget-header abs">
    <image src="<c:url value='/images/shopping_cart.png' />" ></image>
</div>
</body>
</html>
