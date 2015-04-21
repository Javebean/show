//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
$(document).ready(function(){
	//init page
	initTopic();
	
	CKEDITOR.replace( 'content',{
		height: '700px'
		//allowedContent: true
	});
	
	function initTopic(){
		var func = function(data){
			var pt = JSON.parse(data);
			console.log(pt);
			$("#title").val(pt.title);
			$("#content").val(pt.content);
		};
		Zlzx.getZlzxById(topicId,func);
	}
	
	//event binder
	$("#edit_topic").click(saveTopic);
	
	var paramlist = ["title","content"];
	
	function saveTopic(){
		topicdata = getFormdata(paramlist);
		topicdata.id = topicId;
		
		//save ckEditor data
		var ckedata = CKEDITOR.instances.content.getData();
		topicdata.content = ckedata;
		var cketext = CKEDITOR.instances.content.document.getBody().getText();
		topicdata.abs = cketext.substring(0,100);
		
		var func = function(data){
			if(data == true){
				location.href="ma_zlzx.jsp?menu=1";
			}else{
			}
		};
		Zlzx.updateZlzx(topicdata,func);
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