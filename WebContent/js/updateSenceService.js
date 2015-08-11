//global data
var formData = {};
var scene_type_html = "";

$(document).ready(function(){
	var sceneParams = ["type","content"];
	var name = getCookie("user");
	
	if(name==null || getCookie("type")!=1|| name==""){
		$(".resultMsg h3").text("展商未登录，请登录后再来！");
		$(".resultMsg").show();
		return;
	}

	$(".addSceneBtn").click(showSceneBox);

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
}

	function showSceneBox(){
		$.colorbox({
			inline : true,
			innerWidth:970,
			speed: 0,
			href : "#sceneSelectBox",
			close : "关闭"
		});
	}
	/*现场服务申报记录func*/
	var loadSceneServ = function(data){

		if(data==null){
		//	$(".resultMsg").show();
			$(".showAll").removeClass("hide");
			return;
		}
		data = JSON.parse(data);
		if(data.error){
			$(".resultMsg h3").text(data.error);
			$(".resultMsg").show();
			return;
		}
	$(".showAll").removeClass("hide");
		formData = data.data;
		if(formData.length>0){
			var html2 = '<div class="fwts">您已经选择了以下现场服务</div>';
			$(".scene_item_selected").prepend(html2);
			$(".videoimg.addSceneBtn").remove();
			$(".update_scene_item").show();
		}
		var flag = false;
		for(var i=0;i<formData.length;i++){
			for(var j=0;j<CON_SCENE_TYPE.length;j++){
				var CST = CON_SCENE_TYPE[j];
				var image;
				var info=[];
				var index = -1;
				if(formData[i].type==CST.name){
					image=  CST.image;
					info = CST.info;
					index = j;
					break;
				}
				if(j==CON_SCENE_TYPE.length-1){
					$(".scene_other").val(formData[i].type);
					flag = true;
				}

			}
			if(flag){
				continue;
			}

			html = '<div class="fwnr" index="'+index+'" itemname="'+formData[i].type+'">'+
			  '<input type="button" class="remove_item"/><span class="ydcp"><img src="'+SCENE_IMAGE_BASE+image+'" /></span>'+
			  '<div class="ydxq"><div class="hy2" style="margin-top:16px;"><span class="hyspan2"><span class="fwnr_span1"><strong>名称：'+formData[i].type+'</strong></span></span>'+
		        '<span class="hyspan2"><span class="fwnr_span1"><strong>数量：</strong></span><input class="item_count" type="text" value="'+formData[i].content+'" /><span class="fwnr_span2"><strong>个</strong></span></span>'+
		       '</div><div class="hy2"><span class="hyspan2">'+info[0]+'</span>'+
		       '<span class="hyspan2">'+info[1]+'</span>'+
		       '<span class="hyspan2">'+info[2]+'</span></div></div></div>';
			$(".scene_item_selected").append(html);

		}

		//bind remove_item
		$(".scene_item_selected .remove_item").click(function(){
			$(this).parent().remove();
		});
	}

	/*现场服务申报记录*/
	SceneServ.getSceneServByUsername(name,loadSceneServ);

	//event binder
	$("#submitForm_senceSer").click(saveForm_senceSer);

	//公共方法
	function setViewForm(tableID,data){
		$(tableID+" .fview_value").each(function(){
			var key = $(this).attr("name");
			if(data[key]){
				$(this).val(data[key]);
			}
		});
	}

	/*现场服务更新*/
	function saveForm_senceSer(){
		
		var tt = ".5";
		
		var index = tt.indexOf(".")
		
		
		var flag = false;
		//对数量进行判断
		$(".fwnr").each(function(index){
			var num = $(this).find("input.item_count").val();
			
			if(!$.isNumeric(num)){
				jAlert("数量应该是整数","信息");
				flag = true;
				return;
			}else if(num<0){
				jAlert("数量不能小于0","信息");
				flag = true;
				return;
			}else if(num.indexOf(".")>=0){
				jAlert("数量不能是小数","信息");
				flag = true;
				return;
			}
			
		});
		
		if(flag){
			return;
		}
		
		var func = function(data){
			data = JSON.parse(data);
			if(data == true){
				$(".showAll").addClass("hide");
				$(".resultMsg h3").text("修改成功！");
				$(".resultMsg").show();

			}else{
				jAlert(data.message, "信息");
			}
		};

		var SceneServList = [];
		$(".fwnr").each(function(index){
			var obj ={};
			obj.type = $(this).attr("itemname");
			obj.content = $(this).find("input.item_count").val();
			SceneServList.push(obj);
		});

		var text = $(".scene_other").val();
		var obj2 ={};
		obj2.type =text;
		obj2.content = 1;
		if($.trim(text)!=""){
			SceneServList.push(obj2);
		}
		SceneServ.updateSceneServList(name,SceneServList,func);

	}
})
