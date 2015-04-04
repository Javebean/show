//global data
var topicdata = {};
var PIC_BASE = 'resources/topicimages/';
var picFlag = false;
$(document).ready(function(){
	CKEDITOR.replace( 'content',{
		//allowedContent: true
	});
	 
	//event binder
	$("#add_topic").click(saveTopic);
	
	var paramlist = ["title","content"];
	
	function saveTopic(){
		topicdata = getFormdata(paramlist);
		topicdata.type="homec";
		if(picFlag) topicdata.picPath=topicId+'.jpg';
		topicdata.id = topicId;
		
		//save ckEditor data
		var ckedata = CKEDITOR.instances.content.getData();
		topicdata.content = ckedata;
		
		var func = function(data){
			data = JSON.parse(data);
			location.href="manager.jsp?menu=0";
		};
		PTopic.savePT(topicdata,func);
	}
	
	function getFormdata(paramlist){
		var data = {};
		for (var key in paramlist) {
			var keyname = paramlist[key];
			data[keyname] = $("#"+keyname).val();
		}
		return data;
	}
	  
	setTimeout(function(){
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
				picFlag = true;
				setTimeout(function(){
					$("#topic_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
				},300);
			}
		});
	},10);
});