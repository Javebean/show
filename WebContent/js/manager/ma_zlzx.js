$(document).ready(function(){
	var ROWS_PER_PAGE = 10;
	var rowCount = 0;
	
	//init page
	showTopicList(1);
	$("#add_topic").click("ma_addnewzlzx.jsp",jumpto);
	
	function jumpto(e){
		location.href=e.data;
	}
	
	function showTopicList(page){
		var func = function(data){
			data = JSON.parse(data);
			rowCount = data.size;
			if(!rowCount || rowCount==0) return;
			
			initPaging(page,rowCount);
			data = data.data;

			$('.pt_cen_box').empty();
			var html = "";
			var status = ["open","close"];
			for(var i=0;i<data.length;i++){
				var topic = data[i];
				
				var timeStr = topic.publishTime;
				if(timeStr){
					topic.publishTime = timeStr.substring(0,timeStr.lastIndexOf("."));
				}
				
				html += 
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.title + '</td>' +
	                '<td>' + topic.soruce + '</td>' +
	                '<td>' + topic.publishTime + '</td>' +
	                '<td>' + status[topic.state] + '</td>' +
	                '<td><button type="button" class="btn btn-sm btn-success edit_tp" eid="'+topic.id+'">编辑</button>' +
	                '<button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
	                '</tr>';
			}
			$('.pt_cen_box').append(html);
			$('.edit_tp').click(editTP);
			$('.delete_tp').click(deleteTP);
		}
		Zlzx.getShortZlzxForPage((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,func);
	}
	
	function editTP(){
		var eid = $(this).attr("eid");
		location.href="ma_editzlzx.jsp?topicId="+eid;
	}
	
	function deleteTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			
			if(data==true) location.reload();
		}
		Zlzx.deleteZlzxById(eid,func);
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
