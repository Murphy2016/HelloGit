<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String userlevel=(String)request.getSession().getAttribute("userlevel");
%>
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

	<script type="text/javascript">
		function myformatter(date){  
		    var y = date.getFullYear();  
		    var m = date.getMonth()+1;  
		    var d = date.getDate();  
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
		} 
		$(function () {
			var curr_time = new Date(); 
		    $("#dd").datebox("setValue",myformatter(curr_time));
		});
	</script>

</head>
	
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
	<div style="margin-bottom:20px;display:inline">
            <label class="label-top" style="display:inline">日期:</label>
            <input id="dd" class="easyui-datebox" style="width:100px;height:26px;display:inline">
    </div>
<!--     <input class="easyui-searchbox" data-options="prompt:'设备编号',searcher:doSearch" style="width:300px"></input> -->
    <input id="search"  placeholder="设备编码/mac/场所编号" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    
    <div style="margin-top:10px;">
	<table id="dg" title="维修信息" class="easyui-datagrid" data-options="nowrap:false" 
			style="width:100%;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="maintain_id"               width="50">序号</th>
				<th field="area_code"             width="50">场所编号</th>
				<th field="equipment_code"             width="80">设备编码</th>
				<th field="mac"             width="50">设备mac</th>
				<th field="date1"          width="50">维修日期</th>
				<th field="note"        width="50">备注</th>
				<th field="need_time_display"         width="50">维修时长</th>

			</tr>
		</thead>
	</table>
	</div>
	
	<c:if test="${userlevel == 1}">
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增维修</a>
		
		
	</div>
	</c:if>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">维修信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>序号:</label>
				<input name="maintain_id" class="easyui-textbox" disabled="true" required="true">
			</div>
			<div class="fitem">
				<label>场所编号:</label>
				<input name="area_code" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>设备编码:</label>
				<input name="equipment_code" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>设备mac:</label>
				<input name="mac" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>维修日期:</label>
				<input name="date1" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>备注:</label>
				<input name="note" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>维修时长:</label>
				<input name="need_time" class="easyui-textbox" >
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
			$('#dlg').dialog('open').dialog('setTitle','新维修信息');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑维修信息');
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
				$.messager.confirm('确认','确定删除这条信息？',function(r){
					
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
        function doSearch(){
        	var value = document.getElementById("search").value;
        	var v = $('#dd').datebox('getValue');   
        	//alert(v)
            //alert('You input: ' + value);
            //var tbl=document.getElementById("dg"); 
            //tbl.setAttribute("url", "test?id=2");
            //$('#dg').datagrid('reload');
            if(value && value.length>0){
	            $('#dg').datagrid({
	                url:'getdata?code='+value
	                
	            });
            }else  if(v && v.length>0){
            	$('#dg').datagrid({
	                url:'getdata?date='+v
	                
	            });
            }
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