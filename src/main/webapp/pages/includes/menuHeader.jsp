<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="topmenu">
				<ul id="menu">
				<li><a href="<c:url value='/Welcome.action"' /> "><s:text name="menu.inici" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="#"><s:text name="menu.restaurants" /></a>
					<ul>
						<s:iterator value="restaurantList" var="restaurant">
						 	<li><a href="#" onclick="document.getElementById('${restaurant.id}').click()">${restaurant.nom}</a></li>
						</s:iterator>				        
        			</ul></li> 
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/comanda/getVins.action"><s:text name="menu.vins" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/comanda/getRefrescos.action"><s:text name="menu.refresc" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="#"><s:text name="menu.fercomanda" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li class="confirm"><a href="#" onclick="confirmComanda()" ><label id="numProduct" ></label> </a></li>
				</ul>
</div>

