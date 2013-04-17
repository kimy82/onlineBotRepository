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
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/foro.min.css' />" />
	<title><s:text name="txt.welcome.comanda.principal" /></title>	
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<div id="foro">
	<div id="foro_int">
			<div id="move" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix addmove">
			</div>
			<div class="foro_top">
				<s:text name="txt.login.title" />
				<input class="tancarBot" type="button" onclick="closeInfoPlat()"  value="X" />
			</div>
			<hr class="sep8" />
			<div class="light_body">
				<c:if test="${not empty plat }">
					<input type="hidden" id="idPlat" value="${plat.id}" />
						<div id="leftvota">
							<table class="vota">
								<tr>
									<td><img class="imgvot" id="imageRestaurant" src="/${initParam.app}/img/elements/estrelles${plat.votacio.punctuacio}.jpg" /></td>
								</tr>
								<tr class="imatge">	
									<td ><img id="imageRestaurant" width="230px"  src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}" /></td>
								</tr>
								<tr>
									<td class="nom">${plat.nom}</td>
								</tr>
								<tr class="descripció">
									<td>${plat.descripcio}</td>
								</tr>
							</table>
						
    				<c:if test="${nameAuth ne 'anonymousUser' }">
						<div id="star_punctuation" >
							<table id="tbl_stars" >
								<tr>
									<td class="votplat" colspan="5" ><s:text name="txt.vota.el.plat" />:</td>
								</tr>
								<tr>
									<td><a href="#" onclick="starManager(1)" ><img id="star1" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
									<td><a href="#" onclick="starManager(2)" ><img id="star2" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
									<td><a href="#" onclick="starManager(3)" ><img id="star3" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
									<td><a href="#" onclick="starManager(4)" ><img id="star4" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
									<td><a href="#" onclick="starManager(5)" ><img id="star5" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
								</tr>
								<tr>
									<td colspan="5" ><input class="votat" type="button" id="saveVotButton"  onclick="votaPlatDialog()" value="submit votacio"  /></td>
								</tr>
							</table>					
						</div>
					</c:if>
					</br>
				</div>
		</div>
		<div id="rightforo">
    		<div class="comments_foro" id="plat_${plat.id}" >
   				 <div id="tablascroll">
					<table id="comments_tbl" >				
					    <s:iterator value="plat.comments" var="comt">
									<tr id="${comt.id}" >
										<td class="comenta">${comt.comment} <b>(${comt.nomUsu} &nbsp; ${comt.dia})</b></td>																															
										<c:if test="${nameAuth eq 'ROLE_ADMIN' }">											
												<td class="delet"><a href="#" onclick="deleteComment(${comt.id})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																			
										</c:if>	
									</tr>						
						</s:iterator>								
					</table>
				</div>
				<div id="degra"><img src="<c:url value='/images/degradat.png' />" /></div>
				<table class="coment">
					<c:if test="${nameAuth ne 'anonymousUser' }">
						<tr>
							<td><textarea rows="4" cols="44" id="newComment" ></textarea></td>
						</tr>
						<tr>
							<td><input class="enviarcom" type="button" onclick="saveComment()"  value="Submit" /></td>							
						</tr>
					</c:if>
				</table>
			</div>
		</c:if>
	</div>
	</div>
</div>
	<script src="<c:url value='/js/jsforo.js'/>" type="text/javascript"></script>
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
