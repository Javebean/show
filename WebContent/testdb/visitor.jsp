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
	<script type='text/javascript' src='../dwr/interface/Visitor.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var visitordata = {};
visitordata.name="测试名字";
visitordata.org = "测试公司";
visitordata.phone = "123456";
visitordata.position = "职员";
visitordata.type = 1;
visitordata.photo = "s_29f63ee6-6bb1-4427-a2f5-e11a7144401e.jpg";
visitordata.eid = "4028b8814cb190bb014cb196cd350003";

	function save_onclick(){
    	 Visitor.saveVisitor(visitordata,"10,10,200,200");
    }
    
    function count_onclick(){
    	 Visitor.getVisitorTotalCount();
    }
    
    function countByType_onclick(){
    	 Visitor.getVisitorCountByType(1);
    }
    
    function getByEid_onclick(){
    	Visitor.getVisitorByEid("4028b8814cb190bb014cb196cd350003");
    }
    
     function getById_onclick(){
    	Visitor.getVisitorById("4028b8814cb59d81014cb5a697d70002");
    }
    
     function getByOrg_onclick(){
    	Visitor.getVisitorByOrg("测试公司");
    }
    
    function update_onclick(){
    	visitordata.name="另一个测试名字";
		visitordata.org = "另一个测试公司";
		visitordata.phone = "0000123456";
		visitordata.position = "另一个职员";
		visitordata.photo = "anotherceshihttp://www.baidu.com/fdfdff.png";

		visitordata.id = "4028b8814cb59d81014cb5a697d70002";
		//zytzdata.publishTime="2015-04-10 11:23:27";
    	Visitor.updateVisitor(visitordata);
    }
    
    function queryPage_onclick(){
    	Visitor.getVisitorForPage(0,4);
    }
    
    function queryPageByState_onclick(){
    	Visitor.getVisitorForPageByState(0,4,1);
    }
    
    
    function delete_onclick(){
    	Visitor.deleteVisitorById("4028b8814cb59d81014cb5a733620003");
    }
    
    function queryByName_onclick(){
    	Visitor.getVisitorByName("王奕");
    }
</script>

<body>
	<input type="button" value="存储Visitor" onclick="javascript:save_onclick();" />
	<input type="button" value="Visitor数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="Visitor数量通过type"  onclick="javascript:countByType_onclick();" />
	<input type="button" value="获取Visitor"  onclick="javascript:getById_onclick();" />
	<input type="button" value="获取Visitor通过eid"  onclick="javascript:getByEid_onclick();" />
	<input type="button" value="获取Visitor通过org名字"  onclick="javascript:getByOrg_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="page查询ByState"  onclick="javascript:queryPageByState_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	<input type="button" value="根据姓名查找现场证件"  onclick="javascript:queryByName_onclick();" />
</body>
</html>