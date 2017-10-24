<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery :: Traversing :: Filtering</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
		 $("div").first().css({"background-color", "yellow"});
		 $("div").last().css({"background-color", "skyblue"});
		 $("div").eq(1).css({"background-color", "red"});
		 $("div").children("p:first-child").css({"background-color", "green"});
		 $("div").children().filter(".member")css({"background-color", "orange"});
	});
	/* Other Filter 
	   :animated >> 현재 애니메이션의 모든 요소 선택
	   :eq(index)
	   :even >>짝수 인덱스 번호 선택
	   :odd  >>홀수 인덱스 번호 선택
	   :gt(index) >> 선택된 인덱스보다 큰 인덱스 선택
	   :lt(index) >> 선택된 인덱스보다 작은 인덱스 선택
	   :first
	   :last
	   not
	*/
</script>
</head>
<body>
<h1>Welcome to ICIA Education Center</h1>

	<p>제4차 산업혁명 선도인력 양성 사업</p>

	<div style="border: 1px solid black;">
		<p>북두칠성</p>
		<p class="member">신태휘 외 6명</p>
	</div>
	<br/>

	<div style="border: 1px solid black;">
		<p>공조</p>
		<p class="member">남영환 외 4명</p>
	</div>
	<br/>

	<div style="border: 1px solid black;">
		<p>헬로월드</p>
		<p class="member">이경한 외 3명</p>
	</div>
	<br/>
	
	<div style="border: 1px solid black;">
		<p>강조</p>
		<p class="member">이노훈 외 4명</p>
	</div>
</body>
</html>