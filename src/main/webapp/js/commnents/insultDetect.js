if(insult == undefined || insult =='undefined'){
	var insult={};
}

insult.array_insults= new Array("puta","puto","cabron","cabrona","gilipollas","desgraciat","desgraciado",
		  "desgraciada","idiota","bobo","boba","soca","tonto","tonta","subnormal",
		  "capullo","capulla","follar","matar","zorra","baboso","babosa","perra",
		  "descapacitado","descapacitada","descapacitat");


insult.detectInsult = function(value,alert){
											    var arr = insult.array_insults.join("|");
											    var reg = new RegExp(arr,"i");
											    if(reg.test(value)){
											    	alertOnline.subalertes(alert);
											    	return true;
												}else{	
													return false;
											    }
											}



      

   