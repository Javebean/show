var PIC_BASE = 'resources/topicimages/';
var IMAGE_NOT_FOUND = "";
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
			console.log(data);
			rowCount = data.size;
			if(!rowCount || rowCount==0) return;
				
			initPaging(page,rowCount);
			data = data.data;

			$('.pt_cen_box').empty();
			var html = "";
			var status = ["申请","批准","驳回"];
			for(var i=0;i<data.length;i++){
				var topic = data[i];
				
				var timeStr = topic.applyTime;
				topic.applyTime = timeStr.substring(0,timeStr.lastIndexOf("."));
				
				html += 
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.orgName + '</td>' +
	                '<td>' + topic.region + '</td>' +
	                '<td>' + topic.applyTime + '</td>' +
	              //  '<td>' + status[topic.state] + '</td>' +
	                '<td><button type="button" class="btn btn-sm btn-success view_tp" eid="'+topic.id+'">查看</button>'+
	                '<td><button type="button" class="btn btn-sm btn-success update_tp" eid="'+topic.id+'">批准</button>'+
	                '<button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
	                '</tr>';
			}
			$('.pt_cen_box').append(html);
			$('.delete_tp').click(deleteTP);
			$('.update_tp').click(updateTP);
			$('.view_tp').click(viewTP);
		}
		Exhibitor.getShortExhibitorsForPage((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,func);
	}
	
	var itemParams = ["name","version","number","length","width"];
	var visitorParams = ["name","sex","position","phone"];
	function viewTP(){
		$("#popup_box span").empty();
		var eid = $(this).attr("eid");
		var func = function(data){
			data = JSON.parse(data);
			console.log(data);
			setViewTable(".exb_detail",data.exhibitors);
			setDymTable(".showitems", itemParams, data.displayItem);
			setDymTable(".visitors", visitorParams, data.visitor);
			if(data.sceneServ.length>0){
				var sdata = data.sceneServ[0];
				$(".scene_type").text(sdata.type);
				$(".scene_content").text(sdata.content);
			}
			if(data.transportation.length>0){
				var sdata = data.transportation[0];
				$(".trans_type").text(sdata.type);
				$(".trans_content").text(sdata.content);
			}
			if(data.construction.length>0){
				var sdata = data.construction[0];
				$("#cons_image").attr("src",PIC_BASE+sdata.picture+"?"+Math.random());
			} else {
				$("#cons_image").attr("src",IMAGE_NOT_FOUND);
			}
			
			$.colorbox({
				inline : true,
				innerWidth:800,
				innerHeight:500,
				href : "#popup_box",
				close : "关闭"
			});
		}
		Exhibitor.getTotalExhibitInfoById(eid,func);
	}
	
	function setViewTable(tableID,data){
		$(tableID+" .fview_value").each(function(){
			var key = $(this).attr("ename");
			if(data[key]){
				$(this).text(data[key]);
			}
		});
	}
	
	function setDymTable(tableID, params, data){
		var html = '';
		for(var i=0;i<data.length;i++){
			html += '<tr class="item_row">';
			var row = data[i];
			for(var j=0;j<params.length;j++){
				if(row[params[j]]){
					html += '<td>'+ row[params[j]] +'</td>';
				} else {
					html += '<td></td>';
				}
			}
			html += '</tr>';
		}
		
		$(tableID+" .item_row").remove();
		$(tableID).append(html);
	}
	
	function deleteTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			if(data==true) location.reload();
		}
		Exhibitor.deleteExhibitorById(eid,func);
	}
	
	function updateTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			if(data==true) location.reload();
		}
		Exhibitor.updateExhibitorState(eid,1,func);
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
