	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type="text/javascript" src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" href="plugins/colorbox/colorbox.css" >

	
	<script type='text/javascript' src='dwr/interface/Event.js'></script>
    <script type="text/javascript" src="js/manager/ma_event.js"></script>
	<script>
    	var menu = 7;
    </script>

    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">专题活动列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>组织者</th>
                  <th>活动名称</th>
                  <th>活动时长</th>
                  <th>活动类型</th>
                  <th>邀请对象</th>
                  <th>备注说明</th>
                  <th>申请时间</th>
                </tr>
              </thead>
              <tbody class="pt_cen_box">
              </tbody>
            </table>
          </div>

			<div class="paging hide">
				<nav class="paging_bar" style="float:left;">
  				 </nav>
			</div>

			<div style='display: none'>
				<div id='popup_box' style='padding: 10px; background: #fff;'>
					<h2 class="sub-header">专题活动信息</h2>
					<table class="table table-bordered table-striped fview_table event_detail">
				        <tr>
				          <th class="text-nowrap" width="14%">组织者</th>
				          <td colspan="3"><span ename="organizer" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">活动名称</th>
				          <td colspan="3"><span ename="eventName" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">邀请对象</th>
				          <td colspan="3"><span ename="invitee" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">活动类型</th>
				          <td colspan="3"><span ename="type" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">活动时长</th>
				          <td colspan="3"><span ename="period" class="fview_value"></span></td>
				        </tr>
				        <tr>
				          <th class="text-nowrap" width="14%">备注说明</th>
				          <td colspan="3"><span ename="note" class="fview_value"></span></td>
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
  <body><br></body>
</html>
