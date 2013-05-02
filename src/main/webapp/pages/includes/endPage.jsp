<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="footer">
	<div id="footer_int">
	<div id="footer_left">
	<s:text name="txt.footer.descripcio" />
	</br></br>
    <s:text name="txt.footer.dades" /> <a class="more" href=mailto:hola@portamu.com>hola@portamu.com</a>
    </br></br></br>
    COMPRA SEGURA AMB</br></br>
    <img  src="<c:url value='/img/elements/bancsabadell.png'/>"  >
	</div>
	<div id="footer_center">
	<img  src="<c:url value='/img/elements/logogris.png'/>"  ></br></br></br>
	
	<a class="more" href="/${initParam.app}/politicaPrivacitat.action" ><s:text name="txt.footer.politica" /></a>
	</br>
	<hr class="s2">
	<a class="more" href="/${initParam.app}/politicaCompra.action" ><s:text name="txt.footer.devolcions" /></a>
	</br>
	<hr class="s2">
	<a class="more" href="/${initParam.app}/comFerComanda.action"><s:text name="menu.fercomanda" /></a>
	</br>
	<hr class="s2">
	<a class="more" href="/${initParam.app}/Preguntes freqüents.action"><s:text name="menu.faq" /></a>
	</br>
	<hr class="s2">
	</div>
		<div id="footer_rigth">
		<a class="twitter-timeline"  href="https://twitter.com/portamu"  data-widget-id="328825192312881153">Tuits de @portamu</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

		</div>
	</div>
</div>
<div id="credits">
	<div id="int_credits">
		
	PORTAMU ROGASO SL. Tots els drets reservats ·2013· Disseny i programació<a class="more "href="http://www.alexamny.com"> Alexmany.com</a>
	</div>
</div>

