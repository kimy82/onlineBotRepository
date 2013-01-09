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
	<title>User comandes</title>
</head>

<body>	
<br>
<table id="tbl_platscomanda" >
<h1>PLats</h1>
<s:iterator value="comanda.plats" var="plat" >
<tr class="item" >
<td>${plat.nom}</td>
<td>${plat.tipus}</td>
<td>${plat.preu}</td>
<td><input type="text" id="${plat.id}_num" class="idPlat" onblur="changePlat(this.id, this.value);"  value="" /></td>
<td>${plat.descripcio}</td>
</tr>
</s:iterator>
</table>
<br>
<h1><textarea rows="7" cols="30" id="observacions">${comanda.observacions}</textarea> </h1>
<br>
<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<div id="map_canvas" style="float: right; height: 200px; width: 400px;"></div>			
						                
					                <table>
					                	<tr>
						                	<td>Adreça anterior</td>
						                	<td><input type="text"  value="${comanda.address}" /></td>
						                	<td> <input type="checkbox" onclick="openCloseDiv('newAddress')" /> </td>
					                	</tr>
					              </table>
					              <div id="newAddress" >
					              							                	
	                        					<label for="carrer">
	                        							 <s:text name="adreca.carrer" /></label>		
	                        					<input type="text" id="carrer"  />  
	                        					
	                        					<label for="numcarrer">
	                        							  <s:text name="adreca.numcarrer" /></label>		
	                        					<input type="text" id="numcarrer"  />
	                        					
	                        					<label for="codi">
	                        							  <s:text name="adreca.codipostal" /></label>		
	                        					<input type="text" id="codi"  />
	                        					
	                        					<label for="poble">
	                        							  <s:text name="adreca.poble" /></label>		
	                        					<input type="text" id="poble"  />                        						                    													    										                
						            
					              </div>
					              <table>
						                </tr>
						                <tr>
							                <td>				                
							                    <input id="checkAdd" value="Comprova si és correcte" type="button" />
							                </td>
							                <td>
							                <label id="addressOK" ></label>
							                </td>    
						                </tr>
					                </table>
				</form>
		</div>
</div>
<table>
	<tr>
		<td><s:text name="comanda.dia" ></s:text></td>
		<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
					<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>					
		<td><s:text name="comanda.hora" ></s:text></td>										
		<td>
			<s:select list="horaList" key="comanda.hora" listKey="descripcio" listValue="descripcio" id="hora" theme="simple" >					
									</s:select>
		</td>
	</tr>																		
</table>													
<s:form action="checkComanda" method="POST" id="comandaform" enctype="multipart/form-data" >
					<s:hidden key="comanda.plats" id="platsHid"></s:hidden>
					<s:hidden key="comanda.dia" id="diaHid" ></s:hidden>
					<s:hidden key="comanda.hora" id="horaHid" ></s:hidden>
					<s:hidden key="comanda.observacions" id="observacionsHid" ></s:hidden>
					<s:hidden key="comanda.id" id="idcomandaHid" ></s:hidden>	                   
					<s:hidden key="comanda.address" id="comandaddressHid" value="" ></s:hidden>																		
					<s:submit onclick="setInfoInForm();" ></s:submit>
</s:form>	

<!-- Scripts --> 


	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />	

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
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<!-- Calendari -->  
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-cat.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-es.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-idioma.js'/>"></script>		
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-setup.js'/>"></script>
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteTown.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/users/recoverComanda/jscomandes.js' />"></script>

<script type="text/javascript" >		

    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });

new Address.addressValidation();
var comanda = new Comanda('${comanda.id}');
$("#comandaddressHid").val('${comanda.address}');
</script>
 
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>