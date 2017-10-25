<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#chatGuildUserArea {
	display: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/chat.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var nickName = $("<input />");
		nickName.attr("type", "hidden");
		nickName.attr("value", '${id}');
		nickName.attr("id", "nickname");
		$("body").append(nickName);
		$("#jobCode").click(function() {
			if ($("#jobCode").val() == "0") {
				$("#chatGuildUserArea").css({
					"display" : "none"
				});
				$("#chatEveryUserArea").css({"display" : "block"});
			} else {
				$("#chatGuildUserArea").css({"display" : "block"});
				$("#chatEveryUserArea").css({"display" : "none"});
			};
		});
	});
</script>
<script type="text/javascript">
function createForm(formName, actionName, method) {
	alert(formName);
	alert(actionName);
	alert(method);
	var f = $("#fixForm");
	var i = $("<input />");
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	i.attr("type","hidden");
	i.attr("name","id");
	i.attr("value",'${id }');
	$("#fixForm").append(i);
	
  document.characterinfo.submit();
}
</script>
</head>
<body>
	<h1>마을 페이지</h1>
	<div id="chatEveryUserArea" style="border: 1px solid black;">
		<h4>전체 채팅</h4>
		<div id="chatEveryUserMsgArea"></div>
	</div>
	<div id="chatGuildUserArea" style="border: 1px solid black;">
		<h4>길드 채팅</h4>
		<div id="chatGuildUserMsgArea"></div>
	</div>
	전송할 메시지:
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
	<select id='jobCode'>
		<option value="0">전체</option>
		<option value="1">길드</option>
	</select>
	<input type ="button" value='캐릭터 정보' onClick="createForm('characterinfo','CharacterInfo','POST')" />
	<a href='QuestPage'>퀘스트</a>
	<a href='GuildPage'>길드</a>
	<a href='DungeonPage'>던전 이동</a>
	<a href='ShopPage'>무기 상점</a>
	<a href='ShopPage'>방어구 상점</a>
	<a href='ShopPage'>포션 상점</a>
	<a href='ShopPage'>강화 상점</a>
	<a href='RankingPage'>랭킹</a>
	<form id="fixForm"></form>
</body>
</html>