<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Locale"%>
<html>
<head>
    <title>Gestió</title>

    <link rel="stylesheet" href="<c:url value='/css/loadCalendar.css' />" type="text/css" />
    <link rel="stylesheet" href="<c:url value='/css/participadasCalendar.css'/>" type="text/css" />         
        
    <script src="<c:url value='/js/jquery/jquery.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/loadCalendar.js'/>" type="text/javascript" ></script>   	
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/ext-all.css'/>" />        
    <script type="text/javascript" src="<c:url value='/js/ext/ext-base.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/ext/ext-all-debug.js'/>"></script>  	
	<script src="<c:url value='/pages/admin/restaurants/configMoters/jsconfig.js'/>" type="text/javascript"></script>    
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	
	<script language="javascript">
		var initTableParams = new InitTableParams("<s:text  name='txt.dades.cargades'/>",
													"<s:text  name='datatables.paginate.last'/>",
													"<s:text  name='datatables.paginate.next'/>",
													"<s:text  name='datatables.paginate.previous'/>",
													"<s:text  name='datatables.paginate.first'/>",
													"<s:text  name='datatables.loading'/>",
													"<s:text  name='txt.avis.borrat'/>");
	</script>
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
			    
				<div  style="width:950px;" alig="center" >
					<table class="selecciom dataTable" id="tbl_moters">
						<thead>
							<tr>
								<th><s:text name="mant.moters.dia" /></th>
								<th>8:00</th>
								<th>8:30</th>	
								<th>9:00</th>
								<th>9:30</th>
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
								<th>19:30</th>
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

</body>
</html>