<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
	<link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/jquery.edatagrid.js">
	<script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../html/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">
		var first = true;
		/*function myrefresh()
		{
		       window.location.reload();
		}
		var timer = setTimeout('myrefresh()',1000); //指定1秒刷新一次*/
		//parent.location.reload();
		var str = '<%=(String)request.getSession().getAttribute("scroll_begin") %>';
		var begin = window.parent.document.getElementById("scroll_begin").innerHTML=str+str;
		var end = window.parent.document.getElementById("scroll_end").innerHTML=str+str;
		//begin.html()
		//title.load(function(){});
		/*if(first){
			window.location.reload();
			first = false;
		}*/
		//alert("succeed!");
		/*$('#dg').datagrid({  
			onLoadSuccess:function(data){  
		        alert("succeed!");
		}  
		}); */
		
		$.extend($.fn.datagrid.methods, {    
			loaded : function(jq) {    
		        //alert("extend loaded!");
		        $.ajax({ 
		        	 type: "post",
		        
		        	url:"getmsg",
		        	dataType: "json",
		        	
		          data:{
				    name:"Donald Duck",
				    city:"Duckburg"
				  },
				  success: function(data){
				    //alert("Data: " + data);
				    var str = data.msg+data.msg+data.msg;
				    //alert(str);
				    var begin = window.parent.document.getElementById("scroll_begin").innerHTML=str+str;
					var end = window.parent.document.getElementById("scroll_end").innerHTML=str+str;
				  }
		        });
		        

		        return jq.each(function () {  
		            $(this).datagrid("getPager").pagination("loaded");  
		            var pnl = $(this).datagrid("getPanel");  
		            pnl.children("div.datagrid-mask-msg").remove();  
		            pnl.children("div.datagrid-mask").remove();  
		        });  
		    }
		    
		    
		      
		}); 

	</script>

</head>
<body>
<!-- 	<h2>您好，欢迎访问</h2> -->
<!-- 	<p>点击按钮做相应操作</p> -->
	
	<div style="margin:20px 0;"></div>
<!--     <input class="easyui-searchbox" data-options="prompt:'设备编号',menu:'#mm',searcher:doSearch" style="width:300px"></input> -->
    
    <select id="mmm"  name="">   
	  <option   value="1" selected="true" >服务在线</option>   
	  <option   value="0">服务离线</option>
	       
    </select>
    <input id="search"  placeholder="设备编号/mac/场所编码" style="width:200px;display:inline"></input>
    <a  class="easyui-linkbutton" onclick="doSearch()">查询</a>
    
    <!-- <div id="mm">
        <div data-options="name:'1'">服务在线</div>
        <div data-options="name:'2'">服务离线</div>
        <div data-options="name:'3'">设备维护</div>
    </div> -->
    <div style="margin-top:10px;">
	<table id="dg" title="设备信息" class="easyui-datagrid" style="width:100%;"
			url="getdata"  data-options="onLoadSuccess:refreshMsg()" 
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="allap_id"               width="50">序号</th>
				
				<th field="equipment_code"             width="70">设备编号</th>
				<th field="mac"          width="50">设备mac</th>
				<th field="area_code"        width="50">场所编码</th>
				
 				<th field="display_state"    data-options="styler:cellStyler"         width="50">设备服务状态</th>
 				<th field="display_data_state"    data-options="styler:cellStyler"         width="50">设备数据状态</th>
 				<th field="display_working_state"    data-options="styler:cellStyler"         width="50">设备工作状态</th>

			</tr>
		</thead>
	</table>
	</div>
	
	<c:if test="${userlevel == 1}">
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑设备</a>
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
			<div class="fitem">
				<label>设备编码:</label>
				<input name="equipment_code" class="easyui-textbox" disabled="true">
			</div>
			<div class="fitem">
				<label>设备mac:</label>
				<input name="mac" class="easyui-textbox" disabled="true">
			</div>
			<div class="fitem">
				<label>场所编码:</label>
				<input name="area_code" class="easyui-textbox" disabled="true">				
			</div>

			<div class="fitem">
				<label>服务状态:</label>
				<select  name="state" style="border-radius: 5px">
                <option value="0" selected>服务离线</option>
                <option value="1">服务在线</option>
                </select>
			</div>
			<div class="fitem">
				<label>数据状态:</label>
				<select  name="data_state" style="border-radius: 5px">
                <option value="0" selected>数据离线</option>
                <option value="1">数据在线</option>
                </select>
			</div>
			
			<div class="fitem">
				<label>工作状态:</label>
				<select  name="working_state" style="border-radius: 5px">
                <option value="1" selected>正常工作</option>
                <option value="2" selected>离线状态</option>
                <option value="3">异常状态</option>
                <option value="4">维修状态</option>
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
		
		function refreshMsg(){
			alert("ok");
			var str = '<%=(String)request.getSession().getAttribute("scroll_begin") %>';
			var begin = window.parent.document.getElementById("scroll_begin").innerHTML=str+str;
			var end = window.parent.document.getElementById("scroll_end").innerHTML=str+str;

		}
		
		function editUser(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','编辑设备');
				$('#fm').form('load',row);
				url = 'update01?id='+row.allap_id;
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
		
	</script>
	
	<script type="text/javascript">
		function cellStyler(value,row,index){
			if(value == "服务在线" || value=="数据在线"){
				return 'color:green;';
			}else{
				return 'color:red;';
			}
		}
	</script>
	
	<script>
        function doSearch(){
            //alert('You input: ' + value);
            //var tbl=document.getElementById("dg"); 
            //tbl.setAttribute("url", "test?id=2");
            //$('#dg').datagrid('reload');
            var AdultObj = document.getElementById("mmm");
			var name = AdultObj.options[AdultObj.selectedIndex].value;
            //alert(name);
            var value = document.getElementById("search").value;
            if(value && value.length>0){
	            $('#dg').datagrid({
	                url:'getdata?code='+value+'&state='+name
	                
	            });
            }else{
            	$('#dg').datagrid({
	                url:'getdata?state='+name
	                
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