<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.novahome.commonservice.Constants"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String accountid = (String)session.getAttribute(Constants.SESSION_ID);
String username = (String)session.getAttribute(Constants.SESSION_NAME);
if(accountid==null){
	response.sendRedirect("admin.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>nova team</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<!-- Bootstrap core CSS -->
    <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/manager.css" rel="stylesheet">

	<script type='text/javascript' src="jslib/jquery-1.11.1.min.js"></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
    <script type="text/javascript" src="jslib/json2.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/interface/Account.js'></script>
	<script type="text/javascript" src="js/util.js"></script>

    <style>
    #add_topic{
    	margin-left:100px;
    }
    .banner_menu a{
    	cursor:pointer;
    }
    </style>

    <script>
    function logout(){
    	Account.logout(function(){
    		window.location.href = "admin.jsp";
    	});
    }
    </script>
  </head>
  <body>
   <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">连博会后台管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right banner_menu">
          	<li><a>当前用户: <%= username%></a></li>
            <li><a onclick="logout();"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 注销</a></li>
          </ul>
          <!-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </nav>
