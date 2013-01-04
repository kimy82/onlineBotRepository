<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div align="left">
<table>
	<c:if test="${nameAuth eq 'anonymousUser' }">
		<tr>
			<td colspan='2'><a name="register" type="button" href="<c:url value='/preRegisterUser.action'/>" ><s:text name="txt.user.register" /></a>
						</td>
			<td colspan='2'><a name="register" type="button" href="<c:url value='/login.action'/>" ><s:text name="txt.user.login" /></a>
						</td>
		</tr>
	</c:if>		
	<c:if test="${nameAuth ne 'anonymousUser' }">
		<tr>		
			<td><a href="<c:url value="/j_spring_security_logout" />" > <s:text name="txt.user.logout" /></a></td>
		</tr>	
	</c:if>
</table>
</div>