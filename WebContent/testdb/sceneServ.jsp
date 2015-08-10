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
	<script type='text/javascript' src='../dwr/interface/SceneServ.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var sceneServdata = {};
sceneServdata.type="建设内容";
sceneServdata.content ="制度建设方面广交会出口展展品质量>";
sceneServdata.eid = "4028b8814cb190bb014cb196cd350003";

var sceneServ01 = {};
sceneServ01.type = "服务";
sceneServ01.content =2;

var sceneServ02 = {};
sceneServ02.type = "产品";
sceneServ02.content =2;

sList = [sceneServ01,sceneServ02];

	function save_onclick(){
    	 SceneServ.saveSceneServ(sceneServdata);
    }
    
    function count_onclick(){
    	 SceneServ.getSceneServTotalCount();
    }
    
    function countByEid_onclick(){
    	 SceneServ.getSceneServCountByEid("4028b8814cb190bb014cb196cd350003");
    }
    
    function getByEid_onclick(){
    	SceneServ.getSceneServByEid("4028b8814cb190bb014cb196cd350003");
    }
    
     function getById_onclick(){
    	SceneServ.getSceneServById("4028b8814cb320a5014cb326e72d0005");
    }
    
    function update_onclick(){
    	sceneServdata.type="22建设内容";
		sceneServdata.content ="222制度建设方面广交会出口展展品质量>";
		sceneServdata.id = "4028b8814cb320a5014cb326e72d0005";
		
		//zytzdata.publishTime="2015-04-10 11:23:27";
    	SceneServ.updateSceneServ(sceneServdata);
    }
    
    function queryPage_onclick(){
    	SceneServ.getSceneServForPage(0,4);
    }
    
    
    function delete_onclick(){
    	SceneServ.deleteSceneServById("4028b8814cb320a5014cb327133c0006");
    }
    
    function getbyusername_onclick(){
    SceneServ.getSceneServByUsername("e71396");
    }
    
    function updatebyusername_onclick()
    {
    	
    	SceneServ.updateSceneServList("e71396",sList);
    }
</script>

<body>
	<input type="button" value="存储SceneServ" onclick="javascript:save_onclick();" />
	<input type="button" value="SceneServ数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="SceneServ数量通过eid"  onclick="javascript:countByEid_onclick();" />
	<input type="button" value="获取SceneServ"  onclick="javascript:getById_onclick();" />
	<input type="button" value="获取SceneServ通过eid"  onclick="javascript:getByEid_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	<input type="button" value="根据ex的username查询"  onclick="javascript:getbyusername_onclick();" />
	<input type="button" value="根据ex的username更新scecne"  onclick="javascript:updatebyusername_onclick();" />
</body>
</html>