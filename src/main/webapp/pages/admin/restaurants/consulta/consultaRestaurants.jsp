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
<body id="conrest">
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
				<s:text name="mant.restaurants.title" />
			</h2>
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>	
	  		 <div  style="width:932px;" alig="center">
				<table class="selecciom dataTable" id="tbl_restaurants" width="932px">
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
					<s:textarea key="restaurant.descripcioES" id="descrestaurantES" cols="40" rows="4" ></s:textarea>
					<s:textfield key="restaurant.codiMaquina" id="codiMaquina" onkeyup="return ismaxlength(this,20)"  ></s:textfield>				
					<s:file name="fileUpload" label="Canvia la foto" size="40" />
					<s:hidden key="restaurant.id"  id="idRestaurant" ></s:hidden>
					<s:hidden key="restaurant.hores" id="horesRestaurant" />
					<s:submit></s:submit>
				</s:form>
				<table style="overflow:auto;" width="600px;" >
					<tr>
					<td><input type="button"  id="0800" class="notcheck" value="0800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0830" class="notcheck" value="0830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0900" class="notcheck" value="0900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0930" class="notcheck" value="0930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1000" class="notcheck" value="1000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1030" class="notcheck" value="1030" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1100" class="notcheck" value="1100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1130" class="notcheck" value="1130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1200" class="notcheck" value="1200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1230" class="notcheck" value="1230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1300" class="notcheck" value="1300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1330" class="notcheck" value="1330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1400" class="notcheck" value="1400" onclick="saveHoraObertura(this.id)" /></td>
				</tr>
				<tr>
					<td><input type="button"  id="1430" class="notcheck" value="1430" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1500" class="notcheck" value="1500" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1530" class="notcheck" value="1530" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1600" class="notcheck" value="1600" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1630" class="notcheck" value="1630" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1700" class="notcheck" value="1700" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1730" class="notcheck" value="1730" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1800" class="notcheck" value="1800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1830" class="notcheck" value="1830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1900" class="notcheck" value="1900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1930" class="notcheck" value="1930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2000" class="notcheck" value="2000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2030" class="notcheck" value="2030" onclick="saveHoraObertura(this.id)" /></td>
				</tr>
				<tr>
					<td><input type="button"  id="2100" class="notcheck" value="2100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2130" class="notcheck" value="2130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2200" class="notcheck" value="2200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2230" class="notcheck" value="2230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2300" class="notcheck" value="2300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2330" class="notcheck" value="2330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2400" class="notcheck" value="2400" onclick="saveHoraObertura(this.id)" /></td>
				</tr>
				</table>
				<br>
				<img id="imageRestaurant" width="200px"  src="../images/noFoto.gif" />
				<br>
				<b><s:text name="mant.restaurants.llistat.plats" /></b>
				<br>
				<div  style="width:932px;" alig="center" >
					<table class="selecciom dataTable" id="tbl_plats" width="932px">
						<thead>
							<tr>
								<th><s:text name="mant.retaurants.table.plats.nom" /></th>
								<th><s:text name="mant.retaurants.table.plats.descripcio" /></th>
								<th><s:text name="mant.retaurants.table.plats.prioritat" /></th>
								<th><s:text name="mant.retaurants.table.plats.actiu" /></th>								
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
</div>
</div>
<!-- Scripts --> 
	<style>
		.notcheck{
			background-color: grey;
		}
		.check{
			background-color: green;
		}
	</style>
	<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
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
			"<s:text  name='mant.restaurant.txtconfirm.delete.restaurant'/>",
			"<s:text  name='mant.restaurants.prioritat.canviat'/>");
</script>
</body>
</html>
