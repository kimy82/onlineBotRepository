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

<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/begudes/jsbegudes.js' />"></script>

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
				<s:text name="mant.begudes.title" />
				<a href="#" onclick="opendivNewBeguda();" ><s:text name="beguda.new" /></a>
			</h2> 
	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
	
	  		 <div  style="width:800px;" alig="center">
				<table class="selecciom dataTable" id="tbl_begudes" width="800px">
					<thead>
						<tr>							
							<th><s:text name="mant.begudes.table.tipus" /></th>
							<th><s:text name="mant.begudes.table.nom" /></th>
							<th><s:text name="mant.begudes.table.preu" /></th>
							<th width="40px"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>					
				<div id="infobegudanew">
				 <table class="noborder"  style="background-color:rgb(192,192,192)">
			         <tr>
				          <td colspan="3" >
				         	     <h1 class="capcalera" style="display:inline;padding:0px">
				         	      	<s:text name="mant.begudes.info.title" />
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>		
				
					<h1><s:text name="txt.begudes.dades.generals" /></h1>
					
		
					
					<div id="apd_div">
						<s:form action="saveBeguda" method="POST" enctype="multipart/form-data" >
							<s:textfield key="beguda.nom" id="nomBeguda"  ></s:textfield>
							<s:select list="tipusBegudaList" key="beguda.tipus" id="tipusBeguda" listKey="descripcio" listValue="descripcio">					
							</s:select>					
							<s:textfield key="beguda.preu"  id="importBeguda" onblur="onlyDouble(this.value,this.id)" ></s:textfield>		
							<s:textarea key="beguda.descripcio" id="descripcioBeguda" onkeyup="return ismaxlength(this,1000)" cols="40" rows="4" ></s:textarea>																																											
							<s:hidden key="beguda.id" id="id" ></s:hidden>			
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
