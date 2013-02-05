<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="header">
	<div id="int_header">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		<div id="int_right">
			<ul>
			<c:if test="${nameAuth eq 'anonymousUser' }">
				<li><img src="<c:url value='/img/elements/bar.png' />"></li>
				<li><a name="register" type="button" onclick="goToRegist()" href="#" > <s:text name="txt.user.register" /></a></li>
				<li><img src="<c:url value='/img/elements/bar.png' />"></li>
				<li><a name="register" type="button" href="#" onclick="goToLogin()" ><s:text name="txt.user.login" /></a></li>
				<li class="last"><img src="<c:url value='/img/elements/bar.png' />"></li>
			</c:if>	
			</ul>
		</div>
		<c:if test="${nameAuth ne 'anonymousUser' }">
		<div id="ben">
			<ul>
			<li>Benvingut ${nameAuth}, <a href="<c:url value='/user/comandesPasades.action' />" ><s:text name="txt.comandes.historic" /></a></li>
			<li class="last"><img src="<c:url value='/img/elements/bar.png' />"></li>
			<li><a href="<c:url value='/j_spring_security_logout' />" > <s:text name="txt.user.logout" /></a></li>
			</ul>
		</div>
		</c:if>
	</div>
</div>
<div id="login_dialog" class="filtres filtres-oberts" title="<s:text name='txt.user.login' />">	 		
</div> 
<div id="regist_dialog" class="filtres filtres-oberts" title="<s:text name='txt.user.register' />">	 		
</div> 
<script type="text/javascript" >
function goToLogin(){
	
	$("#login_dialog").load("/"+context+"/login.action");	
	$("#login_dialog").dialog({ 
		   autoOpen: false,
		   height: 370,
		   width: 330,
		   modal: true,
		   close: function(event, ui) { 			   
			   $("#login_dialog").dialog("close"); 			  
			}
	});
    $("#login_dialog").dialog("open");    
}

function goToRegist(){
	
	$("#regist_dialog").load("/"+context+"/preRegisterUser.action");	
	$("#regist_dialog").dialog({ 
		   autoOpen: false,
		   height: 780,
		   width: 960,
		   modal: true,
		   close: function(event, ui) { 			   
			   $("#regist_dialog").dialog("close"); 			  
			}
	});
    $("#regist_dialog").dialog("open");    
}
</script>
