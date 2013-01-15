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
<title><s:text name="mant.restaurants.title" /></title>
<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="mant.restaurants.title" />
			</h2>
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>	
	  		 <div  style="width:500px;" alig="center">
				<table class="selecciom dataTable" id="tbl_restaurants" width="500px">
					<thead>
						<tr>
							<th><s:text name="mant.retaurants.table.rest.nom" /></th>
							<th><s:text name="mant.retaurants.table.rest.descripcio" /></th>
							<th width="40px"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="inforestaurant">
				 <table class="noborder"  style="background-color:rgb(192,192,192)">
			         <tr>
				          <td colspan="3" >
				         	     <h1 class="capcalera" style="display:inline;padding:0px">
				         	      	<s:text name="mant.restaurants.info.title" />
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>		
				<s:form action="saveRestaurant" method="POST" enctype="multipart/form-data" >
					<s:textfield key="restaurant.nom" id="nomrestaurant"  ></s:textfield>
					<s:textarea key="restaurant.descripcio" id="descrestaurant" cols="40" rows="4" ></s:textarea>							
					<s:file name="fileUpload" label="Canvia la foto" size="40" />
					<s:hidden key="restaurant.id"  id="idRestaurant" ></s:hidden>
					<s:submit></s:submit>
				</s:form>
				<br>
				<img id="imageRestaurant" width="200px"  src="../images/noFoto.gif" />
				<br>
				<b><s:text name="mant.restaurants.llistat.plats" /></b>
				<br>
				<div  style="width:700px;" alig="center" >
					<table class="selecciom dataTable" id="tbl_plats" width="700px">
						<thead>
							<tr>
								<th><s:text name="mant.retaurants.table.plats.nom" /></th>
								<th><s:text name="mant.retaurants.table.plats.descripcio" /></th>
								<th><s:text name="mant.retaurants.table.plats.preu" /></th>	
								<th width="40px" ></th>						
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>	
			</div>			
		</div>
	</div>
</div>
<!-- Scripts --> 


	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/consulta/jsrestaurants.js' />"></script>

<script language="javascript">
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='txt.avis.borrat'/>",
			"<s:text  name='mant.restaurant.txtconfirm.delete.plat'/>",
			"<s:text  name='mant.restaurant.txtconfirm.delete.restaurant'/>");
</script>
</body>
</html>
