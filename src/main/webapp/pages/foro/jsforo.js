///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtCommentSaved, txtComentDeleted){		
	
	this.txtCommentSaved = txtCommentSaved;
	this.txtComentDeleted = txtComentDeleted;
	
}

function saveComment() {
		var idPlat = $("#idPlat").val();
		var comment = $("#newComment").text();
		var data = "idPlat=" + idPlat + "&comment=" + comment;
		
		$.ajax({
					type : "POST",
					url : '/onlineBot/foro/ajaxSaveCommentForPlat.action',
					dataType : 'json',
					data : data,
					success : function(json) {
						if (json == null || json.error != null) {
							errorOnline.error(json.error);
						} else {							
							alertOnline.alertes(initParams.txtCommentSaved);							
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
					if (json == null || json.error != null) {
						errorOnline.error(json.error);
					} else {							
						alertOnline.alertes(initParams.txtComentDeleted);							
					}
				},
				error : function(e) {						
					errorOnline.error("Error in AJAX");
				}
			});
}