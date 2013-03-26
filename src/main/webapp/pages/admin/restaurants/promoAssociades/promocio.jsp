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
	<link rel="stylesheet" href="<c:url value='/css/min/admin.promosassociades.min.css' />" type="text/css"   media="screen" />
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
					
					
						<s:form action="savePromocioAssociada" method="POST" enctype="multipart/form-data" >
							
							
							<s:checkbox key="promocioAssociada.hora" id="hora" ></s:checkbox>
					
							<s:textfield key="promocioAssociada.nom" id="nompromo"  ></s:textfield>
							<s:textfield key="promocioAssociada.nomES" id="nompromoES"  ></s:textfield>
							<s:select list="tipusDescompteList" key="promocioAssociada.tipuDescompte" listKey="descripcio" listValue="descripcio">					
							</s:select>					
							<s:textfield key="promocioAssociada.descompteImport"  id="descompteImport" onblur="onlyDouble(this.value,this.id)" ></s:textfield>		
							<s:textfield key="promocioAssociada.numBegudes"  id="numBegudes" onblur="onlyEntero(this.value,this.id)" ></s:textfield>																
							<s:select list="tipusBegudaList" key="promocioAssociada.tipusBeguda" id="tipusBeguda" listKey="descripcio" listValue="descripcio">					
							</s:select>	
							<s:textfield key="promocioAssociada.importAPartirDe"  id="importAPartirDe"  onblur="onlyDouble(this.value,this.id)" ></s:textfield>
							
							<s:hidden key="promocioAssociada.id" id="id_promo" ></s:hidden>																					
			
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
	<!-- link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /-->
	
	<!--  script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>

	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/promoAssociades/jspromocio.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/validations/jsvalidations.js' />"></script-->
	
	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/promoAssociades/jspromocio.min.js' />"></script>

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
</body>
</html>
