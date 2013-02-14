<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<table>
						  <tr class="myform">
							  <th>						                	
		                      <label for="carrer"><s:text name="adreca.carrer" /></label><br>
		                      </th>
		                      <th>
		                      <label for="numcarrer"> <s:text name="adreca.numcarrer" /></label><br>		
		                      </th>
		                      <th>
		                      <label for="codi"><s:text name="adreca.codipostal" /></label><br>		
		                      </th>
		                      <th>
		                      <label for="poble"><s:text name="adreca.poble" /></label><br>		
							  </th>							               
						  </tr>
						   <tr>
							  <th>						                	
		                      <input type="text" id="carrer" class="inputs sepa" />  
		                      </th>
		                      <th>
		                      <input type="text" id="numcarrer" class="numca sepa"  />
		                      </th>
		                      <th>
		                      <input type="text" id="codi" class="posta sepa" />
		                      </th>
		                      <th>
		                      <input type="text" id="poble" disabled="disabled" value="Girona" class="inputs sepa" />                            						                    													    										                
							  </th>
							   <th>
							   <input class="boton" id="checkAdd" value="<s:text name="txt.button.address.ok" />" type="button" />		
							   </th>
							   <th>
							  <label id="addressOK" ></label>	
							   </th>			               
						  </tr>
					  </table>
				</form>
				<div id="map_canvas" style="float: left; height: 250px; width: 1000px; display:none"></div>
		</div>
	</div>