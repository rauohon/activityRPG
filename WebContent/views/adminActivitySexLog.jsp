<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
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
			<h1>AdminActivityLogSexPage 입니다.</h1>
			<h2>회원 전체 일일 평균 걸음 수</h2>
			${avgStepAllUser }
			<h2>회원 전체 일일 평균 오른 층 수</h2>
			${avgFloorAllUse }
		</div>
</body>
</html>