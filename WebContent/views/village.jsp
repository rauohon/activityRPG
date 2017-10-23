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
<script type="text/javascript">
	$(document).ready(function() {
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
	<a href='CharacterInfo'>캐릭터 정보</a>
	<a href='QuestPage'>퀘스트</a>
	<a href='GuildPage'>길드</a>
	<a href='DungeonPage'>던전 이동</a>
	<a href='ShopPage'>무기 상점</a>
	<a href='ShopPage'>방어구 상점</a>
	<a href='ShopPage'>포션 상점</a>
	<a href='ShopPage'>강화 상점</a>
	<a href='RankingPage'>랭킹</a>

</body>
</html>