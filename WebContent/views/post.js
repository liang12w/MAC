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
	$.ajax({
		url:"http://localhost:8080/GIFme/getGifAction.do",
		type:"POST",
		dataType:"json",
		success:function(data){
			console.info(data);
		},
	});
}
