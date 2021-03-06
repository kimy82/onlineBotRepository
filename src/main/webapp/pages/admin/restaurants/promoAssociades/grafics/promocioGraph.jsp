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
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="mant.promo.title.gestio" /></title>	
	<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/admin.ass.promograph.min.css' />" type="text/css"   media="screen" />
</head>
<body id="promo">
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
			   		 			<div id="container">
    
								</div>
								<s:select list="idsPromocions" listKey="id" listValue="descripcio" onchange="showDates(this.value)"></s:select>
								<div id="containerDates">
    
								</div>
							</div>
					</div>
		</div>
	</div>
</div>
	<script type="text/javascript" src="<c:url value='/js/jspromocioGraph.ass.admin.min.js' />"></script>
<script language="javascript">			
	var myData = ${requestScope.dataChart};
	var initGraficParams= new InitGraficParams("<s:text  name='txt.promo.uses'/>","<s:text  name='txt.promo.used'/>");
</script> 
</body>
</html>
