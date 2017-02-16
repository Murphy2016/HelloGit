<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>   
<%
String user=(String)request.getSession().getAttribute("userctx");

%>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../html/bootstrap/favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../html/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../html/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../html/bootstrap/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../html/bootstrap/assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../html/js/jquery-1.11.1.js"></script>
    <script src="../html/bootstrap/js/moment-with-locales.min.js"></script>
    <script src="../html/bootstrap/js/bootstrap-datetimepicker.min.js"></script> 
    <script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
    
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">后台监控管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           
            <li><a href="#"><%=user%></a></li>
            <li><a href="#">退出</a></li>
          </ul>
          <!-- <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="../monitor/index1">设备监控</a></li>
            <li><a href="../group/index">分组管理</a></li>
            <li class="active"><a href="#">任务单管理</a></li>
            <li><a href="../task/index">任务管理</a></li>
          </ul>
          <!-- <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul> -->
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          
		  
		<h3 class="page-header">任务单管理</h3> 
		 <div class="well well-sm">
         <div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;">任务单编号：</div>
				<input id="taskextid" style="display:inline;" placeholder="" value="${taskextid }"></input>
				<div style="display:inline;margin-left:10px;">任务单名称：</div>
				<input id="taskextname" style="display:inline;" value="${taskextname }"></input>
				<div style="display:inline;margin-left:10px;">发布对象：</div>				
				<select id="taskextdest">
					<option <c:if test="${taskextdest=='设备小组1'}"> selected = "selected"</c:if> >设备小组1</option>
					<option <c:if test="${taskextdest=='设备小组2'}"> selected = "selected"</c:if> >设备小组2</option>
					<option <c:if test="${taskextdest=='设备小组3'}"> selected = "selected"</c:if> >设备小组3</option>					
				</select>
				<div style="display:inline;margin-left:10px;">执行状态：</div>
				<select id="taskextstate">
					<option <c:if test="${taskextstate=='已完成'}"> selected = "selected"</c:if> >已完成</option>
					<option <c:if test="${taskextstate=='已停止'}"> selected = "selected"</c:if> >已停止</option>
					<option <c:if test="${taskextstate=='进行中'}"> selected = "selected"</c:if> >进行中</option>					
				</select>
				<br/><br/>
				<div style="display:inline;">起&nbsp;始&nbsp;时&nbsp;间：</div>
				<%-- <input id="starttime" style="display:inline;" placeholder="yyyy-MM-dd" value="${starttime }"></input> --%>
				<input id="starttime" type="text" value="${starttime }">
				
				
				<div style="display:inline;margin-left:10px;">结&nbsp;束&nbsp;时&nbsp;&nbsp;间：</div>
				<%-- <input id="endtime" style="display:inline;" placeholder="yyyy-MM-dd" value="${endtime }"></input> --%>
				<input id="endtime" type="text" value="${endtime }">
 				
				
				<button style="margin-left:100px;margin-top:5px;" onclick="search()">搜索</button>
				<button style="margin-left:50px;margin-top:5px;" onclick="create()">创建任务单</button>
				
			</div> 
			</div><h2 class="sub-header">任务单列表</h2>
			  <button >批量终止</button>
	          <div class="table-responsive">
	            <table class="table table-striped">
	              <thead>
	                <tr>
	                  <th><input type="checkbox"  onclick="Checkall('checkbox1')"/></th>
	                  <th>任务单编号</th>
	                  <th>任务单名称</th>
	                  <th>发布对象</th>
	                  <th>执行状态</th>
	                  
	                  <th>任务期限</th>
	                  <th>任务单内容</th>
	                  <th>说明</th>
	                  <th>操作</th>
	                  
	                </tr>
	              </thead>
	              <tbody>
	              	<c:forEach items="${list}" var="item" >
	                <tr>
	                  <td><input type="checkbox" name="checkbox1"/></td>
	                  <td><a href="#" onclick="window.open('detail?taskextid=${item.taskextid }&taskextname=${item.taskextname }')">
                  		${item.taskextid }</a></td>
					  <td>${item.taskextname }</td>
					  <td>${item.taskextdest }</td>
					  <td>${item.taskextstate }</td>
					  <td>${item.taskextperiod }</td>
					  <td>${item.taskextcontent }</td>
					  <td>${item.taskextcomment }</td>
					  <td><button  onclick="edit('${item.taskextid }','${item.taskextname }','${item.taskextdest }','${item.taskextstate }','${item.taskextperiod }','${item.taskextcontent }','${item.taskextcomment }')">编辑</button></td>
					  
	                </tr>
	               </c:forEach> 
	              </tbody>
	            </table>
	            <%@ include file="../Page.jsp" %>
	          </div>
				
			
			
			
          
        </div>
      </div>
    </div>
	<script type="text/javascript">
	    function Checkall(XelementName){
	        var checkboxes=document.getElementsByName(XelementName);
	        for(var i=0;i<checkboxes.length;i++)
	        {
				checkboxes[i].checked=!checkboxes[i].checked;
	        }
	    }
	    
	    function search(){
	    	var taskextid = $("#taskextid").val();
	    	var taskextname = $("#taskextname").val();
	    	var taskextdest = $("#taskextdest").val();
	    	var taskextstate = $("#taskextstate").val();
	    	
	    	var starttime = $("#starttime").val();
	    	var endtime = $("#endtime").val();
	    	
	    	window.location.href='index?taskextid='+taskextid+'&taskextname='+taskextname
			+'&taskextdest='+taskextdest+'&taskextstate='+taskextstate+'&starttime='+starttime+
			'&endtime='+endtime;
	    }
		function create(){
	    	
	    	window.location.href='create';
	    }
		function edit(taskextid,taskextname,taskextdest,taskextstate,taskextperiod,taskextcontent,taskextcomment){
	    	
	    	window.location.href='edit?taskextid='+taskextid+'&taskextname='+taskextname
			+'&taskextdest='+taskextdest+'&taskextstate='+taskextstate+
			'&taskextperiod='+taskextperiod+
			'&taskextcontent='+taskextcontent+'&taskextcomment='+taskextcomment;
			
	    }
		
		$('#starttime').datetimepicker({
			format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')
		});
		$('#endtime').datetimepicker({
			format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')
		});
    </script>
	<script src="../html/js/startenddate.js"></script>
</body>
</html>