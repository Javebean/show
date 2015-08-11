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
	
	if(name==null || getCookie("type")!=1|| name==""){
		$(".resultMsg h3").text("展商未登录，请登录后再来！");
		$(".resultMsg").show();
		return;
	}
	
	var loadDisplayItem = function(data){
		$(".showAll").removeClass("hide");
		if(data==null){
			return;
		}
		
		data = JSON.parse(data);
		if(data.error){
			$(".resultMsg h3").text(data.error);
			$(".resultMsg").show();

			return;
		}
		tableData = data.data;
		var input = $(".item_row input.cell");
		$(input[0]).val(tableData[0].name);
		$(input[1]).val(tableData[0].version);
		$(input[2]).val(tableData[0].number);
		$(input[3]).val(tableData[0].length);
		$(input[4]).val(tableData[0].width);
		$(input[5]).val(tableData[0].height);
		$(input[6]).val(tableData[0].weight);
		
		for(var i=1;i<tableData.length;i++){
			var html = '<tr class="item_row">';
			html+= '<td><div align="center"><input class="cell" value="'+tableData[i].name+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].version+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].number+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].length+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].width+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].height+'"/></div></td>';
			html+='<td><div align="center"><input class="cell" value="'+tableData[i].weight+'"/></div></td>';
			html += '<td><div align="center"><input class="btn_delrow delete_item" type="button" /></div></td>';
			$(".gridtable.showitems").append(html);
			
			$(".delete_item").last().click(deleteItem);
		}

	};
	
	
	DisplayItem.getDisplayItemByUsername(name,loadDisplayItem);

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

	$(".add_item").click(function(){
		addItem(this,7);
	});
	$(".delete_item").click(deleteItem);
	
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
	function deleteItem(){
		$(this).parent().parent().parent().remove();
	}

	//公共方法
	function setViewForm(tableID,data){
		$(tableID+" .fview_value").each(function(){
			var key = $(this).attr("name");
			if(data[key]){
				$(this).val(data[key]);
			}
		});
	}

	function saveForm(){

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
		
		var displayItemList =[];
		$(".item_row").each(function(){
			var inputVal = $(this).find(":input");
			var item = {};
			item.name = $(inputVal[0]).val();
			item.version = $(inputVal[1]).val();
			item.number = $(inputVal[2]).val();
			item.length = $(inputVal[3]).val();
			item.width = $(inputVal[4]).val();
			item.height= $(inputVal[5]).val();
			item.weight = $(inputVal[6]).val();
			
			displayItemList.push(item);
			
		});
		
		DisplayItem.updateDisplayItemList(name, displayItemList,func);

	}

});
