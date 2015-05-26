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
	<script type='text/javascript' src='../dwr/interface/Exhibitor.js'></script>
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	<title>NOVA小组</title>
</head>

<script>
var exhibitordata = {};
exhibitordata.address="测试地址";
exhibitordata.tssm ="dfjdsfljsdfjdkslfjkdlsfjkdsjfkldjsfjdflk";
exhibitordata.contact="阿道夫";
exhibitordata.jgzxsb="df";
exhibitordata.contactPosition="会计";
exhibitordata.org ="GIot公司";
exhibitordata.orgEng = "giot";
exhibitordata.orgIntro ="<p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p><p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>";
exhibitordata.region = "中国";

var construction = {};
construction.type = "建设";
construction.content ="建设内容";
cList = [construction];

var transportation = {};
transportation.type = "搬运";
transportation.content ="搬运内容";
tList = [transportation];

var sceneServ = {};
sceneServ.type = "服务";
sceneServ.content ="服务内容";
sList = [sceneServ];

var displayItem01 ={};
displayItem01.name = "001";
displayItem01.width =3;
displayItem01.height =2;
displayItem01.length =1;
displayItem01.weight =2;
displayItem01.number =2;

var displayItem02 ={};
displayItem02.name = "002";
displayItem02.width =3;
displayItem02.height =2;
displayItem02.length =1;
displayItem02.weight =2;
displayItem02.number =2;

dList = [displayItem01, displayItem02];

var visitor01 = {};
visitor01.name = "人们";
visitor01.sex = "1"; 
visitor01.phone ="2323";
visitor01.position ="职员";

var visitor02 = {};
visitor02.name = "人们2";
visitor02.sex = "0"; 
visitor02.phone ="8453";
visitor02.position ="职员";
vList = [visitor01, visitor02];

	function save_onclick(){
    	 Exhibitor.saveExhibitor(exhibitordata);
    }
    
    function saveTotal_onclick(){
    	 Exhibitor.saveTotalExhibitInfo(exhibitordata,cList,tList,sList,vList,dList);
    }
    
    function getTotal_onclick(){
    	 Exhibitor.getTotalExhibitInfoById("4028b8814cb188b7014cb18a0b4f0003");
    }
    
    function count_onclick(){
    	 Exhibitor.getExhibitorsTotalCount();
    }
    
    function get_onclick(){
    	Exhibitor.getExhibitorById("4028b8814cb188b7014cb18a0b4f0003");
    }
    
   
    
    function queryPage_onclick(){
    	Exhibitor.getExhibitorsForPage(0,3);
    }
    
    function queryShortPage_onclick(){
    	Exhibitor.getShortExhibitorsForPage(0,3);
    }
    
    function queryShortPageByState_onclick(){
    	Exhibitor.getExhibitorsForPageByState(0,2,0);
    }
    
     function login_onclick(){
    	Exhibitor.login("b26962216","049494");
    }
    
    function update_onclick(){
	    exhibitordata.address="2222222222测试地址";
		exhibitordata.tssm ="22dfjdsfljsdfjdkslfjkdlsfjkdsjfkldjsfjdflk";
		exhibitordata.contact="22阿道夫";
		exhibitordata.jgzxsb="22df";
		exhibitordata.contactPosition="22会计";
		exhibitordata.org ="22GIot公司";
		exhibitordata.orgEng = "22giot";
		exhibitordata.orgIntro ="22<p><strong>快照二：推进制度化，完善制度推动组展优化</strong></p><p>&nbsp; &nbsp;规范管理，制度先行。第117届广交会继续在制度建设方面务实耕耘，出台了《广交会出口展组展业务线上管理规范》，推进无纸化办公，简化办事流程，为参展企业提供更便捷的服务；修订《广交会出口展展品质量及贸易纠纷投诉监控办法》，促进企业规范签约行为，完善监控办法，打造一个祥和有序的展会环境。</p>";
		exhibitordata.region = "22中国";
		exhibitordata.id ="4028b8814cb188b7014cb18aa0c8000b";
    	Exhibitor.updateExhibitor(exhibitordata);
    }
    
    function updateState_onclick(){
	   
		var id ="4028b8814cf9ddcd014cfa1da5c5000b";
		var state =2;
    	Exhibitor.updateExhibitorState(id,state);
    }
    
    function updateStateReason_onclick(){
	   
		var id ="4028b8814cf9ddcd014cfa1da5c5000b";
		var state =2;
		var reason ="没有详细介绍内容";
    	Exhibitor.updateExhibitorStateReason(id,state,reason);
    }
    
    function delete_onclick(){
    	Exhibitor.deleteExhibitorById("4028b8814cb188b7014cb189c9880002");
    }
    
    function queryShortByName_onclick(){
    	Exhibitor.getExhibitorsByName("顺丰速运（集团）有限公司");
    }
    
    function queryRecStat_onclick(){
    	Exhibitor.getRecommenderStat();
    }
</script>

<body>
	<input type="button" value="存储exhibitor" onclick="javascript:save_onclick();" />
	<input type="button" value="存储Totalexhibit" onclick="javascript:saveTotal_onclick();" />
	<input type="button" value="获取exhibitortotal" onclick="javascript:getTotal_onclick();" />
	<input type="button" value="获取exhibitor数量"  onclick="javascript:count_onclick();" />
	<input type="button" value="获取exhibitor"  onclick="javascript:get_onclick();" />
	<input type="button" value="page查询"  onclick="javascript:queryPage_onclick();" />
	<input type="button" value="page查询缩略"  onclick="javascript:queryShortPage_onclick();" />
	<input type="button" value="page查询通过state"  onclick="javascript:queryShortPageByState_onclick();" />
	<input type="button" value="登陆"  onclick="javascript:login_onclick();" />
	<input type="button" value="删除"  onclick="javascript:delete_onclick();" />
	<input type="button" value="更新"  onclick="javascript:update_onclick();" />
	<input type="button" value="更新状态"  onclick="javascript:updateState_onclick();" />
	<input type="button" value="更新状态和驳回理由"  onclick="javascript:updateStateReason_onclick();" />
	<input type="button" value="根据展商名称查询展商"  onclick="javascript:queryShortByName_onclick();" />
	<input type="button" value="展商引荐信息"  onclick="javascript:queryRecStat_onclick();" />
	
</body>
</html>