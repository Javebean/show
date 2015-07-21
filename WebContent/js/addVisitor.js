//global data
var formData = {};
var picFlag = false;
var picFlagFro = false;
var picFlagBac = false;
var PIC_BASE = 'resources/topicimages/';
if(location.href.indexOf("/eng/")!=-1){
	PIC_BASE = '../resources/topicimages/';
}

var pic_scare = "";
var topicId = Math.uuid();
var pic_icfro_ID = Math.uuid();
var pic_icbac_ID = Math.uuid();
var img_selection;
$(document).ready(function(){
	//event binder
	$("#submitForm").click(saveForm);
	$("#chuyang").click(chuyang);

	//test
	$(".test_btn").click(function(){
		var visitor = getDymiTableData(".visitors", visitorParams);
		var displayItem = getDymiTableData(".showitems", itemParams);
		getFormdata("regForm");
	});

	function chuyang(){
		var formData = getFormdata("regForm");
		if(formData.type == 1)
			$(".cp").css('background','url(images/exhibitcard.png)');
		else if(formData.type == 4)
			$(".cp").css('background','url(images/staffcard.png)');
		else if(formData.type == 3)
				$(".cp").css('background','url(images/presscard.png)');
		else
		{
			$(".cp").css('background','url(images/guestcard.png)');
		}
		$(".cp_name").text(formData.name);
		$(".cp_company").text(formData.org);
		if(picFlag){
			var oriHeight = $("#topic_image").height();
			$(".cp_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
			 var scaleX = 300 / (img_selection.width || 1);
			 var scaleY = oriHeight / (img_selection.height || 1);
			$('.cp_image').css({
		        width: Math.round(scaleX * 118) + 'px',
		        height: Math.round(scaleY * 149) + 'px',
		        marginLeft: '-' + Math.round(img_selection.x1 * 118 / img_selection.width) + 'px',
		        marginTop: '-' + Math.round(img_selection.y1 * 149 / img_selection.height) + 'px'
		    });
		}
	}

	function saveForm(){
		if(!$("#form").valid()){
			$(window).scrollTop(400);
			jAlert("请检查输入内容", "信息");
			return;
		}
		if(!picFlag || !picFlagFro || !picFlagBac){
			jAlert("请上传证件照，身份证正面照，身份证背面照。", "信息");
			return;
		}
		var formData = getFormdata("regForm");
		if(picFlag){
			formData.photo = topicId + ".jpg";
			formData.idFont = pic_icfro_ID + ".jpg";
			formData.idBack = pic_icbac_ID + ".jpg";
		}

		var func = function(data){
			data = JSON.parse(data);
			if(data.result == true){
				$(".userForm").hide();
				$(".footer").nextAll().hide();
				$(".resultMsg").show();
			}else{
				jAlert(data.message, "信息");
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

		return data;
	}

	setTimeout(function(){
		$("#uploadify").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + topicId,
			'fileDesc' : 'Image Files',
			'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '5MB',

			onSelectError: function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alert(msgText);
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadSuccess : function(file, data, response) {
				console.log(file);
				pic_scare = "";
				picFlag = true;
				setTimeout(function(){
					$("#topic_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
					$("#img_preview").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
					$('#topic_image').imgAreaSelect({ onSelectChange: preview,aspectRatio: '118:149' });
				},300);
			}
		});
	},10);

	function preview(img, selection) {
		var oriHeight = $("#topic_image").height();
		var scaleX = 118 / (selection.width || 1);
	    var scaleY = 149 / (selection.height || 1);

	    $('#img_preview').css({
	        width: Math.round(scaleX * 300) + 'px',
	        height: Math.round(scaleY * oriHeight) + 'px',
	        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
	        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
	    });

	    pic_scare = "";
	    if(selection.width>10){
	    	pic_scare += selection.x1+","+selection.y1+","+selection.width+","+selection.height+",300,"+oriHeight;
	    	img_selection = selection;
	    }
	}

	//身份证正面
	setTimeout(function(){
		$("#uploadify_idfront").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + pic_icfro_ID,
			'fileDesc' : 'Image Files',
			'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '5MB',

			onSelectError: function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alert(msgText);
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadSuccess : function(file, data, response) {
				picFlagFro = true;
				setTimeout(function(){
					$("#topic_image_idfront").attr("src",PIC_BASE+pic_icfro_ID+".jpg?"+Math.random());
				},300);
			}
		});
	},10);

	//身份证背面
	setTimeout(function(){
		$("#uploadify_idback").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + pic_icbac_ID,
			'fileDesc' : 'Image Files',
			'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '5MB',

			onSelectError: function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alert(msgText);
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadSuccess : function(file, data, response) {
				picFlagBac = true;
				setTimeout(function(){
					$("#topic_image_idback").attr("src",PIC_BASE+pic_icbac_ID+".jpg?"+Math.random());
				},300);
			}
		});
	},10);
});
