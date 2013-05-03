<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
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
