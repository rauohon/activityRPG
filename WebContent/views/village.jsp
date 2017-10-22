<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/chat.js"></script>
</head>
<body>
이름:<input type="text" id="nickname"/>
	<input type="button" id="enterBtn" value="입장"/>
	<input type="button" id="exitBtn" value="퇴장"/>
	
	<h1>채팅 영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	전송할 메시지:<input type="text" id="message"/>
	<input type="button" id="sendBtn" value="전송"/>
	<select id='jobCode'>
		<option value="0">전체</option>
		<option value="1">길드</option>
	</select>
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