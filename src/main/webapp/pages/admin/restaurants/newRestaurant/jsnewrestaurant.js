function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function saveHoraObertura(id){
	
	var hores =$("#horesRestaurant").val();
	$("#horesRestaurant").val(hores+"|"+id);
	
	if($("#horesRestaurant").hasClass("notcheck")){
		$("#horesRestaurant").removeClass("notcheck");
		$("#horesRestaurant").removeClass("check");
	}
	if($("#horesRestaurant").hasClass("check")){
		$("#horesRestaurant").removeClass("check");
		$("#horesRestaurant").removeClass("notcheck");
	}
	
}

$(document).ready(function() {


} );















