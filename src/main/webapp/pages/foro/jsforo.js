///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtCommentSaved, txtComentDeleted, txtconfirmVot, txtvotguardat){		
	
	this.txtCommentSaved = txtCommentSaved;
	this.txtComentDeleted = txtComentDeleted;
	this.txtconfirmVot = txtconfirmVot;
	this.txtvotguardat = txtvotguardat;
}
var actualVot=0;
function starManager(num){
	
	if((Math.abs(num-actualVot))>1){
		return;
	}
	
	if(num==1){
		//first star
		if(actualVot==num){			
			firstStar(false);
		}else{
			firstStar(true);
		}
	}else if(num==2){
		//second star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
		
	}else if(num==3){		
		//third star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
		
	}else if(num==4){
		//fourth star
		if(actualVot==num){			
			otherStar(false,num);
		}else{
			otherStar(true,num);
		}
	}else if(num==5){
		//fiveth star
		if(actualVot==num){			
			lastStar(false);
		}else{
			lastStar(true);
		}
	}
}

function firstStar(addRemove){
	
	if(addRemove){
		actualVot=1;
		$("#star1").attr("src","/onlineBot/images/star.jpg");	
	}else{		
		actualVot=0;
		$("#star1").attr("src","/onlineBot/images/star0.jpg");
	}	
}
function otherStar(addRemove,num){
		
	if(addRemove){
		actualVot=num;
		$("#star"+num).attr("src","/onlineBot/images/star.jpg");	
	}else{
		actualVot=num-1;
		$("#star"+num).attr("src","/onlineBot/images/star0.jpg");	
	}	
}
function lastStar(addRemove){
	
	if(addRemove){
		actualVot=5;
		$("#star5").attr("src","/onlineBot/images/star.jpg");	
	}else{
		actualVot=4;
		$("#star5").attr("src","/onlineBot/images/star0.jpg");	
	}	
}

function votaPlatDialog(){
	confirmOnline.confirm(initParams.txtconfirmVot,votaPlat);
}

function votaPlat(){
	
	var idPlat = $("#idPlat").val();
	var data = "idPlat=" + idPlat + "&punctuacio=" + actualVot;
	
	$.ajax({
				type : "POST",
				url : '/onlineBot/foro/ajaxSavePunctuacioForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.error(json.error);
					} else {							
						alertOnline.alertes(initParams.txtvotguardat);		
						$("#saveVotButton").attr("disabled","disabled");
						confirmOnline.close();
					}
				},
				error : function(e) {						
					errorOnline.error("Error in AJAX");
				}
			});
}

function saveComment() {
		var idPlat = $("#idPlat").val();
		var comment = $("#newComment").val();
		var data = "idPlat=" + idPlat + "&comment=" + comment;
		
		$.ajax({
					type : "POST",
					url : '/onlineBot/foro/ajaxSaveCommentForPlat.action',
					dataType : 'json',
					data : data,
					success : function(json) {
						if (json != null && json.error != null) {
							errorOnline.error(json.error);
						} else {							
							alertOnline.alertes(initParams.txtCommentSaved);	
							insertTR(idPlat,comment,json.idComment);
						}
					},
					error : function(e) {						
						errorOnline.error("Error in AJAX");
					}
				});
	}

function deleteComment(id) {
	var idPlat = $("#idPlat").val();	
	var data = "idPlat=" + idPlat + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/onlineBot/foro/ajaxDeleteCommentForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.error(json.error);
					} else {							
						alertOnline.alertes(initParams.txtComentDeleted);	
						deleteTR(id);
					}
				},
				error : function(e) {						
					errorOnline.error("Error in AJAX");
				}
			});
}

function insertTR(idPlat,comment,idComment){
	
		var table=document.getElementById("comments_tbl");
		var row=table.insertRow(-1);
		row.setAttribute("id",idComment);
		var cell1=row.insertCell(0);
		var cell2=row.insertCell(1);
		cell1.innerHTML=comment;
		cell2.innerHTML="<a href='#' onclick='deleteComment("+idComment+")' ><img src='/onlineBot/images/delete.png' /> </a>";
			
}

function deleteTR(id){
	
	var table=document.getElementById("comments_tbl");
	var rows = table.rows;
	for (var i=0; i<rows.length; i++) {
		var tr = rows[i];
		if(tr.getAttribute("id")==id)
			table.deleteRow(i);
	}
		
}