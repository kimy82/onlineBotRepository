///////////////////////////////////
//variables per textos en locale
var comanda=null;

//per el formulari
function onlyDouble(value,id){
	 var n=value.split(".");
	  if(n.length==1){
		  value=value+".00";
	  }
	  if(value =='' || /^[0-9]*\.[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrordouble);
	}
}  

function onlyEntero(value,id){
	  if(value =='' || /^[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alert(initTableParams.txterrornumber);
	}
}

function ismaxlength(obj,mlength){
	if (obj.getAttribute && obj.value.length>mlength)
		obj.value=obj.value.substring(0,mlength);
	
}


function fillAddress(){
	
	var addressCarrer = $("#carrer").val();
	var addressNum = $("#numcarrer").val();
	var addressCodi = $("#codi").val();
	var addressPoble = $("#poble").val();
	
	$("#address").val(addressCarrer+" "+addressNum+", "+addressCodi+" "+addressPoble);
	
}


function openCloseDiv(id){
	 	
	 id($("#"+id).is(":hidden")){
		 $("#"+id).show('slow');
	 }else{
		 $("#"+id).hide('slow');
	 }
}
function changePlat(id){
	if(comanda!=null){
		comanda.changeNumPlats(id,num);
	}
}

//obj comanda
function Comanda(id){
	
	var self = this;
	this.id= id;
	this.plats= new Array();
	this.address ="";
	this.dia="";
	this.hora="";
	this.observacions="";
				
	this._init(self);
	
	$("#hora").onchange= function(){
		self.hora = this.value;
	}
	
	$("#dia").onchange= function(){
		self.dia = this.value;
	}
	
	$("#comandaddress").onchange= function(){
		self.address = this.value;
	}
	
	
	this.setPlat= function(plat){
		self.plats.push(plat);
	}
	
	this.changeNumPlats= function(id,num){
		 for (i=0;i<self.plats.length;i++){			 
			 if(self.plats[i].id==id){self.plats[i].numPlats=num;}
		  }		
	}
	
	
	
}

Comanda.prototype.getToStringPlats= function(){
	 var platsString="";
	 for (i=0;i<this.plats.length;i++){			 
		 platsString= ";"+self.plats[i].id+""+this.plats[i].numPlats;
	  }
	 if(platsString!=""){
		 platsString=platsString.substr(1);
	 }
	 return platsString;
}

Comanda.prototype._init= function(self){
	
	$("#tbl_platscomanda tr.item").each(function() {
		var _self = $(this);
		var id = _self.find("input.idPlat").id;
		var quantity = _self.find("input.quantity").val();
		var plat = new Plat(id,quantity);
		self.setPlat(plat);
	});	
	self.address= $("#comandaddress").val();
	self.observacions= $("#observacions").text();
	
}

//obj plat
function PLat(idPlat, num){
	
	this.id=idplat;
	this.numPlats = num;
	
}

function setInfoInForm(){
	var frm = $("#comandaform")[0];
	if(comanda!=null){
		frm.platsHid.value=comanda.getToStringPlats();
		frm.diaHid= comanda.dia;
		frm.horaHid= comanda.hora;
		frm.idcomandaHid = comanda.id;
		frm.comandaddressHid= comanda.address;
		frm.observacionsHid = comanda.observacions;				
	}
}


$(document).ready(function() {
				
		//ocultem divs
		$("#errorsajax").hide();
		$("#infoUser").hide();
		
} );















