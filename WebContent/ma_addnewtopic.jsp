<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.novahome.commonservice.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String accountid = (String)session.getAttribute(Constants.SESSION_ID);
String username = (String)session.getAttribute(Constants.SESSION_NAME);
if(accountid==null){
	response.sendRedirect("adminLogin.jsp");
}
String topicId = UUID.randomUUID().toString();
%>
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
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="plugins/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/manager.css" rel="stylesheet">
    <link href="plugins/uploadify/uploadify.css" rel="stylesheet"/>
	
	<script src="jslib/jquery-1.11.1.min.js"></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/util.js'></script>
    <script src="jslib/json2.js"></script>
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="plugins/docs.min.js"></script>
    
    <script src="plugins/uploadify/jquery.uploadify.js"></script>
	<script src='dwr/interface/PTopic.js'></script>
    <script src="js/addnewtopic.js"></script>
    
    <script>
    	var topicId = '<%=topicId%>';
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
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />   
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">添加图片新闻</h2>
          <!-- jobListBox -->
	   		<div class="topicForm">
	   			<table width="100%" cellpadding="1" cellspacing="1" class="t_value">
	            <tbody>
	            <tr>
	                <th width="17%" scope="row">标题</th>
	                <td width="83%">
	                  <input id="title" type="text" maxlength="30" size="40" class="required inp" value="">
	                </td>
	            </tr>
	            <tr>
	                <th scope="row">图片</th>
	                <td>
	                	<div class="picUpload" style="border:1px solid #ccc;padding:10px;width:420px;" >
							<div style="margin-bottom:10px;"><img id="topic_image" width="400" height="300 src=""/></div>
							<div id="queue"></div>
						    <input type="file" name="uploadify" id="uploadify" />
						    <!-- <p>
						      <a href="javascript:$('#uploadify').uploadifyUpload()">上传</a>| 
						    </p> -->
						</div>
	                 <!--  <input id="picPath" type="text" maxlength="60" size="40" class="required inp" value=""> -->
	            </tr>
	            <tr>
	                <th scope="row">内容</th>
	                <td>
	                  <div class="NT_Textarea_Wrap">
	                    <textarea  id="content" rows="20" class="inp" style="width:805px;height:500px;resize:none;"></textarea>
	                  </div>
	                </td>
	            </tr>
	             
	            <tr>
	                <th scope="row">&nbsp;</th>
	                <td>
	                  <button type="button" id="add_topic" class="btn btn-lg btn-primary">发布</button>
	                </td>
	            </tr>
	          </tbody></table>
	   		</div>
   		
        </div>
      </div>
    </div>
  </body>
</html>
