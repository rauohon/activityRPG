<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		//이동 버튼을 누를 때 이벤트 처리(보류)
		$("button").click(function() {
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
			document.movement.submit();
		});
	});
</script>
</head>
<body>
<h1>Dungeon 페이지 입니다.</h1>
<h3><a href='BackPage'>뒤로</a></h3>
<div id='dungeonImage' style='border:1px solid black;'>${dungeonImage }</div>
<h4><a href='GameForm'>마을로 이동</a></h4>
<h4><a href='CharacterInfo'>캐릭터 정보</a></h4>
<h4><a href='QuestPage'>퀘스트 정보</a></h4>
<button name='moveValue' value="2">앞쪽으로</button>
<button name='moveValue' value='4'>왼쪽으로</button>
<button name='moveValue' value='5'>오른쪽으로</button>
<button name='moveValue' value='3'>뒤쪽으로</button>
</body>
</html>