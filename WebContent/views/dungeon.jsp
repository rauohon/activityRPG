<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			forms.attr("method", "get");
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
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<h3 style="text-align: right;"><a href='BackPage'>뒤로</a></h3>
		<div id='dungeonImage' style='border:1px solid black;'>${dungeonImage }</div>
		<div id='dungeonTeleport' style="margin-top:30%; padding-left:15%; float: left;">
			<div style=" float: left;">
				<button onClick="total('gameForm', 'GameForm', 'post')" style='height: 150px; width: 200px;'>마을로 이동</button>
			</div>
				<button onClick="total('characterinfo', 'CharacterInfo', 'post')" style='height: 70px; margin-left: 20px; width: 120px;'>캐릭터 정보</button><br/>
				<button style='height: 70px; margin-top:10px; margin-left: 20px; width: 120px;'>퀘스트 정보</button>
		</div>
		<div id='dungeonController' style="margin-top:30%; margin-left:60%">
			<div id = 'dungeonConFront' style="margin-left:30%;">
				<button name='moveValue' value="2" style='border-radius: 50%; height: 50px; width: 60px;'>▲</button><br/>
			</div>
			<div id='dungeonConLefRigh' style="margin-left:20%;">
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