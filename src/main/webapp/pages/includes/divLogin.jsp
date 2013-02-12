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
<div id="login_dialog" class="filtres filtres-oberts" >	 		
</div> 
<div id="regist_dialog" class="filtres filtres-oberts">	 		
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
    $("#login_dialog").siblings('div.ui-dialog-titlebar').remove();  
    $("#login_dialog").removeClass("ui-dialog-content");
    $("#login_dialog").removeClass("ui-widget-content");
   	$("#login_dialog").addClass("ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix");
   	
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
    $("#regist_dialog").siblings('div.ui-dialog-titlebar').remove();  
    $("#regist_dialog").removeClass("ui-dialog-content");
    $("#regist_dialog").removeClass("ui-widget-content");
   	$("#regist_dialog").addClass("ui-helper-clearfix");
}
</script>
