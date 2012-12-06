<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Gestió</title>

<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>

<!-- Calendari -->  
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
<script type="text/javascript" src="<c:url value='/js/calendari/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-cat.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-es.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-idioma.js'/>"></script>		
<script type="text/javascript" src="<c:url value='/js/calendari/calendar-setup.js'/>"></script>

<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/promocions/jspromocio.js' />"></script>

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	
<script language="javascript">
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
</head>
<body>

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
	
	  		 <div  style="width:800px;" alig="center">
				<table class="selecciom dataTable" id="tbl_promos" width="800px">
					<thead>
						<tr>
							<th><s:text name="mant.promo.table.nom" /></th>
							<th><s:text name="mant.promo.table.tipuDescompte" /></th>
							<th><s:text name="mant.promo.table.descompteImport" /></th>
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
							<s:textfield key="promocioAPartirDe.nom" id="nompromo_apd"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioAPartirDe.tipuDescompte" listKey="descripcio" listValue="descripcio">					
							</s:select>					
							<s:textfield key="promocioAPartirDe.descompteImport"  id="descompteImport_apd" onblur="onlyDouble(this.value,this.id)" ></s:textfield>																	
							<s:textfield key="promocioAPartirDe.importAPartirDe"  id="importAPartirDe"  onblur="onlyDouble(this.value,this.id)" ></s:textfield>
							<tr>
							<td><s:text name="promocioAPartirDe.diaString" ></s:text></td>
							<td><s:textfield key="promocioAPartirDe.diaString"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
							<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData" ></td>
							</tr>
							<s:hidden key="promocioAPartirDe.id" id="id_apd" ></s:hidden>			
								<s:submit></s:submit>
						</s:form>			
					</div>					
					<div id="pnc_div">
						<s:form action="savePromocioNumComandes" method="POST" enctype="multipart/form-data" >
							<s:textfield key="promocioNumComandes.nom" id="nompromo_pnc"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioNumComandes.tipuDescompte" listKey="descripcio" listValue="descripcio">					
							</s:select>										
							<s:textfield key="promocioNumComandes.descompteImport"  id="descompteImport_pnc"  onblur="onlyDouble(this.value,this.id)" ></s:textfield>	
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
<script>			

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
</script>  
</body>
</html>
