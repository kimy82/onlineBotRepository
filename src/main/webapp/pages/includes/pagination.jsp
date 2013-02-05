<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="pagination">
	<ul class="pagination">
		<li id="prev"><a href="Welcome.action?actualPage=0" ><img src="<c:url value='/images/icono-paginador-inicio.gif' />" style='vertical-align:middle'></a></li>
		
		<c:if test="${actualPage==0}">
			
		</c:if>
		<c:if test="${actualPage>0}">
			<li><a href="Welcome.action?actualPage=${actualPage-1}">${actualPage-1}</a></li>			
		</c:if>
				
				<li><a href="#">${actualPage}</a></li>			
				
		<c:if test="${actualPage==totalPage}">
			
		</c:if>
		<c:if test="${actualPage<totalPage}">
			<li><a href="Welcome.action?actualPage=${actualPage+1}">${actualPage+1}</a></li>
		</c:if>
		
		<li id="next"><a href="Welcome.action?actualPage=${totalPage}" ><img src="<c:url value='/images/icono-paginador-fin.gif' />" style='vertical-align:middle'></a></li>
	</ul>
</div>