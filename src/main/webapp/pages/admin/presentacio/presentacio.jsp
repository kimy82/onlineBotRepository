<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Gestió</title>

<link rel="stylesheet" href="<c:url value='/css/demo_table.css' />" type="text/css"   media="screen" />
<link rel="stylesheet" href="<c:url value='/css/components.css' />" type="text/css"   media="screen" />  
<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/jquery/jquery.dataTables.js'/>" type="text/javascript"></script>


<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	
</head>
<body>

<div align="center">
    <div id="div_pantalla" style="width:950px;" alig="">
      <c:import url="pestanas.jsp" />
   		 <div id="div_body" align="left">
			<h2>
				<s:text name="txt.mant.presentacio.fotos" />
			</h2>
			<br>	
	  		 <div  style="width:800px; height: 500px;" alig="center">
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="1" ></s:submit>
				</s:form>
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="2" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="3" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="4" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="5" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="6" ></s:submit>
				</s:form>	
				<s:form action="newFotoPresentacio" method="POST" enctype="multipart/form-data" >										
					<s:file name="fileUpload" label="Escull una fotografia" size="40" />					
					<s:submit name="foto" value="7" ></s:submit>
				</s:form>		
			</div>				
		</div>
	</div>
		<h2>
				<s:text name="txt.mant.presentacio.fotos.only.jpg" />
		</h2>
</div>
</body>
</html>

