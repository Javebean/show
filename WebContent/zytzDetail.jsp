<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博览会</title>

    <script type='text/javascript' src="jslib/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type="text/javascript" src="jslib/json2.js"></script>

	<script type='text/javascript' src='dwr/interface/Zytz.js'></script>
</head>

	<%String topicId = (String)request.getParameter("topicId"); %>
<script>
var topicId = '<%=topicId%>';
$(document).ready(function(){
	var func = function(data){
		var pt = JSON.parse(data);
		$("#title").html(pt.title);
		$("#content").html(pt.content);
	};
	Zytz.getZytzById(topicId,func);
});
</script>
<style>
#content{
	font-size: 13px;
	line-height: 1.8;
  margin-top:30px;
}
#title{
  text-align:center;
}
</style>

<body>
	<div id="title"></div>
	<div id="content"></div>
</body>
</html>
