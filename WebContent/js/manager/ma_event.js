$(document).ready(function(){
	var ROWS_PER_PAGE = 10;
	var rowCount = 0;

	//init page
	showTopicList(1);

	function jumpto(e){
		location.href=e.data;
	}

	function showTopicList(page){
		var func = function(data){
			data = JSON.parse(data);
			rowCount = data.size;
			if(!rowCount || rowCount==0) return;

			initPager(".paging_bar",page,rowCount,ROWS_PER_PAGE,showTopicList);
			data = data.data;

			$('.pt_cen_box').empty();
			var html = "";

			for(var i=0;i<data.length;i++){
				var topic = data[i];

				var timeStr = topic.applyTime;
				topic.applyTime = timeStr.substring(0,timeStr.lastIndexOf("."));

				html +=
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.organizer + '</td>' +
	                '<td>' + topic.eventName + '</td>' +
	                '<td>' + topic.period + '</td>' +
	                '<td>' + topic.type + '</td>' +
	                '<td>' + topic.invitee + '</td>' +
	                '<td>' + topic.note + '</td>' +
	                '<td>' + topic.applyTime + '</td>' +
	              //  '<td>' + status[topic.state] + '</td>' +
	                '<td><button type="button" class="btn btn-sm btn-primary view_tp" eid="'+topic.id+'">查看</button>'+
	                '<button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
	                '</tr>';
			}
			$('.pt_cen_box').append(html.replace(/undefined/g,""));
			$('.view_tp').click(viewTP);
			/*$('.delete_tp').click(deleteTP);*/
			$('.delete_tp').click(function(){
				 if (!confirm("确认要删除？")) {
					 	return false;
			        }else{
			        	deleteTP($(this).attr("eid"));
			        }
			});
		}
		Event.getEventForPage((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,func);
	}

	function viewTP(){
		$("#popup_box span").empty();
		var eid = $(this).attr("eid");
		var func = function(data){
			data = JSON.parse(data);

			setViewTable(".event_detail",data);

		//	$(".dym_row").remove();
			$.colorbox({
				inline : true,
				innerWidth:800,
				innerHeight:500,
				href : "#popup_box",
				close : "关闭"
			});
		}
		Event.getEventById(eid,func);
	}

	function setViewTable(tableID,data){
		$(tableID+" .fview_value").each(function(){

			var key = $(this).attr("ename");

			if(data[key]){
				$(this).text(data[key]);

			}
		});
	}

	function deleteTP(eid){
		//var eid = $(this).attr("eid");
		var func = function(data){

			if(data==true) location.reload();
		}
		Event.deleteEventById(eid,func);
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
