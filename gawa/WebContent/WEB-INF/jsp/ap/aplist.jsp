<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%request.setCharacterEncoding("UTF-8");%>
<%
String userlevel=(String)request.getSession().getAttribute("userlevel");
%>
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

	<script type="text/javascript">
		/* $(function () {
			var obj = document.getElementById("mySelect22");
			obj.options.add(new Option("content", "key"));
		});  */
	
	
		$(function () {
			//var test=1;
			$.post('getdata01',function(result){
				//alert("11");
				if (result.success){
					//alert("22");
					var obj = document.getElementById("mySelect11");
					var arr = result.rows;
					for(var i=0;i<arr.length;i++){
						 var elem = arr[i];
						 obj.options.add(new Option(elem.area_name+"-"+elem.area_code, elem.area_code));
					}

					
					//$('#dg').datagrid('reload');	// reload the user data
				}else{
					//alert("33");
				}
			},'json');
			
			
			
			
			
			
			
			//var obj = document.getElementById("mySelect11");
			//obj.options.add(new Option("放假", 3));
			
		});
		function testselect (){
			var count = document.getElementById('mySelect88').options.length;
			//alert(count);
			var obj = document.getElementById("mySelect88");
			//var option = document.getElementById('mySelect88').options;
			var html=obj.innerHTML;
			alert(html);
			for(i=1;i<count;i++){
				var aaa = option[i].value;
				var bbb = option[i].text;
				//alert(aaa+'+'+bbb);
			}
		}
	</script>

</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
<!--     <input class="easyui-searchbox" data-options="prompt:'设备编号/mac/场所编码/车牌号',searcher:doSearch" style="width:300px"></input> -->
    <input id="search"  placeholder="设备编号/mac/场所编码/车牌号" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
<!--     <a  class="easyui-linkbutton" onclick="testselect()">testselect</a> -->
    
			<!-- <select id="mySelect22" class="easyui-combobox" name="area_code" >
                <option value="1" selected>营业</option>
                <option value="2">停业</option>
            </select> -->
    
<%--     <a id="btn" href="<%=basePath %>zip/145-353030334-330300-330300-mobileap-00001.zip" class="easyui-linkbutton" >zip下载</a> --%>
    <div style="margin-top:10px;">
	<table id="dg" title="移动AP信息" class="easyui-datagrid" style="width:1400px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="allap_id"               width="50">序号</th>
<!-- 				<th field="area_id"             width="50">场所序号</th> -->
				<th field="equipment_code"             width="80">设备编码</th>
				<th field="mac"          width="70">设备mac</th>
				<th field="area_code"        width="60">场所编码</th>
				<th field="station_info"        width="50">站点信息</th>
				<th field="subway_line_info"         width="50">线路信息</th>
				<th field="subway_vehical_info"           width="50">地铁车辆信息</th>
				<th field="subway_compartment_no"              width="50">地铁车厢信息</th>
				<th field="car_code"            width="70">车牌号</th>
				<th field="display_approval"            width="70">审批状态</th>
<!-- 				<th field="state"              width="70">设备状态</th> -->

			</tr>
		</thead>
	</table>
	</div>
	
	<c:if test="${userlevel == 1}">
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增设备</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑设备</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除设备</a>
	</div>
	</c:if>
	
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
				<select id="mySelect11"  name="area_code"  style="border-radius: 5px">
                <!-- <option value="1" selected>营业</option>
                <option value="2">停业</option> -->
                </select>
			</div>
			<div class="fitem">
				<label>站点信息:</label>
				<input name="station_info" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>线路信息:</label>
				<input name="subway_line_info" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>地铁车辆信息:</label>
				<input name="subway_vehical_info" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>地铁车厢信息:</label>
				<input name="subway_compartment_no" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>车牌号:</label>
				<input name="car_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>审批状态:</label>
				<select  name="approval" style="border-radius: 5px">
                <option value="0" selected>未审批</option>
                <option value="1">审批</option>
                </select>
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
				url = 'update?id='+row.allap_id;
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