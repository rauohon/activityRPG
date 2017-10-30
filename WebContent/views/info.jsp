<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/info.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
function total(formname, action, method){
	var form = createForm(formname, action, method);
	
	createObj("hidden", "id", "${id}", "");
	
	relationObj("mainForm", "id");
	relationObj("msgForm", "id");
	relationObj("boardForm", "id");
	relationObj("gameForm", "id");
	relationObj("activityDayLogForm", "id");
	relationObj("enrollRaspberryPiForm", "id");
	form.submit();
}

$("#thebutton span").text("My NEW Text");
$("span", this).text("My NEW Text");

</script>
</head>
<body>
	<div id="layer_fixed">
		<table id="table" cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td id="maintext">TEXT-RPG</td>
			</tr> -->
			<tr>
				<td><button onClick="total('mainForm', '/', 'post')" id="bar">MAIN PAGE</button></td>
				<td><button onClick="total('msgForm', 'getMessageList', 'post')" id="bar">MESSAGE</button></td>
				<td><button onClick="total('boardForm', 'board', 'post')" id="bar">BOARD</button></td>
				<td><button onClick="total('gameForm', 'game', 'post')" id="bar">GAME PLAY</button></td>
			</tr>
		</table>
	</div>
	</br></br>
	<h3>나의 정보</h3>
	<form action="InfoUpdate" method="post">
	${userInfo }
	<input type="submit" value="정보 수정" />
	
	<button onClick="total('activityDayLogForm', 'ActivityDayLogPage', 'post')">운동 정보 확인</button>
	<button onClick="total('enrollRaspberryPiForm', 'EnrollRaspberryPiPage', 'post')">내 운동량 측정 등록하기</button>
	
	</form>
</body>
</html>