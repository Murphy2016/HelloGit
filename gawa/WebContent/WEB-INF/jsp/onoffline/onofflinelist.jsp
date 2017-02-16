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
	<div style="margin-bottom:20px;display:inline;width:100%;height:100%">
            <label class="label-top" style="display:inline">开始日期:</label>
            <input id="dd" class="easyui-datebox" style="width:100px;height:26px;display:inline">
<!--     </div> -->
<!--     <div style="margin-bottom:20px"> -->
            <label class="label-top" style="display:inline">结束日期:</label>
            <input id="dd1" class="easyui-datebox" style="width:100px;height:26px;display:inline">
    </div>
    <input id="search"  placeholder="认证账号" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    
<%--     <a id="btn" href="<%=basePath %>zip/145-353030334-330300-330300-onoffline-00001.zip" class="easyui-linkbutton" >zip下载</a> --%>
    <div style="margin-top:10px;">
	<table id="dg" title="上下线信息" class="easyui-datagrid" style="width:5000px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="onoff_id"               width="60">序号</th>
				<th field="auth_time_display"              width="140">上线时间</th>
				<!-- <th field="area_id"             width="50">场所序号</th>
				<th field="allap_id"             width="50">设备序号</th> -->
				<th field="auth_type_display"          width="70">认证类型</th>
				<th field="auth_name"        width="110">认证账号</th>
				<th field="auth_area_code"         width="130">场所编号</th>
				<th field="auth_area_type_display"      width="60">场所类型</th>
				<th field="identification_type_display"       width="60">证件类型</th>
				<th field="identification"        width="110">证件号码</th>
				<th field="app_company_name"         width="70">app厂商名</th>
				<th field="app_software_name"           width="70">app软件名</th>
				<th field="app_version"              width="70">app版本</th>
				<th field="app_id"            width="70">app认证码</th>
				
				<th field="de_auth_time_display"           width="140">下线时间</th>
				<th field="ip_lan"          width="90">内网ip</th>
				<th field="ip4_wan"            width="90">源外网ip4地址</th>
				<th field="ip6_wan"     width="90">源外网ip6地址</th>
				<th field="ip4_port_start_wan"                 width="110">源外网ip4起始端口</th>
				<th field="ip4_port_end_wan"               width="110">源外网ip4结束端口</th>
				<th field="ip6_port_start_wan"             width="110">源外网ip6起始端口</th>
				<th field="ip6_port_end_wan"             width="110">源外网ip6结束端口</th>
				<th field="user_mac"          width="130">终端mac</th>
				<th field="ap_code"        width="160">设备编码 </th>
				<th field=ap_mac         width="130">设备mac</th>
				<th field="ap_longitude"      width="80">设备经度</th>
				<th field="ap_latitude"       width="80">设备纬度</th>
				<th field="session_id"        width="250">会话id</th>
				<th field="ap_signal_strength"         width="70">设备信号强度</th>
				<th field="location_x"           width="90">终端相对设备x坐标</th>
				<th field="location_y"              width="90">终端相对 设备y坐标</th>
				<th field="name1"            width="60">上网人姓名</th>
				<th field="imsi"              width="60">终端imsi</th>
				<th field="imei"           width="60">终端imei</th>
				<th field="os_name"          width="60">终端系统</th>
				<th field="brand"            width="60">终端品牌</th>
				<th field="model"     width="60">终端型号</th>
							
			</tr>
		</thead>
	</table>
	</div>
<!-- 	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增上下线</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑上下线</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除上下线</a>
	</div> -->
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">场所信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>序号:</label>
				<input name="onoff_id" class="easyui-textbox" disabled="true" required="true">
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
				<label>场所经度:</label>
				<input name="auth_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>认证账号:</label>
				<input name="auth_name" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所编码:</label>
				<input name="auth_area_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所类型:</label>
				<input name="auth_area_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>证件类型:</label>
				<input name="identification_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>证件号码:</label>
				<input name="identification" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>app厂商名:</label>
				<input name="app_company_name" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>app软件名:</label>
				<input name="app_software_name" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>app版本:</label>
				<input name="app_version" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>app认证码:</label>
				<input name="app_id" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>上线时间:</label>
				<input name="auth_time" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>下线时间:</label>
				<input name="de_auth_time" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>内网ip:</label>
				<input name="ip_lan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ipv4地址:</label>
				<input name="ip4_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip6地址:</label>
				<input name="ip6_wan" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>源外网ip4起始端口:</label>
				<input name="ip4_port_start_wan" class="easyui-textbox" disabled="true" required="true">
			</div>
			<div class="fitem">
				<label>源外网ip4结束端口:</label>
				<input name="ip4_port_end_wan" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>源外网ip6起始端口:</label>
				<input name="ip6_port_start_wan" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>源外网ip6结束端口:</label>
				<input name="ip6_port_end_wan" class="easyui-textbox" >
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
			<div class="fitem">
				<label>会话id:</label>
				<input name="session_id" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>设备信号强度:</label>
				<input name="ap_signal_strength" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端相对设备x坐标:</label>
				<input name="location_x" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端相对设备y坐标:</label>
				<input name="location_y" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>上网人姓名:</label>
				<input name="name1" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端imsi:</label>
				<input name="imsi" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端imei:</label>
				<input name="imei" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端系统:</label>
				<input name="os_name" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>终端品牌:</label>
				<input name="brand" class="easyui-textbox" >
			</div>	
			<div class="fitem">
				<label>终端型号:</label>
				<input name="model" class="easyui-textbox" >
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
			$('#dlg').dialog('open').dialog('setTitle','新信息');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑信息');
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