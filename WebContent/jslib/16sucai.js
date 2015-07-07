// JavaScript Document
$(document).ready(function()
{
	//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked
	$("#firstpane a.menu_head").click(function()
    {
		 $(".menu_Left").removeClass("menu_active");
		$(this).css({backgroundImage:"url(images/cdhli2.jpg)",color:"#fff"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
       	$(this).siblings().css({backgroundImage:"",color:"#222222"});
	});
	//slides the element with class "menu_body" when mouse is over the paragraph
	$("#secondpane a.menu_head").mouseover(function()
    {
	     $(this).css({backgroundImage:"url(images/down.png)"}).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
         $(this).siblings().css({backgroundImage:"url(images/left.png)"});
	});


});

function loadContent(){
	var uploadifyList = ['#uploadify','#uploadify_logo','#uploadify_vheader','#uploadify_idfront','#uploadify_idback'];
	for(var i in uploadifyList){
		var key = uploadifyList[i];
		if ($(key).length > 0) {
			$(key).uploadify('destroy');
		}
	}
	
    $(".menu_Left").removeClass("menu_active");
    $(this).addClass("menu_active");
    var url = $(this).attr("eurl");
    $(".nymain_right").load(url);
}