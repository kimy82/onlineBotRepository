var actualVot=0;
function starManager(num){
	
	//if((Math.abs(num-actualVot))>1){
	//	return;
	//}
	
	if(num==1){
		//first star
		if(actualVot==num){			
			firstStar(false);
			actualVot=0;
		}else{		
			firstStar(true);
			otherStar(false,2);
			otherStar(false,3);
			otherStar(false,4);
			otherStar(false,5);
			actualVot=1;
		}
	}else if(num==2){
		//second star
		if(actualVot==num){			
			otherStar(false,2);
			actualVot=1;
		}else{
			firstStar(true);
			otherStar(true,num);
			actualVot=2;
		}
		
	}else if(num==3){		
		//third star
		if(actualVot==num){			
			otherStar(false,num);
			actualVot=2;
		}else{
			firstStar(true);
			otherStar(true,2);
			otherStar(true,3);
			actualVot=3;
		}
		
	}else if(num==4){
		//fourth star
		if(actualVot==num){			
			otherStar(false,num);
			actualVot=3;
		}else{
			firstStar(true);
			otherStar(true,2);
			otherStar(true,3);
			otherStar(true,4);
			actualVot=4;
		}
	}else if(num==5){
		//fiveth star
		if(actualVot==num){			
			lastStar(false);
			actualVot=4;
		}else{
			firstStar(true);
			otherStar(true,2);
			otherStar(true,3);
			otherStar(true,4);
			lastStar(true);
			actualVot=5;
		}
	}
}

function firstStar(addRemove){
	
	if(addRemove){
	
		$("#star1").attr("src","/"+context+"/images/star.jpg");	
	}else{		

		$("#star1").attr("src","/"+context+"/images/star0.jpg");
	}	
}
function otherStar(addRemove,num){
		
	if(addRemove){
	
		$("#star"+num).attr("src","/"+context+"/images/star.jpg");	
	}else{
		
		$("#star"+num).attr("src","/"+context+"/images/star0.jpg");	
	}	
}
function lastStar(addRemove){
	
	if(addRemove){

		$("#star5").attr("src","/"+context+"/images/star.jpg");	
	}else{
	
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
					errorOnline.suberror("LA SESSIÓ HA CADUCAT!!!");
				}
			});
}