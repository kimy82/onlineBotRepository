<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" >
var context= "${initParam.app}";
var confirmTime=parseInt(${initParam.confirmTime});
var transportPreu=parseFloat(${initParam.transport}).toFixed(2);
var txterrorAjax= "<s:text name='txt.error.ajax' />";
var txtavisdosrestaurants ="<s:text name='txt.avis.dos.restaurants' />";
</script>