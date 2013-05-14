<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>

.divCargando{
	position:absolute;
	left:0px;
	top:0px;
	background-color:#CCCCCC;
	opacity: 0.5;
	-moz-opacity: 0.5;
	filter: alpha(opacity=50);
	-khtml-opacity: 0.5;
	z-index:15;
	width: 100%;
    height: 100%;
}

.divCargandoImg{
	text-align: center;
	border:#a5acb2 1px solid;
	background-color:#ffffff;
	position:absolute;
	left:500px;
	top:300px;
	width:200px;
	height:40px;
	padding:20px;
	z-index:16;
}
body {
    position: relative;
    margin: 0;
}

</style>

<div  id="divCargando" class="divCargando">
</div>
<div id="divCargandoImg" class="divCargandoImg">	
  <img src="<c:url value='/images/modelos/grafico-cargador-automatico.gif' />" style="vertical-align: middle" alt="	<bean:message key='txt.cargando'/>" /> &nbsp;CARREGANT...
</div>
