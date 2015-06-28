<%@ page contentType="text/html;charset=UTF-8"%>
<% String menuAccess = (String)session.getAttribute("_menuAccess"); %>
<script type="text/javascript">
$(document).ready(function(){
	$(".menu-box li").removeClass("active");
	$(".menu-box li[elevel='"+menu+"']").addClass("active");
	
	var menuAccess = <%=menuAccess%>;
	var acesArr = menuAccess.split(",");
	$(".menu-box li").hide();
	for(var i=0;i<acesArr.length;i++){
		$(".menu-box li[elevel='"+acesArr[i]+"']").show();
	}
});
</script>
  <div class="col-sm-3 col-md-2 sidebar menu-box">
    <ul class="nav nav-sidebar">
			<li elevel="0" class="active"><a href="ma_zlzx.jsp">展览资讯</a></li>
      <li elevel="1" class="hide"><a href="manager.jsp">图片新闻（测试）</a></li>

      <li elevel="2"><a href="ma_zytz.jsp">重要通知</a></li>
      <li elevel="3"><a href="ma_tjbb.jsp">统计报表</a></li>
    </ul>
    <ul class="nav nav-sidebar">
      <li elevel="4"><a href="ma_audience.jsp">观众管理</a></li>
      <li elevel="5"><a href="ma_exhibitor.jsp">展商管理</a></li>
      <li elevel="6"><a href="ma_visitor.jsp">证件管理</a></li>
      <li elevel="7"><a href="ma_event.jsp">专题活动管理</a></li>
    </ul>
    <ul class="nav nav-sidebar">
    </ul>
  </div>
