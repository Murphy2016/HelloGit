<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String cpath = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cpath+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/images/menu/menuicon.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/images/tool/toolicon.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/css/msg.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/js/loadmask/jquery.loadmask.css">
 <%
     String themeName = "bootstrap";
     Cookie cookies[] = request.getCookies();
     if(cookies != null && cookies.length > 0){
        for(int i = 0; i < cookies.length; i++){
            if("themeName".equals(cookies[i].getName())){
               themeName = cookies[i].getValue();
               break;
            }
        }
     }
  %>
<link id="easyuiTheme"  rel="stylesheet" type="text/css" href="<%=cbasePath%>html/js/jquery-easyui-1.4/themes/<%=themeName%>/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>html/js/jquery-easyui-1.4/themes/icon.css">

<script type="text/javascript" src="<%=cbasePath%>html/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/json.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/loadmask/jquery.loadmask.js"></script>

<script type="text/javascript" src="<%=cbasePath%>html/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/jquery.cookie.js"></script>

<script type="text/javascript" src="<%=cbasePath%>html/js/goform.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=cbasePath%>html/js/dateFormat.js"></script>


