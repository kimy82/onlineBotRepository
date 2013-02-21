<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
      <ul class="pestanyes">               
      <li id="pestanya.mto" ><a href="<c:url value='menu.action' />"><s:text name="mant.restaurants.menu.menu" /></a></li>
       <li id="pestanya.mto" class="seleccionat" ><a href="<c:url value='allComandes.action' />" ><s:text name="pestana.all.comanda" /></a></li> 
      <li id="pestanya.mto"><a href="#" ><s:text name="pestana.comfirm.comanda" /></a></li>                           
      </ul>