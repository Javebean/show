<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".menu-box li").removeClass("active");
	$(".menu-box li:eq("+menu+")").addClass("active");
});
</script>
  <div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar menu-box">
      <li class="active"><a href="manager.jsp?menu=0">图片新闻（测试）</a></li>
      <li><a href="ma_zlzx.jsp?menu=1">展览资讯</a></li>
      <li><a href="manager.jsp?menu=2">Analytics</a></li>
      <li><a href="manager.jsp?menu=3">Export</a></li>
    </ul>
    <ul class="nav nav-sidebar">
      <li><a href="">Nav item</a></li>
      <li><a href="">Nav item again</a></li>
      <li><a href="">One more nav</a></li>
      <li><a href="">Another nav item</a></li>
      <li><a href="">More navigation</a></li>
    </ul>
    <ul class="nav nav-sidebar">
      <li><a href="">Nav item again</a></li>
      <li><a href="">One more nav</a></li>
      <li><a href="">Another nav item</a></li>
    </ul>
  </div>
