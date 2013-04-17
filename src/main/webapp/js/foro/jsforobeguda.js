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
		$("#star1").attr("src","/"+context+"/images/star.jpg");	
	}else{		
		actualVot=0;
		$("#star1").attr("src","/"+context+"/images/star0.jpg");
	}	
}
function otherStar(addRemove,num){
		
	if(addRemove){
		actualVot=num;
		$("#star"+num).attr("src","/"+context+"/images/star.jpg");	
	}else{
		actualVot=num-1;
		$("#star"+num).attr("src","/"+context+"/images/star0.jpg");	
	}	
}
function lastStar(addRemove){
	
	if(addRemove){
		actualVot=5;
		$("#star5").attr("src","/"+context+"/images/star.jpg");	
	}else{
		actualVot=4;
		$("#star5").attr("src","/"+context+"/images/star0.jpg");	
	}	
}

function votaBegudaDialog(){
	
	var idBeguda = $("#idBeguda").val();
	var data = "idBeguda=" + idBeguda + "&punctuacio=" + actualVot;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxSavePunctuacioForBeguda.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {							
						//alertOnline.subalertes(initParams.txtvotguardat);		
						$("#saveVotButton").attr("disabled","disabled");						
					}
				},
				error : function(e) {						
					errorOnline.suberror("Error in AJAX");
				}
			});
}



function saveComment() {
		var idBeguda = $("#idBeguda").val();
		
		var comment = $("#newComment").val();
		if(insult.detectInsult(comment, initParams.txtalertinsult))return;
		
		var html = comment.replace(/[\u00A0-\u00FF]/g, function(c) {
			return '#'+c.charCodeAt(0)+';';
		});
		
		var data = "idBeguda=" + idBeguda + "&comment=" + html;
		
		$.ajax({
					type : "POST",
					url : '/'+context+'/foro/ajaxSaveCommentForBeguda.action',
					dataType : 'json',
					data : data,
					success : function(json) {
						if (json != null && json.error != null) {
							errorOnline.suberror(json.error);
						} else {							
							//alertOnline.subalertes(initParams.txtCommentSaved);	
							insertTR(idBeguda,comment,json.idComment);
						}
					},
					error : function(e) {						
						errorOnline.suberror("Error in AJAX");
					}
				});
	}

function deleteComment(id) {
	var idBeguda = $("#idBeguda").val();	
	var data = "idBeguda=" + idBeguda + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/foro/ajaxDeleteCommentForBeguda.action',
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
					errorOnline.suberror("Error in AJAX");
				}
			});
}

function insertTR(idBeguda,comment,idComment){
	
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