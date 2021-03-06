///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborrabeguda,txterrordouble,txterrornumber){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;	
		this.txtconfirmborrabeguda= txtconfirmborrabeguda;
		this.txterrordouble=txterrordouble;
		this.txterrornumber=txterrornumber;
}


function opendivNewBeguda(){
	resetForm();
	$("#infobegudanew").show('slow');
}

function resetForm(){
		$("#nomBeguda").val("");
		$("#nomBegudaES").val("");
		$("#tipusBeguda option:first").attr('selected','selected');		
		$("#importBeguda").val("");
		$("#descripcioBeguda").val("");
		$("#descripcioBegudaES").val("");
		
		$("#id").val("");		
}

function submitBeguda(){
	
	if($("#nomBeguda").val() !=''){
		$('#nomBeguda').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#nomBeguda').css('border', 'solid 1px red');
		return;
	}
		
	if($("#nomBegudaES").val() !=''){
		$('#nomBegudaES').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#nomBegudaES').css('border', 'solid 1px red');
		return;
	}
	
	if($("#importBeguda").val() !=''){
		$('#importBeguda').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#importBeguda').css('border', 'solid 1px red');
		return;
	}
	
	if($("#descripcioBeguda").val() !=''){
		$('#descripcioBeguda').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#descripcioBeguda').css('border', 'solid 1px red');
		return;
	}
	
	if($("#descripcioBegudaES").val() !=''){
		$('#descripcioBegudaES').css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#descripcioBegudaES').css('border', 'solid 1px red');
		return;
	}
	changePreu('importBeguda');
	document.getElementById("form_saveBeguda").submit();
}

function reloadTableBegudes(){
	oTablebegudes.fnDeleteRow( 0 );
}

function showDivBeguda(id){
	
	data ="idBeguda="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxLoadBegudaAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	
					 		$("#id").val(json.id);
					 		$("#nomBeguda").val(changeHTML(json.nom));
					 		$("#nomBegudaES").val(changeHTML(json.nomES));
					 		$("#tipusBeguda").val(json.tipus);					 		
					 		$("#importBeguda").val(json.preu);
					 		$("#descripcioBeguda").val(changeHTML(json.descripcio));
					 		$("#descripcioBegudaES").val(changeHTML(json.descripcioES));
					 		$("#infobegudanew").show('slow');					 							 			 					 										 					 						 					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("La sessi� pot haver caducat!!");
  								$("#errorsajax").show();  		
		  					}
		});	
	
}

function deleteBeguda(id){
	 var where_to= confirm(initTableParams.txtconfirmborrabeguda);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
		
			data ="idBeguda="+id;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/admin/ajaxDeleteBeguda.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTableBegudes();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("La sessi� pot haver caducat!!");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

var  oTablebegudes=null;


$(document).ready(function() {

	
			
		//taula de les begudes
	oTablebegudes =$("#tbl_begudes").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"tipus","bSortable": false, sWidth: '200px' },
					                  { "mDataProp":"nom", "bSortable": false, sWidth: '400px' },
					                  { "mDataProp":"preu", "bSortable": false, sWidth: '50px' },
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '50px' }
					            ],
					"sPaginationType": "full_numbers",
					"oLanguage": {
						  "sProcessing": "<img src='/"+context+"/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/"+context+"/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/"+context+"/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/"+context+"/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/"+context+"/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "330",		    
					"sScrollX": "902",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableBegudes.action',
		    		"fnServerData": function( sUrl, aoData, fnCallback) {      			    			    			
		     		$.ajax( {
		            	"url": sUrl,
		            	"data": aoData,              
		            	"dataType": "json",
		            	"cache": false,
		           		"success":function(json){  
		           			if(json.error!=null){
		           				$("#errorsajaxlabel").text(json.error);
		                		$("#errorsajax").show();
		           			}else{
			            		fnCallback(json);			            		
		           			}            	
		            	},
		            	"error":function(e){ 
		            		$("#errorsajaxlabel").text("La sessi� pot haver caducat!!");
		            		$("#errorsajax").show();            	
		            	}
		        	} );
		    	}
			} );
		
				
		//ocultem divs
		$("#infobegudanew").hide();
		$("#errorsajax").hide();

} );
