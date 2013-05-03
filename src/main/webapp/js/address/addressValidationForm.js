function Map(elementId, geolocation) {
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
		$("#map_canvas").show();
		map.setCenter(geolocation);
		marker.setMap(map); 

		//POsem address al form
        $("#comandaddress").val(Address.addressValidation.prototype.addressToSave);
        $("#addressOK").text(Address.addressValidation.prototype._addressOK);		
	}else{
		$("#addressOK").text(Address.addressValidation.prototype._addressKO);
	}
    
}

var Address={
				
		 addressValidation : function(addressOK,addressKO){
			var self =this;
			self._init(addressOK,addressKO);
			
			var button = document.getElementById("checkAdd");
			button.onclick=self.checkAdd;
			
		}			
}

Address.addressValidation.prototype._carrer=null;
Address.addressValidation.prototype._numcarrer=null;
Address.addressValidation.prototype._codi=null;
Address.addressValidation.prototype._poble=null;
Address.addressValidation.prototype._mapdiv=null;
Address.addressValidation.prototype._button=null;
Address.addressValidation.prototype._infoLabel=null;
Address.addressValidation.prototype._addressOK=null;
Address.addressValidation.prototype._addressKO=null;
Address.addressValidation.prototype.addressToSave=null;

Address.addressValidation.prototype._init= function(addressOK,addressKO){
	this._carrer= document.getElementById("carrer");
	this._numcarrer = document.getElementById("numcarrer");
	this._codi= document.getElementById("codi");
	this._poble= document.getElementById("poble");
	this._mapdiv= $("#map_canvas");	
	this._infoLabel= $("#addressOK");
	Address.addressValidation.prototype._addressOK=addressOK;
	Address.addressValidation.prototype._addressKO=addressKO;
	Address.addressValidation.prototype.addressToSave="";
}

Address.addressValidation.prototype.checkAdd = function(){
	var address = $("#carrer").val()+","+$("#codi").val()+", Girona, Spain";
	var codiPostal= $("#codi").val();
	if(codiPostal!='17001' && codiPostal!='17002' && codiPostal!='17003' && codiPostal!='17004' && codiPostal!='17005' && codiPostal!='17006' && codiPostal!='17007'){
		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
		 return false;
	}
	
	
    var addressToSave = $("#carrer").val()+"-"+codiPostal;
	if($("#carrer").val() == '' || codiPostal == '' ){
		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
		 return false;
	}
	Address.addressValidation.prototype.addressToSave=addressToSave;
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
                 
            	
                 Map("map_canvas", results[0].geometry.location);

                 
             }else{
            		$("#addressOK").text(Address.addressValidation.prototype._addressKO);
             }
    	 }else{
    		 $("#addressOK").text(Address.addressValidation.prototype._addressKO);
    	 }
             
    	
    });
}


      

   