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
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="txt.gestio.comentaris" /></title>	
	<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/admin.comentaris.min.css' />" type="text/css"   media="screen" />
</head>
<body id="coment">
	<c:import url="/pages/includes/headerContext.jsp" />
	<div class="content">
	<div class="container">
		<div class="topadmin">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		</div>
		<c:import url="pestanas.jsp" />
    <s:select list="listPlats" headerKey="" headerValue="Escull un plat" id="platId" listKey="id" listValue="nom" onchange="loadComments(this.value)" ></s:select>    
    <div class="comments_foro" id="plat_${plat.id}" >
			<table id="comments_tbl" >
		
			    <s:iterator value="plat.comments" var="comt">
							<tr id="${comt.id}" >
								<td>${comt.comment} (${comt.nomUsu} &nbsp; ${comt.dia})</td>																				
									
							    <td><a href="#" onclick="deleteComment(${plat.id},${comt.id})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																									
							</tr>						
				</s:iterator>								
			</table>
	</div>
	  <s:select list="listBegudes" headerKey="" headerValue="Escull una beguda" id="begudaId" listKey="id" listValue="nom" onchange="loadCommentsBeguda(this.value)" ></s:select>
    <div class="comments_foro" id="beguda_${beguda.id}" >
			<table id="comments_tbl_b" >
		
			    <s:iterator value="beguda.comments" var="comt">
							<tr id="${comt.id}" >
								<td>${comt.comment} (${comt.nomUsu} &nbsp; ${comt.dia})</td>																				
									
							    <td><a href="#" onclick="deleteCommentBeguda(${comt.id})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																									
							</tr>						
				</s:iterator>								
			</table>
	</div>
	<s:text name="txt.load.all.comentaris" /><input type="checkbox" id="loadAll" onclick="loadAllComments()" />
 <div class="comments_all" id="comments_all" >
			<table id="comments_tbl_all" >
		
			    <s:iterator value="listComments" var="comt">
			    		<c:if test="${not empty comt.idPlat}">
			    			<tr id="all_pl_${comt.idComment}" >
								<td>${comt.comment} (${comt.nomUsu} &nbsp; ${comt.dia})</td>																													
							    <td><a href="#" onclick="deleteCommentFormAll(${comt.idPlat},${comt.idComment})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																									
							</tr>
			    		</c:if>
			    		<c:if test="${not empty comt.idBeguda}">
			    			<tr id="all_bg_${comt.idComment}" >
								<td>${comt.comment} (${comt.nomUsu} &nbsp; ${comt.dia})</td>																													
							    <td><a href="#" onclick="deleteCommentFromAllBeguda(${comt.idBeguda},${comt.idComment})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																									
							</tr>
			    		</c:if>											
				</s:iterator>								
			</table>
	</div>
</div>
</div>
</div>	
	<script src="<c:url value='/js/jscomentaris.admin.min.js'/>" type="text/javascript"></script>
	<script>
		var initParams = new InitParams("<s:text name='txt.comment.deleted'/>");															
	</script>
	<c:import url="/pages/includes/subAlertOnline.jsp" />
	<c:import url="/pages/includes/subErrorAjax.jsp" />
</body>
</html>
