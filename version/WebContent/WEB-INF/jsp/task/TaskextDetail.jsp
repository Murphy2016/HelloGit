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
            <li class="active"><a href="index">任务单管理</a></li>
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
          
		  
		<h3 class="page-header">任务单详情</h3> 
		 <div class="well well-sm">
         <div  style="margin-left:37px;margin-top:5px;">
				<div>
					<label>编号：</label>
					<span id="number">${number }</span>
				</div>
				<div>
					<label>名称：</label>
					<span id="name">${name }</span>
				</div>
				<div>
					<label>执行结果：</label>
					<span >${result }</span>
				</div>
				<div>
					<label>任务说明：</label>
					<span>${comment }</span>
				</div>
				<div>
					<label>任务期限：</label>
					<span>${period }</span>
				</div>
			
			</div> 
			</div><h2 class="sub-header">任务单进度</h2>
			
				<span for="name">小组名称</span>
				<select id="groupname">
					<option  <c:if test="${groupname=='小组1'}"> selected = "selected"</c:if> >小组1</option>
					<option  <c:if test="${groupname=='小组2'}"> selected = "selected"</c:if> >小组2</option>
					<option  <c:if test="${groupname=='小组3'}"> selected = "selected"</c:if> >小组3</option>
				</select>
				<span style="margin-left:10px;" for="name">组内设备</span>
				<select id="devicename">
					<option  <c:if test="${devicename=='设备1'}"> selected = "selected"</c:if> >设备1</option>
					<option  <c:if test="${devicename=='设备2'}"> selected = "selected"</c:if> >设备2</option>
					<option  <c:if test="${devicename=='设备3'}"> selected = "selected"</c:if> >设备3</option>
				</select>
				<span style="margin-left:10px;" for="name">进度状态</span>
				<select id="state">
					<option  <c:if test="${state=='全部'}"> selected = "selected"</c:if> >全部</option>
					<option  <c:if test="${state=='已完成'}"> selected = "selected"</c:if> >已完成</option>
					<option  <c:if test="${state=='未完成'}"> selected = "selected"</c:if> >未完成</option>
				</select>
				<div style="display:inline;margin-left:10px;">关键字：</div>
				<input id="keyword" style="display:inline;" value="${keyword }"></input>
				<button style="margin-left:50px;margin-top:5px;" onclick="search()">搜索</button>
	          
	          <div class="table-responsive">
	            <table class="table table-striped">
	              <thead>
	                <tr>
	                  
	                  <th>文件名称</th>
	                  <th>文件大小</th>
	                  <th>小组名</th>
	                  <th>设备名</th>
	                  <th>剩余时间</th>
	                  <th>完成状态</th>
	                  
	                  
	                  <th>操作</th>
	                  
	                </tr>
	              </thead>
	              <tbody>
	              	<c:forEach items="${list}" var="item" >
	                <tr>
	                  <td>${item.filename }</td>
					  <td>${item.filesize }</td>
					  <td>${item.groupname }</td>
					  <td>${item.devicename }</td>
					  <td>${item.needtime }</td>
					  <td>${item.percent }</td>
					  
					  <td><button>删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</button><br/><button>重新执行</button></td>
					  
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
	function search(){
		var number = $("#number").text();
    	var name = $("#name").text();
    	var groupname = $("#groupname").val();
    	var devicename = $("#devicename").val();
    	var state = $("#state").val();
    	var keyword = $("#keyword").val();
    	
    	
    	
    	window.location.href='detail?groupname='+groupname+'&devicename='+devicename
		+'&state='+state+'&keyword='+keyword+'&taskextid='+number+'&taskextname='+name;
    }
	
	
	</script>

</body>
</html>