<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


	<div class="page">
		<div id="main">
		   		<form id="MyForm" name="MyForm" action="form.html">
					<table>
						  <tr>
							  <td>						                	
		                      <label for="carrer"><s:text name="adreca.carrer" /></label><br>
		                      </td>
		                  </tr>
						   <tr>
		                      <td>						                	
		                      <input type="text" id="carrer" class="inputs sepa" />  
		                      </td>
		                   </tr>
						   <tr> 
						   	  <td>
		                      <label for="numcarrer"> <s:text name="adreca.numcarrer" /></label><br>		
		                      </td>
		                   </tr>
						   <tr>
		                      <td>
		                      <input type="text" id="numcarrer" class="numca sepa"  />
		                      </td>
		                   </tr>
						   <tr> 
						   	  <td>
						   	  <label for="codi"><s:text name="adreca.codipostal" /></label><br>		
		                      </td>
		                   </tr>
						   <tr>
		                      <td>
		                      <input type="text" id="codi" class="posta sepa" />
		                      </td>
		                   </tr>
		                   <tr>
							  <td>
		                      <label for="poble"><s:text name="adreca.poble" /></label><br>		
							  </td>							               
						  </tr>
						   <tr>
		                      <td>
			                      <select id="poble" class="inputs sepa" >
										<option value="Girona" >Girona</option>
										<option value="Salt" >Salt</option>
								  </select>           						                    													    										                
							  </td>
						   </tr>
						  <tr>
							  <td>
							  <input class="boton" id="checkAdd" value="<s:text name="txt.button.address.ok" />" type="button" />		
							  </td>
		                   </tr>
						   <tr>
		                  	  <td>
							  <label id="addressOK" ></label>	
							  </td>			               
						  </tr>
					  </table>
				</form>
				<div id="map_canvas" style="float: left; height: 250px; width: 1000px; display:none"></div>
		</div>
	</div>