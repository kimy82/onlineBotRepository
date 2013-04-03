<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<c:url value='/css/admin.presentacio.min.css' />" type="text/css"   media="screen" />
	<title><s:text name="txt.presentacio.title.gestio" /></title>	
</head>
<body>
<c:import url="/pages/includes/headerContext.jsp" />
<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="txt.mant.presentacio.fotos" />&nbsp;&nbsp;&nbsp;
			
				(<s:text name="txt.mant.presentacio.fotos.only.jpg" />)
			</h2>
			<br>	
	  		 <div  style="width:800px; height: 500px;" alig="center">
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 1ra foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="1_ca" ></s:submit>
				</s:form>
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 2na foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="2_ca" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 3ra foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="3_ca" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 4rt foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="4_ca" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 5na foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="5_ca" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 6na foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="6_ca" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 7na foto en CATALÀ" size="40" />					
					<s:submit name="foto" value="7_ca" ></s:submit>
				</s:form>		
				
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 1ra foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="1_es" ></s:submit>
				</s:form>
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 2na foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="2_es" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 3ra foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="3_es" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 4rt foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="4_es" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 5na foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="5_es" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 6na foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="6_es" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull la 7na foto en CASTELLÀ" size="40" />					
					<s:submit name="foto" value="7_es" ></s:submit>
				</s:form>		
			</div>				
		</div>
	</div>
		
</div>
	<script src="<c:url value='/js/jspresentacio.min.js'/>" type="text/javascript"></script>
</body>
</html>

