<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=0.99,maximum-scale=0.99" />
	<link rel="shortcut icon" href="<c:url value='/img/elements/logo_portamu16.jpg' />"> 
	<title><s:text name="txt.welcome.principal" /></title>	
	<link rel="stylesheet" href="<c:url value='/css/comanda.welcome.min.css' />" type="text/css"   media="screen" />
	<link href='https://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>	
	<style>
		.iterate_no_plats{
		  	float: left;
    		height: 200px;
    		margin-bottom: 20px;
    		margin-right: 12px;
    		width: 764px;
		}
	</style>
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-40284930-1', 'portamu.com');
  ga('send', 'pageview');

</script>
</head>
<body id="restaurants">
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">
	<div id="content">
				<c:import url="/pages/includes/menuHeaderNoConfirm.jsp" />
				<c:import url="/pages/includes/divLanguage.jsp" />
		<div id="content_seccion">		
			<div id="seccion">
				<div id="rest">
					${restaurant.nom }
				</div>
				<div class="selector">
					<ul>
					<li><a class="selec" id="plat_select_1" href="#" onclick="filterPlats('primer', this.id)" ><s:text name="txt.plat.primer" /></a></li>
					<li><a href="#" id="plat_select_2" onclick="filterPlats('segon', this.id)" ><s:text name="txt.plat.segon" /></a></li>
					<li><a href="#" id="plat_select_3" onclick="filterPlats('postre', this.id)" ><s:text name="txt.plat.postre" /></a></li>
					</ul>
				</div>
				<hr class="sep2">
				<c:set var="existPlats" value="false"/>
				<s:iterator value="platList" var="plat">
					<c:set var="existPlats" value="true"/>
					<c:if test="${plat.actiu == true }" >
						<div class="iterate_Rest">
						<div class="selector_jq ui-widget-content img_Rest" id="draggable_${plat.id}" >
	  						<img height="152" width="230" src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}">
	  						<div class="big_img">
	  							<a href="#" onclick="showImagePlat(${plat.foto.id})"><img height="14" src="<c:url value='/img/elements/zoom.png' />"></a>
	  						</div>
	  					</div>
	  					<div class="format">
			  				<div class="titol_Rest">
			  					<h1 id="p_desc_${plat.id}" >
			  					<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
			  						${plat.nom}
			  					</c:if>
			  					<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
			  						${plat.nomES}
			  					</c:if>	
			  					</h2>
			  				</div>
			  				<div class="cos_Rest">
			  				<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
	  							<p>${plat.descripcio}</p>
	  						</c:if>
	  						<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
	  							<p>${plat.descripcioES}</p>
	  						</c:if>			  					
			  				</div>			  				
	  					</div>
	  					<div id="afegir">
							<div class="left_price">
								<a href="#" class="entrar" onclick="addProduct('draggable_${plat.id}')" ><s:text name="txt.plat.afegir" /></a>
							</div>
							<div class="right_price">
								 <span class="price">${plat.preu} &euro; </span><br>
							</div>
							<hr class="sep">
						</div>
					</c:if>					
					<c:if test="${plat.actiu == false }" >
						<div class="iterate_Rest"  title="Plat no actiu per avui" >
						<div class="selector_jq ui-widget-content  img_Rest_CLOSE" id="draggable_${plat.id}">
	  						<img height="152" width="230" class="img_CLOSE" src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}">
	  					</div>
	  					<div class="format">
			  				<div class="titol_Rest_CLOSE">
			  					<h1 id="p_desc_${plat.id}" >${plat.nom}</h2>
			  				</div>
			  				<div class="cos_Rest_CLOSE">
			  					<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
	  								<p>${plat.descripcio}</p>
	  							</c:if>
	  							<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
	  								<p>${plat.descripcioES}</p>
	  							</c:if>			  					
			  					<br><br>
			  				</div>
		  				</div>
		  				<div id="afegir">
							<div class="left_price">
								<a href="#" onclick="addProduct('draggable_${plat.id}')" class="entrar_CLOSE"><s:text name="txt.plat.afegir" /></a>
							</div>
							<div class="right_price">
								 <span class="price">${plat.preu} &euro;</span><br>
							</div>
							<hr class="sep">
						</div>
						</c:if>	
						<div class="titsep">
						<s:text name="txt.plat.aptes" />
						</div>
					<c:if test="${plat.celiacs == false }" >
					
					</c:if>	
					<c:if test="${plat.celiacs == true }" >
						<div class="alergia"><img title="<s:text name="txt.plat.celiacs" />"  src="../img/elements/celiac.png"></div>
					</c:if>	
					<c:if test="${plat.vegetarians == false }" >
					
					</c:if>	
					<c:if test="${plat.vegetarians == true }" >
					<div class="alergia"><img title="<s:text name="txt.plat.vegetarians" />" src="../img/elements/vegetaria.png"></div>
					</c:if>	
					<c:if test="${plat.ous == false }" >
					
					</c:if>	
					<c:if test="${plat.ous == true }" >
					<div class="alergia"><img title="<s:text name="txt.plat.ous" />" src="../img/elements/ous.png"></div>
					</c:if>	
					<c:if test="${plat.lactics == false }" >
					
					</c:if>	
					<c:if test="${plat.lactics == true }" >
					<div class="alergia"><img title="<s:text name="txt.plat.lactics" />" src="../img/elements/llet.png"></div>
					</c:if>	
					<c:if test="${plat.fruitsCecs == false }" >
					
					</c:if>	
					<c:if test="${plat.fruitsCecs == true }" >
					<div class="alergia"><img title="<s:text name="txt.plat.fruits" />" src="../img/elements/fruits.png"></div>
					</c:if>	
					<hr class="sep23"></hr>
						<div class="coments">
							<img class="estrelles_rest" src="/${initParam.app}/img/elements/estrelles${plat.votacio.punctuacio}.jpg">	
							<br>
							<span class="coments_titol"><s:text name="txt.coments.valoracions" /></span><br>
							<p>								
									<c:set var="comments" value="${plat.comments}" ></c:set>
									<c:set var="commentInsert" value="${0}" ></c:set>
									<c:forEach items="${comments}" var="comt" >
										<c:if test="${not empty comt && commentInsert < 1}">				
											<c:set var="commentInsert" value="${commentInsert+1}" ></c:set>																				
											 ${fn:substring(comt.comment, 0, 100)}...&nbsp;
										</c:if>																																																
									</c:forEach>								
							<a href="#" class="more" onclick="goToInfoPlat(${plat.id})" ><s:text name="txt.leer.mas" /></a></p>
						</div>
					</div>				
				</s:iterator>
				<c:if test="${existPlats==false}">
					<div class="iterate_no_plats">
						<s:text name="txt.noexist.plats" />
					</div>
				</c:if>
				<c:import url="/pages/includes/paginationPlats.jsp" />
				
					<div id="begudes_titol">
						<s:text name="txt.afegir.vi" />
					</div>
						<hr class="sep4">
						<s:iterator value="begudaList" var="beguda" >
						<div class="iterate_ref" >
							<div class="img_Rest ui-widget-content selectorBeg" id="draggableb_${beguda.id}" >
	  							<img height="152" width="230" src="/${initParam.app}/comanda/ImageAction.action?imageId=${beguda.foto.id}">
	  						</div>
							<div class="format">
					  				<div class="titol_Rest">
					  					<h1 id="p_desc_beg_${beguda.id}" >
					  					<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
	  										${beguda.nom}
	  									</c:if>
	  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
	  										${beguda.nomES}
	  									</c:if>					  					
					  					</h2>
					  				</div>
					  				<div class="cos_Rest">
					  					<p>
						  					<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='ca'}">
		  										${beguda.descripcio}
		  									</c:if>
		  									<c:if test="${sessionScope.WW_TRANS_I18N_LOCALE=='es'}">
		  										${beguda.descripcioES}
		  									</c:if>
					  					</p><br><br>
					  				</div>
	  						</div>
							<div id="afegir">
								<div class="left_price">
									<a href="#" class="entrar" onclick="addProduct('draggableb_${beguda.id}')" ><s:text name="txt.plat.afegir" /></a>
								</div>
								<div class="right_price">
									 <span class="price">${beguda.preu} &euro;</span><br>
								</div>
								<hr class="sep">
							</div>						
							<div class="comentss">
								<img class="estrelles_rest" src="/${initParam.app}/img/elements/estrelles${beguda.votacio.punctuacio}.jpg">	
								<br>
								<span class="coments_titol"><s:text name="txt.coments.valoracions" /></span><br>
								<p>								
										<c:set var="comments" value="${beguda.comments}" ></c:set>
										<c:set var="commentInsert" value="${0}" ></c:set>
										<c:forEach items="${comments}" var="comt" >	
											<c:if test="${not empty comt && commentInsert < 5}">		
												<c:set var="commentInsert" value="${commentInsert+1}" ></c:set>																								
												${fn:substring(comt.comment, 0, 35)}... &nbsp;					
											</c:if>																																										
										</c:forEach>									
								<a href="#" class="more" onclick="goToInfoBeguda(${beguda.id})" ><s:text name="txt.leer.mas" /></a></p>
							</div>															
						</div>
					</s:iterator>
				</div>
				<div id="left">
					<div id="bar_left">
						<img src="<c:url value='/img/elements/bar3.png' />">
					</div>
					<div id="bar_right">
						<div id="droppable"  class="ui-widget-header">
							<div id="carreta">
								<div class="car_int">
									<s:text name="txt.carreta" /><input type="hidden" id="numComanda"></input>
								</div>
								<hr class="sep3">
								<div class="car_pro">
									<s:text name="comanda.num.plats" />:<label id="numplats" ></label><br></br>
									<s:text name="comanda.num.begudes" />:<label id="numbegudes" ></label>
									<div id="disp_plate">										
									</div>																								
									<div id="disp_beguda">										
									</div>
								</div>
								<hr class="sep3">
								<div class="car_sub">
									<s:text name="txt.subtotal" /> <br>
									<label id="preu" ></label> &euro; <br><br>
									<a href="#" class="btn_conf" onclick="goToComandaPas1();"  ><s:text name="txt.comanda.comfirmar" /></a>
								</div>
								<div class="hora">
									<s:text name="txt.propera.hora" />: <br><br>
									<span class="hora_int" id="hora_int" ></span>
								</div>
							</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<div id="infoPlat_dialog" class="filtres filtres-oberts" title="<s:text name='txt.info.title' />">	 		
</div>  
<div id="infoBeguda_dialog" class="filtres filtres-oberts">	 		
</div>  
<div id="imageBig" class="filtres filtres-oberts" style="visibility: hidden;" width="600">
<div id="circle">
<a href="#" onclick="closeImage()" class="colose" >X</a>
</div> 
	<div id="imageBig2" class="filtres filtres-oberts"  width="600">		
		<img id="imgBigId"  width="500" src="/${initParam.app}/comanda/ImageAction.action?imageId=${plat.foto.id}">		
	</div>
</div>   
<c:import url="/pages/includes/endPage.jsp" />
	<script src="<c:url value='/pages/comanda/jqueryWelcome.min.js'/>" type="text/javascript"></script>
	<script type="text/javascript" >		
			var lastIdRestaurant = window.localStorage.getItem("comanda.restaurant");		
			$("#numComanda").text('${requestScope.idComanda}');
			$("#numplats").text('${requestScope.numPlats}');
			$("#numbegudes").text('${requestScope.numBegudes}');			
			$("#preu").text('${requestScope.comanda.preu}');   
			var dataActual = '${requestScope.dataActual}';
			var idRestaurant = '${requestScope.restaurant.id}';
			window.localStorage.setItem("comanda.restaurant",idRestaurant);
	</script>
	<script src="https://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	<script src="<c:url value='/js/jswelcome.comanda.min.js'/>" type="text/javascript"></script>	
	<script type="text/javascript" >
		var initParams = new InitParams("<s:text name='txt.welcome.confirmar' />","<s:text name='txt.welcome.productes' />",
										"<s:text name='txt.welcome.producte' />","<s:text name='txt.comanda.existeix.vol.continuar' />",
										"<s:text name='txt.avis.restaurant.tancat' />");
	</script>
	<c:import url="/pages/includes/confirmOnline.jsp" />
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>