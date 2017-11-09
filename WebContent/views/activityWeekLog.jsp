<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
	<body>
		<%@ include file="nav.jsp"%>
		<div id='wraper' style="padding-top: 60px; padding-left: 5%;">
			<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
			<div style='width: 50%; text-align: center; float: left;'>
				일주일 걸음 / 계단을 이용한 층 수
				<div id='chart1_2'>
					<canvas id="myChart1" width="100%" height="40%"></canvas>
					<canvas id="myChart2" width="100%" height="40%"></canvas>
					<script>	
					var dataArray = new Array();
					dataArray = ${activityWeekActData}
					// for(var i in dataArray){
					// }
					var ctx = document.getElementById('myChart1').getContext('2d');
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["걸음수"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[0].step]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[1].step]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[2].step]
					        },
					        {
					        	label:dataArray[3].date,
					        	backgroundColor: 'rgba(255, 132, 255,0.5)',
					            borderColor: 'rgb(162, 54, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[3].step]
					        }
					// 			{
					//         	label:dataArray[4].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[4].step]
					//         },{
					//         	label:dataArray[5].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[5].step]
					//         },{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[6].step]
					//         }
					        ]
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
					        labels: ["계단을 이용한 층 수"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[0].floor]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[1].floor]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[2].floor]
					        },
					        {
					        	label:dataArray[3].date,
					        	backgroundColor: 'rgba(255, 132, 255,0.5)',
					            borderColor: 'rgb(162, 54, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[3].floor]
					        },
					//         {
					//         	label:dataArray[4].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[4].floor]
					//         },{
					//         	label:dataArray[5].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[5].floor]
					//         },{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[6].floor]
					//         }
					        ]
					    },
					    // Configuration options go here
					    options: {
					    }
					});
					</script>
				</div>
				일주일 경험치 전환 내역
				<div id='chart3'>
					<canvas id="myChart3" width="100%" height="40%"></canvas>
					<script type="text/javascript">
					var dataArray = new Array();
					dataArray = ${activityWeekExpData }
					var ctx = document.getElementById('myChart3').getContext('2d');
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["전환한 경험치"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[0].exp]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[1].exp]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,0.5)',
					            borderColor: 'rgb(54, 162, 235, 0.2)',
					            borderWidth: 1,
					           data:[dataArray[2].exp]
					        },
					//         {
					//         	label:dataArray[3].date,
					//         	backgroundColor: 'rgba(255, 132, 255,0.5)',
					//             borderColor: 'rgb(162, 54, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[3].exp]
					//         },
					//         {
					//         	label:dataArray[4].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[4].exp]
					//         },{
					//         	label:dataArray[5].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[5].exp]
					//         },{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,0.5)',
					//             borderColor: 'rgb(54, 162, 235, 0.2)',
					//             borderWidth: 1,
					//            data:[dataArray[6].exp]
					//         }
					        ]
					    },
					    // Configuration options go here
					    options: {
					    }
					});
					</script>
				</div>
			</div>
			<br>
			<div>
				${activityAllData }
			</div>
		</div>
	</body>
</html>