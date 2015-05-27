	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type="text/javascript" src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" href="plugins/colorbox/colorbox.css" >
	<script type='text/javascript' src='dwr/interface/Audience.js'></script>
    <script type="text/javascript" src="js/manager/ma_audience.js"></script>
	<script>
    	var menu = 4;
    </script>

    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">观众列表</h2>
          
          <!-- search area -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<span class="glyphicon glyphicon-search navbar-brand" aria-hidden="true"></span>
					</div>
					<div class="collapse navbar-collapse">
						<div class="navbar-form navbar-left" role="search">
							<div class="form-group" style="margin-left: 10px;">
								<span>观众姓名:</span>
							</div>
							<div class="form-group">
								<input class="name_search" type="text" style="height: 34px;width: 240px;"/>
							</div>
							
							<button type="button" class="btn btn-sm btn-primary do_search" style="margin-left: 10px;">查询</button>
						</div>
					</div>
					
				</div>
			</nav>
			
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>姓名</th>
                  <th>电话</th>
                  <th>电子邮箱</th>
                  <th>公司名称</th>
                  <th>观展身份</th>
                  <th>登入帐号</th>
                  <th>申请时间</th>
                </tr>
              </thead>
              <tbody class="pt_cen_box">
              </tbody>
            </table>
          </div>

			<div class="paging hide">
				<nav>
				<ul class="pagination pagination-lg">
					<li><a page="P" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
					<li><a page="N" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</ul>
				</nav>
			</div>

			<div style='display: none'>
				<div id='popup_box' style='padding: 10px; background: #fff;'>
					<h2 class="sub-header">观众详细信息</h2>
					<table class="table table-bordered table-striped fview_table audience_detail">
				        <tr>
				          <th class="text-nowrap" width="14%">姓名</th>
				          <td colspan="3"><span ename="name" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">性别</th>
				          <td colspan="3"><span ename="sex" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">职位</th>
				          <td colspan="3"><span ename="position" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">电话</th>
				          <td colspan="3"><span ename="phone" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">电子邮箱</th>
				          <td colspan="3"><span ename="email" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">邀请人</th>
				          <td colspan="3"><span ename="inviter" class="fview_value"></span></td>
				        </tr>
								<tr>
				          <th class="text-nowrap" width="14%">公司名称</th>
				          <td colspan="3"><span ename="org" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">观众身份</th>
				          <td colspan="3"><span ename="buyer" class="fview_value"></span></td>
				        </tr>
								<tr>
				          <th class="text-nowrap" width="14%">地址</th>
				          <td colspan="3"><span ename="address" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">消息来源</th>
				          <td colspan="3"><span ename="infoSource" class="fview_value"></span></td>
				        </tr>
								<tr>
				          <th class="text-nowrap" width="14%">参会目的</th>
				          <td colspan="3"><span ename="target" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">登入账号</th>
				          <td colspan="3"><span ename="username" class="fview_value"></span></td>
				        </tr>
				         				    </table>


					<!-- <div class="form-group audit_box hide">
						<div class="col-sm-offset-5 col-sm-5">
							<button type="button" class="btn btn-sm btn-success update_tp" eid="">批准</button>
							<button type="button" class="btn btn-sm btn-danger reject_tp" eid="">驳回</button>
						</div>
					</div> -->
				</div>
			</div>


		</div>
      </div>
    </div>
  </body>
</html>
