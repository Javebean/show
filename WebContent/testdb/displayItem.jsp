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
	<script type='text/javascript' src='../dwr/interface/DisplayItem.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var displayItemdata = {};
displayItemdata.name="展品";
displayItemdata.length = 2;
displayItemdata.width = 2;
displayItemdata.weight = 2;
displayItemdata.eid = "4028b8814cb190bb014cb196cd350003";


var dp01 = {};
dp01.name="展品";
dp01.length = 2;
dp01.width = 2;
dp01.weight = 2;
dp01.number = 2;

var dp02 = {};
dp02.name="产品";
dp02.length = 0.2;
dp02.width = 0.2;
dp02.weight = 0.2;
dp02.number = 5;

sList = [dp01,dp02];
	function save_onclick(){
    	 DisplayItem.saveDisplayItem(displayItemdata);
    }
    
    function count_onclick(){
    	 DisplayItem.getDisplayItemTotalCount();
    }
    
    function countByEid_onclick(){
    	 DisplayItem.getDisplayItemCountByEid("4028b8814cb190bb014cb196cd350003");
    }
    
    function getByEid_onclick(){
    	DisplayItem.getDisplayItemByEid("4028b8814cb190bb014cb196cd350003");
    }
    
     function getById_onclick(){
    	DisplayItem.getDisplayItemById("4028b8814cb34178014cb341ac280001");
    }
    
    function update_onclick(){
    	displayItemdata.name="22展品";
		displayItemdata.width = 3;
		displayItemdata.nubmer = 3;
		displayItemdata.version = "dfsdfsdf";
		displayItemdata.id = "4028b8814cb34178014cb341ac280001";
		//zytzdata.publishTime="2015-04-10 11:23:27";
    	DisplayItem.updateDisplayItem(displayItemdata);
    }
    
    function queryPage_onclick(){
    	DisplayItem.getDisplayItemForPage(0,4);
    }
    
    
    function delete_onclick(){
    	DisplayItem.deleteDisplayItemById("4028b8814cb34178014cb341b4580002");
    }
    
    
     function getbyusername_onclick(){
    	DisplayItem.getDisplayItemByUsername("b76840");
    }
    
    function updatebyusername_onclick()
    {
    	
    	DisplayItem.updateDisplayItemList("b76840",sList);
    }
</script>

<body>
	<input type="button" value="存储DisplayItem" onclick="javascript:save_onclick();" />
	<input type="button" value="DisplayItem数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="DisplayItem数量通过eid"  onclick="javascript:countByEid_onclick();" />
	<input type="button" value="获取DisplayItem"  onclick="javascript:getById_onclick();" />
	<input type="button" value="获取DisplayItem通过eid"  onclick="javascript:getByEid_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	<input type="button" value="根据ex的username查询"  onclick="javascript:getbyusername_onclick();" />
	<input type="button" value="根据ex的username更新displayitem"  onclick="javascript:updatebyusername_onclick();" />
</body>
</html>