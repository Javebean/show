//global data
var formData = {};
$(document).ready(function(){
	 
	//event binder
	$("#submitForm").click(saveForm);
	
	function saveForm(){
		if(!$("#form").valid()){
			return;
		}
		formData = getFormdata("regForm");
		
		var func = function(data){
			data = JSON.parse(data);
			
			if(data.result == true){
				//location.href="ma_zlzx.jsp?menu=1";
				$(".userForm").hide();
				$("#login_id").text(data.username);
				$("#login_pass").text(data.password);
				$(".resultMsg").show();
			}else{
				alert(data.message);
			}
		};
		Audience.saveAudience(formData,func);
	}
	
	function getFormdata(formName){
		var form = document.forms[formName];
		var entryNames = [];
		for(var i=0;i<form.length;i++){
			if(form[i].name != ""){
				if(i==0 || (i>0 && form[i].name!=form[i-1].name)){
					entryNames.push(form[i].name);
				}
			}
		}
		
		var data = {};
		for(var i=0;i<entryNames.length;i++){
			var key = entryNames[i];
			data[key] = form[key].value;
		}
		
		return data;
	}
});