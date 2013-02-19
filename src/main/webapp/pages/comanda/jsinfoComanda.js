///////////////////////////////////
//variables per textos en locale
var initParams=null ;

function InitParams(txtBegudaNoPromocio,txtNoMoreDrinksToAddinPromo,txtAddDrinkstoBox, 
					txtdescompteaplicat,txtpromodeleted, txtfaltahora, txtcheckaddress, 
					checkok, checkko,txtconfirm,txtproductes,txtproducte,txtnomesdomocili,
					txtbottreurepromo){		
		this.txtBegudaNoPromocio=txtBegudaNoPromocio;
		this.txtNoMoreDrinksToAddinPromo=txtNoMoreDrinksToAddinPromo;
		this.txtAddDrinkstoBox = txtAddDrinkstoBox;
		this.txtdescompteaplicat = txtdescompteaplicat;
		this.txtpromodeleted = txtpromodeleted;
		this.txtfaltahora = txtfaltahora;
		this.txtcheckaddress = txtcheckaddress;
		this.checkok = checkok;
		this.checkko = checkko;
		this.txtconfirm = txtconfirm;
		this.txtproductes = txtproductes;
		this.txtproducte = txtproducte;
		this.txtnomesdomocili = txtnomesdomocili;
		this.txtbottreurepromo = txtbottreurepromo;
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
$("#arecollir_div").hide();
$("#adomicili_div").hide();

function initNumPlats(){
}

function initNumBegudes(){
	var numbegudes = window.localStorage.getItem("comanda.numbegudes");
	if (numbegudes != 'undefined' && numbegudes != null) {
		$("#numbegudes").text(numbegudes);
	}	
}

function initAddress(){
	
	if(window.addressToLoad!=''){
		var arrayAddress = window.addressToLoad.split("-");
		$("#carrer").val(arrayAddress[0]);
		$("#codi").val(arrayAddress[1]);
		$("#comandaddress").val(window.addressToLoad);
		$("#checkAdd").click();
	}
	
}

function goToRestaurantMenu(id){
	var comanda = window.localStorage.getItem("comanda");
	var data = window.localStorage.getItem("comanda.data");
	if(comanda != 'undefined' && comanda != null){
		acceptComandaDialog();
	}else{
		window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+dataInicialComanda;
	}
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

function reloadHores(){
	var dia = $("#dia").val();
	var data = window.localStorage.setItem("comanda.data",dia);
	var comanda = window.localStorage.getItem("comanda");
	var data = "data="+dia+"&idComanda="+comanda;
	
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/comanda/loadHores.action',
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
	if($("#"+id).hasClass("notcheck")){
		return;
	}
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
				window.location.href="/"+context+"/payment/paymentEntry.action?"+data;
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
	
		 var preuPlat = $("#platpreu_"+id).text();
		 var preu = $("#preu").text();
		 var numPlatsAnt = window.localStorage.getItem("comanda.plat_"+id);
		 if(numPlatsAnt != 'undefined' && numPlatsAnt != null){
			 
			 var nPlatsAdded = parseInt(value);
			 $("#labelnum_"+id).text(parseInt(numPlatsAnt)+parseInt(value));
			 window.localStorage.setItem("comanda.plat_"+id,parseInt(numPlatsAnt)+parseInt(value));
			 
			 var preuToAdd = parseFloat(preuPlat)*nPlatsAdded;
			 var preuActual = $("#labelpreutotal_"+id).text();
			 var newPreu = preuToAdd+parseFloat(preuActual);
			 $("#labelpreutotal_"+id).text(parseFloat(newPreu).toFixed(2));
			 
			 var preuCom = parseFloat(preu)+preuToAdd;
			 $("#preu").text(parseFloat(preuCom).toFixed(2));
			 $("#labelpreutotalPromo").text(parseFloat(preuCom).toFixed(2));
			 
			 var preuPlats =  window.localStorage.getItem("comanda.preu");
			 window.localStorage.setItem("comanda.preu",parseFloat(parseFloat(preuPlats)+ parseFloat(preuToAdd)).toFixed(2));
			 
			 initPromoDescompteFromStorage();
			 //Guardem els plats afegits
			 savePlatToComanda(id,nPlatsAdded);
		
	}
}
function eliminaPlat(id){
	window.localStorage.setItem("comanda.plat_"+id,"0");
	 var n = $("#labelnum_"+id).text();
	 $("#labelnum_"+id).text("0");	
	 var preuTotalPlat = $("#labelpreutotal_"+id).text();
	 $("#labelpreutotal_"+id).text("0");
	 var preu =  $("#preu").text();
	 var preuComanda = parseFloat(preu)-parseFloat(preuTotalPlat);
	 $("#preu").text(parseFloat(preuComanda).toFixed(2));
	 $("#labelpreutotalPromo").text(parseFloat(preuComanda).toFixed(2));
	 initPromoDescompteFromStorage();
	 savePlatToComanda(id,-n); 
	 
}

function eliminaBeguda(id){
	 var n = $("#labelnum_b_"+id).text();
	 $("#labelnum_b_"+id).text("0");	
	 var preuTotalBeguda = $("#labelpreutotal_b_"+id).text();
	 $("#labelpreutotal_b_"+id).text("0");
	 var preu =  $("#preu").text();
	 var preuComanda = parseFloat(preu)-parseFloat(preuTotalBeguda);
	 $("#preu").text(parseFloat(preuComanda).toFixed(2));
	 $("#labelpreutotalPromo").text(parseFloat(preuComanda).toFixed(2));
	 saveBegudaToComanda(id,false,-n); 
}

function addDomicili(){
	 var preu = $("#preu").text();
	 var preuT = $("#labelpreutotalPromo").text();
	 $("#arecollir_div").hide('slow'); 
	if($("#adomicili").is(':checked')){		
		
		 $("#arecollir").attr('checked',false);		 
		 $("#transport_lb").text("40");
		 $("#preu").text(parseFloat(parseFloat(preu)+parseFloat(40)).toFixed(2));		 
		 $("#labelpreutotalPromo").text(parseFloat(parseFloat(preuT)+parseFloat(40)).toFixed(2));
		 initPromoDescompteFromStorage();
		 $("#adomicili_div").show('slow');
	}else{
		 $("#adomicili_div").hide('slow');
		 $("#transport_lb").text("0");
		 $("#preu").text(parseFloat(parseFloat(preu)-parseFloat(40)).toFixed(2));
		 $("#labelpreutotalPromo").text(parseFloat(parseFloat(preuT)-parseFloat(40)).toFixed(2));
		 initPromoDescompteFromStorage();
	}
}

function targeta(){
	
	$("#targetaContrarembols").val("targeta");
	$("#contrarembols").attr('checked',false);
	
}

function contrarembols(){
	$("#targetaContrarembols").val("contrarembols");
	$("#targeta").attr('checked',false);
	
}


function addRecollir(){
	$("#adomicili_div").hide('slow');
	
	
	
	if($("#arecollir").is(':checked')){		 		 
	
		$("#adomicili").attr('checked',false);
		var idcomanda= window.localStorage.getItem("comanda");
		var data ="idComanda="+idcomanda;
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/'+context+'/comanda/ajaxLoadInfoARecollir.action',
	  		  dataType: 'json',
	  		  data: data,
	  		  success: function(json){	
	  			  if(json!=null && json.error!=null){
	  				errorOnline.error("Error in AJAX: "+json.error);	
	       		  }else{
	       			  if(json.moreThanOne=='true' || json.moreThanOne==true){	       				  
	       				alertOnline.alertes(initParams.txtnomesdomocili);	 
	       			  }
	       			$("#targetaContrarembols").val("targeta");
	       			$("#address_restaurant").text(json.address);
	       			$("#arecollir_div").show('slow');
	       		  }				
	  		  },
	  		  error: function(e){   errorOnline.error("Error in AJAX");	
	  		  					}
	  		});	
		
		
	}else{
		$("#arecollir_div").hide('slow'); 
	}
}
function savePlatToComanda(idPlat,nPlats){
	var idcomanda= window.localStorage.getItem("comanda");
	var data ="idPlat="+idPlat+"&idComanda="+idcomanda+"&nplats="+nPlats;
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/comanda/ajaxLoadNumPlat.action',
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
		var address = $("#comandaddress").val();
		if($("#adomicili").is(':checked')){
			
			adomicili=true;			
			
			if(address==''){
				alertOnline.alertes(initParams.txtcheckaddress);
				return;
			}
			address =  address.replace(/\n/g, "");
		}
		
		var targeta =false;
		
		var targetaContrarembols = $("#targetaContrarembols").val();
		if(targetaContrarembols=='contrarembols'){
			
		}else if (targetaContrarembols=='targeta'){
			targeta=true;
		}else{
			targeta=true;
		}
		
		
		$("#chargeBar").show();
		var data ="idComanda="+comanda+"&dia="+dia+"&hora="+hora+"&aDomicili="+adomicili+"&targeta="+targeta+"&address="+address;
	  	$.ajax({
	  		  type: "POST",
	  		  url: '/'+context+'/comanda/checkComanda.action',
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
var func = $(function(){
	
	$( ".draggable" ).click(function() {
		
		var dragBeguda =$(this).clone();
		dragBeguda.appendTo("#preus");
		
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
				saveBegudaToComanda(id,false,1);
			}else{
				if(window.promoBeguda.numBegudesAdded < window.promoBeguda.numBegudes){
	    			if(tipus!= window.promoBeguda.tipusBeguda){
	    				alertOnline.alertes(initParams.txtBegudaNoPromocio);	
	    				return;
	    			}
	    			//Afegim beguda al contador
	    			var n = window.promoBeguda.numBegudesAdded;
	    			window.promoBeguda.numBegudesAdded = parseInt(n) +1;
	    			saveBegudaToComanda(id,true,1);
	    		}else{
	    			alertOnline.alertes(initParams.txtNoMoreDrinksToAddinPromo);	    			
	    		}
			}
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

function saveBegudaToComanda(idBeguda,promo,amount){
	
	var data ="idBeguda="+idBeguda+"&idComanda="+$("#idcomanda").val()+"&amount="+amount+"&promo="+promo;
  	$.ajax({
  		  type: "POST",
  		  url: '/'+context+'/comanda/ajaxLoadBeguda.action',
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
       					var begudes = json.begudes;
       					$('#beguda_'+idBeguda).remove();
       					var table=document.getElementById("order");
       					$.each(begudes, function(index, value){ 
       					 	           					 	
       						numBegudesPromo=numBegudesPromo+value.numBegudesPromo;

   					 		numBegudes= numBegudes+value.numBegudes;

   					 		preuBegudes=  parseFloat(preuBegudes) + parseFloat(value.beguda.preu)*value.numBegudes;           					
   					 		   					 		
   					 		if(value.beguda.id==idBeguda){
   					 			
   					 			if(value.numBegudes!=0){
	       					 		var row=table.insertRow(1);
	       					 		
	       					 		$(row).addClass("selector_pl");
	       					 		row.id="beguda_"+value.beguda.id;
	       					 		
	       					 		var cell1=row.insertCell(0);
	       					 		var cell2=row.insertCell(1);
	       					 		var cell3=row.insertCell(2);
	       					 		var cell4=row.insertCell(3);
	       					 		var cell5=row.insertCell(4);
	       					 		var cell6=row.insertCell(5);
	       					 		
	       					 		
	       					 		$(cell1).addClass("img_order");
	       					 		cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+idBeguda+"' />";
	       					 		
	       					 		
	       					 		$(cell2).addClass("descri");
	       					 		cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+value.beguda.descripcio+"</td>";
	       					 		
	       					 		
	       					 		$(cell3).addClass("preusun");
	       					 		cell3.innerHTML="<label id='begudapreu_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
	       					 		
	       					 		
	       					 		$(cell4).addClass("canti");
	       					 		cell4.innerHTML="<input class='mores' type='submit' onclick='saveBegudaToComanda("+value.beguda.id+",false,-1);' value='-'><label id='labelnum_b_"+value.beguda.id+"'>"+value.numBegudes+"</label><input class='mores' type='submit' onclick='saveBegudaToComanda("+value.beguda.id+",false,1);' value='+'>";
	       					 		
										
									$(cell5).addClass("total");
	       					 		cell5.innerHTML="<label id='labelpreutotal_b_"+value.beguda.id+"'>"+parseFloat(parseFloat(value.beguda.preu)*parseFloat(value.numBegudes)).toFixed(2)+"</label> &euro;";
	       					 		
	       					 		
	       					 		$(cell6).addClass("elimi");
	       					 		cell6.innerHTML="<input class='elimin' type='submit' onclick='eliminaBeguda("+value.beguda.id+")'  value='ELIMINAR'>";
   					 			}
   					 			if(value.numBegudesPromo!=0){
	   					 			var row=table.insertRow(1);
	       					 		
	       					 		
	   					 			$(row).addClass("selector_pl");
	       					 		row.id="beguda_p_"+value.beguda.id;
	       					 		var list = window.localStorage.getItem("comanda.promo.begudes.list");
	       					 		if(list == 'undefined' || list == null){
	       					 			window.localStorage.setItem("comanda.promo.begudes.list",value.beguda.id);
	       					 		}else{
	       					 			window.localStorage.setItem("comanda.promo.begudes.list",list+","+value.beguda.id);
	       					 		}
	       					 		
	       					 		var cell1=row.insertCell(0);
	       					 		var cell2=row.insertCell(1);
	       					 		var cell3=row.insertCell(2);
	       					 		var cell4=row.insertCell(3);
	       					 		var cell5=row.insertCell(4);
	       					 		var cell6=row.insertCell(5);
	       					 		
	       					 		
	       					 		$(cell1).addClass("img_order");
	       					 		cell1.innerHTML="<img width='114px' src='"+context+"/comanda/ImageAction.action?imageId="+idBeguda+"' />";
	       					 		
	       					 		
	       					 		$(cell2).addClass("descri");
	       					 		cell2.innerHTML="<span class='tit'>"+value.beguda.nom+"</span><br>"+value.beguda.descripcio+"</td>";
	       					 		
	       					 		
	       					 		$(cell3).addClass("preusun");
	       					 		cell3.innerHTML="<label id='begudapreu_p_"+value.beguda.id+"' >"+value.beguda.preu+"</label>&euro;";
	       					 		
	       					 		
	       					 		$(cell4).addClass("canti");
	       					 		cell4.innerHTML="<label id='labelnum_b_p_"+value.beguda.id+"'>"+value.numBegudesPromo+"</label>";
	       					 		
										
									$(cell5).addClass("total");
	       					 		cell5.innerHTML="<label id='labelpreutotal_b_p_"+value.beguda.id+"'>0</label> &euro;";
	       					 		
	       					 		
	       					 		$(cell6).addClass("elimi");
	       					 		cell6.innerHTML="<input class='elimin' type='submit' onclick='deletePromoApplied();'  value='"+initParams.txtbottreurepromo+"'>";
   					 			}
       						}       					 	   					 		      					 		           					       						 		       					 	
       					});
       					
       					window.localStorage.setItem("comanda.promo.nBegudes.added",numBegudesPromo);
       					window.localStorage.setItem("comanda.numbegudes",numBegudes);
       					window.localStorage.setItem("comanda.beguda.preu",preuBegudes.toFixed(2));           					           				
       					var preuComanda = window.localStorage.getItem("comanda.preu");
       					
       					var transport=0;
       					if($("#adomicili").is(':checked')){
       						transport=40;
       					}
       					
       					var preuFinal = parseFloat(preuComanda) + parseFloat(preuBegudes)+ parseFloat(transport);
       					$("#preu").text(preuFinal.toFixed(2));
       					$("#labelpreutotalPromo").text(preuFinal.toFixed(2));
       					initPromoDescompteFromStorage();
       			}   
  		  }
  		  },
  		  error: function(e){  errorOnline.error("Error in AJAX");	
  		  					}
  		});	
}

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
	  		  url: '/'+context+'/comanda/checkComandaPromos.action',
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
			$("#numbegudespromo").text(nbegudesAdded);
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
	
	var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
	if(preuBegudes=='undefined' || preuBegudes==null){
		preuBegudes=0.0;
		window.localStorage.setItem("comanda.beguda.preu","0.0");
	}
	var transport= 0;
	if($("#adomicili").is(':checked')){
		transport=40;
	}
	var preu =  parseFloat(preuPlats)+ parseFloat(preuBegudes)+ parseFloat(transport);
	
	if(tipusDescompte=='1' || tipusDescompte=='tant per cent %' ){
		var preuF = parseFloat(preu)*((100-parseFloat(importDescompte))/100);
		$("#promoImp").text("-"+importDescompte+" %");
		$("#labelpreutotalPromo").text(preu+" (-"+importDescompte+" %) ="+parseFloat(preuF).toFixed(2));
	}else{
		var preuF = parseFloat(preu)-parseFloat(importDescompte);
		$("#promoImp").text("-"+importDescompte+" Euros");
		$("#labelpreutotalPromo").text(preu+" (-"+importDescompte+" Euros) ="+parseFloat(preuF).toFixed(2));
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
		var transport= 0;
		if($("#adomicili").is(':checked')){
			transport=40;
		}
		
		var preu =  parseFloat(preuPlats)+ parseFloat(preuBegudes)+ parseFloat(transport);
		
		$("#promoImp").text("");
		$("#labelpreutotalPromo").text(preu.toFixed());
		
	}
	if(isPromoBeguda!='undefined' && isPromoBeguda!=null){
		
		window.localStorage.removeItem("comanda.promo.beguda");
		window.localStorage.removeItem("comanda.promo.nBegudes.total");
		window.localStorage.removeItem("comanda.promo.beguda.tipus");
		
		var list = window.localStorage.getItem("comanda.promo.begudes.list");
		if(list!='undefined' && list!=null){
			var array = list.split(",");
			$.each(array, function(index, value){
				$('#beguda_p_'+value).remove();			
			});
		}
		window.promoBeguda= null;
		
		$("#numbegudespromo").text("0");
		
		deleteAjaxBegudesPromo();
	}
	$("#deletePromoApplied").hide();
	$("#checkPromocionsDisponibles").show();
	alertOnline.alertes(initParams.txtpromodeleted);

}

function deleteAjaxBegudesPromo(){
	var comandaId = window.localStorage.getItem("comanda");
	if(comandaId!= null && comandaId != 'undefined'){

		var data ="idComanda="+comandaId;

	  	$.ajax({
	  		  type: "POST",
	  		  url: '/'+context+'/comanda/deleteBegudesPromo.action',
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
	
		var comanda = window.localStorage.getItem("comanda");		
		
		if(comanda != 'undefined' && comanda != null){
			$("#numComanda").text(comanda);
			
			
			var preu = window.localStorage.getItem("comanda.preu");
			if (preu != 'undefined' && preu != null) {
				var preuBegudes = window.localStorage.getItem("comanda.beguda.preu");
				if (preuBegudes != 'undefined' && preuBegudes != null) {
					preu =  parseFloat(preu) + parseFloat(preuBegudes);
				}
				$("#preu").text(parseFloat(preu).toFixed(2));			
				$("#labelpreutotalPromo").text(parseFloat(preu).toFixed(2));
			}

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
			$("#numProduct").text(initParams.txtconfirm+" 0 "+initParams.txtproductes);
		}
		
		initPromoBegudaFromStorage();
		initPromoDescompteFromStorage();
});