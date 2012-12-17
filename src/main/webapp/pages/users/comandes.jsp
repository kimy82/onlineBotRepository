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
	<title>User comandes</title>
</head>

<body>
	<div id="errorsajax">
				<label style="color: red" id="errorsajaxlabel"></label>
	</div>
<h2>INFO USER</h2>	
<br><a href="#" onclick="openCloseDiv('infoUser');" >Canvia dades personals</a>
			<div  style="width:500px;" alig="center" id="infoUser" >
				<s:form action="saveUserDetails" method="POST" enctype="multipart/form-data" >
					<s:textfield key="user.username" id="username" onkeyup="return ismaxlength(this,45)"  ></s:textfield>		
					<tr>
						<td colspan="2"><input type="checkbox" onclick="openCloseDiv('password')" >Change password</a></td>
					</tr>				
					<div id="password">				
						<s:password key="user.password" id="password" onkeyup="return ismaxlength(this,45)" ></s:password>
						<tr><td>ReType :</td><td><input type="password" id="passwordRetyped" onkeyup="checkPassword()"/></td></tr>
					</div>					
											<tr><td>						                	
	                        					<label for="carrer">
	                        							 <s:text name="adreca.carrer" /></label>		
	                        					<input type="text" id="carrer"  />  
	                        					
	                        					<label for="numcarrer">
	                        							  <s:text name="adreca.numcarrer" /></label>		
	                        					<input type="text" id="numcarrer"  />
	                        					
	                        					<label for="codi">
	                        							  <s:text name="adreca.codipostal" /></label>		
	                        					<input type="text" id="codi"  />
	                        					
	                        					<label for="poble">
	                        							  <s:text name="adreca.poble" /></label>		
	                        					<input type="text" id="poble"  />                        						                    													    										                
						                    </td></tr>	
					<s:textfield key="user.telNumber" id="telNumber" onkeyup="return ismaxlength(this,10)" ></s:textfield>						                    
					<s:hidden key="user.address" id="address" ></s:hidden>		
					<s:hidden key="user.id" id="idUser" ></s:hidden>						                    										
					<s:submit onclick="fillAddress()" ></s:submit>
				</s:form>							
			</div>
<h2>INFO COMANDES REALITZADES</h2>

 			<div  style="width:500px;" alig="center">
				<table class="selecciom dataTable" id="tbl_comandes_user" width="500px">
					<thead>
						<tr>
							<th><s:text name="user.comandes.table.dia" /></th>
							<th><s:text name="user.comandes.table.plats" /></th>
							<th><s:text name="user.comandes.table.preu" /></th>
							<th><s:text name="user.comandes.table.descripcio" /></th>
							<th width="40px"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>

<!-- Scripts --> 
<c:if test="${fn:contains(header.Host,'7070')}">
	
	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />	
	<script src="<c:url value='/js/jsQuery.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.min.js' />" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/users/jscomandes.js' />"></script>
</c:if>
<c:if test="${fn:contains(header.Host,'9090')}">

	<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		

	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.dataTables.js' />" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/pages/users/jscomandes.js' />"></script>
	
</c:if>
<script language="javascript">
	var initTableParams = new InitTableParams(
			"<s:text  name='datatables.paginate.last'/>",
			"<s:text  name='datatables.paginate.next'/>",
			"<s:text  name='datatables.paginate.previous'/>",
			"<s:text  name='datatables.paginate.first'/>",
			"<s:text  name='datatables.loading'/>",
			"<s:text  name='txt.avis.borrat'/>");
</script>
</body>
</html>