var PIC_BASE = 'resources/topicimages/';
var IMAGE_NOT_FOUND = "";
var search_state = -1;
$(document).ready(function(){
	var ROWS_PER_PAGE = 10;
	var rowCount = 0;

	var status = ["申请","已批准","已驳回"];
	var types = ["","展商","观众","媒体"];
	var shifou = ["否","是"];

	//init page
	showTopicList(1);

	//event binder
	$(".state_search").change(function(){
		search_state = $(this).val();
		showTopicList(1);
	});

	function jumpto(e){
		location.href=e.data;
	}

	function showTopicList(page){
		var func = function(data){
			data = JSON.parse(data);

			rowCount = data.size;
			if(!rowCount || rowCount==0) {
				$('.pt_cen_box').empty();
				$('.pt_cen_box').html('<td colspan="99" style="text-align:center;">暂无记录</td>');
				return;
			}

			initPaging(page,rowCount);
			data = data.data;

			$('.pt_cen_box').empty();
			var html = "";

			for(var i=0;i<data.length;i++){
				var topic = data[i];

				var timeStr = topic.applyTime;
				topic.applyTime = timeStr.substring(0,timeStr.lastIndexOf("."));

				var statestr = status[topic.state];
				var typestr = types[topic.type];
				var buyerstr = shifou[topic.buyer];

				html +=
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.name + '</td>' +
	                '<td>' + topic.phone + '</td>' +
	                '<td>' + typestr + '</td>' +
	                '<td>' + topic.org + '</td>' +
	                '<td>' + topic.applyTime + '</td>' +
	                '<td>' + statestr + '</td><td>';
	              //  '<td>' + status[topic.state] + '</td>' +
                if(topic.state == 0){
                	html += '<button type="button" class="btn btn-sm btn-success update_tp" eid="'+topic.id+'">批准</button>'+
                	'<button type="button" class="btn btn-sm btn-danger reject_tp" eid="'+topic.id+'">驳回</button>';
                }
                html +=
									'<button type="button" class="btn btn-sm btn-primary detail_tp" eid="'+topic.id+'" ename="'+topic.name+'" imagesrc="'+topic.photo+'">查看</button>' +
									'<button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
                '</tr>';
			}
			$('.pt_cen_box').append(html.replace(/undefined/g,""));
			$('.delete_tp').click(deleteTP);
			$('.update_tp').click(updateTP);
			$('.reject_tp').click(rejectTP);
			// $('.view_tp').click(viewTP);
			$('.detail_tp').click(detailTP);
		}
		Visitor.getVisitorForPageByState((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,search_state, func);
	}

	// function viewTP(){
	// 	var imagesrc = $(this).attr("imagesrc");
	// 	if(imagesrc != "undefined"){
	// 		$(".cp_image").attr("src",PIC_BASE + imagesrc);
	// 	} else {
	// 		$(".cp_image").attr("src",IMAGE_NOT_FOUND);
	// 	}
	// 	$(".cp_name").text($(this).attr("ename"));
	//
	// 	$.colorbox({
	// 		inline : true,
	// 		innerWidth:800,
	// 		innerHeight:600,
	// 		href : "#popup_box",
	// 		close : "关闭"
	// 	});
	// }

	function detailTP(){
		$("#popup_detail_box span").empty();
		var imagesrc = $(this).attr("imagesrc");
		if(imagesrc != "undefined"){
			$(".cp_image").attr("src",PIC_BASE + imagesrc);
		} else {
			$(".cp_image").attr("src",IMAGE_NOT_FOUND);
		}
		$(".cp_name").text($(this).attr("ename"));

		var eid = $(this).attr("eid");
		var func = function(data){
			data = JSON.parse(data);

			setViewTable(".visitor_detail",data);

			$.colorbox({
				inline : true,
				innerWidth:800,
				innerHeight:500,
				href : "#popup_detail_box",
				close : "关闭"
			});
		}
		Visitor.getVisitorById(eid,func);
	}

	function setViewTable(tableID,data){
		$(tableID+" .fview_value").each(function(){

			var key = $(this).attr("ename");
			if(data[key]){
					$(this).text(data[key]);
			}
			if(key == "state")
			{
				$(this).text(status[data[key]]);
			}
			else if(key == "buyer")
			{
				$(this).text(shifou[data[key]]);
			}
			else if(key == "type")
			{
				$(this).text(types[data[key]]);
			}

		});

	}


	function deleteTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			if(data==true) showTopicList(1);
		}
		Visitor.deleteVisitorById(eid,func);
	}

	function updateTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			if(data==true) {
				showTopicList(1);
			}
		}
		Visitor.updateVisitorState(eid,1,func);
	}

	function rejectTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			if(data==true) {
				showTopicList(1);
			}
		}
		Visitor.updateVisitorState(eid,2,func);
	}

	function initPaging(pageActive, rowCount){
		var pageCount = Math.ceil(rowCount/ROWS_PER_PAGE);
		if(pageCount>1){
			var html = '<li><a page="P" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>';
			for(var i=1;i<=pageCount;i++){
				if(pageActive!=i){
					html += '<li><a page="'+i+'">'+i+'</a></li>';
				}else{
					html += '<li class="active"><a page="'+i+'">'+i+'</a></li>';
				}
			}
			html+='<li><a page="N" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>';
			$(".pagination").html(html);
			$(".paging").removeClass("hide");
		}

		//bind events
		$(".paging li").click(function(){
			var pageTo = $(this).children().attr("page");
			if(pageTo == "P"){
				pageTo = 1;
			}else if(pageTo == "N"){
				pageTo = pageCount;
			}else{
				pageTo = parseInt(pageTo);
			}
			showTopicList(pageTo);
		});

	}
});
