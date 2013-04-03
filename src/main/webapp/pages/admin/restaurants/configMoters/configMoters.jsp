<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Locale"%>
<html>
<head>
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
    <title><s:text name="txt.config.moters.title.gestio" /></title>     
    <link rel="stylesheet" href="<c:url value='/css/admin.configMoters.min.css' />" type="text/css"   media="screen" />	        
</head>
<body id="confMot">
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
	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
    <center>  	
    
    
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
			    <div  style="width:950px; height: 127px;" alig="center" margin-bottom="20px;">
			    		<s:form action="saveConfigMotersForRang" method="POST" enctype="multipart/form-data" >
							<tr>
								<td><s:text name="motersRang.diaIni" ></s:text></td>
								<td><s:textfield key="motersRang.diaIni"  id="diaIni" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
								<td><s:text name="motersRang.diaFi" ></s:text></td>
								<td><s:textfield key="motersRang.diaFi"  id="diaFi" maxlength="10" size="12" onfocus="blur()" theme="simple" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData2" ></td>
								<td><s:text name="motersRang.horaIni" ></s:text></td>										
								<td>
									<s:select list="horaList" key="motersRang.horaIni" listKey="descripcio" listValue="descripcio" theme="simple" >					
									</s:select>
								</td>
								<td><s:text name="motersRang.horaFi" ></s:text></td>										
								<td>
									<s:select list="horaList" key="motersRang.horaFi" listKey="descripcio" listValue="descripcio" theme="simple" >					
									</s:select>
								</td>						
								<td><s:text name="motersRang.numMoters" ></s:text></td>
								<td>
									<s:textfield key="motersRang.numMoters" ></s:textfield>
								</td>
							</tr>
							<s:submit></s:submit>
						</s:form>	
			    </div>
				<div  style="width:932px;" alig="center" >
				  <c:import url="calendar.jsp" /> 
					<table class="selecciom dataTable" id="tbl_moters" width="932px" >
						<thead>
							<tr>
								<th><s:text name="mant.moters.dia" /></th>
								<!--<th>08:00</th>
								<th>08:30</th>	
								<th>09:00</th>
								<th>09:30</th>
								<th>10:00</th>
								<th>10:30</th>
								<th>11:00</th>
								<th>11:30</th>
								<th>12:00</th>
								<th>12:30</th>
								<th>13:00</th>
								<th>13:30</th>
								<th>14:00</th>
								<th>14:30</th>
								<th>15:00</th>
								<th>15:30</th>
								<th>16:00</th>
								<th>16:30</th>
								<th>17:00</th>
								<th>17:30</th>
								<th>18:00</th>
								<th>18:30</th>
								<th>19:00</th>
								<th>19:30</th>-->
								<th>20:00</th>
								<th>20:30</th>
								<th>21:00</th>
								<th>21:30</th>
								<th>22:00</th>
								<th>22:30</th>
								<th>23:00</th>
								<th>23:30</th>
								<th>24:00</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
		
	</div>
		      	
</center>
</div>
</div>
</div>
</div>
	<script src="<c:url value='/js/jsconfig.admin.min.js'/>" type="text/javascript"></script>		
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar.min.js'/>"></script>	
<script type="text/javascript" >			
//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "diaIni",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });    
    Calendar.setup({
        inputField    	:    "diaFi",      // id del campo de texto
        ifFormat       	:    "%d-%m-%Y",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData2",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
var initTableParams = new InitTableParams("<s:text  name='txt.dades.cargades'/>","<s:text  name='datatables.paginate.last'/>","<s:text  name='datatables.paginate.next'/>","<s:text  name='datatables.paginate.previous'/>","<s:text  name='datatables.paginate.first'/>","<s:text  name='datatables.loading'/>","<s:text  name='txt.avis.borrat'/>");
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