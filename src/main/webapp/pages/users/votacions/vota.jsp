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
	<script type="text/javascript" src="<c:url value='/js/jsvota.min.js' />"></script>
	<c:import url="/pages/includes/subAlertOnline.jsp" />
</body>
</html>