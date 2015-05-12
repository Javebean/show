<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./css/style.css" rel="stylesheet" type="text/css"  media="all" />
	<link href="./css/slider.css" rel="stylesheet" type="text/css" media="all" />
	<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
	
	<title>后台页面</title>
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

	<div>
		<a href="manager.jsp">管理后台</a>
		<a style="padding-left: 50px;" href="addnewAud.jsp">观众注册</a> 
		<a style="padding-left: 50px;" href="addnewExhibitor.jsp">展商注册</a>
		<a style="padding-left: 50px;" href="addnewVisitor.jsp">证件申办</a>
	</div>

</body>
</html>