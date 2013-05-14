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
		  
			var target = $('#target').val();
			var txt = $('#mytextbox').val();
			
			txt = txt.replace(/[\u00A0-\u00FF]/g, function(c) {
				return '#'+c.charCodeAt(0)+';';
			});
			
			txt = txt.replace(/"/g, function(c) {
				return '#34;';
			});

			txt = txt.replace(/&/g, function(c) {
				return '#38;';
			});
			
			
			data ="txt="+txt+"&target="+target;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/admin/ajaxSendLetterAction.action',
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
				  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}
