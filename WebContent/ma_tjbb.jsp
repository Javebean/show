	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
    <script>
    	var menu = 3;
    </script>
    <style>
    .statistics{
    	font-size:17px;
    	margin-top:16px;
    }
    </style>


    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">统计报表</h2>
			<div class="statistics"><a href="ma_tjbb_xcfw.jsp">现场服务统计表</a></div>
			<div class="statistics"><a href="ma_tjbb_hywl.jsp">货运物流统计表</a></div>
			<div class="statistics"><a href="ma_tjbb_people.jsp">招展引荐单位统计表</a></div>
			<div class="statistics"><a href="ma_tjbb_exhibit_region.jsp">展商地区划分统计表</a></div>
			<div class="statistics"><a href="ma_tjbb_audience_region.jsp">观众地区划分统计表</a></div>
		</div>
      </div>
    </div>
  </body>
</html>
