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
	<script type='text/javascript' src='../dwr/interface/Event.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var eventdata = {};
eventdata.aid="4028b8814c9d2c1a014c9d2dcabf0001";
eventdata.eventName="宣传活动"
eventdata.note ="地方的方法都";
eventdata.place = "展厅";

	function save_onclick(){
    	 Event.saveEvent(eventdata);
    }
    
    function count_onclick(){
    	 Event.getEventTotalCount();
    }
    
    function get_onclick(){
    	Event.getPeopleEventById("34");
    }
    
   function queryPeopleEventPage_onclick(){
    	Event.getPeopleEventForPage(0,3);
    }
    
    function queryPage_onclick(){
    	Event.getEventForPage(0,3);
    }
    
    function delete_onclick(){
    	Event.deleteEventById("44");
    }
</script>

<body>
	<input type="button" value="存储Event" onclick="javascript:save_onclick();" />
	<input type="button" value="获取Event数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="获取event"  onclick="javascript:get_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="page查询peopleEvent"  onclick="javascript:queryPeopleEventPage_onclick();" />
	
</body>
</html>