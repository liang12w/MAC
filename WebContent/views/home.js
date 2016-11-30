var params = [];
function init(){
	// var sid = $.cookie('sid');
	// console.info(sid);
	if ($.cookie('sid')!=undefined) {
		params.push({'sid':$.cookie('sid')});
	};
	$.ajax({
		url:"http://localhost:8080/GIFme/views/validateAction.do",
		type:"text",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==-1) {
				window.location.href = "../login.html";
			}else if(data.errorCode== 0){
				$.cookie("sid",{expires:7});
				generateInfo(data.moments);
			}
		},
	});
}

$(init)

function generateInfo(data){
	var content = undefined;
	for (var i = 0; i < data.length; i++) {
		cont += "<article id='"+data.motId+"'><div class='heading'><h2>"+data[i].motContent+"</h2></div>"+
					"<div class='content'><img src='"+data[i].motGifUri+"' width='200px' height='200px' /></div>"+
					"<div class='info'><p>By "+data[i].userInfo.usrName+" on "+data[i].motSentTime+
					" - <a href='javascript:getComments('"+data.motId+"');' >"+data[i].motCommentNum+" Commnets</a></p>"+
					"</div></article>";
	};
	$(".col-md-6").append(content);
}

function getComments(motId){
	params.push('motId',motId);
	$.ajax({
		url:"http://localhost:8080/GIFme/views/getCommentsAction.do",
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
				$.cookie("sid",{expires:7});
				generateComments(data.moments);
			}
		},
	});
}

function submitComment(){
	params.push("comment",undefined);//todo
	params.push("motId",undefined);//todo
	$.ajax({
		url:"http://localhost:8080/GIFme/views/getCommentsAction.do",
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