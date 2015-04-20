<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".menu-box li").removeClass("active");
	$(".menu-box li:eq("+menu+")").addClass("active");
});
</script>
  <div class="col-sm-3 col-md-2 sidebar menu-box">
    <ul class="nav nav-sidebar">
      <li class="active"><a href="manager.jsp">图片新闻（测试）</a></li>
      <li><a href="ma_zlzx.jsp">展览资讯</a></li>
      <li><a href="ma_zytz.jsp">重要通知</a></li>
      <li><a href="manager.jsp">Analytics</a></li>
    </ul>
    <ul class="nav nav-sidebar">
      <li><a href="ma_audience.jsp">观众管理</a></li>
      <li><a href="ma_exhibitor.jsp">展商管理</a></li>
      <li><a href="ma_visitor.jsp">证件管理</a></li>
      <li><a href="">Another nav item</a></li>
      <li><a href="">More navigation</a></li>
    </ul>
    <ul class="nav nav-sidebar">
      <li><a href="">Nav item again</a></li>
      <li><a href="">One more nav</a></li>
      <li><a href="">Another nav item</a></li>
    </ul>
  </div>
