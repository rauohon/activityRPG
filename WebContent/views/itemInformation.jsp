<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Information</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- alert창 변경 -->
<script>
	function enhance(){
		createForm("enhanceForm", "Enhance", "post");
		var form = document.getElementsByName("enhanceForm")[0];
		
		var itemCode = document.createElement("input");
		itemCode.type = "hidden";
		itemCode.name = "itemCode";
		itemCode.value = $("#itemCode").data('db');
		
		var requireAbility = document.createElement("input");
		requireAbility.type = "hidden";
		requireAbility.name = "requireAbility";
		requireAbility.value = $("#requireAbility").data('db');
		
		var upAbility = document.createElement("input");
		upAbility.type = "hidden";
		upAbility.name = "upAbility";
		upAbility.value = $("#upAbility").data('db');
		
		var enhanceLevel = document.createElement("input");
		enhanceLevel.type = "hidden";
		enhanceLevel.name = "enhanceLevel";
		enhanceLevel.value = $("#enhanceLevel").data('db');
		
		var itemAmount = document.createElement("input");
		itemAmount.type = "hidden";
		itemAmount.name = "itemAmount";
		itemAmount.value = $("#itemAmount").data('db');
		
		form.appendChild(itemCode);	//아이템 코드
		form.appendChild(requireAbility);	//아이템 요구 능력치
		form.appendChild(upAbility);	//증가량
		form.appendChild(enhanceLevel);	//강화 레벨
		form.appendChild(itemAmount);	//아이템 개수
		
		var material = document.createElement("input");
		material.type = "hidden";
		material.name = "material";
		material.value = $("#material").data('db');
		
		var enHanceChance = document.createElement("input");
		enHanceChance.type = "hidden";
		enHanceChance.name = "enHanceChance";
		enHanceChance.value = $("#enHanceChance").data('db');
		
		var increase = document.createElement("input");
		increase.type = "hidden";
		increase.name = "increase";
		increase.value = $("#increase").data('db');
		
		form.appendChild(material);	//요구 강화석
		form.appendChild(enHanceChance);	//강화 확률
		form.appendChild(increase);	//증가량
		
		form.submit();
	}
</script>
<style>
#currentItemDiv {
	width: 400px;
	height: 220px;
	padding: 0px 0px 0px 35px;
}
#enhanceItemDiv {
	width: 400px;
	height: 220px;
	padding: 0px 0px 0px 35px;
	position: absolute;
	left: 400px;
	top: 10px;
}

#currentItemDiv td {
	font-size: 18px;
	color: #fff;
	font-weight: 700;
}

#enhanceItemDiv td {
	font-size: 18px;
	color: #f47757;
	font-weight: 700;
}

.button {
	font-size:20px;
	/* background: white; */
	position: absolute;
	left: 100px;
}

.button:hover {
	color: #f47757;
	border-color: #f47757;
}
</style>
<body>
	${itemInformation }
</body>
</html>