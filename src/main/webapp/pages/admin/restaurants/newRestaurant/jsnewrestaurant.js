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

function submitRestaurant(){
		if($("#nomrestaurant").val() !=''){
			$('#nomrestaurant').css('border', 'solid 1px rgb(135,155,179)');
		}else{
			$('#nomrestaurant').css('border', 'solid 1px red');
			return;
		}
		if($("#codiMaquina").val() !=''){
			$('#codiMaquina').css('border', 'solid 1px rgb(135,155,179)');
		}else{
			$('#codiMaquina').css('border', 'solid 1px red');
			return;
		}
		if($("#descrestaurant").val() !=''){
			$('#descrestaurant').css('border', 'solid 1px rgb(135,155,179)');
		}else{
			$('#descrestaurant').css('border', 'solid 1px red');
			return;
		}
		if($("#descrestaurantES").val() !=''){
			$('#descrestaurantES').css('border', 'solid 1px rgb(135,155,179)');
		}else{
			$('#descrestaurantES').css('border', 'solid 1px red');
			return;
		}
		if($("#addressrestaurant").val() !=''){
			$('#addressrestaurant').css('border', 'solid 1px rgb(135,155,179)');
		}else{
			$('#addressrestaurant').css('border', 'solid 1px red');
			return;
		}
	
	document.getElementById("form_saveNewRestaurant").submit();
}
















