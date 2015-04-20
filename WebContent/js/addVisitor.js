//global data
var formData = {};
var picFlag = false;
var PIC_BASE = 'resources/topicimages/';
var pic_scare = "";
$(document).ready(function(){
	//event binder
	$("#submitForm").click(saveForm);
	
	//test
	$(".test_btn").click(function(){
		var visitor = getDymiTableData(".visitors", visitorParams);
		var displayItem = getDymiTableData(".showitems", itemParams);
		getFormdata("regForm");
	});
	
	function saveForm(){
		if(!$("#form").valid()){
			return;
		}
		if(!picFlag){
			jAlert("请上传图片", "信息");
			return;
		}
		var formData = getFormdata("regForm");
		if(picFlag){
			formData.photo = topicId + ".jpg";
		}
		
		var func = function(data){
			data = JSON.parse(data);
			if(data.result == true){
				$(".userForm").hide();
				$(".container-fluid").nextAll().hide();
				$(".resultMsg").show();
			}else{
				alert(result.message);
			}
		};
		Visitor.saveVisitor(formData,pic_scare,func);
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
		console.log(data);
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
				pic_scare = "";
				picFlag = true;
				setTimeout(function(){
					$("#topic_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
					$("#img_preview").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
					$('#topic_image').imgAreaSelect({ aspectRatio: '4:3', onSelectChange: preview });
				},300);
			}
		});
	},10);
	
	function preview(img, selection) {
	    var scaleX = 200 / (selection.width || 1);
	    var scaleY = 150 / (selection.height || 1);
	  
	    $('#img_preview').css({
	        width: Math.round(scaleX * 400) + 'px',
	        height: Math.round(scaleY * 300) + 'px',
	        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
	        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
	    });
	    
	    pic_scare = "";
	    if(selection.width>10){
	    	pic_scare += selection.x1+","+selection.y1+","+selection.width+","+selection.height;
	    }
	}
});