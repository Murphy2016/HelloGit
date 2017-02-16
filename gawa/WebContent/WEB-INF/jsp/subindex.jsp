<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
	<script type="text/javascript">
	$(function () {
        $('#container').highcharts({
            title: {
                text: 'Monthly Average Temperature',
                x: -20 //center
            },
            subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },
            credits:{
                enabled:false//不显示highCharts版权信息
            },
            lang:{
            	downloadJPEG: "下载JPEG图片",
                downloadPDF: "下载PDF文档",
                downloadPNG: "下载PNG图片",
                downloadSVG: "下载SVG矢量图",
                printChart: "打印图片",
                exportButtonTitle: "导出图片"
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
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }]
        });
    });
	
	</script>
</head>
<body>
	<h2>Basic CRUD Application</h2>
	<p>Click the buttons on datagrid toolbar to do crud actions.</p>
	<iframe id="export" style="display:none" src = ""></iframe>
	<a  class="easyui-linkbutton" onclick="exportTable()">导出</a>
	<a  class="easyui-linkbutton" onclick="maptable()">图表</a>
<!-- 	<div id="content"></div> -->
	
		
	
	<table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="firstname" width="50">first name</th>
				<th field="lastname" width="50">发呆</th>
				<th field="phone" width="50">座机电话</th>
				<th field="email" width="50">电邮</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>First Name:</label>
<!-- 				<input name="firstname" class="easyui-textbox" required="true">  -->
				<select class="easyui-combobox" name="firstname" >
                <option value="Tom" selected>Tom</option>
                <option value="Jack">Jack</option>
                <option value="Rose">Rose</option>
				</select>
			</div>
			<div class="fitem">
				<label>Last Name:</label>
				<input name="lastname" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>Phone:</label>
				<input name="phone" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>Email:</label>
				<input name="email" class="easyui-textbox" validType="email">
				
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
	</div>
	<div id="container" style="width:100%; height:400px;"></div>
	<script type="text/javascript">
		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','New User');
			$('#fm').form('clear');
			url = 'save_user.php';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','Edit User');
				$('#fm').form('load',row);
				url = 'update_user.php?id='+row.id;
			}else{
				alert("请选中一行！");
			}
		}
		function saveUser(){
			$('#fm').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.errorMsg){
						$.messager.show({
							title: 'Error',
							msg: result.errorMsg
						});
					} else {
						$('#dlg').dialog('close');		// close the dialog
						$('#dg').datagrid('reload');	// reload the user data
					}
				}
			});
		}
		function destroyUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
					if (r){
						$.post('destroy_user.php',{id:row.id},function(result){
							if (result.success){
								$('#dg').datagrid('reload');	// reload the user data
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.errorMsg
								});
							}
						},'json');
					}
				});
			}{
				alert("请选中一行！");
			}
		}
		function exportTable(){
			//var label=document.getElementById("export");
			//label.src="exportTable";
			//document.getElementById('export').contentWindow.location.reload(true);
			///var label=document.getElementById("content");   
			///label.innerHTML="<iframe id=\"iframe1\"  src = \"exportTable\" frameborder=\"0\" style=\"width:0px;height:0px\"></iframe>";
			var label=document.getElementById("export");
			label.src="exportTable";
		}
		
		function maptable(){
			$.post('maptable',{id:3},function(result){
				if (result.success){
					highchart(result.data);
					
				} else {
					$.messager.show({	// show error message
						title: 'Error',
						msg: result.errorMsg
					});
				}
			},'json');
		}
		function highchart(dataarr) {
	        $('#container').highcharts({
	            title: {
	                text: 'Monthly Average Temperature',
	                x: -20 //center
	            },
	            subtitle: {
	                text: 'Source: WorldClimate.com',
	                x: -20
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
	                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
	                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	            },
	            yAxis: {
	                title: {
	                    text: 'Temperature (°C)'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                valueSuffix: '°C'
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'middle',
	                borderWidth: 0
	            },
	            series: [{
	                name: 'Tokyo',
	                data: dataarr
	            }]
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
	</style>
</body>
</html>