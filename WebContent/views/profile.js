/**
 * http://usejsdoc.org/
 */
$(function(){
	$.ajax({
		url:"getUserInfoAction.do",
		type:"POST",
		dataType:"json",
		data:{'sid':$.cookie('sid')},
		success:function(data){
			if(data.errorCode== 0){
				generateInfo(data.userinfo);
			}else{
				alert(data.errorMsg);
				window.location.herf="home.html";
			}
		},
	});
});

function generateInfo(user){
	var name = new Array();
	var name = user["usrNickname"].split('$');
	$('#first_name').attr("value",name[0]);
	$('#last_name').attr("value",name[1]);
	$('#username').attr("value",user["usrName"]);
	$('#email').attr("value",user["usrEmail"]);
}