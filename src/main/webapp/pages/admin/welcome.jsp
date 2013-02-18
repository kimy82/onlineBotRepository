<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<div class="content">
	<div class="container">
		<div class="topadmin">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		</div>
		<table class="int_menu">
		<tr>
		<td class="first"><a href="<c:url value='usuaris.action' />" ><img width="100px" src="<c:url value='/images/admin/usuaris.png' />"></a></td>
		<td><a href="<c:url value='plats.action' />" ><img width="100px" src="<c:url value='/images/admin/plats.png' />"></a></td>
		<td><a href="<c:url value='restaurants.action' />" ><img width="100px" src="<c:url value='/images/admin/restaurants.png' />"></a></td>
		<td><a href="<c:url value='config.action' />" ><img width="100px" src="<c:url value='/images/admin/config.png' />"></a></td>
		<td><a href="<c:url value='configMoters.action' />" ><img width="100px" src="<c:url value='/images/admin/configmotos.png' />"></a></td>
		<td><a href="<c:url value='promocions.action' />" ><img width="100px" src="<c:url value='/images/admin/promos.png' />"></a></td>
		<td><a href="<c:url value='begudes.action' />" ><img width="100px" src="<c:url value='/images/admin/begudes.png' />"></a></td>
		<td><a href="<c:url value='presentacio.action' />" ><img width="100px" src="<c:url value='/images/admin/presentacio.png' />"></a></td>
		<td><a href="<c:url value='comments.action' />" ><img width="100px" src="<c:url value='/images/admin/comentaris.png' />"></a></td>
		<td></td>
		</tr>
		<tr>
		<td class="first"><a href="<c:url value='usuaris.action' />" >Usuaris</a></td>
		<td><a href="<c:url value='plats.action' />" >Plats</a></td>
		<td><a href="<c:url value='restaurants.action' />" >Restaurants</a></td>
		<td><a href="<c:url value='config.action' />" >Config.</a></td>
		<td><a href="<c:url value='configMoters.action' />" >Config. Moters</a></td>
		<td><a href="<c:url value='promocions.action' />" >Promos</a></td>
		<td><a href="<c:url value='begudes.action' />" >Begudes</a></td>
		<td><a href="<c:url value='presentacio.action' />" >Slider</a></td>
		<td><a href="<c:url value='comments.action' />" >Comments</a></td>
		<td><a href="<c:url value='primeresComandes.action' />" >Control comandes</a></td>
		</tr>
		
		
		
		
		
	
		</table>
<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
	</div>
</div>
</body>
</html>
