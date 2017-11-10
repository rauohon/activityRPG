<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
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
		<h3 style="text-align: right;"><a href='BackPage'>뒤로</a></h3>
		<div id='dungeonImage' style='border:1px solid black;'>${dungeonImage }</div>
		<div id='dungeonTeleport' style="margin-top:30%; padding-left:15%; float: left;">
			<div style=" float: left;">
				<button onClick="total('gameForm', 'GameForm', 'post')" style='height: 150px; width: 200px;'>마을로 이동</button>
			</div>
				<button onClick="total('characterinfo', 'CharacterInfo', 'get')" style='height: 70px; margin-left: 20px; width: 120px;'>캐릭터 정보</button><br/>
				<button style='height: 70px; margin-top:10px; margin-left: 20px; width: 120px;'>퀘스트 정보</button>
		</div>
		<div id='dungeonController' style="margin-top:30%; margin-left:60%">
			<div id = 'dungeonConFront' style="margin-left:30%;">
				<button name='moveValue' value="2" style='border-radius: 50%; height: 50px; width: 60px;'>▲</button><br/>
			</div>
			<div id='dungeonConLefRigh' style="margin-left:18%;">
				<button name='moveValue' value='4' style='border-radius: 50%; height: 50px; width: 60px;'>◀</button>
				<button name='moveValue' value='5' style='border-radius: 50%; height: 50px; width: 60px;margin-left: 13.8%;'>▶</button><br/>
			</div>
			<div id='dungeonConAfter' style="margin-left:30%;">
				<button name='moveValue' value='3' style='border-radius: 50%; height: 50px; width: 60px;'>▼</button>
			</div>
		</div>
	</div>
</body>
</html>