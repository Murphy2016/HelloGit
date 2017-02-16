<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
	    <meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">		
		<title></title>
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/login2.css" />		
		
		<style type="text/css">
		    input:-webkit-autofill {
		         -webkit-box-shadow: 0 0 0px 1000px white inset;
		       }
		       
		    .ac_results { padding: 0px; border: 1px solid black; background-color: red; overflow: hidden; z-index: 99999; }   
		</style>
		<script type="text/javascript" >
			function doCheck(){
			  document.frmLogin.submit();
			}
			
			
	
		</script>
	</head>
	<body>
		<div class="container">		
			<div class="ac_results" style="display: none;">
		      <ul style="overflow: auto; max-height: 180px;">
		        //the results here as li's.. they vary with what you typed
		      </ul>
		   </div>	
			<div class="content-right">				
				<div class="content">
					<form action="<%=basePath %>login" name="frmLogin" method="post">						
						<p class="p1">
							<img src="image/person.png">
							<img src="image/login-1.png"  class="login_bar"/>
							<input name="username" type="text"/ 
							
							class="" placeholder="用户名/ID">
						</p>						
						<p class="p2">
							<img src="image/password.png">
							<img src="image/login-1.png"  class="login_bar"/>
							<input  name="password" type="password" 
							onkeydown='if(event.keyCode==13){doCheck();}' placeholder="密码"/>
						</p>	
						 <p class="p3">												
							<img src="image/login.jpg" class="button" 
							 onclick="doCheck()"/>
						</p>	
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
