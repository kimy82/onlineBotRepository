<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="mant.promo.title.gestio" /></title>	
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/admin.promos.min.css' />" type="text/css"   media="screen" />
</head>
<body id="promo">
<c:import url="/pages/includes/headerContext.jsp" />
<div class="content">
	<div class="container">
		<div class="topadmin"> 
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		</div>
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="mant.promos.title" />
				<a href="#" onclick="opendivNewPromo();" ><s:text name="promo.new" /></a>
			</h2> 
	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
	
	  		 <div  style="width:932px;" alig="center">
				<table class="selecciom dataTable" id="tbl_promos" width="932px">
					<thead>
						<tr>
							<th><s:text name="mant.promo.table.nom" /></th>
							<th><s:text name="mant.promo.table.fentrada" /></th>
							<th><s:text name="mant.promo.table.tipuDescompte" /></th>
							<th><s:text name="mant.promo.table.descompteImport" /></th>
							<th><s:text name="mant.promo.table.numBegudes" /></th>
							<th><s:text name="mant.promo.table.tipusBeguda" /></th>
							<th><s:text name="mant.promo.table.code" /></th>	
							<th width="40px"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>					
				<div id="infopromonew">
				 <table class="noborder"  style="background-color:rgb(192,192,192)">
			         <tr>
				          <td colspan="3" >
				         	     <h1 class="capcalera" style="display:inline;padding:0px">
				         	      	<s:text name="mant.promo.info.title" />
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>		
				
					<h1><s:text name="txt.promo.dades.generals" /></h1>
					
					<label for="apd" ><s:text name="txt.promo.tipus.promo.1" /></label>
					<input type="radio" value="apd" onclick="openDivTipuPromo(this.id)" name="promo" id="apd" ></input>
					<label for="pnc" ><s:text name="txt.promo.tipus.promo.2" /></label>
					<input type="radio" value="pnc" onclick="openDivTipuPromo(this.id)" name="promo" id="pnc" ></input>
					
					<div id="apd_div">
						<s:form action="savePromocioAPartirDe" method="POST" enctype="multipart/form-data" >
							<tr>
								<td><s:text name="promocioAPartirDeDTF.dilluns" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.dilluns" id="adp_dilluns" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.dimarts" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.dimarts" id="adp_dimarts" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.dimecres" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.dimecres" id="adp_dimecres" theme="simple"  ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.dijous" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.dijous" id="adp_dijous" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.divendres" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.divendres" id="adp_divendres" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.dissabte" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.dissabte" id="adp_dissabte" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioAPartirDeDTF.diumenge" ></s:text>:<s:checkbox key="promocioAPartirDeDTF.diumenge" id="adp_diumenge" theme="simple" ></s:checkbox></td>
							</tr>
							
							<s:checkbox key="promocioAPartirDeDTF.visibility" id="adp_visibility" ></s:checkbox>
							<s:checkbox key="promocioAPartirDeDTF.hora" id="adp_hora" ></s:checkbox>
							<s:textfield key="promocioAPartirDeDTF.numUses"  id="numUses_apd" onblur="onlyEntero(this.value,this.id)" ></s:textfield>	
							<s:textfield key="promocioAPartirDeDTF.nom" id="nompromo_apd"  ></s:textfield>
							<s:textfield key="promocioAPartirDeDTF.nomES" id="nompromoES_apd"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioAPartirDeDTF.tipuDescompte" listKey="id" listValue="descripcio">					
							</s:select>					
							<s:textfield key="promocioAPartirDeDTF.descompteImport"  id="descompteImport_apd" onblur="onlyDouble(this.value,this.id)" ></s:textfield>		
							<s:textfield key="promocioAPartirDeDTF.numBegudes"  id="numBegudes_apd" onblur="onlyEntero(this.value,this.id)" ></s:textfield>																
							<s:select list="tipusBegudaList" key="promocioAPartirDeDTF.tipusBeguda" id="tipusBeguda_apd" listKey="descripcio" listValue="descripcio">					
							</s:select>	
							<s:textfield key="promocioAPartirDeDTF.importAPartirDe"  id="importAPartirDe"  onblur="onlyDouble(this.value,this.id)" ></s:textfield>
							<tr>
							<td><s:text name="promocioAPartirDeDTF.diaString" ></s:text></td>
							<td><s:textfield key="promocioAPartirDeDTF.diaString"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
							<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData" ></td>
							</tr>
							<s:hidden key="promocioAPartirDeDTF.id" id="id_apd" ></s:hidden>			
								<s:submit></s:submit>
						</s:form>			
					</div>					
					<div id="pnc_div">
						<s:form action="savePromocioNumComandes" method="POST" enctype="multipart/form-data" >
						
							<tr>
								<td><s:text name="promocioNumComandes.dilluns" ></s:text>:<s:checkbox key="promocioNumComandes.dilluns" id="pnc_dilluns" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.dimarts" ></s:text>:<s:checkbox key="promocioNumComandes.dimarts" id="pnc_dimarts" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.dimecres" ></s:text>:<s:checkbox key="promocioNumComandes.dimecres" id="pnc_dimecres" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.dijous" ></s:text>:<s:checkbox key="promocioNumComandes.dijous" id="pnc_dijous" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.divendres" ></s:text>:<s:checkbox key="promocioNumComandes.divendres" id="pnc_divendres" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.dissabte" ></s:text>:<s:checkbox key="promocioNumComandes.dissabte" id="pnc_dissabte" theme="simple" ></s:checkbox></td>
								<td><s:text name="promocioNumComandes.diumenge" ></s:text>:<s:checkbox key="promocioNumComandes.diumenge" id="pnc_diumenge" theme="simple" ></s:checkbox></td>
							</tr>
						
							<s:checkbox key="promocioNumComandes.visibility" id="pnc_visibility" ></s:checkbox>
							<s:checkbox key="promocioNumComandes.hora" id="pnc_hora" ></s:checkbox>
							<s:textfield key="promocioNumComandes.numUses"  id="numUses_pnc" onblur="onlyEntero(this.value,this.id)" ></s:textfield>		
							<s:textfield key="promocioNumComandes.nom" id="nompromo_pnc"  ></s:textfield>
							<s:textfield key="promocioNumComandes.nomES" id="nompromoES_pnc"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioNumComandes.tipuDescompte" listKey="id" listValue="descripcio">					
							</s:select>										
							<s:textfield key="promocioNumComandes.descompteImport"  id="descompteImport_pnc"  onblur="onlyDouble(this.value,this.id)" ></s:textfield>
							<s:textfield key="promocioNumComandes.numBegudes"  id="numBegudes_pnc" onblur="onlyEntero(this.value,this.id)" ></s:textfield>																
							<s:select list="tipusBegudaList" key="promocioNumComandes.tipusBeguda" id="tipusBeguda_pnc" listKey="descripcio" listValue="descripcio">					
							</s:select>	
								
							<s:textfield key="promocioNumComandes.numComandes"  id="numComandes"  onblur="onlyEntero(this.value,this.id)" ></s:textfield>
							<s:textfield key="promocioNumComandes.temps"  id="temps"  onblur="onlyEntero(this.value,this.id)" ></s:textfield>
							<s:hidden key="promocioNumComandes.id" id="id_pnc" ></s:hidden>
								<s:submit></s:submit>
						</s:form>		
					</div>
					
					
				<br>						
			</div>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript" src="<c:url value='/js/jspromocio.admin.min.js' />"></script>
<script language="javascript">			
//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='txt.avis.borrat'/>",
			"<s:text  name='promo.confirm.borra'/>",
			"<s:text  name='.error.double'/>",
			"<s:text  name='txt.error.number'/>");
</script> 
</body>
</html>
