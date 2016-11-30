function doInit() {
	$("#submit").on('click', function() {
		var radios = document.getElementsByName("optionsRadios");
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked)
				var gender = radios[i].value;
		}
		var nickname = $("#first_name").val()+"$"+$("#last_name").val();
		var params = {
			"nickname" : nickname,
			"username" : $("#username").val(),
			"email" : $("#email").val(),
//			"gender":gender,
			"password" : $("#password").val()
		}
		for (var i in params) {
			if (params[i]==undefined){
				alert("Please input/select "+i);
				return;
			}
		};
		if (/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(params["email"])==false) {
			alert("Invalid email address");
			return;
		};
		if (params["password"] == $('#password_confirm').val()&&params["password"].length>=6)
			params["password"] = $.md5($("#password").val());
		else{
			alert('Invalid Password!');
			return;
		}

		// var checkUrl = "checkUsernameAction.do";
		var submitUrl = "signupAction.do";
		// $.ajax({
		// 	url : checkUrl,
		// 	data : params["username"],
		// 	success : function(data) {
		// 		if (data.errorCode == 1) {
		// 			alert("Username is existed.");
		// 			// alert(data.errorMsg);
		// 			return;
		// 		} 
		// 	}
		// });

		$.ajax({
			url : submitUrl,
			data : params,
			type:"POST",
			success : function(data) {
				if (data.errorCode == 0) {
					alert("Sign up successed.");
					window.location.href = "login.html";
				} else {
					alert(data.errorMsg);
				}
			}
		});
	});
};

$(doInit);