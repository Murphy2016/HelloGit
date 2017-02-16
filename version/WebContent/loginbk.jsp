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
		
	</head>
	<body>
		<div class="container">			
			<div class="content-right">				
				<div class="content">
					<form action="<%=basePath %>login" name="frmLogin" method="post" id='loginForm'>						
						<p class="p1">
							<img src="image/person.png">
							<img src="image/login-1.png"  class="login_bar"/>
							<input name="username" type="text"/ class="" placeholder="用户名/ID" id="user" autocomplete="off">
						</p>						
						<p class="p2">
							<img src="image/password.png">
							<img src="image/login-1.png"  class="login_bar"/>
							<input  name="password" type="password" 
							onkeydown='if(event.keyCode==13){doCheck();}' placeholder="密码"/ id="psd">
						</p>	
						 <p class="p3">												
							<img src="image/login.jpg" class="button" id="button"/>
						</p>	
					</form>
				</div>
			</div>
		</div>
		
		
		<script src="./html/js/jquery-1.4.4.min.js"></script>
		<script>
			
			var tiparray = JSON.parse(localStorage.getItem( "tiparr" )) || [];
			
			$("#button").bind("click",function(){
				var user=$("#user").val();
				tiparray.push( user  )
				localStorage.setItem( "tiparr",JSON.stringify(tiparray) )
				document.frmLogin.submit();
			})
			$("#user").bind("input",function(){
				var val = $(this).val();
				creatInputList( val,{
					left : $(this).offset().left,
					top : $(this).offset().top,
					height :　$(this).height()
				} );
			})
			
			$("#user").bind("blur",function(){
				setTimeout(function(){
					$(".inputList").remove();
				},200)
				
			})
			
			function creatInputList( str,offset ){
				console.log(str)
				$(".inputList").remove();
				var html = "<div class='inputList'><ul>";
				for(var i=0; i<tiparray.length; i++){
					if( tiparray[i].indexOf(str) != -1){
					html += "<li>"+tiparray[i]+"</li>";
					}
				}
				html += "</ul></div>";
				
				$('body').append(html);
				
				$(".inputList").css({
					"position" : "absolute",
					"width" : offset.width,
					"left" : offset.left,
					"top" : offset.top+offset.height
				})
				
				$(".inputList li").bind("click",function(){

					$("#user").val( $(this).text() )
				})
			}
			
			
			function doCheck(){
				  document.frmLogin.submit();
			}
			
		</script>
	</body>
</html>
