<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="toplang">
				<ul>
				<li class="menuleft"><a href="/${initParam.app}/changeLocale.action?request_locale=CA&locale=CA">CAT</a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/changeLocale.action?request_locale=ES&locale=CA">ES</a></li>
				</ul>
</div>