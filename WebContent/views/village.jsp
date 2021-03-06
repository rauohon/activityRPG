<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/home.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<style type="text/css">
@font-face {
  font-family: 'Jeju Hallasan';
  font-style: normal;
  font-weight: 400;
  src: url(//fonts.gstatic.com/ea/jejuhallasan/v3/JejuHallasan-Regular.eot);
  src: url(//fonts.gstatic.com/ea/jejuhallasan/v3/JejuHallasan-Regular.eot?#iefix) format('embedded-opentype'),
       url(//fonts.gstatic.com/ea/jejuhallasan/v3/JejuHallasan-Regular.woff2) format('woff2'),
       url(//fonts.gstatic.com/ea/jejuhallasan/v3/JejuHallasan-Regular.woff) format('woff'),
       url(//fonts.gstatic.com/ea/jejuhallasan/v3/JejuHallasan-Regular.ttf) format('truetype');
}

#chatGuildUserArea {
	display: none;
}
.button {
	background : rgba(0, 0, 0,0.6);
	font-size:40px;
	font-family: Jeju Hallasan;
	border: 3px solid #fff;
	border-radius: 5px;
	color: #fff;
	text-transform: uppercase;
}
.button:hover {
	border: 3px solid #f68a6f;
	background: rgba(246, 138, 111,0.6);
}
</style>
<script src="js/common.js"></script>
<script src="js/chat.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var guildcode = '${guildCode}';
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
			};
		});
	});
	function movePage(formName, actionName, method) {
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
	
	function EnhanceShopMove(){
		createForm("EnhanceShopMoveForm", "EnhanceShop", "get");
		var form = document.getElementsByName("EnhanceShopMoveForm")[0];
		form.submit();
	}
	
	
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

//서버에서 메시지가 왔을 때 호출되는 함수
function onMessage(evt){
	//서버가 전송한 메시지 가져오기
	var data = evt.data;
	//메시지를 출력
	$('#chatEveryUserMsgArea').append(data + "<br />");
}
</script>
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
<style>
body{
	background-image: url('images/village.png');
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
}
</style>
	<div id='wraper' style="padding-top: 10%;">
		<div id='chatArea' style="width:30%; float: left; height: 60%; margin-left: 80px; background : rgba(0, 0, 0, 0.6);">
			<div id="chatEveryUserArea" style="border: 1px solid black; height:600px;">
				<h4>전체 채팅</h4>
				<div id="chatEveryUserMsgArea" style="font-size:20px;"></div>
			</div>
			<div id="chatGuildUserArea" style="border: 1px solid black; height:600px;">
				<h4>길드 채팅</h4>
				<div id="chatGuildUserMsgArea">
					${guildChat}
				</div>
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
					<button class='button' onClick="movePage('characterinfo','CharacterInfo','GET')" style='height: 100px; width: 39%;'>캐릭터 정보</button>
					<button class='button' onClick="movePage('questall','QuestAll','POST')"  style='height: 100px; width: 39%; margin-left: 1%;'>퀘스트</button>
			</div>
			<div id='go_guild'>
				<button class='button' onClick="movePage('guild','Guild','POST')" style='height: 100px; width: 79.5%; margin-top: 1%;'>길드</button>
			</div>
			<div id='go_dungeon'>
				<button class='button' onClick="movePage('dungeonpage','DungeonPage','POST')" style='height: 100px; width: 79.5%; margin-top: 1%;'>탐험</button>
			</div>
			<div id='go_shop'>
				<button class='button' onClick="movePage('shopequip','ShopEquip','GET')" style='height: 100px; width: 39%; margin-top: 1%;'>아이템 상점</button>
				<button onClick="EnhanceShopMove()" class='button' style='height: 100px; width: 39%; margin-left: 1%;  margin-top: 1%;'>대장간</button>
			</div>
			<div id='go_ranking'>
				<button class='button' onClick="movePage('ranking','Ranking','POST')" style='height: 100px; width: 79.5%; margin-top: 1%;'>랭킹</button>
			</div>
				<form id="fixForm"></form>
		</div>
	</div>
</body>
</html>