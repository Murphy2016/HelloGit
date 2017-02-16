<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../html/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../html/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="../html/js/jquery-1.11.1.js"></script>
<script src="../html/bootstrap/js/moment-with-locales.min.js"></script>
<script src="../html/bootstrap/js/bootstrap-datetimepicker.min.js"></script> 
<script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>



</head>
<body>

<!-- <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
<div>
          <h1 class="page-header">设备监控-月</h1>

          <div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;" >月份：</div>
				
				<%-- <input id="month" style="display:inline;" placeholder="yyyy-MM" value="${month }"></input> --%>
				<div class="input-group date" style="display:inline;">
				<input id="month" type="text" value="${month }">
				</div>
				
				<!-- <div style="display:inline;">设备编号：</div>
				<input  style="display:inline;"></input>
				<div style="display:inline;">设备SN码：</div>
				<input  style="display:inline;"></input>
				<div style="display:inline;">设备名称：</div>
				<input  style="display:inline;"></input> -->
				
				<button style="margin-left:2px;margin-top:5px;" onclick="search()">搜索</button>
				
			</div>
		  <br/><br/>
		  
		  
		  
		  <div class="well well-sm">
		   	<table>
			<tr>
			<td><div style="margin-left:37px;">
				
				<div style="margin-left:30px;display:inline;"><h4>设备总数: ${device_count }</h4></div>
				<br>			
				<div id="strong" style="margin-left:30px;display:inline;">在线</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				${online_count }
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">开机</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				${poweron_count }
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">通讯</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				${com_count }
				</div>
			</div></td>
			<td><div style="margin-left:130px;">
				
				<div style="margin-left:30px;display:inline;"><h4>设备总时长: ${total_time }</h4></div>
				<!-- <div  style="display:inline;">
				9999
				</div> -->	
				<br>			
				<div id="strong" style="margin-left:30px;display:inline;">最大</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				${max_time }
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">最小</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				${min_time }
				</div>
			</div></td>
			</tr>
			</table>
		  </div>
		  <div id="hicontainermonth" hide="true" style="width:100%; height:400px;"></div>
          <h2 class="sub-header">设备列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  
                  <th>时间</th>
                  <th>设备总数</th>
                  <th>开机设备数</th>
                  <th>开机总时长</th>
                  
                  <th>平均开机时长</th>
                  <th>设备通讯数</th>
                  <th>通讯总时长</th>
                  <th>平均通讯时长</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${list}" var="item" >
                <tr>
                  <td>${item.day }</td>
				  <td>${item.total_amt }</td>
				  <td>${item.poweron_amt }</td>
				  <td>${item.poweron_totaltime }</td>
				  <td>${item.poweron_avgtime }</td>
				  <td>${item.cmt_amt }</td>
				  <td>${item.cmt_totaltime }</td>
				  <td>${item.cmt_avgtime }</td>
                </tr>
               </c:forEach> 
                
              </tbody>
            </table>
            <%@ include file="../Page.jsp" %>
          </div>
       
    </div>
    <script type="text/javascript">

	function highchart(container,hour,count1,count2) {
	    $('#'+container).highcharts({
	    	chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            
	            //backgroundColor: 'blue'
	        },
	        title: {
	            text: '设备数统计',
	            x: -20 //center
	        },
	        
	        
	        exporting:
	        {
	              enabled:true,//默认为可用，当设置为false时，图表的打印及导出功能失效
	              buttons:{    //配置按钮选项
	                  exportButton:{    //配置导出按钮
	                      width:50,
	                      symbolSize:20,
	                      borderWidth:2,
	                      borderRadius:0,
	                      hoverBorderColor:'red',
	                      height:30,
	                      symbolX:25,
	                      symbolY:15,
	                      x:-150,
	                      y:20
	                  }
	              },
	              filename:'52wulian.org',//导出的文件名
	              type:'image/png',//导出的文件类型
	              width:800    //导出的文件宽度
	       },
	        
	        
	        xAxis: {
	            categories: hour
	        },
	        yAxis: {
	            title: {
	                text: '数量'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '开机设备数',
	            data: count1
	        },
	        {
	            name: '通讯设备数',
	            data: count2
	        }
	        
	        ]
	    });
	}

	</script>
    <script type="text/javascript">

		
		{
			//highchart(list[i].id,hour,count1,count2);
			var month = $("#month").val();
			var myurl ='getmapdata?sn='+'hicontainermonth&type=month&month='+month;
			$.post(myurl,function(result){
				if (result.success){
					highchart(result.sn,result.hour,result.count1,result.count2);
					
				} else {
					$.messager.show({	// show error message
						title: 'Error',
						msg: result.errorMsg
					});
				}
			},'json');
		}
		function search(){
	    	var month = $("#month").val();
	    	
	    	window.location.href='month?month='+month;
	    }
		$('#month').datetimepicker({
			format: 'YYYY-MM',  
	        locale: moment.locale('zh-cn')
		});
		
	</script>
    
    
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="../html/bootstrap/js/bootstrap.min.js"></script>
    <!-- <script src="../html/bootstrap/assets/js/docs.min.js"></script> -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../html/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>


</body>
</html>