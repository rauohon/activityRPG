<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/writingMessage.css" media="screen" type="text/css" />
<script src="js/common.js"></script>
<script>
function message(){
	var form = createForm("messageForm", "getMessageList", "post");
	createObj("hidden", "id", "${id}", "");
	relationObj("messageForm", "id");
	form.submit();
}
</script>
</head>
<body>
	<form id="setMessage" action="setMessage" method="post">
		<div class="head">
			<h2>메시지 보내기</h2>
		</div>
		<div class="container">
			<p>보내는 사람 : ${id }</p>
			<input type="text" id="a" name="mbid" placeholder="받는 사람" /><br />
			<input type="text" id="b" name="msgText" placeholder="내용" /> <br />
			<input id="submit" type="submit" value="메시지 보내기 " />
		</div>
	</form>
	<button id="list" onClick="message()">목록</button>
</body>
</html>