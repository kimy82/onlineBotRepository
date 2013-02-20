<!-- nou estaurant --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
      <ul class="pestanyes">               
	      <li id="pestanya.mto"><a href="#" >USUARIS</a>
		      	<ul>
			      	<li id="pestanya.mto"><a href="<c:url value='usuaris.action' />" >Manteniment usuaris</a></li>
			      	<li id="pestanya.mto"><a href="<c:url value='letterUsuaris.action' />" >Newsletter usuaris</a></li>     
			      	<li id="pestanya.mto"><a href="<c:url value='newsletter.action' />" ><s:text name="txt.pestanya.newsletter" /></a></li>
		      	</ul></li>  
		  <li id="pestanya.mto" class="seleccionat"><a href="#" >ALTES</a>
		      	<ul>
			      	<li id="pestanya.mto"><a href="<c:url value='plats.action' />" >Nou plat</a></li>    
			      	<li id="pestanya.mto"><a href="<c:url value='newRestaurant.action' />">Nou restaurant</a></li>
			      	<li id="pestanya.mto"><a href="<c:url value='begudes.action' />" >Nova beguda</a></li>
		      	</ul></li>
		  <li id="pestanya.mto"><a href="#" >CONSULTA I MODIFICACIÓ</a>
		  		<ul>
		  			<li id="pestanya.mto"><a href="<c:url value='restaurants.action' />" >Configuració Restaurants i plats</a></li>
			      	<li id="pestanya.mto"><a href="<c:url value='begudes.action' />" >Begudes</a></li>
		      	</ul></li>
		  <li id="pestanya.mto"><a href="<c:url value='config.action' />" >Config Hores obertures</a></li>
		  <li id="pestanya.mto"><a href="<c:url value='configMoters.action' />" >Config Moters</a></li>
		  <li id="pestanya.mto"><a href="<c:url value='promocions.action' />" >Promos</a></li>
		  <li id="pestanya.mto"><a href="<c:url value='presentacio.action' />" >Slider</a></li>
		  <li id="pestanya.mto"><a href="<c:url value='comments.action' />" >Comments</a></li>  	      	   	  
      </ul>