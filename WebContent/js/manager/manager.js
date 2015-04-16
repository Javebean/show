$(document).ready(function(){
	var PT_TYPE_HOMEC = "homec";
	var ROWS_PER_PAGE = 10;
	
	//init page
	showTopicList();
	$("#add_topic").click("ma_addnewtopic.jsp",jumpto);
	
	initPaging(1,51);
	
	function jumpto(e){
		location.href=e.data;
	}
	
	function showTopicList(){
		var func = function(data){
			data = JSON.parse(data);
			data = data.data;
			listlength = data.length;

			$('.pt_cen_box').empty();
			var html = "";
			var status = ["close","open","deleted"];
			for(var i=0;i<data.length;i++){
				var topic = data[i];
				//For every last 4th topic in the row, add id="last"
				var content = topic.content;
				if(content.length>20){
					content = content.substring(0,20)+'...';
				}
				var picPath = topic.picPath;
				if(!picPath) picPath = "";
				
				html += 
					'<tr>' +
					'<td>' + (i+1) + '</td>' +
	                '<td>' + topic.title + '</td>' +
	                '<td>' + picPath + '</td>' +
	                '<td>' + content + '</td>' +
	                '<td>' + topic.updatetime + '</td>' +
	                '<td>' + status[topic.status] + '</td>' +
	                '<td><button type="button" class="btn btn-sm btn-success edit_tp" eid="'+topic.id+'">编辑</button>' +
	                '<button type="button" class="btn btn-sm btn-danger delete_tp" eid="'+topic.id+'">删除</button></td>'+
	                '</tr>';
			}
			$('.pt_cen_box').append(html);
			$('.edit_tp').click(editTP);
			$('.delete_tp').click(deleteTP);
		}
		PTopic.getPTsByType(PT_TYPE_HOMEC,func);
	}
	
	function editTP(){
		var eid = $(this).attr("eid");
		location.href="ma_edittopic.jsp?topicId="+eid;
	}
	
	function deleteTP(){
		var eid = $(this).attr("eid");
		var func = function(data){
			console.log(data);
			if(data==true) location.reload();
		}
		PTopic.deletePTById(eid,func);
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
			initPaging(pageTo, rowCount);
		});
		
	}
});
