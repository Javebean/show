//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
var picFlag = false;
$(document).ready(function(){
	CKEDITOR.replace( 'content',{
		height: '700px'
		//allowedContent: true
	});
	 
	//event binder
	$("#add_topic").click(saveTopic);
	
	var paramlist = ["title","content"];
	
	function saveTopic(){
		topicdata = getFormdata(paramlist);
		
		//save ckEditor data
		var ckedata = CKEDITOR.instances.content.getData();
		topicdata.content = ckedata;
		
		var func = function(data){
			data = JSON.parse(data);
			if(data.result == true){
				location.href="ma_zytz.jsp?menu=1";
			}else{
				alert(result.message);
			}
		};
		Zytz.saveZytz(topicdata,func);
	}
	
	function getFormdata(paramlist){
		var data = {};
		for (var key in paramlist) {
			var keyname = paramlist[key];
			data[keyname] = $("#"+keyname).val();
		}
		return data;
	}
});