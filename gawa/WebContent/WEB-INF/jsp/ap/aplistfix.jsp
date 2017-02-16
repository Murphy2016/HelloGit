<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<!--     <input class="easyui-searchbox" data-options="prompt:'设备编号',searcher:doSearch" style="width:300px"></input> -->
    <input id="search"  placeholder="设备编号" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
<%--     <a id="btn" href="<%=basePath %>zip/145-353030334-330300-330300-fixedap-00001.zip" class="easyui-linkbutton" >zip下载</a> --%>
    <div style="margin-top:10px;">
	<table id="dg" title="固定AP信息" class="easyui-datagrid" style="width:1400px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="allap_id"               width="50">序号</th>
<!-- 				<th field="area_id"             width="50">场所序号</th> -->
				<th field="equipment_code"             width="50">设备编号</th>
				<th field="mac"          width="50">设备mac</th>
				<th field="area_code"        width="50">场所编码</th>
				<th field="longitude"         width="50">设备经度</th>
				<th field="latitude"      width="50">设备纬度</th>
				<th field="floor1"       width="50">场所楼层</th>
<!-- 				<th field="state"              width="70">设备状态</th> -->

			</tr>
		</thead>
	</table>
	</div>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增设备</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑设备</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除设备</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">设备信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>序号:</label>
				<input name="allap_id" class="easyui-textbox" disabled="true" required="true">
			</div>
			<!-- <div class="fitem">
				<label>场所序号:</label>
				<input name="area_id" class="easyui-textbox" required="true">
			</div> -->
			<div class="fitem">
				<label>设备编码:</label>
				<input name="equipment_code" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>设备mac:</label>
				<input name="mac" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所编码:</label>
				<input name="area_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备经度:</label>
				<input name="longitude" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备纬度:</label>
				<input name="latitude" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所楼层:</label>
				<input name="floor1" class="easyui-textbox" >
			</div>
			<!-- <div class="fitem">
				<label>设备状态:</label>
				<input name="state" class="easyui-textbox" >
			</div> -->

		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newUser(){
			$('#dlg').dialog('open').dialog('setTitle','新设备');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑设备');
				$('#fm').form('load',row);
				url = 'update?id='+row.area_id;
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
				//$.messager.defaults = { ok:"是", cancel:"否" };
				$.messager.confirm('确认','确定删除这条设备信息？',function(r){
					
					if (r){
						$.post('delete',{ap_id:row.allap_id},function(result){
							
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
			}else{
				alert("请选中一行！");
			}
		}
	</script>
	<script>
        function doSearch(){
        	var value = document.getElementById("search").value;
            $('#dg').datagrid({
                url:'getdata?code='+value
                
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