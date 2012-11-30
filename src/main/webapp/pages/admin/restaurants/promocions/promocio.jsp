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
			"<s:text  name='promo.confirm.borra'/>");
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
	
	  		 <div  style="width:500px;" alig="center">
				<table class="selecciom dataTable" id="tbl_promos" width="500px">
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
				
					<h1>Dades generals</h1>
					
					<label for="apd" >Promoció a partir de:</label>
					<input type="radio" value="apd" onclick="openDivTipuPromo(this.id)" name="promo" id="apd" ></input>
					<label for="pnc" >Promoció per numero de comandes:</label>
					<input type="radio" value="pnc" onclick="openDivTipuPromo(this.id)" name="promo" id="pnc" ></input>
					
					<div id="apd_div">
						<s:form action="savePromocioAPartirDe" method="POST" enctype="multipart/form-data" >
							<s:textfield key="promocioAPartirDe.nom" id="nompromo_apd"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioAPartirDe.tipuDescompte" listKey="descripcio" listValue="descripcio">					
							</s:select>					
							<s:textfield key="promocioAPartirDe.descompteImport"  id="descompteImport_apd" ></s:textfield>																	
							<s:textfield key="promocioAPartirDe.importAPartirDe"  id="importAPartirDe" ></s:textfield>
							<s:textfield key="promocioAPartirDe.dia"  id="dia" ></s:textfield>	
							<s:hidden key="promocioAPartirDe.id" id="id_apd" ></s:hidden>			
								<s:submit></s:submit>
						</s:form>			
					</div>					
					<div id="pnc_div">
						<s:form action="savePromocioNumComandes" method="POST" enctype="multipart/form-data" >
							<s:textfield key="promocioNumComandes.nom" id="nompromo_pnc"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioNumComandes.tipuDescompte" listKey="descripcio" listValue="descripcio">					
							</s:select>										
							<s:textfield key="promocioNumComandes.descompteImport"  id="descompteImport_pnc" ></s:textfield>	
							<s:textfield key="promocioNumComandes.numComandes"  id="numComandes" ></s:textfield>
							<s:textfield key="promocioNumComandes.temps"  id="temps" ></s:textfield>
							<s:hidden key="promocioNumComandes.id" id="id_pnc" ></s:hidden>
								<s:submit></s:submit>
						</s:form>		
					</div>
					
					
				<br>						
			</div>
		</div>
	</div>
</div>
</body>
</html>
