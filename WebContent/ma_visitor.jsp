	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type="text/javascript" src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" href="plugins/colorbox/colorbox.css" >

	<script type='text/javascript' src='dwr/interface/Visitor.js'></script>
    <script type="text/javascript" src="js/manager/ma_visitor.js"></script>
			<script type="text/javascript" src="js/constants.js"></script>
    <script>
    	var menu = 11;
			var step = <%= request.getParameter("step")%>;
			//var username = '<%= username%>;
			var official = '<%=officialname%>';

    	if(step==2){
    		menu = 6;
    	}
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
    	.cp{ width:322px; height:415px; background:url(images/samplecard.png); margin-top:40px; padding-top:1px}
		.cp_photo{ width:118px; height:149px; margin:171px 0 0 108px}
		.cp_name{ color:#2c2c2c; font-size:20px; margin:5px 0 0 150px;font-weight:bold;}
		.cp_company{ color:#2c2c2c; font-size:20px; margin:15px 0 0 150px;font-weight:bold;}
		.cp_image_fro, .cp_image_bac{
			height:240px;
			width:360px;
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

					<div class="collapse navbar-collapse">
						<div class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<span>审核状态:</span>
							</div>
							<div class="form-group">
								<select class="form-control search_ele state_search">
									<option value="-1">全部</option>
									<option value="0">待审核</option>
									<option value="1">已批准</option>
									<option value="2">已驳回</option>
								</select>
							</div>

							<div class="form-group" style="margin-left: 5px;">
								<span>证件类型:</span>
							</div>
							<div class="form-group">
								<select class="form-control search_ele type_search">
									<option value="-1">全部</option>
									<option value="1">展商</option>
									<option value="2">专业观众</option>
									<option value="3">媒体</option>
									<option value="4">工作人员</option>
									<option value="5">嘉宾</option>
								</select>
							</div>

							<div class="form-group recommender_search_label" style="margin-left: 5px;">
								<span>引荐单位:</span>
							</div>
							<div class="form-group recommender_search_label">
								<select class="form-control search_ele recommender_search" id="recommender_search_dropbox">

								</select>
							</div>

							<div class="form-group" style="margin-left: 5px;">
								<span>人员姓名:</span>
							</div>
							<div class="form-group">
								<input class="name_search" type="text" style="height: 34px;width: 100px;"/>
							</div>

							<button type="button" class="btn btn-sm btn-primary do_search" style="margin-left: 5px;">查询</button>
						</div>
					</div>

				</div>
			</nav>

			<div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>姓名</th>

                  <th>手机</th>

                  <th>证件类型</th>

                  <th>所属单位</th>
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
				<nav class="paging_bar">
  				 </nav>
			</div>

			<%-- <div style='display: none'>
				<div id='popup_box' style='padding: 10px; background: #fff;'>
					<h2 class="sub-header">证件出样</h2>
					<div class="form-group audit_box">
						<div class="cp">
					       <div class="cp_photo" style="overflow:hidden;"><img class="cp_image" style="width:118px;height:149px;" src="" /></div>
					       <div class="cp_name"></div>
					      </div>
					</div>
					<div class="form-group audit_box">
						<div class="col-sm-offset-2" style="margin-top:10px;">
							<button type="button" class="btn btn-sm btn-success print_tp" eid="">打印</button>
						</div>
					</div>
				</div>
			</div> --%>


			<div style='display: none'>
				<div id='popup_detail_box' style='padding: 10px; background: #fff;'>
					<h2 class="sub-header">证件详细信息</h2>
					<table class="table table-bordered table-striped fview_table visitor_detail">
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
				          <th class="text-nowrap" width="14%">身份证件</th>
				          <td colspan="3"><span ename="idType" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">证件号码</th>
				          <td colspan="3"><span ename="idNo" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">证件类型</th>
				          <td colspan="3"><span ename="type" class="fview_value"></span></td>
				        </tr>
								<tr>
				          <th class="text-nowrap" width="14%">是否采购商</th>
				          <td colspan="3"><span ename="buyer" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">单位名称</th>
				          <td colspan="3"><span ename="org" class="fview_value"></span></td>
				        </tr>
								<tr>
									<th class="text-nowrap" width="14%">招展引荐单位</th>
									<td colspan="3"><span ename="recommender" class="fview_value"></span></td>
								</tr>
								<tr>
								<th class="text-nowrap" width="14%">申请状态</th>
								<td colspan="3"><span ename="state" class="fview_value"></span></td>
							</tr>
				         				    </table>

					<h4>身份证正反面：</h4>
					<div class="form-group">
						<img class="cp_image_fro" src="" />
					</div>
					<div class="form-group">
						<img class="cp_image_bac" src="" />
					</div>

					<div class="form-group">
						<div class="cp">
						<!-- 添加证件二维码 -->
			       		<div class="cp_barcode" style="width: 70px;height:70px; border:solid 0px;float: left;margin-top: 170px;margin-left: 20px;">
			       		</div>
					       <div class="cp_photo" style="overflow:hidden;">
					       		<img class="cp_image" style="width:118px;height:149px;" src="" />
					       </div>
					       <div class="cp_name"></div>
								<div class="cp_company"></div>
					      </div>
					</div>
					<div class="form-group print_box">
						<div class="col-sm-offset-2" style="margin-top:10px;">
							<button type="button" class="btn btn-sm btn-success print_tp" eid="">打印</button>
						</div>
					</div>

					<div class="audit_box hide">
						<div class="form-group">
							<label>驳回原因：（如需驳回请填写此项）</label>
							<input type="text" class="form-control" id="reject_reason" style="width: 100%;"/>
						</div>

					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-5">
							<button type="button" class="btn btn-sm btn-success update_tp" eid="">批准</button>
							<button type="button" class="btn btn-sm btn-danger reject_tp" eid="">驳回</button>
						</div>
					</div>
				</div>

				</div>
			</div>




		</div>
      </div>
    </div>
  </body>
</html>
