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
    </style>

    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />   
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">展商管理</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>公司名称</th>
                  <th>所属地区</th>
                  <th>申请时间</th>
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
				          <th class="text-nowrap" width="14%">所属地区</th>
				          <td colspan="3"><span ename="region" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">企业性质</th>
				          <td colspan="3"><span ename="orgType" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">手机号码</th>
				          <td><span ename="phone" class="fview_value"></span></td>
				          <th class="text-nowrap" width="14%">电子邮箱</th>
				          <td><span ename="email" class="fview_value"></span></td>
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
				          <th colspan="2">参展服务</th>
				        </tr>
				         <!----------- 现场服务 ----------------->
				        <tr>
							<td colspan="2" class="t_sub_header">现场服务</td>
				        </tr>
				        <tr>
				          <td class="text-nowrap" width="14%">服务类别</td>
				          	<td>
					          <span ename="phone" class="scene_type"></span>
					        </td>
				        </tr>
				        <tr>
				          <td class="text-nowrap">具体内容</td>
				          <td><span ename="phone" class="scene_content"></span></td>
				        </tr>
				        <!----------- 货运物流 ----------------->
				         <tr>
							<td colspan="2" class="t_sub_header">货运物流</td>
				        </tr>
				        <tr>
				          <td class="text-nowrap" width="14%">服务类别</td>
				          	<td>
					          <span ename="phone" class="trans_type"></span>
					        </td>
				        </tr>
				        <tr>
				          <td class="text-nowrap">具体内容</td>
				          <td><span ename="phone" class="trans_content"></span></td>
				        </tr>
				        
				         <!----------- 施工管理 ----------------->
				         <tr>
							<td colspan="2" class="t_sub_header">施工管理</td>
				        </tr>
				        <tr>
				          <td colspan="2" class="">
				          	<img id="cons_image" class="thumbnail" width="400" height="300" src=""/>
				          	<div>施工图片效果</div>
				          </td>
				        </tr>
				    </table>
					
					
				</div>
			</div>


		</div>
      </div>
    </div>
  </body>
</html>
