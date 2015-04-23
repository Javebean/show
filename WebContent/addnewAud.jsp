<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博览会</title>
	<link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
	
    <script type='text/javascript' src="jslib/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type="text/javascript" src="jslib/json2.js"></script>
    <script type="text/javascript" src="plugins/jquery.validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="plugins/jquery.validate/jquery.validate.cn.js"></script>
    
	<script type='text/javascript' src='dwr/interface/Audience.js'></script>
    <script type="text/javascript" src="js/addAud.js"></script>

<style>
label.error {
	margin-left: 10px;
	width: auto;
	color: red;
	font-family:"微软雅黑", "宋体"; font-size:12px;
	display: block;
}
</style>

<body>
    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">观众注册</h2>
          <!-- jobListBox -->
	   		<div class="userForm">
				<form class="form-horizontal" name="regForm" id="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">姓名</label>
						<div class="col-xs-3">
							<input name="name" class="form-control required">
						</div>
						<span class="control-hint">(必填)</span>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">性别</label>
						<div class="col-xs-3">
							<label><input type="radio" name="sex" value="男" checked>男</label>
  							<label><input type="radio" name="sex" value="女">女</label>
  						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">职位</label>
						<div class="col-xs-3">
							<input name="position" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">电话</label>
						<div class="col-xs-3">
							<input name="phone" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">电子邮箱</label>
						<div class="col-xs-3">
							<input name="email" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">邀请者</label>
						<div class="col-xs-3">
							<input name="inviter" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">公司名称</label>
						<div class="col-xs-3">
							<input name="org" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">地址</label>
						<div class="col-xs-3">
							<input name="address" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">信息来源</label>
						<div class="col-xs-3">
							<input name="infoSource" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">参会目的</label>
						<div class="col-sm-5">
							<input name="target" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button id="submitForm" type="button" class="btn btn-lg btn-primary">提交</button>
						</div>
					</div>
				</form>
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
