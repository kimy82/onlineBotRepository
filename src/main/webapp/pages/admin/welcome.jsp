<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<h2>Menu</h2>

<br>
<table>
<tr>
<td><a href="<c:url value='usuaris.action' />" >M. Usuaris</a></td>
</tr>
<tr>
<td><a href="<c:url value='plats.action' />" >M. Plats</a></td>
</tr>
<tr>
<td><a href="<c:url value='restaurants.action' />" >M. Resta</a></td>
</tr>
<tr>
<td><a href="<c:url value='config.action' />" >M. Config</a></td>
</tr>
<tr>
<td><a href="<c:url value='configMoters.action' />" >M. Config Moters</a></td>
</tr>
<tr>
<td><a href="<c:url value='promocions.action' />" >M. Promos</a></td>
</tr>
<tr>
<td><a href="<c:url value='begudes.action' />" >M. begudes</a></td>
</tr>
</table>
</body>
</html>
