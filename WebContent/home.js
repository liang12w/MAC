function init(){
	var sid = $.cookie('sid');
	console.info(sid);
	$.ajax({
		url:"http://localhost:8080/GIFme/validateAction.do",
		type:"text",
		dataType:"json",
		data:sid,
		success:function(data){
			if (data.errorCode==1) {
				window.location.href = "login.html";
			}else{
				generateInfo(data.moments);
			}
		},
	});
}

$(init)

function generateInfo(data){
	var content = undefined;
	for (var i = 0; i < data.length; i++) {
		cont += "<article><div class='heading'><h2>"+data[i].motContent+"</h2></div>"+
					"<div class='content'><img src='"+data[i].motGifUri+"' width='200px' height='200px' /></div>"+
					"<div class='info'><p>By "+data[i].userInfo.usrName+" on "+data[i].motSentTime+
					" - <a href='comment.html'>"+data[i].motCommentNum+" Commnets</a></p>"+
					"</div></article>";
	};
	$(".col-md-6").append(content);
}