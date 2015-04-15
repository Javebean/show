<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.novahome.data.service.AccountService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>nova team</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<!-- Bootstrap core CSS -->
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
	
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/Account.js'></script>
	<script type="text/javascript" src="jslib/jquery.min.js"></script>
    <script type="text/javascript" src="jslib/json2.js"></script>
	
	<script type="text/javascript" src="js/adminsignin.js"></script>
  </head>
<style>
#login{
	margin-top:20px;
}
</style>
  <body>
   <div class="container">
      <div class="form-signin">
		<div id="message" style="color:red;"></div>
        <h2 class="form-signin-heading">管理员登入</h2>
        <input id="name" type="" class="form-control" placeholder="用户名" required autofocus>
        <input id="password" type="password" class="form-control" placeholder="密码" required>
        <!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
        <button id="login" class="btn btn-lg btn-primary btn-block">登录</button>
      </div>

    </div> <!-- /container -->
  </body>
</html>
