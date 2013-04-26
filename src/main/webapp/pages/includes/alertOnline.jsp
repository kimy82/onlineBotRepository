<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="dialog_alert" class="filtres filtres-oberts" title="<s:text name='txt.alert.title' />">
		
		<div class="al_top">
			<s:text name="txt.alert" />
			<table>
	 	<tr>
	 		<input class="tancarBot" type="button" id="cancel" value="X"  onclick="alertOnline.close();" />
	 	</tr>
	 </table>
		</div>
		<hr class="sep19">
	 
	 <div id="alertOn" > 
	
	 
	 
	 </div>
	 	
</div>  

<script>





$("#dialog_alert").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 250,
	  width: 400,		
	  open: function(event, ui) { 		
		$('#dialog_alert').css('overflow', 'hidden');		
		var winWidth = $(window).width();
		var winHeight = $(window).height();
		$(".ui-resizable").css("left", ((winWidth / 2) - 150) + "px").css("top", ((winHeight / 2) - 85) + "px");	
	 }
}).siblings('div.ui-dialog-titlebar').remove();

$("#dialog_alert").removeClass("ui-dialog-content");
$("#dialog_alert").removeClass("ui-widget-content");
	$("#move").addClass("ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix");
$(".ui-widget-content").css("background-color", "transparent");
	$(".ui-widget-content").css("border", "0px");
$(":ui-dialog").dialog('option', 'position', 'center');





	if(alertOnline == undefined || alertOnline =='undefined'){
		var alertOnline={};
	}
	
	alertOnline.alertes= function(txt){
								 $("#alertOn").text(txt);
								 $("#dialog_alert").dialog("open");		
							 };
	alertOnline.close = function(txt){								
								 $("#dialog_alert").dialog("close");		
							 };


</script>