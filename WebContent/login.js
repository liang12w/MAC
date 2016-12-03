function submit(){
	$('#submit').on("click", function(){
		var params = {
			'username':$('#username').val(),
			'password':$.md5($('#password').val())
		};
		var checked = $('#checked').val();
		$.ajax({
			url:"loginAction.do",
			type : "POST",
			dataType:"json",
			data:params,
			success:function(data){
				console.info("data is "+data);
				if (data.errorCode==0) {
					if(checked==true){
						$.cookie("sid",data.sid,{expires:7});
					}else{
						$.cookie("sid",data.sid);						
					}
					window.location.href = "views/home.html";
				}else{
					alert(data.errorMsg);
					$('#password').val(null);
//					window.location.href = "login.html";
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