<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
<body>
	<h3><s:text name="txt.register.info" /></h3>
 
 <s:form action="registerUser"  id="registerForm" >
 
	<s:textfield key="username" id="username"  />
	<s:password key="password" id="password" />
	<s:password key="confirmPassword" id="confirmPassword" />
	<s:textfield key="telefon" maxlength="10" id="telefon" ></s:textfield>
	<h1><s:text name="txt.address.div" /></h1>
	<s:hidden key="address" id="comandaddressbis" ></s:hidden>
	<c:import url="/pages/includes/addressbis.jsp" />	
 	
 	<input type="button" value="submit" onclick="validate();" />

</s:form>

<!-- Scripts --> 
	<c:import url="/pages/includes/headerContext.jsp" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />	
	
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/pages/login/jsregister.js' />" type="text/javascript"></script>

<script type="text/javascript" >		

new Addressbis.addressValidation();
initParamsbis = new  InitParamsbis( "<s:text name='txt.user.empty' />","<s:text name='txt.user.wrong' />", "<s:text name='txt.password.empty' />","<s:text name='txt.password.noteq' />","<s:text name='txt.tel.empty' />","<s:text name='txt.address.empty' />");
</script> 
<c:import url="/pages/includes/subAlertOnline.jsp" />
</body>
</html>