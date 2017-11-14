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
.active{
	background-color: gray;
}
.pageNum{
	text-align:center;
	margin-left: auto;
	margin-top: 2%;
}
.pageNum button{
	background: none;
	border: 3px solid #fff;
	border-radius: 5px;
	margin: 1%;
	width: 5%;
}
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
<script type="text/javascript">
	function readGboard(code) {
		var f = $("#fixForm");
		var i = $("<input />");
		f.attr("name", "readgboardpage");
		f.attr("action", "ReadGBoardPage");
		f.attr("method", "GET");
		i.attr("type", "hidden");
		i.attr("name", "gbCode");
		i.attr("value", code);
		$("#fixForm").append(i);
		f.submit();
	}
	$(document).ready(function() {
		$("tr").hover(function() {
			$(this).toggleClass( "active" );
		});
	});
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
	function guildboardpage2(code){
		var f = $("#fixForm");
		var i = $("<input />");
		f.attr("name", "guildboardpage");
		f.attr("action", "GuildBoardPage");
		f.attr("method", "GET");
		i.attr("type", "hidden");
		i.attr("name", "gbStep");
		i.attr("value", code);
		$("#fixForm").append(i);
		f.submit();
	}
	function guildboardadmindelete(code){
		var f = $("#fixForm");
		var i = $("<input />");
		f.attr("name", "guildBoardAdminDelete");
		f.attr("action", "GuildBoardAdminDelete");
		f.attr("method", "POST");
		i.attr("type", "hidden");
		i.attr("name", "gbCode");
		i.attr("value", code);
		$("#fixForm").append(i);
		f.submit();
	}
</script>
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<div style="color: white;">
		${boards }
	</div>
	<div id='control' style="text-align: center;">
		<a href='WriteGBoardPage' class="button">글 작성</a>
		<input type="text" name="text" />
		<input type="button" id="button" class="button" onClick="titlefine()" value="검색" />
		<form id="fixForm"></form>
	</div>
</div>
</body>
</html>