<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div align="left">
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
</div>
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

$("#login_dialog").dialog({ 
	   autoOpen: false,
	   height: 700,
	   width: 750,
	   modal: true,
	   close: function(event, ui) { 			   
		   $("#login_dialog").dialog("close"); 			  
		}
	});
</script>
