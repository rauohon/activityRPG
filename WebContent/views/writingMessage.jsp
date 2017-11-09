<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="css/writingMessage.css" media="screen" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
function message(){
	var form = createForm("messageForm", "getMessageList", "post");
	createObj("hidden", "id", "${id}", "");
	relationObj("messageForm", "id");
	form.submit();
}
</script>
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
	<form id="setMessage" action="setMessage" method="post">

		<div style="padding-top: 60px">
			<div class="head">
				<h2>메시지 보내기</h2>
			</div>
			<div class="container">
				<p>보내는 사람 : ${id }</p>
				<input type="text" id="a" name="mbid" placeholder="받는 사람" /><br />
				<input type="text" id="b" name="msgText" placeholder="내용" /> <br />
				<input id="submit" type="submit" value="메시지 보내기 " />
			</div>
		</div>
	</form>
	<button id="list" onClick="message()">목록</button>
</body>
</html>