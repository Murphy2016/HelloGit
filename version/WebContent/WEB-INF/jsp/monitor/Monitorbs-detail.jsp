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
    <link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/icon.css">
    <script src="../html/js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="../html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
    <script src="../html/bootstrap/js/bootstrap.min.js"></script>
	<script src="../html/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
    
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
            <li><a href="../group/index">分组管理</a></li>
            <li><a href="../taskext/index">任务单管理</a></li>
            <li><a href="../task/index">任务管理</a></li>
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
          <h1 class="page-header">设备详情</h1>
          
          
          

          <div><h3>当前设备的名字</h3></div>
			<%-- <div>
				<label>状态：</label>
				<label>${state }</label>
			</div> --%>
			<div>
				<label>编号：</label>
				<label >${device_id }</label>
			</div>
			<div>
				<label>SN码：</label>
				<label id="sn">${sn }</label>
			</div>
			<div>
				<label>IP地址：</label>
				<label>${ip }</label>
			</div>
			<div>
				<label>最后通讯：</label>
				<label>${last_com }</label>
				
				
			</div>
			<div><h3>设备趋势图</h3></div>
			
			<div id="hicontainer" hide="true" style="width:100%; height:400px;"></div>
			
			
			
			
         <ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#home" data-toggle="tab">
					 文件管理
				</a>
			</li>
			<li><a href="#week" data-toggle="tab">命令执行</a></li>
			
			
		</ul> 
		 <div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				
				<div style="width:30%;height:400px;background-color:AliceBlue;float:left;overflow:auto;">
				
				<ul id="tt" class="easyui-tree"></ul>
				
				</div>
				
				
				
				
				<div style="width:70%;height:400px;background-color:#E9F3FB;float:left;">选中内容
				
					<div id="treecontent" style="margin-left:5px">未选择文件或文件夹
					<!-- <div style="border:1px solid #000;display:inline-block;width:100px;height:100px;">aa</div>
					 -->
					</div>
				
				
				</div>
			</div>
			<div class="tab-pane fade" id="week">
				<div id="compart"   style="background-color:black;border-radius:10px;width:100%;height:500px">
	    		<textarea onkeydown='if(event.keyCode==13){execute();}' 
	    		 	style="border-radius:8px;width:95%;height:80px;float:left"></textarea ><button onclick="execute()" style="height:80px;">执行</button>
		          <div style="clear:both;"></div>
		          <br/>
		        <font color="white"><p>执行结果显示！</p></font>
	        	</div>
				
			
			
			
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
		  
		  
		  	
		  
		  
		  
        </div>
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
	            text: '设备在线统计',
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
	        series: [
	        {
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

		
		{
			var sn=$("#sn").text();
			//alert(sn);
			var myurl ='getmapdata?sn='+sn;
			$.post(myurl,function(result){
				if (result.success){
					highchart("hicontainer",result.hour,result.count1,result.count2);
					
				} else {
					$.messager.show({	// show error message
						title: 'Error',
						msg: result.errorMsg
					});
				}
			},'json');
		}
		
		function execute(){
			var command=$("textarea").val();
			var myurl ='getcmdret?command='+command;
			$.post(myurl,function(result){
				if (result.success){
					$("p").text('您输入的内容是：'+result.command);
					$("textarea").val('');
					
				} else {
					$.messager.show({	// show error message
						title: 'Error',
						msg: result.errorMsg
					});
				}
			},'json');
		}
		
	</script>
    
    
    
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    
    <!-- <script src="../html/bootstrap/assets/js/docs.min.js"></script> -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    
  </body>
</html>