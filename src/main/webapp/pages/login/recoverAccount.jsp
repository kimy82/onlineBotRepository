<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Recover Page</title>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>Recover with Username and Password (Custom Page)</h3>
 
 <s:form action="recoverAccount.action" namespace="/" >
 
	<s:textfield key="username" />
 
	<s:submit key="submit" name="submit" />
 
</s:form>
</body>
</html>