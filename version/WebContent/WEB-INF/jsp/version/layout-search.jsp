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
	<link href="../html/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../html/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<!-- <script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script> -->
	<script type="text/javascript" src="../html/js/jquery-1.11.1.js"></script>
	<script src="../html/bootstrap/js/moment-with-locales.min.js"></script>
	<script src="../html/bootstrap/js/bootstrap-datetimepicker.min.js"></script> 
	<script type="text/javascript" src="../html/js/highcharts/highcharts.js"></script>
	<style type="text/css">
			body {
				//background:url(../css/versionback.jpg) top left;
				background-color: rgb(67,105,154);
				margin: 0;
			}
			a {
				color:black;
			}
			input {
				background-color:rgb(111,144,186)
			}
			button {
				background-color:rgb(111,144,186)
			}
			.line {
				margin-left:37px;
				margin-top:8px;
				margin-bottom:8px;
				width:700px;
				height:3px;
				background-color:rgb(134,143,153)
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
						<a  ><%=user%></a>  
					</div>
         			<div style="margin-top:17px;">
						
						<a style="margin-left:20px;" href="">退出</a>
						
					</div>
				</div>
				<div style="clear:both;margin-left:20px;">
					<br>
				  <div style="background-color:rgb(78,103,136);width:80px;" onclick="window.location.href='index'"><a href="#">版本管理</a></div>
				  <br>
				  <div style="background-color:rgb(78,103,136);width:80px;"><a href="#">查询</a></div>
				</div>
		</div>
	 
		<div id="content" style="background-color:transparent;float:left;">
			<div style="clear:both;margin-left:40px;margin-top:40px;"><h1>查询</h1></div>
			
			<div  style="margin-left:37px;margin-top:20px;">
				<div style="display:inline;">设备编号</div>
				<input id="device_id"  placeholder="" style="margin-left:30px;width:100px;display:inline" value="${result.version.device_id }"></input>
				<div style="display:inline;">Biz_Num</div>
				<input id="biz_num"  placeholder="" style="margin-left:30px;width:100px;display:inline" value="${result.version.biz_num }"></input>
				<div style="display:inline;">创建时间</div>
				<%-- <input id="create_time"  placeholder="yyyy-MM-dd" style="margin-left:30px;width:100px;" value="${result.version.create_time }"></input> --%>
				<div class="input-group date" style="display:inline;margin-left:30px;width:100px;">
				<input id="create_time" type="text" value="${result.create_time }">
				</div>
				
			</div>
			<div  style="margin-left:37px;margin-top:20px;">
				<div style="margin-top:20px;display:inline">软件名称</div>
				<input id="software_name"  placeholder="" style="margin-left:30px;width:100px;display:inline" value="${result.version.software_name}"></input>
				<div style="margin-top:20px;display:inline">版本号</div>
				<input id="version_str"  placeholder="" style="margin-left:30px;width:100px;display:inline" value="${result.version.version_str }"></input>
				<div style="margin-top:20px;display:inline">更新时间</div>
				<%-- <input id="update_time"  placeholder="yyyy-MM-dd" style="margin-left:30px;width:100px;display:inline" value="${result.version.update_time }"></input> --%>
				<div class="input-group date" style="display:inline;margin-left:30px;width:100px;">
				<input id="update_time" type="text" value="${result.update_time }">
				</div>
				
				<button style="margin-left:30px;width:50px;display:inline" onclick="search()" type="button">搜索</button>
			</div>
			
			
			<div>
					<div class="line"></div>
					<div style="margin-left:37px;margin-top:20px;width:700px;height:30px"><h3>结果汇总</h2></div>
					<div  style="margin-left:37px;margin-top:10px;">						
						<div style="margin-left:30px;display:inline;">筛选结果</div>
						<div  style="margin-left:10px;display:inline;">${result.count}条</div>						
						<div  style="margin-left:30px;display:inline;">设备数</div>
						<div style="margin-left:10px;display:inline;">${result.devicecount}</div>
						<div  style="margin-left:30px;display:inline;">软件数</div>
						<div style="margin-left:10px;display:inline;">${result.softwarecount}</div>
						<div  style="margin-left:30px;display:inline;">版本数</div>
						<div style="margin-left:10px;display:inline;">${result.versioncount}</div>
					</div>
					<div style="margin-left:37px;margin-top:10px;width:700px;height:30px"><h3>列表详情</h2></div>
					<div style="margin-left:400px;margin-bottom:10px;">
						<button style="margin-left:30px;width:100px;display:inline" type="button" onclick="exportTable1()">批量导出</button>
						<button style="margin-left:5px;width:100px;display:inline" type="button" onclick="exportTable2()">按编号导出</button>
					</div>
					<div style="margin-left:40px;float:left;height:500px;overflow:auto;">
						<table border="1" width="700px" height="500px" style="height:300px;">
									<thead>
										<tr>
											
											<th>编号</th>
											<th>Biz_Num</th>
											<th>软件名称</th>
											<th>版本号</th>
											<th>更新时间</th>
											<th>创建时间</th>
										</tr>
									</thead>
									<tbody >
										<c:forEach items="${result.rows}" var="item" >
										<tr>
											<td><div data-id="${item.device_id }" onclick="getelem(this)">
											<a href="#">${item.device_id }</a></div>
											</td>
											<td>${item.biz_num }</td>
											<td>${item.software_name }</td>
											<td>${item.version_str }</td>
											<td>${item.update_time_str }</td>
											<td>${item.create_time_str }</td>
										
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
	function getquery(){
		var query="";
		var device_id = $('#device_id').val();
		if(device_id!=""){
			if(query!="")
				query = query + "&";
			query = query + "device_id="+device_id;
		}
		var biz_num = $('#biz_num').val();
		if(biz_num!=""){
			if(query!="")
				query = query + "&";
			query = query + "biz_num="+biz_num;
		}
		var software_name = $('#software_name').val();
		if(software_name!=""){
			if(query!="")
				query = query + "&";
			query = query + "software_name="+software_name;
		}
		var version_str = $('#version_str').val();
		if(version_str!=""){
			if(query!="")
				query = query + "&";
			query = query + "version_str="+version_str;
		}
		var update_time = $('#update_time').val();
		if(update_time!=""){
			if(query!="")
				query = query + "&";
			query = query + "time2="+update_time;
		}
		var create_time = $('#create_time').val();
		if(create_time!=""){
			if(query!="")
				query = query + "&";
			query = query + "time1="+create_time;
		}
		return query;
	}
	function search(){
		
		var query = getquery();
		if(query!="")
			window.location.href='search'+'?'+query+'&page=1';
	}
	function search1(page){
		
		var query = getquery();
		if(query!="")
			window.location.href='search'+'?'+query+'&page='+page;
		else
			window.location.href='search'+'?'+'page='+page;
			
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
		var query = getquery();
		var label=document.getElementById("export1");
		if(query!="")
			label.src="exportTable1?"+query;
		else
			label.src="exportTable1";
	}
	function exportTable2(){
		var query = getquery();
		var label=document.getElementById("export2");
		if(query!="")
			label.src="exportTable2?"+query;
		else
			label.src="exportTable2";
	}
	
	function getelem(e){
		var id=e.getAttribute("data-id");
		
		//alert("Id: "+id);
		
		var myurl ='getdata?device_id='+id;
		$.post(myurl,function(result){
			if (result.success){
				
				//alert("total="+result.total);
				float_table(result.rows);
				
			} else {
				$.messager.show({	// show error message
					title: 'Error',
					msg: result.errorMsg
				});
			}
		},'json');
	}
	
	
	
	function float_table(list){
		//清除之前的样式
        $("#fullScreen,#floatLayer").remove();
		
		var trs="";
        $.each(list,function(i,value){
            //alert(i+"..."+value);
            trs +="<tr>";
            trs +="<td>"+value.device_id+"</td>";
            trs +="<td>"+value.biz_num+"</td>";
            trs +="<td>"+value.software_name+"</td>";
            trs +="<td>"+value.version_str+"</td>";
            trs +="<td>"+value.update_time_str+"</td>";
            trs +="<td>"+value.create_time_str+"</td>";
            
            
            trs +="</tr>";
       });
		
        $("body").append
        (
        		
            //占据整个屏幕Div
            "<div id='fullScreen'></div>"+
            //浮层区
            "<div id='floatLayer'>" +
            "<button id='hide'>X</button>" +
            "<div style='height:350px;overflow:auto;'>"+
            "<table border='1'><thead>"+
				"<tr>"+
					
					"<th>编号</th>"+
					"<th>Biz_Num</th>"+
					"<th>软件名称</th>"+
					"<th>版本号</th>"+
					"<th>更新时间</th>"+
					"<th>创建时间</th>"+
					"</tr>"+
					"</thead>"+
					
					"</thead>"+
					"<tbody >"+
					trs+
						
						
				"</tbody></table></div>"
            
            +"</div>"
        );
        //隐藏浮层
        $("#hide").click(function()
        {
            $("#fullScreen,#floatLayer").remove();
        });
	}
	function listToTable(list){
		var table;
		
		
		
		return table;
	}
	$('#create_time').datetimepicker({
		format: 'YYYY-MM-DD',  
        locale: moment.locale('zh-cn')
	});
	$('#update_time').datetimepicker({
		format: 'YYYY-MM-DD',  
        locale: moment.locale('zh-cn')
	});


</script> 
<style>
	/*人为制造一个占据整个屏幕的Div,其透明度为0.7且z-index为9999使之前的页面被压在底层无法点击*/
        #fullScreen
        {
            position: fixed;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            opacity: 0.7;
            background-color: black;
            z-index: 9999;
        }
        /*浮层,可随意设置大小宽高，但是z-index必须比上面fullScreen大才能显示出来*/
        #floatLayer
        {
            position: fixed;
            width: 500px;
            height: 400px;
            left: 34%;
            top: 15%;
            background-color: rgb(67,105,154);
            z-index: 10000;
        }
        button {
        	background-color:rgb(111,144,186);
        	
        }
</style>

 
 
</body>
</html>

