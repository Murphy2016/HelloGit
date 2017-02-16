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
    <link href="../html/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

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
            <li ><a href="../monitor/index1">设备监控</a></li>
            <li><a href="../group/index">分组管理</a></li>
            <li><a href="../taskext/index">任务单管理</a></li>
            <li class="active"><a href="#">任务管理</a></li>
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
          
		  
		<h3 class="page-header">任务管理</h3> 
		 <div class="well well-sm">
         <div  style="margin-left:37px;margin-top:5px;">
				<div style="display:inline;">任务编号：</div>
				<input id="taskid" style="display:inline;" placeholder="" value="${taskid }"></input>
				<div style="display:inline;">任务名称：</div>
				<input id="taskname" style="display:inline;" value="${taskname }"></input>
				<div style="display:inline;">任务类型：</div>
				<select id="tasktype">
					<option <c:if test="${tasktype=='文件推送'}"> selected = "selected"</c:if> >文件推送</option>
					<option <c:if test="${tasktype=='查看资源'}"> selected = "selected"</c:if> >查看资源</option>
					<option <c:if test="${tasktype=='删除文件'}"> selected = "selected"</c:if> >删除文件</option>
				</select>
				<br/>
				<div style="display:inline;" >创建时间：</div>
				<%-- <input id="createtime" style="display:inline;" placeholder="yyyy-MM-dd" value="${createtime }"></input> --%>
				<input id="createtime" type="text" value="${createtime }">
				
				<div style="display:inline;" placeholder="yyyy-MM-dd">更新时间：</div>
				<%-- <input id="updatetime" style="display:inline;" placeholder="yyyy-MM-dd" value="${updatetime }"></input> --%>
				<input id="updatetime" type="text" value="${updatetime }">
				
				<div style="display:inline;">关&nbsp;键&nbsp;字&nbsp;：</div>
				<input id="keyword" style="display:inline;" value="${keyword }"></input>
				
				<button style="margin-left:50px;margin-top:5px;" onclick="search()">搜索</button>
				<button style="margin-left:50px;margin-top:5px;" onclick="create()">创建任务</button>
				
			</div> 
			</div><h2 class="sub-header">任务列表</h2>
	          <div class="table-responsive">
	            <table class="table table-striped">
	              <thead>
	                <tr>
	                  
	                  <th>任务编号</th>
	                  <th>名称</th>
	                  <th>任务类型</th>
	                  <th>任务内容</th>
	                  
	                  <th>创建时间</th>
	                  <th>更新时间</th>
	                  <th>任务描述</th>
	                  <th>操作</th>
	                  
	                </tr>
	              </thead>
	              <tbody>
	              	<c:forEach items="${list}" var="item" >
	                <tr>
	                  <td>${item.taskid }</td>
					  <td>${item.taskname }</td>
					  <td>${item.tasktype }</td>
					  <td>${item.taskcontent }</td>
					  <td>${item.taskcreatetime }</td>
					  <td>${item.taskupdatetime }</td>
					  <td>${item.taskdisc }</td>
					  <td><button onclick="edit('${item.taskid }','${item.taskname }','${item.tasktype }','${item.taskcontent }','${item.taskcreatetime }','${item.taskupdatetime }','${item.taskdisc }')">编辑</button></td>
					  
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
	    	var taskid = $("#taskid").val();
	    	var taskname = $("#taskname").val();
	    	var tasktype = $("#tasktype").val();
	    	var createtime = $("#createtime").val();
	    	var updatetime = $("#updatetime").val();
	    	var keyword = $("#keyword").val();
	    	
	    	window.location.href='index?taskid='+taskid+'&taskname='+taskname
			+'&tasktype='+tasktype+'&createtime='+createtime+'&updatetime='+updatetime
			+'&keyword='+keyword;
	    }
		function create(){
	    	
	    	window.location.href='create';
	    }
		function edit(taskid,taskname,tasktype,taskcontent,createtime,updatetime,taskdisc){
	    	
	    	window.location.href='edit?taskid='+taskid+'&taskname='+taskname
			+'&tasktype='+tasktype+'&taskcontent='+taskcontent+'&createtime='+createtime+'&updatetime='+updatetime
			+'&taskdisc='+taskdisc;
	    }
		
		$('#createtime').datetimepicker({
			format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')
		});
		$('#updatetime').datetimepicker({
			format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')
		});
		
	
	</script>

</body>
</html>