<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Locale"%>
<html>
<head>
    <title>Gestió</title>     
</head>
<body>
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
										
					<s:submit></s:submit>
				</s:form>	
	</div>		      
</center>
</div>
</div>
<!-- Scripts --> 
<c:if test="${fn:contains(header.Host,'7070')}">	
	<link rel="stylesheet" href="<c:url value='/css/tbl_comp_cal_ext.min.css' />" type="text/css"   media="screen" />
	<script src="<c:url value='/js/jsQueryAlone.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/calendarPop.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jsext.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jsconfigRestaurants.min.js' />" type="text/javascript"></script>
</c:if>
<c:if test="${fn:contains(header.Host,'9090')}">

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
</c:if>
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