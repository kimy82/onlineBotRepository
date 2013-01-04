///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtusernameempty,txtusernamewrong, txtpasswordempty,txtpasswordnotequal,txttelempty,txtaddressempty){		

	this.txtusernameempty= txtusernameempty;
	this.txtusernamewrong = txtusernamewrong;
	this.txtpasswordempty= txtpasswordempty;
	this.txtpasswordnotequal=txtpasswordnotequal;
	this.txttelempty = txttelempty;
	this.txtaddressempty = txtaddressempty;

}

//per el formulari
function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

function validate(){
	
	var self = $("#registerForm")[0];
	
	 
	if(self.username.value==''){
	 	
		$("#username").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtusernameempty);			
		return false;
		
	}else{
		$("#username").css('border', 'solid 1px rgb(135,155,179)');	
	}
	
	if(!validateEmail(self.username.value)){
		
		$("#username").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtusernamewrong);			
		return false;
	}
	
	if(self.password.value==''){
	 	
		$("#password").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtpasswordempty);			
		return false;
		
	}else{
		if(self.password.value!=self.confirmPassword.value){
			$("#confirmPassword").css('border', 'solid 1px red');
			$("#password").css('border', 'solid 1px red');
			alertOnline.alertes(initParams.txtpasswordnotequal);				
		}else{
			$("#confirmPassword").css('border', 'solid 1px rgb(135,155,179)');
			$("#password").css('border', 'solid 1px rgb(135,155,179)');
		}
	}
	
	if(self.telefon.value==''){
		
		$("#telefon").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txttelempty);			
		return false;
		
	}else{
		$("#telefon").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	if(self.comandaddress.value==''){
		
		$("#comandaddress").css('border', 'solid 1px red');
		alertOnline.alertes(initParams.txtaddressempty);			
		return false;
		
	}else{
		$("#comandaddress").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	self.submit();
		
}
















