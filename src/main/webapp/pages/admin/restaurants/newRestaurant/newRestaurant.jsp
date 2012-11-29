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

<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/newRestaurant/jsnewrestaurant.js' />"></script>

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

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
					<s:textarea key="restaurant.descripcio" id="descrestaurant" onkeyup="return ismaxlength(this,1000)" cols="40" rows="4" ></s:textarea>							
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit></s:submit>
				</s:form>			
			</div>
		</div>
	</div>
</div>
</body>
</html>
