<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="ma_header.jsp" %>
	<link rel="stylesheet" type="text/css" href="plugins/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css" />
	<script type="text/javascript" src="plugins/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
    <script src="plugins/ckEditor/ckeditor.js"></script>
    
	<script type='text/javascript' src='dwr/interface/Zlzx.js'></script>
    <script type="text/javascript" src="js/manager/ma_editzlzx.js"></script>
    <script>
    	var menu = 1;
    </script>
    
    <%String topicId = (String)request.getParameter("topicId"); %>
	<script>
    	var topicId = '<%=topicId%>';
    </script>
    
    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />   
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">编辑展览资讯</h2>
          <!-- jobListBox -->
	   		<div class="topicForm">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-1 control-label">标题</label>
						<div class="col-sm-5">
							<input id="title" type="text" maxlength="30" size="40" class="required inp" value="">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-1 control-label">内容</label>
						<div class="col-sm-9">
							<div class="NT_Textarea_Wrap">
								<textarea id="content" class="inp" style=""></textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="edit_topic" class="btn btn-lg btn-primary">发布</button>
						</div>
					</div>
				</form>
	   		</div>
   		
        </div>
      </div>
    </div>
  </body>
</html>
