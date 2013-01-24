function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function saveHoraObertura(id){
	
	var hores =$("#horesRestaurant").val();	
	
	if($("#"+id).hasClass("notcheck")){
		$("#horesRestaurant").val(hores+"|"+id);
		$("#"+id).removeClass("notcheck");
		$("#"+id).addClass("check");
		return;
	}
	if($("#"+id).hasClass("check")){
		
		var array = hores.split("|");
		var newhores = "";
		$.each(array, function (i,item){
			if(item!=id){
				newhores=item+"|";
			}
		});
		$("#horesRestaurant").val(newhores);
		$("#"+id).removeClass("check");
		$("#"+id).addClass("notcheck");
	}	
}

$(document).ready(function() {


} );















