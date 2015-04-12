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
	<script type='text/javascript' src='../dwr/interface/Zlzx.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var zlzxdata = {};
zlzxdata.title="测试标题";
zlzxdata.content ="<p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p><p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>";
zlzxdata.source = "海蜇";

	function save_onclick(){
    	 Zlzx.saveZlzx(zlzxdata);
    }
    
    function count_onclick(){
    	 Zlzx.getZlzxTotalCount();
    }
    
    function get_onclick(){
    	Zlzx.getZlzxByTitle("测试标题");
    }
    
    function update_onclick(){
    	zlzxdata.id="4028b8814ca15311014ca153aa810001";
    	zlzxdata.title="测试标题2";
		zlzxdata.content ="<p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p><p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>";
		zlzxdata.source = "海蜇";
    	Zlzx.updateZlzx(zlzxdata);
    }
    
    function queryPage_onclick(){
    	Zlzx.getZlzxForPage(0,3);
    }
    
    function queryShortPage_onclick(){
    	Zlzx.getShortZlzxForPage(0,3);
    }
    
    function delete_onclick(){
    	Zlzx.deleteZlzxById("4028b8814ca15311014ca15457480003");
    }
</script>

<body>
	<input type="button" value="存储zlzx" onclick="javascript:save_onclick();" />
	<input type="button" value="获取zlzx数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="获取zlzx"  onclick="javascript:get_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="page查询缩略"  onclick="javascript:queryShortPage_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	
</body>
</html>