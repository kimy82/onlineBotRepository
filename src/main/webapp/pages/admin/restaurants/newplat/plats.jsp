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
	<title><s:text name="mant.plats.title.gestio" /></title>		
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="mant.plats.new.title" />
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
				         	      	<s:text name="mant.plats.info.title" />
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>
			    
				<s:form action="saveNewPlat" method="POST" enctype="multipart/form-data" >
					<s:select list="restaurantBasicList" key="idRestaurants" id="restaurantsid" listKey="id" listValue="descripcio" headerKey="0" headerValue="Restaurant" >					
					</s:select>
					<s:textfield key="plat.nom" id="nomplat" onkeyup="return ismaxlength(this,100)"  ></s:textfield>
					<s:textfield key="plat.nomES" id="nomplatES" onkeyup="return ismaxlength(this,100)"  ></s:textfield>
					<s:textfield key="plat.prioritat" id="prioritatplat" onkeyup="onlyEntero(this.value,this.id)"  ></s:textfield>
					<s:textfield key="plat.tempsPreparacio" id="tempsplat" onkeyup="onlyEntero(this.value,this.id)"  ></s:textfield>
					<s:textfield key="plat.codi" id="codiplat" onkeyup="return ismaxlength(this,20)"  ></s:textfield>
					<c:if test="${plat.actiu == true}">
						<s:checkbox  key="plat.actiu" id="actiuplat" value="true" ></s:checkbox>
					</c:if>
					<c:if test="${plat.actiu == false}">
						<s:checkbox  key="plat.actiu" id="actiuplat" ></s:checkbox>
					</c:if>
					<s:textarea key="plat.descripcio" id="descplat" cols="40" rows="4" onkeyup="return ismaxlength(this,1000)" ></s:textarea>	
					<s:textarea key="plat.descripcioES" id="descplatES" cols="40" rows="4" onkeyup="return ismaxlength(this,1000)" ></s:textarea>
					<s:textfield key="plat.preu" id="preuplat" onblur=" onlyDouble(this.value, this.id)" ></s:textfield>
					<s:select list="tipusPlat" key="plat.tipus" id="tipusplat" listKey="descripcio" listValue="descripcio" headerKey="" headerValue="" ></s:select>											
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />	
					<s:hidden key="plat.id" id="idplat" ></s:hidden>				
					<s:submit></s:submit>
				</s:form>			
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
	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/newplat/jsplats.js' />"></script>

<script language="javascript">
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='error.double'/>",
			"<s:text  name='txt.error.number'/>");
	
	$("#nomplat").val("${plat.nom}");
	$("#nomplatES").val("${plat.nomES}");
	$("#prioritatplat").val("${plat.prioritat}");
	$("#descplat").val("${plat.descripcio}");
	$("#descplatES").val("${plat.descripcioES}");
	$("#preuplat").val("${plat.preu}");
	$("#tipusplat [value='${plat.tipus}']").attr('selected', true);
	$("#restaurantsid [value='${idRestaurants}']").attr('selected', true);
	$("#codiplat").val("${plat.codi}");
	$("#idplat").val("${plat.id}");

	
</script>
</body>
</html>
