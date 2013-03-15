<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>	
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="txt.welcome.principal" /></title>	
</head>
<body id="carrito">
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">

	<div id="content">
			<!-- menu -->
				<c:import url="/pages/includes/menuHeaderNoConfirm.jsp" />
			<!-- END menu -->
			<!-- Language -->
				<c:import url="/pages/includes/divLanguage.jsp" />
			<!-- END language -->
			<div class="titols_comanda"> <s:text name="txt.add.some.drink" /></div>
			<div id="recordatori">
				<div id="slider" style=" height:260px; width:1000px; top:140px;"  >
				    <ul style="height:260px;">
				    	<s:iterator value="refrescList" var="refresc">
					    	<li style="height:260px;">
					    			<div id="iterate_Rec" >
					    				<div id="img_Rest" >
					    					<img id="imageRefresc_${refresc.id}" width="200px"  src="/${initParam.app}/comanda/ImageAction.action?imageId=${refresc.id}" title="" />
					    				</div>
					    				<div class="titol_Rest">
					    					
											<h1><c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${refresc.descripcio}
		  										</c:if>
		  										<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${refresc.descripcioES}
		  										</c:if>	
		  									</h1>
										</div>
										<div class="left_price">
											<a class="entrar draggable" id="${refresc.idSub}" title="${refresc.tipus}" href="#"><s:text name="txt.plat.afegir" /></a>
										</div>
										<div class="right_price">
											<span class="price">${refresc.preu} &euro; </span>
											<br>
										</div>									
					    			</div>					    			
					    	</li>				    		
				        </s:iterator>	
				    </ul>
				</div>
			</div>
			<div id="preus" >
					<table id="order">
						<thead>
							<tr>
							<th class="titolars pro" scope="col"><s:text name="txt.table.infocomanda.th1" /></th>
							<th class="titolars des" scope="col"><s:text name="txt.table.infocomanda.th2" /></th>
							<th class="titolars pre" scope="col"><s:text name="txt.table.infocomanda.th3" /></th>
							<th class="titolars can" scope="col"><s:text name="txt.table.infocomanda.th4" /></th>
							<th class="titolars tot" scope="col"><s:text name="txt.table.infocomanda.th5" /></th>
							<th class="titolars elm" scope="col"></th>
							</tr>
						</thead>
						<tbody class="tb">
							<s:iterator value="platComandaList" var="platComanda">
								<tr class="selector_pl" id="plat_${platComanda.plat.id}" >
									<td class="img_order">
										<img width="103px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${platComanda.plat.foto.id}">
									</td>
									<td class="descri">
										<span class="tit">
											<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${platComanda.plat.nom}
		  									</c:if>
		  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${platComanda.plat.nomES}
		  									</c:if>												
										</span>
									<br>
											<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${platComanda.plat.descripcio}
		  									</c:if>
		  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${platComanda.plat.descripcioES}
		  									</c:if>	
										
									</td>
									<td class="preusun"><label id="platpreu_${platComanda.plat.id}" >${platComanda.plat.preu}</label>&euro; </td>
									<td class="canti">										
										<label id="labelnum_${platComanda.plat.id}">${platComanda.numPlats}</label>
										<input class="mores" type="submit" onclick="saveNewPLatAmount(${platComanda.plat.id}, -1)" value="-">
										<input class="mores" type="submit" onclick="saveNewPLatAmount(${platComanda.plat.id}, 1)" value="+">
									</td>
									<td class="total"><label id="labelpreutotal_${platComanda.plat.id}">${platComanda.plat.preu*platComanda.numPlats}</label> &euro; </td>
									<td class="elimi">
										<input class="elimin" type="submit" onclick="eliminaPlat(${platComanda.plat.id})"  value="ELIMINAR">
									</td>								
								</tr>
							</s:iterator>
							<s:iterator value="begudaComandaList" var="begudaComanda">
								<c:if test="${begudaComanda.numBegudes>0}" >
									<tr class="selector_bg" id="beguda_${begudaComanda.beguda.id}" >
									<td class="img_order">
										<img width="103px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${begudaComanda.beguda.foto.id}">
									</td>
									
									<td class="descri">
										<span class="tit">
											<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${begudaComanda.beguda.nom}
		  									</c:if>
		  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${begudaComanda.beguda.nomES}
		  									</c:if>											
										</span>
									<br>
										<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${begudaComanda.beguda.descripcio}
		  								</c:if>
		  								<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${begudaComanda.beguda.descripcioES}
		  								</c:if>	
										
									</td>
									<td class="preusun"><label id="begudapreu_${begudaComanda.beguda.id}" >${begudaComanda.beguda.preu}</label>&euro; </td>
									<td class="canti">										
										<input class="mores" type="submit" onclick="saveBegudaToComanda(${begudaComanda.beguda.id},false,-1);" value="-">
										<label id="labelnum_b_${begudaComanda.beguda.id}">${begudaComanda.numBegudes}</label>
										<input class="mores" type="submit" onclick="saveBegudaToComanda(${begudaComanda.beguda.id},false,1)" value="+">
									</td>
									<td class="total"><label id="labelpreutotal_b_${begudaComanda.beguda.id}"><fmt:formatNumber maxFractionDigits="2" type="number" value="${begudaComanda.beguda.preu*begudaComanda.numBegudes}" ></fmt:formatNumber> </label> &euro; </td>
									<td class="elimi">
										<input class="elimin" type="submit" onclick="eliminaBeguda(${begudaComanda.beguda.id})"  value="ELIMINAR">
									</td>								
									</tr>								
								</c:if>
								<c:if test="${begudaComanda.numBegudesPromo>0}" >
									<tr class="selector_bg" id="beguda_p_${begudaComanda.beguda.id}" >
									<td class="img_order">
										<img width="103px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${begudaComanda.beguda.foto.id}">
									</td>
									<td class="descri">
										<span class="tit">
											<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${begudaComanda.beguda.nom}
		  									</c:if>
		  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${begudaComanda.beguda.nomES}
		  									</c:if>	
										</span>
									<br>
										<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  											${begudaComanda.beguda.descripcio}
		  								</c:if>
		  								<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  											${begudaComanda.beguda.descripcioES}
		  								</c:if>	
									</td>
									<td class="preusun"><label id="begudapreu_p_${begudaComanda.beguda.id}" >${begudaComanda.beguda.preu}</label>&euro; </td>
									<td class="canti">										
										<label id='labelnum_b_p_${begudaComanda.beguda.id}'>${begudaComanda.numBegudesPromo}</label>
									</td>
									<td class="total"><label id="labelpreutotal_b_p_${begudaComanda.beguda.id}">0</label> &euro; </td>
									<td class="elimi">
										<input class='elimin' type='submit' onclick='deletePromoApplied();'  value="<s:text name='txt.infocomanda.treure.promo' />">
									</td>								
									</tr>
								</c:if>
							</s:iterator>
							<tr>
								<td class="trans" colspan="4"><s:text name="txt.table.infocomanda.transport" /></td>
								<td class="total"><label id="transport_lb"></label>&euro; </td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="trans" colspan="4"><s:text name="txt.table.infocomanda.subtotal" /></td>
								<td class="total"><label id="preu"></label>&euro;  </td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="trans" colspan="4"><s:text name="txt.table.infocomanda.promos" /></td>
								<td class="total"><label id="promoImp"></label></td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="trans" colspan="4"><s:text name="txt.table.infocomanda.total" /></td>
								<td class="total" ><label id="labelpreutotalPromo"></label> &euro;</td>
								<td class="total"></td>
							</tr>						
						</tbody>
					</table>
			</div>
	<!-- LOGIN -->
	<c:if test="${nameAuth eq 'anonymousUser' }">
		<div id="login" style="float: left; margin-top: 20px;" >
		<h1><s:text name="txt.logate" /></h1>
			<form name='f' id="f" action="/${initParam.app}/j_spring_security_check" method="post">
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
		</div>
	</c:if>	
	<br><br><br><br><br><br><br>
	<div id="checkPromocionsDisponibles" style="float: left; margin-top: 20px;" ><input type="button"  onclick="openDialogPromos();" value="<s:text name='txt.infocomanda.checkPromos.boton' />" /></div>
	<div id="deletePromoApplied" style="float: left; margin-top: 20px;" ><input type="button"  onclick="deletePromoApplied();" value="<s:text name='txt.infocomanda.deletePromos.boton' />" /></div>
	<br><br><br><br><br><br><br>
	<div style="float: left; margin-top: 100px;" >
	
		<s:checkbox key="comanda.aDomicili" onclick="addDomicili()"  id="adomicili"   ></s:checkbox>
		<s:checkbox key="comanda.aRecollir" onclick="addRecollir()"  id="arecollir"   ></s:checkbox>
		
	</div>
	<br><br><br><br><br><br>		
	<div style="float: left; margin-top: 20px;" >
		<table>
			<tr>
				<td><s:text name="comanda.dia" ></s:text></td>
				<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" onchange="reloadHores()" ></s:textfield>
				<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
			</tr>
		</table>
							
		<c:import url="/pages/includes/horesInfoComanda.jsp" />
	</div>
	
	<div id="arecollir_div" style="float: left;margin-top: 150px; border: 1px; " >
		<s:text name="txt.infocomanda.passala.arecorrir.per" />
		<h1 id="address_restaurant" ></h1>
		<input type="button"  onclick="checkComandaJS();" value="<s:text name='txt.infocomanda.paga' />" />
	</div>
	
	<div id="adomicili_div" style="float: left;margin-top: 30px; border: 1px; " >
		<s:checkbox key="comanda.targeta" id="targeta" onclick="targeta()" ></s:checkbox>
		<s:checkbox key="comanda.contrarembols" id="contrarembols" onclick="contrarembols()"></s:checkbox>
		<c:import url="/pages/includes/address.jsp" />
		<input type="button"  onclick="checkComandaJS();" value="<s:text name='txt.infocomanda.paga' />" />
	</div>
		
<div id="chargeBar"></div>
			
<div id="check"></div>
				

<s:hidden  key="comanda.hora" id="targetaContrarembols" ></s:hidden>					
<s:hidden  key="comanda.hora" id="comandahora" ></s:hidden>					
<s:hidden key="comanda.id" id="idcomanda" ></s:hidden>	                   
<s:hidden key="comanda.address" id="comandaddress"></s:hidden>																				

	
</div>
</div>
<!-- END container -->
<c:import url="/pages/includes/endPage.jsp" />

<!-- div per al llistat de promocions -->
<div id="dialog_promo" class="filtres filtres-oberts" title="Promo">
	 
		 <h1><s:text name="txt.promo.escull" /></h1>
				<ul id="prm" >
				
					
				</ul>
		<h1><s:text name="txt.promo.escull.promo.especial" /><input id="codePromo" type="text" value="" onblur="checkPromoEspecial()" /></h1>
				<ul id="esp" >
				
					
				</ul>
		
</div>  
	
	<!-- CSS portamu --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<!-- CSS portamu -->
	<style>
		.notcheck{
			background-color: grey;
		}
		.check{
			background-color: yellow;
		}
		
		.checked{
			background-color: green;
		}
	</style>
	
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/sudoSlider.css' />"  />	
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/progress.css' />" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/portamu/prova.css' />" />

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
<script type="text/javascript" >
			  
var initParams = new InitParams("<s:text name='txt.beguda.no.tipus.promo' />","<s:text name='txt.beguda.no.more.promo' />","<s:text name='txt.add.beguda.to.box' />", 
								"<s:text name='txt.promo.descompte.aplicat' />","<s:text name='txt.promo.deleted' />","<s:text name='comanda.falta.hora' />",
								"<s:text name='comanda.check.address' />","<s:text name='comanda.user.check.ok' />","<s:text name='comanda.user.check.ko' />",
								"<s:text name='txt.welcome.confirmar' />","<s:text name='txt.welcome.productes' />","<s:text name='txt.welcome.producte' />",
								"<s:text name='txt.infocomanda.arecollir.more.than.one' />","<s:text name='txt.infocomanda.boto.treure.promo' />");
								

$("#idcomanda").val('${requestScope.idComanda}');
$("#numComanda").text('${requestScope.idComanda}');
$("#dia").val('${requestScope.horesDTO.data}');


initNumPlats();
initNumBegudes();

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
	        	$("#loged").text(initParams.checkok);	
	        	var promo = window.localStorage.getItem("comanda.promo.id");
	        	if(promo!='undefined' && promo!=null){
	        		$("#checkPromocionsDisponibles").hide();
	        		$("#deletePromoApplied").show();	
	        		
	        	}else{

	        		$("#checkPromocionsDisponibles").show();
	        		$("#deletePromoApplied").hide();
	        	}
	        	
	        } else if (json.result == "error") {	        	
	        	$("#loged").text(initParams.checkko);
	        }
	    }
	});	
}


var addressToLoad ='${requestScope.comanda.address}';
if(addressToLoad==''){
	addressToLoad =  '${requestScope.user.address}';	
}

initAddress();

</script>
<c:if test="${nameAuth ne 'anonymousUser' }">
	<script type="text/javascript" >
		$("#checkPromocionsDisponibles").show();
	</script>
</c:if>
<c:if test="${not empty requestScope.recoveredComanda}">
<script type="text/javascript" >
window.localStorage.clear();
window.localStorage.setItem("comanda.data","${requestScope.horesDTO.data}");
window.localStorage.setItem("comanda","${requestScope.idComanda}");

window.localStorage.setItem("comanda.preu","${requestScope.comanda.preu}");
window.localStorage.setItem("comanda.numplats","${requestScope.numPlats}");
$("#numplats").text('${requestScope.numPlats}');
</script>
</c:if>
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
	<c:import url="/pages/includes/confirmOnline.jsp" />
</body>
</html>