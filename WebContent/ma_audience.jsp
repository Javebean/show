	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
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
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>姓名</th>
                  <th>性别</th>
                  <th>职位</th>
                  <th>电话</th>
                  <th>电子邮箱</th>
                  <th>邀请者</th>
                  <th>公司名称</th>
                  <th>地址</th>
                  <th>信息来源</th>
                  <th>参会目的</th>
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
		</div>
      </div>
    </div>
  </body>
</html>
