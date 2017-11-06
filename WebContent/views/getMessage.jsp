<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
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

function messageDelete(mbid, msgText){
	alert(mbid);
	alert(msgText);
	var form = createForm("messageDeleteForm", "messageDelete", "post");
	createinput("hidden", "mbid", mbid);
	createinput("hidden", "msgText", msgText);
	relationObj("messageDeleteForm", "mbid");
	relationObj("messageDeleteForm", "msgText");
	form.submit();
}
</script>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<h3>받은 메시지 함</h3>
	<button id="click" onClick="total('messageForm', 'writingMessage', 'post')">메시지 쓰기</button>
	<button id="click" onClick="total('sendmsgForm', 'sendmessage', 'post')">보낸 메시지</button>

	<div>
	${messagelist }
	</div>
	</div>
</body>
</html>