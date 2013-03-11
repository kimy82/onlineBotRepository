<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" >
var context= "${initParam.app}";
var confirmTime=parseInt(${initParam.confirmTime});
var txterrorAjax= "<s:text name='txt.error.ajax' />";
</script>