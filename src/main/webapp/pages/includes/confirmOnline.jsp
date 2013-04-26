<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="dialog_confirm" class="filtres filtres-oberts" title="<s:text name='txt.confirm.title' />">
		
		<div class="al_top">
			<s:text name="txt.alert" />
		</div>
		<hr class="sep19">
		<div id="tot_conf">
	 <div id="confirmOn" >
	 
	 
	 </div>		
	 <table id="tableCons">
	 	<tr>
	 		<td><input class="boton" type="button" id="cancel" value="cancel"  onclick="confirmOnline.close();" /> </td>
	 		<td><input class="boton" type="button" id="aceptar" value="Aceptar"  /></td>
	 	</tr>
	 </table>
	 </div>
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
		var winWidth = $(window).width();
		var winHeight = $(window).height();
		$(".ui-resizable").css("left", ((winWidth / 2) - 150) + "px").css("top", ((winHeight / 2) - 85) + "px");		    
	 }
}).siblings('div.ui-dialog-titlebar').remove();




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