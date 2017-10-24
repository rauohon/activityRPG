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
<h1>EnrollRaspberryPiPage 입니다.</h1>
<h3>라즈베리파이 코드</h3>
<form action="EnrollRaspberry" method='get' >
${id }님!! 반갑습니다.
<input type='text' placeholder='라즈베리파이에 쓰여있는 코드를 입력하세요.' name='riId' />
<input type='hidden' name='id' value='${id }' />
<input type='submit' value='제출' />
</form>
</body>
</html>