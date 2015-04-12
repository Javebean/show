<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./css/style.css" rel="stylesheet" type="text/css"  media="all" />
	<link href="./css/slider.css" rel="stylesheet" type="text/css" media="all" />
	<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
	
	<script src="./jslib/jquery.min.js"></script>
	<script type="text/javascript" src="./jslib/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="./jslib/camera.min.js"></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/PTopic.js'></script>
	<script type='text/javascript' src='dwr/interface/Zlzx.js'></script>
	
	<script type="text/javascript" src="./js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
jQuery(function(){
	jQuery('#camera_wrap_1').camera({
		height: '500px',
		pagination: false,
	});
	
	$(".scroll").click(function(event){		
		event.preventDefault();
		$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
	});
});
</script>

<style>
.pad_L_20{
	padding-left:20px;
}
.pad_L_100{
	padding-left:100px;
}
a{
	cursor:pointer;
}
</style>

<body>
<!----start-wrap---->
	<div class="wrap">
		<!---start-header---->
		<div class="top-links" id="top">
				<div class="contact-info">
					联系我们: <span class="contact-info1">025-00000000</span></div>
				<div class="social-icons">
	                <ul>
	                    <li><a class="weixin" href="http://www.baidu.com" target="_blank"> </a></li>
	                    <li><a class="weibo" href="http://weibo.com/u/3910209776?topnav=1&wvr=5&topsug=1" target="_blank"></a></li>
	                    <li><a class="email" href="mailto:jiangmalone@gmail.com" target="_blank"></a></li>
	                    
	                </ul>
				</div>
				<div class="clear"><span class="logo"><a href="index.html"><img src="images/1.jpg" width="150" height="80"></a></span> </div>
			</div>
		</div>
		<div class="header">
			<div class="wrap">
			<div class="logo"></div>
			<div class="top-nav">
				<ul>
					<li class="active"><a href="index.html">首页</a></li>
					<li><a href="about.html">关于</a></li>
					<li><a href="services.html">服务</a></li>
					<li><a href="portfolio.html">产品</a></li>
					<li><a href="blog.html">活动</a></li>
					<li><a href="contact.html">联系我们</a></li>
					<div class="clear"> </div>
				</ul>
			</div>
			<div class="clear"> </div>
		</div>
		<!---End-header---->
	</div>
	 <!--start-image-slider---->
		<div class="slider">					     
				<div class="camera_wrap camera_azure_skin" id="camera_wrap_1">									           
					<div data-src="./images/slider3.jpg">  </div> 
					<div data-src="./images/slider2.jpg">  </div>
					<div data-src="./images/slider1.jpg">  </div>
					<div data-src="./images/slider2.jpg">  </div>
				</div>
				<div class="clear"> </div>					       
		</div>					
	 <!--End-image-slider---->
	 <!---start-content---->
	 <div class="content">
	 	<div class="top-grids">
	 		<div class="wrap">
		 		<div class="top-grid">
		 			<a href="#"><img src="./images/icon1.png" title="icon-name" /></a>
		 			<h3>哈哈</h3>
		 			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
		 			<a class="button" href="#">更多</a>
		 		</div>
		 		<div class="top-grid">
		 			<a href="#"><img src="./images/icon2.png" title="icon-name" /></a>
		 			<h3>吼吼</h3>
		 			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  </p>
		 			<a class="button" href="#">更多</a></div>
		 		<div class="top-grid last-topgrid">
		 			<a href="#"><img src="./images/icon4.png" title="icon-name" /></a>
		 			<h3>呵呵</h3>
		 			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
		 			<a class="button" href="#">更多</a></div>
		 		<div class="clear"> </div>
	 		</div>
	 	</div>
	 	
	 	<div class="pad_L_100">
		 	<div>展览资讯：</div>
		 	<div class = "zlzx_box">
		 		
		 	</div>
			<div class="paging hide">
				<nav>
				<ul class="pagination pagination-lg">
					<li><a page="P" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span></a></li>
					<li><a page="N" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</ul>
				</nav>
			</div>
		</div>

		<!---start-mid-grids---->
	 	<div class="mid-grids">
	 		<div class="wrap pt_cen_box">
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider1.jpg" title="image-name" /></a>
		 			<h3>Everton</h3>
		 			<p>consectetur adipisicing elit, sed do eiusmod tempor incididunt ut .</p>
		 			<a class="mid-button" href="#">更多</a>
		 		</div>
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider2.jpg" title="image-name" /></a>
		 			<h3>Ross </h3>
		 			<p>consectetur adipisicing elit, sed do eiuntre magna aliqua.</p>
		 			<a class="mid-button" href="#">更多</a>
		 		</div>
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider3.jpg" title="image-name" /></a>
		 			<h3>tony</h3>
		 			<p>consectetur adipisicing elit, sed do eiusmode magna aliqua.</p>
		 			<a class="mid-button" href="#">更多</a>
		 		</div>
		 		<div class="mid-grid" id="last">
		 			<a href="#"><img src="./images/slider4.jpg" title="image-name" /></a>
		 			<h3>bila</h3>
		 			<p>consectetur adipi incididunt ut labore et dolore magna aliqua.</p>
		 			<a class="mid-button" href="#">更多</a>
		 		</div>
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider1.jpg" title="image-name" /></a>
		 			<h3>bila</h3>
                       <p>consectetur adipi incididunt ut labore et dolore magna aliqua.</p>
                       <a class="mid-button" href="#">更多</a></div>
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider2.jpg" title="image-name" /></a>
		 			<h3>Everton</h3>
                       <p>consectetur adipisicing elit, sed do eiusmod tempor incididunt ut .</p>
                     <a class="mid-button" href="#">更多</a></div>
		 		<div class="mid-grid">
		 			<a href="#"><img src="./images/slider3.jpg" title="image-name" /></a>
		 			<h3>tony</h3>
                       <p>consectetur adipisicing elit, sed do eiusmode magna aliqua.</p>
                     <a class="mid-button" href="#">更多</a></div>
		 		<div class="mid-grid" id="last">
		 			<a href="#"><img src="./images/slider4.jpg" title="image-name" /></a>
		 			<h3>bila</h3>
                       <p>consectetur adipi incididunt ut labore et dolore magna aliqua.</p>
                       <a class="mid-button" href="#">更多</a></div>
	 		<div class="clear"> </div>
	 		</div>
	 	</div>
	 	<!---End-mid-grids---->
	 	
	 	<!---start-box----->
	 	<div class="box">
	 		<div class="wrap">
				<div class="gallery">
					<h3>Gallery</h3>
					<ul>
						<li><a href="./images/slider1.jpg"><img src="./images/slider1.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider2.jpg"><img src="./images/slider2.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider3.jpg"><img src="./images/slider3.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider4.jpg"><img src="./images/slider4.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider1.jpg"><img src="./images/slider1.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider2.jpg"><img src="./images/slider2.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider3.jpg"><img src="./images/slider3.jpg" alt=""></a><a href="#"></a></li>
						<li><a href="./images/slider4.jpg"><img src="./images/slider4.jpg" alt=""></a><a href="#"></a></li>
					</ul>
				</div>
				<script type="text/javascript" src="./jslib/jquery.lightbox.js"></script>
			    <link rel="stylesheet" type="text/css" href="./css/lightbox.css" media="screen">
			  	<script type="text/javascript">
			    $(function() {
			        $('.gallery a').lightBox();
			    });
			    </script>
				<div class="terminals">
					<h3>HAHAHA</h3>
					<p>哈哈哈哈 </p>
					<span><a href="#">- 蒋卓伟</a></span>
				</div>
				<div class="clear"> </div>
			</div>
			</div>
	 	<!---end-box---->
	 	<!---start-articals------>
	 	<div class="p-sections">
	 		<div class="wrap">
		 		<h3>导航</h3>
		 		<ul>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军 </a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 		</ul>
		 		<ul>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军 </a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 		</ul>
		 		<ul>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军 </a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 		</ul>
		 		<ul>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军 </a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 		</ul>
		 		<ul>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军 </a></li>
		 			<li><a href="#">Everton是冠军</a></li>
		 			<li><a href="#">Everton是冠军</a></li>

		 		</ul>
		 	<div class="clear"> </div>
	 		</div>
	 	</div>
	 	<!---start-articals------>
	 </div>
	 <!---End-content---->
	 <!---start-footer---->
	 <div class="footer">
	 	<div class="wrap">
	 		<div class="footer-grid">
	 			<h3>COMPANY</h3>
	 			<ul>
		 			<li><a href="#">ABOUT US</a></li>
		 			<li><a href="#">CLIENTS</a></li>
		 			<li><a href="#">PRESENTATION</a></li>
		 			<li><a href="#">SUPPORT</a></li>
		 			<li><a href="#">SCHEDULE</a></li>
		 		</ul>
	 		</div>
	 		<div class="footer-grid">
	 			<h3>OVERVIEW</h3>
	 			<ul>
		 			<li><a href="#">WHAT WE DO</a></li>
		 			<li><a href="#">NEWS</a></li>
		 			<li><a href="#">IT SOLUTIONS</a></li>
		 			<li><a href="#">SUPPORT</a></li>
		 		</ul>
	 		</div>
	 		<div class="footer-grid">
	 			<h3>FOR CLIENTS</h3>
	 			<ul>
		 			<li><a href="#">FORUMS</a></li>
		 			<li><a href="#">CLIENTS</a></li>
		 			<li><a href="#">PROMOTIONS</a></li>
		 			<li><a href="#">SIGN IN</a></li>
		 			<li><a href="#">SCHEDULE</a></li>
		 		</ul>
	 		</div>
	 		<div class="footer-grid">
	 			<h3>ARCHIVE</h3>
	 			<ul>
		 			<li><a href="#">JAN 2013</a></li>
		 			<li><a href="#">FEB 2013</a></li>
		 			<li><a href="#">MAR 2013</a></li>
		 			<li><a href="#">APR 2013</a></li>
		 			<li><a href="#">MAY 2013</a></li>
		 		</ul>
	 		</div>
	 		<div class="clear"> </div>
	 	</div>
	 </div>
	 <!---End-footer---->
	 <!---start-copy-right----->
	 <div class="copy-right">
			<div class="top-to-page">
					<a href="#top" class="scroll"> </a>
					<div class="clear"> </div>
				</div>
				<p>Design by <a href="http://www.giot.com/">GIOT</a></p>
		</div>
	 <!---End-copy-right---->
	<!----End-wrap---->
</body>
</html>