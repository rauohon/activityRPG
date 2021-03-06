<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
   <style>
   @import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
   
   .button {
   	font-family: 'Jeju Hallasan', serif;
   	font-size:30px;
	background: none;
	border: 3px solid #fff;
	border-radius: 5px;
	color: #fff;
	text-transform: uppercase;
}
.button:hover {
	border: 3px solid #f68a6f;
	background: #f68a6f;
}
.buttons {
	background: none;
	border: 3px solid #fff;
	border-radius: 5px;
	color: #fff;
	text-transform: uppercase;
}
.buttons:hover {
	border: 3px solid #f68a6f;
	background: #f68a6f;
}
   </style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	$(document).ready(function() {	
		//이동 버튼을 누를 때 이벤트 처리(보류)
		$("button[name=moveValue]").click(function() {
			var msg = $(this).val();
			var forms = $("<form />");
			forms.attr("name", "movement");
			forms.attr("action", "Movement");
			forms.attr("method", "post");
			var hiddenBox = $("<input />");
			hiddenBox.attr("type","hidden");
			hiddenBox.attr("name","moveValue");
			hiddenBox.attr("value",msg);
			$("body").append(forms);
			$("form[name=movement]").append(hiddenBox);
			forms.submit();
		});
	});
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
	
	//************************김훈***************************
	var getExp = "${getExp}";
	var getGold = "${getGold}";
	var getItem = "${getItem}";
	var getLevel = "${characterLevel}";
	var monsterName = "${monsterName}";
	var monsterCode = "${monsterCode}";
		
	if(monsterName != ""){
		alert(monsterName + "을 쓰러뜨렸습니다.");
	}
	if(getExp != "" && getGold != ""){
		alert("당신은 " + getExp + "의 경험치를 획득하셨습니다.");
		alert("당신은 " + getGold + "골드를 획득하셨습니다.");
	}
	if(getExp >= 100 && getExp < 200){
		alert("축하합니다! 레벨이 " + getLevel + "로 증가했습니다.");
		alert("새로운 스킬 뇌광권을 습득하셨습니다.");
	}
	if(getItem != ""){
		alert(getItem);
	}
	//************************김훈***************************
}
</script>
</head>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<h3><a href='BackPage' class='buttons'>뒤로가기</a></h3>
		<div id='dungeonImage' style='border:1px solid black;'><img src='images/dungeon.png' alt='던전문' style="width: 100%;"/></div>
		<div id='dungeonTeleport' style="margin-top:1%; padding-left:20%; width:500px; float: left;">
			<div style="float: left;">
				<button class='button' onClick="total('gameForm', 'GameForm', 'post')" style='height: 150px; width: 100%;'>마을로 이동</button>
			</div>
			<div>
				<button class='button' onClick="total('characterinfo', 'CharacterInfo', 'get')" style='height: 70px; margin-left: 3%; width: 50%;'>캐릭터 정보</button><br/>
				<button class='button' onClick="total('myquestlist','MyQuestList','POST')" style='height: 70px; margin-top:10px; margin-left: 3%; width: 50%;'>퀘스트 정보</button>
			</div>
		</div>
		<div id='dungeonController' style="margin-top:1%; margin-left:60%">
			<div id = 'dungeonConFront' style="margin-left:30%;">
				<button class='button' name='moveValue' value="2" style='border-radius: 50%; height: 50px; width: 60px;'>▲</button><br/>
			</div>
			<div id='dungeonConLefRigh' style="margin-left:18%;">
				<button class='button' name='moveValue' value='4' style='border-radius: 50%; height: 50px; width: 60px;'>◀</button>
				<button class='button' name='moveValue' value='5' style='border-radius: 50%; height: 50px; width: 60px;margin-left: 13.8%;'>▶</button><br/>
			</div>
		</div>
	</div>
</body>
</html>