<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="pagination">
	<ul class="pagination">
		<li id="prev"><a href="#" onclick="pagin(0)" ><img src="<c:url value='/images/icono-paginador-inicio.gif' />" style='vertical-align:middle'></a></li>
		
		<c:if test="${actualPage==0}">
			
		</c:if>
		<c:if test="${(actualPage-3)>0}">
			<li><a href="#" onclick="pagin(${actualPage-4})" >${actualPage-4}</a></li>			
		</c:if>
		<c:if test="${(actualPage-2)>0}">
			<li><a href="#" onclick="pagin(${actualPage-3})" >${actualPage-3}</a></li>			
		</c:if>
		<c:if test="${(actualPage-1)>0}">
			<li><a href="#" onclick="pagin(${actualPage-2})" >${actualPage-2}</a></li>			
		</c:if>
		<c:if test="${actualPage>0}">
			<li><a href="#" onclick="pagin(${actualPage-1})" >${actualPage-1}</a></li>			
		</c:if>
				
				<li><a href="#">${actualPage}</a></li>			
				
		<c:if test="${actualPage==totalPage}">
			
		</c:if>
		<c:if test="${actualPage<totalPage}">
			<li><a href="#" onclick="pagin(${actualPage+1})">${actualPage+1}</a></li>
		</c:if>
		<c:if test="${(actualPage+1)<totalPage}">
			<li><a href="#" onclick="pagin(${actualPage+2})">${actualPage+2}</a></li>
		</c:if>		
		<c:if test="${(actualPage+2)<totalPage}">
			<li><a href="#" onclick="pagin(${actualPage+3})">${actualPage+3}</a></li>
		</c:if>
		<c:if test="${(actualPage+3)<totalPage}">
			<li><a href="#" onclick="pagin(${actualPage+4})">${actualPage+4}</a></li>
		</c:if>
		
		<li id="next"><a href="#" onclick="pagin(${totalPage})" ><img src="<c:url value='/images/icono-paginador-fin.gif' />" style='vertical-align:middle'></a></li>
	</ul>
</div>
<script type="text/javascript" >
function pagin(page){
	var order = window.localStorage.getItem("plats.order");
	if(order=='undefined' || order==null){
		window.location.href="/"+context+"/comanda/Welcome.action?actualPage="+page+"&restaurantId="+idRestaurant+"&data="+window.localStorage.getItem("comanda.data");
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?actualPage="+page+"&restaurantId="+idRestaurant+"&data="+window.localStorage.getItem("comanda.data")+"&order="+order;
	}	
}
</script>