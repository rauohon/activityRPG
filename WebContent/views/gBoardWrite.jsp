<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function startc(){
	alert('ddd');
}
</script>
</head>
<body>
<h1>writeGBoard Page 입니다.</h1>
<form action='WriteGBoard' method='GET'>
<table style='border:1px solid black;'>
<tr>
<td>작성자</td>
<td>
<input type='text' name='chName' value='${chName }' readonly/>
</td>
</tr>
<tr>
<td>제목</td>
<td>
<input type='text' name='gbTitle' placeholder='제목을 입력 하세요' />
</td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea rows="10" name='gbContent' placeholder="내용을 입력하세요"></textarea>
</td>
</tr>
<tr>
<td colspan="2" style='text-align: right;'>
<input type='submit' value='작성'/>
</td>
</tr>
</table>
</form>


</body>
</html>