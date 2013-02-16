<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Locale"%>
<html>
<head>
    <title><s:text name="txt.config.restaurants.title.gestio" /></title>     
</head>
<body id="conf">
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
				<s:text name="txt.config.title" />
			</h2>	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
    <center>  	
     <c:import url="calendar.jsp" />  
	<div id="config_rest">
		<table class="noborder"  style="background-color:rgb(192,192,192)">
			         <tr>
				          <td colspan="3" >
				         	     <h1 class="capcalera" style="display:inline;padding:0px">
				         	      	<s:text name="txt.config.info.dia" />&nbsp;&nbsp;&nbsp;<span id="datePicked"></span>
				         	     </h1>
				    	  </td>
			       	 
			         </tr>
			    </table>
			    
				<s:form action="saveConfig" method="POST" enctype="multipart/form-data" >
					<s:select multiple="true" list="restaurantBasicList" key="idRestaurants" listKey="id" listValue="descripcio" onclick="loadMotersAndConfig(this.value)" >					
					</s:select>
					
					<s:checkbox key="configRestaurant.obert" id="obert" ></s:checkbox>					
					<s:hidden key="dia" id="selectedDia" ></s:hidden>				
					<s:hidden key="configRestaurant.hores" id="horesRestaurant" />			
										
					<s:submit></s:submit>
				</s:form>	
				<table style="overflow: scroll;" width="600px;" >
				<tr>
					<td><input type="button" class="notcheck" id="0800" value="0800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="0830" value="0830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="0900" value="0900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="0930" value="0930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1000" value="1000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1030" value="1030" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1100" value="1100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1130" value="1130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1200" value="1200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1230" value="1230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1300" value="1300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1330" value="1330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1400" value="1400" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1430" value="1430" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1500" value="1500" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1530" value="1530" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1600" value="1600" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1630" value="1630" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1700" value="1700" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1730" value="1730" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1800" value="1800" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1830" value="1830" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1900" value="1900" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="1930" value="1930" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2000" value="2000" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2030" value="2030" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2100" value="2100" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2130" value="2130" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2200" value="2200" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2230" value="2230" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2300" value="2300" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2330" value="2330" onclick="saveHoraObertura(this.id)" /></td>
					<td><input type="button" class="notcheck" id="2400" value="2400" onclick="saveHoraObertura(this.id)" /></td>
				</tr>
				</table>
	</div>		      
</center>
</div>
</div>
</div>
</div>
	<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
<!-- Scripts --> 
	<style>
		.notcheck{
			background-color: grey;
		}
		.check{
			background-color: green;
		}
	</style>

  	<link rel="stylesheet" href="<c:url value='/css/loadCalendar.css' />" type="text/css" />
    <link rel="stylesheet" href="<c:url value='/css/participadasCalendar.css'/>" type="text/css" />         
    <link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/ext-all.css'/>" />   
	<script src="<c:url value='/js/jquery/jquery.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/loadCalendar.js'/>" type="text/javascript" ></script>   
    <script type="text/javascript" src="<c:url value='/js/ext/ext-base.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/ext/ext-all-debug.js'/>"></script>  	
	<script src="<c:url value='/pages/admin/restaurants/configRestaurant/jsconfig.js'/>" type="text/javascript"></script>    	

<script language="javascript">
		var initTableParams = new InitTableParams("<s:text  name='txt.dades.cargades'/>");
		
Ext.onReady(function(){

	new Ext.Button({
        text: '',
        renderTo:'div_buttons_prev',
        scale: 'medium',
        width:'50px',
        iconCls: 'back',
        iconAlign: 'left',
        handler:function(){
        	previousYear('<%=Locale.getDefault().getLanguage()%>');
        }
	});
	new Ext.Button({
        text: '',
        renderTo:'div_buttons_next',
        scale: 'medium',
        width:'50px',
        iconCls: 'forward',
        iconAlign: 'right',
        handler:function(){
        	nextYear('<%=Locale.getDefault().getLanguage()%>');
        }
	});
	
});

</script>

</body>
</html>