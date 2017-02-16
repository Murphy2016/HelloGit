<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String user=(String)request.getSession().getAttribute("userctx");

%>
<%Integer count=0; %>
<!DOCTYPE html>
<html>
<head> 
	<meta charset="utf-8"> 
	<title>版本管理</title> 
	<script src="http://code.jquery.com/jquery.min.js"></script>
  <script src="http://code.highcharts.com/highcharts.js"></script>
  <script type="text/javascript" src="../html/js/jquery-1.4.4.min.js"></script>
	<style type="text/css">
			body {
				//background:url(../css/versionback.jpg) top left;
				background-color: rgb(67,105,154);
				margin: 0;
			}
			a {
				color:black;
			}
	</style>
	<!--<STYLE> 
		/* 设置滚动条的样式 */::-webkit-scrollbar {    width: 12px;}/* 滚动槽 */::-webkit-scrollbar-track {    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);    border-radius: 10px;}/* 滚动条滑块 */::-webkit-scrollbar-thumb {    border-radius: 10px;    background: rgba(0,0,0,0.1);    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);}::-webkit-scrollbar-thumb:window-inactive {    background: rgba(255,0,0,0.4);}
	</STYLE> -->
	<style>
		::-webkit-scrollbar {
		  width: 15px;
	} /* 这是针对缺省样式 (必须的) */
	::-webkit-scrollbar-track {
		  background-color: rgb(62,90,125);
	} /* 滚动条的滑轨背景颜色 */

	::-webkit-scrollbar-thumb {
		  background-color: rgba(0, 0, 0, 0.2); 
	} /* 滑块颜色 */

	::-webkit-scrollbar-button {
		  background-color: rgb(42,74,113);
	} /* 滑轨两头的监听按钮颜色 */

	::-webkit-scrollbar-corner {
		  background-color: rgb(57,80,108);
	} /* 横向滚动条和纵向滚动条相交处尖角的颜色 */
	</style>
	
	
	<script type="text/javascript">
	$(function () {
    // Create the chart
    var cnt = $('#softcnt').text();
    
    for(var i=1;i<=cnt;i++){
    var valuea = $('#valuea'+i).text();
    var floata = parseFloat(valuea);
    var valueb = $('#valueb'+i).text();
    var floatb = parseFloat(valueb);
    /*var arr = [{
        name: '已安装',
        y: valuea
        
    }, {
        name: '未安装',
        y: valueb
        
    }];*/
    var arr = [{
        name: '已安装',
        y: floata
        
    }, {
        name: '未安装',
        y: floatb
        
    }];
    //var array = "[{'name':'已安装','y':valuea},{'name':'未安装','y':valueb}]";
    
    Highcharts.chart('container'+i, {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie',
            backgroundColor: 'rgba(0,0,0,0)'
        },
        title: {
            text: ''
            
        },
        credits: {
            enabled: false
        },
        tooltip: {
            pointFormat: ' <b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
        },

       
        series: [{
            name: 'type',
            colorByPoint: true,
            data: arr
        }]
       
    });
    }
});

</script>
</head>
<body>
 
<div id="container" style="width:100%;height:1500px">
 
	
 	<div style="background-color:transparent;height:100%">
		<div id="menu" style="background-color:transparent;width:25%;float:left;">
				<div style="margin-left:20px;margin-top:20px;width:183px;height:63px">
					<a style="width:183px;height:63px">
			        	<img style="max-width:100%;" alt="logo" src="../css/logo1.png">
			    	</a>
				</div>
				<a  href="" style="float:left;margin-right:5px;margin-left:20px;margin-top:20px;">
						              <img   alt="User Picture" src="../css/user.png"></img>
						              
				</a>
				<div>
					<div style="margin-top:25px;"> 
						<a  ><%=user%></a>  
					</div>
					<div style="margin-top:17px;">
						
						<a style="margin-left:20px;" href="">退出</a>
						
					</div>
				</div>
				<div style="clear:both;margin-left:20px;">
					<br>
					<div style="background-color:rgb(78,103,136);width:80px;"><a style="color:black;" href="#">版本管理</a></div>
					<br>
					<div style="background-color:rgb(78,103,136);width:80px;" onclick="window.location.href='search'"><a style="color:black;" href="#">查询</a></div>
				</div>
		</div>
	 
		<div id="content" style="background-color:transparent;float:left;">
			<div style="clear:both;margin-left:40px;margin-top:40px;"><h1>版本管理</h1></div>
			<div style="margin-left:37px;margin-top:20px;">
				
				<div style="margin-left:30px;display:inline;"><big><b>软件总数</b></big></div>
				<div id="softcnt" style="display:inline;">
				<a href="search?page=1">
				<big><b>${cnt.software_count}</b></big></a>
				</div>				
				<div  style="margin-left:60px;display:inline;"><big><b>设备总数</b></big></div>
				<div style="margin-left:1px;display:inline;">
				<a href="search?page=1"><big><b>${cnt.device_count}</b></big></a>
				</div>
			</div>
			
			
			<c:forEach items="${cnt.map0}" var="item" >
			<% count = count+1; %>
			<div style="height:250px">
					
					<div style="margin-left:37px;margin-top:20px;background-color:rgb(78,103,136);width:750px;height:30px"><big><b>${item.key }</b></big></div>
					<div style="margin-left:37px;float:left;height:200px;overflow:auto;">
						<table border="1" width="480px" height="150px" style="height:300px;">
									<thead>
										<tr>
											
											<th>版本名称</th>
											<th>安装设备数</th>
											<th>占安装设备总数比</th>
											<th>其他</th>
											
										</tr>
									</thead>
									<tbody >
										<c:forEach items="${item.value.list}" var="item1" >
										<tr>
											<td><a href="search?software_name=${ item.key}&version_str=${item1.version_str}&page=1">
											${item1.version_str}</a></td>
											<td>${item1.count}</td>
											<td>${item1.percent}</td>
											<td><a href="search2?software_name=${ item.key}&version=${item1.version}&page=1">其他</a></td>
										</tr>
										</c:forEach>
										
									</tbody>
									
						</table>			
					
					
					</div>
					
					
					<div style="margin-left:37px;float:left;width:200px;height:200px">
						<div id="container<%=count %>" style="min-width: 200px; max-width: 200px; height: 200px; margin: 0 auto"></div>
						<div id="valuea<%=count %>" hidden="hidden">${item.value.percent1 }</div>
						<div id="valueb<%=count %>" hidden="hidden">${item.value.percent2 }</div>
					</div>
					<div style="margin-left:520px;margin-top:210px;">
						<div style="margin-left:30px;display:inline;">已安装数</div>
						<div  style="margin-left:10px;display:inline;">
						<a href="search?software_name=${item.key}&page=1">
						${item.value.install }</a>
						</div>
						<div style="margin-left:15px;display:inline;">未安装数</div>
						<div  style="margin-left:10px;display:inline;">
						<a href="search2?software_name=${item.key}&page=1">
						${item.value.uninstall }</a>
						</div>
					</div>
			
			</div>
			</c:forEach>
			
		</div>
  </div>
	
 
</div>
 
</body>
</html>