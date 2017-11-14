<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.act{	
	border: 3px solid #fff;
	border-radius: 5px;
	margin-left: 52%;
	margin-right: 15%;
	padding:2%;
}
.active{
	display: "";
}
.act:hover{
	border: 3px solid #f68a6f;
	background: #f68a6f;	
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
$(document).ready(function() {
	$("h1").on({
		mouseenter: function(){
			$("#showDetail").css({"display":"block", "border":"3px solid #fff", "border-radius": "5px", "margin-top":"2%" , "margin-left": "52%","margin-right": "15%", "padding":"2%"});
		},
		mouseleave: function(){
			$("#showDetail").css("display","none");
		}
	});
});
</script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
	<body onLoad='init()'>
		<%@ include file="nav.jsp"%>
		<div id='wraper' style="padding-top: 60px; padding-left: 5%;">
			<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
			<div style='width: 50%; text-align: center; float: left;'>
				일주일 걸음 / 계단을 이용한 층 수
				<div id='chart1'>
					<canvas id="myChart1" width="100%" height="40%"></canvas>
					<script>	
					var dataArray = new Array();
					dataArray = ${activityWeekActData}
					// for(var i in dataArray){
					// }
					var ctx = document.getElementById('myChart1').getContext('2d');
					var stepOption = {legend:{
						labels:{
							fontColor:"white",
							fontSize:15
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
								        max: 20000,
								        stepSize: 5000
								      }
							    }],
							  }
							};
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["일주일 동안 걸음수"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[0].step]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[1].step]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[2].step]
					        },
					        {
					        	label:dataArray[3].date,
					        	backgroundColor: 'rgba(255, 132, 255,1)',
					            borderColor: 'rgb(162, 54, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[3].step]
					        },
								{
					        	label:dataArray[4].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[4].step]
					        },
									{
					        	label:dataArray[5].date,
					        	backgroundColor: 'rgba(132, 255, 255 ,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[5].step]
					        }
// 					        ,{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,1)',
					//             borderColor: 'rgb(54, 162, 235, 1)',
					//             borderWidth: 1,
					//            data:[dataArray[6].step]
					//         }
					        ]
					    },
					    // Configuration options go here
					    options: stepOption
					});
					</script>
				</div>
				<div id='chart2'>					
					<canvas id="myChart2" width="100%" height="40%"></canvas>
					<script type="text/javascript">
					var floorOption = {legend:{
						labels:{
							fontColor:"white",
							fontSize:15
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
								        max: 30,
								        stepSize: 10
								      }
							    }],
							  }
							};
					var ctx = document.getElementById('myChart2').getContext('2d');
					var chart = new Chart(ctx, {
					    // The type of chart we want to create
					    type: 'bar',
					    // The data for our dataset
					    data: {
					        labels: ["일주일 동안 계단을 이용한 층 수"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[0].floor]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[1].floor]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[2].floor]
					        },
					        {
					        	label:dataArray[3].date,
					        	backgroundColor: 'rgba(255, 132, 255,1)',
					            borderColor: 'rgb(162, 54, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[3].floor]
					        },
					        {
					        	label:dataArray[4].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[4].floor]
					        },
					{
					        	label:dataArray[5].date,
					        	backgroundColor: 'rgba(132, 255, 255 ,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[5].floor]
					        },
// 					{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,1)',
					//             borderColor: 'rgb(54, 162, 235, 1)',
					//             borderWidth: 1,
					//            data:[dataArray[6].floor]
					//         }
					        ]
					    },
					    // Configuration options go here
					    options: floorOption
					});
					</script>
				</div>

				<div id='chart3'>
					<canvas id="myChart3" width="100%" height="40%"></canvas>
					<script type="text/javascript">
					var dataArray = new Array();
					dataArray = ${activityWeekExpData }
					var ctx = document.getElementById('myChart3').getContext('2d');
					var expChartOption = {legend:{
						labels:{
							fontColor:"white",
							fontSize:15
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
								        max: 6000,
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
					        labels: ["일주일 동안 전환한 경험치"],
					        datasets: [{
					        	label:dataArray[0].date,
					        	backgroundColor: 'rgba(255, 160, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[0].exp]
					        },{
					        	label:dataArray[1].date,
					        	backgroundColor: 'rgba(255, 99, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[1].exp]
					        },{
					        	label:dataArray[2].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[2].exp]
					        },
					        {
					        	label:dataArray[3].date,
					        	backgroundColor: 'rgba(255, 132, 255,1)',
					            borderColor: 'rgb(162, 54, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[3].exp]
					        },
					        {
					        	label:dataArray[4].date,
					        	backgroundColor: 'rgba(255, 255, 132,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[4].exp]
					        },
					        {
					        	label:dataArray[5].date,
					        	backgroundColor: 'rgba(132, 255, 255 ,1)',
					            borderColor: 'rgb(54, 162, 235, 1)',
					            borderWidth: 1,
					           data:[dataArray[5].exp]
					        },
// 					{
					//         	label:dataArray[6].date,
					//         	backgroundColor: 'rgba(255, 255, 132,1)',
					//             borderColor: 'rgb(54, 162, 235, 1)',
					//             borderWidth: 1,
					//            data:[dataArray[6].exp]
					//         }
					        ]
					    },
					    // Configuration options go here
					    options: expChartOption
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