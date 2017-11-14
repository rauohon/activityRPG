<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 상점</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("tr").on({
		mouseover : function() {
			$(this).css("background-color", "lightgray");
		},
		mouseout : function() {
			$(this).css("background-color", "transparent");
		},
		click : function() {
			$(this).css("background-color", "#90E7F6");
		}
	});
	
});
</script>
</head>
<link rel="stylesheet" type="text/css" href="/css/equipShop.css"/>
<style>
img {
    border-radius: 30%;
}
h3.h3{
	color:#C9AE00;
	position: absolute;
    left: 90px;
    top: 180px;
}
h3.h33{
	color:#C9AE00;
	position: absolute;
    top: 180px;
    left: 590px;

}
</style>
<script>
var equipCode = null;

function equipBuy(itcode){
	equipCode = itcode;
	//alert(equipCode);
}

function buy(){
	//alert(equipCode);
	
	createForm("equipBuy", "EquipBuy", "post");
	var equipBuyForm = document.getElementsByName("equipBuy")[0];
	
	var storeItemcode = document.createElement("input");
	storeItemcode.type = "hidden";
	storeItemcode.name = "storeItemcode";
	storeItemcode.value = equipCode;
	
	equipBuyForm.appendChild(storeItemcode);
	equipBuyForm.submit();
}

function equipSell(itcode){
	equipCode = itcode;
	//alert(equipCode);
}

function sell(){
	//alert(equipCode);
	
	createForm("equipSell", "EquipSell", "post");
	var equipSellForm = document.getElementsByName("equipSell")[0];
	
	var ivItemcode = document.createElement("input");
	ivItemcode.type = "hidden";
	ivItemcode.name = "ivItemcode";
	ivItemcode.value = equipCode;
	
	equipSellForm.appendChild(ivItemcode);
	equipSellForm.submit();
}

//폼 생성
function createForm(formName, action, method){
	var form = document.createElement("form");
	form.name = formName;
	form.action = action;
	form.method = method;
	document.body.appendChild(form);
}


</script>
<body>
	<h1>아이템 상점</h1>
	<h3 class="h3">파는아이템</h3>
	<h3 class="h33">인벤토리</h3>
		${equipItemList }
	<div class="shopbt">
		<div class="gold">
			<img src="/images/gold3.png" alt="Inven Gold" width="33" height="33">&nbsp;${chGold }
		</div>
		<br/>
		<div class="shopDeal">
			<input type="button" value="구매" onClick="buy()"/>
			<input type="button" value="판매" onClick="sell()"/>
		</div>
	</div>
</body>
</html>