<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:text name="txt.login.title" /></title>
</head>
<body onload='document.f.j_username.focus();'>

	<h3><s:text name="txt.login.explanation" /></h3>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'   >
 
		<table>
			<tr>
				<td><s:text name="txt.user.usuario" />:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td><s:text name="txt.user.password" />:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><a name="register" type="button" href="<c:url value='/preRegisterUser.action'/>" ><s:text name="txt.user.register" /></a>
				</td>
			</tr>
			<tr>
				<td colspan='2'><a name="register" type="button" href="<c:url value='/preRecoverAcount.action'/>" ><s:text name="txt.user.forgot.password" /></a>
				</td>
			</tr>
		</table>
 
	</form>
	
</body>
</html>