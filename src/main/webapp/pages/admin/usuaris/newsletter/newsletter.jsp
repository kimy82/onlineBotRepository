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
	<title><s:text name="txt.newsletter.title" /></title>
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
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
	  		 		<option value="users" ><s:text name="txt.newsletter.target.1" /></option>
	  		 		<option value="news" ><s:text name="txt.newsletter.target.2" /></option>
	  		 	</select> 
	  		 	<br></br>
				<textarea cols="100" rows="30" id="mytextbox"></textarea>
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
	<script type="text/javascript" src="<c:url value='/pages/admin/usuaris/newsletter/jsnewsletter.js' />"></script>

<script src="<c:url value='/js/htmlBox/htmlbox.min.js' />" type="text/javascript"></script>
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

