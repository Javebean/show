$(document).ready(function(){
	//event binder
	$("#login").click(login);

	//key event
	$(document).keydown(function(e) {
		if (e.which == 13) {
			login();
			return false;
		}
	})

	function login(){
		var name = $("#name").val();
		var password = $("#password").val();
		if(name==""||password==""){
			$("#message").text("请输入用户名和密码！");
			return;
		}

		var func = function(data){
			data = JSON.parse(data);
			if(data.result === true) {
				window.location.href = "ma_zlzx.jsp";
			} else {
				$("#message").text(data.message);
			}
		}

		Account.login(name,password,func);
	}
});
