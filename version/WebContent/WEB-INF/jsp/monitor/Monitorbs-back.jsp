<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user=(String)request.getSession().getAttribute("userctx");

%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../html/bootstrap/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../html/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../html/bootstrap/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../html/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../html/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
    
    
    <style type="text/css">
			/* body {
				//background:url(../css/versionback.jpg) top left;
				background-color: rgb(67,105,154);
				margin: 0;
			} */
			#strong {
				//font-size: 24px;
			}
			input,button {
				 border-radius:5px;
			}
	</style>
	
	<!-- <script type="text/javascript">
		
		$(function () {
			highchart('hicontainer');
			highchart('hicontainer1');
		});
	</script> -->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">后台监控管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           
            <li><a href="#"><%=user%></a></li>
            <li><a href="#">退出</a></li>
          </ul>
          <!-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">设备监控</a></li>
            <li><a href="#">分组管理</a></li>
            <li><a href="#">任务单管理</a></li>
            <li><a href="#">任务管理</a></li>
          </ul>
          <!-- <ul class="nav nav-sidebar">
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
          </ul> -->
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">设备监控</h1>

          <div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;">日期：</div>
				<input  style="display:inline;" placeholder="yyyy-MM-dd"></input>
				<div style="display:inline;">设备编号：</div>
				<input  style="display:inline;"></input>
				<div style="display:inline;">设备SN码：</div>
				<input  style="display:inline;"></input>
				<div style="display:inline;">设备名称：</div>
				<input  style="display:inline;"></input>
				
				<button style="margin-left:2px;margin-top:5px;">搜索</button>
				
			</div>
		  <br/><br/>
		  
		  
		  
		  <div class="well well-sm">
		   	<table>
			<tr>
			<td><div style="margin-left:37px;">
				
				<div style="margin-left:30px;display:inline;"><h4>设备总数: 9999</h4></div>
				<br>			
				<div id="strong" style="margin-left:30px;display:inline;">在线</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				111
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">开机</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				222
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">通讯</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				333
				</div>
			</div></td>
			<td><div style="margin-left:130px;">
				
				<div style="margin-left:30px;display:inline;"><h4>设备总时长: 9999</h4></div>
				<!-- <div  style="display:inline;">
				9999
				</div> -->	
				<br>			
				<div id="strong" style="margin-left:30px;display:inline;">最大</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				888
				</div>
				<div id="strong" style="margin-left:30px;display:inline;">最小</div>
				<div id="strong" style="margin-left:1px;display:inline;">
				111
				</div>
			</div></td>
			</tr>
			</table>
		  </div>
		  <div id="hicontainer" hide="true" style="width:100%; height:400px;"></div>
          <h2 class="sub-header">设备列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  
                  <th>设备信息</th>
                  <th>在线总时长</th>
                  <th>位置</th>
                  <th>趋势图</th>
                </tr>
              </thead>
              <tbody>
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
    
    
    
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="../html/bootstrap/js/bootstrap.min.js"></script>
    <script src="../html/bootstrap/assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../html/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>