///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtCommentSaved, txtComentDeleted, txtconfirmVot, txtvotguardat, txtalertinsult){		
	
	this.txtCommentSaved = txtCommentSaved;
	this.txtComentDeleted = txtComentDeleted;
	this.txtconfirmVot = txtconfirmVot;
	this.txtvotguardat = txtvotguardat;
	this.txtalertinsult = txtalertinsult;
}
var actualVot=0;
function starManager(num){
if(num==1){
	//first star
	if(actualVot==num){			
		firstStar(false);
		otherStar(false,2);
		otherStar(false,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=0;
	}else{		
		firstStar(true);
		otherStar(false,2);
		otherStar(false,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=1;
	}
}else if(num==2){
	//second star
	if(actualVot==num){			
		otherStar(false,2);
		otherStar(false,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=1;
	}else{
		firstStar(true);
		otherStar(true,2);
		otherStar(false,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=2;
	}
	
}else if(num==3){		
	//third star
	if(actualVot==num){			
		otherStar(false,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=2;
	}else{
		firstStar(true);
		otherStar(true,2);
		otherStar(true,3);
		otherStar(false,4);
		otherStar(false,5);
		actualVot=3;
	}
	
}else if(num==4){
	//fourth star
	if(actualVot==num){			
		otherStar(false,4);		
		otherStar(false,5);
		actualVot=3;
	}else{
		firstStar(true);
		otherStar(true,2);
		otherStar(true,3);
		otherStar(true,4);		
		otherStar(false,5);
		actualVot=4;
	}
}else if(num==5){
	//fiveth star
	if(actualVot==num){			
		lastStar(false);
		actualVot=4;
	}else{
		firstStar(true);
		otherStar(true,2);
		otherStar(true,3);
		otherStar(true,4);
		lastStar(true);
		actualVot=5;
	}
}
}

function firstStar(addRemove){

if(addRemove){

	$("#star1").attr("src","/"+context+"/images/star.jpg");	
}else{		

	$("#star1").attr("src","/"+context+"/images/star0.jpg");
}	
}
function otherStar(addRemove,num){
	
if(addRemove){

	$("#star"+num).attr("src","/"+context+"/images/star.jpg");	
}else{
	
	$("#star"+num).attr("src","/"+context+"/images/star0.jpg");	
}	
}
function lastStar(addRemove){

if(addRemove){

	$("#star5").attr("src","/"+context+"/images/star.jpg");	
}else{

	$("#star5").attr("src","/"+context+"/images/star0.jpg");	
}	
}

function votaPlatDialog(){
	//confirmOnline.confirm(initParams.txtconfirmVot,votaPlat);
	var idPlat = $("#idPlat").val();
	var data = "idPlat=" + idPlat + "&punctuacio=" + actualVot;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxSavePunctuacioForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {							
						//alertOnline.subalertes(initParams.txtvotguardat);		
						$("#saveVotButton").attr("disabled","disabled");
						$("#saveVotButton").addClass("votat_close");
						
					}
				},
				error : function(e) {						
					errorOnline.suberror("LA SESSIÓ HA CADUCAT!!!");
				}
			});
}

function votaPlat(){
	
	var idPlat = $("#idPlat").val();
	var data = "idPlat=" + idPlat + "&punctuacio=" + actualVot;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxSavePunctuacioForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {							
						//alertOnline.subalertes(initParams.txtvotguardat);		
						$("#saveVotButton").attr("disabled","disabled");
						confirmOnline.close();
					}
				},
				error : function(e) {						
					errorOnline.suberror("LA SESSIÓ HA CADUCAT!!!");
				}
			});
}


function saveComment() {
		var idPlat = $("#idPlat").val();
		
		var comment = $("#newComment").val();
		if(insult.detectInsult(comment, initParams.txtalertinsult))return;
		
		var html = comment.replace(/[\u00A0-\u00FF]/g, function(c) {
			return '#'+c.charCodeAt(0)+';';
		});
		
		var data = "idPlat=" + idPlat + "&comment=" +html;
		
		$.ajax({
					type : "POST",
					url : '/'+context+'/foro/ajaxSaveCommentForPlat.action',
					dataType : 'json',
					data : data,
					success : function(json) {
						if (json != null && json.error != null) {
							errorOnline.suberror(json.error);
						} else {							
							//alertOnline.subalertes(initParams.txtCommentSaved);	
							insertTR(idPlat,comment,json.idComment);
						}
					},
					error : function(e) {						
						errorOnline.suberror("LA SESSIÓ HA CADUCAT!!!");
					}
				});
	}

function deleteComment(id) {
	var idPlat = $("#idPlat").val();	
	var data = "idPlat=" + idPlat + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxDeleteCommentForPlat.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {							
						//alertOnline.subalertes(initParams.txtComentDeleted);	
						deleteTR(id);
					}
				},
				error : function(e) {						
					errorOnline.suberror("LA SESSIÓ HA CADUCAT!!!");
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
		cell2.innerHTML="<a href='#' onclick='deleteComment("+idComment+")' ><img src='/"+context+"/images/delete.png' /> </a>";
			
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