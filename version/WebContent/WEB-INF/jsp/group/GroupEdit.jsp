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
            <li ><a href="../monitor/index1">设备监控</a></li>
            <li class="active"><a href="index">分组管理</a></li>
            <li><a href="../taskext/index">任务单管理</a></li>
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
          
		  
		<h3 class="page-header">小组编辑</h3> 
		<div style="width:50%">
		<!-- <form name=”form” role="form" action="#"> -->
			<div class="form-group">
				<label for="name">小组名称</label>
				<input type="text" class="form-control" id="groupname" name="groupname"
					   value="${groupname }">
			</div>
			
			
			
		
		<iframe src="devicelist"  name="mainframe" frameborder="0" scrolling="no" 
			width="520px" height="250px"></iframe>  
		
			<div class="form-group">
				<label for="name">小组说明</label>
				<textarea  class="form-control" id="groupcomment" name="groupcomment" rows="3"></textarea>
			</div>	
			<input id="ids" name="ids" hidden="hidden"></input>
			
			
			
			
			<button type="submit" class="btn btn-default" onclick="summit();">提交</button>
			<button  class="btn btn-default" onclick="history.go(-1)">返回</button>
		<!-- </form> -->
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
	function summit(){
		var a, b;
		a = new Array(0,1,2,3,4);
		b = a.join(",");
		var s = "abc,abcd,aaa";
		ss = s.split(",");
		for(var i=0;i<ss.length;i++){
			var tmp = ss[i];
			var tmp1=tmp;
		}
		
		
		
		
        var ids = $("#ids").val();
        var groupname = $("#groupname").val();
        var groupcomment = $("#groupcomment").val();
		//var form = $("form");
		//form.attr("action", "save");
		//form.summit();
        window.location.href='save?groupname='+groupname+'&groupcomment='+groupcomment
		+'&ids='+ids;
	}
	function search(){
    	var name = $("#groupname").val();
    	var keyword = $("#keyword").val();
    	var online = $("#online").is(':checked');
    	var offline = $("#offline").is(':checked');
    	
    	window.location.href='edit?groupname='+name+'&keyword='+keyword
		+'&online='+online+'&offline='+offline;
    	
    }
	
	</script>

</body>
</html>