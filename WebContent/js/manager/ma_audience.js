$(document).ready(function(){
	var ROWS_PER_PAGE = 10;
	var rowCount = 0;
	var search_name = "";

	var buyer = ["观众","采购商"];
	//init page
	showTopicList(1);
	
	//event binder
	$(".do_search").click(function(){
		doSearch();
	});
	
	$(window).keydown(function(event){
		if(event.keyCode==13){
			doSearch();
		}
	});
	
	function doSearch(){
		search_name = $(".name_search").val();
		showTopicList(1);
	}

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
			var status = ["申请","批准","驳回"];

			for(var i=0;i<data.length;i++){
				var topic = data[i];

				var timeStr = topic.applyTime;
				topic.applyTime = timeStr.substring(0,timeStr.lastIndexOf("."));

				var buyerstr = buyer[topic.buyer];

				html +=
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.name + '</td>' +

	                '<td>' + topic.phone + '</td>' +
	                '<td>' + topic.email + '</td>' +

	                '<td>' + topic.org + '</td>' +
	                '<td>' + buyerstr + '</td>' +
	              
	                '<td>' + topic.username + '</td>' +
	                '<td>' + topic.applyTime + '</td>' +
	              //  '<td>' + status[topic.state] + '</td>' +
								'<td><button type="button" class="btn btn-sm btn-primary view_tp" eid="'+topic.id+'">查看</button>'+
	                '<td><button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
	                '</tr>';
			}
			$('.pt_cen_box').append(html.replace(/undefined/g,""));
			$('.view_tp').click(viewTP);
			$('.delete_tp').click(deleteTP);
		}
		Audience.getAudienceForPage((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,search_name,func);
	}

	function viewTP(){
		$("#popup_box span").empty();
		var eid = $(this).attr("eid");
		var func = function(data){
			data = JSON.parse(data);
			setViewTable(".audience_detail",data);
		//	$(".dym_row").remove();
			$.colorbox({
				inline : true,
				innerWidth:800,
				innerHeight:500,
				href : "#popup_box",
				close : "关闭"
			});
		}
		Audience.getAudienceById(eid,func);
	}

	function setViewTable(tableID,data){
		$(tableID+" .fview_value").each(function(){

			var key = $(this).attr("ename");

			if(data[key]){
				$(this).text(data[key]);
			}
			if(key == "buyer")
			{
				$(this).text(buyer[data[key]]);
			}
		});
	}


	function deleteTP(){
		var eid = $(this).attr("eid");
		var func = function(data){

			if(data==true) location.reload();
		}
		Audience.deleteAudienceById(eid,func);
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
