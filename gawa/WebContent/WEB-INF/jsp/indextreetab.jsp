<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String user=(String)request.getSession().getAttribute("userctx");
String scroll_begin=(String)request.getSession().getAttribute("scroll_begin");
String userlevel=(String)request.getSession().getAttribute("userlevel");
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上网安全系统</title>
    <link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../html/js/jquery-easyui-1.4/demo/demo.css">
    <script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
     
	
	<style type="text/css">  
		body{  
			padding:0px;
			margin:0px;
		} 
    </style>
	<script type="text/javascript">  
	    $(document).ready(function(){  
	        var height1 = $(window).height()-10;  
	        $("#main_layout").attr("style","width:100%;height:"+height1+"px");  
	        $("#main_layout").layout("resize",{  
	            width:"100%",  
	            height:height1+"px"  
	        });  
	    });  
	    
	    
	    $(function ScrollImgLeft(){ 
	    	
	    	var speed=50; 
	    	var scroll_begin = document.getElementById("scroll_begin"); 
	    	var scroll_end = document.getElementById("scroll_end"); 
	    	var scroll_div = document.getElementById("scroll_div"); 
	    	scroll_end.innerHTML=scroll_begin.innerHTML; 
	    	function Marquee(){ 
	    		if(scroll_begin.offsetWidth-scroll_div.scrollLeft<=0) 
	    		scroll_div.scrollLeft-=scroll_begin.offsetWidth; 
	    		else 
	    		scroll_div.scrollLeft++; 
	    	} 
	    	var MyMar=setInterval(Marquee,speed); 
	    	scroll_div.onmouseover=function() {clearInterval(MyMar);} 
	    	scroll_div.onmouseout=function() {MyMar=setInterval(Marquee,speed);} 
	    	
	    });
	</script> 
    
 
</head>

<body>

	<style type="text/css"> 
	#gongao{width:700px;height:30px;overflow:hidden;line-height:30px;font-size:13px;font-family:'宋体';background:#95B8E7;color:#FF0000;font-weight:bold;} 
	#gongao #scroll_begin, #gongao #scroll_end{display:inline} 
	</style> 

	<div style="margin-top:5px;margin-left:5px;margin-right:5px;margin-bottom:5px;">  
	    <div id="main_layout" class="easyui-layout"  style="width:100%; height:680px;">  
	        <!-- 北方 -->  
	        <div id="north_title" data-options="region:'north'" style="height: 40px;background-color:#95B8E7;">  
	            <div style="width:20%; float:left;margin-left:0.5%;">  
	                <font size="3" style="line-height: 2.0em;" color="white" >  
	                    <b>千陌互连上网安全管理系统 V1.0</b>  
	                </font>  
	            </div>  
	              
	            <div id="gongao" style="width:60%; float:left;"> 
					<div style="width:600px;height:30px;margin:0 auto;white-space: nowrap;overflow:hidden;" id="scroll_div" class="scroll_div"> 
						<div id="scroll_begin"> 
						<%=scroll_begin%>
						</div> 
						<div id="scroll_end"></div> 
					</div> 
					<script type="text/javascript">ScrollImgLeft();</script> 
					</div>  
	              
	              
	            <div style="width:10%; float:right;margin-top:10px;margin-right:1px;">  
 	                <font size="3" color="white">欢迎 :<%=user%></font>       
	                <a href="logout"> <font size="3" color="red">退出</font> </a>  
	            </div>
	        </div>  
	          
	        <!-- 西方 -->  
	        <div data-options="region:'west',split:true" title="" style="width: 200px;">  
	            <div class="easyui-accordion"  data-options="fit:true,border:false">
	            
			            
<!-- 						        <ul class="easyui-tree">
						            <li>
						                <span >安全管理系统</span>
						                <ul> -->
<!-- 						                    <li data-options="state:'closed'"> -->
											<ul id="area" class="easyui-tree">
											<li>
						                        <span><div onclick="clearTreeSelected()">场所信息</div></span>
						                        <ul>
						                        	<li>
						                                <div><span onclick="testClick('area','场所基础信息管理')">场所基础信息管理</span><div>
						                            </li>
						                            <li>
						                                <div><span onclick="testClick('apfix','固定AP设备基础信息管理')">固定AP设备基础信息管理</span><div>
						                            </li>
						                            <li>
						                                <div><span onclick="testClick('ap','移动AP设备基础信息管理')">移动AP设备基础信息管理</span></div>
						                            </li>
						                            
						                        </ul>
						                    </li>
						                    </ul>
						                    
						                    <ul id="org" class="easyui-tree">
						                    <li><div class="tree-node tree-node-selected"  onclick="testClick('org','安全厂商管理')">安全厂商管理</div></li>
						                    </ul>
						                    
						                    <ul id="ap" class="easyui-tree">
						                    <li>
						                        <span><div onclick="clearTreeSelected()">厂商对所属设备的管理</div></span>
						                        <ul>
						                            <li><div  onclick="testClick('apstate','设备运行状态的查看')">设备运行状态的查看</div></li>
						                            <li><div  onclick="testClick('maintain','设备维修管理')">设备维修管理</div></li>
						                            <li><div  onclick="testClick('notice','通知管理')">通知管理</div></li>
						                        </ul>
						                    </li>
						                    </ul>
						                    
						                    <ul id="datalog" class="easyui-tree">
						                    <li>
						                        <span><div onclick="clearTreeSelected()">数据接收</div></span>
						                        <ul>
						                            <li><div  onclick="testClick('onoffline','终端上下线信息统计')">终端上下线信息统计</div></li>
						                            <li><div  onclick="testClick('netlog','上网日志信息')">上网日志信息</div></li>
						                        </ul>
						                    </li>
						                    </ul>
						                    
						                    <ul id="statistic" class="easyui-tree">
						                    <li>
						                        <span><div onclick="clearTreeSelected()">信息统计</div></span>
						                        <ul>
						                            <li><div  onclick="testClick('statistic','上网人员统计')">上网人员统计</div></li>
						                            <li><div  onclick="testClick('statisticarea','场所状态统计')">场所状态统计</div></li>
						                            <li><div  onclick="testClick('statisticap','设备状态统计')">设备状态统计</div></li>
						                            <li><div  onclick="testClick('statisticrealname','无线上网场所实名率统计')">无线上网场所实名率统计</div></li>
						                        </ul>
						                    </li>
						                    </ul>
						                    
						                    <c:if test="${userlevel == 1}">
						                    <ul id="operlog" class="easyui-tree">
						                    <li><div   onclick="testClick('operlog','操作日志')">操作日志</div></li>
						                    </ul>
						                    </c:if>
						                    
						        <!--         </ul>
						            </li>
						        </ul> -->
						        
						   
					    
	            
	            
	            </div> 
	            
	             
	        </div>  
	        <!-- 中间 -->  
	        <div data-options="region:'center',title:'',iconCls:'icon-ok'">  
	            <div id="tTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">  
	                <div title="安全厂商管理"  
	                    data-options="closable:true"  
	                    style="overflow: auto; padding: 20px;">
	                    <iframe scrolling="auto" frameborder="0"  src="org" style="width:100%;height:98%;"></iframe>  
	                </div>  
	            </div>  
	        </div>  
	        <!-- 南方 -->  
	        <div data-options="region:'south',split:true" style="height:28px;background-color:#95B8E7;">  
	            <div style="margin-top:3px;" align="center">  
	                <font size="" color="white">Copyright &copy; 2016 千陌互连  All Rights Reserved 版权所有 </font>  
	            </div>  
	        </div>  
	    </div>  
	</div> 

	<script type="text/javascript">
	
		function clearTreeSelected(){
			$('#area').find('.tree-node-selected').removeClass('tree-node-selected');
			$('#org').find('.tree-node-selected').removeClass('tree-node-selected');
			$('#ap').find('.tree-node-selected').removeClass('tree-node-selected');
			$('#datalog').find('.tree-node-selected').removeClass('tree-node-selected');
			$('#statistic').find('.tree-node-selected').removeClass('tree-node-selected');
			$('#operlog').find('.tree-node-selected').removeClass('tree-node-selected');
		}
	
	
		function testClick(type,title){
			//alert("clicked ok!!!!!");
			//var title="上下线信息";
			
			clearTreeSelected();
			if($('#tTabs').tabs('exists', title)){    
			     $('#tTabs').tabs('select', title);
			}else{    
		        var content = '<iframe scrolling="auto" frameborder="0"  src='+type+' style="width:100%;height:98%;"></iframe>';
		        
		        $('#tTabs').tabs('add',{    
		            title:title,    
		            content:content,    
		            closable:true    
		        });    
	    	}
		}
	</script>
	


</body>

</html>