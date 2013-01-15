///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborraplat,txtconfirmborrarest){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;
		this.txtconfirmborraplat= txtconfirmborraplat;
		this.txtconfirmborrarest= txtconfirmborrarest;
}

//Borra  Restaurant
function deleteRestaurant(id){
	 var where_to= confirm(initTableParams.txtconfirmborrarest);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
			  
			
			var idRestaurant = document.getElementById("idRestaurant").value
			
			data ="idRestaurant="+idRestaurant;
			$.ajax({
				  type: "POST",
				  url: '/onlineBot/admin/ajaxDeleteRestaurantAction.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTableRestaurants();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

//Borra plat del restaurant
function deletePlat(id){
	 var where_to= confirm(initTableParams.txtconfirmborraplat);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
			  
			
			var idRestaurant = document.getElementById("idRestaurant").value
			
			data ="idPlat="+id+"&idRestaurant="+idRestaurant;
			$.ajax({
				  type: "POST",
				  url: '/onlineBot/admin/ajaxDeletePlatAction.action',
				  dataType: 'json',
				  data: data,
				  success: function(json){	
					  if(json!=null && json.error!=null){
		   				$("#errorsajaxlabel").text(json.error);
		   				$("#errorsajax").show();
		   			}else{
							 alert(initTableParams.txtborrat);
							 reloadTablePlats();							
		   			}				
				  },
				  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}
//agafem info del restaurant i recarreguem la taula de plats
function showDivRestaurant(id){
	data ="id="+id;
	$.ajax({
		  type: "POST",
		  url: '/onlineBot/admin/ajaxLoadRestaurantAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	if(json.nom!=null){
					 		document.getElementById("nomrestaurant").value= json.nom;
					 	}
					 
					 	if(json.descripcio!=null){
					 		document.getElementById("descrestaurant").value= json.descripcio;
					 	}
					 	if(json.id!=null){
					 		document.getElementById("idRestaurant").value= json.id;
					 		
					 	}
					 	if(json.foto!=null){
					 		document.getElementById("imageRestaurant").src="/onlineBot/admin/ImageAction.action?imageId="+json.foto.id;
					 	}else{
					 		document.getElementById("imageRestaurant").src="../images/noFoto.gif";
					 	}
					
					 
					 	$("#inforestaurant").show('slow');
					 	reloadTablePlats();					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("Error in ajax call");
  								$("#errorsajax").show();  		
		  					}
		});	
}

function goToPlatInfo(id){
	
window.location.href="/onlineBot/admin/plat.action?idPlat="+id;	
}

function reloadTablePlats(){
	oTablePlats.fnDeleteRow( 0 );
}

function reloadTableRestaurants(){
	oTableRest.fnDeleteRow( 0 );
}


var  oTableRest=null;
var  oTablePlats=null;



$(document).ready(function() {

	
			
		//taula dels restaurants
		oTableRest =$("#tbl_restaurants").dataTable( {
					"iDisplayLength": 2,
					 "aoColumns" : [
					                  { "mDataProp":"nom","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"descripcio", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '40px' }
					            ],
					"sPaginationType": "full_numbers",
					"oLanguage": {
						  "sProcessing": "<img src='/onlineBot/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
					      "oPaginate": {
					        "sFirst": "<img src='/onlineBot/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
					        "sLast": initTableParams.txtlast+"&nbsp;<img src='/onlineBot/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
					        "sNext": initTableParams.txtnext+"&nbsp;<img src='/onlineBot/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
					        "sPrevious": "<img src='/onlineBot/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
					      }
					    },
					"sScrollY": "100",		    
					"sScrollX": "152",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/onlineBot/admin/ajaxTableRestaurantsAction.action',
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
			            		//("#tbl_restaurants_paginate").hide();
		           			}            	
		            	},
		            	"error":function(e){ 
		            		$("#errorsajaxlabel").text("Error in ajax call");
		            		$("#errorsajax").show();            	
		            	}
		        	} );
		    	}
			} );
		
		//per agafar click en qualsevol part de la row
		$("#tbl_restaurants tbody").click(function(event) {
			$(oTableRest.fnSettings().aoData).each(function (){	
				$(this.nTr).addClass('select_ro');
			});
			$(event.target.parentNode).removeClass('odd');
			$(event.target.parentNode).removeClass('even');
			
			var id=event.target.parentNode.firstChild.id;
			showDivRestaurant(id);
		
		});
		
		//taula per els plats un cop escollim restaurant
		oTablePlats =$("#tbl_plats").dataTable( {
			"iDisplayLength": 2,
			 "aoColumns" : [
			                  { "mDataProp":"nom","bSortable": false },
			                  { "mDataProp":"descripcio", "bSortable": false },
			                  { "mDataProp":"preu", "bSortable": false},
			                  { "mDataProp":"accio", "bSortable": false}	
			            ],
			"sPaginationType": "full_numbers",
			"oLanguage": {
				  "sProcessing": "<img src='/onlineBot/images/large-loading.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtloading,
			      "oPaginate": {
			        "sFirst": "<img src='/onlineBot/images/icono-paginador-inicio.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtfirst,
			        "sLast": initTableParams.txtlast+"&nbsp;<img src='/onlineBot/images/icono-paginador-fin.gif' style='vertical-align:middle'>",
			        "sNext": initTableParams.txtnext+"&nbsp;<img src='/onlineBot/images/icono-paginador-siguiente.gif' style='vertical-align:middle'>",
			        "sPrevious": "<img src='/onlineBot/images/icono-paginador-anterior.gif' style='vertical-align:middle'>&nbsp;"+initTableParams.txtprevious
			      }
			    },
			"sScrollY": "100",		    
			"sScrollX": "152",	
		    "bScrollCollapse": true,
			"bProcessing": false,
			"bServerSide": true,
			"sAjaxSource": '/onlineBot/admin/ajaxTablePlatsAction.action',
			"fnServerData": function( sUrl, aoData, fnCallback) {
				var id = $("#idRestaurant").val();
				if(id=="")return;
				aoData.push({"name":"id", "value": id});
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
		        		$("#errorsajaxlabel").text("Error in ajax call");
		        		$("#errorsajax").show();            	
		        	}
		    	} );
		}
		} );
		
		//per agafar click en qualsevol part de la row
		/*$("#tbl_plats tbody").click(function(event) {
			$(oTablePlats.fnSettings().aoData).each(function (){	
				$(this.nTr).addClass('select_ro');
			});
			$(event.target.parentNode).removeClass('odd');
			$(event.target.parentNode).removeClass('even');
			
			var id=event.target.parentNode.firstChild.id;
			goToPlatInfo(id);
		
		});*/


		//ocultem divs
		$("#inforestaurant").hide();
		$("#errorsajax").hide();

} );















