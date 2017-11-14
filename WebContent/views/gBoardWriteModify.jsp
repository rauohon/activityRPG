<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<style type="text/css">   
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function startc(){
	alert('ddd');
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
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
<div id='wraper' style="padding-top: 60px;">
<form action='${action }' method='POST'>
<table style='border:1px solid black; width:70%; margin-top:20%; margin-left: 20%;'>
<tr>
<td>작성자</td>
<td>
<input type='text' name='chName' value='${chName }' readonly/>
</td>
</tr>
<tr>
<td>제목</td>
<td>
<input type='text' name='gbTitle' placeholder='제목을 입력 하세요'  value='${gbTitle }' />
<input type='hidden' name='gbGroup' value='${gbGroup }'/>
<input type='hidden' name='gbStep' value='${gbStep }'/>
<input type='hidden' name=gbIndent value='${gbIndent }'/>
</td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea style='width:98%;' rows="10" name='gbContent' placeholder="내용을 입력하세요">
${reply }
${content }
${reply }
</textarea>
</td>
</tr>
<tr>
<td colspan="2" style='text-align: right;'>
<input type='submit' style="margin-top: 2%;" class='button' value='수정'/>
</td>
</tr>
</table>
</form>

</div>
</body>
</html>