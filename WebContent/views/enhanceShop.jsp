<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enhance Shop</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css" href="/css/enhanceShop.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- alert창 변경 -->
<script>
	function choose(ItemCode, EnhanceLevel){
		var characterName = "${characterName}";

		if(ItemCode == 8001){
			swal({
				  text: "강화석은 강화할 수 없습니다.",
				  icon: "error",
				  buttons: false,
				  timer: 3000,

				});
		}else if(EnhanceLevel >= 9){
			swal({
				  text: "이미 최대치에 도달 하셨습니다.",
				  icon: "error",
				  buttons: false,
				  timer: 3000,
				});
		}else{
			$.ajax({
				type:"post",
				url:"ItemInformation",
				data:{"characterName" : characterName, "itemCode" : ItemCode}, //요청 값
				dataType:"text", //json, xml, text(html), jsonp (기본값은 html)
				timeout:"5000", //요청 타임 아웃 (5초안에 실행이 안되면 오류처리)
				success:function(data){ //성공 시 실행
					//alert(data);
					console.log(data);
					$("#itemInformationDiv").html(data);
				},
				error:function(error){ //에러 시 실행
					alert("error");
					console.log(error);
				}
			});
		}
	}
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
}
</script>
<style>
		.button {
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
</style>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<style>				
		body {
			background-image: url("/images/enhance2.png");
			background-size: cover;
			background-repeat: no-repeat;
			background-attachment: fixed;
		}
	</style>
	<div id='wraper' style="padding-top: 60px;">
	<a href='BackPage' class='button'>뒤로가기</a>
		<div id="message">
			${message }
		</div>
		<div id="itemInformationDiv">			
		</div>
		<div id="inventoryDiv">
			${userInventory }
		</div>
	</div>
</body>
</html>