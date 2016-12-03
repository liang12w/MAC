function init(){
	$.ajax({
		url:"getMomentsAction.do",
		type:"POST",
		dataType:"json",
		data:{'sid':$.cookie('sid')},
		success:function(data){
			if(data.errorCode== 0){
				generateInfo(data.list);
			}else if (data.errorCode==-1) {
				alert(data.errorMsg);
				window.location.href = '../login.html';
			}else{
				alert(data.errorMsg);
				window.location.reload();
			}
		},
	});
}

$(init)

function generateInfo(data){
	var content = "";
	// console.info(Date(data[0]["motSentTime"]));
	
	for (var i = 0; i < data.length; i++) {
		content += "<article id='art"+data[i]["motId"]+"'><div class='heading'><h2>"+data[i]["motContent"]+"</h2></div>"+
					"<div class='content'><img src='"+data[i]["motGifUri"]+"' width='200px' height='200px' /></div>"+
					"<div class='info'><p>By "+data[i]["userInfo"]["usrName"]+" on "+Date(data[0]["motSentTime"])+
					" - <a href='javascript:showComments("+data[i]["motId"]+");' >"+data[i]["motCommentNum"]+" Commnets</a></p>"+
					"</div></article>";
	};
	$(".col-md-6").append(content);
}

function showComments(motId){
	if ($('#com'+motId).length<1) {
		getComments(motId);
	}else{
		$('#com'+motId).toggle();
	}
}

function getComments(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId
	}
	$.ajax({
		url:"getCommentsAction.do",
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==-1) {
				$.cookie('sid', null); 
				alert(data.errorMsg);
				window.location.href = "../login.html";
			}else if(data.errorCode==0){
				generateComments(data.list,motId);
			}else if(data.errorCode==1){
				generateComments(-1,motId);
			}else{
				alert(data.errorMsg);
				window.location.reload();
			}
		},
	});
}

function generateComments(data,motId){
	var content = "<div class='comment' id='com"+motId+"'>"+
              "<form id='contact-form' method='post'>"+
                "<fieldset>"+
                    "<textarea id='comment"+motId+"' onBlur='if(this.value=='') this.value='Message'' onFocus='if(this.value =='Message' ) this.value='''>Message</textarea><div class='buttons'>"+
                      "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a href='#' onClick='document.getElementById('contact-form').reset()'>Clear</a>"+
                        "&nbsp; &nbsp; <a href='#' onClick='submitComment("+motId+");'>Send</a>"+
                    "</div></fieldset>"+           
            	"</form></div>";
    $('#art'+motId).append(content);
    if (data==-1) {
    	return;
    } 
    var comments = "<table>";
    for(var i = 0; i < data.length; i++){
    	comments += "<tr><td class='comment'>"+data[i]["userInfo"]["usrName"]+
          ":</td><td class ='comment1'>"+ data[i]["comtContent"]+"</td></tr>"+ 
          "<tr><td class='comment'>Time：</td><td class='comment1'>"+Date(data[i]["comtTime"])+"</td></tr>"+  
          "</table> "
    }
    $('#art'+motId).append(comments);
          // "<tr><td class='comment'>"+data[i]["userInfo"]["usrName"]+
          // ":</td><td class ='comment1'>"+ data[i]["comtContent"]+"</td></tr>"+ 
          // "<tr><td class='comment'>Time：</td><td class='comment1'>"+Date(data[i]["comtTime"])+"</td></tr>"+  
          // "</table> "
}

function submitComment(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId,
		'comment':$('#comment'+motId).val()
	}
	$.ajax({
		url:"submitCommentsAction.do",
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==-1) {
				$.cookie('sid', null); 
				alert(data.errorMsg);
				window.location.href = "../login.html";
			}else if(data.errorCode==1){
				alert(data.errorMsg);
				window.location.reload();
			}else{
				;//todo
			}
		},
	});
}

function addLike(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId,
	}
	$.ajax({
		url:"addLikeAction.do",
		type:"text",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==-1) {
				$.cookie('sid', null); 
				alert(data.errorMsg);
				window.location.href = "../login.html";
			}else if(data.errorCode==1){
				alert(data.errorMsg);
				window.location.reload();
			}else{
				;//todo
			}
		},
	});
}

function removeLike(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId,
	}
	$.ajax({
		url:"removeLikeAction.do",
		type:"text",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==-1) {
				$.cookie('sid', null); 
				alert(data.errorMsg);
				window.location.href = "../login.html";
			}else if(data.errorCode==1){
				alert(data.errorMsg);
				window.location.reload();
			}else{
				;//todo
			}
		},
	});
}
