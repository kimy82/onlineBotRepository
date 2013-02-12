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
	<title><s:text name="txt.welcome.principal" /></title>	
</head>
<body id="carrito">
<c:import url="/pages/includes/headerContext.jsp" />
<c:import url="/pages/includes/divLogin.jsp" />
<div id="container">

	<div id="content">
			<!-- menu -->
				<c:import url="/pages/includes/menuHeader.jsp" />
			<!-- END menu -->
			<!-- Language -->
				<c:import url="/pages/includes/divLanguage.jsp" />
			<!-- END language -->
			<div class="titols_comanda"> <s:text name="txt.add.some.drink" /></div>
			<div id="recordatori">
				<div id="slider" style=" height:219px; width:1000px;"  >
				    <ul>
				    	<s:iterator value="refrescList" var="refresc">
					    	<li class="draggable" id="${refresc.idSub}" title="${refresc.tipus}">
					    			<div id="iterate_Rec" >
					    				<div id="img_Rest" >
					    					<img id="imageRefresc_${refresc.id}" width="200px"  src="/${initParam.app}/comanda/ImageAction.action?imageId=${refresc.id}" title="${refresc.descripcio} -> Double Click to Add" />
					    				</div>
					    				<div class="titol_Rest">
											<h1>${refresc.descripcio}</h1>
										</div>
										<div class="left_price">
											<a class="entrar draggable" href="#"><s:text name="txt.plat.afegir" /></a>
										</div>
										<div class="right_price">
											<span class="price">${refresc.preu} &euro; </span>
											<br>
										</div>									
					    			</div>					    			
					    	</li>				    		
				        </s:iterator>	
				    </ul>
				</div>
			</div>
			<div id="preus" >
					<table id="order">
						<thead>
							<tr>
							<th class="titolars pro" scope="col"><s:text name="txt.table.infocomanda.th1" /></th>
							<th class="titolars des" scope="col"><s:text name="txt.table.infocomanda.th2" /></th>
							<th class="titolars pre" scope="col"><s:text name="txt.table.infocomanda.th3" /></th>
							<th class="titolars can" scope="col"><s:text name="txt.table.infocomanda.th4" /></th>
							<th class="titolars tot" scope="col"><s:text name="txt.table.infocomanda.th5" /></th>
							<th class="titolars elm" scope="col"></th>
							</tr>
						</thead>
						<tbody class="tb">
							<s:iterator value="platComandaList" var="platComanda">
								<tr class="selector_pl" id="plat_${platComanda.plat.id}" >
									<td class="img_order">
										<img width="114px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${platComanda.plat.foto.id}">
									</td>
									<td class="descri">
									<span class="tit">${platComanda.plat.nom}</span>
									<br>
										${platComanda.plat.descripcio}
									</td>
									<td class="preusun"><label id="platpreu_${platComanda.plat.id}" >${platComanda.plat.preu}</label>&euro; </td>
									<td class="canti">										
										<input class="mores" type="submit" onclick="saveNewPLatAmount(${platComanda.plat.id}, -1)" value="-">
										<label id="labelnum_${platComanda.plat.id}">${platComanda.numPlats}</label>
										<input class="mores" type="submit" onclick="saveNewPLatAmount(${platComanda.plat.id}, 1)" value="+">
									</td>
									<td class="total"><label id="labelpreutotal_${platComanda.plat.id}">${platComanda.plat.preu*platComanda.numPlats}</label> &euro; </td>
									<td class="elimi">
										<input class="elimin" type="submit" onclick="eliminaPlat(${platComanda.plat.id})"  value="ELIMINAR">
									</td>								
								</tr>
							</s:iterator>
							<s:iterator value="begudaComandaList" var="begudaComanda">
								<c:if test="${begudaComanda.numBegudes>0}" >
									<tr class="selector_bg" id="beguda_${begudaComanda.beguda.id}" >
									<td class="img_order">
										<img width="114px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${begudaComanda.beguda.foto.id}">
									</td>
									<td class="descri">
									<span class="tit">${begudaComanda.beguda.nom}</span>
									<br>
										${begudaComanda.beguda.descripcio}
									</td>
									<td class="preusun"><label id="begudapreu_${begudaComanda.beguda.id}" >${begudaComanda.beguda.preu}</label>&euro; </td>
									<td class="canti">										
										<input class="mores" type="submit" onclick="saveBegudaToComanda(${begudaComanda.beguda.id},false,-1);" value="-">
										<label id="labelnum_b_${begudaComanda.beguda.id}">${begudaComanda.numBegudes}</label>
										<input class="mores" type="submit" onclick="saveBegudaToComanda(${begudaComanda.beguda.id},false,1)" value="+">
									</td>
									<td class="total"><label id="labelpreutotal_b_${begudaComanda.beguda.id}">${begudaComanda.beguda.preu*begudaComanda.numBegudes}</label> &euro; </td>
									<td class="elimi">
										<input class="elimin" type="submit" onclick="eliminaBeguda(${begudaComanda.beguda.id})"  value="ELIMINAR">
									</td>								
									</tr>								
								</c:if>
								<c:if test="${begudaComanda.numBegudesPromo>0}" >
									<tr class="selector_bg" id="beguda_p_${begudaComanda.beguda.id}" >
									<td class="img_order">
										<img width="114px" src="/${initParam.app}/comanda/ImageAction.action?imageId=${begudaComanda.beguda.foto.id}">
									</td>
									<td class="descri">
									<span class="tit">${begudaComanda.beguda.nom}</span>
									<br>
										${begudaComanda.beguda.descripcio}
									</td>
									<td class="preusun"><label id="begudapreu_p_${begudaComanda.beguda.id}" >${begudaComanda.beguda.preu}</label>&euro; </td>
									<td class="canti">										
										<label id='labelnum_b_p_${begudaComanda.beguda.id}'>${begudaComanda.numBegudesPromo}</label>
									</td>
									<td class="total"><label id="labelpreutotal_b_p_${begudaComanda.beguda.id}">0</label> &euro; </td>
									<td class="elimi">
										<input class='elimin' type='submit' onclick='deletePromoApplied();'  value="<s:text name='txt.infocomanda.treure.promo' />">
									</td>								
									</tr>
								</c:if>
							</s:iterator>
							<tr>
								<td class="img_order" colspan="4"><s:text name="txt.table.infocomanda.transport" /></td>
								<td class="total"><label id="transport_lb"></label>&euro; </td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="img_order" colspan="4"><s:text name="txt.table.infocomanda.subtotal" /></td>
								<td class="total"><label id="preu"></label>&euro;  </td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="img_order" colspan="4"><s:text name="txt.table.infocomanda.promos" /></td>
								<td class="total"><label id="promoImp"></label></td>
								<td class="total"></td>
							</tr>
							<tr>
								<td class="img_order" colspan="4"><s:text name="txt.table.infocomanda.total" /></td>
								<td class="total" ><label id="labelpreutotalPromo"></label> &euro;</td>
								<td class="total"></td>
							</tr>						
						</tbody>
					</table>
			</div>
		</div>
	<div id="formpreus" >	
	<c:import url="/pages/includes/address.jsp" />	
	<s:form action="checkComanda" method="POST" enctype="multipart/form-data" >
					<td><s:text name="comanda.dia" ></s:text></td>
					<td><s:textfield key="comanda.dia"  id="dia" maxlength="10" size="12" onfocus="blur()" theme="simple" onchange="reloadHores()" ></s:textfield>
									<img  src="<c:url value='/images/calendar/calendar_full.png'/>"  id="llencadorData1" ></td>
					
														
					<s:checkbox key="comanda.aDomicili" onclick="addDomicili()"  id="adomicili"   ></s:checkbox>
					<s:checkbox key="comanda.targeta" id="targeta"   ></s:checkbox>
					<s:hidden  key="comanda.hora" id="comandahora" ></s:hidden>					
					<s:hidden key="comanda.id" id="idcomanda" ></s:hidden>	                   
					<s:hidden key="comanda.address" id="comandaddress"></s:hidden>			
																							
					<tr><td><input type="button"  onclick="checkComandaJS();" value="Pay Comanda" /></td><td>  <div id="chargeBar"></div></td></tr>
					
					
	</s:form>	
	
	<table>
					<tr><td><s:text name="comanda.hora" ></s:text></td></tr>										
					<tr>
						<c:if test="${requestScope.horesDTO._0800 ne 'true'}">
							<td><input type="button"  id="0800" value="0800" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._0800 eq 'true'}">
							<td><input type="button"  id="0800" value="0800" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._0830 ne 'true'}">
							<td><input type="button"  id="0830" value="0830" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._0830 eq 'true'}">
							<td><input type="button"  id="0830" value="0830" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._0900 ne 'true'}">
							<td><input type="button"  id="0900" value="0900" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._0900 eq 'true'}">
							<td><input type="button"  id="0900" value="0900" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._0930 ne 'true'}">
							<td><input type="button"  id="0930" value="0930" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._0930 eq 'true'}">
							<td><input type="button"  id="0930" value="0930" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1000 ne 'true'}">
							<td><input type="button"  id="1000" value="1000" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1000 eq 'true'}">
							<td><input type="button"  id="1000" value="1000" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1030 ne 'true'}">
							<td><input type="button"  id="1030" value="1030" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1030 eq 'true'}">
							<td><input type="button"  id="1030" value="1030" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1100 ne 'true'}">
							<td><input type="button"  id="1100" value="1100" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1100 eq 'true'}">
							<td><input type="button"  id="1100" value="1100" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1130 ne 'true'}">
							<td><input type="button"  id="1130" value="1130" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1130 eq 'true'}">
							<td><input type="button"  id="1130" value="1130" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1200 ne 'true'}">
							<td><input type="button"  id="1200" value="1200" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1200 eq 'true'}">
							<td><input type="button"  id="1200" value="1200" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1230 ne 'true'}">
							<td><input type="button"  id="1230" value="1230" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1230 eq 'true'}">
							<td><input type="button"  id="1230" value="1230" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1300 ne 'true'}">
							<td><input type="button"  id="1300" value="1300" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1300 eq 'true'}">
							<td><input type="button"  id="1300" value="1300" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1330 ne 'true'}">
							<td><input type="button"  id="1330" value="1330" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1330 eq 'true'}">
							<td><input type="button"  id="1330" value="1330" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1400 ne 'true'}">
							<td><input type="button"  id="1400" value="1400" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1400 eq 'true'}">
							<td><input type="button"  id="1400" value="1400" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1430 ne 'true'}">
							<td><input type="button"  id="1430" value="1430" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1430 eq 'true'}">
							<td><input type="button"  id="1430" value="1430" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1500 ne 'true'}">
							<td><input type="button"  id="1500" value="1500" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1500 eq 'true'}">
							<td><input type="button"  id="1500" value="1500" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1530 ne 'true'}">
							<td><input type="button"  id="1530" value="1530" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1530 eq 'true'}">
							<td><input type="button"  id="1530" value="1530" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1600 ne 'true'}">
							<td><input type="button"  id="1600" value="1600" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1600 eq 'true'}">
							<td><input type="button"  id="1600" value="1600" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1630 ne 'true'}">
							<td><input type="button"  id="1630" value="1630" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1630 eq 'true'}">
							<td><input type="button"  id="1630" value="1630" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1700 ne 'true'}">
							<td><input type="button"  id="1700" value="1700" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1700 eq 'true'}">
							<td><input type="button"  id="1700" value="1700" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1730 ne 'true'}">
							<td><input type="button"  id="1730" value="1730" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1730 eq 'true'}">
							<td><input type="button"  id="1730" value="1730" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.requestScope.horesDTO._1800 ne 'true'}">
							<td><input type="button"  id="1800" value="1800" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1800 eq 'true'}">
							<td><input type="button"  id="1800" value="1800" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1830 ne 'true'}">
							<td><input type="button"  id="1830" value="1830" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1830 eq 'true'}">
							<td><input type="button"  id="1830" value="1830" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1900 ne 'true'}">
							<td><input type="button"  id="1900" value="1900" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1900 eq 'true'}">
							<td><input type="button"  id="1900" value="1900" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._1930 ne 'true'}">
							<td><input type="button"  id="1930" value="1930" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._1930 eq 'true'}">
							<td><input type="button"  id="1930" value="1930" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2000 ne 'true'}">
							<td><input type="button"  id="2000" value="2000" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2000 eq 'true'}">
							<td><input type="button"  id="2000" value="2000" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2030 ne 'true'}">
							<td><input type="button"  id="2030" value="2030" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2030 eq 'true'}">
							<td><input type="button"  id="2030" value="2030" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
				
						<c:if test="${requestScope.horesDTO._2100 ne 'true'}">
							<td><input type="button"  id="2100" value="2100" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2100 eq 'true'}">
							<td><input type="button"  id="2100" value="2100" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2130 ne 'true'}">
							<td><input type="button"  id="2130" value="2130" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2130 eq 'true'}">
							<td><input type="button"  id="2130" value="2130" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2200 ne 'true'}">
							<td><input type="button"  id="2200" value="2200" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2200 eq 'true'}">
							<td><input type="button"  id="2200" value="2200" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2230 ne 'true'}">
							<td><input type="button"  id="2230" value="2230" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2230 eq 'true'}">
							<td><input type="button"  id="2230" value="2230" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2300 ne 'true'}">
							<td><input type="button"  id="2300" value="2300" class="notcheck" onclick="checKHour(this.id)"/></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2300 eq 'true'}">
							<td><input type="button"  id="2300" value="2300" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2330 ne 'true'}">
							<td><input type="button"  id="2330" value="2330" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2330 eq 'true'}">
							<td><input type="button"  id="2330" value="2330" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2400 ne 'true'}">
							<td><input type="button"  id="2400" value="2400" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2400 eq 'true'}">
							<td><input type="button"  id="2400" value="2400" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>						
					</tr>	
	</table>
	<div id="checkPromocionsDisponibles" ><input type="button"  onclick="openDialogPromos();" value="promos Comanda" /></div>
	
	<div id="deletePromoApplied" ><input type="button"  onclick="deletePromoApplied();" value="delete promo applied" /></div>
	<br>

	<c:if test="${nameAuth eq 'anonymousUser' }">
		<h1><s:text name="txt.logate" /></h1>
		<form name='f' id="f" action="/${initParam.app}/j_spring_security_check" method="post">
			<table>
				<tr>
					<td><s:text name="txt.user.of.login" />:</td>
					<td><input type='text' name='j_username' value=''>
					</td>
					<td><s:text name="txt.pass.of.login" />:</td>
					<td><input type='password' name='j_password' />
					</td>
					<td colspan='2'><input name="submit" type="button"
						value="submit" onclick="submitLog()" />
					</td>
				</tr>			
			</table>
	 		<label id="loged" ></label>
		</form>
	</c:if>
	</div>
<div id="dialog_promo" class="filtres filtres-oberts" title="Promo">
 
	 <h1><s:text name="txt.promo.escull" /></h1>
			<ul id="prm" >
			
				
			</ul>
</div>  	
</div>
<!-- END container -->
<div id="footer">
	<div id="footer_int">
		<div id="footer_rigth">
		
		</div>
	</div>
</div>
<div id="credits">
	<div id="int_credits">
		<s:text name="txt.footer.reserved" />
	</div>
</div>
	
	<!-- CSS portamu --> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/global.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/portamu/reset.css' />" />
	<!-- FONTS -->
	<link href='http://fonts.googleapis.com/css?family=Raleway:800,400' rel='stylesheet' type='text/css'>
	<!-- CSS portamu -->
	<style>
		.notcheck{
			background-color: grey;
		}
		.check{
			background-color: yellow;
		}
		
		.checked{
			background-color: green;
		}
	</style>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/calendar-blau.css' />" title="win2k-cold-1" />
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/sudoSlider.css' />"  />	
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/progress.css' />" />
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/online.css' />" />

	<script src="<c:url value='/js/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.core.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.widget.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.mouse.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.dialog.js' />" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.position.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.draggable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.droppable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.ui.resizable.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.effects.core.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery.bgiframe-2.1.1.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/jquery/jquery-ui.js' />" type="text/javascript"></script>
	<!-- Calendari -->  
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-cat.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-es.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-idioma.js'/>"></script>		
	<script type="text/javascript" src="<c:url value='/js/calendari/calendar-setup.js'/>"></script>
	<!-- Per validar l'adreca -->
	<script src="<c:url value='/js/address/autocompleteStreet.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/address/autocompleteCodi.js'/>" type="text/javascript"></script>
	
	<script src="<c:url value='/js/address/addressValidationForm.js'/>" type="text/javascript"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	
	<!-- Sliders de begudes -->
	<script src="<c:url value='/js/sudoSlider/jquery.sudoSlider.js'/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value='/js/progressbar/progress.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/pages/comanda/jsinfoComanda.js'/>"></script>	
<script type="text/javascript" >
			  
var initParams = new InitParams("<s:text name='txt.beguda.no.tipus.promo' />","<s:text name='txt.beguda.no.more.promo' />","<s:text name='txt.add.beguda.to.box' />", 
								"<s:text name='txt.promo.descompte.aplicat' />","<s:text name='txt.promo.deleted' />","<s:text name='comanda.falta.hora' />",
								"<s:text name='comanda.check.address' />","<s:text name='comanda.user.check.ok' />","<s:text name='comanda.user.check.ko' />",
								"<s:text name='txt.welcome.confirmar' />","<s:text name='txt.welcome.productes' />","<s:text name='txt.welcome.producte' />");
								

$("#idcomanda").val('${requestScope.idComanda}');
$("#numComanda").text('${requestScope.idComanda}');
$("#dia").val('${requestScope.horesDTO.data}');

//window.localStorage.setItem("comanda.preu",'${requestScope.comanda.preu}');

initNumPlats();
initNumBegudes();

function submitLog(){
	
	$.ajax({
	    url: "<c:url value='/onlineBot/j_spring_security_check' />",
	    type: "POST",
	    data: $("#f").serialize(),
	    dataType: 'json',
	    beforeSend: function (xhr) {
	        xhr.setRequestHeader("X-Ajax-call", "true");
	    },
	    success: function(json) {
	        if (json.result == "ok") {
	        	$("#loged").text(initParams.checkok);	             
	        } else if (json.result == "error") {	        	
	        	$("#loged").text(initParams.checkko);
	        }
	    }
	});	
}


var addressToLoad ='${requestScope.comanda.address}';
if(addressToLoad==''){
	addressToLoad =  '${requestScope.user.address}';	
}

initAddress();

</script>

<c:if test="${not empty requestScope.recoveredComanda}">
<script type="text/javascript" >
window.localStorage.clear();
window.localStorage.setItem("comanda.data","${requestScope.horesDTO.data}");
window.localStorage.setItem("comanda","${requestScope.idComanda}");

window.localStorage.setItem("comanda.preu","${requestScope.comanda.preu}");
window.localStorage.setItem("comanda.numplats","${requestScope.numPlats}");
$("#numplats").text('${requestScope.numPlats}');
</script>
</c:if>
	<c:import url="/pages/includes/alertOnline.jsp" />
	<c:import url="/pages/includes/errorAjax.jsp" />
</body>
</html>
