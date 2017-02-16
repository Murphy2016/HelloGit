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
		    $("#dd1").datebox("setValue",myformatter(curr_time));
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
    <input id="search"  placeholder="场所编码" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    
<%--     <a id="btn" href="<%=basePath %>zip/145-353030334-330300-330300-netlog-00001.zip" class="easyui-linkbutton" >zip下载</a> --%>
	
	<div style="margin-top:10px;">
	<table id="dg" title="上网日志信息" class="easyui-datagrid" style="width:4000px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="log_id"               width="60">序号</th>
				<!-- <th field="area_id"             width="50">场所序号</th>
				<th field="allap_id"             width="50">设备序号</th> -->
				<th field="session_id"          width="250">会话id</th>
				<th field="create_time_display"        width="140">时间</th>
				<th field="auth_area_code"         width="130">场所编码</th>
				<th field="protocol_type_display"      width="60">协议类型</th>
				<th field="ip_lan"       width="90">内网ip</th>
				<th field="ip_port_lan"        width="60">内网端口</th>
				<th field="ip4_wan"         width="90">源外网ipv4地址</th>
				<th field="ip6_wan"           width="90">源外网ipv6地址</th>
				<th field="ip4_port_start_wan"              width="110">源外网ipv4起始端口</th>
				<th field="ip4_port_end_wan"            width="110">源外网ipv4结束端口</th>
				<th field="ip6_port_start_wan"              width="110">源外网ipv6起始端口</th>
				<th field="ip6_port_end_wan"           width="110">源外网ipv6结束端口</th>
				<th field="ip4_target_wan"          width="100">目的公网ipv4地址</th>
				<th field="ip6_target_wan"            width="100">目的公网ipv6地址</th>
				<th field="ip4_target_port_wan"     width="100">目的公网ipv4端口</th>
				<th field="ip6_target_port_wan"                 width="100">目的公网ipv6端口</th>
				<th field="user_mac"                 width="130">终端mac</th>
				<th field="ap_code"                 width="160">设备编码</th>
				<th field="ap_mac"                 width="130">设备mac</th>
				<th field="ap_longitude"                 width="80">设备经度</th>
				<th field="ap_latitude"                 width="80">设备纬度</th>
			</tr>
		</thead>
	</table>
	</div>
	
<!-- 	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增日志</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑日志/a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除日志</a>
	</div> -->
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">上网日志信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>日志序号:</label>
				<input name="log_id" class="easyui-textbox" disabled="true" required="true">
			</div>
			<!-- <div class="fitem">
				<label>场所序号:</label>
				<input name="area_id" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>设备序号:</label>
				<input name="allap_id" class="easyui-textbox">
			</div> -->
			<div class="fitem">
				<label>会话id:</label>
				<input name="session_id" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>时间:</label>
				<input name="create_time" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所编码:</label>
				<input name="auth_area_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>协议类型:</label>
				<input name="protocol_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>内网ip:</label>
				<input name="ip_lan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>内网端口:</label>
				<input name="ip_port_lan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip4地址:</label>
				<input name="ip4_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>电话号码:</label>
				<input name="ip6_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip4起始端口:</label>
				<input name="ip4_port_start_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip4结束端口:</label>
				<input name="ip4_port_end_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip6起始端口:</label>
				<input name="ip6_port_start_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip6结束端口:</label>
				<input name="ip6_port_end_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>目的公网ip4地址:</label>
				<input name="ip4_target_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>目的公网ip6地址:</label>
				<input name="ip6_target_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>目的公网ip4端口:</label>
				<input name="ip4_target_port_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>目的公网ip6端口:</label>
				<input name="ip6_target_port_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端mac:</label>
				<input name="user_mac" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备编码:</label>
				<input name="ap_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备mac:</label>
				<input name="ap_mac" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备经度:</label>
				<input name="ap_longitude" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备纬度:</label>
				<input name="ap_latitude" class="easyui-textbox" >
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
			$('#dlg').dialog('open').dialog('setTitle','新日志');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑日志');
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
        	var v1 = $('#dd1').datebox('getValue'); 
        	if(value && value.length>0){
        		if( (v && v.length>0) && (v1 && v1.length>0) ){
		            $('#dg').datagrid({
		                url:'getdata?code='+value+'&date1='+v+'&date2='+v1
		                
		            });
        		}else{
        			$('#dg').datagrid({
		                url:'getdata?code='+value
		                
		            });
        		}
        		
        	}else{
        		if( (v && v.length>0) && (v1 && v1.length>0) ){
		            $('#dg').datagrid({
		                url:'getdata?date1='+v+'&date2='+v1
		                
		            });
        		}
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