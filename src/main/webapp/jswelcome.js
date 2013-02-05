///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirmcontinuar,txtconfirm,txtproductes,txtproducte,txtguardat,txtwrongemail){		
	
	this.txtconfirmcontinuar= txtconfirmcontinuar;
	this.txtconfirm = txtconfirm;
	this.txtproductes = txtproductes;
	this.txtproducte = txtproducte;
	this.txtguardat = txtguardat; 
	this.txtwrongemail = txtwrongemail;
}
function getDateToday(){
	var d=new Date();
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}

$( ".selector_jq" ).click(function() {
	var id = $(this).attr("id");
	confirmComanda.idRestaurant=id;
	var comanda = window.localStorage.getItem("comanda");
	var dataInicialComanda =$("#dataObert_"+id).val();
	window.localStorage.setItem("comanda.data",dataInicialComanda);
	if(comanda != 'undefined' && comanda != null){
		acceptComandaDialog();
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+dataInicialComanda;
	}
});

var actionCloseConfirm = function(){
	var data = window.localStorage.getItem("comanda.data");
	window.localStorage.clear();	
	window.localStorage.setItem("comanda.data",data);
	window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+confirmComanda.idRestaurant+"&data="+data;
}

function acceptComandaDialog(){
	confirmOnline.closeSetFunc(actionCloseConfirm);
	confirmOnline.confirm(initParams.txtconfirmcontinuar,confirmComanda);
}

var confirmComanda = function (){
									var comanda = window.localStorage.getItem("comanda");
									if(comanda != 'undefined' && comanda != null){
										var data = window.localStorage.getItem("comanda.data");
										window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+confirmComanda.idRestaurant+"&idComanda="+comanda+"&data="+data;
									}
								}


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
		alertOnline.alertes(initParams.txtwrongemail);
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
  				alertOnline.alertes(initParams.txtguardat);		
  			  }				
  		  },
  		  error: function(e){   errorOnline.error("Error in AJAX");	
  		  					}
  		});	
}

$(document).ready(function() {
	var comanda = window.localStorage.getItem("comanda");
	if(comanda != 'undefined' && comanda != null){
		var numplats = window.localStorage.getItem("comanda.numplats");
		var numbegudes = window.localStorage.getItem("comanda.numbegudes");
		var nProductes = parseInt(numplats)+parseInt(numbegudes);
		if(nProductes==1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproducte);
		}else if(nProductes>1){
			$("#numProduct").text(initParams.txtconfirm+" "+nProductes+" "+initParams.txtproductes);
		}		
		
		
	}else{
		window.localStorage.clear();
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}
	
	if(isNaN(nProductes)){
		window.localStorage.clear();
		$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
	}
});


