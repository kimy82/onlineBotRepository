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
	<title><s:text name="mant.restaurants.new.title" /></title>
</head>
<body>

<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="mant.restaurants.new.title" />
			</h2>
	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
	
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
				<s:form action="saveNewRestaurant" method="POST" enctype="multipart/form-data" >
					<s:textfield key="restaurant.nom" id="nomrestaurant" onkeyup="return ismaxlength(this,200)"  ></s:textfield>
					<s:textfield key="restaurant.codiMaquina" id="codiMaquina" onkeyup="return ismaxlength(this,20)"  ></s:textfield>
					<s:textarea key="restaurant.descripcio" id="descrestaurant" onkeyup="return ismaxlength(this,1000)" cols="40" rows="4" ></s:textarea>							
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />	
					<s:hidden key="restaurant.hores" id="horesRestaurant" />									
					<s:submit></s:submit>
				</s:form>			
				<table style="overflow: scroll;" width="600px;" >
				<tr>
					<td><input type="button"  id="0800" value="0800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0830" value="0830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0900" value="0900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="0930" value="0930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1000" value="1000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1030" value="1030" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1100" value="1100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1130" value="1130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1200" value="1200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1230" value="1230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1300" value="1300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1330" value="1330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1400" value="1400" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1430" value="1430" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1500" value="1500" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1530" value="1530" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1600" value="1600" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1630" value="1630" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1700" value="1700" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1730" value="1730" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1800" value="1800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1830" value="1830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1900" value="1900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="1930" value="1930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2000" value="2000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2030" value="2030" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2100" value="2100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2130" value="2130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2200" value="2200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2230" value="2230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2300" value="2300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2330" value="2330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button"  id="2400" value="2400" onclick="saveHoraObertura(this.id)" /></td>
				</tr>
				</table>
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
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/newRestaurant/jsnewrestaurant.js' />"></script>
</body>
</html>
