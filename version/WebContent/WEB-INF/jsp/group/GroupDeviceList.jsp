<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="../html/js/jquery-1.11.1.js"></script>
</head>
<body>
	<label>选择设备</label> <br/>
		<span><input id="online" type="checkbox"  <c:if test="${online}"> checked="checked"</c:if> >在线</span>
		<span><input id="offline" type="checkbox"  <c:if test="${offline}"> checked="checked"</c:if> >离线</span>
		<div style="display:inline;margin-left:20px;">关键字：</div>
		<input id="keyword" style="display:inline;" value="${keyword }"></input>
		<button style="margin-left:2px;margin-top:5px;" onclick="search()">搜索</button>
		<br/><br/>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th><input type="checkbox"  onclick="Checkall('device')"/></th>
                  <th>设备编号</th>
                  <th>SN码</th>
                  
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${list}" var="item" >
                <tr>
                  <td><input type="checkbox" name="device" onclick="Check(this,'${item.deviceid }')" value="${item.deviceid }"/></td>
                  <!-- <td><a href="#" onclick="window.parent.location='detail1'">设备000111</a></td> -->
                  <td>${item.deviceid }</td>
				  <td>${item.sn }</td>
							
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
	<script type="text/javascript">
	function Checkall(XelementName){
        var checkboxes=document.getElementsByName(XelementName);
        for(var i=0;i<checkboxes.length;i++)
        {
			checkboxes[i].checked=!checkboxes[i].checked;
			Check(checkboxes[i],checkboxes[i].value);
        }
    }
	function Check(elem,deviceid){
		//alert(deviceid);
		var ids = parent.document.getElementById("ids");
		if(elem.checked){
			if(ids.value==""){
				ids.value = deviceid;
			}else{
				ids.value += ","+deviceid;
			}			
			//alert(parent.document.getElementById("ids").value);
		}else{
			//alert("uncheck");
			var ss = ids.value.split(",");
			var tmp="";
			for(var i=0;i<ss.length;i++){
				if(ss[i]!=deviceid){
					if(tmp!="")
						tmp =+ ",";
					tmp += ss[i];
				}
			}
		}
	}
	
	</script>
</body>
</html>