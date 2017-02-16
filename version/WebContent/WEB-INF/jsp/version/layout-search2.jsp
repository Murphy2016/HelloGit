<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String user=(String)request.getSession().getAttribute("userctx");

%>

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
			button {
				background-color:rgb(111,144,186)
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
	
	
<!--	<script type="text/javascript">
	$(function () {
    // Create the chart
    Highcharts.chart('container1', {
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
            pointFormat: ' <b>{point.percentage:.1f}%</b>'
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
            data: [{
                name: 'apple',
                y: 56.33
                
            }, {
                name: 'banana',
                y: 43.67
                
            }]
        }]
       
    });
});

</script>-->
</head>
<body>
 
<div id="container" style="width:100%;">
 
	
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
						<a   ><%=user%></a>  
					</div>
          			<div style="margin-top:17px;">
						
						<a style="margin-left:20px;" href="">退出</a>
						
					</div>
				</div>
				<div style="clear:both;margin-left:20px;">
					<br>
				  <div style="background-color:rgb(78,103,136);width:80px;" onclick="window.location.href='index'"><a href="#">返回</a></div>
				  
				</div>
		</div>
	 
		<div id="content" style="background-color:transparent;float:left;">
			<div style="clear:both;margin-left:40px;margin-top:40px;"><h1>未安装设备列表</h1></div>
			
			
			<div>
					<%-- <div style="margin-left:37px;margin-top:20px;">						
						<div style="margin-left:1px;display:inline;"><big>筛选结果</big></div>
						<div  style="margin-left:10px;display:inline;"><big>${result.count}条</big></div>
					</div> --%>
					<div style="margin-left:37px;margin-top:20px;width:750px;height:30px"><h3>列表详情</h3></div>
					<div style="margin-left:400px;margin-top:10px;margin-bottom:10px;">
						<span ><big>总共${result.count}条</big></span>
						<button style="margin-left:5px;width:100px;display:inline" type="button" onclick="exportTable1()">批量导出</button>
						<button style="margin-left:5px;width:100px;display:inline" type="button" onclick="exportTable2()">按编号导出</button>
					</div>
					<div id="software_name" style="display:none">${result.version.software_name}</div>
					<div id="version" style="display:none">${result.version.version}</div>
					<div style="margin-left:40px;float:left;height:500px;overflow:auto;">
						<table border="1" width="700px" height="500px" style="height:300px;">
									<thead>
										<tr>
											
											<th>编号</th>
											<th>Biz_Num</th>
											
										</tr>
									</thead>
									<tbody >
										<c:forEach items="${result.rows}" var="item" >
										<tr>
											<td>${item.device_id }</td>
											<td>${item.biz_num }</td>
											
										
										</tr>
										</c:forEach>
									</tbody>
									
						</table>			
						
					
					</div>
					<c:if test="${result.pages > 1}">
					<div style="margin-left:450px;margin-top:10px">
						
						<button style="display:inline;" onclick="previouspage()">上一页</button>						
						<div style="display:inline;">第${result.page}页</div>
						<div style="display:inline;">共${result.pages}页</div>
						<button style="display:inline;" onclick="nextpage()">下一页</button>
						<div id="page" style="display:none">${result.page}</div>
						<div id="pages" style="display:none">${result.pages}</div>
						
					</div>
					</c:if>
			
			</div>
			<iframe id="export1" style="display:none" src = ""></iframe>
			<iframe id="export2" style="display:none" src = ""></iframe>
		</div>
  </div>
	
 
</div>
<script type="text/javascript">
	

	function search1(page){
		
		var software_name=$('#software_name').text();
		var version=$('#version').text();
		
		if(version==''){
			window.location.href='search2'+'?software_name='+software_name+'&page='+page;	
		}else{
			window.location.href='search2'+'?software_name='+software_name+
					'&version='+version+
					'&page='+page;
		}
		
		
			
	}
	function nextpage(){
		var page = $('#page').text();
		var pages = $('#pages').text();
		page++;
		if(page<=pages){
			search1(page);
		}
	}
	function previouspage(){
		var page = $('#page').text();
		var pages = $('#pages').text();
		page--;
		if(page>=1){
			search1(page);
		}
	}
	
	
	function exportTable1(){
		var software_name=$('#software_name').text();
		var version=$('#version').text();
		var label=document.getElementById("export1");
		if(version==''){
			label.src="exportTable1?not=1&software_name="+software_name;
		}else{
			label.src="exportTable1?not=1&software_name="+software_name+'&version='+version;
		}
	}
	function exportTable2(){
		var software_name=$('#software_name').text();
		var version=$('#version').text();
		var label=document.getElementById("export2");
		if(version==''){
			label.src="exportTable2?not=1&software_name="+software_name;
		}else{
			label.src="exportTable2?not=1&software_name="+software_name+'&version='+version;
		}
	}

</script> 

 
</body>
</html>

