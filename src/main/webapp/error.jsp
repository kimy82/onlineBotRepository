<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style type="text/css">
.errors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:400px;
	margin-bottom:8px;
}
.errors li{ 
	list-style: none; 
}
</style>
<body>
<img src="/images/error/500.png" />
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
