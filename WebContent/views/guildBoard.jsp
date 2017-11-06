<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<style>
table{
  width:100%;
  table-layout: fixed;
}
thead{
  background-color: rgba(255,255,255,0.3);
 }
tbody{
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 12px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 12px;
  color: #fff;
  border-bottom: solid 1px rgba(255,255,255,0.1);
}

</style>
<script type="text/javascript">
function createForm(code) {
	var f = $("#fixForm");
	var i = $("<input />");
	f.attr("name","readgboardpage");
	f.attr("action","ReadGBoardPage");
	f.attr("method","GET");
	i.attr("type","hidden");
	i.attr("name","gbCode");
	i.attr("value",code);
	$("#fixForm").append(i);
	
  document.readgboardpage.submit();
}
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<a href='WriteGBoardPage'>글 작성</a>
	<div style="color: white;">
		${boards }
	</div>
	<form id="fixForm"></form>
</div>
</body>
</html>