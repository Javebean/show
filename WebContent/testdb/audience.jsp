<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./css/style.css" rel="stylesheet" type="text/css"  media="all" />
	<link href="./css/slider.css" rel="stylesheet" type="text/css" media="all" />
	
	<script src="./jslib/jquery.min.js"></script>
	<script type="text/javascript" src="../jslib/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="../jslib/camera.min.js"></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	<script type='text/javascript' src='../dwr/interface/Audience.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var audiencedata = {};
audiencedata.name="haha";
audiencedata.state=1;
audiencedata.buyer=1;
audiencedata.username = "user";
audiencedata.password =  "test";
	function save_onclick(){
    	 Audience.saveAudience(audiencedata);
    }
    
    function count_onclick(){
    	 Audience.getAudienceTotalCount();
    }
    
    function getUser_onclick(){
    	Audience.getAudienceByUserName("user");
    }
    
    function login_onclick(){
    	Audience.login("user","test");
    }
    
    function queryPage_onclick(){
    	Audience.getAudienceForPage(1,5);
    }
    
    function delete_onclick(){
    	Audience.deleteAudienceById("2");
    }
    
    function queryByName_onclick(){
    	Audience.getAudienceForPage(0,2,"泰勒斯威夫特");
    }
</script>

<body>
	<input type="button" value="存储audience" onclick="javascript:save_onclick();" />
	<input type="button" value="获取count数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="获取用户"  onclick="javascript:getUser_onclick();" />
	<input type="button" value="登陆"  onclick="javascript:login_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="根据姓名查找用户"  onclick="javascript:queryByName_onclick();" />
</body>
</html>