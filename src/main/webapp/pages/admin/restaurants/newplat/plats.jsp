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
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="mant.plats.title.gestio" /></title>		
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/admin.newplat.min.css' />" type="text/css"   media="screen" />
</head>
<body id="plats">
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
			    
				<s:form id="form_saveNewPlat" action="saveNewPlat" method="POST" enctype="multipart/form-data" >
					<s:select list="restaurantBasicList" key="idRestaurants" id="restaurantsid" listKey="id" listValue="descripcio" headerKey="0" headerValue="Restaurant" >					
					</s:select>
					<s:textfield key="plat.nom" id="nomplat" onkeyup="return ismaxlength(this,100)"  ></s:textfield>
					<s:textfield key="plat.nomES" id="nomplatES" onkeyup="return ismaxlength(this,100)"  ></s:textfield>
					<s:textfield key="plat.prioritat" id="prioritatplat" onkeyup="onlyEntero(this.value,this.id)"  ></s:textfield>
					<s:textfield key="plat.tempsPreparacio" id="tempsplat" onkeyup="onlyEntero(this.value,this.id)"  ></s:textfield>
					<s:textfield key="plat.codi" id="codiplat" onkeyup="return ismaxlength(this,20)"  ></s:textfield>
					<c:if test="${empty plat or plat.actiu == true or  empty plat.id}" var="actiu" >
						<s:checkbox  key="plat.actiu" id="actiuplat" value="true" ></s:checkbox>
					</c:if>
					<c:if test="${actiu== false}">
						<s:checkbox  key="plat.actiu" id="actiuplat" ></s:checkbox>
					</c:if>
					<s:textarea key="plat.descripcio" id="descplat" cols="40" rows="4" onkeyup="return ismaxlength(this,1000)" ></s:textarea>	
					<s:textarea key="plat.descripcioES" id="descplatES" cols="40" rows="4" onkeyup="return ismaxlength(this,1000)" ></s:textarea>
					<s:textfield key="plat.preu" id="preuplat" onblur="onlyDouble(this.value,this.id)" ></s:textfield>
					<s:select list="tipusPlat" key="plat.tipus" id="tipusplat" listKey="descripcio" listValue="descripcio" headerKey="" headerValue="" ></s:select>
					<tr>											
					<c:if test="${empty plat or plat.celiacs == true or  empty plat.id}" var="celiacs" >
						<td><s:text name="plat.celiacs" ></s:text></td><td><s:checkbox  key="plat.celiacs" id="celiacsplat" value="true" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${celiacs== false}">
						<td><s:text name="plat.celiacs" ></s:text></td><td><s:checkbox  key="plat.actiu" id="celiacsplat" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${empty plat or plat.ous == true or  empty plat.id}" var="ous" >
						<td><s:text name="plat.ous" ></s:text></td><td><s:checkbox  key="plat.ous" id="ousplat" value="true" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${ous== false}">
						<td><s:text name="plat.ous" ></s:text></td><td><s:checkbox  key="plat.ous" id="ousplat" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${empty plat or plat.lactics == true or  empty plat.id}" var="lactics" >
						<td><s:text name="plat.lactics" ></s:text></td><td><s:checkbox  key="plat.lactics" id="lacticsplat" value="true" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${lactics== false}">
						<td><s:text name="plat.lactics" ></s:text></td><td><s:checkbox  key="plat.lactics" id="lacticsplat" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${empty plat or plat.fruitsCecs == true or  empty plat.id}" var="fruitsCecs" >
						<td><s:text name="plat.fruitsCecs" ></s:text></td><td><s:checkbox  key="plat.fruitsCecs" id="fruitsCecsplat" value="true" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${fruitsCecs== false}">
						<td><s:text name="plat.fruitsCecs" ></s:text></td><td><s:checkbox  key="plat.fruitsCecs" id="fruitsCecsplat" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${empty plat or plat.vegetarians == true or  empty plat.id}" var="vegetarians" >
						<td><s:text name="plat.vegetarians" ></s:text></td><td><s:checkbox  key="plat.vegetarians" id="vegetariansplat" value="true" theme="simple" ></s:checkbox></td>
					</c:if>
					<c:if test="${vegetarians== false}">
						<td><s:text name="plat.vegetarians" ></s:text></td><td><s:checkbox  key="plat.vegetarians" id="vegetariansCecsplat" theme="simple" ></s:checkbox></td>
					</c:if>
					</tr>
					
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />	
					
					<s:hidden key="plat.id" id="idplat" ></s:hidden>		
					<tr><td><input type="button" value="submit" onclick="submitPlat()" ></input></td></tr>							
				</s:form>			
			</div>
		</div>
	</div>
</div>
</div>
</div>
	<!-- script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/admin/restaurants/newplat/jsplats.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/validations/jsvalidations.js' />"></script -->
	
	<script type="text/javascript" src="<c:url value='/js/jsplats.admin.min.js' />"></script>

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
