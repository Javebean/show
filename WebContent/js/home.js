var PT_TYPE_HOMEC = "homec";
var PIC_BASE = './resources/topicimages/s_';

$(document).ready(function(){
	
	//init page
	showTopicList();
	
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
					content = content.substring(0,80)+'...';
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
			
			$('.pt_cen_box').append(html);
		}
		PTopic.getPTsByType(PT_TYPE_HOMEC,func);
	}
	
});
