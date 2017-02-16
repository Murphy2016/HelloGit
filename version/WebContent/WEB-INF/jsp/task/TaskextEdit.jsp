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
    <script src="../html/bootstrap/js/bootstrap.min.js"></script>
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
          
		  
		<h3 class="page-header">任务单编辑</h3> 
		<div style="width:50%">
		<form role="form" action="save">
			<div class="form-group">
				<label for="name">任务单编号</label>
				<input type="text" class="form-control" id="name" name="taskextid"
					   readonly="readonly" value="${taskextid }">
			</div>
			<div class="form-group">
				<label for="name">任务单名称</label>
				<input type="text" class="form-control" id="name" name="taskname"
					   placeholder="请输入名称" value="${taskextname }">
			</div>
			<div class="form-group">
				<label for="name">任务单期限</label>
				
					   
					   
				<div class="row">  
				    <div class='col-sm-6'>  
				        <div class="form-group">  
				            <span>选择开始时间：</span>  
				            <!--指定 date标记-->  
				            <div class='input-group date' id='datetimepicker1'>  
				                <input type='text' class="form-control" id="startdate" name="startdate"/>  
				                <span class="input-group-addon">  
				                    <span class="glyphicon glyphicon-calendar"></span>  
				                </span>  
				            </div>  
				        </div>  
				    </div>  
				    <div class='col-sm-6'>  
				        <div class="form-group">  
				            <span>选择结束时间：</span>  
				            <!--指定 date标记-->  
				            <div class='input-group date' id='datetimepicker2'>  
				                <input type='text' class="form-control" id="enddate" name="enddate"/>  
				                <span class="input-group-addon">  
				                    <span class="glyphicon glyphicon-calendar"></span>  
				                </span>  
				            </div>  
				        </div>  
				    </div>  
				</div>  	   
					   
					   
					   	   
			</div>
			<label for="name">发布对象</label>
			<select class="form-control">
				<option <c:if test="${taskextdest=='设备小组1'}"> selected = "selected"</c:if> >设备小组1</option>
				<option <c:if test="${taskextdest=='设备小组2'}"> selected = "selected"</c:if> >设备小组2</option>
				<option <c:if test="${taskextdest=='设备小组3'}"> selected = "selected"</c:if> >设备小组3</option>
				
			</select>
			<label for="name">选择任务</label>
			<select class="form-control">
				<option>任务1</option>
				<option>任务2</option>
				<option>任务3</option>
				
			</select>
			
			<div class="form-group">
			    <label for="name">任务单说明</label>
			    <textarea class="form-control" rows="3">${taskextcomment }</textarea>
			</div>
			
			
			<button type="submit" class="btn btn-default">提交</button>
			<button  class="btn btn-default" onclick="history.go(-1)">返回</button>
		</form>
        </div> 
			
			
	         
				
			
			
			
          
        </div>
      </div>
    </div>
	<script src="../html/js/startenddate.js"></script>

</body>
</html>