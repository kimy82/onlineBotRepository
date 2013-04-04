///////////////////////////////////
//variables per textos en locale

function InitParams(){		

	
}



var actualVot=0;
function starManager(num){
	
	if((Math.abs(num-actualVot))>1){
		return;
	}
	
	if(num==1){
		//first star
		if(actualVot==num){			
			firstStar(false);
		}else{
			firstStar(true);
		}
	}else if(num==2){
		//second star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
		
	}else if(num==3){		
		//third star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
		
	}else if(num==4){
		//fourth star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
	}else if(num==5){
		//fiveth star
		if(actualVot==num){			
			lastStar(false);
		}else{
			lastStar(true);
		}
	}
}

function firstStar(addRemove){
	
	if(addRemove){
		actualVot=1;
		$("#star1").attr("src","/"+context+"/images/star.jpg");	
	}else{		
		actualVot=0;
		$("#star1").attr("src","/"+context+"/images/star0.jpg");
	}	
}
function otherStar(addRemove,num){
		
	if(addRemove){
		actualVot=num;
		$("#star"+num).attr("src","/"+context+"/images/star.jpg");	
	}else{
		actualVot=num-1;
		$("#star"+num).attr("src","/"+context+"/images/star0.jpg");	
	}	
}
function lastStar(addRemove){
	
	if(addRemove){
		actualVot=5;
		$("#star5").attr("src","/"+context+"/images/star.jpg");	
	}else{
		actualVot=4;
		$("#star5").attr("src","/"+context+"/images/star0.jpg");	
	}	
}

function votaPlatDialog(idPlat){
	
	
	var data = "idPlat=" + idPlat + "&punctuacio=" + actualVot;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxSavePunctuacioForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {													
						$("#saveVotButton_"+idPlat).attr("disabled","disabled");						
					}
				},
				error : function(e) {						
					errorOnline.suberror("Error in AJAX");
				}
			});
}