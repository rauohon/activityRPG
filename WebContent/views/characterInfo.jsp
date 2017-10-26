<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function startAjax(code){
		$.ajax({
			type: "get",
			url: "ItemInfo",
			data: { chName : '${chName}', itcode:code},							// 전달 값
			dataType: "html",										// json, xml, html(text): 안쓰면 html
			timeout : "5000",										// 타임아웃
			success : function(data) {							// 성공
				console.log(data);
				$("#ajax_div").append(data);
			},
			error : function( error ) {							// 실패
				alert( "error" );
				console.log(error);
			}
		});
	}
// 	function hideDiv(){
// 	사라지게 하기
// 		$("#ajax_div").fadeOut();
// 	}
</script>
<style>
	table{
            text-align: center;
        }
</style>
</head>
<body>
	<h3>
		<a href='BackPage'>뒤로</a>
	</h3>
	캐릭터 정보 페이지 입니다.
	<h4>캐릭터 사진</h4>
	${characterImage }
	<div id='ajax_div'></div>
	<h4>캐릭터 이름 : ${chName }</h4>
	<h5>캐릭터 레벨 : ${chLevel }</h5>
	<h5>경험치 : ${chExp }</h5>
	<h5>체력 : ${chHp }</h5>
	<h5>마나 : ${chMp }</h5>
	<h5>힘 : ${chStr }</h5>
	<h5>민첩 : ${chDex }</h5>
	<h5>지능 : ${chInt }</h5>
	<h5>공격력 : ${chAttack }</h5>
	<h5>방어 : ${chDefense }</h5>
	<h5>소지금 : ${chGold }</h5>
	<h4>착용아이템</h4>
	<h5>무기 : ${weapon } ${weaponEn }</h5>
	<h5>갑옷 : ${armor } ${armorEn }</h5>
	<h5>장갑 : ${glove } ${gloveEn }</h5>
	<h5>신발 : ${shoe } ${shoeEn }</h5>
	<h5>반지 : ${ring } ${ringEn }</h5>
	<h5>목걸이 : ${necklace } ${necklaceEn }</h5>
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