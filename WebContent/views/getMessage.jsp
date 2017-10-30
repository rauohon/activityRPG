<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/getMessage.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
function total(formname, action, method){
	var form = createForm(formname, action, method);
	
	createObj("hidden", "id", "${id}", "");
	
	relationObj("mainForm", "id");
	relationObj("msgForm", "id");
	relationObj("boardForm", "id");
	relationObj("gameForm", "id");
	relationObj("messageForm", "id");
	relationObj("sendmsgForm", "id");
	form.submit();
}
</script>
</head>
<body>
<div id="layer_fixed">
		<table cellspacing="0" cellpadding="0">
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
	<div></br></br>
	<h3>받은 메시지 함</h3>
	<button id="click" onClick="total('messageForm', 'writingMessage', 'post')">메시지 쓰기</button>
	<button id="click" onClick="total('sendmsgForm', 'sendmessage', 'post')">보낸 메시지</button>
	</div>
	<div>
	${messagelist }
	</div>
	<div>
	<input type="submit" value="삭제">
	</div>
</body>
</html>