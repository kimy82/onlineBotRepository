<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
      <ul class="pestanyes">  
      <li id="pestanya.mto" ><a href="<c:url value='menu.action' />"><s:text name="mant.restaurants.menu.menu" /></a></li>
      <li id="pestanya.mto" class="seleccionat"   ><a href="#"><s:text name="txt.pestana.config" /></a></li>
      <li id="pestanya.mto"><a href="<c:url value='configMoters.action' />"><s:text name="txt.pestana.moters" /></a></li>   
      <li id="pestanya.mto"><a href="<c:url value='promocions.action' />"><s:text name="txt.pestana.promo" /></a></li>
      <li id="pestanya.mto"><a href="<c:url value='begudes.action' />"><s:text name="txt.pestana.begudes" /></a></li>                          
      </ul>
      