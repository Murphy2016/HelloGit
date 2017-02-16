<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String user=(String)request.getSession().getAttribute("userctx");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备详情</title>
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/icon.css">
	<script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
  	<script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
  	<script type="text/javascript" src="../html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
  	
	<style type="text/css">
		body {
			background:url(../css/versionback.jpg) top left;
			margin: 0;
		}
	</style>


	<script type="text/javascript">
		
		$(function () {
			highchart('hicontainer');
			highchart('hicontainer1');
		});
		
		function highchart(container) {
		    $('#'+container).highcharts({
		    	chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            
		            backgroundColor: 'blue'
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
		            categories: ['00:00','01:00','02:00','03:00','04:00','05:00']
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
		            data: [100,110,120,110,100,90]
		        },
		        {
		            name: '离线设备数',
		            data: [110,90,130,150,100,80]
		        }
		        
		        ]
		    });
		}
	</script>
</head>
<body>

<div id="container" style="width:100%;height:1500px">
	<div style="background-color:transparent;height:100%">
	
		<div id="menu" style="background-color:transparent;width:25%;float:left;">
				<div style="margin-left:20px;margin-top:20px;width:183px;height:63px">
					<a style="width:183px;height:63px">
			        	<img style="max-width:100%;" alt="logo" src="../css/logo1.png">
			    	</a>
				</div>
				<a  href="" style="float:left;margin-right:5px;margin-left:20px;margin-top:20px;">
						              <img   alt="User Picture" src="../css/user.png"></img>
						              
				</a>
				<div>
					<div style="margin-top:20px;"> 
						<a  style="color:blue;" ><%=user%></a>  
					</div>
					<div style="margin-top:5px;">
						<small>
						<a style="margin-left:20px;color:blue;" href="">退出</a>
						</small> 
					</div>
				</div>
				<div style="clear:both;margin-left:20px;">
					<br>
					<div style="background-color:blue;width:80px;">设备监控</div>
					<br>
					<div style="background-color:blue;width:80px;" onclick="window.location.href='search'">单台设备</div>
				</div>
		</div>
		
		<div id="content" style="background-color:transparent;width:70%;float:left;">
			<div>当前设备的名字</div>
			<div>
				<label>状态：</label>
				<label>在线</label>
			</div>
			<div>
				<label>编号：</label>
				<label>394297375345</label>
			</div>
			<div>
				<label>SN码：</label>
				<label>9384289430</label>
			</div>
			<div>
				<label>IP地址：</label>
				<label>192.168.0.1</label>
			</div>
			<div>
				<label>最后通讯：</label>
				<label>2016-12-27 09:00:00</label>
				<label>2016-12-27 09:00:00</label>
				
			</div>
			<div>设备趋势图</div>
			
			<div id="hicontainer" hide="true" style="width:100%; height:400px;"></div>
			
			<div style="width:40%;height:400px;background-color:red;float:left;overflow:auto;">
			
			<ul id="tt" class="easyui-tree"></ul>
			
			</div>
			
			
			
			
			<div style="width:60%;height:400px;background-color:yellow;float:left;">选中内容
			
			<div id="treecontent" style="margin-left:5px">未选择文件或文件夹
			<!-- <div style="border:1px solid #000;display:inline-block;width:100px;height:100px;">aa</div>
			 -->
			</div>
			
			</div>
			<script>
				$('#tt').tree({
				    url:'gettreedata',
				    onClick: function(node){
						//alert(node.text);  // alert node text property when clicked
						var content = $('#treecontent');
						var html ="";
						if(!node.children){						
							html += "<div style='border:1px solid #000;display:inline-block;width:100px;height:100px;margin-left:5px;margin-top:5px;'>"+
								node.text +
								"</div>";
							
						}else{
							 $.each(node.children,function(i,value){
								 html += "<div style='border:1px solid #000;display:inline-block;width:100px;height:100px;margin-left:5px;margin-top:5px;'>"+
									value.text +
									"</div>";
							 });
						}
						content.empty();	
						content.append(html);	 
					}
				});
			</script>
			
			
			
			<div style="clear:both;"></div>
		
		
		</div>
	
	
	
	</div>



</div>	


</body>
</html>