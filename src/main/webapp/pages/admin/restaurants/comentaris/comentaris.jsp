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
	<title><s:text name="txt.gestio.comentaris" /></title>	
</head>
<body>
	<c:import url="/pages/includes/headerContext.jsp" />
    <s:select list="listPlats" headerKey="" headerValue="Escull un plat" id="platId" listKey="id" listValue="nom" onchange="loadComments(this.value)" ></s:select>
    <div class="comments_foro" id="plat_${plat.id}" >
			<table id="comments_tbl" >
		
			    <s:iterator value="plat.comments" var="comt">
							<tr id="${comt.id}" >
								<td>${comt.comment}</td>																				
									
							    <td><a href="#" onclick="deleteComment(${comt.id})" ><img src="<c:url value='/images/delete.png' />" /> </a></td>																									
							</tr>						
				</s:iterator>								
			</table>
	</div>



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
	<script src="<c:url value='/pages/admin/restaurants/comentaris/jscomentaris.js'/>" type="text/javascript"></script>
	<script>

		var initParams = new InitParams("<s:text name='txt.comment.deleted'/>");
																		
	</script>
	<c:import url="/pages/includes/subAlertOnline.jsp" />
	<c:import url="/pages/includes/subErrorAjax.jsp" />
</body>
</html>
