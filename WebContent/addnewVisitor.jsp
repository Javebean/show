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
	<link rel="stylesheet" type="text/css" href="plugins/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css" />
	<link href="plugins/jquery.alert/jquery.alerts.css" rel="stylesheet"/>
	
    <script type='text/javascript' src="jslib/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type="text/javascript" src="jslib/json2.js"></script>
    <script type="text/javascript" src="plugins/jquery.validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="plugins/jquery.validate/jquery.validate.cn.js"></script>
	<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="plugins/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
	<script type="text/javascript" src="plugins/jquery.alert/jquery.alerts.js"></script>
    
	<script type='text/javascript' src='dwr/interface/Visitor.js'></script>
    <script type="text/javascript" src="js/addVisitor.js"></script>
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
          <h2 class="sub-header">证件申办</h2>
          <!-- jobListBox -->
	   		<div class="userForm">
				<form class="form-horizontal" name="regForm" id="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">姓名</label>
						<div class="col-xs-3">
							<input name="name" class="form-control required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">性别</label>
						<div class="col-xs-3">
							<select name="sex" class="form-control">
					          <option value="男">男</option>
					          <option value="女">女</option>
					        </select>
  						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">公司</label>
						<div class="col-xs-3">
							<input name="org" class="form-control required">
  						</div>
					</div>
				</form>
					<div class="form-group">
						<div class="picUpload" style="border:1px solid #ccc;padding:10px;width:420px;" >
							<div style="margin-bottom:10px;"><img id="topic_image" width="400" height="300 src=""/></div>
							<div>上传完后点击图片拖拽可进行图片切割</div>
							<div id="queue"></div>
						    <input type="file" name="uploadify" id="uploadify" />
						    <h4>预览：</h4>
						 	<div style="width:200px;height:150px;overflow: hidden;">
						 		<img id="img_preview" style="width:200px;height:150px;" src="" style="position: relative;" />
						 	</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="submitForm" class="btn btn-lg btn-primary">提交</button>
							<button type="button" class="btn btn-lg btn-primary">证件出样</button>
							<button type="button" class="btn btn-sm btn-danger test_btn">测试</button>
						</div>
					</div>
				</div>
				
					<div class="resultMsg" style="display: none;">
						<h3>申请完成，等待审核。</h3>
					</div>
      </div>
    </div>
  </body>
</html>
