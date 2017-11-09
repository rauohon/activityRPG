<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<a href='AdminActivityLogPage'>회원 전체 일반 통계</a>
			<a href='AdminActivityAgeLogPage'>연령대 기준 조회</a>
			<a href='AdminActivitySexLogPage'>성별 기준 조회</a>
		</div>
</body>
</html>