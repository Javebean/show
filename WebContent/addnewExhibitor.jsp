<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID"%>

<% String topicId = UUID.randomUUID().toString();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博览会</title>
	<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="plugins/uploadify/uploadify.css" rel="stylesheet"/>
	
    <script type='text/javascript' src="jslib/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
    <script type="text/javascript" src="jslib/json2.js"></script>
    <script type="text/javascript" src="plugins/jquery.validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="plugins/jquery.validate/jquery.validate.cn.js"></script>
	<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.js"></script>
    
	<script type='text/javascript' src='dwr/interface/Exhibitor.js'></script>
    <script type="text/javascript" src="js/addExhibitor.js"></script>
<script>
    	var topicId = '<%=topicId%>';
</script>
    
<style>
label.error {
	margin-left: 10px;
	width: auto;
	color: red;
	font-family:"微软雅黑", "宋体"; font-size:12px;
	display: block;
}
.showitems input{
	width:100px;
}
.showitems{
	width: 800px;
}
.visitors input{
	width:100px;
}
.visitors{
	width: 600px;
}
.exb_service{
	width: 800px;
}
.t_sub_header{
	padding-left:50px !important;
	font-weight:bold;
}

</style>

<body>
    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">展位申请</h2>
          <!-- jobListBox -->
	   		<div class="userForm">
				<form class="form-horizontal" name="regForm" id="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">单位名称*</label>
						<div class="col-xs-3">
							<input name="orgName" class="form-control required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">所属地区</label>
						<div class="col-xs-3">
							<input name="region" class="form-control required">
  						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">企业性质</label>
						<div class="col-xs-3">
							<select name="orgType" class="form-control">
					          <option value="私企">私企</option>
					          <option value="国企">国企</option>
					          <option value="民企">民企</option>
					        </select>
  						</div>
					</div>
				</form>
					
					<strong>展品情况</strong>
					<table class="table table-bordered showitems">
						<tr>
							<th>展品名称</th>
							<th>展品型号</th>
							<th>展品数量</th>
							<th>长度（米）</th>
							<th>宽度（米）</th>
							<th></th>
						</tr>
						<tr class="item_row">
							<td><input/></td>
							<td><input/></td>
							<td><input/></td>
							<td><input/></td>
							<td><input/></td>
							<td><button type="button" class="btn btn-sm btn-danger delete_item">删除</button></td>
						</tr>
						<tr>
							<td colspan="99"><button type="button" class="btn btn-sm btn-success add_item">添加</button></td>
						</tr>
					</table>
					
					<strong>参展人员名单</strong>
					<table class="table table-bordered visitors">
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>职务</th>
							<th>联系电话（手机）</th>
							<th></th>
						</tr>
						<tr class="item_row">
							<td><input/></td>
							<td><input/></td>
							<td><input/></td>
							<td><input/></td>
							<td><button type="button" class="btn btn-sm btn-danger delete_item">删除</button></td>
						</tr>
						<tr>
							<td colspan="99"><button type="button" class="btn btn-sm btn-success add_item">添加</button></td>
						</tr>
					</table>
					
					<table class="table table-bordered exb_service">
						<tr>
				          <th colspan="2">参展服务</th>
				        </tr>
				         <!----------- 现场服务 ----------------->
				        <tr>
							<td colspan="2" class="t_sub_header">现场服务</td>
				        </tr>
				        <tr>
				          <td class="text-nowrap" width="14%">服务类别</td>
				          	<td>
					          	<select id="scene_type" class="form-control">
						          <option value="吃饭">吃饭</option>
						          <option value="喝水">喝水</option>
						        </select>
					        </td>
				        </tr>
				        <tr>
				          <td class="text-nowrap">具体内容</td>
				          <td><input id="scene_content" class="form-control"></td>
				        </tr>
				        <!----------- 货运物流 ----------------->
				         <tr>
							<td colspan="2" class="t_sub_header">货运物流</td>
				        </tr>
				        <tr>
				          <td class="text-nowrap" width="14%">服务类别</td>
				          	<td>
					          	<select id="trans_type" class="form-control">
						          <option value="跑步">跑步</option>
						          <option value="骑车">骑车</option>
						        </select>
					        </td>
				        </tr>
				        <tr>
				          <td class="text-nowrap">具体内容</td>
				          <td><input id="trans_content" class="form-control"></td>
				        </tr>
				        
				         <!----------- 施工管理 ----------------->
				         <tr>
							<td colspan="2" class="t_sub_header">施工管理</td>
				        </tr>
				        <tr>
				          <td colspan="2" class="">
				          	<div class="picUpload" style="border:1px solid #ccc;padding:10px;width:420px;" >
								<div style="margin-bottom:10px;"><img id="topic_image" width="400" height="300" src=""/>施工图片效果</div>
								<div id="queue"></div>
							</div>
				          </td>
				        </tr>
				        <tr>
				        	<td colspan="2" class="t_sub_header">上传施工图片<input type="file" name="uploadify" id="uploadify" /></td>
				        </tr>
				    </table>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="submitForm" class="btn btn-lg btn-primary">提交</button>
							<button type="button" class="btn btn-sm btn-danger test_btn">测试</button>
						</div>
					</div>
	   		</div>
	   		
	   		<div class="resultMsg" style="display:none;">
	   			<h3>请记住用户名和密码用于登录！</h3>
	   			<h3>用户名：<span id="login_id"></span></h3>
	   			<h3>密     码：<span id="login_pass"></span></h3>
	   		</div>
   		
        </div>
      </div>
    </div>
  </body>
</html>
