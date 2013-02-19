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
<h2><s:text name="txt.info.user" /></h2>
<br>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/goHome.jsp" />	
<br><a href="#" onclick="openDialog('infoUser');" ><s:text name="txt.canvi.dades.personals" /></a>	
<h2><s:text name="txt.info.comandes.user.title" /></h2>

 			<div  width="1000px" alig="center">
				<table class="selecciom dataTable" id="tbl_comandes_user" width="1000px">
					<thead>
						<tr>
							<th><s:text name="user.comandes.table.dia" /></th>
							<th><s:text name="user.comandes.table.plats" /></th>
							<th><s:text name="user.comandes.table.links" /></th>
							<th><s:text name="user.comandes.table.preu" /></th>
							<th><s:text name="user.comandes.table.descripcio" /></th>
							<th width="40px"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			
	<h2><s:text name="txt.info.promos" /></h2>
			<div style="width:500px;" alig="center" >
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
<!-- Scripts --> 
<!-- Dialog per escollir promocio -->
<div id="dialog_details" class="filtres filtres-oberts" title="<s:text name="txt.canvi.details.title" />">
 
	 <h1><s:text name="txt.canvi.details" /></h1>
	 
	 			<c:import url="/pages/includes/address.jsp" />	
	 					

				<s:form  action="saveUserDetails" id="saveUserDetails" method="POST" enctype="multipart/form-data" >
						<s:textfield key="user.username" id="username" onkeyup="return ismaxlength(this,45)"  ></s:textfield>											                   
						<s:textfield key="user.telNumber" id="telNumber" onkeyup="return ismaxlength(this,9)" ></s:textfield>						                    
						<s:hidden key="user.id" id="idUser" ></s:hidden>							
						<tr><td><s:text name="user.password" /></td><td><s:password key="user.password" id="password" onkeyup="return ismaxlength(this,45)" value="" theme="simple" ></s:password></td>
						<td><s:text name="txt.password.retype" />:</td><td><input type="password" id="passwordRetyped" onblur="checkPassword()"/></td></tr>						
						<s:hidden key="user.address" id="comandaddress" ></s:hidden>
						<tr><td><input type="button"  onclick="fillAddress()" value="submit"/></td></tr>	                    															
				</s:form>	
</div> 
<div id="votaPlats_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
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
		$("#codi").val(addArray[1]);
	</script>
	</c:if>
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
	</div>
	<!-- END Content-->
</div>	
<!-- END container -->
<div id="footer">
	<div id="footer_int">
		<div id="footer_rigth">
		
		</div>
	</div>
</div>
<div id="credits">
	<div id="int_credits">
		<s:text name="txt.footer.reserved" />
	</div>
</div>
	</div>
</body>
</html>