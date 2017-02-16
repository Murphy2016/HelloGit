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
</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
    <input class="easyui-searchbox" data-options="prompt:'Please Input Value',searcher:doSearch" style="width:300px"></input>
    
    <div style="margin-top:10px;">
	<table id="dg" title="统计信息" class="easyui-datagrid" style="width:800px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="statistic_id"               width="50">统计序号</th>
				<th field="total"             width="50">设备总数</th>
				<th field="working"             width="50">正常工作数</th>
				<th field="unworking"          width="50">故障数</th>
				<th field="date1"        width="50">日期</th>

			</tr>
		</thead>
	</table>
	</div>
	
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增统计</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑统计</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除统计</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">统计信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>统计序号:</label>
				<input name="statistic_id" class="easyui-textbox" disabled="true" required="true">
			</div>
			<div class="fitem">
				<label>设备总数:</label>
				<input name="total" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>正常工作数:</label>
				<input name="working" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>故障数:</label>
				<input name="unworking" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>日期:</label>
				<input name="date1" class="easyui-textbox" >
			</div>

		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','新统计');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑统计');
				$('#fm').form('load',row);
				url = 'update?id='+row.area_id;
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
				//$.messager.defaults = { ok:"是", cancel:"否" };
				$.messager.confirm('确认','确定删除这条统计信息？',function(r){
					
					if (r){
						$.post('delete',{area_id:row.area_id},function(result){
							
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
			}
		}
	</script>
	<script>
        function doSearch(value){
            //alert('You input: ' + value);
            //var tbl=document.getElementById("dg"); 
            //tbl.setAttribute("url", "test?id=2");
            //$('#dg').datagrid('reload');
            $('#dg').datagrid({
                url:'getdata?id='+value
                
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