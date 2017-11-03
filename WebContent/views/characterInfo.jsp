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
	function itemuse(code){
		var f = $("#fixForm");
		var i = $("<input />");
		var chName = $("<input />")
		f.attr("name", "itemUse");
		f.attr("action","ItemUse");
		f.attr("method","POST");
		i.attr("name","itcode");
		chName.attr("name", "chName");
		i.attr("value",code);
		chName.attr("value",'${chName}');
		i.attr("type","hidden");
		chName.attr("type","hidden");
		$("#fixForm").append(i);
		$("#fixForm").append(chName);
		
		f.submit();
	}
	function itemdisarm(code){
		var f = $("#fixForm");
		var i = $("<input />");
		var chName = $("<input />")
		f.attr("name", "itemDisArm");
		f.attr("action","ItemDisArm");
		f.attr("method","POST");
		i.attr("name","itcode");
		chName.attr("name", "chName");
		i.attr("value",code);
		chName.attr("value",'${chName}');
		i.attr("type","hidden");
		chName.attr("type","hidden");
		$("#fixForm").append(i);
		$("#fixForm").append(chName);
		
		f.submit();
	} 
</script>
<style>
	table{
	           text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
		<div id='charaInfo' style='float: left; width:40%; margin-left: 2%; margin-right: 2%;'>
			<div id='charaPhoto' style='float: left; width:50%; color: white;'>
				<h4>캐릭터 사진</h4>
				<img alt="테스트" src="images/skull_Icon.png" width="50%" height="50%">
<%-- 				${characterImage } --%>
			</div>
			<div id='charaGeneInfo' style="color: white;">
				<h4>캐릭터 이름 : ${chName }</h4>
				<h5>캐릭터 레벨 : ${chLevel }</h5>
			</div>
			<div id='charaStatus' style="margin-top: 12px; color: white;">
				<table style="width: 100%;">
					<tr>
						<th>경험치</th><td> ${chExp }</td>
					</tr>
					<tr>
						<th>체력</th><td> ${chHp }</td>
					</tr>
					<tr>
						<th>마나</th><td>  ${chMp }</td>
					</tr>
					<tr>
						<th>힘</th><td> ${chStr }</td>
					</tr>
					<tr>
						<th>민첩</th><td> ${chDex }</td>
					</tr>
					<tr>
						<th>지능</th><td>${chInt }</td>
					</tr>
					<tr>
						<th>공격력</th><td>${weaponAbility }</td>
					</tr>
					<tr>
						<th>방어력</th><td>${armorAbility }</td>
					</tr>
				</table>
			</div>
			<div style="margin-top: 12px; background-image: url('images/people_Icon.png'); color: white; height: 455px;">
				<br/>
				<div style='margin-left: 35%; margin-top: 10%;'>
					<h5>${necklace } ${necklaceEn }</h5>
				</div>
				<div style='margin-top: 20%;'>
					<div style='float: left; margin-left: 19%;'>
						<h5>${glove } ${gloveEn }</h5>
					</div>
					<div style='float: left; margin-left: 2%;'>
						<h5>${armor } ${armorEn }</h5>
					</div>
				</div>
				<br/><br/><br/>
				<div style='float: left; margin-left: 25%'>
					<h5>${weapon }${weaponEn }</h5>
				</div>
				<div style='margin-left: 60%;'>
					<h5> ${ring } ${ringEn }</h5>
				</div>
				<div style='margin-left: 38%; margin-top: 14%;'>
					<h5>${shoe } ${shoeEn }</h5>
				</div>
			</div>
		</div>
		<div id='inventory' style="margin-left: ; color: white; float:left; margin-right: 2%;">
			<h4>소지금 : ${chGold }</h4>
			<div style='margin-top:4%;'>
				<h4>무기</h4>
				${weaponItemList }
			</div>
			<div style='margin-top:4%;'>
				<h4>방어구</h4>
				${armorItemList }
			</div>
			<div style='margin-top:4%;'>
				<h4>포션</h4>
				${potionItemList }
			</div>
			<div style='margin-top:4%;'>
				<h4>강화석</h4>
				${ehanceItemList }
			</div>
		</div>
		<h3 style="text-align: right;"><a href='BackPage'>뒤로</a></h3>
		<div id='ajax_div' style="color: white;" >${msg}</div>
	</div>
	
	<form id="fixForm"></form>
</body>
</html>