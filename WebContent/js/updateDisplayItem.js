//global data
$(document).ready(function(){
	var itemParams = ["name","version","number","length","width","height","weight","showType"];
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
			$(".showAll").addClass("hide");
			return;
		}
		tableData = data.data;
		var input = $(".item_row input.cell");
		for(var i=0;i<input.length;i++){
		/*	console.log(tableData[0][itemParams[i]]);*/
			$(input[i]).val(tableData[0][itemParams[i]]);
		}
		for(var i=1;i<tableData.length;i++){
			var html = '<tr class="item_row">';
			for(var j=0;j<itemParams.length;j++){
				html+= '<td><div align="center"><input class="cell" value="'+tableData[i][itemParams[j]]+'"/></div></td>';
			}
			html += '<td><div align="center"><input class="btn_delrow delete_item" type="button" /></div></td>';
			$(".gridtable.showitems").append(html);
			$(".delete_item").last().click(deleteItem);
		}

	};
	
	
	DisplayItem.getDisplayItemByUsername(name,loadDisplayItem);

	//event binder
	$("#submitForm").click(saveForm);

	$(".add_item").click(function(){
		addItem(this,8);
	});
	addItem(".add_item",8);
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


	function saveForm(){
		var flag = false;
		$(".item_row").each(function(){
			var inputVal = $(this).find(":input");
			var item_name = $(inputVal[0]).val();
			var item_num = $(inputVal[2]).val();
			var item_length = $(inputVal[3]).val();
			var item_width = $(inputVal[4]).val();
			var item_height =  $(inputVal[5]).val();
			var item_weight = $(inputVal[6]).val();
			var item_showtype = $(inputVal[7]).val();
			
			if($.trim(item_name)==""){
				jAlert("展品名称不能为空！", "信息");
				flag = true;
				return ;
			}
			
			if(item_showtype == ""){
				jAlert("请输入展示形式", "信息");
				flag = true;
				return;
			}
			
			if((!$.trim(item_num)==""&&!$.isNumeric(item_num))||(!$.trim(item_length)==""&&!$.isNumeric(item_length))||(!$.trim(item_width)==""&&!$.isNumeric(item_width))||(!$.trim(item_height)==""&&!$.isNumeric(item_height))
					||(!$.trim(item_weight)==""&&!$.isNumeric(item_weight))){
				jAlert("请输入合法的数字！", "信息");
				flag = true;
				return ;
			}else if((!$.trim(item_num)==""&&item_num<0)||(!$.trim(item_length)==""&&item_length<0)||(!$.trim(item_width)==""&&item_width<0)||(!$.trim(item_height)==""&&item_height<0)||(!$.trim(item_weight)==""&&item_weight<0)){
				jAlert("数字不能小于0！", "信息");
				flag = true;
				return ;
			}else if((!$.trim(item_num)==""&&item_num.indexOf(".")>=0)){
				jAlert("数量必须为整数！", "信息");
				flag = true;
				return ;
			}
			
		});
		
		if(flag){
			return;
		}
		
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
			for(var i=0;i<inputVal.length;i++){
				item[itemParams[i]] = $(inputVal[i]).val();
			}
			displayItemList.push(item);
			
		});
		
		DisplayItem.updateDisplayItemList(name, displayItemList,func);
	}

});
