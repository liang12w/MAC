function validate(){
	$.ajax({
		url:"http://localhost:8080/GIFme/views/validateAction.do",
		type:"POST",
		dataType:"json",
		data:{"sid":$.cookie('sid')}, 
		success:function(data){
			if (data.errorCode==-1) {
				alert(data.errorMsg);
				window.location.href = '../login.html';
			};
		},
	});
}

function submit(){
	params = {
		'sid':$.cookie('sid'),
		'motContent':$('#content').val(),
		'url':$("img[name='add GIF']").attr("src")
	}
	if (content=undefined) {
		alert("Please input something.");
		return;
	};
	$.ajax({
		url:"http://localhost:8080/GIFme/views/submitMomentAction.do",
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==0) {
				window.location.href = 'me.html';
			} else{
				alert(data.errorMsg);
				window.location.href = 'home.html';
			};
		},
	});
}

function getGif(){
	params = {
		'sid':$.cookie('sid'),
		'content':$('#content').val()
	}
	$.ajax({
		url:"http://localhost:8080/GIFme/views/WatsonService.do",
		type:"POST",
		dataType:"json",
		data:params, 
		success:function(data){
			if (data.errorCode==-1) {
				alert(data.errorMsg);
				window.location.href = '../login.html';
			} else if (data.errorCode==0) {
				//TODO
				$("img[name='add GIF']").attr("src",data.url);
			} else{
				alert(data.errorMsg);
				window.location.href = 'home.html';
			};
		},
	});
}

 $(validate)