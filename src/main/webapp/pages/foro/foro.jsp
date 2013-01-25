<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<title><s:text name="txt.welcome.comanda.principal" /></title>	
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<c:if test="${not empty plat }">

<input type="hidden" id="idPlat" value="${plat.id}" />

<c:if test="${nameAuth ne 'anonymousUser' }">
	<div id="star_punctuation" >
		<table id="tbl_stars" >
			<tr>
				<td colspan="5" ><s:text name="txt.vota.el.plat" />:</td>
			</tr>
			<tr>
				<td><a href="#" onclick="starManager(1)" ><img id="star1" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
				<td><a href="#" onclick="starManager(2)" ><img id="star2" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
				<td><a href="#" onclick="starManager(3)" ><img id="star3" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
				<td><a href="#" onclick="starManager(4)" ><img id="star4" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
				<td><a href="#" onclick="starManager(5)" ><img id="star5" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
			</tr>
			<tr>
				<td colspan="5" ><input type="button" id="saveVotButton"  onclick="votaPlatDialog()" value="submit votacio"  /></td>
			</tr>
		</table>
	</div>
</c:if>
	<table>
		<tr>
			<td><img id="imageRestaurant" width="50px"  src="/{initParam.app}/images/star${plat.votacio.punctuacio}.jpg" /></td>
		</tr>
		<tr>
			<td>${plat.nom}</td>
			<td>${plat.preu}</td>
			<td>${plat.tipus}</td>
			<td rowspan="2" ><img id="imageRestaurant" width="200px"  src="/{initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}" /></td> 
		</tr>
		<tr>
			<td colspan="3" >${plat.descripcio}</td>
		</tr>
	</table>
    
    <div class="comments_foro" id="plat_${plat.id}" >
			<table id="comments_tbl" >
		
			    <s:iterator value="plat.comments" var="comt">
							<tr id="${comt.id}" >
								<td>${comt.comment}</td>								
												
								<c:if test="${nameAuth eq 'ROLE_ADMIN' }">
									
										<td><a href="#" onclick="deleteComment(${comt.id})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>								
									
								</c:if>	
							</tr>						
				</s:iterator>								
			</table>
			<table>
				<c:if test="${nameAuth ne 'anonymousUser' }">
					<tr>
						<td><textarea rows="4" cols="60" id="newComment" ></textarea></td>
					</tr>
					<tr>
						<td><input type="button" onclick="saveComment()"  value="Submit" /></td>
					</tr>
				</c:if>
			</table>
	</div>
</c:if>



</div>

<!-- Scripts --> 
	
	<link rel="stylesheet" href="<c:url value='/css/coin-slider-styles.css' />" type="text/css" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.dialog.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/slider/coin-slider.min.js' />"></script>
	<script src="<c:url value='/pages/foro/jsforo.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/comments/insultDetect.js'/>" type="text/javascript"></script>
	<script>

		var initParams = new InitParams("<s:text name='txt.comment.saved' />",
										"<s:text name='txt.comment.deleted'/>",
										"<s:text name='txt.confirm.vot'/>",
										"<s:text name='txt.vot.guardat'/>",
										"<s:text name='txt.alert.insult'/>");
																		
	</script>
	<c:import url="/pages/includes/subAlertOnline.jsp" />
	
</body>
</html>
