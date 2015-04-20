	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type="text/javascript" src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" href="plugins/colorbox/colorbox.css" >
	
	
	<script type='text/javascript' src='dwr/interface/Visitor.js'></script>
    <script type="text/javascript" src="js/manager/ma_visitor.js"></script>
    <script>
    	var menu = 6;
    </script>
    
    <style>
    	.fview_table th{
    		text-align:center;
    	}
    	.btn{
    		margin-right:10px;
    	}
    	.search_box .search_ele{
    		width: 150px;
    	}
    	.glyphicon-search{
    		font-size:20px;
    	}
    </style>

    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />   
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">证件管理</h2>
          <!-- search area -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<span class="glyphicon glyphicon-search navbar-brand" aria-hidden="true"></span>
					</div>
					<div class="navbar-header">
						<span class="navbar-brand" href="#">审核状态</span>
					</div>
					<div class="collapse navbar-collapse">
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<select class="form-control search_ele state_search">
									<option value="-1">全部</option>
									<option value="0">待审核</option>
									<option value="1">已批准</option>
									<option value="2">已驳回</option>
								</select>
							</div>
						</form>
					</div>
				</div>
			</nav>

			<div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>姓名</th>
                  <th>性别</th>
                  <th>职务</th>
                  <th>号码</th>
                  <th>电子邮箱</th>
                  <th>证件类型</th>
                  <th>是否采购商</th>
                  <th>所属公司</th>
                  <th>申请时间</th>
                  <th>状态</th>
                  <th>操作</th>
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
					<h2 class="sub-header">证件出样</h2>
					<div class="form-group audit_box">
						<div class="col-sm-offset-1 col-sm-4" style="height:300px;border:1px solid #000;">
							此处为证件样本
						</div>
					</div>
					<div class="form-group audit_box">
						<div class="col-sm-offset-2 col-sm-8" style="margin-top:20px;">
							<button type="button" class="btn btn-sm btn-success print_tp" eid="">打印</button>
						</div>
					</div>
				</div>
			</div>


		</div>
      </div>
    </div>
  </body>
</html>
