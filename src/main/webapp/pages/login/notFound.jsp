<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><s:text name="txt.nofound.page" /></title>
</head>
<body onload='document.f.j_username.focus();'>
	<h3><s:text name="txt.nofound.user" /></h3>
 <br>
 <c:import url="/pages/includes/goHome.jsp" />	
 <br>
<b><%=request.getAttribute("userNotFound").toString() %></b>
</body>
</html>