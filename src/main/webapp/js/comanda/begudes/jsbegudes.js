///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirm,txtproductes,txtproducte, txtavisrestauranttancat,txtconfirmcontinuar){		
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtavisrestauranttancat = txtavisrestauranttancat;
	this.txtconfirmcontinuar = txtconfirmcontinuar;
}

//events
$(function() {

	$(".selectorBeg").dblclick(function() {

		var dragBeguda = $(this).clone();
		dragBeguda.appendTo("#droppable");

		dragBeguda.animate({
			width : "90%",
			opacity : 0.4,
			marginLeft : "0.6in",
			fontSize : "3em",
			borderWidth : "10px",
			left : "+=250px"
		}, 1800, function() {
			var item_id = $(this).attr("id");
			var rawBeguda = item_id.split("_");
			begudaOBJ.saveBegudaToComanda(rawBeguda[1]);
			$(dragBeguda).css("visiblity", "hidden");
			$(dragBeguda).css("display", "none");
		});

	});

	$(".selectorBeg").draggable({
		helper : 'clone',
		start : function(event, ui) {
			var id = $(this).attr("id");
		},
		stop : function(event, ui) {

		}
	});
	$("#droppable").droppable({
		drop : function(event, ui) {

			var item_id = ui.draggable.attr("id");
			var rawBeguda = item_id.split("_");		
			begudaOBJ.saveBegudaToComanda(rawBeguda[1]);

		}
	});
});


//OBJ for managment of welcome page 
var welcomeAction ={
}
welcomeAction._self=null;

welcomeAction ={
	
	_init: function (){
		this._self =this;
		
			$(document).ready(function() {
				try{
					
					
					var comanda = window.localStorage.getItem("comanda");
					if(!isNaN(comanda)  && comanda != null){
						
						$("#numComanda").text(comanda);
					
						welcomeAction._setPreu();
					
						var numplats = welcomeAction._setNumPlats();
					
						var numbegudes = welcomeAction._setNumBegudes();
						
						var nProductes = parseInt(numplats)+parseInt(numbegudes);
						welcomeAction._setNumProducts(nProductes);
								
					}else{
						window.localStorage.clear();
						$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
					}															
				}catch(error){
					console.log(error);
				}
			});
		
	},	
	_setPreu: function(){
		var preu = window.localStorage.getItem("comanda.preu");
		
		if(isNaN(preu)  || preu == null){
			preu=0.0;
			window.localStorage.setItem("comanda.preu","0.0");
		}
		
		if ( preu != null) {
			var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
			if (!isNaN(preuBegudes) && preuBegudes != null) {
				preu =  parseFloat(preu) + parseFloat(preuBegudes);
			}
			$("#preu").text(parseFloat(preu).toFixed(2));
		}
	},
	_setNumPlats: function(){
		var numplats = window.localStorage.getItem("comanda.numplats");
		
		if(isNaN(numplats) || numplats == null){
			numplats=0;
			window.localStorage.setItem("comanda.numplats","0");
		}else{
			
			var lis= window.localStorage.getItem("comanda.plats.lis");
			if(lis!=null){
				$("#disp_plate").append(lis);
			}
		}
		
		if ( numplats != null) {
			$("#numplats").text(numplats);
		}
		return numplats;
	},
	_setNumBegudes: function(){		
		
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		if(isNaN(numbegudes) || numbegudes == null){
			numbegudes=0;
			window.localStorage.setItem("comanda.numbegudes","0");
		}else{
			var lis= window.localStorage.getItem("comanda.begudes.lis");
			if( lis!=null){
				$("#disp_beguda").append(lis);
			}
		}
	
		if (!isNaN(numbegudes) && numbegudes != null) {
			$("#numbegudes").text(numbegudes);
		}
		return numbegudes;
	},
	_setNumProducts: function(nProducts){
		if(isNaN(nProducts)){console.log("numproducts not a number")}
		if(nProducts==1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProducts+" "+initParams.txtproducte);
		}else if(nProducts>1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProducts+" "+initParams.txtproductes);
		}		
		
		if(isNaN(nProducts)){
			window.localStorage.clear();
			$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
		}
	},
	addProduct: function(id){
		
		$("#"+id).dblclick();
		
	}
}


//OBJ for managment of menu restaurant 
var menuRestaurantAction ={
}
menuRestaurantAction._self=null;

menuRestaurantAction ={
	
	_init: function (){
		this._self =this;
	},
	goToRestaurantMenu: function(id){
		try{
			var comanda = window.localStorage.getItem("comanda");
			window.localStorage.setItem("comanda.restaurant",id);
			var data = window.localStorage.getItem("comanda.data");
			
			var comandaConfirm = window.localStorage.getItem("comanda.confirm");
			
			if(comandaConfirm!=null){
				var currentDay = new Date();
				if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
					window.localStorage.removeItem("comanda.confirm");
				}
			}
			
			comandaConfirm = window.localStorage.getItem("comanda.confirm");
			
			var restaurantId = window.localStorage.getItem("comanda.restaurant");
			if( restaurantId != null && restaurantId!=id){
				alertOnline.alertes(txtavisdosrestaurants);	
			}
			
			if(comanda != null && comandaConfirm == null){
				var day = new Date();
				window.localStorage.setItem("comanda.confirm",day.getTime());
				acceptComandaDialog();
			}else{
				window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
			}
		}catch(error){
			console.log(error);
		}
	},
	getConfirm: function(){
		try{
			var comandaConfirm = window.localStorage.getItem("comanda.confirm");
			
			if( comandaConfirm!=null){
				var currentDay = new Date();
				if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
					window.localStorage.removeItem("comanda.confirm");
				}
			}
			return window.localStorage.getItem("comanda.confirm");
		}catch(error){
			console.log(error);
		}
	},
	actionCloseConfirm: function(){
		try{
			var data = window.localStorage.getItem("comanda.data");
			var idRestaurant = window.localStorage.getItem("comanda.restaurant");
			if(idRestaurant==null || isNaN(idRestaurant)) return;
			window.localStorage.clear();	
			window.localStorage.setItem("comanda.data",data);
			window.localStorage.setItem("comanda.restaurant",idRestaurant);
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data;

		}catch(error){
			console.log(error);
		}
	},
	acceptComandaDialog: function(){
		try{
			confirmOnline.closeSetFunc(_self.actionCloseConfirm);
			confirmOnline.confirm(initParams.txtconfirmcontinuar,_self.confirmComanda);
		}catch(error){
			console.log(error);
		}
		
	},
	confirmComanda: function(){
		try{
			var comanda = window.localStorage.getItem("comanda");
			var idRestaurant = window.localStorage.getItem("comanda.restaurant");
			if(isNaN(idRestaurant) || isNaN(comanda)) return;
			if($("#list_rest_"+idRestaurant).hasClass("tancat")){
				alertOnline.alertes(initParams.txtavisrestauranttancat);	
			}
			if( comanda != null){
				var data = window.localStorage.getItem("comanda.data");
				window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
			}
		}catch(error){
			console.log(error);
		}
	},
	confirmComandaBox: function(){
		try{
			var comanda = window.localStorage.getItem("comanda");
			var idRestaurant = window.localStorage.getItem("comanda.restaurant");
			if(isNaN(idRestaurant) || isNaN(comanda)) return;
			if($("#list_rest_"+idRestaurant).hasClass("tancat")){
				alertOnline.alertes(initParams.txtavisrestauranttancat);	
			}
			if(comanda != null){
				var data = window.localStorage.getItem("comanda.data");
				window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+comanda+"&data="+data;
			}
		}catch(error){
			console.log(error);
		}
		
	},
	resetConfirm: function(){
		 window.localStorage.removeItem("comanda.confirm");
		 return '';
	},
	goToComandaPas1: function(){
		var plats = window.localStorage.getItem("comanda.numplats");
		if(isNaN(plats) || plats == null ||parseInt(plats) <= 0){
			alertOnline.alertes("No tens plats a la comanda");
			return;
		}
		var data = window.localStorage.getItem("comanda.data");
		window.location.href = "/"+context+"/comanda/goToPas1Action.action?idComanda="+$("#numComanda").text()+"&data="+data;
	}
	
}	

//manager for drinks
begudaOBJ ={
		
		_init: function (){
			this._self =this;
		},
		saveBegudaToComanda: function(idBeguda){
			if(isNaN(idBeguda)){console.log("idBeguda is not a number"); return;}
			var idComanda = $("#numComanda").text();
			if(isNaN(idComanda)){console.log("idComanda is not a number"); return;}
			var data ="idBeguda="+idBeguda+"&idComanda="+idComanda+"&promo=false";
		  	$.ajax({
		  		  type: "POST",
		  		  url: '/'+context+'/comanda/ajaxLoadBeguda.action',
		  		  dataType: 'json',
		  		  data: data,
		  		  success: function(json){
		  			  try{
			  			  if(json!=null && json.error!=null){           				
			       				console.log(json.error);	
			       			}else{
			       				if(json!=null && json.alerta!=null){
			       					alertOnline.alertes(json.alerta);
			       					
			       				}else{
			       					var numBegudes=0;
			       					var numBegudesPromo=0;           					
			       					var preuBegudes = 0.0;
			       					var html="";
			       					var begudes= json.begudes;	       					
			       					$("#disp_beguda").html("");
			       					var lis="";
			       					$.each(begudes, function(index, value) { 
			       					 	
			       					 	if(value.promo==true || value.promo=='true'){
			       					 		numBegudesPromo=numBegudesPromo+value.numBegudes;
			       					 		
			       					 	}else{
			       					 		numBegudes= numBegudes+value.numBegudes;
			       					 		preuBegudes=  parseFloat(preuBegudes) + (parseFloat(value.beguda.preu)*value.numBegudes);	       					 		
			       							var li= value.numBegudes+" <span class='plats' id='span_b_"+value.beguda.id+"'>x</span> "+changeHTML(value.beguda.nom)+"&nbsp;<a href='#' onclick='begudaOBJ.eliminaBeguda("+value.beguda.id+")' ><img class='dele' src='/"+context+"/images/delete2.png'></a><br><br>";
			       							lis = lis+li;
			    							$("#disp_beguda").append(li);
			       						}	       				
			       						
			       					});
			       				
			       					window.localStorage.setItem("comanda.begudes.lis",lis);
			       					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
			       					window.localStorage.setItem("comanda.numbegudes",numBegudes);
			       					window.localStorage.setItem("comanda.beguda.preu",preuBegudes.toFixed(2));	       					
			       					$("#numbegudes").text(numBegudes);	       				
			       					var preuComanda = window.localStorage.getItem("comanda.preu");
			       					if(!isNaN(preuComanda) && preuComanda != null){
			       						var preuFinal = parseFloat(preuComanda) + parseFloat(preuBegudes);
			       						$("#preu").text(parseFloat(preuFinal).toFixed(2));
			       					}else{
			       						window.localStorage.setItem("comanda",json.numComanda);
			       						window.localStorage.setItem("comanda.preu","0");
			       						$("#preu").text(parseFloat(preuBegudes).toFixed(2));
			       					}	       						       				
			       					
			       					$("#numComanda").text(json.numComanda);
			       				
			       				}
			       			}
		  			  }catch(error){
		  				  console.log(error);
		  			  }
		  		  },
		  		  error: function(e){  errorOnline.error(txterrorAjax);	
		  		  					}
		  		});	
		},
		eliminaBeguda: function(id){
			var idcomanda= window.localStorage.getItem("comanda");
			if(isNaN(id) || isNaN(idcomanda)){console.log("idcomanda or idbeguda not a number"); return;}
			var data ="idBeguda="+id+"&idComanda="+idcomanda;
		  	$.ajax({
		  		  type: "POST",
		  		  url: '/'+context+'/comanda/deleteBegudaFromCarrito.action',
		  		  dataType: 'json',
		  		  data: data,
		  		  success: function(json){	
		  			  if(json!=null && json.error!=null){
		  				console.log(json.error);	
		       		  }else{
		       			var begudesAnterior = window.localStorage.getItem("comanda.numbegudes");
		       			var numBegudes= parseInt(begudesAnterior)-parseInt(json.numBegudes);
		       			window.localStorage.setItem("comanda.numbegudes",numBegudes);
		       			$("#numbegudes").text(numBegudes);
		       			var preuBegudaComanda = window.localStorage.getItem("comanda.beguda.preu");
		       			var preuComanda = window.localStorage.getItem("comanda.preu");
		       			var preuBegFinal = parseFloat(parseFloat(preuBegudaComanda) - parseFloat(json.preuToRest)).toFixed(2);
		       			window.localStorage.setItem("comanda.beguda.preu",preuBegFinal);
		       			var preuFinal = parseFloat(parseFloat(preuBegFinal)+ parseFloat(preuComanda)).toFixed(2);
		       			if(isNaN(preuFinal)){
		       				$("#preu").text("0.0");
		       			}else{
		       				$("#preu").text(parseFloat(preuFinal).toFixed(2));
		       			}
		       			
		       			var lis= window.localStorage.getItem("comanda.begudes.lis");
		       			var lista="";
		       			var begudes = lis.split("<br><br>");
		       				$.each(begudes, function(index, value) { 		       					 	
		       						 if(value.indexOf("span_b_"+id)==-1 && value!='')
		       							 lista=lista+value+"<br><br>";		       										       						
		       				});

		       				window.localStorage.setItem("comanda.begudes.lis",lista);
		       				$("#disp_beguda").html(lista);
		       		  }			
		  		  },
		  		  error: function(e){   console.log("error: "+e+" ajax: "+txterrorAjax);	
		  		  					}
		  		});	
		},
		goToInfoBeguda: function(id){
			if(isNaN(id)){console.log("idBeguda is not a number"); return;}
			$("#infoBeguda_dialog").load("../foro/foroBeguda.action?idBeguda="+id);	
			$("#infoBeguda_dialog").dialog({ 
				   autoOpen: false,
				   height: 500,
				   width: 750,
				   modal: true,
				   close: function(event, ui) { 			   
					   $("#infoBeguda_dialog").dialog("close"); 			  
					}
			});
		    $("#infoBeguda_dialog").dialog("open");   
		    $("#infoBeguda_dialog").siblings('div.ui-dialog-titlebar').remove();  
		    $("#infoBeguda_dialog").removeClass("ui-dialog-content");
		    $("#infoBeguda_dialog").removeClass("ui-widget-content");
		   	$("#infoBeguda_dialog").addClass("ui-helper-clearfix");
		},
		closeInfoBeguda: function(){
			$("#infoBeguda_dialog").dialog("close");
		}	


	}

//OBJ for disches management 
var platOBJ ={
}
platOBJ._self=null;


platOBJ ={
	
	_init: function (){
		this._self =this;
	},
	eliminaPlat: function(id){
		if(isNaN(id)){console.log("idPlat is not a number"); return;}
		var idcomanda= window.localStorage.getItem("comanda");
		if(isNaN(idcomanda)){console.log("idcomanda is not a number"); return;}
		var data ="idPlat="+id+"&idComanda="+idcomanda;
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/'+context+'/comanda/deletePlatFromCarrito.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  if(json!=null && json.error!=null){
	  				console.log(json.error);	
	       		  }else{
	       			try{
		       			var platsAnterior = window.localStorage.getItem("comanda.numplats");
		       			var numPlats = parseInt(platsAnterior)-parseInt(json.numPlats)
		       			window.localStorage.setItem("comanda.numplats",numPlats);
		       			$("#numplats").text(numPlats);	       			
		       			window.localStorage.removeItem("comanda.plat_"+id);
		       			var preuComanda = window.localStorage.getItem("comanda.preu");
		       			var preuBegudaComanda = window.localStorage.getItem("comanda.beguda.preu");
		       			var preuPlatFinal = parseFloat(parseFloat(preuComanda) - parseFloat(json.preuToRest)).toFixed(2);
		       			window.localStorage.setItem("comanda.preu",preuPlatFinal);
		       			var preuFinal = parseFloat(parseFloat(preuPlatFinal)+ parseFloat(preuBegudaComanda)).toFixed(2);
		       			if(isNaN(preuFinal)){
		       				$("#preu").text("0.0");
		       			}else{
		       				$("#preu").text(parseFloat(preuFinal).toFixed(2));
		       			}
		       			var lis= window.localStorage.getItem("comanda.plats.lis");
		       			var lista="";
		       			var plats = lis.split("<br><br>");
		       				$.each(plats, function(index, value) { 		       					 	
		       						 if(value.indexOf("span_p_"+id)==-1 && value!='')
		       							 lista=lista+value+"<br><br>";		       										       						
		       				});

		       				window.localStorage.setItem("comanda.plats.lis",lista);
		       				$("#disp_plate").html(lista);
	       			}catch(error){
						console.log(error);
					}
	       		  }			
	  		  },
	  		  error: function(e){   errorOnline.error(txterrorAjax);	
	  		  					}
	  		});			
	}

}

begudaOBJ._init(); 
platOBJ._init(); 
menuRestaurantAction._init();
welcomeAction._init();



