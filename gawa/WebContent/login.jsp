<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common.jsp" %>

<title>千陌娱乐后台管理系统</title>
	<script type="text/javascript" >
		function doCheck(){
		  if(document.frmLogin.username.value=="" ){
			alert('请输入正确的用户名！');
		      return;
		  }
		  if(document.frmLogin.passwd.value=="" || document.frmLogin.passwd.value.length < 4){
			  alert('请输入正确的密码！');
		      return;
		  }
	
		  
		  $("body").mask("登录中...");
		  document.frmLogin.submit();
		}
	
	
	
	</script>
	
	
</head>



<body style="height:100%;width: 364px;border:none;text-align:center;"  >
    <div >
        <div class="wrap">
            <div class="wrap">
                
                <div class="wrap">
                
                	<form id="ff" action="<%=basePath %>login/login" name="frmLogin" method="post" style="width: 328px; ">
                		
                
                    <div id="con_one_1" class="hover">
                        <div class="login_inputb login_inputb_bg1">
                            <input name="username" type="text" placeholder="ll姓名">
                        </div>
                        <div class="login_inputb login_inputb_bg3">
                            <input name="passwd"  type="password" placeholder="密码">
                        </div>
									
                    </div>
                        

                        <a class="btn_dl" href="javascript:void(0);" onclick="doCheck()">登&nbsp;&nbsp;录</a>
                        <div class="login_zc">
                        </div>
                   
                    
                  </form>
                  
                    
                </div>
            </div>
            
        </div>
    </div>
    
	<style type="text/css">
		
		.wrap{
			margin:0 auto;
			
		}
	</style>    
</body>
</html>

