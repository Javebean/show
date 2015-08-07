	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="ma_header.jsp" %>
	 <script src="jslib/jquery.min.js"></script>
	<link href="css/jquery.jqplot.min.css" rel="stylesheet" />
	<script src="jslib/jquery.jqplot.min.js"></script>

	<script type='text/javascript' src='dwr/interface/Exhibitor.js'></script>
	<script src="jslib/plugins/jqplot.pieRenderer.min.js"></script>
	<script src="jslib/plugins/jqplot.donutRenderer.min.js"></script>
	    <script>
			var menu = 3;
	    	$(document).ready(function(){
	   			var func = function(data){
	   				data = JSON.parse(data);
	   				if(data.error!=null){
	   					return;
	   				}
	   				var name=data.name.split(",");
	   				var num = data.num.split(",");
	   				var html="";
	   				for(var i=0;i<name.length;i++){
	   					html+='<tr><td>'+name[i]+'</td><td>'+num[i]+'</td></tr>';
	   				}
					$(".fview_table").append(html);
					var plotData = [];

					for(var i=0;i<name.length;i++){
						plotData.push([name[i], num[i]-0]);
					}

					$.jqplot('chart', [plotData], {
							seriesDefaults: {
									renderer: $.jqplot.PieRenderer,
									rendererOptions: {
											showDataLabels: true
										}
							},
							legend: {
									show: true,
									location: "e"
							}
					});
	   			};
					Exhibitor.getRegionStat(func);
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
        <div id="chart" style="width:900px; height:600px;"></div>
          <h2 class="sub-header">展商地区划分统计表</h2>
          <table class="table table-bordered table-striped fview_table" style="width:600px;">
		        <tr>
		          <th class="text-nowrap" width="80%">国家地区</th>
		          <th class="text-nowrap" width="20%">参展数量</th>
		        </tr>
		   </table>
		</div>
      </div>
    </div>
  </body>
</html>
