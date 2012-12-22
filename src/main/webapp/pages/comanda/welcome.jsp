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
	<title>Welcome</title>	
</head>
<body>

<div align="left">
<table>
	<c:if test="${nameAuth eq 'anonymousUser' }">
		<tr>
			<td colspan='2'><a name="register" type="button" href="<c:url value='/preRegisterUser.action'/>" >Register</a>
						</td>
			<td colspan='2'><a name="register" type="button" href="<c:url value='/login.action'/>" >Login</a>
						</td>
		</tr>
	</c:if>		
	<c:if test="${nameAuth ne 'anonymousUser' }">
		<tr>		
			<td><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></td>
		</tr>	
	</c:if>
		<tr>		
			<td><a href="#" onclick="history.go(-1);" > Home</a></td>
		</tr>
</table>
</div>

<div id='coin-slider'>
	    <a href="<c:url value='/images/presentacio/image1.jpg' />" target="_blank">
	        <img src='<c:url value='/images/presentacio/image1.jpg' />' >
	 
	    </a>
	    <a href="<c:url value='/images/presentacio/image2.jpg' />">
	        <img src='<c:url value='/images/presentacio/image2.jpg' />' >
	     
	    </a>
	     <a href="<c:url value='/images/presentacio/image3.jpg' />">
	        <img src='<c:url value='/images/presentacio/image3.jpg' />' >
	  
	    </a>
	     <a href="<c:url value='/images/presentacio/image4.jpg' />">
	        <img src='<c:url value='/images/presentacio/image4.jpg' />' >
	       
	    </a>
	     <a href="<c:url value='/images/presentacio/image5.jpg' />">
	        <img src='<c:url value='/images/presentacio/image5.jpg' />' >
	       
	    </a>
	     <a href="<c:url value='/images/presentacio/image6.jpg' />">
	        <img src='<c:url value='/images/presentacio/image6.jpg' />' >
	      
	    </a>
	     <a href="<c:url value='/images/presentacio/image7.jpg' />">
	        <img src='<c:url value='/images/presentacio/image7.jpg' />' >	      
	    </a>
</div>
<div id="draggables_pl" style="width: 500px; height: 400px;" align="left" >
<s:iterator value="platList" var="plat">
<div class="selector ui-widget-content" id="draggable_${plat.id}" >
	<table>
		<tr><td rowspan="5" ><a href="#"><image src="<c:url value='/images/shopping_cart.png' />" ></image></a></td></tr>
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

<div id="userOptions"  class="ui-widget-header">
  <a href="<c:url value='/user/comandesPasades.action' />" ><img src="<c:url value='/images/shopping_cart.png' />" />Mira les comandes anteriors </a>
</div>

</div>

<!-- Scripts --> 
<c:if test="${fn:contains(header.Host,'7070')}">
	<link rel="stylesheet" href="<c:url value='/css/coin-slider-stylesMim.min.css' />" type="text/css" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/js/jsQuery.min.js' />" type="text/javascript"></script>
</c:if>
<c:if test="${fn:contains(header.Host,'9090')}">
	<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
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
</c:if>

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
	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>

<script type="text/javascript" >
$(function() {

$( ".selector" ).dblclick(function() {
		
		var dragBeguda =$(this).clone();
		dragBeguda.appendTo("#droppable");
		
		dragBeguda.animate({
							    width: "90%",
							    opacity: 0.4,
							    marginLeft: "0.6in",
							    fontSize: "3em",
							    borderWidth: "10px",
							    left: "+=250px"
	  						}, 1800,function() {	  								  						
	  								var item_id = $(this).attr("id");  
	  								var rawPlat = item_id.split("_");
	  								savePlatToComanda(rawPlat[1]);
	      							$(this).css("visiblity","hidden");
	      							$(this).css("display","none");
	    					});
	  	
	});

	function savePlatToComanda(idPlat){
		
		var data ="idPlat="+idPlat+"&idComanda="+$("#numComanda").text();
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
           					alert(json.alerta);
           				}else{
           					//Posem id en el local storage
           					window.localStorage.setItem("comanda",json.numComanda);
           					window.localStorage.setItem("comanda.preu",json.preu);
           					window.localStorage.setItem("comanda.numplats",json.numPlats);
           					
           					$("#numComanda").text(json.numComanda);
           					$("#numplats").text(json.numPlats);
           					$("#preu").text(json.preu);
           				}
           			}				
      		  },
      		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
        								$("#errorsajax").show();  		
      		  					}
      		});	
	}

    $( ".selector" ).draggable({
    	 helper:'clone',
    	 start: function(event, ui) {				
 	    	 var id= $(this).attr("id");
 	    }, 	  
 	    stop: function(event, ui) { 	
 	      	    	
 	    }
    });
    $( "#droppable" ).droppable({
        drop: function( event, ui ) {
            
            var item_id = ui.draggable.attr("id");  
            var rawPlat = item_id.split("_");           
            savePlatToComanda(rawPlat[1]);
             
        }
    });
});

		//Carrega del cistell de compra 
		$("#numComanda").text(${idComanda});
		$("#numplats").text(${fn:length(comanda.plats)});
		$("#preu").text(${comanda.preu});

		function goToComandaPas1(){
			window.location.href="/onlineBot/comanda/goToPas1Action.action?idComanda="+$("#numComanda").text();
		}
		
	    $(document).ready(function() {
	        $('#coin-slider').coinslider();
	    });

	    var comanda = window.localStorage.getItem("comanda");
	    
	    if(comanda != 'undefined' && comanda != null){
					
	    	$("#numComanda").text(comanda);
	    	
	    	var preu = window.localStorage.getItem("comanda.preu");
	    	if(preu != 'undefined' && preu != null){
	    		$("#preu").text(preu);		
	    	}
			
	    	var numplats = window.localStorage.getItem("comanda.numplats");
	    	if(numplats != 'undefined' && numplats != null){
	    		$("#numplats").text(numplats);		
	    	}
	    	
	    	var numbegudes = window.localStorage.getItem("comanda.numbegudes");
	    	if(numbegudes != 'undefined' && numbegudes != null){
	    		$("#numbegudes").text(numbegudes);		
	    	}
		}
	    
	  
	       
</script>

</body>
</html>
