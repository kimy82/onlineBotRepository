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
	<link rel="stylesheet" href="<c:url value='/css/admin.newlet.min.css' />" type="text/css"   media="screen" />  
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /> 
	<title><s:text name="txt.newsletter.title" /></title>
</head>
<body>
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
				
	  		 <div  style="width:800px;" alig="center">
	  		 	<label for="target" ><s:text name="txt.newsletter.target" /></label>
	  		 	<select id="target">
	  		 		<option value="tots" ><s:text name="txt.newsletter.target.0" /></option>
	  		 		<option value="news" ><s:text name="txt.newsletter.target.1" /></option>
	  		 		<option value="users" ><s:text name="txt.newsletter.target.2" /></option>
	  		 	</select> 
	  		 	<br></br>
				<textarea cols="100" rows="30" id="mytextbox"></textarea>
			</div>				
		</div>
	</div>
</div>
</div>
</div>	
<script type="text/javascript" src="<c:url value='/js/jsnewsletter.admin.min.js' />"></script>
<script type="text/javascript">
$(document).ready(function(){
	var save_tool = {
			icon:"mail.png",tooltip:"Save",command:function(){send();}
			};
	var toolbar_1 =[save_tool,"separator","bold","italic","underline","fontsize","fontfamily","fontcolor","highlight","separator","undo","redo","left","right","center","justify","separator","image"];	
	$("#mytextbox").htmlbox({
		skin:"blue",
		toolbars:[toolbar_1]
	});
});

	var initTableParams = new InitTableParams(
			"<s:text  name='txt.confirm.sending'/>",
			"<s:text  name='txt.sent'/>");
</script>
</body>
</html>