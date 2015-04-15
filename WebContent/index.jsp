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
	<div class="pad_L_100">
		<div>展览资讯：</div>
		<div class="zlzx_box"></div>
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

	<div>
		<a href="manager.jsp">管理后台</a>
		<a style="padding-left: 50px;" href="addnewAud.jsp">观众注册</a> 
		<a style="padding-left: 50px;" href="addnewExhibitor.jsp">展商注册</a>
	</div>

	<!--start-image-slider---->
	<div class="slider" style="display:none;">
		<div class="camera_wrap camera_azure_skin" id="camera_wrap_1">
			<div data-src="./images/slider3.jpg"></div>
			<div data-src="./images/slider2.jpg"></div>
			<div data-src="./images/slider1.jpg"></div>
			<div data-src="./images/slider2.jpg"></div>
		</div>
		<div class="clear"></div>
	</div>
	<!--End-image-slider---->
</body>
</html>