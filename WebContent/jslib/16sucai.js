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

var APP_VERSION = "1.0.8";
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

var browser={};
browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
browser.msie = /msie/.test(navigator.userAgent.toLowerCase());
//检查用户有没有安装FLASH
function checkFlash(){
	var flash = true;
	
	if(browser.msie){
	    try{
	        var swf1 = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
	    }
	    catch(e){
	    	flash = false;
	    }
	} else {
	    try{
	        var swf2 = navigator.plugins['Shockwave Flash'];
	        if(swf2 == undefined){
	        	flash = false;
	        }
	    }
	    catch(e){
	    	flash = false;
	    }
	}
	
	if(!flash){
		jAlert('未安装FLASH插件，请安装后刷新页面重试！<a style="color:blue;text-decoration:underline;" href="http://dlsw.baidu.com/sw-search-sp/soft/34/17153/flashplayer_18_ax_debug_V18.0.0.209.1437017012.exe">FLASH下载</a>', "信息");
	}
}

//自定义弹出框组件，目前只支持一个页面有一个这个框
function popBox(action){
	var shadow = $(".popBox_shadow");
	var frame = $(".popBox_frame");
	if(shadow.length!=1 || frame.length!=1){
		return;
	}
	if(action=="CLOSE"){
		shadow.hide();
		frame.hide();
	}
	if(action=="OPEN"){
		shadow.show();
		var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
		frame.css("top",scrollTop);
		var winWidth = $(window).width();
		if(winWidth>frame.width()){
			var left = (winWidth-frame.width())/2;
			frame.css("left",left);
		}else{
			frame.css("left",0);
		}
		frame.show();
		
		$("#popBoxClose").unbind();
		$("#popBoxClose").bind("click",function(){
			popBox("CLOSE");
		});
		shadow.unbind();
		shadow.bind("click",function(){
			popBox("CLOSE");
		});
	}
}