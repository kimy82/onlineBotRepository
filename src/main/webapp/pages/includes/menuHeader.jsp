<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="topmenu">
				<ul id="menu">
				<li><a href="<c:url value='/Welcome.action"' /> "><s:text name="menu.inici" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="#"><s:text name="menu.restaurants" /></a>
					<ul>
						<s:iterator value="restaurantList" var="rest">
							<c:if test="${not empty rest.configRestaurants}">
								<c:set var="configs" value="${rest.configRestaurants}" ></c:set>
								<c:set var="doneLoopList" value="false"/>
									<c:forEach items="${configs}" var="config" >
										<c:if test="${config.data eq dataAvui }">
											<c:if test="${config.obert==true && doneLoopList==false }">
												<li id="list_rest_${rest.id}" ><a href="#" onclick="goToRestaurantMenu(${rest.id})">${rest.nom}</a></li>
											</c:if>
										 	<c:if test="${config.obert==false && doneLoopList==false }">
												<li class="tancat" id="list_rest_${rest.id}" ><a href="#" onclick="goToRestaurantMenu(${rest.id})">${rest.nom}</a></li>
											</c:if>
											<c:set var="doneLoopList" value="true"/>
										</c:if>
									</c:forEach>
							</c:if>
							<c:if test="${empty rest.configRestaurants}">								
									<li id="list_rest_${rest.id}" ><a href="#" onclick="goToRestaurantMenu(${rest.id})">${rest.nom}</a></li>								
							</c:if>
						</s:iterator>				        
        			</ul></li> 
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/comanda/getVins.action"><s:text name="menu.vins" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/comanda/getRefrescos.action"><s:text name="menu.refresc" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li><a href="/${initParam.app}/comFerComanda.action"><s:text name="menu.fercomanda" /></a></li>
				<li><img src="<c:url value='/img/elements/bar2.png' />"></li>
				<li class="confirm"><a href="#" onclick="confirmComanda()" ><label id="numProduct" ></label> </a></li>
				</ul>
</div>

