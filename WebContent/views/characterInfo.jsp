<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
<h3><a href='BackPage'>뒤로</a></h3>
캐릭터 정보 페이지 입니다.
<h4>캐릭터 능력치</h4>
${characterStatus }
<h4>경험치</h4>
${characterExp }
<h4>착용아이템</h4>
${equipedList }
<h3>소지아아템</h3>
<h4>무기</h4>
${weaponItemList }
<h4>방어구</h4>
${armorItemList }
<h4>포션</h4>
${potionItemList }
<h4>강화석</h4>
${ehanceItemList }
</body>

</html>