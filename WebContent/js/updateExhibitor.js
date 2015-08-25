//global data
var formData = {};
var picFlag = false;
var picFlag_logo = false;
var PIC_BASE = 'resources/topicimages/';
var IMAGE_NOT_FOUND = "";
if(location.href.indexOf("/eng/")!=-1){
	PIC_BASE = '../resources/topicimages/';
}

var topicId = Math.uuid();
var topicId_logo = Math.uuid();

var pic_icheader_ID = "";
var pic_icfro_ID = "";
var pic_icbac_ID = "";
var pic_scare = "";
var img_selection;

var scene_type_html = "";
var trans_type_html = "";
var reg_num = /^[0-9]*$/;
var reg_float = /^[1-9]\d*\.?\d*|0\.\d*[1-9]\d*$/;
$(document).ready(function(){
	var itemParams = ["name","number","length","width","height","weight"];
	var visitorParams = ["name","sex","position","phone"];
	var sceneParams = ["type","content"];
	var transParams = ["type","content","time"];

	if(getCookie("type")!=1){
		$(".resultMsg h3").text("请先进行展商登录。");
		$(".resultMsg").show();

		return;
	}

	var name = getCookie("user");
	var loadExhibitor = function(data){
		data = JSON.parse(data);
		if(data.error){
			$(".resultMsg h3").text(data.error);
			$(".resultMsg").show();

			return;
		}

		formData = data;

		//预设表单信息
		setViewForm(".userForm" ,formData);
		if(formData.logo){
			$("#topic_image_logo").attr("src",PIC_BASE+ formData.logo +"?"+Math.random());
		} else {
			$("#topic_image_logo").attr("src",IMAGE_NOT_FOUND);
		}

		if(formData.tytzzs==1){
			$("input[name='tytzzs'][value=1]").attr("checked",'checked');
		}
		if(formData.swzs==1){
			$("input[name='swzs'][value=1]").attr("checked",'checked');
		}

		$(".userForm").show();

		//检查用户是否安装FLASH
		checkFlash();

	};



	Exhibitor.getExhibitorByUserName(name, loadExhibitor);

	//event binder
	$("#submitForm").click(saveForm);
	pageInit();

	//初始化现场服务和货运物流
	function pageInit(){
		//初始化招商引荐单位
		var recommender_sel_html = "";
		for(var i=0; i< CON_REC_SEL.length; i++){
			var item = CON_REC_SEL[i];
			if (item == undefined){

				}
					else {
						recommender_sel_html += '<option value = "' + item.name + '">' + item.name +'</option>';
					}

		}
		$("#recommender_dropbox").html(recommender_sel_html);
		$("#recommender_dropbox").trigger("change");

	}

	//公共方法
	function setViewForm(tableID,data){
		$(tableID+" .fview_value").each(function(){
			var key = $(this).attr("name");
			if(data[key]){
				$(this).val(data[key]);
				//alert(key + " ; " + data[key])
			}
		});
	}

	function saveForm(){
		var exbData = wrapExbData();
		if(!exbData) return;

		exbData.applyTime = "";

		var func = function(data){
			data = JSON.parse(data);

			if(data.result == true){
				$(".userForm").hide();
				$(".resultMsg h3").text("修改成功！");
				$(".resultMsg").show();

			}else{
				jAlert(data.message, "信息");
			}
		};

		Exhibitor.updateExhibitor(exbData,func);

	}

	function wrapExbData(){
		if(!$("#form").valid({
			 rules: {
				sex: "required"
			},

			errorPlacement: function(error, element) { //指定错误信息位置
				if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
					var eid = element.attr('name'); //获取元素的name属性
					error.insertAfter(element.parent().parent().parent()); //将错误信息添加当前元素的父结点后面
				} else if(element.is('textarea')){
					error.insertAfter(element);
				} else {
					if(element.parent().hasClass("right")){
						error.css("margin-bottom","-20px");
						error.css("margin-left","360px");
					}
					if(element.parent().parent().hasClass("tablezh")){
						error.insertAfter(element.parent().parent());
					} else {
						error.insertAfter(element.parent());
					}
				}
			},
		})){
			$(window).scrollTop(400);
			jAlert("请检查输入内容", "信息");
			return false;
		}
		var exbData = getFormdata("regForm", formData);
		exbData.tytzzs= $("input[name='tytzzs']:checked").val();
		exbData.swzs= $("input[name='swzs']:checked").val();
		var btsl = exbData.btsl;
		if(btsl == "标摊每个3m X 3m"){
			$(window).scrollTop(1300);
			jAlert("请输入标摊数量", "信息");
			return false;
		}

		// if(isNaN(parseInt(btsl))){
			//if(!reg_num.test(btsl) && btsl!="")
			if(!$.isNumeric(btsl) || parseInt(btsl)>1000)
			{
			$(window).scrollTop(1300);
			jAlert("标摊数量请输入整数", "信息");
			return false;
		} else {
			exbData.btsl = parseInt(btsl);
		}

		if(picFlag_logo){
			exbData.logo = topicId_logo + ".jpg";
		}

		return exbData;
	}

	function getFormdata(formName, dataObj){
		var form = document.forms[formName];
		var entryNames = [];
		for(var i=0;i<form.length;i++){
			if(form[i].name != ""){
				if(i==0 || (i>0 && form[i].name!=form[i-1].name)){
					entryNames.push(form[i].name);
				}
			}
		}

		for(var i=0;i<entryNames.length;i++){
			var key = entryNames[i];
			dataObj[key] = form[key].value;
		}

		return dataObj;
	}

	setTimeout(function(){
		$("#uploadify").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + topicId,
			'fileDesc' : 'Image Files',
			'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '2MB',

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

			// onUploadError : function(event, queueID, fileObj, errorObj) {
			// 	return false;
			// },
			onUploadError : function(file, errorCode, errorMsg, errorString) {
				alert('图片格式不支持,CMYK模式的JPG图片可能导致此错误，请将图片保存为PNG格式或RGB模式的JPG图片' );
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

	setTimeout(function(){
		$("#uploadify_logo").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + topicId_logo,
			'fileDesc' : 'Image Files',
			'fileTypeExts' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '2MB',

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

			// onUploadError : function(event, queueID, fileObj, errorObj) {
			// 	return false;
			// },
			onUploadError : function(file, errorCode, errorMsg, errorString) {
				alert('图片格式不支持,CMYK模式的JPG图片可能导致此错误，请将图片保存为PNG格式或RGB模式的JPG图片' );
				return false;
			},
			onUploadSuccess : function(file, data, response) {
				picFlag_logo = true;
				setTimeout(function(){
					$("#topic_image_logo").attr("src",PIC_BASE+topicId_logo+".jpg?"+Math.random());
				},300);
			}
		});
	},10);
});
