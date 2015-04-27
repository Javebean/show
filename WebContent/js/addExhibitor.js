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
	var transParams = ["type","content"];
	 
	//event binder
	$("#submitForm").click(saveForm);
	$(".delete_item").click(deleteItem);
	$(".add_item").click(function(){
		addItem(this,7);
	});
	$(".add_visitor").click(function(){
		addItem(this,4);
	});
	$(".add_scene").click(function(){
		addItemServ(this,scene_type_html);
	});
	$(".add_trans").click(function(){
		addItemServ(this,trans_type_html);
	});
	
	pageInit();
	function pageInit(){
		for(var i=0;i<CON_SCENE_TYPE.length;i++){
			scene_type_html += '<option value="'+CON_SCENE_TYPE[i]+'">'+CON_SCENE_TYPE[i]+'</option>';
		}
		for(var i=0;i<CON_TRANS_TYPE.length;i++){
			trans_type_html += '<option value="'+CON_TRANS_TYPE[i]+'">'+CON_TRANS_TYPE[i]+'</option>';
		}
		$(".sceneserv select").append(scene_type_html);
		$(".transserv select").append(trans_type_html);
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
	
	function addItemServ(obj,selectHtml){
		var html = '<tr class="item_row">';
		html += '<td><div align="center"><select class="select">'+selectHtml+
				'</select></div></td><td><div align="center"><input class="cell"/></div></td>';
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
	
	function getDymiTableDataServ(tableID, params){
		var itemList = [];
		$(tableID+" .item_row").each(function(){
			var item = {};
			var row = $(this);
			
			if(row.find("input").eq(0).val()!=""){
				item[params[0]] = row.find("select").val();
				item[params[1]] = row.find("input").eq(0).val();
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
		var displayItem = getDymiTableData(".showitems", itemParams);
		//var itemParams = ["name","version","number","length","width","height","weight"];
		for(var i=0;i<displayItem.length;i++){
			var item = displayItem[i];
			if(!reg_num.test(item.number) && item.number!=""){
				$(window).scrollTop(1500);
				jAlert("展品数量请输入整数", "信息");
				return;
			} else if((!reg_float.test(item.length) && item.length!="")
					||(!reg_float.test(item.width) && item.width!="")
					||(!reg_float.test(item.height) && item.height!="")
					||(!reg_float.test(item.weight) && item.weight!="")){
				$(window).scrollTop(1500);
				jAlert("展品长度，宽度，高度，重量请输入数字或小数", "信息");
				return;
			}
		}
		
		//获取参展服务数据
		var transportation = getDymiTableDataServ(".transserv", transParams);
		for(var i=0;i<transportation.length;i++){
			var item = transportation[i];
			if(!reg_num.test(item.content) && item.content!=""){
				$(window).scrollTop(2400);
				jAlert("货运物流数量请输入整数", "信息");
				return;
			}
		}
		var sceneServ = getDymiTableDataServ(".sceneserv", sceneParams);
		for(var i=0;i<sceneServ.length;i++){
			var item = sceneServ[i];
			if(!reg_num.test(item.content) && item.content!=""){
				$(window).scrollTop(2200);
				jAlert("现场服务数量请输入整数", "信息");
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
	
	setTimeout(function(){
		$("#uploadify_logo").uploadify({
			'swf'      : 'uploadify.swf',
			'uploader' : 'imageUpload?topicId=' + topicId_logo,
			'fileDesc' : 'Image Files',
			'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
			'multi' : false,
			'sizeLimit' : 3000000,
			
			onUploadError : function(event, queueID, fileObj, errorObj) {
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