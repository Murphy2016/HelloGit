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
</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
<!--     <input class="easyui-searchbox" data-options="prompt:'Please Input Value',searcher:doSearch" style="width:300px"></input> -->
    <div style="margin-top:10px;">
    <label class="label-top" style="display:inline">日志保存时限:</label>
    <select id="mmm"  name="">   
	  <option   value="7"   <c:if test="${period==7}">selected="true"</c:if> 
	  >7天</option> 
	  <option   value="14"   <c:if test="${period==14}">selected="true"</c:if> 
	  >14天</option>  
	  <option   value="30"   <c:if test="${period==30}">selected="true"</c:if> 
	  >30天</option>
	  <option   value="60"   <c:if test="${period==60}">selected="true"</c:if> 
	  >60天</option>
	  <option   value="120"   <c:if test="${period==120}">selected="true"</c:if> 
	  >120天</option>
	       
    </select>
    <a  class="easyui-linkbutton" onclick="doSearch()">修改</a>
	<table id="dg" title="操作日志信息" class="easyui-datagrid" data-options="nowrap:false" 
			style="width:100%;"
			url="getdata"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="oper_id"               width="50">序号</th>
				<th field="username"             width="50">用户名</th>
				<th field="operation"             width="50">操作内容</th>
				<th field="add_time"          width="50">操作时间</th>

			</tr>
		</thead>
	</table>
	</div>
	
	<c:if test="${userlevel == 1}">
	<!-- <div id="toolbar">
		
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑通知</a>
		
	</div>-->
	</c:if>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">通知信息</div>
		<form id="fm" method="post" novalidate accept-charset="utf-8">
			<div class="fitem">
				<label>序号:</label>
				<input name="notice_id" class="easyui-textbox"  required="true">
			</div>
			<div class="fitem">
				<label>通知内容:</label>
				<input name="content" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>通知日期:</label>
				<input name="date1" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>回复内容:</label>
				<input name="reply" class="easyui-textbox" >
			</div>
			<div class="fitem">
				<label>回复日期:</label>
				<input name="reply_date" class="easyui-textbox" >
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
			$('#dlg').dialog('open').dialog('setTitle','新通知');
			$('#fm').form('clear');
			url = 'insert';
		}
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑通知');
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
            var AdultObj = document.getElementById("mmm");
			var name = AdultObj.options[AdultObj.selectedIndex].value;
			$.ajax({ 
	        	 type: "post",
	        
	        	url:"updatesetting",
	        	dataType: "json",
	        	
	          data:{
			    period:name
			    
			  },
			  success: function(data){
			    alert("修改成功！");
			   
			  }
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