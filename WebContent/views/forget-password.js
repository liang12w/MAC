function init(){
	$('submit').on('click',function(){
		var email = $('email').val();
		if (/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)==false) {
			alert("Invalid email address");
			return;
		};
		params = {
			'sid':$.cookie('sid'),
			'email':email
		}
		$.ajax({
		url:"http://localhost:8080/GIFme/validateEmailAction.do",
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if (data.errorCode==0) {
				window.location.href('reset.html');
			} else{
				alert(data.errorMsg);
				// window.location.href('.html');
			};
		},
	});
	})
}

$(init);