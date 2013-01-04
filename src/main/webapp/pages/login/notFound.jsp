<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Not found Page</title>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>User not found</h3>
 
<b><%=request.getAttribute("userNotFound").toString() %></b>
</body>
</html>