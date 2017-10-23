<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
<h1>AdminActivityLogPage 입니다.</h1>
<h2>회원 전체 일일 평균 걸음 수</h2>
${avgStepAllUser }
<h2>회원 전체 일일 평균 오른 층 수</h2>
${avgFloorAllUse }
<h2>회원 전체 누적 걸음 수</h2>
${stepAllUser }
<h2>회원 전체 누적 오른 층 수</h2>
${floorAllUser }
<a href='AdminActivityLogPage'>회원 전체 일반 통계</a>
<a href='AdminActivityAgeLogPage'>연령대 기준 조회</a>
</body>
</html>