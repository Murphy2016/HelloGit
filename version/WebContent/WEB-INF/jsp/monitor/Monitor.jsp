<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user=(String)request.getSession().getAttribute("userctx");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>设备监控</title>
	<!-- <script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script> -->
  	<script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
  	<script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
	<style type="text/css">
		body {
			//background:url(../css/versionback.jpg) top left;
			margin: 0;
		}
		.border {
			border:1px solid gray
		}
		.line {
				margin-left:37px;
				margin-top:8px;
				margin-bottom:8px;
				width:95%;
				height:3px;
				background-color:rgb(134,143,153)
		}
	</style>


	<script type="text/javascript">
		
		$(function () {
			highchart('hicontainer');
			highchart('hicontainer1');
		});
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
						<a   ><%=user%></a>  
					</div>
					<div style="margin-top:5px;">
						<small>
						<a style="margin-left:20px;" href="http://baidu.com" target="_Blank">退出</a>
						</small> 
					</div>
				</div>
				<div style="clear:both;margin-left:20px;">
					<br>
					<div style="width:80px;">设备监控</div>
					<br>
					<div style="width:80px;" onclick="window.location.href='detail'">单台设备</div>
				</div>
		</div>
		
		<div id="content" style="background-color:transparent;float:left;">
			<!-- <div style="clear:both;display:none;"></div> -->
			<div style="margin-left:37px;margin-top:10px;">
				<button>日</button>
				<button>周</button>
				<button>月</button>
				<button>时间段</button>
			</div>
			<div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;">日期：</div>
				<input style="display:inline;" placeholder="yyyy-MM-dd"></input>
				<div style="display:inline;">设备编号：</div>
				<input style="display:inline;"></input>
				<div style="display:inline;">设备SN码：</div>
				<input style="display:inline;"></input>
				<div style="display:inline;">设备名称：</div>
				<input style="display:inline;"></input>
				<br>
				<button style="margin-left:200px;margin-top:5px;">搜索</button>
				
			</div>
			<div class="line"></div>
			<table>
			<tr>
			<td><div style="margin-left:37px;margin-top:20px;width:300px:float:left">
				
				<div style="margin-left:30px;display:inline;"><big>设备总数</big></div>
				<div  style="display:inline;">
				<big>9999</big>
				</div>	
				<br>			
				<div  style="margin-left:30px;display:inline;"><big>在线</big></div>
				<div style="margin-left:1px;display:inline;">
				<big>111</big>
				</div>
				<div  style="margin-left:30px;display:inline;"><big>开机</big></div>
				<div style="margin-left:1px;display:inline;">
				<big>222</big>
				</div>
				<div  style="margin-left:30px;display:inline;"><big>通讯</big></div>
				<div style="margin-left:1px;display:inline;">
				<big>333</big>
				</div>
			</div></td>
			<td><div style="margin-left:130px;margin-top:20px;width:400px;float:left">
				
				<div style="margin-left:30px;display:inline;"><big>设备总时长</big></div>
				<div  style="display:inline;">
				<big>9999</big>
				</div>	
				<br>			
				<div  style="margin-left:30px;display:inline;"><big>最大</big></div>
				<div style="margin-left:1px;display:inline;">
				<big>888</big>
				</div>
				<div  style="margin-left:30px;display:inline;"><big>最小</big></div>
				<div style="margin-left:1px;display:inline;">
				<big>111</big>
				</div>
			</div></td>
			</tr>
			</table>
			
			
			<div id="hicontainer" hide="true" style="width:100%; height:400px;"></div>
			<div style="margin-top:5px;margin-bottom:5px;">
			<div style="margin-left:40px;margin-top:10px;
					width:100px;height:30px;display:inline;"><big>设备列表</big></div>
				<!-- <div  style="margin-left:30px;display:inline;">设备总数</div>
				<div style="margin-left:1px;display:inline;">
					222
				</div> -->
				<button style="margin-left:500px;">添加组</button>
			</div>
			<div style="margin-left:40px;float:left;height:500px;overflow:auto;">
				<table border="1" width="700px" height="500px" >
					<thead>
						<tr>
							
							<th>设备信息</th>
							<th>在线总时长</th>
							<th>位置</th>
							<th>趋势图</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody >
						
						<tr>
							<td>设备000111</td>
							<td>111</td>
							<td>
								<a  id="show" href="map">北京</a>
							</td>
														
							<td>
							
							<div id="hicontainer1" hide="true" style="width:500px; height:250px;"></div>
							
							
							</td>
						
						</tr>
						
					</tbody>
									
				</table>			
						
					
			</div>
		
		
		
		
		
		</div>
	
	
	</div>

</div>



<script type="text/javascript">

	function highchart(container) {
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



<style>

/*超链接的样式:蓝色按钮*/

/*人为制造一个占据整个屏幕的Div,其透明度为0.7且z-index为9999使之前的页面被压在底层无法点击*/
#fullScreen
{
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    opacity: 0.7;
    background-color: black;
    z-index: 9999;
}
/*浮层,可随意设置大小宽高，但是z-index必须比上面fullScreen大才能显示出来*/
#floatLayer
{
    position: fixed;
    width: 800px;
    height: 800px;
    left: 14%;
    top: 15%;
    background-color: white;
    z-index: 10000;
}
</style>

<div id="jsp" style="display:none">
	<a href='#' id='hide'>隐藏浮层</a>
</div>


<script>
   	function float()
    {
    	var jsp = $('#jsp').text();
        //点击弹出浮层
        $("#show").click(function()
        {
            //清除之前的样式
            $("#fullScreen,#floatLayer").remove();
            $("body").append
            (
                //占据整个屏幕Div
                "<div id='fullScreen'></div>"+
                //浮层区
                "<div id='floatLayer'>" +
                jsp+
                "</div>"
            );
            //隐藏浮层
            $("#hide").click(function()
            {
                $("#fullScreen,#floatLayer").remove();
            });
        });
    }
</script>

</body>


</html>