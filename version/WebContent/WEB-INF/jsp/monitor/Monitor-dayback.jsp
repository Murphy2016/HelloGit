<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../html/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../html/bootstrap/css/dashboard.css" rel="stylesheet">
<link href="../html/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="../html/js/jquery-1.11.1.js"></script>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="../html/bootstrap/js/bootstrap.min.js"></script>
    <!-- <script src="../html/bootstrap/assets/js/docs.min.js"></script> -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../html/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>



<script src="../html/bootstrap/js/moment-with-locales.min.js"></script>
<script src="../html/bootstrap/js/bootstrap-datetimepicker.min.js"></script> 
<script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
 


</head>
<body>
<input id="createtime" type="text" value="${createtime }">
<!-- <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
<div>
 
          <h1 class="page-header">设备监控-日</h1>

          <div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;">日期：</div>
				<%-- <input id="date" style="display:inline;" placeholder="yyyy-MM-dd" value="${date }"></input> --%>
				<input id="date111" type="text" >	
				
				<div style="display:inline;">设备编号：</div>
				<input id="device_id" style="display:inline;" value="${device_id }"></input>
				<div style="display:inline;">设备SN码：</div>
				<input id="sn" style="display:inline;" value="${sn }"></input>
				<div style="display:inline;">设备名称：</div>
				<input id="name" style="display:inline;" value="${name }"></input>
				
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
		  <div id="hicontainer0" class="hicontainer"  style="width:100%; height:400px;"></div>
          <h2 class="sub-header">设备列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th><input type="checkbox"  onclick="Checkall('checkbox1')"/></th>
                  <th>设备信息</th>
                  <th>在线总时长</th>
                  <th>位置</th>
                  <th>趋势图</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${list}" var="item" >
                <tr>
                  <td><input type="checkbox" name="checkbox1"/></td>
                  <!-- <td><a href="#" onclick="window.parent.location='detail1'">设备000111</a></td> -->
                  <td><a href="#" onclick="window.open('detail1?wifi_sn=${item.wifi_sn }&biz_num=${item.biz_num }&day=${item.day }&ip=${item.ip }&longitude=${item.longitude }&latitude=${item.latitude }&online_total_time=${item.online_total_time }')">
                  		${item.wifi_sn }<br/> ${item.ip }</a></td>
							<td>${item.online_total_time }</td>
							<td>
								<a  id="show" href="#" onclick="window.open('map?longitude=${item.longitude}&latitude=${ item.latitude}')">位置</a>
							</td>
														
							<td>
							
							<div id="hicontainer${item.wifi_sn }" class="hicontainer"  style="width:400%; height:250px;"></div>
							
							
							</td>
                </tr>
                </c:forEach>
                <!--  <tr>
                  <td><input type="checkbox" name="checkbox1"/></td>
                  <td><a href="#" onclick="window.parent.location='detail1'">设备000111</a></td>
                  <td><a href="#" onclick="window.open('detail1')">设备000111</a></td>
							<td>111</td>
							<td>
								<a  id="show" href="#" onclick="window.open('map')">北京</a>
							</td>
														
							<td>
							
							<div id="hicontainer2" class="hicontainer"  style="width:500px; height:250px;"></div>
							
							
							</td>
                </tr> -->
                
              </tbody>
            </table>
          </div>
       
    </div>
    <script type="text/javascript">
    function Checkall(XelementName){
        var checkboxes=document.getElementsByName(XelementName);
        for(var i=0;i<checkboxes.length;i++)
        {
                 checkboxes[i].checked=!checkboxes[i].checked;
        }
    }
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
	            name: '在线设备数',
	            data: count1
	        },
	        {
	            name: '离线设备数',
	            data: count2
	        }
	        
	        ]
	    });
	}
	
	function highchart1(container,hour,count1,count2) {
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
	            name: '开机状态',
	            data: count1
	        },
	        {
	            name: '在线状态',
	            data: count2
	        }
	        
	        ]
	    });
	}

	</script>
    
    <script type="text/javascript">
    $('#date111').datetimepicker({
		format: 'YYYY-MM-DD',  
        locale: moment.locale('zh-cn')
	});
    $('#createtime').datetimepicker({
		format: 'YYYY-MM-DD',  
        locale: moment.locale('zh-cn')
	});
    
    gethighchart();
    function search(){
    	var date = $("#date").val();
    	var device_id = $("#device_id").val();
    	var sn = $("#sn").val();
    	var name = $("#name").val();
    	window.location.href='day?date='+date+'&device_id='+device_id
		+'&sn='+sn+'&name='+name;
    }
    function gethighchart(){
    	var date = $("#date").val();
    	var device_id = $("#device_id").val();
    	var sn = $("#sn").val();
    	var name = $("#name").val();
    	//alert(name);
    	
		var list=$(".hicontainer");
		/* var hour=["00:00","01:00","02:00","03:00","04:00","05:00"];
		var count1=[100,90,85,95,98,90];
		var count2=[90,80,95,85,100,80]; */
		for(var i=0;i<list.length;i++){
			//highchart(list[i].id,hour,count1,count2);
			var myurl ='getmapdata?sn='+list[i].id+'&date='+date;
			$.post(myurl,function(result){
				if (result.success){
					if(result.sn=='hicontainer0'){
						highchart(result.sn,result.hour,result.count1,result.count2);
					}else{
						highchart1(result.sn,result.hour,result.count1,result.count2);
					}
					
				} else {
					$.messager.show({	// show error message
						title: 'Error',
						msg: result.errorMsg
					});
				}
			},'json');
		}
    }
		
		
		
	</script>
    
    

   


</body>
</html>