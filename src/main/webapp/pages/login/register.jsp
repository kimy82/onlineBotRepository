<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<body>
	<h3>Register with Username and Password</h3>
 
 <s:form action="registerUser"  id="registerForm" >
 
	<s:textfield key="username" id="username"  />
	<s:password key="password" id="password" />
	<s:password key="confirmPassword" id="confirmPassword" />
	<s:textfield key="telefon" maxlength="10" id="telefon" ></s:textfield>
	<h1>Address</h1>
		<div class="page">
			<div id="main">		   		
					<div id="map_canvas" style="float: right; height: 200px; width: 400px;"></div>				                
					                <table>
						                <tr>
						                	<td>						                	
	                        					<label for="carrer">
	                        							 <s:text name="adreca.carrer" /></label>		
	                        					<input type="text" id="carrer"  />  
	                        					
	                        					<label for="numcarrer">
	                        							  <s:text name="adreca.numcarrer" /></label>		
	                        					<input type="text" id="numcarrer"  />
	                        					
	                        					<label for="codi">
	                        							  <s:text name="adreca.codipostal" /></label>		
	                        					<input type="text" id="codi"  />
	                        					
	                        					<label for="poble">
	                        							  <s:text name="adreca.poble" /></label>		
	                        					<input type="text" id="poble" disabled="disabled" value="Girona"  />                        						                    													    										                
						                    </td>
						                </tr>
						                <tr>
							                <td>				                
							                    <input id="checkAdd" value="Comprova si és correcte" type="button" />
							                </td>
							                <td>
							                <label id="addressOK" ></label>
							                </td>    
						                </tr>
					                </table>
			</div>
		</div>
 	<s:textfield key="address" id="comandaddress" ></s:textfield>
 	<input type="button" value="submit" onclick="validate();" />

</s:form>

<!-- Scripts --> 

	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />	
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script src="<c:url value='/pages/login/jsregister.js' />" type="text/javascript"></script>

<script>		

new Address.addressValidation();
initParams = new  InitParams( "<s:text name='txt.user.empty' />","<s:text name='txt.user.wrong' />", "<s:text name='txt.password.empty' />","<s:text name='txt.password.noteq' />","<s:text name='txt.tel.empty' />","<s:text name='txt.address.empty' />");
</script> 
</body>
</html>