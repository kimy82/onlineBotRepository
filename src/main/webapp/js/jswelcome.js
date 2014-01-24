//Acces a portamu

_browser = {};
function detectBrowser() {
    var uagent = navigator.userAgent.toLowerCase();

    _browser.firefox = /mozilla/.test(uagent) && /firefox/.test(uagent);
    _browser.chrome = /webkit/.test(uagent) && /chrome/.test(uagent);
    _browser.safari = /applewebkit/.test(uagent) && /safari/.test(uagent) 
                                                    && !/chrome/.test(uagent);
    _browser.opera = /opera/.test(uagent);
    _browser.msie = /msie/.test(uagent);
    _browser.version = '';

    for (x in _browser) {
        if (_browser[x]) {            
            _browser.version = uagent.match(new RegExp("(" + x +
                                                           ")( |/)([0-9]+)"))[3];
            break;
        }
    }
}
detectBrowser();

var tidakbagusPortamu = false;
if(_browser.firefox ){

	if(_browser.version>=20){
		tidakbagusPortamu=true;
	}
}
if(_browser.safari){
	
	if(_browser.version>=534){		
		tidakbagusPortamu=true;
	}
}
if(_browser.opera){

	if(_browser.version>=9){
		tidakbagusPortamu=true;
	}
}
if(_browser.chrome){
	tidakbagusPortamu=true;
}

function getInternetExplorerVersion()
// Returns the version of Windows Internet Explorer or a -1
// (indicating the use of another browser).
{
   var rv = -1; // Return value assumes failure.
   if (navigator.appName == 'Microsoft Internet Explorer')
   {
      var ua = navigator.userAgent;
      var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
      if (re.exec(ua) != null)
         rv = parseFloat( RegExp.$1 );
   }
   return rv;
}

function getInternetExplorerVersion()
{
   var rv = -1;
   if (navigator.appName == 'Microsoft Internet Explorer')
   {
      var ua = navigator.userAgent;
      var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
      if (re.exec(ua) != null)
         rv = parseFloat( RegExp.$1 );
   }
   return rv;
}

   var ver = getInternetExplorerVersion();
   if ( ver> -1 )
   {
      if ( ver>= 9.0 )
    	  tidakbagusPortamu= true;
    }

if(tidakbagusPortamu == false){
	window.location.href="https://www.portamu.com/elteurestaurantacasa/canvia.action";
}

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
};
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
};




//OBJ for managment of menu restaurant 
var menuRestaurantAction ={
};
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
	
};

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