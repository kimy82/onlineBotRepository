///////////////////////////////////
//variables per textos en locale
var initTableParams=null ;
function InitTableParams(txtdadesCargades){		
	this.txtdadesCargades=txtdadesCargades;
}

//Inicialitza el calendari
$(document).ready(function() {
	now=new Date();
	$("#config_rest").hide();
	selectedDate=new Date();
    document.getElementById('div_calendar').innerHTML='';
	loadCalendar("ca");		
	var txtDia =now.getDate();    
	clickDay(txtDia);
	
});

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


function loadHoraObertura(id){
	
	if($("#"+id).hasClass("notcheck")){
		$("#"+id).removeClass("notcheck");
		$("#"+id).addClass("check");
	}
	if($("#"+id).hasClass("check")){
		$("#"+id).removeClass("notcheck");
	}	
}


//Funcio que s'executa quan es selecciona un restaurant del select multiple
function loadMotersAndConfig(idRestaurant){
	
	 var dia = $("#selectedDia").val();
	 data ="id="+idRestaurant+"&dia="+dia;
		$.ajax({
			  type: "POST",
			  url: '/'+context+'/admin/loadMotersAndConfig.action',
			  dataType: 'json',
			  data: data,
			  success: function(json){	
				  if(json!=null && json.error!=null){
	   				$("#errorsajaxlabel").text(json.error);
	   				$("#errorsajax").show();
	   			}else{	   				
	   			
					if(json.obert==true){
						$("#obert").prop('checked', true);
					}else{
						$("#obert").prop('checked', false);
					}					
					resetHores();					
					if(json.hores!=null && json.hores!=''){
						var arrayhores= json.hores.split("|")
						$.each(arrayhores, function(i,item){
							if(item!=''){
								loadHoraObertura(item);
							}
						});
						$("#horesRestaurant").val(json.hores);
					}
						
					alert(initTableParams.txtdadesCargades);
	   			}				
			  },
			  error: function(e){   $("#errorsajaxlabel").text("La sessió pot haver caducat!!");
									$("#errorsajax").show();  		
			  					}
			});
}

function pad(str,ch,len) { 
    if (null==str) str="";
    var ps='';
    for(var i=0; i<Math.abs(len); i++) {
		  ps+=ch;
    }
    if (len>0) {
      var concat = (str+ps);
      var max = Math.max(str.length, len);
      return concat.substring(0,max);
    }else {
      var concat = (ps+str);
      var ini = concat.length+len;
      var fin = concat.length;
      return concat.substring( ini, fin);
    }
}

//Variable que conte el calendari
var datepicker=null;




		function loadCalendar(lang){
			var local={
					days: ["Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"],
					daysShort: ["diu", "dll", "dim", "dix", "dij", "div", "dis", "diu"],
					daysMin: ["diu", "dll", "dim", "dix", "dij", "div", "dis", "diu"],
					months: ["Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"],
					monthsShort: ["Gen", "Feb", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Des"],
					weekMin: "Set"
				};
			if(lang=="es"){
				local= {
					days: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
					daysShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sab", "dom"],
					daysMin: ["dom", "lun", "mar", "mié", "jue", "vie", "sab", "dom"],
					months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
					monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
					weekMin: "Sem"
				};
			}

         var year = now.getFullYear();
         var month = now.getMonth()+1;

            var oo =[];// eval('hashFiest.p'+year);
       
            if (oo!=null) oo=oo.slice(0);
            else          oo== [];

            oo=[];
            var hoy = new Date();
            var hoyYear =hoy.getFullYear();
            var hoyMonth =hoy.getMonth()+1;
            var hoyDay =hoy.getDate();    
            var hoy = hoyYear+'-'+pad(hoyMonth,'0',-2)+'-'+pad(hoyDay,'0',-2);

           

			$('#div_calendar').DatePicker({
					flat: true,
					dateOk: [hoy],
					dateNook:eval([""]),
					dateEsp: oo,
					current: year+'-'+pad(month,'0',-2)+'-01',
					format: 'Y-m-d',
					calendars: 1,
					mode: 'multiple',
					extraHeight:1,
					extraWidth:1,
					starts: 1,				
					locale:local
				});
			
			
				
					
             $(".datepickerDays tr td").addClass("hand_cursor").click(function() {
            	 	
                     $(".datepickerDays tr td").removeClass("border_selected");
                     $(".datepickerDays tr td").removeClass("green");                     
                     if(! $(this).hasClass("datepickerNotInMonth")){
                    	 $(this).addClass("border_selected");
                         clickDay($("span",this).html());	 
                     }else{                    	 
                    	 var dia = $("span",this).html();
                    	 if(parseInt(dia)<15){
                    		 nextYear(lang);
                    	 }else{                    		
                    		 previousYear(lang);
                    	 }
                     }                    
              });
		}
		
		function clickDay(txtDia) {
           var year = now.getFullYear();
           var month = now.getMonth()+1;
          
           selectedDate=new Date(year, month, txtDia, 0, 0, 0, 0);                             
           var ddmmyyyy = txtDia+'-'+month+'-'+year;

           $("#selectedDia").val(ddmmyyyy);
           $("#datePicked").text(ddmmyyyy);
           $("#config_rest").show('slow');          							
                                               
		}

//JS BUTTONS
ButtonPanel = Ext.extend(Ext.Panel, {
	id: 'panelb',
  layout:'table',
  defaultType: 'button',
  baseCls: 'x-plain',
  cls: 'btn-panel',
  renderTo : 'plantillabut',
  menu: undefined,
  split: false,

  layoutConfig: {
      columns:3
  },
  constructor: function(desc, buttons){
      // apply test configs
      for(var i = 0, b; b = buttons[i]; i++){
          b.menu = this.menu;
          b.enableToggle = this.enableToggle;
          b.split = this.split;
          b.arrowAlign = this.arrowAlign;
      }
      var items = [{
          xtype: 'box',
          autoEl: {tag: 'h3', html: desc, style:"padding:15px 0 3px;"},
          colspan: 3
      }].concat(buttons);

      ButtonPanel.superclass.constructor.call(this, {
          items: items
      });
  }
});



function nextYear(lang) {
	document.getElementById('div_calendar').innerHTML='';
	now.setDate(1);
	now.setMonth(now.getMonth() + 1);
	loadCalendar(lang);
}

function previousYear(lang) {
	document.getElementById('div_calendar').innerHTML='';
	now.setDate(1);	
	now.setMonth(now.getMonth() - 1);
	loadCalendar(lang);
}
