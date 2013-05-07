<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');

</script>

</head>
<style type="text/css">

.errors li{ 
	list-style: none; 
}

.cen {
    margin: 140px auto auto;
    width: 620px;
}
</style>
<body>
<div class="cen">
<img src="<c:url value='/images/error/pagenotfoundd.png' />" alt="error" width="620px;" align="middle" />
</div>
<br>
<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror/>
   </div>
</s:if>
	
<br>
<br>
</body>
</html>
