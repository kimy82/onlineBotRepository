<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
<title><s:text name="txt.recoveruser.page" /></title>
</head>
<body onload='document.f.j_username.focus();'>
	<h3><s:text name="txt.recoveruser.explanation" /></h3>
 
 <s:form action="recoverAccount.action" namespace="/" >
 
	<s:textfield key="username" />
 
	<s:submit key="submit" name="submit" />
 
</s:form>
</body>
</html>