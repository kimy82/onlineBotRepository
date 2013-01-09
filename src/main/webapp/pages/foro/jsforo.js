///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtCommentSaved, txtComentDeleted){		
	
	this.txtCommentSaved = txtCommentSaved;
	this.txtComentDeleted = txtComentDeleted;
	
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