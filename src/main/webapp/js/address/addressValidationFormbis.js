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

    map.setCenter(geolocation);
    marker.setMap(map);  
    
}

var Addressbis={
				
		 addressValidation : function(){
			var self =this;
			self._init();
			
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

Addressbis.addressValidation.prototype._init= function(){
	this._carrer= document.getElementById("carrerbis");
	this._numcarrer = document.getElementById("numcarrerbis");
	this._codi= document.getElementById("codibis");
	this._poble= document.getElementById("poblebis");
	this._mapdiv= $("#map_canvasbis");	
	this._infoLabel= $("#addressOK");
}

Addressbis.addressValidation.prototype.checkAdd = function(){
	var address = $("#carrerbis").val()+","+$("#codibis").val()+", Girona, Spain";
    var addressToSave = $("#carrerbis").val()+"-"+$("#codibis").val()
	if($("#carrerbis").val() == '' || $("#codibis").val() == '' ){
		return false;
	}
	
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
                 
            	 $("#map_canvasbis").show();
                 Map("map_canvasbis", results[0].geometry.location);

                 //POsem address al form
                 $("#comandaddressbis").val(addressToSave);
                 $("#addressOKbis").text("OK Address");
             }
    	 }else{
    		 $("#addressOKbis").text("KO Address");
    	 }
             
    	
    });
}


      

   