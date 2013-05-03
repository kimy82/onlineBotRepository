function getDateToday(){
	var d=new Date();
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}

function goToRestaurantMenu(id){
	
	var data = window.localStorage.getItem("comanda.data");
	if(data=='undefined' || data==null) data=getDateToday();
	window.location.href="/"+context+"/comanda/Welcome.action?restaurantId="+id+"&data="+data;
}

