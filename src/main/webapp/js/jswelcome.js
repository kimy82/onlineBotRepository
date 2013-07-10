///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirmcontinuar,txtconfirm,txtproductes,txtproducte,txtguardat,txtwrongemail, txtavisrestauranttancat){		
	
	this.txtconfirmcontinuar= txtconfirmcontinuar;
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtguardat = txtguardat; 
	this.txtwrongemail = txtwrongemail;
	this.txtavisrestauranttancat = txtavisrestauranttancat;
}

function inicio(){
	$("#divCargando").hide();
	$("#divCargandoImg").hide();
}
function wait(){
	$("#divCargando").show();
	$("#divCargandoImg").show();
}

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
					if(comanda != null && !isNaN(comanda)){
						var numplats = window.localStorage.getItem("comanda.numplats");
						if(numplats == 'undefined' || numplats == null){
							numplats=0;
							window.localStorage.setItem("comanda.numplats","0");
						}
						
						var numbegudes = window.localStorage.getItem("comanda.numbegudes");
						if(numbegudes == null){
							numbegudes=0;
							window.localStorage.setItem("comanda.numbegudes","0");
						}
						var nProductes = parseInt(numplats)+parseInt(numbegudes);
						if(nProductes==1){
							$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproducte);
						}else if(nProductes>1){
							$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproductes);
						}		
						
						if(isNaN(nProductes)){
							window.localStorage.clear();
							$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
						}
						
					}else{
						window.localStorage.clear();
						$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
					}
					
				}catch(error){
					console.log(error);
				}
			});
		
	},
	saveEmail: function(self){
		$(self).attr('disabled','disabled');
		var email = $("#email").val();
		if(!welcomeAction._validateEmail(email)){
			$("#error").text(initParams.txtwrongemail);
			return;
		}
		var data = "email="+email;
		
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/'+context+'/setNewsLetter.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  if(json!=null && json.error!=null){
	  				console.log(json.error);	
	  			  }else{
	  				$(self).removeAttr('disabled');  
	  				$("#error").text(initParams.txtguardat);		
	  			  }				
	  		  },
	  		  error: function(e){   errorOnline.error(txterrorAjax);	
	  		  					}
	  		});	
	},
	_validateEmail: function(email){
		 var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		 return re.test(email);
	},
	getDateToday: function(){
		var d=new Date();
		return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
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
			if(isNaN(id)){console.log("Restaurant with no id");	}
			var restaurantId = window.localStorage.getItem("comanda.restaurant");
			if(!isNaN(restaurantId) && restaurantId != null && restaurantId!=id){
				alertOnline.alertes(txtavisdosrestaurants);	
			}
			document.getElementById(id).click();
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
			if(idRestaurant==null) return;
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
			confirmOnline.closeSetFunc(menuRestaurantAction.actionCloseConfirm);
			confirmOnline.confirm(initParams.txtconfirmcontinuar,menuRestaurantAction.confirmComanda);
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
	}
	
}	

$( ".selector_jq" ).click(function() {
	var id = $(this).attr("id");
	menuRestaurantAction.confirmComanda.idRestaurant=id;
	window.localStorage.setItem("comanda.restaurant", id);
	var comanda = window.localStorage.getItem("comanda");
	var dataInicialComanda =$("#dataObert_"+id).val();

	var comandaConfirm = window.localStorage.getItem("comanda.confirm");
	
	if(comandaConfirm!=null){
		var currentDay = new Date();
		if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
			window.localStorage.removeItem("comanda.confirm");
		}
	}
	
	comandaConfirm = window.localStorage.getItem("comanda.confirm");
	window.localStorage.setItem("comanda.data",dataInicialComanda);
	if(!isNaN(comanda) && comanda != null && comandaConfirm == null ){
		var day = new Date();
		window.localStorage.setItem("comanda.confirm",day.getTime());
		menuRestaurantAction.acceptComandaDialog();
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+dataInicialComanda;
	}
});

welcomeAction._init();
menuRestaurantAction._init();