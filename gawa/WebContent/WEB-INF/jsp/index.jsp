<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上网安全系统</title>
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
     
<!--     <script src="http://code.highcharts.com/highcharts.js"></script> -->
    
    <style type="text/css">  
		body{  
			padding:0px;
			margin:0px;
		} 
		.container {
			height:100%;
			/*width: 100%;*/
			border: 1px solid green;
			position:fixed;
		}
		
	</style>
    
    
    <script type="text/javascript">
    $(function(){
    $('#mm1').menu({  
    	 onClick: function (item) {  
	    	 if(item.id=="testpage")  
	    	 {  
				//alert("Copy button clicked!"); 
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"subindex\" frameborder=\"0\" style=\"width:750px;height:1000px\"></iframe>"; //JS  
				//document.getElementById("iframe").src="sohu.com";
	    	 }
	    	 if(item.id=="areainfo")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"area\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="ap1")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"apfix\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="ap2")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"ap\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 
	    	 
	    	 
	    	 
    	 }  
    });
    $('#mm2').menu({  
   	 onClick: function (item) {  
	    	 
	    	 if(item.id=="orgman")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"org\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 
   	 }  
     });
    $('#mm3').menu({  
      	 onClick: function (item) {  
   	    	 
      		if(item.id=="devstate")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"apstate\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="maintain")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"maintain\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="notice")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"notice\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
   	    	 
      	 }  
        });
    $('#mm4').menu({  
      	 onClick: function (item) {  
   	    	 
      		if(item.id=="onoff")  
	    	 {  
      			
      			var height0 = $(window).height();
      	    	var height1 =  document.getElementById("box1").offsetHeight;
      	    	var height2 =  document.getElementById("content").offsetHeight;
      	    	var height = height0-height1-10;
      	    	//alert(height0+"and"+height1+"and"+height2);
      	    	
      			
      			
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"onoffline\" frameborder=\"0\" style=\"width:100%;height:100%\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="netlog")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"netlog\" frameborder=\"0\" style=\"width:1450px;height:650px\"></iframe>"; //JS  
	    	 }
   	    	 
      	 }  
        });
    $('#mm5').menu({  
      	 onClick: function (item) {  
   	    	 
      		if(item.id=="usersta")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"statistic\" frameborder=\"0\" style=\"width:1500px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="areasta")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"statisticarea\" frameborder=\"0\" style=\"width:1500px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="apsta")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"statisticap\" frameborder=\"0\" style=\"width:1500px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 if(item.id=="authsta")  
	    	 {  
				var label=document.getElementById("content");   
				label.innerHTML="<iframe id=\"iframe\"  src = \"statisticrealname\" frameborder=\"0\" style=\"width:1500px;height:650px\"></iframe>"; //JS  
	    	 }
	    	 
	    	 if(item.id=="logout")  
	    	 {  
	    		var form = document.getElementById("abc");     
	    	 	form.submit();
	    	 	
	    	 }
   	    	 
      	 }  
        });
    
    });
    
    $(function(){
    	var height0 = $(window).height();
    	var height1 =  document.getElementById("box1").offsetHeight;
    	var height2 =  document.getElementById("content").offsetHeight;
    	var height = height0-height1-40;
    	//alert(height0+"and"+height1+"and"+height2);
    	$("#content").attr("style","height:"+height+"px");
    	//alert(height0+"and"+height1+"and"+height2);
    });
    
    </script>
</head>

<body>
<div id="box1" style="width:100%;height:100%">
    <h2>上网安全系统</h2>
    <div style="margin:40px 0;"></div>
    <div class="easyui-panel" style="padding:5px;width:450px;">
    	<a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-tip'">场所</a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-man'">厂商</a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm3',iconCls:'icon-lock'">设备</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm4',iconCls:'icon-filter'">数据</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm5',iconCls:'icon-sum'">统计</a>        
    </div>
    
    <div id="mm1" style="width:150px;">
<!--         <div id="testpage">测试页</div> -->
        <div id="areainfo">场所信息</div>
        <div id="ap1">固定AP</div>
        <div id="ap2">移动AP</div>
<!--         <div class="menu-sep"></div> -->
        
        
    </div>
    
   <div id="mm2" style="width:100px;">
    	<div id="orgman">厂商管理</div>
        
    </div>
    <div id="mm3" style="width:100px;">
    	<div id="devstate">设备状态</div>
    	<div id="maintain">维修管理</div>
    	<div id="notice">通知管理</div>
        
    </div>
	<div id="mm4" style="width:100px;">
    	<div id="onoff">上下线信息</div>
    	<div id="netlog">上网日志信息</div>
        
    </div>
    <div id="mm5" style="width:100px;">
    	<div id="usersta">上网人员统计</div>
    	<div id="areasta">场所状态统计</div>
    	<div id="apsta">设备状态统计</div>
    	<div id="authsta">上网实名率统计</div>
        <div id="logout">退出</div>
    </div>
    
    <form id="abc"  action="logout" method="post" style="display:none"></form>
</div>
    
    <div id="content" style="width:100%;height:100%">
    	
    	

    </div>

    
</body>

</html>