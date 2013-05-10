<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="txt.welcome.principal" /></title>	
	<link rel="stylesheet" href="<c:url value='/css/tpvUser.css' />" type="text/css"   media="screen" />	
</head>
<body id="tpv">
<c:import url="/pages/includes/headerContext.jsp" />
<div class="contenttpv"><div id="headertpv">
	<div id="int_header">
		<div id="int_left">
			<img src="<c:url value='/img/elements/logo_portamu.png' />">
		</div>
		<div id="int_right">
		<c:import url="/pages/includes/menuHeaderTPV.jsp" />
		</div>
	</div>
</div>
	<div class="containertpv">
		<form  method="POST" id="form_p" action="${requestScope.payment.url}">
			<input name="Ds_Merchant_Order" type="hidden" value="${requestScope.payment.ds_Merchant_Order}">
			<input name="Ds_Merchant_Amount" type="hidden" value="${requestScope.payment.ds_Merchant_Amount}">
			<input name="Ds_Merchant_Currency" type="hidden" value="${requestScope.payment.ds_Merchant_Currency}">
			<input name="Ds_Merchant_ProductDescription" type="hidden" value="${requestScope.payment.ds_Merchant_ProductDescription}">
			<input name="Ds_Merchant_Titular" type="hidden" value="${requestScope.payment.ds_Merchant_Titular}">
			<input name="Ds_Merchant_MerchantCode" type="hidden" value="${requestScope.payment.ds_Merchant_MerchantCode}">
			<input name="Ds_Merchant_UrlOK" type="hidden" value="${requestScope.payment.ds_Merchant_UrlOK}">
			<input name="Ds_Merchant_UrlKO" type="hidden" value="${requestScope.payment.ds_Merchant_UrlKO}">
			<input name="Ds_Merchant_ConsumerLanguage" type="hidden" value="${requestScope.payment.ds_Merchant_ConsumerLanguage}">
			<input name="Ds_Merchant_MerchantName" type="hidden" value="${requestScope.payment.ds_Merchant_MerchantName}">
			<input name="Ds_Merchant_Terminal" type="hidden" value="${requestScope.payment.ds_Merchant_Terminal}">
			<input name="Ds_Merchant_TransactionType" type="hidden" value="${requestScope.payment.ds_Merchant_TransactionType}">
			<input name="Ds_Merchant_MerchantSignature" type="hidden" value="${requestScope.payment.ds_Merchant_MerchantSignature}">			
		</form>		
	</div>
</div>
<script type="text/javascript">
window.localStorage.clear();
document.getElementById("form_p").submit();	
</script>
</body>
</html>