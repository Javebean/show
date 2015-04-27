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
	<script type='text/javascript' src='../dwr/interface/Transportation.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var transportationdata = {};
transportationdata.type="运输内容";
transportationdata.content ="制度建设方面广交会出口展展品质量>";
transportationdata.eid = "4028b8814cb188b7014cb18aa0c8000b";

	function save_onclick(){
    	 Transportation.saveTransportation(transportationdata);
    }
    
    function count_onclick(){
    	 Transportation.getTransportationTotalCount();
    }
    
    function countByEid_onclick(){
    	 Transportation.getTransportationCountByEid("4028b8814cb188b7014cb18aa0c8000b");
    }
    
    function getByEid_onclick(){
    	Transportation.getTransportationByEid("4028b8814cb188b7014cb18aa0c8000b");
    }
    
     function getById_onclick(){
    	Transportation.getTransportationById("4028b8814cb320a5014cb320cc750001");
    }
    
    function update_onclick(){
    	transportationdata.type="22建设内容";
		transportationdata.content ="222制度建设方面广交会出口展展品质量>";
		transportationdata.id = "4028b8814cb320a5014cb320cc750001";
		//zytzdata.publishTime="2015-04-10 11:23:27";
    	Transportation.updateTransportation(transportationdata);
    }
    
    function queryPage_onclick(){
    	Transportation.getTransportationForPage(0,4);
    }
    
    
    function delete_onclick(){
    	Transportation.deleteTransportationById("4028b8814cb320a5014cb321cef20002");
    }
    
    function stat_onclick(){
    	Transportation.getTransportationStat();
    }
    
</script>

<body>
	<input type="button" value="存储transportation" onclick="javascript:save_onclick();" />
	<input type="button" value="transportation数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="transportation数量通过eid"  onclick="javascript:countByEid_onclick();" />
	<input type="button" value="获取transportation"  onclick="javascript:getById_onclick();" />
	<input type="button" value="获取transportation通过eid"  onclick="javascript:getByEid_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	<input type="button" value="统计"  onclick="javascript:stat_onclick();" />
	
</body>
</html>