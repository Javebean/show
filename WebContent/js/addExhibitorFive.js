//global data
var formData = {};
var picFlag = false;
var picFlag_logo = false;
var PIC_BASE = 'resources/topicimages/';
var topicId = Math.uuid();
var topicId_logo = Math.uuid();
var scene_type_html = "";
var trans_type_html = "";
var reg_num = /^[1-9]\d*$/;
var reg_float = /^[1-9]\d*\.?\d*|0\.\d*[1-9]\d*$/;
$(document).ready(function(){
	var itemParams = ["name","version","number","length","width","height","weight"];
	var visitorParams = ["name","sex","position","phone"];
	var sceneParams = ["type","content"];
	var transParams = ["type","content","time"];

	//event binder
	$("#submitForm").click(saveForm);

	//添加html5弹出页面的提交事件
	$("#submitVisitorForm").click(saveVisitorForm);
	$(".delete_item").click(deleteItem);
	$(".add_item").click(function(){
		addItem(this,7);
	});
	$(".add_visitor").click(function(){
		//addItem(this,4);
		addVisitorLine();
	});
	$(".addSceneBtn").click(showSceneBox);

	function showSceneBox(){
		$.colorbox({
			inline : true,
			innerWidth:970,
			href : "#sceneSelectBox",
			close : "关闭"
		});
	}

	pageInit();

	//初始化现场服务和货运物流
	function pageInit(){
		//现场服务
		for(var i=0;i<CON_SCENE_TYPE.length;i++){
			var item = CON_SCENE_TYPE[i];
			scene_type_html += '<div class="oneItem" index="'+i+'">'+
				   '<div class="itemImage"><img src="'+SCENE_IMAGE_BASE+item.image+'" /></div>'+
			       '<div class="itemName">'+item.name+'</div>'+
			       '<div class="itemInfo">'+item.info[0]+'<br/>'+item.info[1]+'<br/>'+item.info[2]+'</div></div>';
		}
		$(".sceneItemBox").append(scene_type_html);

		$(".oneItem").click(function(){
			if($(this).hasClass("item_on")){
				$(this).removeClass("item_on");
			} else {
				$(this).addClass("item_on");
			}
		});

		//添加选中的物品到页面
		$(".scene_ok_btn").click(function(){
			var preItems = {};
			$(".fwnr").each(function(){
				var row = $(this);
				var name = row.attr("itemname");
				preItems[name] = row.find(".item_count").val();
			});

			var html = '<div class="fwts">您已经选择了以下现场服务</div>';
			$(".sceneItemBox .item_on").each(function(){
				var index = $(this).attr("index");
				var item = CON_SCENE_TYPE[index];
				html+= '<div class="fwnr" index="'+index+'" itemname="'+item.name+'">'+
					  '<input type="button" class="remove_item"/><span class="ydcp"><img src="'+SCENE_IMAGE_BASE+item.image+'" /></span>'+
					  '<div class="ydxq"><div class="hy2" style="margin-top:16px;"><span class="hyspan2"><span class="fwnr_span1"><strong>名称：'+item.name+'</strong></span></span>'+
				        '<span class="hyspan2"><span class="fwnr_span1"><strong>数量：</strong></span><input class="item_count" type="text" value=1 /><span class="fwnr_span2"><strong>个</strong></span></span>'+
				       '</div><div class="hy2"><span class="hyspan2">'+item.info[0]+'</span>'+
				       '<span class="hyspan2">'+item.info[1]+'</span>'+
				       '<span class="hyspan2">'+item.info[2]+'</span></div></div></div>';
			});

			$(".scene_item_selected").html(html);

			$(".fwnr").each(function(){
				var row = $(this);
				var name = row.attr("itemname");
				if(preItems[name]){
					row.find(".item_count").val(preItems[name]);
				}
			});

			$(".scene_item_selected .remove_item").click(function(){
				$(this).parent().remove();
			});

			$(".addSceneBtn").hide();
			$(".update_scene_item").show();
			$.colorbox.close();
		});

		$(".update_scene_item").click(function(){
			$(".sceneItemBox .item_on").removeClass("item_on");
			$(".fwnr").each(function(){
				var index = $(this).attr("index");
				$(".sceneItemBox .oneItem:eq("+index+")").addClass("item_on");
			});
			showSceneBox();
		});

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

		//货运物流
		var trans_type_html = "";
		for(var i=0;i<CON_TRANS_TYPE.length;i++){
			var item = CON_TRANS_TYPE[i];
			trans_type_html += '<div class="hy" index="'+i+'" itemname="'+item.name+'">'+
						'<div class="hy1" style="margin-top:16px;"><span class="hyspan"><span style="display:block; float:left; margin-left:20px"><strong>'+item.name+'</strong></span></span>'+
					'<span class="hyspan"><span style="display:block; float:left; "><strong>数量：</strong></span><input class="trans_count" type="text" /><span style="display:block; float:left;margin-left:4px "><strong>辆</strong></span></span>'+
					'<span class="hyspan"><span style="display:block; float:left; "><strong>时间：</strong></span><input class="trans_time" type="text" /><span style="display:block; float:left;margin-left:4px "><strong>小时</strong></span></span>'+
				'</div><div class="hy1"><span class="hyspan" style=" padding-left:40px; width:120px">'+item.info[0]+'</span>'+
					'<span class="hyspan">'+item.info[1]+'</span>'+
					'<span class="hyspan">'+item.info[2]+'</span></div></div>';
		}
		$(".trans_select_box").html(trans_type_html);
	}

	//增加html5的增加visitor的页面
	function addVisitorLine(){
		//Empty inputs
		$("#addVisitorform .tablezh input").val("");
		//Init image src
		$("#topic_image_vheader").attr("src","");
		$("#topic_image_idfront").attr("src","");
		$("#topic_image_idback").attr("src","");
		$(".cp_image").attr("src","");

		pic_icheader_ID = "";
		pic_icfro_ID = "";
		pic_icbac_ID = "";
		$.colorbox({
			inline : true,
			innerWidth:750,
			speed: 0,
			href : "#addVisitorBox",
			close : "关闭",
			onClosed : function(){

			}
		});
	}

	function saveVisitorForm(){
		if(!$("#addVisitorform").valid()){
			jAlert("请检查输入内容", "信息");
			return;
		}
		//针对证件类型号码的判断
		//身份证
		if($("select[name=idType]").val()==0){
			var value = $("input[name=idNo]").val();
			value=$.trim(value);
			var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if(!reg.test( value ) || value.length!=18){
				jAlert("请输入有效的18位身份证号码", "信息");
				return;
			}
		}
		//护照
		if($("select[name=idType]").val()==1){
			var value = $("input[name=idNo]").val();
			value=$.trim(value);
			if(!$.isNumeric( value ) || value.length!=11){
				jAlert("请输入有效的11位护照号码", "信息");
				return;
			}
		}
		//港澳台通行证 2015-8-6.暂时对港澳台和台胞证不做限定
		/*if($("select[name=idType]").val()==2){
			var value = $("input[name=idNo]").val().trim();
			if(!$.isNumeric( value ) || value.length!=11){
				jAlert("请输入有效的11位港澳台通行证号码", "信息");
				return;
			}
		}*/
		//台胞证
		/*if($("select[name=idType]").val()==3){
			var value = $("input[name=idNo]").val().trim();
			if(!$.isNumeric( value ) || (value.length!=9 && value.length!=8)){
				jAlert("请输入有效的8或9位台胞证号码", "信息");
				return;
			}
		}*/
		/*证件照，身份证正面照，身份证背面照改为非必填*/
		/*if(!pic_icheader_ID || !pic_icfro_ID || !pic_icbac_ID){
			jAlert("请上传证件照，身份证正面照，身份证背面照。", "信息");
			return;
		}*/
		var formData = getFormdata("addVisitorform");
		formData.photo = pic_icheader_ID + ".jpg";
		formData.idFont = pic_icfro_ID + ".jpg";
		formData.idBack = pic_icbac_ID + ".jpg";

		var html = '<tr class="item_row">'+
					'<td><div align="center">'+formData.name+'</div></td>' +
					'<td><div align="center">'+formData.sex+'</div></td>' +
					'<td><div align="center">'+formData.position+'</div></td>' +
					'<td><div align="center">'+formData.phone+'</div></td>' +
					'<td><div align="center">'+formData.idNo+'</div></td>';

		html += '<td><div align="center"><input class="btn_delrow delete_item" type="button" /></div></td>';

		$(".visitors").append(html.replace(/undefined/g,""));
		$(".delete_item").click(deleteItem);
		$(".visitors .item_row").last().data("newvisitor",formData);

		$.colorbox.close();
	}

	//test
	$(".test_btn").click(function(){
		var visitor = getDymiTableData(".visitors", visitorParams);
		var displayItem = getDymiTableData(".showitems", itemParams);
		getFormdata("regForm");
	});

	function deleteItem(){
		$(this).parent().parent().parent().remove();
	}

	function addItem(obj, count){
		var html = '<tr class="item_row">';
		var cell = '<td><div align="center"><input class="cell"/></div></td>';
		for(var i=0;i<count;i++){
			html += cell;
		}
		html += '<td><div align="center"><input class="btn_delrow delete_item" type="button" /></div></td>';

		$(obj).parent().prev().append(html.replace(/undefined/g,""));
		$(".delete_item").click(deleteItem);
	}

	function getDymiTableData(tableID, params){
		var itemList = [];
		$(tableID+" .item_row").each(function(){
			var item = {};
			var row = $(this);

			if(row.find("input").eq(0).val()!=""){
				for(var i=0;i<params.length;i++){
					item[params[i]] = row.find("input").eq(i).val();
				}
				itemList.push(item);
			}
		});
		return itemList;
	}

	function getDymiTableDataServ(){
		var itemList = [];
		$(".fwnr").each(function(){
			var item = {};
			var row = $(this);

			if(row.find(".item_count").val()!=""){
				item[sceneParams[0]] = row.attr("itemname");
				item[sceneParams[1]] = row.find(".item_count").val();
				itemList.push(item);
			}
		});
		return itemList;
	}

	function getDymiTableDataTrans(){
		var itemList = [];
		$(".hy").each(function(){
			var item = {};
			var row = $(this);

			if(row.find(".trans_check").is(':checked')){
				item[transParams[0]] = row.attr("itemname");
				item[transParams[1]] = row.find(".trans_count").val();
				item[transParams[2]] = row.find(".trans_time").val();
				itemList.push(item);
			}
		});
		return itemList;
	}

	function saveForm(){
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
			return;
		}
		var formData = getFormdata("regForm");
		formData.tytzzs= $("input[name='tytzzs']:checked").val();
		formData.swzs= $("input[name='swzs']:checked").val();
		var btsl = formData.btsl;
		if(btsl == "标摊每个3m X 3m"){
			$(window).scrollTop(1300);
			jAlert("请输入标摊数量", "信息");
			return;
		}

		if(isNaN(parseInt(btsl))){
			$(window).scrollTop(1300);
			jAlert("标摊数量请输入整数", "信息");
			return;
		} else {
			formData.btsl = parseInt(btsl);
		}

		if(picFlag_logo){
			formData.logo = topicId_logo + ".jpg";
		}

		//获取展品和参展人员数据
		var visitor = getDymiTableData(".visitors", visitorParams);
		
		//添加对addExhibitorFive的邮箱验证 by javebean
		var emailvalue = $("input[name=email]").val();
		emailvalue = $.trim(emailvalue);
		var emailreg = /^([\w-_]+(?:\.[\w-_]+)*)@((?:[a-z0-9]+(?:-[a-zA-Z0-9]+)*)+\.[a-z]{2,6})$/i ;
		//	alert(emailvalue);
		if(!emailreg.test(emailvalue))
		{
			jAlert("请输入有效的电子邮箱", "信息");
			return;
		}
		
		var displayItem = getDymiTableData(".showitems", itemParams);
		//var itemParams = ["name","version","number","length","width","height","weight"];
		for(var i=0;i<displayItem.length;i++){
			var item = displayItem[i];
			if(!reg_num.test(item.number) && item.number!=""){
				$(window).scrollTop(1500);
				jAlert("展品数量请输入整数", "信息");
				return;
			} else if((!$.isNumeric(item.length) && $.trim(item.length)!="")
					||(!$.isNumeric(item.width) && $.trim(item.width)!="")
					||(!$.isNumeric(item.height) && $.trim(item.height)!="")
					||(!$.isNumeric(item.weight) && $.trim(item.weight)!="")){
				$(window).scrollTop(1500);
				jAlert("展品长度，宽度，高度，重量请输入数字或小数", "信息");
				return;
			}
		}

		//获取参展服务数据
		var sceneServ = getDymiTableDataServ();
		for(var i=0;i<sceneServ.length;i++){
			var item = sceneServ[i];
			if(!reg_num.test(item.content) && item.content!=""){
				$(window).scrollTop(2200);
				jAlert("现场服务数量请输入整数", "信息");
				return;
			}
		}

		var transportation = getDymiTableDataTrans();
		for(var i=0;i<transportation.length;i++){
			var item = transportation[i];
			if(!reg_num.test(item.content) || item.content=="" || !reg_num.test(item.time) || item.time==""){
				$(window).scrollTop(2400);
				jAlert("货运物流数量，时间请输入整数", "信息");
				return;
			}
		}

		var construction = [];
		if(picFlag){
			var cdata = {};
			cdata.picture = topicId + ".jpg";
			construction.push(cdata);
		}

		var func = function(data){
			data = JSON.parse(data);

			if(data.result == true){
				//location.href="ma_zlzx.jsp?menu=1";
				$(".userForm").hide();
				$("#login_id").text(data.username);
				$("#login_pass").text(data.password);
				$(".resultMsg").show();

			}else{
				jAlert(data.message, "信息");
			}
		};
		Exhibitor.saveTotalExhibitInfo(formData,construction,transportation,sceneServ,visitor,displayItem,func);
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
		$("#uploadify").uploadifive({
			/*'queueID'          : 'queue',*/
			'uploadScript' : 'imageUpload?topicId=' + topicId,
			'fileDesc' : 'Image Files',
			'fileType' : 'image/*',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '2MB',

			onError: function(errorType, file) {
				var msgText = "上传失败\n";
				switch (errorType) {
						case 'FILE_SIZE_LIMIT_EXCEEDED':
								msgText += "文件大小超过限制(2MB)";
								break;
						default:
								msgText += "错误代码：" + errorType + "\n" + errorMsg;
				}
        alert(msgText);
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadComplete : function(file, data, response) {
				picFlag = true;
				setTimeout(function(){
					$("#topic_image").attr("src",PIC_BASE+topicId+".jpg?"+Math.random());
				},300);
			}
		});
	},10);

	setTimeout(function(){
		$("#uploadify_vheader").uploadifive({
			/*'queueID'          : 'queue',*/
			'uploadScript' : 'imageUpload?topicId=' + 'NEWID',
			'fileDesc' : 'Image Files',
			'fileType' : 'image/*',
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
        alert(msgText+"a");
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadComplete : function(file, data, response) {
				pic_icheader_ID = data;
				setTimeout(function(){
					$("#topic_image_vheader").attr("src",PIC_BASE+pic_icheader_ID+".jpg?"+Math.random());
					//暂时注掉图片切割
					//$("#img_preview_vheader").attr("src",PIC_BASE+pic_icheader_ID+".jpg?"+Math.random());
					//$('#topic_image_vheader').imgAreaSelect({ onSelectChange: preview,aspectRatio: '118:149',autoHide: true });
				},300);
			}
		});
	},10);



	setTimeout(function(){
		$("#uploadify_logo").uploadifive({
			/*'queueID'          : 'queue',*/

			'uploadScript' : 'imageUpload?topicId=' + topicId_logo,
			'fileDesc' : 'Image Files',
			'fileType' : 'image/*',
			'multi' : false,
			'queueSizeLimit' : 1,
			'fileSizeLimit' : '2MB',

			onError: function(errorType, file) {
        var msgText = "上传失败\n";
				switch (errorType) {
						case 'FILE_SIZE_LIMIT_EXCEEDED':
								msgText += "文件大小超过限制(2MB)";
								break;
						default:
								msgText += "错误代码：" + errorType + "\n" + errorMsg;
				}
        alert(msgText);
    },

			onUploadError : function(event, queueID, fileObj, errorObj) {
				return false;
			},
			onUploadComplete : function(file, data, response) {
				picFlag_logo = true;
				setTimeout(function(){
					$("#topic_image_logo").attr("src",PIC_BASE+topicId_logo+".jpg?"+Math.random());
				},300);
			}
		});
	},10);
});

setTimeout(function(){
	$("#uploadify_idfront").uploadifive({
		/*'queueID'          : 'queue',*/
		'uploadScript' : 'imageUpload?topicId=' + 'NEWID',
		'fileDesc' : 'Image Files',
		'fileType' :  'image/*',
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

		onUploadError : function(event, queueID, fileObj, errorObj) {
			return false;
		},
		onUploadComplete : function(file, data, response) {
			pic_icfro_ID = data;
			setTimeout(function(){
				$("#topic_image_idfront").attr("src",PIC_BASE+pic_icfro_ID+".jpg?"+Math.random());
			},300);
		}
	});
},10);

setTimeout(function(){
	$("#uploadify_idback").uploadifive({
		/*'queueID'          : 'queue',*/

		'uploadScript' : 'imageUpload?topicId=' + 'NEWID',
		'fileDesc' : 'Image Files',
		'fileType' : 'image/*',
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

		onUploadError : function(event, queueID, fileObj, errorObj) {
			return false;
		},
		onUploadComplete : function(file, data, response) {
			pic_icbac_ID = data;
			setTimeout(function(){
				$("#topic_image_idback").attr("src",PIC_BASE+pic_icbac_ID+".jpg?"+Math.random());
			},300);
		}
	});
},10);
