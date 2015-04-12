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
	<script type='text/javascript' src='../dwr/interface/Staff.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var staffdata = {};
staffdata.name="测试名字";
staffdata.email ="123@163.com";
staffdata.phone = "e34434";
staffdata.userName ="user111";
staffdata.password ="123";
	function save_onclick(){
    	 Staff.saveStaff(staffdata);
    }
    
    function count_onclick(){
    	 Staff.getStaffTotalCount();
    }
    
    function get_onclick(){
    	Staff.getStaffByUserName("user1");
    }
    
    function update_onclick(){
    	staffdata.id ="4028b8814ca2b885014ca2b93c480002";
    	staffdata.name="测试名字2";
		staffdata.email ="qq123@163.com";
		staffdata.phone = "11e34434";
		staffdata.userName ="user2";
		//staffdata.password ="1233";
    	Staff.updateStaff(staffdata);
    }
    
    function login_onclick(){
    	Staff.login("user1","123");
    }
    
    function delete_onclick(){
    	Staff.deleteStaffById("");
    }
</script>

<body>
	<input type="button" value="存储staff" onclick="javascript:save_onclick();" />
	<input type="button" value="获取staffx数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="获取staff"  onclick="javascript:get_onclick();" />
	<input type="button" value="login"  onclick="javascript:login_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	
</body>
</html>