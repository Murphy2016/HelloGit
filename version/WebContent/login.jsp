<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!--<link rel="stylesheet" href="../css/reset.css" />-->
		<link rel="stylesheet" href="css/login3.css" />
		
		<script>		
			function doCheck(){
				var user=$("#user").val();
				var len=tiparray.length;
				var find=false;
				for(var i=0;i<len;i++){
					if(tiparray[i]==user){
						find=true;
					}
				}
				if(!find){
					tiparray.push( user  )
				}
				localStorage.setItem( "tiparr",JSON.stringify(tiparray) )
				document.frmLogin.submit();
			}		
		</script>
	</head>
	<body>
		<div class="container">
			<div class="content">
				<img src="image/login-con-left.png">
				<form action="<%=basePath %>login" name="frmLogin" method="post">						
					<p class="p1">
						<img src="image/person.png" class="person_img">
						<img src="image/login-1.png"  class="login_bar"/>
						<input name="username" type="text"/ class="user" placeholder="用户名/ID" maxlength="16" id="user" autocomplete="off">
					</p>						
					<p class="p2">
						<img src="image/password.png" class="pass_img">
						<img src="image/login-1.png"  class="login_bar2"/>
						<input name="password" 
						onkeydown='if(event.keyCode==13){doCheck();}' 
						type="password"/ placeholder="密码"  maxlength="16" id="psd">
					</p>	
					 <p class="p3">												
						<img src="image/login.jpg" class="button" id="button" 
						 />
					</p>	
				</form>
			</div>
		</div>
	</body>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script>			
		var tiparray = JSON.parse(localStorage.getItem( "tiparr" )) || [];			
			$("#button").bind("click",function(){
				var user=$("#user").val();
				var len=tiparray.length;
				var find=false;
				for(var i=0;i<len;i++){
					if(tiparray[i]==user){
						find=true;
					}
				}
				if(!find){
					tiparray.push( user  )
				}
				localStorage.setItem( "tiparr",JSON.stringify(tiparray) )
				document.frmLogin.submit();
			})
			$("#user").bind("input",function(){
				var val = $(this).val();
				creatInputList(val,{
					left: $(this).offset().left,
					top : $(this).offset().top,
					height :$(this).height()
				} );
			})
			$("#user").bind("click",function(){
				var val = $(this).val();
				creatInputList(val,{
					left: $(this).offset().left,
					top : $(this).offset().top,
					height :$(this).height()
				} );
			})
		
			$("#user").bind("blur",function(){
				setTimeout(function(){
					$(".inputList").remove();
				},100)				
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
			
			
		</script>
</html>
