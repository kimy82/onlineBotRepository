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
<title><s:text name="admin.title" /></title>
</head>
<body id="conrest">
<c:import url="/pages/includes/headerContext.jsp" />
<div class="content">
	<div class="container">
	<div class="topadmin">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		</div>
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="comandes.comfirm.title" />
			</h2>
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>	
	  		 <div  style="width:700px;" alig="center">
	  		 	<a href="#" onclick="reloadTableComandes()" ><img src="<c:url value='/images/refresh.png' />" /></a>
				<table class="selecciom dataTable" id="tbl_all_comandes" width="800px">
					<thead>
						<tr>
							<th><s:text name="tbl.comandes.confirm.nom" /></th>
							<th><s:text name="tbl.comandes.confirm.tel" /></th>
							<th><s:text name="tbl.comandes.confirm.direccio" /></th>
							<th><s:text name="tbl.comandes.hora" /></th>
							<th><s:text name="tbl.comandes.confirm.preu" /></th>
							<th><s:text name="tbl.comandes.nomrestaurant" /></th>
							<th><s:text name="tbl.comandes.plats" /></th>
							<th><s:text name="tbl.comandes.method.pagament" /></th>																												
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>		
		</div>
	</div>
</div>
</div>
</div>
<!-- Scripts --> 
	<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/TableTools.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/TableTools.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/ZeroClipboard.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/admin/comandes/all/jscomandes.js' />"></script>

<script language="javascript">
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='txt.avis.borrat'/>",
			"<s:text  name='txt.avis.enviat'/>");
</script>
</body>
</html>
