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
	var itemParams = ["name","version","number","length","width","height","weight"];
	var visitorParams = ["name","sex","position","phone"];
	var sceneParams = ["type","content"];
	var transParams = ["type","content","time"];


	var name = getCookie("user");
	console.log(name);
	if(name==null || getCookie("type")!=1|| name==""){
		$(".resultMsg h3").text("展商未登录，请登录后再来！");
		$(".resultMsg").show();
		return;
	}
	$(".showAll").removeClass("hide");
	/*现场服务申报记录func*/
	var loadSceneServ = function(data){
		
		if(data==null){
			$(".resultMsg").show();
			return;
		}
		data = JSON.parse(data);
		if(data.error){
			$(".resultMsg h3").text(data.error);
			//$(".resultMsg").show();
			return;
		}

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
		var func = function(data){
			data = JSON.parse(data);
			if(data == true){
				$(".showAll").hide();
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