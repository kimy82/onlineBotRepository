///////////////////////////////////
//variables per textos en locale
var initParams=null ;
function InitParams(txtComentDeleted){		
	
	this.txtComentDeleted = txtComentDeleted;
	
}

function loadComments(id){
	window.localStorage.setItem("idPlat",id);
	window.location.href="/"+context+"/admin/loadComments.action?idPlat="+id;	
}

function loadCommentsBeguda(id){
	window.localStorage.setItem("idBeguda",id);
	window.location.href="/"+context+"/admin/loadCommentsBeguda.action?idBeguda="+id;	
}

function loadAllComments(){
	window.location.href="/"+context+"/admin/loadAllComments.action";	
}

function deleteComment(id) {
	var idPlat = $("#platId").val();	
	var data = "idPlat=" + idPlat + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/admin/ajaxDeleteCommentAction.action',
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

function deleteCommentFormAll(idPlat, idCommnet) {
	
	var data = "idPlat=" + idPlat + "&idComment=" + idCommnet;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/admin/ajaxDeleteCommentAction.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {													
						deleteTRAllPlat(idCommnet);
					}
				},
				error : function(e) {						
					errorOnline.suberror("Error in AJAX");
				}
			});
}

function deleteCommentFromAllBeguda(idBeguda,idComment) {
		
	var data = "idBeguda=" + idBeguda + "&idComment=" + idComment;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/admin/ajaxDeleteCommentBegudaAction.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {													
						deleteTRAllBeguda(idCommnet);
					}
				},
				error : function(e) {						
					errorOnline.suberror("Error in AJAX");
				}
			});
}

function deleteCommentBeguda(id) {
	var idBeguda = $("#begudaId").val();	
	var data = "idBeguda=" + idBeguda + "&idComment=" + id;
	
	$.ajax({
				type : "POST",
				url : '/'+context+'/admin/ajaxDeleteCommentBegudaAction.action',
				dataType : 'json',
				data : data,
				success : function(json) {
					if (json != null && json.error != null) {
						errorOnline.suberror(json.error);
					} else {													
						deleteTRBeguda(id);
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

function deleteTRBeguda(id){
	
	var table=document.getElementById("comments_tbl_b");
	var rows = table.rows;
	for (var i=0; i<rows.length; i++) {
		var tr = rows[i];
		if(tr.getAttribute("id")==id)
			table.deleteRow(i);
	}		
}

function deleteTRAllBeguda(id){
	
	var table=document.getElementById("comments_tbl_all");
	var rows = table.rows;
	for (var i=0; i<rows.length; i++) {
		var tr = rows[i];
		if(tr.getAttribute("id")=='all_bg_'+id)
			table.deleteRow(i);
	}		
}

function deleteTRAllPlat(id){
	
	var table=document.getElementById("comments_tbl_all");
	var rows = table.rows;
	for (var i=0; i<rows.length; i++) {
		var tr = rows[i];
		if(tr.getAttribute("id")=='all_pl_'+id)
			table.deleteRow(i);
	}		
}


var idPlat = window.localStorage.getItem("idPlat");
if(idPlat != 'undefined' && idPlat != null){
	$("#platId [value="+idPlat+"]").attr('selected', true);
}

var idBeguda = window.localStorage.getItem("idBeguda");
if(idBeguda != 'undefined' && idBeguda != null){
	$("#begudaId [value="+idBeguda+"]").attr('selected', true);
}