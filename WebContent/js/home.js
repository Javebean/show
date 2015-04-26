var PT_TYPE_HOMEC = "homec";
var PIC_BASE = './resources/topicimages/s_';
var ROWS_PER_PAGE = 10;

$(document).ready(function(){
	
	//init page
	showTopicList();
	
	showZlzxList(1);
	
	function showZlzxList(page){
		var func = function(data){
			data = JSON.parse(data);
			rowCount = data.size;
			initPaging(page,rowCount);
			data = data.data;

			$('.zlzx_box').empty();
			var html = "";
			for(var i=0;i<data.length;i++){
				var topic = data[i];
				
				var timeStr = topic.publishTime;
				topic.publishTime = timeStr.substring(0,timeStr.lastIndexOf("."));
				
				html += 
					'<div>' +
	                '<a href="zlzxDetail.jsp?topicId='+topic.id+'">' + topic.title + '</a>' +
	                '<span class="pad_L_20">' + topic.publishTime + '</span>' +
	                '</div>';
			}
			$('.zlzx_box').append(html.replace(/undefined/g,""));
		}
		Zlzx.getShortZlzxForPage((page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE,func);
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
			showZlzxList(pageTo);
		});
		
	}
	
	function showTopicList(){
		var func = function(data){
			data = JSON.parse(data);
			data = data.data;
			listlength = data.length;

			$('.pt_cen_box').empty();
			var html = "";
			for(var i=0;i<data.length;i++){
				var topic = data[i];
				//For every last 4th topic in the row, add id="last"
				var subh = (i+1)%4==0?'id="last"':'';
				var content = topic.content;
				if(content.length>80){
					//content = content.substring(0,80)+'...';
				}
				
				var picPath = topic.picPath;
				if(picPath){
					picPath = PIC_BASE+picPath;
				} else{
					picPath = "";
				}
				html += 
					'<div class="mid-grid" ' +subh+ '>' +
					'<a href="#"><img src="' + picPath + '" title="image-name" /></a>' +
					'<h3>' + topic.title + '</h3>' +
					'<p>' + content + '</p>' +
					'<a class="mid-button" href="#">更多</a>' +
					'</div>';
			}
			html+='<div class="clear"> </div>';
			
			$('.pt_cen_box').append(html.replace(/undefined/g,""));
		}
		PTopic.getPTsByType(PT_TYPE_HOMEC,func);
	}
	
});
