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
	<!--  link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" /-->
	
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/vota.on.min.css' />" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	
	<title><s:text name="txt.info.comandes.title" /></title>
</head>

<body>

<br>
<c:import url="/pages/includes/headerContext.jsp" />
		<s:iterator value="platListToVote" var="plat" >
			<table id="tbl_stars" >
				<tr>
					<td colspan="5" ><s:text name="txt.vota.el.plat" />&nbsp; ${plat.nom}:</td>
				</tr>
				<tr>
					<td><a href="#" onclick="starManager(1)" ><img id="star1" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
					<td><a href="#" onclick="starManager(2)" ><img id="star2" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
					<td><a href="#" onclick="starManager(3)" ><img id="star3" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
					<td><a href="#" onclick="starManager(4)" ><img id="star4" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
					<td><a href="#" onclick="starManager(5)" ><img id="star5" width="20px"  src="<c:url value='/images/star0.jpg' />" /></a></td>
				</tr>
				<tr>
					<td colspan="5" ><input type="button" id="saveVotButton_${plat.id}"  onclick="votaPlatDialog(${plat.id})" value="submit votacio"  /></td>
				</tr>
			</table>
			<br></br>
		</s:iterator>
		
	
	

	<!--  script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
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
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js' />" type="text/javascript"></script-->
	
	<script type="text/javascript" src="<c:url value='/pages/users/votacions/jsvota.min.js' />"></script>
	<script language="javascript">
		
		var initParams = new  InitParams();
				
	</script>
	<c:if test="${not empty user}" >
	
	</c:if>
		
	<c:import url="/pages/includes/subAlertOnline.jsp" />
	
</body>
</html>