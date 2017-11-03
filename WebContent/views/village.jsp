<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<style type="text/css">
#chatGuildUserArea {
	display: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/chat.js"></script>
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
				$("#chatEveryUserArea").css({
					"display" : "block"
				});
			} else {
				$("#chatGuildUserArea").css({
					"display" : "block"
				});
				$("#chatEveryUserArea").css({
					"display" : "none"
				});
			}
			;
		});
	});
	function createForm(formName, actionName, method) {
		var f = $("#fixForm");
		var i = $("<input />");
		f.attr("name", formName);
		f.attr("action", actionName);
		f.attr("method", method);
		i.attr("type", "hidden");
		i.attr("name", "id");
		i.attr("value", '${id }');
		$("#fixForm").append(i);

		f.submit();
	}
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<div id='chatArea' style="width:30%; float: left; height: 60%; margin-left: 80px;">
			<h1>마을 페이지</h1>
			<div id="chatEveryUserArea" style="border: 1px solid black; height:600px;">
				<h4>전체 채팅</h4>
				<div id="chatEveryUserMsgArea"></div>
			</div>
			<div id="chatGuildUserArea" style="border: 1px solid black; height:600px;">
				<h4>길드 채팅</h4>
				<div id="chatGuildUserMsgArea"></div>
			</div>
			<div id='chatTalkArea' style='height: 75px;'>
			<p style='text-align: center;'>
				<select id='jobCode'  style='margin-top:15px; height: 40px; width: 14%'>
					<option value="0">전체</option>
					<option value="1">길드</option>
				</select>
				<input type="text" id="message" placeholder="전송할 메시지"  style="height: 40px;width: 60%;" />
				<input type="button" id="sendBtn" value="전송"  style='height: 45px; width: 20%;'/>
			</p>
			</div>
		</div>
		<div id='teleportWraper' style='margin-left: 40%; padding-left: 20px; margin-top: 5%;'>
			<div id='charaInfo'>
					<input type="button" value='캐릭터 정보' onClick="createForm('characterinfo','CharacterInfo','GET')" style='height: 100px; width: 39%;'/>
					<input type="button" value='퀘스트' style='height: 100px; width: 39%; margin-left: 1%;'/>
			</div>
			<div id='go_guild'>
				<input type="button" value='길드' style='height: 100px; width: 79.5%; margin-top: 1%;'/>
			</div>
			<div id='go_dungeon'>
				<input type="button" value='던전 이동' onClick="createForm('dungeonpage','DungeonPage','POST')" style='height: 100px; width: 79.5%; margin-top: 1%;'/>
			</div>
			<div id='go_shop'>
				<input type="button" value='무기 상점' style='height: 100px; width: 18.4%; margin-top: 1%;'/>
				<input type="button" value='방어구 상점' style='height: 100px; width: 18.3%; margin-left: 10px; margin-top: 1%;'/>
				<input type="button" value='포션 상점' style='height: 100px; width: 18.3%; margin-left: 10px; margin-top: 1%;'/>
				<input type="button" value='강화 장점' style='height: 100px; width: 18.4%; margin-left: 10px; margin-top: 1%;'/>
			</div>
			<div id='go_ranking'>
				<input type="button" value='랭킹' style='height: 100px; width: 79.5%; margin-top: 1%;'/>
			</div>
				<form id="fixForm"></form>
		</div>
	</div>
</body>
</html>