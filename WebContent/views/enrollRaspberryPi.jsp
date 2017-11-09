<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style type="text/css">
p {
	color : tomato;
}
</style>
<script>
function init(){
	var userType = "${userType}";
	if(userType==2){
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
}
</script>
</head>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
		<div id='wraper' style="padding-top: 60px;">
			<h1>EnrollRaspberryPiPage 입니다.</h1>
			<h3>라즈베리파이 코드</h3>
			<form action="EnrollRaspberry" method='get' >
				<p>${msg }</p>
				${id }님!! 반갑습니다.
				<input type='text' placeholder='라즈베리파이에 쓰여있는 코드를 입력하세요.'  name='riId' />
				<input type='hidden' name='id' value='${id }' />
				<input type='submit' value='제출' />
			</form>
		</div>
</body>
</html>