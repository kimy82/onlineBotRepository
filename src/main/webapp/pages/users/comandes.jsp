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
	<title><s:text name="txt.info.comandes.title" /></title>
</head>
<body id="personal">
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
<div id="content">
		<!-- menu -->
			<c:import url="/pages/includes/menuHeader.jsp" />
		<!-- END menu -->
		<!-- Language -->
			<c:import url="/pages/includes/divLanguage.jsp" />
		<!-- END language -->
<c:import url="/pages/includes/headerContext.jsp" />
					<div id="content_per">
					<div id="content_left">
					<div id="canvia">
					<h2><s:text name="txt.info.user" /></h2>
					<h1><s:text name="txt.canvi.details" /></h1>
			 
			 			<c:import url="/pages/includes/addresses.jsp" />	
			 		
						<s:form  action="saveUserDetails" id="saveUserDetails" method="POST" enctype="multipart/form-data" >
								<s:textfield key="user.username" id="username" onkeyup="return ismaxlength(this,45)"  ></s:textfield>											                   
								<s:textfield key="user.telNumber" id="telNumber" onkeyup="return ismaxlength(this,9)" ></s:textfield>						                    
								<s:hidden key="user.id" id="idUser" ></s:hidden>							
								<s:text name="user.password" /><s:password key="user.password" id="password" onkeyup="return ismaxlength(this,45)" value="" theme="simple" ></s:password>
								<s:text name="txt.password.retype" />:<input type="password" id="passwordRetyped" onblur="checkPassword()"/>					
								<s:hidden key="user.address" id="comandaddress" ></s:hidden>
								<input type="button"  onclick="fillAddress()" value="submit"/>	                    															
						</s:form>	
					</div>
					
					<div id="promocionsdiv">
					
						<h2><span class="promocions_peso"><s:text name="txt.info.promos" /></span></h2>
						<div class="prom_int" style="width:350px;" alig="center" >
						<ul>
						<s:iterator value="promoListAPartirDe" var="promoAPD" >
						
						
							<c:if test="${not empty promoAPD.numBegudes}">
								<li><s:text name="txt.promo.info.begudes.n1" /> ${promoAPD.numBegudes } <s:text name="txt.promo.info.n2" /> ${promoAPD.tipusBeguda} <s:text name="txt.promo.info.n3" /> ${promoAPD.importAPartirDe}</li>
							</c:if>
							<c:if test="${not empty promoAPD.descompteImport}">
								<li><s:text name="txt.promo.info.import.n1" />  ${promAPD.descompteImport }<s:text name="txt.promo.info.en" /> ${promoAPD.tipuDescompte} <s:text name="txt.promo.info.n3" /> ${promoAPD.importAPartirDe}</li>
							</c:if>
						
						</s:iterator>
						<s:iterator value="promocioNumComandes" var="promoNC" >
						
						
							<c:if test="${not empty promoNC.numBegudes}">
								<li><s:text name="txt.promo.info.begudes.n1" /> ${promoNC.numBegudes } <s:text name="txt.promo.info.n2" /> ${promoNC.tipusBeguda} <s:text name="txt.promo.info.n3" /> ${promoNC.numComandes} <s:text name="txt.promo.info.en" /> ${promoNC.temps } <s:text name="txt.promo.info.dies" /></li>
							</c:if>
							<c:if test="${not empty promoNC.descompteImport}">
								<li><s:text name="txt.promo.info.import.n1" />  ${promoNC.descompteImport } <s:text name="txt.promo.info.en" /> ${promoNC.tipuDescompte} <s:text name="txt.promo.info.n3" /> ${promoNC.numComandes} <s:text name="txt.promo.info.en" /> ${promoNC.temps } <s:text name="txt.promo.info.dies" /></li>
							</c:if>
						
						</s:iterator>
						</ul>
						</div>
					</div>	
		 			</div>
		 			<div id="content_right">
		 			<div id="taula" width="650px" alig="center">
						<table class="selecciom dataTable" id="tbl_comandes_user" width="650px">
							<thead>
								<tr>
									<th><s:text name="user.comandes.table.dia" /></th>
									<th><s:text name="user.comandes.table.plats" /></th>
									<th><s:text name="user.comandes.table.links" /></th>
									<th><s:text name="user.comandes.table.preu" /></th>
									<th><s:text name="user.comandes.table.descripcio" /></th>
									
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					</div>
					</div>
<div id="votaPlats_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  
	
	
<!-- scripts -->
<link rel="stylesheet" href="<c:url value='/css/portamu/tables.css' />" type="text/css"   media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />
	
	<!-- CSS portamu --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/prova.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<!-- CSS portamu -->

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
	<script src="<c:url value='/js/jquery/jquery.dataTables.js' />" type="text/javascript"></script>
	
		<!-- Per validar l'adreca -->
		<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	
	<script type="text/javascript" src="<c:url value='/pages/users/jscomandes.js' />"></script>
	<script language="javascript">

	
		new Address.addressValidation();
		var initParams = new  InitParams( "<s:text name='txt.user.empty' />", "<s:text name='txt.password.empty' />","<s:text name='txt.password.noteq' />","<s:text name='txt.tel.empty' />","<s:text name='txt.address.empty' />","<s:text name='error.double' />","<s:text name='error.number' />");
		var initTableParams = new InitTableParams(
					"<s:text  name='datatables.paginate.last'/>",
					"<s:text  name='datatables.paginate.next'/>",
					"<s:text  name='datatables.paginate.previous'/>",
					"<s:text  name='datatables.paginate.first'/>",
					"<s:text  name='datatables.loading'/>",
					"<s:text  name='txt.avis.borrat'/>");
		
	</script>
	<c:if test="${not empty user}" >
	<script language="javascript">
		var address = '${user.address}';
		var addArray = address.split("-");
		
		$("#carrer").val(addArray[0]);
		$("#codi").val(addArray[addArray.length-1]);
	</script>
	</c:if>
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
	</div>
	<!-- END Content-->
</div>	
<!-- END container -->
	<c:import url="/pages/includes/endPage.jsp" />
</body>
</html>