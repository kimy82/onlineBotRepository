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
<br>
<c:import url="/pages/includes/goHome.jsp" />
	
<div style="position:relative; left: 30px;">
	<div id="slider" style=" height: 500px;"  >
	    <ul>
	    	<s:iterator value="refrescList" var="refresc">
	    			<li class="draggable" id="${refresc.idSub}" title="${refresc.tipus}" ><img id="imageRefresc_${refresc.id}" width="200px"  src="/onlineBot/comanda/ImageAction.action?imageId=${refresc.id}" title="${refresc.descripcio} -> Double Click to Add" /></li>
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
	   <s:text name="comanda.num.begudes.promo" />:<label id="numbegudespromo" ></label>
	    <br>
	   <s:text name="comanda.preu" />:<label id="preu" ></label>
</div>
<br>
<br>
<div id="plats" class="">
<s:iterator value="platComandaList" var="platComanda">
	<div class="selector" id="plat_${platComanda.plat.id}" >
		<table>
			<tr>
				<td>${platComanda.plat.nom}</td>
				<td><label id="platpreu_${platComanda.plat.id}" >${platComanda.plat.preu}</label> </td>
				<td><input type="text" id="${platComanda.plat.id}" onblur="saveNewPLatAmount(this.id, this.value)"  value="${platComanda.numPlats}" ></td>
				<td>${platComanda.plat.tipus}</td>			
			</tr>
			<tr>
				<td colspan="3" >${platComanda.plat.descripcio}</td>
			</tr>
		</table>
	</div>
</s:iterator>
</div>
<div id="begudes" class="">
</div>

<br>
<c:import url="/pages/includes/address.jsp" />	
	<s:form action="checkComanda" method="POST" enctype="multipart/form-data" >
					<td><s:text name="comanda.dia" ></s:text></td>
					<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
					
														
					<s:checkbox key="comanda.aDomicili" id="adomicili"  ></s:checkbox>
					<s:hidden  key="comanda.hora" id="comandahora" ></s:hidden>					
					<s:hidden key="comanda.id" id="idcomanda" ></s:hidden>	                   
					<s:hidden key="comanda.address" id="comandaddress"></s:hidden>			
																							
					<tr><td><input type="button"  onclick="checkComandaJS();" value="Check Comanda" /></td><td>  <div id="chargeBar"></div></td></tr>
					
					
	</s:form>	
	<table>
					<tr><td><s:text name="comanda.hora" ></s:text></td></tr>										
					<tr>
						<c:if test="${horesDTO._0800 ne ''}">
							<td><input type="button"  id="0800" value="0800" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._0800 eq ''}">
							<td><input type="button"  id="0800" value="0800" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._0830 ne ''}">
							<td><input type="button"  id="0830" value="0830" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._0830 eq ''}">
							<td><input type="button"  id="0830" value="0830" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._0900 ne ''}">
							<td><input type="button"  id="0900" value="0900" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._0900 eq ''}">
							<td><input type="button"  id="0900" value="0900" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._0930 ne ''}">
							<td><input type="button"  id="0930" value="0930" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._0930 eq ''}">
							<td><input type="button"  id="0930" value="0930" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1000 ne ''}">
							<td><input type="button"  id="1000" value="1000" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1000 eq ''}">
							<td><input type="button"  id="1000" value="1000" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1030 ne ''}">
							<td><input type="button"  id="1030" value="1030" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1030 eq ''}">
							<td><input type="button"  id="1030" value="1030" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1100 ne ''}">
							<td><input type="button"  id="1100" value="1100" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1100 eq ''}">
							<td><input type="button"  id="1100" value="1100" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1130 ne ''}">
							<td><input type="button"  id="1130" value="1130" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1130 eq ''}">
							<td><input type="button"  id="1130" value="1130" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1200 ne ''}">
							<td><input type="button"  id="1200" value="1200" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1200 eq ''}">
							<td><input type="button"  id="1200" value="1200" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1230 ne ''}">
							<td><input type="button"  id="1230" value="1230" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1230 eq ''}">
							<td><input type="button"  id="1230" value="1230" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1300 ne ''}">
							<td><input type="button"  id="1300" value="1300" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1300 eq ''}">
							<td><input type="button"  id="1300" value="1300" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1330 ne ''}">
							<td><input type="button"  id="1330" value="1330" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1330 eq ''}">
							<td><input type="button"  id="1330" value="1330" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1400 ne ''}">
							<td><input type="button"  id="1400" value="1400" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1400 eq ''}">
							<td><input type="button"  id="1400" value="1400" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1430 ne ''}">
							<td><input type="button"  id="1430" value="1430" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1430 eq ''}">
							<td><input type="button"  id="1430" value="1430" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1500 ne ''}">
							<td><input type="button"  id="1500" value="1500" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1500 eq ''}">
							<td><input type="button"  id="1500" value="1500" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1530 ne ''}">
							<td><input type="button"  id="1530" value="1530" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1530 eq ''}">
							<td><input type="button"  id="1530" value="1530" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1600 ne ''}">
							<td><input type="button"  id="1600" value="1600" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1600 eq ''}">
							<td><input type="button"  id="1600" value="1600" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1630 ne ''}">
							<td><input type="button"  id="1630" value="1630" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1630 eq ''}">
							<td><input type="button"  id="1630" value="1630" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1700 ne ''}">
							<td><input type="button"  id="1700" value="1700" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1700 eq ''}">
							<td><input type="button"  id="1700" value="1700" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1730 ne ''}">
							<td><input type="button"  id="1730" value="1730" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1730 eq ''}">
							<td><input type="button"  id="1730" value="1730" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1800 ne ''}">
							<td><input type="button"  id="1800" value="1800" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1800 eq ''}">
							<td><input type="button"  id="1800" value="1800" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1830 ne ''}">
							<td><input type="button"  id="1830" value="1830" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1830 eq ''}">
							<td><input type="button"  id="1830" value="1830" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1900 ne ''}">
							<td><input type="button"  id="1900" value="1900" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1900 eq ''}">
							<td><input type="button"  id="1900" value="1900" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._1930 ne ''}">
							<td><input type="button"  id="1930" value="1930" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._1930 eq ''}">
							<td><input type="button"  id="1930" value="1930" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2000 ne ''}">
							<td><input type="button"  id="2000" value="2000" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2000 eq ''}">
							<td><input type="button"  id="2000" value="2000" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2030 ne ''}">
							<td><input type="button"  id="2030" value="2030" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2030 eq ''}">
							<td><input type="button"  id="2030" value="2030" class="nocheck" /></td>
						</c:if>
				
						<c:if test="${horesDTO._2100 ne ''}">
							<td><input type="button"  id="2100" value="2100" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2100 eq ''}">
							<td><input type="button"  id="2100" value="2100" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2130 ne ''}">
							<td><input type="button"  id="2130" value="2130" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2130 eq ''}">
							<td><input type="button"  id="2130" value="2130" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2200 ne ''}">
							<td><input type="button"  id="2200" value="2200" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2200 eq ''}">
							<td><input type="button"  id="2200" value="2200" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2230 ne ''}">
							<td><input type="button"  id="2230" value="2230" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2230 eq ''}">
							<td><input type="button"  id="2230" value="2230" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2300 ne ''}">
							<td><input type="button"  id="2300" value="2300" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2300 eq ''}">
							<td><input type="button"  id="2300" value="2300" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2330 ne ''}">
							<td><input type="button"  id="2330" value="2330" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2330 eq ''}">
							<td><input type="button"  id="2330" value="2330" class="nocheck" /></td>
						</c:if>
						
						<c:if test="${horesDTO._2400 ne ''}">
							<td><input type="button"  id="2400" value="2400" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${horesDTO._2400 eq ''}">
							<td><input type="button"  id="2400" value="2400" class="nocheck" /></td>
						</c:if>						
					</tr>	
	</table>
	<div id="checkPromocionsDisponibles" ><input type="button"  onclick="openDialogPromos();" value="promos Comanda" /></div>
	
	<div id="deletePromoApplied" ><input type="button"  onclick="deletePromoApplied();" value="delete promo applied" /></div>
	<br>

	<c:if test="${nameAuth eq 'anonymousUser' }">
		<h1><s:text name="txt.logate" /></h1>
		<form name='f' id="f" action="/onlineBot/j_spring_security_check" method="post">
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
	
<!-- Dialog per escollir promocio -->
<div id="dialog_promo" class="filtres filtres-oberts" title="Promo">
 
	 <h1>Escull una de les promocions</h1>
			<ul id="prm" >
			
				
			</ul>
</div>  	
	
<!-- Scripts --> 
	<style>
		.notcheck{
			background-color: grey;
		}
		.check{
			background-color: green;
		}
	</style>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/sudoSlider.css' />"  />	
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/progress.css' />" />
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
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<!-- Calendari -->  
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-cat.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-es.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-idioma.js'/>"></script>		
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-setup.js'/>"></script>
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	
	<!-- Sliders de begudes -->
	<script src="<c:url value='/js/sudoSlider/jquery.sudoSlider.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/progressbar/progress.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/pages/comanda/jsinfoComanda.js'/>"></script>	
<script>

var initParams = new InitParams("<s:text name='txt.beguda.no.tipus.promo' />","<s:text name='txt.beguda.no.more.promo' />","<s:text name='txt.add.beguda.to.box' />", "<s:text name='txt.promo.descompte.aplicat' />","<s:text name='txt.promo.deleted' />");


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

$("#idcomanda").val('${idComanda}');
$("#numComanda").text('${idComanda}');

$("#numplats").text('${fn:length(comanda.plats)}');
$("#preu").text('${comanda.preu}');
$("#numbegudes").text('${fn:length(comanda.begudes)}');


</script>
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>
