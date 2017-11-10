<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		float: left;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
			<div id="chart_area">
				<div id="chart1" style="width: 50%; height:60%;">
					<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
					<canvas id="myChart1" width="85%" height="50%"></canvas>
					<script type="text/javascript">
					var ctx = document.getElementById('myChart1').getContext('2d');
					var stepChartOption = {
							legend:{
								labels:{
									fontColor:"white"
								}
							},
							  scales: {
									xAxes:[{
										ticks:{
											fontColor:"white",
											fontSize:20
										}
									}],
							    yAxes: [{
							      gridLines: {
							    	zeroLineColor: "white",
							        display: true,
							        color:"white"
							      },
							      ticks: {
							    	  	fontColor:"white",
								        min: 0,
								        max: 10000,
								        stepSize: 2000
								      }
							    }],
							  }
							};
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["하루 평균 걸음 수"],
					        datasets: [{
					            label: "일일 평균",
					            backgroundColor: 'rgba(0, 216, 255,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					            data: [${avgActivityAllUser}[0].step]
					        }]
					    },
					    // Configuration options go here
					    options: stepChartOption
					});
					</script>
				</div>
				<div id="chart2" style="width: 50%; height:60%;">
					<canvas id="myChart2" width="85%" height="50%"></canvas>
					<script type="text/javascript">
					var ctx = document.getElementById('myChart2').getContext('2d');
					var stepChartOption = {
							legend:{
								labels:{
									fontColor:"white"
								}
							},
							  scales: {
									xAxes:[{
										ticks:{
											fontColor:"white",
											fontSize:20
										}
									}],
							    yAxes: [{
							      gridLines: {
							    	zeroLineColor: "white",
							        display: true,
							        color:"white"
							      },
							      ticks: {
							    	  	fontColor:"white",
								        min: 0,
								        max: 10,
								        stepSize: 2
								      }
							    }],
							  }
							};
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["하루 평균 층 수"],
					        datasets: [{
					            label: "일일 평균",
					            backgroundColor: 'rgba(255, 99, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					            data: [${avgActivityAllUser}[0].floor]
					        }]
					    },
					    // Configuration options go here
					    options: stepChartOption
					});
					</script>
				</div>
				<div id='chart3' style="width: 50%; height:60%;">
					<canvas id="myChart3" width="85%" height="50%"></canvas>
					<script type="text/javascript">
					var ctx = document.getElementById('myChart3').getContext('2d');
					var stepChartOption = {
							legend:{
								labels:{
									fontColor:"white"
								}
							},
							  scales: {
									xAxes:[{
										ticks:{
											fontColor:"white",
											fontSize:20
										}
									}],
							    yAxes: [{
							      gridLines: {
							    	zeroLineColor: "white",
							        display: true,
							        color:"white"
							      },
							      ticks: {
							    	  	fontColor:"white",
								        min: 0,
								        max: 60000,
								        stepSize: 10000
								      }
							    }],
							  }
							};
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["누적 걸음 수"],
					        datasets: [{
					            label: "걸음 수 누적",
					            backgroundColor: 'rgba(0, 216, 255,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					            data: [${activityAllUser}[0].step]
					        }]
					    },
					    // Configuration options go here
					    options: stepChartOption
					});
					</script>
				</div>
				<div id='chart4' style="width: 50%; height:60%;">
					<canvas id="myChart4" width="85%" height="50%"></canvas>
					<script type="text/javascript">
					var ctx = document.getElementById('myChart4').getContext('2d');
					var stepChartOption = {
							legend:{
								labels:{
									fontColor:"white"
								}
							},
							  scales: {
									xAxes:[{
										ticks:{
											fontColor:"white",
											fontSize:20
										}
									}],
							    yAxes: [{
							      gridLines: {
							    	zeroLineColor: "white",
							        display: true,
							        color:"white"
							      },
							      ticks: {
							    	  	fontColor:"white",
								        min: 0,
								        max: 50,
								        stepSize: 10
								      }
							    }],
							  }
							};
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["누적 층 수"],
					        datasets: [{
					            label: "층 수 누적",
					            backgroundColor: 'rgba(255, 99, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					            data: [${activityAllUser}[0].floor]
					        }]
					    },
					    // Configuration options go here
					    options: stepChartOption
					});
					</script>
				</div>
			</div>
		</div>
</body>
</html>