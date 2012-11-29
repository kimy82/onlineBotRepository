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
			<div id="infopromo">
				 <table class="noborder"  style="background-color:rgb(192,192,192)">
			         <tr>
				          <td colspan="3" >
				         	     <h1 class="capcalera" style="display:inline;padding:0px">
				         	      	<s:text name="mant.promo.info.title" />
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>		
				<s:form action="updatePromocio" method="POST" enctype="multipart/form-data" >
					<s:textfield key="promocio.nom" id="nompromo"  ></s:textfield>
					<s:textarea key="promocio.tipuDescompte" id="tipuDescompte" cols="40" rows="4" ></s:textarea>				
					<s:hidden key="promocio.descompteImport"  id="descompteImport" ></s:hidden>								
					<s:hidden key="promocio.id"  id="idpromo" ></s:hidden>
					<s:submit></s:submit>
				</s:form>
				<br>						
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
				<s:form action="savePromocio" method="POST" enctype="multipart/form-data" >
					<s:textfield key="promocio.nom" id="nompromo"  ></s:textfield>
					<s:textarea key="promocio.tipuDescompte" id="tipuDescompte" cols="40" rows="4" ></s:textarea>				
					<s:hidden key="promocio.descompteImport"  id="descompteImport" ></s:hidden>								
					<s:hidden key="promocio.id"  id="idpromo" ></s:hidden>
					<s:submit></s:submit>
				</s:form>
				<br>						
			</div>
		</div>
	</div>
</div>
</body>
</html>
