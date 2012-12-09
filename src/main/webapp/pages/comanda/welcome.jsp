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
<script type="text/javascript" >
$(function() {
	
	var originalTop=null;
	var originalLeft =null;
	
    $( ".selector" ).draggable({
    	 start: function(event, ui) {
				
 	    	 var id= $(this).attr("id");
 		 	 originalTop = $("#"+id).position().top;
 		   	 originalLeft = $("#"+id).position().left;
 	    }, 	  
 	    stop: function(event, ui) { 	
 	    	$(this).appendTo("#draggables_pl");  	    	
 	    }
    });
    $( "#droppable" ).droppable({
        drop: function( event, ui ) {
            
            var item_id = ui.draggable.attr("id");  
            var rawPlat = item_id.split("_")
            data ="idPlat="+rawPlat[1]+"&idComanda="+$("#numComanda").val();
        	$.ajax({
        		  type: "POST",
        		  url: '/onlineBot/comanda/ajaxLoadPlat.action',
        		  dataType: 'json',
        		  data: data,
        		  success: function(json){	
        			  if(json==null || json.error!=null){
             				$("#errorsajaxlabel").text(json.error);
             				$("#errorsajax").show();
             			}else{
             				if(json.alerta!=null){
             					
             				}else{
        					 	json.numPlats;
             				}
             			}				
        		  },
        		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
          								$("#errorsajax").show();  		
        		  					}
        		});	
             
        }
    });
});

</script>
</head>
<body>
<div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
<s:iterator value="platList" var="plat">
<div class="selector ui-widget-content" id="draggable_${plat.id}" >
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

<div id="droppable"  class="ui-widget-header abs">
    <image src="<c:url value='/images/shopping_cart.png' />" ></image>
	    <label id="numComanda"></label>
	    <label id="numplats" ></label>
</div>

</div>

</body>
</html>
