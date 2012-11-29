<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Register Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>Register with Username and Password (Custom Page)</h3>
 
 <s:form action="registerUser.action">
 
	<s:textfield key="username" />
	<s:password key="password" />
	<s:password key="confirmPassword" />
 
	<s:submit key="submit" name="submit" />
 
</s:form>
</body>
</html>