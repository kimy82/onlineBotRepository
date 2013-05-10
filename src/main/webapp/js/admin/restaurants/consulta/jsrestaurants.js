///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtlast,txtnext,txtprevious,txtfirst,txtloading,txtborrat,txtconfirmborraplat,txtconfirmborrarest,txtguardat){		
		this.txtlast=txtlast;
		this.txtnext=txtnext;
		this.txtprevious=txtprevious;
		this.txtfirst=txtfirst;
		this.txtloading=txtloading;
		this.txtborrat=txtborrat;
		this.txtconfirmborraplat= txtconfirmborraplat;
		this.txtconfirmborrarest= txtconfirmborrarest;
		this.txtguardat = txtguardat;
}

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
}

function saveHoraObertura(id){
	
	var hores =$("#horesRestaurant").val();
	
	
	if($("#"+id).hasClass("notcheck")){
		$("#"+id).removeClass("notcheck");
		$("#"+id).addClass("check");
		$("#horesRestaurant").val(hores+"|"+id);
		return;
	}
	if($("#"+id).hasClass("check")){
		
		var array = hores.split("|");
		var newhores = "";
		$.each(array, function (i,item){
			if(item!=id){
				newhores=newhores+item+"|";
			}
		});
		$("#horesRestaurant").val(newhores);
		$("#"+id).removeClass("check");
		$("#"+id).addClass("notcheck");
		return;
	}	
}


//Borra  Restaurant
function deleteRestaurant(id){
	 var where_to= confirm(initTableParams.txtconfirmborrarest);
	  if (where_to== false)
	  {
		    return;
	  }
	  else {
			
			data ="idRestaurant="+id;
			$.ajax({
				  type: "POST",
				  url: '/'+context+'/admin/ajaxDeleteRestaurantAction.action',
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
				  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
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
				  url: '/'+context+'/admin/ajaxDeletePlatAction.action',
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
				  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
										$("#errorsajax").show();  		
				  					}
				});
	  }
}

function resetHores(){
	var arrayHores= new Array("0800","0830","0900","0930","1000","1030","1100","1130","1200","1230",
							  "1300","1330","1400","1430","1500","1530","1600","1630","1700","1730",
							  "1800","1830","1900","1930","2000","2030","2100","2130","2200","2230",
							  "2300","2330","2400");
	$.each(arrayHores,function(i,item){
		if(item !=""){
			if($("#"+item).hasClass("check")){
				$("#"+item).removeClass("check");
				$("#"+item).addClass("notcheck");
			}
		}
	});	
}

//agafem info del restaurant i recarreguem la taula de plats
function showDivRestaurant(id){
	data ="id="+id;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxLoadRestaurantAction.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json==null || json.error!=null){
     				$("#errorsajaxlabel").text(json.error);
     				$("#errorsajax").show();
     			}else{
					 	if(json.nom!=null){
					 		document.getElementById("nomrestaurant").value= json.nom;
					 		$("#nomrestaurant").val(changeHTML(json.nom));
					 	}					 
					 	if(json.descripcio!=null){
					 		$("#descrestaurant").val(changeHTML(json.descripcio));					 		
					 	}
					 	if(json.descripcioES!=null){
					 		$("#descrestaurantES").val(changeHTML(json.descripcioES));
					 	}
					 	if(json.id!=null){
					 		document.getElementById("idRestaurant").value= json.id;					 		
					 	}
					 	if(json.foto!=null){
					 		document.getElementById("imageRestaurant").src="/"+context+"/admin/ImageAction.action?imageId="+json.foto.id;
					 	}else{
					 		document.getElementById("imageRestaurant").src="../images/noFoto.gif";
					 	}
					 	
					 	if(json.codiMaquina!=null){
					 		$("#codiMaquina").val(json.codiMaquina);
					 	}
					 						 	
					 	if(json.hores!=null){
					 		var array = json.hores.split("|");
							resetHores();
							$.each(array, function (i,item){
								if(item!=""){
									$("#"+item).addClass("check");
									$("#"+item).removeClass("notcheck");
								}								
							});
							$("#horesRestaurant").val(json.hores);						
					 	}
					 	
					 	$("#inforestaurant").show('slow');
					 	reloadTablePlats();					 						  
     			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
  								$("#errorsajax").show();  		
		  					}
		});	
}

function goToPlatInfo(id){
	
window.location.href="/"+context+"/admin/plat.action?idPlat="+id;	
}

function reloadTablePlats(){
	oTablePlats.fnDeleteRow( 0 );
}

function reloadTableRestaurants(){
	oTableRest.fnDeleteRow( 0 );
}

function changePrioritat(idPlat){
	
	var prioritat = $("#prior_"+idPlat).val();
	data ="idPlat="+idPlat+"&prioritat="+prioritat;
	$.ajax({
		  type: "POST",
		  url: '/'+context+'/admin/ajaxChangePrioritatPlat.action',
		  dataType: 'json',
		  data: data,
		  success: function(json){	
			  if(json!=null && json.error!=null){
   				$("#errorsajaxlabel").text(json.error);
   				$("#errorsajax").show();
   			}else{
					 alert(initTableParams.txtguardat);							
   			}				
		  },
		  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
								$("#errorsajax").show();  		
		  					}
		});
	
}

var  oTableRest=null;
var  oTablePlats=null;



$(document).ready(function() {

	
			
		//taula dels restaurants
		oTableRest =$("#tbl_restaurants").dataTable( {
					"iDisplayLength": 12,
					 "aoColumns" : [
					                  { "mDataProp":"nom","bSortable": false, sWidth: '150px' },
					                  { "mDataProp":"descripcio", "bSortable": false, sWidth: '350px' },
					                  { "mDataProp":"accio", "bSortable": false, sWidth: '40px' }
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
					"sScrollY": "100%",		    
					"sScrollX": "100%",	
				    "bScrollCollapse": true,
		    		"bProcessing": false,
		    		"bServerSide": true,
		    		"sAjaxSource": '/'+context+'/admin/ajaxTableRestaurantsAction.action',
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
		            		$("#errorsajaxlabel").text("La sessió pot haver caducat!!");
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
			"iDisplayLength": 12,
			 "aoColumns" : [
			                  { "mDataProp":"nom","bSortable": false },
			                  { "mDataProp":"descripcio", "bSortable": false },
			                  { "mDataProp":"prioritatPlat", "bSortable": false },
			                  { "mDataProp":"actiuPlat", "bSortable": false },
			                  { "mDataProp":"preu", "bSortable": false},
			                  { "mDataProp":"accio", "bSortable": false}	
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
			"sScrollY": "900",		    
			"sScrollX": "452",	
		    "bScrollCollapse": true,
			"bProcessing": false,
			"bServerSide": true,
			"sAjaxSource": '/'+context+'/admin/ajaxTablePlatsAction.action',
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
		        		$("#errorsajaxlabel").text("La sessió pot haver caducat!!");
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















