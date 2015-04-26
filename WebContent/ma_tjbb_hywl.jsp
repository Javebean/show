	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	<script type='text/javascript' src='dwr/interface/Transportation.js'></script>
    <script>
    	var menu = 3;
    	$(document).ready(function(){
   			var func = function(data){
   				data = JSON.parse(data);
   				var type=data.type.split(",");
   				var num = data.num.split(",");
   				var html="";
   				for(var i=0;i<type.length;i++){
   					html+='<tr><td>'+type[i]+'</td><td>'+num[i]+'</td></tr>';
   				}
				$(".fview_table").append(html);
   			};
   			Transportation.getTransportationStat(func);
    	});
    </script>
    <style>
    .statistics{
    	font-size:17px;
    	margin-top:px;
    }
    </style>

	  
    <div class="container-fluid">
      <div class="row">
      <!-- menu -->
		<jsp:include page="ma_menu.jsp" />   
		    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">货运物流统计表</h2>
          <table class="table table-bordered table-striped fview_table" style="width:600px;">
		        <tr>
		          <th class="text-nowrap" width="80%">服务类别</th>
		          <th class="text-nowrap" width="20%">总数量</th>
		        </tr>
		   </table>
		</div>
      </div>
    </div>
  </body>
</html>
