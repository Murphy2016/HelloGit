<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%request.setCharacterEncoding("UTF-8");%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String scroll_begin=(String)request.getSession().getAttribute("scroll_begin");
String userlevel=(String)request.getSession().getAttribute("userlevel");
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
		//parent.location.reload();
		var str = '<%=(String)request.getSession().getAttribute("scroll_begin") %>';
		var begin = window.parent.document.getElementById("scroll_begin").innerHTML=str+str;
		var end = window.parent.document.getElementById("scroll_end").innerHTML=str+str;
		//begin.html()
		//title.load(function(){});
	</script>

</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
	<div id="scroll_begin" style="display:none"><%=scroll_begin%></div>
	
	<!-- <div style="margin-bottom:20px;width:200px">
            <label class="label-top">Start Date:</label>
            <input id="datebox" class="easyui-datebox" style="width:100%;height:26px">
    </div> -->
<!--     <input class="easyui-searchbox" data-options="prompt:'上网服务场所编码',searcher:doSearch" style="width:300px"></input> -->
    <input id="search"  placeholder="上网服务场所编码/名称/地址" style="width:250px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    
<%--     <a id="btn" href="<%=basePath %>zip/145-353030334-330300-330300-areainfo-00001.zip" class="easyui-linkbutton" >zip下载</a> --%>
    <div style="margin-top:10px;">
	<table id="dg" title="场所信息" class="easyui-datagrid" data-options="nowrap:false"
			style="width:1400px;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="area_id"               width="50">序号</th>
				<th field="area_code"             width="120">场所编码</th>
				<th field="area_name"             width="100">场所名称</th>
				<th field="area_address"          width="100">场所地址</th>
				<th field="area_longitude"        width="50">场所经度</th>
				<th field="area_latitude"         width="50">场所纬度</th>
				<th field="area_sevice_type"      width="50">服务类型</th>
				<th field="business_nature"       width="50">经营性质</th>
				<th field="law_owner_name"        width="50">法人名字</th>
				<th field="owner_id_type"         width="50">证件类型</th>
				<th field="owner_id_no"           width="50">证件号码</th>
				<th field="phone_no"              width="50">电话号码</th>
				<th field="start_time"            width="70">营业开始时间</th>
				<th field="end_time"              width="70">营业结束时间</th>
				<th field="access_type"           width="70">网络接入方式</th>
				<th field="net_provider"          width="70">网络服务商</th>
				<th field="account_ip"            width="80">上网账号或IP</th>
				<th field="organization_code"     width="70">厂商机构代码</th>
 				<th field="display_state"         width="50">经营状态</th>
				              
				
			</tr>
		</thead>
	</table>
	</div>
	
	<c:if test="${userlevel == 1}">
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增场所</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑场所</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser1()">设为营业</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除场所</a>
	</div>
	</c:if>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">场所信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>场所编码:</label>
				<input name="area_code" class="gf-input-textbox" disabled="true" required="true">
			</div>
			<div class="fitem">
				<label>场所名称:</label>
				<input name="area_name" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>场所地址:</label>
				<input name="area_address" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>场所经度:</label>
				<input name="area_longitude" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>场所纬度:</label>
				<input name="area_latitude" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>服务类型:</label>
				<input name="area_sevice_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>经营性质:</label>
				<input name="business_nature" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>法人名字:</label>
				<input name="law_owner_name" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>证件类型:</label>
				<input name="owner_id_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>证件号码:</label>
				<input name="owner_id_no" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>电话号码:</label>
				<input name="phone_no" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>营业开始时间:</label>
				<input name="start_time" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>营业结束时间:</label>
				<input name="end_time" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>网络接入方式:</label>
				<input name="access_type" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>网络服务商:</label>
				<input name="net_provider" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>上网账号:</label>
				<input name="account_ip" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>厂商代码:</label>
				<input name="organization_code" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>经营状态:</label>
<!-- 				<input name="state" class="easyui-textbox" > -->
				<select  name="state" style="border-radius: 5px">
                <option value="1" selected>营业</option>
                <option value="2">停业</option>
                </select>
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
			$('#dlg').dialog('open').dialog('setTitle','新场所');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑场所');
				$('#fm').form('load',row);
				url = 'update?id='+row.area_id;
			}else{
				alert("请选中一行！");
			}
		}
		function editUser1(){
			var row = $('#dg').datagrid('getSelected');
			//row.state=1;
			if(row){
				url = 'updatestate?id='+row.area_id+'&state='+1;
				$.post(url,{area_id:row.area_id},function(result){
					
					if (result.success){
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
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
				$.messager.confirm('确认','确定删除这条场所信息？',function(r){
					
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
            //var value1 = $("#datebox").datebox("getValue");
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