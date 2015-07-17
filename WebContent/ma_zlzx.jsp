	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type='text/javascript' src='dwr/interface/Zlzx.js'></script>
    <script type="text/javascript" src="js/manager/ma_zlzx.js"></script>
    <script>
    	var menu = 0;
    </script>


    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">展览资讯<button type="button" id="add_topic" class="btn btn-sm btn-primary">添加</button></h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>标题</th>
                  <th>发布人</th>
                  <th>更新时间</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody class="pt_cen_box">
                <!-- <tr>
                  <td>1,002</td>
                  <td>amet</td>
                  <td>consectetur</td>
                  <td>adipiscing</td>
                  <td>elit</td>
                  <td>sit</td>
                  <td>
                  	<button type="button" class="btn btn-sm btn-success">编辑</button>
                  	<button type="button" class="btn btn-sm btn-danger">删除</button>
                  </td>
                </tr> -->
              </tbody>
            </table>
          </div>

			<div class="paging hide">
				<nav class="paging_bar" style="float:left;">
  				 </nav>
			</div>
		</div>
      </div>
    </div>
  </body>
</html>
