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
	<title><s:text name="txt.info.comanda.title" /></title>
</head>
<body>
<h2><s:text name="txt.info.comanda.user" /></h2>
<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
</div>
<br>
<table>
	<tr>		
			<td><a href="#" onclick="history.go(-1);" > Home</a></td>
	</tr>
</table>	
<div style="position:relative; left: 30px;">
	<div id="slider" style=" height: 500px;"  >
	    <ul>
	    	<s:iterator value="refrescList" var="refresc">
	    			<li class="draggable" id="${refresc.idSub}" ><img id="imageRefresc_${refresc.id}" width="200px"  src="/onlineBot/comanda/ImageAction.action?imageId=${refresc.id}" title="${refresc.descripcio} -> Double Click to Add" /></li>
	        </s:iterator>	
	    </ul>
	</div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div id="droppable"  class="ui-widget-header abs">

  <a href="#" onclick="saveBegudaToComanda();" >  <img src="<c:url value='/images/shopping_cart.png' />"  height="20px" ></img></a>
  		<br>
	   <s:text name="comanda.num.id" />:<label id="numComanda"></label>
	    <br>
	   <s:text name="comanda.num.plats" />:<label id="numplats" ></label>
	   <br>
	   <s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>
	    <br>
	   <s:text name="comanda.preu" />:<label id="preu" ></label>
</div>
<br>
<br>
<div id="plats" class="abs">
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
</div>
<div id="begudes" class="abs">
</div>

<br>
	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<div id="map_canvas" style="float: right; height: 200px; width: 400px;"></div>				                
					                <table>
						                <tr>
						                	<td>						                	
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
						                    </td>
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
	<s:form action="checkComanda" method="POST" enctype="multipart/form-data" >
					<td><s:text name="comanda.dia" ></s:text></td>
					<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
					
					<td><s:text name="comanda.hora" ></s:text></td>										
					<td>
						<s:select list="horaList" key="comanda.hora" listKey="descripcio" listValue="descripcio" theme="simple" >					
									</s:select>
					</td>																
					<s:hidden key="comanda.id" id="idcomanda" ></s:hidden>	                   
					<s:hidden key="comanda.address" id="comandaddress"></s:hidden>			
																				
					<tr><td><input type="button"  onclick="checkComandaJS();" value="Check Comanda" /></td><td>  <div id="chargeBar"></div></td></tr>
					
					<tr><td><input type="button"  onclick="checkComandaJS();" value="Pay Comanda" /></td><td></td></tr>
	</s:form>	
	<br>

	<c:if test="${nameAuth eq 'anonymousUser' }">
		<h1><s:text name="txt.logate" /></h1>
		<form name='f' id="f" action="/onlineBot/j_spring_security_check" method='POST'>
			<table>
				<tr>
					<td><s:text name="txt.user.of.login" />:</td>
					<td><input type='text' name='j_username' value=''>
					</td>
					<td><s:text name="txt.pass.of.login" />:</td>
					<td><input type='password' name='j_password' />
					</td>
					<td colspan='2'><input name="submit" type="button"
						value="submit" onclick="submitLog()" />
					</td>
				</tr>			
			</table>
	 		<label id="loged" ></label>
		</form>
	</c:if>
<!-- Scripts --> 
<c:if test="${fn:contains(header.Host,'7070')}">
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />	
	<script src="<c:url value='/js/jsQuery.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/calendarInput.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/addressFunctions.min.js' />" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>	
</c:if>
<c:if test="${fn:contains(header.Host,'9090')}">

	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/sudoSlider.css' />"  />	
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/progress.css' />" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/infoComanda.css' />" />

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
	
	<!-- Sliders de begudes -->
	<script src="<c:url value='/js/sudoSlider/jquery.sudoSlider.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/progressbar/progress.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/pages/comanda/jsinfoComanda.js'/>"></script>	
</c:if>

</body>
</html>
