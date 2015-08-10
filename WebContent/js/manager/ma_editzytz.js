//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
$(document).ready(function(){
	//init page
	initTopic();

	function initTopic(){
		var func = function(data){
			var pt = JSON.parse(data);
			$("#title").val(pt.title);
			$("#content").val(pt.content);
			
			CKEDITOR.replace( 'content',{
				height: '700px',
				width: '753px'
			});
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
		var cketext = CKEDITOR.instances.content.document.getBody().getText();
		topicdata.abs = cketext.trim().substring(0,50);
		if(cketext.length>50) topicdata.abs += '...';

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
