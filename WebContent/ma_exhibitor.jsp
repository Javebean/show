	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type="text/javascript" src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" href="plugins/colorbox/colorbox.css" >


	<script type='text/javascript' src='dwr/interface/Exhibitor.js'></script>
    <script type="text/javascript" src="js/manager/ma_exhibitor.js"></script>
    <script>
    	var menu = 5;
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
          <h2 class="sub-header">展商管理</h2>
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
							<div class="form-group" style="margin-left: 10px;">
								<span>展商名称:</span>
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
                  <th>编号</th>
                  <th>公司名称</th>
                  <th>所属地区</th>
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
					<h2 class="sub-header">参展企业信息</h2>
					<table class="table table-bordered table-striped fview_table exb_detail">
				        <tr>
				          <th class="text-nowrap" width="14%">单位名称</th>
				          <td colspan="3"><span ename="orgName" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">英文名称</th>
				          <td colspan="3"><span ename="orgEng" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">法定代表人</th>
				          <td colspan="3"><span ename="president" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">招展引荐单位</th>
				          <td colspan="3"><span ename="recommender" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">地址</th>
				          <td colspan="3"><span ename="address" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">网址</th>
				          <td width="36%"><span ename="site" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">邮编</th>
				          <td width="36%"><span ename="zipcode" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">国家地区</th>
				          <td><span ename="region" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">企业性质</th>
				          <td><span ename="orgType" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">行业类别</th>
				          <td><span ename="industryType" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">行业规模</th>
				          <td><span ename="scale" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">联系人</th>
				          <td><span ename="contact" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">职务</th>
				          <td><span ename="contactPosition" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">手机号码</th>
				          <td><span ename="phone" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">电子邮箱</th>
				          <td><span ename="email" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" width="14%">固定电话</th>
				          <td><span ename="telephone" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">传真</th>
				          <td><span ename="fax" class="fview_value"></span></td>
				        </tr>
				         <tr>
				          <th class="text-nowrap" colspan="4">参展单位及产品简介</th>
				        </tr>
				        <tr>
				          <td colspan="4"><span ename="orgIntro" class="fview_value"></span></td>
				        </tr>
				    </table>

				    <strong>企业logo</strong>
				    <div><img id="logo_image" class="thumbnail" width="75" height="75" src=""/></div>

				    <strong>摊位申请</strong>
					<table class="table table-bordered fview_table areaapply">
						<tr>
							<th width="15%">申请标摊数量</th>
							<th width="17%">是否申请统一特展</th>
							<th width="15%">是否室外展示</th>
							<th width="53%">特装服务类容</th>
						</tr>
						<tr>
							<td><span ename="btsl" class="fview_value"></span></td>
							<td><span ename="tytzzs" class="fview_value"></span></td>
							<td><span ename="swzs" class="fview_value"></span></td>
							<td><span ename="tzfw" class="fview_value"></span></td>
						</tr>
					</table>

				    <strong>展品情况</strong>
					<table class="table table-bordered fview_table showitems">
						<tr>
							<th>展品名称</th>
							<th>展品型号</th>
							<th>展品数量</th>
							<th>长度（米）</th>
							<th>宽度（米）</th>
						</tr>
					</table>

					<strong>参展人员名单</strong>
					<table class="table table-bordered fview_table visitors">
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>职务</th>
							<th>联系电话（手机）</th>
						</tr>
					</table>

					<table class="table table-bordered exb_service">
						<tr>
				          <th colspan="3">参展服务</th>
				        </tr>
				         <!----------- 现场服务 ----------------->
				        <tr>
							<th colspan="3" class="t_sub_header">现场服务</th>
				        </tr>
				        <tr class="scene_header">
				          <td class="text-nowrap" width="50%">服务类别</td>
				          <td colspan="2" class="text-nowrap" width="50%">数量</td>
				        </tr>
				        <!----------- 货运物流 ----------------->
				         <tr>
							<th colspan="3" class="t_sub_header">货运物流</th>
				        </tr>
				        <tr class="trans_header">
				          <td class="text-nowrap" width="50%">服务类别</td>
				          <td class="text-nowrap" width="25%">数量</td>
				          <td class="text-nowrap" width="25%">时间</td>
				        </tr>

				         <!----------- 施工管理 ----------------->
				         <tr>
							<th colspan="3" class="t_sub_header">施工管理</th>
				        </tr>
				        <tr>
				          <td colspan="3" class="">
				          	<img id="cons_image" class="thumbnail" width="400" height="300" src=""/>
				          	<div>施工图片效果</div>
				          </td>
				        </tr>
				    </table>

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
