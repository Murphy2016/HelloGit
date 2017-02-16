<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/color.css">
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/demo/demo.css">
	<script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../html/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script src="../html/js/highcharts/highcharts.js"></script>
	<script src="../html/js/highcharts/exporting.js"></script>

	<script type="text/javascript">
		function myformatter(date){  
		    var y = date.getFullYear();  
		    var m = date.getMonth()+1;  
		    var d = date.getDate();  
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
		} 
		$(function () {
			var curr_time = new Date(); 
			var prev_time = new Date(); 
			var date = curr_time.getDate();
			//date = date - 7;
			prev_time.setDate(date - 8);
			curr_time.setDate(date-1);
			
		    $("#dd").datebox("setValue",myformatter(prev_time));
		    $("#dd1").datebox("setValue",myformatter(curr_time));
		    maptable();
		});
	</script>

</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
	<div style="margin-bottom:20px;display:inline">
            <label class="label-top" style="display:inline">开始日期:</label>
            <input id="dd" class="easyui-datebox" style="width:100px;height:26px;display:inline">
<!--     </div> -->
<!--     <div style="margin-bottom:20px"> -->
            <label class="label-top" style="display:inline">结束日期:</label>
            <input id="dd1" class="easyui-datebox" style="width:100px;height:26px;display:inline">
    </div>
<!--     <input id="search"  placeholder="手机号" style="width:200px;display:inline"></input> -->
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    <iframe id="export" style="display:none" src = ""></iframe>
    <a  class="easyui-linkbutton" onclick="exportTable()">导出</a>
<!--     <input id="search" class="easyui-searchbox" data-options="prompt:'手机号',searcher:doSearch" style="width:300px"></input> -->
<!--     <a  class="easyui-linkbutton" onclick="maptable()">图表</a> -->
     <div id="container" hide="true" style="visibility:none;display:none;width:100%; height:400px;"></div>
	
	<div style="margin-top:10px;">
	<table id="dg" title="统计信息" class="easyui-datagrid" style="width:100%;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				  <th field="date1"        width="400">日期</th>
				<th field="realname_rate"             width="500">实名认证整体实名率</th>
				<th field="realname_rate"             width="500">公共交通工具实名率</th>
				
				

			</tr>
		</thead>
	</table>
	
	
	</div>
	</div>
	
	<script>
        function doSearch(){
        	var v = $('#dd').datebox('getValue');   
        	var v1 = $('#dd1').datebox('getValue'); 
        	
        		if( (v && v.length>0) && (v1 && v1.length>0) ){
		            $('#dg').datagrid({
		                url:'getdata?mobileno='+value+'&date1='+v+'&date2='+v1
		                
		            });
        		}
        		
        	
        }
        function exportTable(){
			var label=document.getElementById("export");
			label.src="exportTable";
		}
        
        function maptable(){
        	var v = $('#dd').datebox('getValue');   
        	var v1 = $('#dd1').datebox('getValue'); 
        	//var value = document.getElementById("search").value;
        	//if(value && value.length>0){
        		if( (v && v.length>0) && (v1 && v1.length>0) ){
        			var myurl ='getdata1?date1='+v+'&date2='+v1;
        			$.post(myurl,function(result){
        				if (result.success){
        					document.getElementById("container").style.visibility="visible";
        					document.getElementById("container").style.display="";
        					highchart(result.date,result.count,result.count1);
        					
        				} else {
        					$.messager.show({	// show error message
        						title: 'Error',
        						msg: result.errorMsg
        					});
        				}
        			},'json');
        		}
        		
        	//}
			
		}
        
        function highchart(date,count,count1) {
	        $('#container').highcharts({
	            title: {
	                text: '用户上网实名率统计',
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
	                categories: date
	            },
	            yAxis: {
	                title: {
	                    text: '百分比'
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
	                name: '实名认证整体实名率',
	                data: count
	            },
	            {
	                name: '公共交通工具实名率',
	                data: count1
	            }
	            ]
	        });
	    }
        
    </script>
	<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
		.fitem input{
			width:160px;
		}
		.wrap{
			margin:0 auto;
			width:960px;
		}
	</style>
	
</body>
</html>