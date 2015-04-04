//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
$(document).ready(function(){
	//init page
	initTopic();
	
	function initTopic(){
		var func = function(data){
			data = JSON.parse(data);
			var pt = data.data;
			console.log(pt);
			if(pt.picPath){
				$("#topic_image").attr("src",PIC_BASE+pt.picPath+"?"+Math.random());
			}
			$("#title").val(pt.title);
			$("#content").val(pt.content);
		};
		PTopic.getPTById(topicId,func);
	}
	
	//event binder
	$("#edit_topic").click(saveTopic);
	
	var paramlist = ["title","content"];
	
	function saveTopic(){
		topicdata = getFormdata(paramlist);
		topicdata.type="homec";
		topicdata.picPath=topicId+'.jpg';
		topicdata.id = topicId;
		topicdata.status = 1;
		var func = function(data){
			data = JSON.parse(data);
			location.href="manager.jsp?menu=0";
		};
		PTopic.updatePT(topicdata,func);
	}
	
	function getFormdata(paramlist){
		var data = {};
		for (var key in paramlist) {
			var keyname = paramlist[key];
			data[keyname] = $("#"+keyname).val();
		}
		return data;
	}
	
    $("#uploadify").uploadify({
    	'swf'      : 'uploadify.swf',
		'uploader' : 'imageUpload?topicId=' + topicId,
		'fileDesc' : 'Image Files',
		'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
        'multi' : false,
        'sizeLimit' : 10485760,
        onUploadError : function(event, queueID, fileObj, errorObj) {
			return false;
		},
		onUploadSuccess : function(file, data, response) {
			setTimeout(function(){
				$("#topic_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
			},300);
		}
    });
});