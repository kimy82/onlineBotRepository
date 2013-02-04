<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="pagination">
	<ul class="pagination">
		<li id="prev"><a href="Welcome.action?actualPage=0" ><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-inicio.gif' style='vertical-align:middle'></a></li>
		
		<c:if test="${actualPage==0}">
			<li><a href="#" style="background-color: grey;" ><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'></a></li>
		</c:if>
		<c:if test="${actualPage>0}">
			<li><a href="Welcome.action?actualPage=${actualPage-1}"><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-anterior.gif' style='vertical-align:middle'></a></li>			
		</c:if>
				
				<li><a href="#">${actualPage}</a></li>			
				
		<c:if test="${actualPage==totalPage}">
			<li><a href="#" style="background-color: grey;"  ><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'></a></li>
		</c:if>
		<c:if test="${actualPage<totalPage}">
			<li><a href="Welcome.action?actualPage=${actualPage+1}"><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-siguiente.gif' style='vertical-align:middle'></a></li>
		</c:if>
		
		<li id="next"><a href="#"><a href="Welcome.action?actualPage=${totalPage}" ><img src='/ParticipadasIntosWeb/web/img/modelos/icono-paginador-fin.gif' style='vertical-align:middle'></a></a></li>
	</ul>
</div>