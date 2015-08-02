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

var APP_VERSION = "1.0.5";
function JloadRewrite(){
	if(!$.prototype.loadFlag){
		$.prototype.loadFlag = "Y";
		$.prototype.load = function(base) {
			return function() {
				var self = this;
				var url = arguments[0];
				url += "?ver="+APP_VERSION;
				
				if(arguments.length>1){
					base.call(self,url,arguments[1]);
				} else {
					base.call(self,url);
				}
			}
		}($.prototype.load);
	}
	
	jQuery._evalUrl = function( url ) {
		return jQuery.ajax({
			url: url+"?ver="+APP_VERSION,
			type: "GET",
			dataType: "script",
			async: false,
			global: false,
			"throws": true,
			cache: true
		});
	};
}
JloadRewrite();

function loadContent(){
	JloadRewrite();
	  $(".menu_Left").removeClass("menu_active");
	    $(this).addClass("menu_active");
	    var url = $(this).attr("eurl");
	    $(".nymain_right").load(url);
	    
	/*
	// 由于点击太快造成循环没结束再次运行该函数，导致不可预知的异常。注掉目前没发现问题 by javebean
	已修改uploadify源码，没有加载好的元素不会销毁by XY
	 */
	var uploadifyList = ['#uploadify','#uploadify_logo','#uploadify_vheader','#uploadify_idfront','#uploadify_idback'];
	for(var i in uploadifyList){
		var key = uploadifyList[i];
		if ($(key).length > 0) {
			$(key).uploadify('destroy');
		}
	}
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function setCookie(name,value)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + 60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}