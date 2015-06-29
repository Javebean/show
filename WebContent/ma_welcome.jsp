	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
    <script>
    	var menu = -1;
    </script>


    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div style="margin-top:20px;">
          	<img src="images/adminheader.png" width="120px" height="120px"/>
          	<h2>这里是后台管理.</h2>
          </div>
		</div>
      </div>
    </div>
  </body>
</html>
