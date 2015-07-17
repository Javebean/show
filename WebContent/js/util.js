var initPager = function(tagetObj, curPage, itemCount, pageSize, pageFunction) {
		var headhtml = '<ul class="pagination" style="margin:0px;">'+
		'<li><a class="page_first" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
	    var endhtml = '<li><a class="page_last" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li></ul>';
		if(itemCount<=pageSize){
			$(tagetObj).empty();
		} else {
			var pageCount = Math.ceil(itemCount/pageSize);
			var start = 1;
			var end = 1;
			if(pageCount<=10) {
				start = 1;
				end = pageCount;
			} else if(curPage<6){
				start = 1;
				end = 10;
			} else if(pageCount - curPage<10) {
				start = pageCount-9;
				end = pageCount;
			} else {
				start = curPage - 5;
				end = parseInt(curPage) + 4;
			}
			
			var html = "";
			html += headhtml;
			for(var index=start;index<=end;index++){
				if(index==curPage){
					html+='<li class="active"><a class="page_between" pageIndex="'+index+'">'+index+' <span class="sr-only">(current)</span></a></li>';
				} else {
					html+='<li><a class="page_between" pageIndex="'+index+'">'+index+'</a></li>';
				}
			}
			html += endhtml;
			
			$(tagetObj).html(html);
			$(tagetObj+" .page_first").click(function(){
				pageFunction(1);
			});
			$(tagetObj+" .page_last").click(function(){
				pageFunction(pageCount);
			});
			$(tagetObj+" .page_between").click(function(){
				var index = $(this).attr("pageIndex");
				pageFunction(index);
			});
			$(".paging").removeClass("hide");
		}
};