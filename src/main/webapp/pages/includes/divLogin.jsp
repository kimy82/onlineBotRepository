<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="header">
	<div id="int_header">
		<div id="int_left">
			<img src="img/elements/logo_portamu.png">
		</div>
		<div id="int_right">
			<ul>
			<c:if test="${nameAuth eq 'anonymousUser' }">
				<li><img src="img/elements/bar.png"></li>
				<li><a name="register" type="button" href="<c:url value='/preRegisterUser.action'/>" > <s:text name="txt.user.register" /></a></li>
				<li><img src="img/elements/bar.png"></li>
				<li><a name="register" type="button" href="#" onclick="goToLogin()" ><s:text name="txt.user.login" /></a></li>
				<li class="last"><img src="img/elements/bar.png"></li>
			</c:if>	
			<c:if test="${nameAuth ne 'anonymousUser' }">
				<li><a href="<c:url value="/j_spring_security_logout" />" > <s:text name="txt.user.logout" /></a></li>
				<li><img src="img/elements/bar.png"></li>
				 <a href="<c:url value='/user/comandesPasades.action' />" > <s:text name="txt.comandes.historic" /></a>				
			</c:if>
			</ul>
		</div>
	</div>
</div>

<!-- div align="left">
	<table>
		<c:if test="${nameAuth eq 'anonymousUser' }">
			<tr>
				<td colspan='2'><a name="register" type="button" href="<c:url value='/preRegisterUser.action'/>" ><s:text name="txt.user.register" /></a>
							</td>
				<td colspan='2'><a name="register" type="button" href="#" onclick="goToLogin()" ><s:text name="txt.user.login" /></a>
							</td>
			</tr>
		</c:if>		
		<c:if test="${nameAuth ne 'anonymousUser' }">
			<tr>		
				<td><a href="<c:url value="/j_spring_security_logout" />" > <s:text name="txt.user.logout" /></a></td>
			</tr>	
		</c:if>
	</table>
</div -->
<div id="login_dialog" class="filtres filtres-oberts" title="<s:text name='txt.user.login' />">	 		
</div> 
<script type="text/javascript" >
function goToLogin(){
	
	$("#login_dialog").load("login.action");	
	$("#login_dialog").dialog({ 
		   autoOpen: false,
		   height: 500,
		   width: 750,
		   modal: true,
		   close: function(event, ui) { 			   
			   $("#login_dialog").dialog("close"); 			  
			}
	});
    $("#login_dialog").dialog("open");    
}
</script>
