///////////////////////////////////
//variables per textos en locale
var initParams=null ;

function InitParams(txtBegudaNoPromocio,txtNoMoreDrinksToAddinPromo,txtAddDrinkstoBox, txtdescompteaplicat,txtpromodeleted, txtfaltahora, txtcheckaddress){		
		this.txtBegudaNoPromocio=txtBegudaNoPromocio;
		this.txtNoMoreDrinksToAddinPromo=txtNoMoreDrinksToAddinPromo;
		this.txtAddDrinkstoBox = txtAddDrinkstoBox;
		this.txtdescompteaplicat = txtdescompteaplicat;
		this.txtpromodeleted = txtpromodeleted;
		this.txtfaltahora = txtfaltahora;
		this.txtcheckaddress = txtcheckaddress;
}
//progress var
						window.setTimeout(function() {
						    var chargeBar = new Progress.bar({ id: "progress1", autoRemove: false, backgroundSpeed: 5, type: "charge" });
						    chargeBar.renderTo(document.getElementById('chargeBar'));
						
						    var percent = 0;
						    window.setInterval(function() {
						        percent = percent + Math.floor(Math.random()*10);
						        percent = percent >= 100 ? 0 : percent;
						        chargeBar.update(percent);
						    }, 500);						
						}, 500);

$("#chargeBar").hide();

function reloadHores(){
	var dia = $("#dia").val();
	var data = window.localStorage.setItem("comanda.data",dia);
	var comanda = window.localStorage.getItem("comanda");
	var data = "data="+dia+"&idComanda="+comanda;
	
  	$.ajax({
  		  type: "POST",
  		  url: '/onlineBot/comanda/loadHores.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json!=null && json.error!=null){
  				errorOnline.error("Error in AJAX: "+json.error);	
  			  }else{
  				  $("#comandahora").val("");
  				if(json._0800=='true'){setHourCheck('0800');}else{setHourNotCheck('0800'); }  
  				if(json._0830=='true'){setHourCheck('0830');}else{setHourNotCheck('0830'); }  
  				if(json._0900=='true'){setHourCheck('0900');}else{setHourNotCheck('0900'); }  				  
  				if(json._0930=='true'){setHourCheck('0930');}else{setHourNotCheck('0930'); }  
  				if(json._1000=='true'){setHourCheck('1000');}else{setHourNotCheck('1000'); }
  				if(json._1030=='true'){setHourCheck('1030');}else{setHourNotCheck('1030'); }  				  
  				if(json._1100=='true'){setHourCheck('1100');}else{setHourNotCheck('1100'); }  
  				if(json._1130=='true'){setHourCheck('1130');}else{setHourNotCheck('1130'); }    				  
  				if(json._1200=='true'){setHourCheck('1200');}else{setHourNotCheck('1200'); }
  				if(json._1230=='true'){setHourCheck('1230');}else{setHourNotCheck('1230'); }  				 
  				if(json._1300=='true'){setHourCheck('1300');}else{setHourNotCheck('1300'); }  				 
  				if(json._1330=='true'){setHourCheck('1330');}else{setHourNotCheck('1330'); }  				 
  				if(json._1400=='true'){setHourCheck('1400');}else{setHourNotCheck('1400'); }
  				if(json._1430=='true'){setHourCheck('1430');}else{setHourNotCheck('1430'); }  				 
  				if(json._1500=='true'){setHourCheck('1500');}else{setHourNotCheck('1500'); }
  				if(json._1530=='true'){setHourCheck('1530');}else{setHourNotCheck('1530'); }
  				if(json._1600=='true'){setHourCheck('1600');}else{setHourNotCheck('1600'); }
  				if(json._1630=='true'){setHourCheck('1630');}else{setHourNotCheck('1630'); }
  				if(json._1700=='true'){setHourCheck('1700');}else{setHourNotCheck('1700'); }
  				if(json._1730=='true'){setHourCheck('1730');}else{setHourNotCheck('1730'); }
  				if(json._1800=='true'){setHourCheck('1800');}else{setHourNotCheck('1800'); }
  				if(json._1830=='true'){setHourCheck('1830');}else{setHourNotCheck('1830'); }
  				if(json._1900=='true'){setHourCheck('1900');}else{setHourNotCheck('1900'); }
  				if(json._1930=='true'){setHourCheck('1930');}else{setHourNotCheck('1930'); }
  				if(json._2000=='true'){setHourCheck('2000');}else{setHourNotCheck('2000'); }
  				if(json._2030=='true'){setHourCheck('2030');}else{setHourNotCheck('2030'); }
  				if(json._2100=='true'){setHourCheck('2100');}else{setHourNotCheck('2100'); }
  				if(json._2130=='true'){setHourCheck('2130');}else{setHourNotCheck('2130'); }
  				if(json._2200=='true'){setHourCheck('2200');}else{setHourNotCheck('2200'); }
  				if(json._2230=='true'){setHourCheck('2230');}else{setHourNotCheck('2230'); }
  				if(json._2300=='true'){setHourCheck('2300');}else{setHourNotCheck('2300'); }
  				if(json._2330=='true'){setHourCheck('2330');}else{setHourNotCheck('2330'); }
  				if(json._2400=='true'){setHourCheck('2400');}else{setHourNotCheck('2400'); }
  			  }				
  		  },
  		  error: function(e){   errorOnline.error("Error in AJAX");	
  		  					}
  		});	
	
}

function checKHour(id){
	var id_old = $("#comandahora").val();
	
	$("#"+id_old).removeClass("checked");
	$("#"+id_old).addClass("check");
	
	$("#"+id).removeClass("check");
	$("#"+id).addClass("checked");

	
	$("#comandahora").val(id);
}

function setHourNotCheck(id){
	$("#"+id).removeClass("check");
	$("#"+id).removeClass("checked");
	$("#"+id).addClass("notcheck");
}
function setHourCheck(id){
	$("#"+id).addClass("check");
	$("#"+id).removeClass("checked");
	$("#"+id).removeClass("notcheck");
}
function setHourChecked(id){
	$("#"+id).removeClass("check");
	$("#"+id).addClass("checked");
	$("#"+id).removeClass("notcheck");	
}
function payComanda(){
		var comanda = window.localStorage.getItem("comanda");
		if(comanda!= null && comanda != 'undefined'){
				$("#chargeBar").show();
				var subdata = checkPromoImport();
				var data ="idComanda="+comanda + subdata;
				window.location.href="/onlineBot/payment/paymentEntry.action?"+data;
		}		
}	

function checkPromoImport(){
	var promo = 	window.localStorage.getItem("comanda.promo.descompte");
	if(promo!=null && promo!='undefined'){
		var importe = window.localStorage.getItem("comanda.promo.descompte.import");
		var tipus = window.localStorage.getItem("comanda.promo.descompte.tipus");
		
		return "&tipuDescomte="+tipus+"&importDescomte="+importe;
	}
	return "";
}

function onlyEntero(value,id){
	  if(value =='' || /^[0-9]*$/.test(value)){
		$('#'+id).css('border', 'solid 1px rgb(135,155,179)');
		return true;
	}else{
		$('#'+id).css('border', 'solid 1px red');
		alertOnline.alertes(initTableParams.txterrornumber);		
		return false;
	}
}

function saveNewPLatAmount(id,value){
	if(onlyEntero(value,id)){
		 var preu = $("#platpreu_"+id).text();
		 var numPlatsAnt = window.localStorage.getItem("comanda.plat_"+id);
		 if(numPlatsAnt != 'undefined' && numPlatsAnt != null){
			 
			 var nPlatsAdded = parseInt(value)-parseInt(numPlatsAnt);
			 
			 window.localStorage.setItem("comanda.plat_"+id,value);
			 
			 var preuToAdd = parseFloat(preu)*nPlatsAdded;
			 var preuActual = $("#preu").text();
			 var newPreu = preuToAdd+parseFloat(preuActual);
			 $("#preu").text(parseFloat(newPreu).toFixed(2));
			 
			 window.localStorage.setItem("comanda.preu",parseFloat(newPreu).toFixed(2));
			 
			 var nPlats = $("#numplats").text();
			 
			 $("#numplats").text(parseInt(nPlats)+nPlatsAdded);
			 window.localStorage.setItem("comanda.numplats",parseInt(nPlats)+nPlatsAdded);
			 //Guardem els plats afegits
			 savePlatToComanda(id,nPlatsAdded);
		 }else{
			 
			 //Si entrem aki hi ha hagut un error
			 alertOnline.alertes("Please try again!! or remove the plat. There aren't more");								 
		 }
	}
}

function savePlatToComanda(idPlat,nPlats){
	var idcomanda= window.localStorage.getItem("comanda");
	var data ="idPlat="+idPlat+"&idComanda="+idcomanda+"&nplats="+nPlats;
  	$.ajax({
  		  type: "POST",
  		  url: '/onlineBot/comanda/ajaxLoadNumPlat.action',
  		  dataType: 'json',
  		  data: data,
  		  success: function(json){	
  			  if(json!=null && json.error!=null){
  				errorOnline.error("Error in AJAX: "+json.error);	
       			}				
  		  },
  		  error: function(e){   errorOnline.error("Error in AJAX");	
  		  					}
  		});	
}

function checkComandaJS(){
	var comanda = window.localStorage.getItem("comanda");
	if(comanda!= null && comanda != 'undefined'){
		var hora = $("#comandahora").val();
		var dia = $("#dia").val();
		if(hora==''){
			alertOnline.alertes(initParams.txtfaltahora);
			return;
		}
		var adomicili =false;
		if($("#adomicili").is(':checked')){
			adomicili=true;
		}
		
		var address = $("#comandaddress").val();
		if(address==''){
			alertOnline.alertes(initParams.txtcheckaddress);
			return;
		}
		address =  address.replace(/\n/g, "");
		$("#chargeBar").show();
		var data ="idComanda="+comanda+"&dia="+dia+"&hora="+hora+"&aDomicili="+adomicili+"&address="+address;
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/checkComanda.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  	if(json==null || json.error!=null){
	       				$("#errorsajaxlabel").text(json.error);
	       				$("#errorsajax").show();
	       			}else{
	       				if(json.alertLoged!=null){
	       					alertOnline.alertes(json.alertLoged);			       					
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				if(json.alerta!=null){
	       					alertOnline.alertes(json.alerta);	       					
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				
	       				if(json.comandaOK !=null ){
	       					alertOnline.alertes(json.comandaOK);	       					
	       					$("#chargeBar").hide();	       					
	       					$("#checkPromocionsDisponibles").show();
	       					payComanda();
	       					return;
	       				}
	       						       			
	       			}	  			  		  			  		  			  	
	  			  $("#chargeBar").hide();
	  		  },
	  		  error: function(e){   errorOnline.error("Error in AJAX");	
	  		  					}
	  		});
	}
}
$(function(){
	
	$( ".draggable" ).dblclick(function() {
		
		var dragBeguda =$(this).clone();
		dragBeguda.appendTo("#slider");
		
		dragBeguda.animate({
							    width: "90%",
							    opacity: 0.4, 
							    marginLeft: "0.6in",
							    fontSize: "3em",
							    borderWidth: "10px",
							    left: "+=250px"
	  						}, 1800,function() {
	  								var id = $(this).attr("id");
	  								var tipus = $(this).attr("title");
	  								addingBegudaMangaer(id,tipus);
	      							$(this).css("visiblity","hidden");
	      							$(this).css("display","none");
	    					});
	  	
	});
	
	function addingBegudaMangaer(id,tipus){
			if(window.promoBeguda == null || window.promoBeguda.promo!="true" ){	
				saveBegudaToComanda(id,false);
			}else{
				if(window.promoBeguda.numBegudesAdded < window.promoBeguda.numBegudes){
	    			if(tipus!= window.promoBeguda.tipusBeguda){
	    				alertOnline.alertes(initParams.txtBegudaNoPromocio);	    				
	    			}
	    			//Afegim beguda al contador
	    			var n = window.promoBeguda.numBegudesAdded;
	    			window.promoBeguda.numBegudesAdded = parseInt(n) +1;
	    			saveBegudaToComanda(id,true);
	    		}else{
	    			alertOnline.alertes(initParams.txtNoMoreDrinksToAddinPromo);	    			
	    		}
			}
	}
	
	function saveBegudaToComanda(idBeguda,promo){
		
		var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val()+"&promo="+promo;
      	$.ajax({
      		  type: "POST",
      		  url: '/onlineBot/comanda/ajaxLoadBeguda.action',
      		  dataType: 'json',
      		  data: data,
      		  success: function(json){	
      			  if(json!=null && json.error!=null){           				
           				errorOnline.error("Error in AJAX: "+json.error);	
           			}else{
           				if(json.alerta!=null){
           					alertOnline.alertes(json.alerta);
           					
           				}else{
           					var numBegudes=0;
           					var numBegudesPromo=0;           					
           					var preuBegudes = 0.0;
           					var html="";
           					$.each(json, function(index, value) { 
           					 	
           					 	if(value.promo==true || value.promo=='true'){
           					 		numBegudesPromo=numBegudesPromo+value.numBegudes;
           					 		
           					 	}else{
           					 		numBegudes= numBegudes+value.numBegudes;
           					 	preuBegudes=  parseFloat(preuBegudes) + parseFloat(value.beguda.preu);
           						}
           						html=html+"<div class='selector'>"+value.beguda.nom+"<br>"+value.beguda.preu+"</div>";
           						
           					});
           				
           					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
           					window.localStorage.setItem("comanda.numbegudes",numBegudes);
           					window.localStorage.setItem("comanda.beguda.preu",preuBegudes.toFixed(2));
           					$("#begudes").html(html);
           					$("#numbegudes").text(numBegudes);
           					$("#numbegudespromo").text(numBegudesPromo);
           					var preuComanda = window.localStorage.getItem("comanda.preu");
           					var preuFinal = parseFloat(preuComanda) + parseFloat(preuBegudes);
           					$("#preu").text(preuFinal.toFixed(2));
           				}
           			}				
      		  },
      		  error: function(e){  errorOnline.error("Error in AJAX");	
      		  					}
      		});	
	}
	
	$( ".draggable" ).draggable({
		 helper:'clone',		
		 start: function(event,ui){				
			 $("#slider").css("height","500px");	  	
	    }
	});
	$( "#droppable" ).droppable({
	    drop: function( event, ui ){
	    	var item_id = ui.draggable.attr("id"); 
	    	var  tipus = ui.draggable.attr("title");
	    	addingBegudaMangaer(item_id,tipus);	    		    		        
	    }
	});
});

//---------------------------------------------------------------------------------------------------------------------
    Calendar.setup({
        inputField    	:    "dia",      // id del campo de texto
        ifFormat       	:    "%Y-%m-%d",          // formato de la fecha, cuando se escriba en el campo de texto
        button         	:    "llencadorData1",          // el id del botón que lanzará el calendario
        locale 		   	:    "ca_ES"
    });
//---------------------------------------------------------------------------------------------------------------------
new Address.addressValidation();


$("#checkPromocionsDisponibles").show();
$("#deletePromoApplied").hide();


var sudoSlider = $("#slider").sudoSlider({
    autowidth:false,
    slideCount:3,
    continuous:true
});

function checkPromocionsDisponibles(){
	var comandaId = window.localStorage.getItem("comanda");
	if(comandaId!= null && comandaId != 'undefined'){

		var data ="idComanda="+comandaId;
		$("#chargeBar").show();
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/checkComandaPromos.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  	if(json!=null && json.error!=null){	       				
	       			 errorOnline.error("Error in AJAX: "+json.error);	
	       			}else{
	       				if(json.alertLoged!=null){
	       					alertOnline.alertes(json.alertLoged);	       					
	       					$("#chargeBar").hide();
	       					return;
	       				}
	       				$("#dialog_promo ul").html("");
	       				if(json.promosNumComanda !=null ){
	       					//Hi ha prom del tipus numero de comanda
	       					fillPromos(json.promosNumComanda[0]);
	       			
	       				}
	       				if(json.promosAPartirDe !=null ){
	       					//Hi ha prom del tipus a partir de
	       					fillPromos(json.promosAPartirDe[0]);
	       					
	       				}
	       						       			
	       			}	  			  		  			  		  			  	
	  			  $("#chargeBar").hide();
	  		  },
	  		  error: function(e){  errorOnline.error("Error in AJAX");	
	  		  					}
	  		});
	}	
	
}

function fillPromos(json){
	$.each(json, function(index,item){
		if(item.numBegudes!=null && item.numBegudes!= 'undefined' && json.numBegudes!= "0"){
			//Promocio de begudes
			var liToAppend = "<li><a href='#' onclick=\"addPromoBeguda('"+item.numBegudes+"','"+item.tipusBeguda+"')\" >'Te un descompte per escollir "+item.numBegudes +" begudes de tipus "+item.tipusBeguda+"</a>";
			$("#dialog_promo ul").append(liToAppend);
			
		
		}
		if(item.descompteImport!=null && item.descompteImport!= 'undefined' && item.descompteImport!="0"){			
			//promocio descompte de pasta
			var liToAppend = "<li><a href='#' onclick=\"addPromoImport('"+item.descompteImport+"','"+item.tipuDescompte+"')\" >'Te un descompte de  "+item.descompteImport +" en "+item.tipuDescompte+"</a>";
			$("#dialog_promo ul").append(liToAppend);

		}
	});		
	
}

function addPromoBeguda(nbegudes, tipusBeguda){
	//La idea es obrir un div on es pugui arrastrar una beguda
	window.localStorage.setItem("comanda.promo.beguda","true");
	window.localStorage.setItem("comanda.promo.nBegudes.total",nbegudes);
	window.localStorage.setItem("comanda.promo.beguda.tipus",tipusBeguda);
	
	window.promoBeguda= {promo : "true"};
	window.promoBeguda.numBegudesAdded=0;
	window.promoBeguda.numBegudes=parseInt(nbegudes);
	window.promoBeguda.tipusBeguda=tipusBeguda;
	
	$("#checkPromocionsDisponibles").hide();
	$("#deletePromoApplied").show();
	closeDialogPromos();
	alertOnline.alertes(initParams.txtAddDrinkstoBox);	
	
}

//Inicialitzem si tenia una promo de begudes
function initPromoBegudaFromStorage(){
	var isPromo =  window.localStorage.getItem("comanda.promo.beguda");
	if(isPromo=='undefined' || isPromo==null){
		window.promoBeguda=null;	
	}else{
		
		var nbegudes= window.localStorage.getItem("comanda.promo.nBegudes.total");	
		var tipoBeguda = window.localStorage.getItem("comanda.promo.beguda.tipus");
		if(nbegudes!='undefined' && nbegudes!=null && tipoBeguda!='undefined' && tipoBeguda != null){
			addPromoBeguda(nbegudes, tipoBeguda);
			var nbegudesAdded = window.localStorage.getItem("comanda.promo.nBegudes.added");
			window.promoBeguda.numBegudesAdded = nbegudesAdded;
		}			
	}
}



//Inicialitzem si tenia una promo de descompte
function initPromoDescompteFromStorage(){
	var isPromo =  window.localStorage.getItem("comanda.promo.descompte");
	if(isPromo=='undefined' || isPromo==null){
		window.promoDescompte=null;	
	}else{
		
		var importe= window.localStorage.getItem("comanda.promo.descompte.import");	
		var tipoDescompte = window.localStorage.getItem("comanda.promo.descompte.tipus");
		if(importe!='undefined' && importe!=null && tipoDescompte!='undefined' && tipoDescompte != null){
			addPromoImport(importe, tipoDescompte);		
		}			
	}
}

function addPromoImport(importDescompte, tipusDescompte){
	
	//La idea és afegir el descompte al div d'info de la comanda
	window.localStorage.setItem("comanda.promo.descompte","true");
	window.localStorage.setItem("comanda.promo.descompte.import",importDescompte);
	window.localStorage.setItem("comanda.promo.descompte.tipus",tipusDescompte);
	
	window.promoDescompte= {promo : "true"};
	window.promoDescompte.importe= importDescompte;
	window.promoDescompte.tipus=tipusDescompte;
	
	//Actualitzem les dadesde la cart
	var preuPlats = window.localStorage.getItem("comanda.preu");
	alert(preuPlats)
	var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
	var preu =  parseFloat(preuPlats)+ parseFloat(preuBegudes);
	
	if(tipusDescompte=='1'){
		var preuF = parseFloat(preu)*((100-parseFloat(importDescompte))/100);
		$("#preu").text(preu+" (-"+importDescompte+" %) ="+parseFloat(preuF).toFixed(2));
	}else{
		var preuF = parseFloat(preu)-parseFloat(importDescompte);
		$("#preu").text(preu+" (-"+importDescompte+" Euros) ="+parseFloat(preuF).toFixed(2));
	}
	
	$("#checkPromocionsDisponibles").hide();
	$("#deletePromoApplied").show();
	closeDialogPromos();
	
}

function deletePromoApplied(){
 
	var isPromoBeguda = window.localStorage.getItem("comanda.promo.beguda");
	var isPromoDescompte = window.localStorage.getItem("comanda.promo.descompte");
	
	if(isPromoDescompte!='undefined' && isPromoDescompte!=null){
		
		window.localStorage.removeItem("comanda.promo.descompte");
		window.localStorage.removeItem("comanda.promo.descompte.import");
		window.localStorage.removeItem("comanda.promo.descompte.tipus");
		
		window.promoDescompte=null;
		
		
		var preuPlats = window.localStorage.getItem("comanda.preu");
		var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
		var preu =  parseFloat(preuPlats)+ parseFloat(preuBegudes);
		
		$("#preu").text(preu);
		
	}
	if(isPromoBeguda!='undefined' && isPromoBeguda!=null){
		
		window.localStorage.removeItem("comanda.promo.beguda");
		window.localStorage.removeItem("comanda.promo.nBegudes.total");
		window.localStorage.removeItem("comanda.promo.beguda.tipus");
		
		window.promoBeguda= null;
		
		$("#numbegudespromo").text("0");
		
		deleteAjaxBegudesPromo();
	}
	$("#deletePromoApplied").hide();
	alertOnline.alertes(initParams.txtpromodeleted);

}

function deleteAjaxBegudesPromo(){
	var comandaId = window.localStorage.getItem("comanda");
	if(comandaId!= null && comandaId != 'undefined'){

		var data ="idComanda="+comandaId;

	  	$.ajax({
	  		  type: "POST",
	  		  url: '/onlineBot/comanda/deleteBegudesPromo.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  	if(json!=null && json.error!=null){	       					       				
	       				errorOnline.error("Error in AJAX: "+json.error); 
	       			}  			  		  			  		  			  	
	  			 
	  		  },
	  		  error: function(e){   errorOnline.error("Error in AJAX"); 		
	  		  					}
	  		});
	}	
}

function openDialogPromos(){
	 $("#dialog_promo").dialog("open"); 
}

function closeDialogPromos(){
	 $("#dialog_promo").dialog("close"); 
}

$("#dialog_promo").dialog( { autoOpen: false,
	  modal: true,
	  position: 'center',
	  draggable: true,
	  height: 390,
	  width: 900,		
	  open: function(event, ui) { 
		 //carrega la taula del dialog
		checkPromocionsDisponibles(); 
		
		$('#dialog_promo').css('overflow', 'hidden');
		

	 }
});

$(document).ready(function() {
	initPromoBegudaFromStorage();
	initPromoDescompteFromStorage();
});