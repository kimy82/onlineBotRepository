///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtconfirmsending, txtsent){		
	this.txtconfirmsending=txtconfirmsending;
	this.txtsent=txtsent;
}

function send(){
	
	 var where_to= confirm(initTableParams.txtconfirmsending);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {			  				
			
			data ="txt="+$('#mytextbox').val();
			$.ajax({
				  type: "POST",
				  url: '/onlineBot/admin/ajaxSendLetterAction.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtsent);													
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}
