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
          
		  
		<h3 class="page-header">分组详情</h3> 
		 <div class="well well-sm">
         	<div  style="margin-left:37px;margin-top:5px;">
				<div>
					<label>小组名称：</label>
					<span id="name">${name }</span>
				</div>
				<div>
					<label>小组说明：</label>
					<span id="comment">${comment }</span>
				</div>
				
			
			</div> 
		</div>
		<h2 class="sub-header">设备列表</h2>
		<div style="display:inline;">关键字：</div>
		<input id="keyword" style="display:inline;" value="${keyword }"></input>
		<span><input id="online" type="checkbox" <c:if test="${online}"> checked="checked"</c:if> >在线</span>
		<span><input id="offline" type="checkbox" <c:if test="${offline}"> checked="checked"</c:if> >离线</span>
		<button style="margin-left:2px;margin-top:5px;" onclick="search()">搜索</button>
		
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th><input type="checkbox"  onclick="Checkall('checkbox1')"/></th>
                  <th>设备信息</th>
                  <th>状态</th>
                  <th>位置</th>
                  <th>最后通讯时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${list}" var="item" >
                <tr>
                  <td><input type="checkbox" name="checkbox1"/></td>
                  <!-- <td><a href="#" onclick="window.parent.location='../monitor/detail1'">设备000111</a></td> -->
                  <td><a href="#" onclick="window.open('../monitor/detail1?wifi_sn=${item.wifi_sn }&biz_num=${item.biz_num }&day=${item.day }&ip=${item.ip }&longitude=${item.longitude }&latitude=${item.latitude }&online_total_time=${item.online_total_time }')">
                  		设备号：${item.biz_num }<br/>SN：${item.wifi_sn }<br/>IP： ${item.ip }</a></td>
							<td>${item.state }</td>
							<td>
								<a  id="show" href="#" onclick="window.open('../monitor/map?longitude=${item.longitude}&latitude=${ item.latitude}')">位置</a>
							</td>
														
							<td>
							
							${item.lastcom }
							
							
							</td>
							<td><button>删除</button></td>
                </tr>
                </c:forEach>
                <!--  <tr>
                  <td><input type="checkbox" name="checkbox1"/></td>
                  <td><a href="#" onclick="window.parent.location='detail1'">设备000111</a></td>
                  <td><a href="#" onclick="window.open('detail1')">设备000111</a></td>
							<td>111</td>
							<td>
								<a  id="show" href="#" onclick="window.open('map')">北京</a>
							</td>
														
							<td>
							
							<div id="hicontainer2" class="hicontainer"  style="width:500px; height:250px;"></div>
							
							
							</td>
                </tr> -->
                
              </tbody>
            </table>
            <%@ include file="../Page.jsp" %>
          </div>
			
				
			
			
			
          
        </div>
      </div>
    </div>

	<script type="text/javascript">
	function search(){
    	var name = $("#name").text();
    	var keyword = $("#keyword").val();
    	var online = $("#online").is(':checked');
    	var offline = $("#offline").is(':checked');
    	
    	
    	
    	window.location.href='detail?groupname='+name+'&keyword='+keyword
		+'&online='+online+'&offline='+offline;
    }
	
	
	
	</script>

</body>
</html>