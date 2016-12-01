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
		'content':$('content').val()
	}
	if (content=undefined) {
		alert("Please input something.");
		return;
	};
	$.ajax({
		url:"http://localhost:8080/GIFme/submitMomentAction.do",
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==0) {
				window.location.href = 'home.html';
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
		'content':$('content').val()
	}
	$.ajax({
		url:"http://localhost:8080/GIFme/views/getGifAction.do",
		type:"POST",
		dataType:"json",
		data:params, 
		success:function(data){
			if (data.errorCode==-1) {
				alert(data.errorMsg);
				window.location.href = '../login.html';
			} else if (data.errorCode==0) {
				//TODO
			} else{
				alert(data.errorMsg);
				window.location.href = 'home.html';
			};
		},
	});
}

 $(validate)