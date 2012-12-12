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

<!-- Calendari -->  
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
<script type="text/javascript" src="<c:url value='/js/calendari/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-cat.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-es.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-idioma.js'/>"></script>		
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-setup.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />

<!-- Per validar l'adreca -->
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script src="<c:url value='/js/address/addressValidation.js'/>" type="text/javascript"></script>
 <script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>

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
</style>
<script type="text/javascript" >
function submitLog(){
	
	$.ajax({
	    url: "<c:url value='/onlineBot/j_spring_security_check' />",
	    type: "POST",
	    data: $("#f").serialize(),
	    dataType: 'json',
	    beforeSend: function (xhr) {
	        xhr.setRequestHeader("X-Ajax-call", "true");
	    },
	    success: function(json) {
	        if (json.result == "ok") {
	        	$("#loged").text("OK, Validació correcte");
	             console.log("ssss");
	        } else if (json.result == "error") {
	        	console.log("error");
	        	$("#loged").text("KO, Validació incorrecte");
	        }
	    }
	});
	
}
</script>
</head>

<body>
<h2>INFO</h2>
<s:iterator value="platComandaList" var="platComanda">
	<div class="selector" id="plat_${platComanda.plat.id}" >
		<table>
			<tr>
				<td>${platComanda.plat.nom}</td>
				<td>${platComanda.plat.preu} </td>
				<td>${platComanda.numPlats}</td>
				<td>${platComanda.plat.tipus}</td>			
			</tr>
			<tr>
				<td colspan="3" >${platComanda.plat.descripcio}</td>
			</tr>
		</table>
	</div>
</s:iterator>
<br>
<div class="page">
					        <div id="main">
					            <form id="MyForm" name="MyForm" action="form.html">
					                <div id="map_canvas" style="float: right; height: 200px; width: 400px;"></div>				                
					                <table>
						                <tr>
						                	<td>
						                	<label for="FullAddress">
                        							Valida el carrer,poble i el codi postal</label>
                        					<label for="carrer">
                        							Carrer</label>		
                        					<input type="text" id="carrer"  />  
                        					
                        					<label for="numcarrer">
                        							 Num Carrer</label>		
                        					<input type="text" id="numcarrer"  />
                        					
                        					<label for="codi">
                        							 Codi postal</label>		
                        					<input type="text" id="codi"  />
                        					
                        					<label for="poble">
                        							 Poble</label>		
                        					<input type="text" id="poble"  />
                        							
                    						<textarea id="FullAddress" name="FullAddress" cols="40" rows="5" class="fulladdressvalidator"></textarea>							    										                
						                    </td>
						                </tr>
						                <tr>
							                <td>				                
							                    <input id="Submit" name="Submit" value="Comprova si és correcte" type="button" />
							                </td>
							                <td>
							                <label id="addressOK" ></label>
							                </td>    
						                </tr>
					                </table>
					            </form>
					        </div>
					    </div>
<s:form action="checkComanda" method="POST" enctype="multipart/form-data" >
					<td><s:text name="comanda.dia" ></s:text></td>
					<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
					
					<td><s:text name="comanda.hora" ></s:text></td>										
					<td>
						<s:select list="horaList" key="comanda.hora" listKey="descripcio" listValue="descripcio" theme="simple" >					
									</s:select>
					</td>							
					<tr>
						
					
					</tr>				
					<s:hidden key="comanda.id" id="idcomanda" ></s:hidden>	                   
					<s:hidden key="comanda.address" id="comandaddress"></s:hidden>																		
					<s:submit></s:submit>
</s:form>	
<br>

<c:if test="${nameAuth eq 'anonymousUser' }">
<h1>Logate</h1>
	<form name='f' id="f" action="/onlineBot/j_spring_security_check"
		method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="button"
					value="submit" onclick="submitLog()" />
				</td>
			</tr>			
		</table>
 		<label id="loged" ></label>
	</form>
</c:if>

<script>			

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
</script> 
</body>
</html>
