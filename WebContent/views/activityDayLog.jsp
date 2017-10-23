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
<h1>activityDayLog 페이지 입니다.</h1>
<h2>내 운동량</h2>
<h3>오늘의 운동량</h3>
${todayActivity }
<h3>획득 가능한 경험치</h3>
${applicableExp }
<a href="#">경험치로 바꾸기</a>
<h3>획득했던 경험치</h3>
${appliedExpIndi }
<a href="ActivityWeekLogPage">자세히 보기</a>
<h3>오늘 얼마나 걸었니 그래프</h3>
${encourageTodayStepData }/${encourageYesterStepData }
<h3>오늘 몇층을 올랐니 그래프</h3>
${encourageTodayFloorData }/${encourageYesterFloorData }
</body>
</html>