function Mapbis(elementId, geolocation) {
    var myOptions = {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById(elementId), myOptions);
    var marker = new google.maps.Marker({
        position: geolocation,
        title:"Here is your address!"
    });
var addressInside=false;
    
    if(41.956389>geolocation.lat() && geolocation.lat()>41.946304 && 2.810225<geolocation.lng()&& geolocation.lng()<2.826877){
    	addressInside=true;
    }else if(41.973301>geolocation.lat() && geolocation.lat()>41.956836 && 2.805161<geolocation.lng()&& geolocation.lng()<2.834516){
    	addressInside=true;
    }else if(41.973557>geolocation.lat() && geolocation.lat()>41.958942 && 2.802415<geolocation.lng()&& geolocation.lng()<2.805591){
    	addressInside=true;
    }else if(41.974067>geolocation.lat() && geolocation.lat()>41.960283 && 2.799497<geolocation.lng()&& geolocation.lng()<2.802501){
    	addressInside=true;
    }else if(41.979363>geolocation.lat() && geolocation.lat()>41.964559 && 2.778726<geolocation.lng()&& geolocation.lng()<2.799926){
    	addressInside=true;
    }else if(41.987875>geolocation.lat() && geolocation.lat()>41.972562 && 2.799883<geolocation.lng()&& geolocation.lng()<2.835417){
    	addressInside=true;
    }else if(41.999868>geolocation.lat() && geolocation.lat()>41.988641 && 2.797136<geolocation.lng()&& geolocation.lng()<2.839537){
    	addressInside=true;
    }else if(42.014829>geolocation.lat() && geolocation.lat()>41.998247 && 2.809968<geolocation.lng()&& geolocation.lng()<2.828164){
    	addressInside=true;
    }else if(42.021142>geolocation.lat() && geolocation.lat()>42.014064 && 2.813487<geolocation.lng()&& geolocation.lng()<2.818294){
    	addressInside=true;
    }else if(42.018273>geolocation.lat() && geolocation.lat()>42.014128 && 2.817521<geolocation.lng()&& geolocation.lng()<2.820783){
    	addressInside=true;
    }else{
    	addressInside=false;
    }
    	
    	
	if(addressInside==true){    	 
		$("#map_canvasbis").show();
		map.setCenter(geolocation);
		marker.setMap(map); 

		//POsem address al form
        $("#comandaddressbis").val(Addressbis.addressValidation.prototype.addressToSave);
        $("#addressOKbis").text(Address.addressValidation.prototype._addressOK);		
	}else{
		$("#addressOKbis").text(Address.addressValidation.prototype._addressKO);
	}
  
    
}

var Addressbis={
				
		 addressValidation : function(addressOK,addressKO){
			var self =this;
			self._init(addressOK,addressKO);
			
			var button = document.getElementById("checkAddbis");
			button.onclick=self.checkAdd;
			
		}			
}

Addressbis.addressValidation.prototype._carrer=null;
Addressbis.addressValidation.prototype._numcarrer=null;
Addressbis.addressValidation.prototype._codi=null;
Addressbis.addressValidation.prototype._poble=null;
Addressbis.addressValidation.prototype._mapdiv=null;
Addressbis.addressValidation.prototype._button=null;
Addressbis.addressValidation.prototype._infoLabel=null;
Addressbis.addressValidation.prototype._addressOK=null;
Addressbis.addressValidation.prototype._addressKO=null;
Addressbis.addressValidation.prototype.addressToSave=null;

Addressbis.addressValidation.prototype._init= function(addressOK,addressKO){
	this._carrer= document.getElementById("carrerbis");
	this._numcarrer = document.getElementById("numcarrerbis");
	this._codi= document.getElementById("codibis");
	this._poble= document.getElementById("poblebis");
	this._mapdiv= $("#map_canvasbis");	
	this._infoLabel= $("#addressOK");
	Addressbis.addressValidation.prototype._addressOK=addressOK;
	Addressbis.addressValidation.prototype._addressKO=addressKO;
	Addressbis.addressValidation.prototype.addressToSave="";
}


Addressbis.addressValidation.prototype.checkAdd = function(){
	
	var codiPostal= $("#codibis").val();
	var poble="Girona";
	if(codiPostal=="17190"){
		poble="Salt";
	}
	var address = $("#carrerbis").val()+","+$("#codibis").val()+", "+poble+", Spain";
	
	if(codiPostal!='17001' && codiPostal!='17002' && codiPostal!='17003' && codiPostal!='17004' && codiPostal!='17005' && codiPostal!='17006' && codiPostal!='17007'){
		 $("#addressOKbis").text(Addressbis.addressValidation.prototype._addressKO);
		 return false;
	}
    var addressToSave = $("#carrerbis").val()+"-"+$("#numcarrerbis").val()+"-"+$("#numpis").val()+"-"+$("#porta").val()+"-"+$("#codibis").val();
	if($("#carrerbis").val() == '' || $("#codibis").val() == '' ){
		 $("#addressOKbis").text(Addressbis.addressValidation.prototype._addressKO);
		 return false;
	}
    Addressbis.addressValidation.prototype.addressToSave=addressToSave;
	address = address.replace(/\n/g, "");
	var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'address': address }, function (results, status) {
		
    	 if (status == google.maps.GeocoderStatus.OK) {
    		// Get the formatted Google result
             var addressGoogle = results[0].formatted_address;
             numCommas = addressGoogle.match(/,/g).length;
             if (numCommas >= 2) {

                 // Replace the first comma found with a line-break
            	 addressGoogle = addressGoogle.replace(/, /, "\n");

                 // Remove USA from the address (remove this, if this is important to you)
            	 addressGoogle = addressGoogle.replace(/, USA$/, "");
                 
            	
            	 Mapbis("map_canvasbis", results[0].geometry.location);

                
             }else{
            		$("#addressOKbis").text(Address.addressValidation.prototype._addressKO);
             }
    	 }else{
    		 $("#addressOKbis").text("KO Address");
    	 }
             
    	
    });
}


      

   