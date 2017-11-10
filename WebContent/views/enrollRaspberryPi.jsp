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
<style type="text/css">
p {
	color : tomato;
}
form > div {
	width: 50%;
	position: relative;
	overflow: hidden;
}
form input{
  width: 100%;
  border: 2px solid gray;
  background: none;
  position: relative;
  top: 0;
  left: 0;
  z-index: 1;
  padding: 8px 12px;
  outline: 0;
}
form input:valid, form textarea:valid {
  background: white;
}
form input:focus, form textarea:focus {
  border-color: #f06d06;
}
form input:focus + label, form textarea:focus + label {
  background: #f06d06;
  color: white;
  font-size: 70%;
  padding: 1px 6px;
  z-index: 2;
  text-transform: uppercase;
}
form label {
  transition: background 0.2s, color 0.2s, top 0.2s, bottom 0.2s, right 0.2s, left 0.2s;
  position: absolute;
  color: #999;
  padding: 7px 6px;
}
form.go-right label {
  top: 2px;
  right: 100%;
  width: 100%;
  margin-right: -100%;
  bottom: 2px;
}
form.go-right input:focus + label, form.go-right textarea:focus + label {
  right: 0;
  margin-right: 0;
  width: 40%;
  padding-top: 5px;
}
</style>
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
</head>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
		<div id='wraper' style="padding-top: 20%; padding-left: 35%;">
			<form action="EnrollRaspberry" method='get' class='go-right'>
				${id }님!! 반갑습니다.<br/>
				<p>${msg }</p>
				<div>
				<input type='text' id='rpCode' name='riId' style="font-size: 20px;" />
				<label for="name">라즈베리파이에 쓰여있는 코드를 입력하세요.</label>
				<input type='hidden' name='id' value='${id }' />
				<input type='submit' value='제출' style="width: 58%"/>
				</div>
			</form>
		</div>
</body>
</html>