<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	function setexp(code){
		var f = $("#fixForm");
		var exp = $("<input />");
		f.attr("name", "setexp");
		f.attr("action", "SetExp");
		f.attr("method", "POST");
		exp.attr("type","hidden");
		exp.attr("name","exp");
		exp.attr("value",code);
		$("#fixForm").append(exp);
		f.submit();
	}
	function activityweeklogpage(){
		var f = $("#fixForm");
		f.attr("name", "activityWeekLogPage");
		f.attr("action", "ActivityWeekLogPage");
		f.attr("method", "POST");
		f.submit();
	}
</script>
</head>
<body>
<h1>activityDayLog 페이지 입니다.</h1>
<br/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
<h2>내 운동량</h2>
<h3>오늘의 운동량</h3>
<div id='chart' style="width: 30%; text-align: center;">
	<canvas id="myChart1" width="100%" height="30%"></canvas>
	<canvas id="myChart2" width="100%" height="30%"></canvas>
	<script>
	$("#chart").append(${todayActivity}[0].step + " / " + ${yesterdayActivity}[0].step + "\t");
	$("#chart").append(${todayActivity}[0].floor + " / " + ${yesterdayActivity}[0].floor);
var ctx = document.getElementById('myChart1').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',
    // The data for our dataset
    data: {
        labels: ["걸음 수"],
        datasets: [{
            label: "오늘",
            backgroundColor: 'rgba(255, 99, 132,0.5)',
            borderColor: 'rgb(54, 162, 235, 0.2)',
            borderWidth: 1,
            data: [${todayActivity}[0].step]
        },{
            label: "어제",
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgb(255, 99, 132)',
            borderWidth: 1,
            data: [${yesterdayActivity}[0].step]
        }]
    },
    // Configuration options go here
    options: {
    }
});
var ctx = document.getElementById('myChart2').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',
    // The data for our dataset
    data: {
        labels: ["계단 사용 층 수"],
        datasets: [{
            label: "오늘",
            backgroundColor: 'rgba(255, 99, 132,0.5)',
            borderColor: 'rgb(54, 162, 235, 0.2)',
            borderWidth: 1,
            data: [${todayActivity}[0].floor]
        },{
            label: "어제",
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgb(255, 99, 132)',
            borderWidth: 1,
            data: [${yesterdayActivity}[0].floor]
        }]
    },
    // Configuration options go here
    options: {}
});
</script>
</div>
<h3>획득 가능한 경험치</h3>
${applicableExp }
<h3>획득했던 경험치</h3>
${appliedExpIndi }
<button onClick="activityweeklogpage()">일주일 기록 보기</button>
<form id="fixForm"></form>
</body>
</html>