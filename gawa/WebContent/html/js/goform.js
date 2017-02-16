
function withoutSearchCondition() {
	$('#SearchConditionPanel').panel('minimize');
	$('#SearchDatagridPanel').panel('maximize');
}

function showSearchConditionPanel() {
	$('#DataGridToolbarDiv').show();
// 	$('#SearchConditionPanel').show();
}

//表格查询
function submitSearchConditionForm(){
	var params = $('#ResultDataGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#SearchConditionForm').serializeArray(); //自动序列化表单元素为JSON对象
	
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	
	$('#ResultDataGrid').datagrid('load'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearSearchConditionForm(){
	$('#SearchConditionForm').form('clear');
	submitSearchConditionForm();
}

//新增
function addrow(onLoadFunc){
	if (!onLoadFunc) {
		onLoadFunc = function() {
//				$('#DataInfoEidtForm').form('clear');
		};
	}
	showWin(
			popupWinTitle,
			popupWinUrl,
			popupWinWidth,
			popupWinHeight,
			onLoadFunc);
}

//更新
function updateRow(data, onLoadFunc){
	if(data == null) {
		var rows = $('#ResultDataGrid').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
		if(rows.length==0){
			$.messager.alert('提示',"请选择要更新的数据。",'error');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条数据进行更新。",'error');
			return;
		}
		if (onLoadFunc) {
			onLoadFunc = function() {
				$("#DataInfoEidtForm").form('load', rows[0]);
			};
		}
		showWin(
				popupWinTitle,
				popupWinUrl,
				popupWinWidth,
				popupWinHeight,
				onLoadFunc);
	} else {
		if (!onLoadFunc) {
			onLoadFunc = function() {
				$("#DataInfoEidtForm").form('load', data);
			};
		}
		showWin(
				popupWinTitle,
				popupWinUrl,
				popupWinWidth,
				popupWinHeight,
				onLoadFunc);
	}
}

//删除
function deleteRow(type){
	var rows = $('#ResultDataGrid').datagrid('getSelections');
	//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
	if(rows.length==0){
		$.messager.alert('提示',"请选择要删除的数据。",'error');
		return;
	}
	$.messager.confirm('提示','确定要删除选择的数据吗?',function(result){
		if (result){
	      	var rows = $('#ResultDataGrid').datagrid('getSelections');
	      	var ps = editDeletIDs(rows);
	      	ajaxServerGET(datagridDeleteUrl + ps, function(data) {
	  			$("body").unmask();
	  			if (data && !data.bussinErr) {
	      			//var rows = $('#ResultDataGrid').datagrid('getSelections');
	              	//var ps = "";
	              	//$.each(rows,function(i,n){
	              	//	var index = $('#ResultDataGrid').datagrid('getRowIndex', n);
	      			//	$('#ResultDataGrid').datagrid('deleteRow', index);
	              	//});
					$.messager.alert('提示',"成功删除的数据。",'info');
	              	if (!type || type == 'datagrid')
	          			$('#ResultDataGrid').datagrid('reload');
	              	else if (type == 'treegrid') {
	          			$('#ResultDataGrid').treegrid('reload'); 
//						$('#ResultDataGrid').treegrid('update', {id: rows[0].id, row: rows[0]});
//						$('#ResultDataGrid').treegrid('refresh', rows[0]);
	              	}
	      		} else {
					showGoformSystemErrorAlert(data.errmsg);
	      		}
		    });
		}
	});
}


function ajaxServerGET(url,okCb,errCb,isNoMask) {
	ajaxServer(url,'get',null,okCb,errCb,isNoMask);
}

function ajaxServerPOST(url,data,okCb,errCb,isNoMask) {
	ajaxServer(url,'post',data,okCb,errCb,isNoMask);
}

function ajaxServer(url,method,data,okCb,errCb,isNoMask) {
  	$.ajax({
		type: method,
		url : url,
		dataType:'html',
		beforeSend:function(){
			if (!isNoMask)
				$("body").mask("正在处理中...");
  		},
	    success: okCb,
	    error: errCb ? errCb : function(XMLHttpRequest, textStatus, errorThrown) {
			showGoformSystemErrorAlert(XMLHttpRequest.responseText);
  			$("body").unmask();
        }
	});
}

function showWin(title, url, width, height, onLoad) {
	$("#PopupWindow").window({
		title:title,
		href:url,
		shadow:false,
		maximizable: false,
		width:width,
		height:height,
		onLoad: onLoad
//		,onBeforeClose: function(){
//			$.messager.confirm('提示','窗口正在关闭，请确认您当前的操作已保存。\n 是否继续关闭窗口？',function(result){
////				alert(result);
//				if (!result){
//					return;
//				}
//				$('#PopupWindow').window('close', true);
//			});
////			alert(111111111111);
//			return false;
//		}
	});
}

function showGoformSystemErrorAlert(exception) {
	if (!exception)
		exception="";
	$.messager.alert('错误提示', '服务器端发生系统错误，请联系系统管理员。<br>'+exception, 'error');
}
function mouseOver(obj){
    if(obj.className="css1")
       obj.className="css2";
 }
 function mouseOut(obj){
    if(obj.className="css2")
       obj.className="css1";
 }
 function onClick(obj)
 {
	 obj.addClass("selected").siblings("tr").removeClass("selected");
 }
