function init(){
	// $.ajax({
	// 	url:"http://localhost:8080/GIFme/views/validateAction.do",
	// 	type:"POST",
	// 	dataType:"json",
	// 	data:{'sid':$.cookie('sid')},
	// 	success:function(data){
	// 		if (data.errorCode==-1) {
	// 			alert(data.errorMsg);
	// 			window.location.href = "../login.html";
	// 		}
	// 	},
	// });
	$.ajax({
		url:"http://localhost:8080/GIFme/views/getMomentsAction.do",
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
	var content = undefined;
	// console.info(data[0]["motId"]);
	// console.info(data[0]["userInfo"]["usrName"]);
	// var time = data[0]["motSentTime"];
	// var date = Date(data[0]["motSentTime"]);
	// console.info(Date(data[0]["motSentTime"]));
	for (var i = 0; i < data.length; i++) {
		content += "<article id='"+data[i]["motId"]+"'><div class='heading'><h2>"+data[i]["motContent"]+"</h2></div>"+
					"<div class='content'><img src='"+data[i]["motGifUri"]+"' width='200px' height='200px' /></div>"+
					"<div class='info'><p>By "+data[i]["userInfo"]["usrName"]+" on "+Date(data[0]["motSentTime"])+
					" - <a href='javascript:getComments('"+data[i]["motId"]+"');' >"+data[i]["motCommentNum"]+" Commnets</a></p>"+
					"</div></article>";
	};
	$(".col-md-6").append(content);
}

function getComments(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId
	}
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

function submitComment(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId,
		'comment':undefined//TODO
	}
	$.ajax({
		url:"http://localhost:8080/GIFme/views/submitCommentsAction.do",
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

function addLike(motId){
	params = {
		'sid':$.cookie('sid'),
		'motId':motId,
	}
	$.ajax({
		url:"http://localhost:8080/GIFme/views/addLikeAction.do",
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
		url:"http://localhost:8080/GIFme/views/removeLikeAction.do",
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