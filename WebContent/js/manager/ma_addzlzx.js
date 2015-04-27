//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
var picFlag = false;
$(document).ready(function(){
	CKEDITOR.replace( 'content',{
		height: '700px',
		width: '753px'
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
		var cketext = CKEDITOR.instances.content.document.getBody().getText();
		topicdata.abs = cketext.trim().substring(0,50);
		if(cketext.length>50) topicdata.abs += '...';


		var func = function(data){
			data = JSON.parse(data);
			if(data.result == true){
				location.href="ma_zlzx.jsp?menu=1";
			}else{
				alert(data.message);
			}
		};
		Zlzx.saveZlzx(topicdata,func);
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
