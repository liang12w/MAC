function submit(){
	$('#submit').on("click", function(){
		var params = {
			'username':$('#username').val(),
			'password':$.md5($('#password').val())
		};

		$.ajax({
			url:"http://localhost:8080/GIFme/loginAction.do",
			type : "POST",
			dataType:"json",
			data:params,
			success:function(data){
				console.info("data is "+data);
				if (data.errorCode==0) {
					$.cookie("sid",data.sid);
					window.location.href = "home.html";
				}else{
					alert(data.errorMsg);
					window.location.href = "login.html";
				}
			},
			error:function(XMLResponse){
				alert("error is "+XMLResponse.responseText);
				console.info(XMLResponse.responseText);
			}
		});
	})
}

$(submit);