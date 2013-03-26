<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<table class="hours">
					<tr class="horh"><td class="tab" colspan="4"><s:text name="comanda.hores" ></s:text></td></tr>										
					<tr>
						<!-- <c:if test="${requestScope.horesDTO._0800 ne 'true'}">
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
						-->
						<c:if test="${requestScope.horesDTO._2000 ne 'true'}">
							<td><input type="button"  id="2000" value="20:00 - 20:30" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2000 eq 'true'}">
							<td><input type="button"  id="2000" value="20:00 - 20:30" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2030 ne 'true'}">
							<td><input type="button"  id="2030" value="20:30 - 21:00" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2030 eq 'true'}">
							<td><input type="button"  id="2030" value="20:30 - 21:00" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
				
						<c:if test="${requestScope.horesDTO._2100 ne 'true'}">
							<td><input type="button"  id="2100" value="21:00 - 21:30" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2100 eq 'true'}">
							<td><input type="button"  id="2100" value="21:00 - 21:30" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						</tr>
						<tr>
						<c:if test="${requestScope.horesDTO._2130 ne 'true'}">
							<td><input type="button"  id="2130" value="21:30 - 22:00" class="notcheck"  onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2130 eq 'true'}">
							<td><input type="button"  id="2130" value="21:30 - 22:00" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2200 ne 'true'}">
							<td><input type="button"  id="2200" value="22:00 - 22:30" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2200 eq 'true'}">
							<td><input type="button"  id="2200" value="22:00 - 22:30" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2230 ne 'true'}">
							<td><input type="button"  id="2230" value="22:30 - 23:00" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2230 eq 'true'}">
							<td><input type="button"  id="2230" value="22:30 - 23:00" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						</tr>
						<tr>
						<c:if test="${requestScope.horesDTO._2300 ne 'true'}">
							<td><input type="button"  id="2300" value="23:00 - 23:30" class="notcheck" onclick="checKHour(this.id)"/></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2300 eq 'true'}">
							<td><input type="button"  id="2300" value="23:00 - 23:30" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2330 ne 'true'}">
							<td><input type="button"  id="2330" value="23:30 - 24:00" class="notcheck" onclick="checKHour(this.id)" /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2330 eq 'true'}">
							<td><input type="button"  id="2330" value="23:30 -24:00" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>
						
						<c:if test="${requestScope.horesDTO._2400 ne 'true'}">
							<td><input type="button"  id="2400" value="24:00 - 24:30" class="notcheck" onclick="checKHour(this.id)"  /></td>
						</c:if>
						<c:if test="${requestScope.horesDTO._2400 eq 'true'}">
							<td><input type="button"  id="2400" value="24:00 - 24:30" class="check" onclick="checKHour(this.id)" /></td>
						</c:if>						
					</tr>	
	</table>