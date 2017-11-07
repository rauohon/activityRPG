<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!-- <script src="//www.google.com/jsapi"></script> -->
<script>
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
var datas =${todayActivity };
var steps = datas.step; 
var floors = datas.floor; 
alert(steps);
alert(floors);
var data = new google.visualization.arrayToDataTable(datas);
var options = {'title':'Hot',
        'width':400,
        'height':300};
	var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}
</script>
</head>
<body>
<h1>activityDayLog 페이지 입니다.</h1>
<h2>내 운동량</h2>
<h3>오늘의 운동량</h3>
<div id='chart_div'></div>
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