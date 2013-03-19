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
	<title><s:text name="mant.user.title" /></title>	
</head>
<body id="users">
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
	
			<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
			</div>
			<br>
			<h1><s:text name="txt.user.AssociaPromo" /></h1><s:select  onchange="linkAllUserToPromo()"  id="promoAssocAllUsers"  headerValue="" headerKey="" list="associadesList" listKey="id" listValue="descripcio">						
			</s:select>
			<br>
	  		<div  style="width:932px;" alig="center">
				<table class="selecciom dataTable" id="tbl_usuaris" width="932px">
					<thead>
						<tr>
							<th><s:text name="mant.user.table.username" /></th>
							<th><s:text name="mant.user.table.estado" /></th>
							<th width="40px"></th>
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
</div>
<div id="user_dialog" class="filtres filtres-oberts">
	<h1><s:text name="txt.user.nom" /></h1><label id="usernom" ></label><br>
	<h1><s:text name="txt.user.email" /></h1><label id="useremail" ></label><br>
	<h1><s:text name="txt.user.tel" /></h1><label id="usertel" ></label><br>
	<h1><s:text name="txt.user.address" /></h1><label id="useraddress" ></label><br>	 		
	<h1><s:text name="txt.user.indicacions" /></h1><label id="userind" ></label><br>
	<h1><s:text name="txt.user.nComandesRealitzades" /></h1><label id="nComandesRealitzades" ></label><br>
	<h1><s:text name="txt.user.nComandesAmbTargeta" /></h1><label id="nComandesAmbTargeta" ></label><br>
	<h1><s:text name="txt.user.nComandesSenseTargeta" /></h1><label id="nComandesSenseTargeta" ></label><br>
	<h1><s:text name="txt.user.AssociaPromo" /></h1><select id="promosAssociades" onchange="linkUserToPromo()" > <option value="" >&nbsp;</option></select>
</div> 
<!-- Scripts --> 
	<link rel="stylesheet" href="<c:url value='/css/admin.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/portamu/reset.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
	<link rel="stylesheet" href="<c:url value='/css/TableTools.css' />" type="text/css"   media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

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
	
	<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/TableTools.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/ZeroClipboard.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/admin/usuaris/jsusuaris.js' />"></script>

<script language="javascript">
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='txt.avis.borrat'/>",
			"<s:text  name='txt.confirm.borra.user'/>");
</script>
</body>
</html>

