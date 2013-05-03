<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
<title><s:text name="txt.recoveruser.page" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/loguin.on.min.css' />" />	
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
</head>
<body onload='document.f.j_username.focus();' id="recovery">
<div id="recover">
<div id="recover_int">
	<div id="recover_top">
			<s:text name="txt.recoveruser.explanation" />
		</div>
	<hr class="sep5">
	<div id="recover_body">
 
	 <s:form action="recoverAccount.action" namespace="/" >
 </br>
	<tr><td class="reco">Mail:</td></tr>
	<tr><td><s:textfield cssClass="inputs" key="username" theme="simple" /></td></tr>
	
 
	<s:submit cssClass="boton"  key="submit" name="submit" value="SUBMIT" />
 
</s:form>
</div>
</div>
</div>
</body>
</html>