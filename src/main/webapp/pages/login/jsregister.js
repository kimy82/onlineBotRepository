///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtusernameempty, txtpasswordempty,txtpasswordnotequal,txttelempty,txtaddressempty){		

	this.txtusernameempty= txtusernameempty;
	this.txtpasswordempty= txtpasswordempty;
	this.txtpasswordnotequal=txtpasswordnotequal;
	this.txttelempty = txttelempty;
	this.txtaddressempty = txtaddressempty;

}

//per el formulari
function validate(){
	
	var self = $("#registerForm")[0];
	
	 
	if(self.username.value==''){
	 	
		$("#username").css('border', 'solid 1px red');
		alert(initParams.txtusernameempty);
		return false;
		
	}else{
		$("#username").css('border', 'solid 1px rgb(135,155,179)');	
	}
	
	if(self.password.value==''){
	 	
		$("#password").css('border', 'solid 1px red');
		alert(initParams.txtpasswordempty);
		return false;
		
	}else{
		if(self.password.value!=self.confirmPassword.value){
			$("#confirmPassword").css('border', 'solid 1px red');
			$("#password").css('border', 'solid 1px red');
			alert(initParams.txtpasswordnotequal);
		}else{
			$("#confirmPassword").css('border', 'solid 1px rgb(135,155,179)');
			$("#password").css('border', 'solid 1px rgb(135,155,179)');
		}
	}
	
	if(self.telefon.value==''){
		
		$("#telefon").css('border', 'solid 1px red');
		alert(initParams.txttelempty);
		return false;
		
	}else{
		$("#telefon").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	if(self.comandaddress.value==''){
		
		$("#comandaddress").css('border', 'solid 1px red');
		alert(initParams.txtaddressempty);
		return false;
		
	}else{
		$("#comandaddress").css('border', 'solid 1px rgb(135,155,179)');
	}
	
	self.submit();
		
}
















