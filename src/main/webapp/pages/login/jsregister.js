///////////////////////////////////
//variables per textos en locale
var initParamsbis=null ;
function InitParamsbis(txtusernameempty,txtusernamewrong, txtpasswordempty,txtpasswordnotequal,txttelempty,txtaddressempty){		

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

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function validate(){
	
	var self = $("#registerForm")[0];
	
	 
	if(self.username.value==''){
	 	
		$("#username").css('border', 'solid 1px red');
		alertOnline.subalertes(initParamsbis.txtusernameempty);			
		return false;
		
	}else{
		$("#username").css('border', 'solid 1px rgb(135,155,179)');	
	}
	
	if(self.email.value==''){
	 	
		$("#email").css('border', 'solid 1px red');
		alertOnline.subalertes(initParamsbis.txtusernameempty);			
		return false;
		
	}else{
		$("#email").css('border', 'solid 1px rgb(135,155,179)');	
	}
	
	if(!validateEmail(self.email.value)){
		
		$("#email").css('border', 'solid 1px red');
		alertOnline.subalertes(initParamsbis.txtusernamewrong);			
		return false;
	}
	
	if(self.password.value==''){
	 	
		$("#password").css('border', 'solid 1px red');
		alertOnline.subalertes(InitParamsbis.txtpasswordempty);			
		return false;
		
	}else{
		if(self.password.value!=self.confirmPassword.value){
			$("#confirmPassword").css('border', 'solid 1px red');
			$("#password").css('border', 'solid 1px red');
			alertOnline.subalertes(initParamsbis.txtpasswordnotequal);				
		}else{
			$("#confirmPassword").css('border', 'solid 1px rgb(135,155,179)');
			$("#password").css('border', 'solid 1px rgb(135,155,179)');
		}
	}
	
	if(self.telefon.value==''){
		
		$("#telefon").css('border', 'solid 1px red');
		alertOnline.subalertes(initParamsbis.txttelempty);			
		return false;
		
	}else{
		$("#telefon").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	if(self.comandaddressbis.value==''){
		
		$("#comandaddressbis").css('border', 'solid 1px red');
		alertOnline.subalertes(initParamsbis.txtaddressempty);			
		return false;
		
	}else{
		$("#comandaddressbis").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	self.submit();
		
}
















