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
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="txt.info.comandes.title" /></title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/user.comandes.css' />" />
	<script src="<c:url value='/js/modernizer.js' />" type="text/javascript"></script>	
	<style>
			.hiddenIn{
				visibility: hidden;
				display: none;
			}
	</style> 
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');

</script>
</head>
<body id="personal">
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
<div id="content">
			<c:import url="/pages/includes/menuHeader.jsp" />
			<c:import url="/pages/includes/divLanguage.jsp" />
					<div id="content_per">
					<div id="content_left">				
					<div class="container">
			<section class="tabs">
	            <input id="tab-1" type="radio" name="radio-set" class="tab-selector-1 opts" checked="checked" />
		        <label for="tab-1" class="tab-label-1 labe"><s:text name="txt.comuser.dades" /></label>		
	            <input id="tab-2" type="radio" name="radio-set" class="tab-selector-2 opts" />
		        <label for="tab-2" class="tab-label-2 labe"><s:text name="txt.comuser.promos" /></label>		
	            <input id="tab-3" type="radio" name="radio-set" class="tab-selector-3 opts" />
		        <label for="tab-3" class="tab-label-3 labe"><s:text name="txt.comuser.user" /></label>		
			    <div class="clear-shadow"></div>				
		        <div class="contente">
			         <div class="contente-1">
			        <div class="useleft">
                        <c:import url="/pages/includes/addrescomanda.jsp" />
                    </div>
					 <div id="changepass">
                        <s:form  action="saveUserDetails" id="saveUserDetails" method="POST" enctype="multipart/form-data" >
                        		
								<tr class="espai"><td><label>${user.username}</label></td></tr>
								
								<tr class="espai"><td><label>${user.nom}<a href="#" onclick="changeClass('nom')" ><img src="<c:url value='/img/elements/edit.png' />" ></img></a></label></td></tr>
								<tr class="espai"><td><s:textfield key="user.nom" id="nom" onkeyup="return ismaxlength(this,45)" cssClass="hiddenIn inputs" theme="simple" ></s:textfield></td></tr>											                   
								
								<tr class="espai"><td><label>${user.telNumber}<a href="#" onclick="changeClass('telNumber')" ><img src="<c:url value='/img/elements/edit.png' />" ></img></a></label></td>
								<tr class="espai"><td><s:textfield key="user.telNumber" id="telNumber" onkeyup="return ismaxlength(this,9)" cssClass="hiddenIn inputs" theme="simple" ></s:textfield></td></tr>	
								
								<s:hidden key="user.id" id="idUser" ></s:hidden>
								<tr class="cha"><td><a href="#" onclick="changeClass('pass');" ><s:text name="userform.changePassword" /></a></td></tr>								
								
								<tr class="hiddenIn" id="pass" >
								<td><s:text name="user.password" />
								<s:password key="user.password" id="password" onkeyup="return ismaxlength(this,45)" cssClass="inputs" value="" theme="simple" ></s:password><br>
								<s:text name="txt.password.retype" />:<input type="password" id="passwordRetyped" onblur="checkPassword()" class="inputs" value=""/></td></tr>					
								
								<s:hidden key="user.indicacions" id="indicacions" ></s:hidden>
								<s:hidden key="user.address" id="comandaddress" ></s:hidden>
								<tr><td><input class="boton" type="button"  onclick="fillAddress()" value="submit"/></td></tr>	                    															
						</s:form>
						</div>	
                    <div id="usemaps">
                     	<img  src="<c:url value='/img/elements/maps10.jpg'/>"  >
                    </div>
                    
				    </div>
			        <div class="contente-2">
						<div id="promocionsdiv">					
						<h2><span class="promocions_peso"><s:text name="txt.info.promos" /></span></h2>
						<div class="prom_int" style="width:950px;" alig="center" >
						<ul>
						<s:iterator value="promoListAPartirDe" var="promoAPD" >		
							<c:if test="${not empty promoAPD.numBegudes}">
							<div class="promosImg">
							    <img width="160px" src="<c:url value='../img/elements/begudes.png'/>"  ></br>
								<span class="reftit">${promoAPD.numBegudes } ${promoAPD.tipusBeguda}</span> </br><s:text name="txt.promo.info.n3" /> ${promoAPD.importAPartirDe} &euro;.
							</div>
							</c:if>
							<c:if test="${not empty promoAPD.descompteImport}">
							<div class="promosImg">
							    <img width="160px" src="<c:url value='../img/elements/descompte.png'/>"  ></br>
								<span class="reftit"><s:text name="txt.promo.info.import.n1" /> ${promAPD.descompteImport } </span></br> <s:text name="txt.promo.info.n3" /> ${promoAPD.importAPartirDe} &euro;.
							</div>
							</c:if>					
						</s:iterator>
						<s:iterator value="promocioNumComandes" var="promoNC" >		
								
							<c:if test="${not empty promoNC.numBegudes}">
							<div class="promosImg">
							    <img width="160px" src="<c:url value='../img/elements/begudes.png'/>"  ></br>		
								<span class="reftit">${promoNC.numBegudes } ${promoNC.tipusBeguda}</span></br> <s:text name="txt.promo.info.n3" /> ${promoNC.numComandes} &euro;.
							</div>
							</c:if>
							<c:if test="${not empty promoNC.descompteImport}">
							<div class="promosImg">
							    <img width="160px" src="<c:url value='../img/elements/descompte.png'/>"  ></br>
								<span class="reftit"><s:text name="txt.promo.info.import.n1" /> ${promoNC.descompteImport } %</br></span> <s:text name="txt.promo.info.n3" /> ${promoNC.numComandes} &euro;.
							</div>
							</c:if>						
						</s:iterator>
						</ul>
						</div>
						
						
						
						
						<c:if test="${not empty promocioAssociada}">
							<h2><span class="promocions_peso"><s:text name="txt.info.promos.associada" /></span></h2>
								<div class="prom_int" style="width:950px;" alig="center" >
									<ul>												
										<c:if test="${not empty promocioAssociada.numBegudes}">
										<div class="promosImg">
							    			<img  src="<c:url value='../img/elements/begudes.png'/>"  ></br>
										<span class="reftit">${promocioAssociada.numBegudes } ${promocioAssociada.tipusBeguda} </br></span><s:text name="txt.promo.info.n3" /> ${promocioAssociada.importAPartirDe} &euro;.
										</div>
										</c:if>
										<c:if test="${not empty promocioAssociada.descompteImport}">
										<div class="promosImg">
							    			<img  src="<c:url value='../img/elements/descompte.png'/>"  ></br>
											<span class="reftit"><s:text name="txt.promo.info.import.n1" /> ${promocioAssociada.descompteImport } %</br></span><s:text name="txt.promo.info.n3" /> ${promocioAssociada.importAPartirDe}
										</div>
										</c:if>					
									</ul>
								</div>							
						</c:if>
						</div>                        
                        </div>
			        <div class="contente-3">
						 <div id="taula" width="957px" alig="center">
						<table class="selecciom dataTable" id="tbl_comandes_user" width="955px">
							<thead>
								<tr>
									<th><s:text name="user.comandes.table.dia" /></th>
									<th><s:text name="user.comandes.table.plats" /></th>
									<th><s:text name="user.comandes.table.links" /></th>
									<th><s:text name="user.comandes.table.preu" /></th>
									<th><s:text name="user.comandes.table.repeteix" /></th>								
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>                        
                        </div>
		        </div>
			</section>
        </div>
		 			</div>		 			
					</div>
<div id="votaPlats_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  		
	<script src="https://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/jsuser.comandes.min.js' />"></script>
	<script type="text/javascript">	
		new Address.addressValidation("<s:text name='txt.addressOK' />","<s:text name='txt.addressKO' />");
		var initParams = new  InitParams( "<s:text name='txt.user.empty' />", "<s:text name='txt.password.empty' />","<s:text name='txt.password.noteq' />",
										  "<s:text name='txt.tel.empty' />","<s:text name='txt.address.empty' />","<s:text name='error.double' />",
										  "<s:text name='error.number' />", "<s:text name='txt.welcome.confirmar' />","<s:text name='txt.welcome.productes' />",
										  "<s:text name='txt.welcome.producte' />","<s:text  name='txt.comanda.existeix.vol.continuar'/>","<s:text name='txt.avis.restaurant.tancat' />");
		var initTableParams = new InitTableParams("<s:text  name='datatables.paginate.last'/>","<s:text  name='datatables.paginate.next'/>","<s:text  name='datatables.paginate.previous'/>","<s:text  name='datatables.paginate.first'/>","<s:text  name='datatables.loading'/>","<s:text  name='txt.avis.borrat'/>");		
	</script>
	<c:if test="${not empty user}" >
	<script type="text/javascript">
		var address = "${user.address}";
		var addArray = address.split("-");		
		$("#carrer").val(addArray[0]);
		var codi = addArray[addArray.length-1];
		$("#codi").val(addArray[addArray.length-1]);
		if(codi=='17190'){
			$('#poble option[value="Salt"]').attr("selected", "selected");
		}else{
			$('#poble option[value="Girona"]').attr("selected", "selected");
		}
		if(addArray.length==5){
			$("#porta").val(addArray[addArray.length-2]);
			$("#num").val(addArray[addArray.length-3]);
			$("#numcarrer").val(addArray[addArray.length-4]);
		}
		$("#altres").val("${user.indicacions}");
		$("#indicacions").val("${user.indicacions}");
	</script>
	</c:if>
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
	</div>
</div>	
	<c:import url="/pages/includes/endPage.jsp" />
</body>
</html>