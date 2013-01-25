///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtconfirmcontinuar){		
	this.txtconfirmcontinuar= txtconfirmcontinuar;
}
function getDateToday(){
	var d=new Date();
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}

$( ".selector" ).click(function() {
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
									var data = window.localStorage.getItem("comanda.data");
									window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+confirmComanda.idRestaurant+"&idComanda="+comanda+"&data="+data;
								}


$(document).ready(function() {
    $('#coin-slider').coinslider();
});