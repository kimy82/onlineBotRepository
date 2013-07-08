function getDateToday(){
	var d=new Date();
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
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
			var data = window.localStorage.getItem("comanda.data");
			if(data==null) data=getDateToday();
			window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
			
		}catch(error){
			console.log(error);
		}
	},
}	


function getHoraDosPunts(hora){
	
	var horaSplited	=hora.split(/(?!$)/);
	var horaDospunts = hora;
	if(horaSplited.length==4){
		horaDospunts = horaSplited[0]+horaSplited[1]+":"+horaSplited[2]+horaSplited[3];
	}
	
	return horaDospunts;
}

function getNextHora(hora){
	var horaSplited	=hora.split(/(?!$)/);
	var nextHoraDospunts = hora;
	if(horaSplited.length==4){
		
		nextHoraDospunts = horaSplited[0]+horaSplited[1]+":"+horaSplited[2]+horaSplited[3];
		
		if(horaSplited[2]=="0" && horaSplited[3]=="0"){
			nextHoraDospunts = horaSplited[0]+horaSplited[1]+":3"+horaSplited[3];
		}else{
			var h =  horaSplited[0]+horaSplited[1];
			var hInt = parseInt(horaSplited[0]+horaSplited[1])+1;
			nextHoraDospunts=hInt+":00"
		}
		
	}	
	return nextHoraDospunts;	
}

function getAddressFormat(address){
	
	var addressArray = address.split("-");
	var addressFormated = address;
	if(addressArray.length==5){
		
		addressFormated = addressArray[0]+", Num."+addressArray[1]+", Pis. "+addressArray[2]+", P. "+addressArray[3]+", C.P. "+addressArray[4];
		
	}
	return addressFormated;
	
}

menuRestaurantAction._init();