<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.novahome.commonservice.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String accountid = (String)session.getAttribute(Constants.SESSION_ID);
String username = (String)session.getAttribute(Constants.SESSION_NAME);
if(accountid==null){
	response.sendRedirect("adminLogin.jsp");
}
%>
<script type="text/javascript">
$(document).ready(function(){
	var menu = <%=request.getParameter("menu")==null?"0":request.getParameter("menu")%>;
	$(".menu-box li").removeClass("active");
	$(".menu-box li:eq("+menu+")").addClass("active");
});
</script>
  <div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar menu-box">
      <li class="active"><a href="manager.jsp?menu=0">图片新闻</a></li>
      <li><a href="manager.jsp?menu=1">Reports</a></li>
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
