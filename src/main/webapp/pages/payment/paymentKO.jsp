<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/welcome.min.css' />" />	
	<title> <s:text name="txt.welcome.principal" />	</title>	
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');
  </script>
<style>
.pagatxt {
    color: #666666;
    font-family: raleway;
    font-weight: bold;
    margin-bottom: 20px;
    padding-bottom: 20px;
    text-transform: uppercase;
	font-size:16px;
}
.poli {
    background-color: white;
    border: 1px solid #C3C3C3;
    float: left;
    font-family: arial;
    font-size: 13px;
    line-height: 2;
    margin-bottom: 110px;
    margin-top: 40px;
    padding: 30px;
	width:940px;
	text-align: center;
}
.yep2 {
    border-color: #C3C3C3 -moz-use-text-color -moz-use-text-color;
    border-style: solid none none;
    border-width: 1px medium medium;
    box-shadow: 1px 1px 0 0 #FFFFFF;
    float: left;
    margin-bottom: 15px;
    width: 1000px;
	margin-left:-30px;
}
.yep3 {
    border-color: #C3C3C3 -moz-use-text-color -moz-use-text-color;
    border-style: solid none none;
    border-width: 1px medium medium;
    float: left;
    margin-bottom: 15px;
    width: 212px;
}
.grax{
	width:433px;
	margin:auto;
	line-height:1.3;
	text-align:left;
}
.compartir{
width:212px;
font-family: raleway;
    font-weight: bold;
	float:left;
	border: 1px solid #C3C3C3;
	color: #666666;
	padding-top:10px;
}
#concom{
width:463px;
margin:auto;
}
.al{
margin-right:20px;
}
.cotui{
margin-left:10px;
height:69px;
float:left;
}
.sotapoli{
margin-top:-90px;

}
.tornara {
    background-color: white;
    border: 1px solid #BDBBBB;
    border-radius: 3px 3px 3px 3px;
    color: #333333;
    cursor: pointer;
    font-family: 'Raleway',sans-serif;
    font-size: 12px;
    font-weight: 800;
    margin-left: 0;
    margin-top: 69px;
    padding: 10px;
}
.tornara:hover {
    background-color: #F29222;
    border: 1px solid #BDBBBB;
    border-radius: 3px 3px 3px 3px;
    color: #333333;
    cursor: pointer;
    font-family: 'Raleway',sans-serif;
    font-size: 12px;
    font-weight: 800;
    margin-left: 0;
    margin-top: 69px;
    padding: 10px;
}
</style>


</head>
<body id=indexPor>
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
	<div id="content">
			<div class="poli">
			<img src="<c:url value='/img/elements/pagamentno.jpg' />"></br>
<span class="pagatxt" ><s:text name="txt.pagasi.error" /></span></br></br>
<a class="tornara" href="<c:url value='/Welcome.action"' />?final=ok"><s:text name="menu.tornar" /></a>

</div>

</div>
<c:import url="/pages/includes/endPage.jsp" />


<script src="<c:url value='/js/jsdivlogin.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/js/auxiliars/jsauxiliars.js' />" type="text/javascript"></script>
</body>
</html>