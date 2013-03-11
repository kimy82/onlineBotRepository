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
function getDateToday(){
	var d=new Date();
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}

$( ".selector_jq" ).click(function() {
	var id = $(this).attr("id");
	confirmComanda.idRestaurant=id;
	window.localStorage.setItem("comanda.restaurant", id);
	var comanda = window.localStorage.getItem("comanda");
	var dataInicialComanda =$("#dataObert_"+id).val();

	var comandaConfirm = window.localStorage.getItem("comanda.confirm");
	
	if(comandaConfirm !='undefined' && comandaConfirm!=null){
		var currentDay = new Date();
		if((currentDay.getTime()-comandaConfirm)>60*confirmTime){
			window.localStorage.removeItem("comanda.confirm");
		}
	}
	
	comandaConfirm = window.localStorage.getItem("comanda.confirm");
	window.localStorage.setItem("comanda.data",dataInicialComanda);
	if(comanda != 'undefined' && comanda != null && comandaConfirm == null ){
		var day = new Date();
		window.localStorage.setItem("comanda.confirm",day.getTime());
		acceptComandaDialog();
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+dataInicialComanda;
	}
});

function goToRestaurantMenu(id){
	document.getElementById(id).click();
}

var actionCloseConfirm = function(){
	var data = window.localStorage.getItem("comanda.data");
	var idRestaurant = window.localStorage.getItem("comanda.restaurant");
	if(idRestaurant=='undefined' || idRestaurant==null) return;
	window.localStorage.clear();	
	window.localStorage.setItem("comanda.data",data);
	window.localStorage.setItem("comanda.restaurant",idRestaurant);
	window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&data="+data;
}

function acceptComandaDialog(){
	confirmOnline.closeSetFunc(actionCloseConfirm);
	confirmOnline.confirm(initParams.txtconfirmcontinuar,confirmComanda);
}

var confirmComanda = function (){
									var comanda = window.localStorage.getItem("comanda");
									var idRestaurant = window.localStorage.getItem("comanda.restaurant");
									if(idRestaurant=='undefined' || idRestaurant==null) return;
									if($("#list_rest_"+idRestaurant).hasClass("tancat")){
										alertOnline.alertes(initParams.txtavisrestauranttancat);	
									}
									if(comanda != 'undefined' && comanda != null){
										var data = window.localStorage.getItem("comanda.data");
										window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+idRestaurant+"&idComanda="+comanda+"&data="+data;
									}
								}


//Borra el param quan tanquem la window
var resetConfirm = function() {
	  window.localStorage.removeItem("comanda.confirm");
	  return '';
};

$(document).ready(function() {
    $('#coin-slider').coinslider({ width: 1000,height: 299});
});

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

function saveEmail(){
	var email = $("#email").val();
	if(!validateEmail(email)){
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
  				errorOnline.error("Error in AJAX: "+json.error);	
  			  }else{
  				$("#error").text(initParams.txtguardat);		
  			  }				
  		  },
  		  error: function(e){   errorOnline.error(txterrorAjax);	
  		  					}
  		});	
}

$(document).ready(function() {
	var comanda = window.localStorage.getItem("comanda");
	if(comanda != 'undefined' && comanda != null){
		var numplats = window.localStorage.getItem("comanda.numplats");
		if(numplats == 'undefined' || numplats == null){
			numplats=0;
			window.localStorage.setItem("comanda.numplats","0");
		}
		
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		if(numbegudes == 'undefined' || numbegudes == null){
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
	
	
});


