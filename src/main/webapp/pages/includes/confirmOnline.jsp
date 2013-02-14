<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="dialog_confirm" class="filtres filtres-oberts" title="<s:text name='txt.confirm.title' />">
	 <h2><label id="confirmOn" > </label></h2>		
	 <table>
	 	<tr>
	 		<td><input class="boton" type="button" id="cancel" value="cancel"  onclick="confirmOnline.close();" /> </td>
	 		<td><input class="boton" type="button" id="aceptar" value="Aceptar"  /></td>
	 	</tr>
	 </table>
</div>  

<script>

$("#dialog_confirm").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 170,
	  width: 300,	
	  open: function(event, ui) { 		
		$('#dialog_confirm').css('overflow', 'hidden');		
	 }
});

 
	if(confirmOnline == undefined || confirmOnline =='undefined'){
		var confirmOnline={};
	}
	
	confirmOnline.confirm= function(txt, func){
								 $("#confirmOn").text(txt);
								 document.getElementById("aceptar").onclick=func;
								 $("#dialog_confirm").dialog("open");		
							 };
	confirmOnline.close=function(){
								 $("#dialog_confirm").dialog("close");	
							 }
	confirmOnline.closeSetFunc=function(func){
		 document.getElementById("cancel").onclick=func;		
	 }

</script>