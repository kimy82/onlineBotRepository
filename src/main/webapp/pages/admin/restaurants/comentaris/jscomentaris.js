///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtComentDeleted){		
	
	this.txtComentDeleted = txtComentDeleted;
	
}

function loadComments(id){
	window.localStorage.setItem("idPlat",id);
	window.location.href="/onlineBot/admin/loadComments.action?idPlat="+id;	
}

function deleteComment(id) {
	var idPlat = $("#platId").val();	
	var data = "idPlat=" + idPlat + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/onlineBot/admin/ajaxDeleteCommentAction.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {													
						deleteTR(id);
					}
				},
				error : function(e) {						
					errorOnline.suberror("Error in AJAX");
				}
			});
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

var idPlat = window.localStorage.getItem("idPlat");
if(idPlat != 'undefined' && idPlat != null){
	$("#platId [value="+idPlat+"]").attr('selected', true);
}