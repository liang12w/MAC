function init(){
	$.ajax({
		url:"http://localhost:8080/GIFme/getGifAction.do",
		type:"POST",
		dataType:"json",
		success:function(data){
			console.info(data);
		},
	});
}

$(init)