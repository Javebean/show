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
		Zytz.getZytzById(topicId,func);
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
		
		var func = function(data){
			if(data == true){
				location.href="ma_zytz.jsp?menu=1";
			}else{
			}
		};
		Zytz.updateZytz(topicdata,func);
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