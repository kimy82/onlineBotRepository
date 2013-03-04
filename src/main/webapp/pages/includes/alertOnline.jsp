<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="dialog_alert" class="filtres filtres-oberts" title="<s:text name='txt.alert.title' />">
	 <h1><label id="alertOn" > </label></h1>		
</div>  

<script>

$("#dialog_alert").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 200,
	  width: 400,		
	  open: function(event, ui) { 		
		$('#dialog_alert').css('overflow', 'hidden');		
		var winWidth = $(window).width();
		var winHeight = $(window).height();
		$(".ui-resizable").css("left", ((winWidth / 2) - 150) + "px").css("top", ((winHeight / 2) - 85) + "px");	
	 }
});


	if(alertOnline == undefined || alertOnline =='undefined'){
		var alertOnline={};
	}
	
	alertOnline.alertes= function(txt){
								 $("#alertOn").text(txt);
								 $("#dialog_alert").dialog("open");		
							 };


</script>