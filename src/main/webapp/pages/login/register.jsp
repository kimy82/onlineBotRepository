<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	
</head>
<body>

<div id="light_Reg">
	<div id="light_Reg_int">
		<c:if test="${dialog eq 'true'}">
			<div id="moveRegist" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix addmove">
				MOVE(image)
			</div>
		</c:if>
		<div class="light_Reg_top">
			<s:text name="txt.register.info" />
		</div>
		<hr class="sep6">
		<div class="light_Reg_body">
			<div id="form_Reg">
				<s:form class="form" action="registerUser" id="registerForm" >
					
				<s:text name="user.username" />*:<br> <s:textfield cssClass="inputs" key="username" id="username" theme="simple" onclick="focus()" onkeyup="return ismaxlength(this,45)" /><br>
				<s:text name="user.telNumber" />*:<br> <s:textfield cssClass="inputs" key="telefon" id="telefon" theme="simple" onclick="focus()" /><br>
				<s:text name="user.email" />*:<br> <s:textfield cssClass="inputs" key="email" id="email" theme="simple" onclick="focus()" /><br>
				<s:text name="user.password" />*:<br> <s:password cssClass="inputs" key="password" id="password" theme="simple" onclick="focus()" /><br>
				<s:text name="user.password.retype" />*:<br> <s:password cssClass="inputs" key="confirmPassword" id="confirmPassword" theme="simple" onclick="focus()" /><br>
		
				<s:hidden key="address" id="comandaddressbis" ></s:hidden>
				
				<c:import url="/pages/includes/addressbis.jsp" />	
									
				<br><br><br>
				<s:text name="user.altres" />*:<br> <s:textfield cssClass="inputs" key="altres" id="altres" theme="simple" onkeyup="return ismaxlength(this,200)" onclick="focus()" /><br>
				
				<input class="boton" type="button" onclick="validate();" value="<s:text name='txt.user.register' />">
				
				<c:if test="${dialog eq 'true'}">
					&nbsp;<input class='boton' type='button' onclick='closeRegist()' value='TANCA' />
				</c:if>
				
				</s:form>
			</div>				
		</div>
	</div>
	<div class="maps" id="map_canvasbis">	
		<iframe width="649" height="649px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.es/maps?f=q&amp;source=s_q&amp;hl=ca&amp;geocode=&amp;q=Carrer+Doctor+Antic+Roca,+23,+Girona&amp;aq=0&amp;oq=Carrer+Doctor+Antic+Roca+23,+Girona&amp;sll=41.973792,2.822064&amp;sspn=0.007314,0.015836&amp;g=Carrer+Doctor+Antic+Roca,+Girona&amp;ie=UTF8&amp;hq=&amp;hnear=Carrer+Doctor+Antic+Roca,+23,+17003+Girona&amp;ll=41.973793,2.822059&amp;spn=0.007314,0.015836&amp;t=m&amp;z=14&amp;output=embed"></iframe><br />
	</div>
	<label id="errorRegist" style="font-style: italic; color: red;" ></label>
</div>

<!-- Scripts --> 
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/registre.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/prova.css' />" />
	
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<c:import url="/pages/includes/headerContext.jsp" />
		
	
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/pages/login/jsregister.js' />" type="text/javascript"></script>
	

<script type="text/javascript" >		
	new Addressbis.addressValidation();
	initParamsbis = new  InitParamsbis( "<s:text name='txt.user.empty' />","<s:text name='txt.user.wrong' />", "<s:text name='txt.password.empty' />","<s:text name='txt.password.noteq' />","<s:text name='txt.tel.empty' />","<s:text name='txt.address.empty' />");
</script> 
</body>
</html>