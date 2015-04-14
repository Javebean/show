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
	<script type='text/javascript' src='../dwr/interface/Construction.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var constructiondata = {};
constructiondata.type="建设内容";
constructiondata.content ="制度建设方面广交会出口展展品质量>";
constructiondata.picture = "http://haizhe.png";
constructiondata.eid = "4028b8814cb188b7014cb18aa0c8000b";

	function save_onclick(){
    	 Construction.saveConstruction(constructiondata);
    }
    
    function count_onclick(){
    	 Construction.getConstructionTotalCount();
    }
    
    function countByEid_onclick(){
    	 Construction.getConstructionCountByEid("4028b8814cb188b7014cb18aa0c8000b");
    }
    
    function getByEid_onclick(){
    	Construction.getConstructionByEid("4028b8814cb188b7014cb18aa0c8000b");
    }
    
     function getById_onclick(){
    	Construction.getConstructionById("4028b8814cb1ea7e014cb1f646670001");
    }
    
    function update_onclick(){
    	constructiondata.type="22建设内容";
		constructiondata.content ="222制度建设方面广交会出口展展品质量>";
		constructiondata.picture = "!!!http://haizhe.png";
		constructiondata.id = "4028b8814cb1ea7e014cb1f8a7280004";
		//zytzdata.publishTime="2015-04-10 11:23:27";
    	Construction.updateConstruction(constructiondata);
    }
    
    function queryPage_onclick(){
    	Construction.getConstructionForPage(0,4);
    }
    
    
    function delete_onclick(){
    	Construction.deleteConstructionById("4028b8814cb1ea7e014cb1f83eea0003");
    }
</script>

<body>
	<input type="button" value="存储construction" onclick="javascript:save_onclick();" />
	<input type="button" value="construction数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="construction数量通过eid"  onclick="javascript:countByEid_onclick();" />
	<input type="button" value="获取construction"  onclick="javascript:getById_onclick();" />
	<input type="button" value="获取construction通过eid"  onclick="javascript:getByEid_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	
</body>
</html>