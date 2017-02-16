<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String userlevel=(String)request.getSession().getAttribute("userlevel");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
	<link rel="stylesheet" type="text/css" href="html/js/jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="html/js/jquery-easyui-1.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="html/js/jquery-easyui-1.4/themes/color.css">
	<link rel="stylesheet" type="text/css" href="html/js/jquery-easyui-1.4/demo/demo.css">
	<script type="text/javascript" src="html/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="html/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
   <!--  <input id="search"  placeholder="厂商名称/机构代码/地址/联系人" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a> -->
    
    <div style="margin-top:10px;">
	<table id="dg" title="版本信息" class="easyui-datagrid" data-options="nowrap:false"
			style="width:100%;"
			url="version/getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="device_id"               width="50">设备号</th>
				<th field="biz_num"             width="50">设备号1</th>
				<th field="software_name"             width="50">软件名</th>
				<th field="version"          width="50">版本</th>
				<th field="update_time"        width="50">更新时间</th>
				<th field="create_time"         width="50">创建时间</th>
				

			</tr>
		</thead>
	</table>
	</div>
	<c:if test="${userlevel == 1}">
	<!-- <div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增厂商</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑厂商</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除厂商</a>
	</div> -->
	</c:if>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">厂商信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>序号:</label>
				<input name="org_id" class="easyui-textbox" disabled="true" required="true">
			</div>
			<div class="fitem">
				<label>场所名称:</label>
				<input name="name1" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>机构代码:</label>
				<input name="code1" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>厂商地址:</label>
				<input name="address" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>厂商联系人:</label>
				<input name="contactor" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>电话号码:</label>
				<input name="contactor_tel" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>电子邮件:</label>
				<input name="contactor_mail" class="easyui-textbox" >
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
			$('#dlg').dialog('open').dialog('setTitle','新厂商');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑厂商');
				$('#fm').form('load',row);
				url = 'update?id='+row.org_id;
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
				$.messager.confirm('确认','确定删除这条厂商信息？',function(r){
					
					if (r){
						$.post('delete',{org_id:row.org_id},function(result){
							
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
            //alert('You input: ' + value);
            //var tbl=document.getElementById("dg"); 
            //tbl.setAttribute("url", "test?id=2");
            //$('#dg').datagrid('reload');
            var value = document.getElementById("search").value;
            $('#dg').datagrid({
                url:'getdata?search='+value
                
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