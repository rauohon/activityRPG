<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activityRPG.utils.ProjectUtils" %>
<%! ProjectUtils pju; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News Board</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/newsBoard.css"/>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function init(){
	var logincheck = document.getElementById("logincheck");
	var get = document.getElementById("get");
	var info = document.getElementById("info");
	var logout = document.getElementById("logout");
	var userType = "${userType}";
	if(userType==2){
		 var input = document.createElement("input");
		 input.type = "button";
		 input.value = "게시글 작성";
		 input.setAttribute("onClick", "newsBoardMakeFormMove()");
		 input.setAttribute("class", "writeButton");
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
		 document.getElementById("divOption").appendChild(input);
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
	var i;
	var listSize = ${listSize };
	for(i = 0; i <= listSize/15; i++){
		var input = document.createElement("input");
		input.type = "button";
		input.value = "[" + (i + 1) + "]";
		input.setAttribute("class", "listNum");
		input.setAttribute("onClick", "listPrint("+ i +")");
		
		document.getElementById("pageNum").appendChild(input);
	}
	$(function() {	//마우스 오버 시 밑줄 처리
		var i;
		var listSize = ${listSize };
		for(i = 0; i < listSize; i++){
			$("#underline" + i).hover(function() {
				$(this).css("text-decoration", "underline")
			}, function(){
				$(this).css("text-decoration", "none")
			})
		}
	});
}
</script>
<style>
	.divClass{
		display:none;
	} 
	div#div0{
		display:block;
	}
	
</style>
<body onload="init()">
<%@ include file="nav.jsp"%>
<div id='wraper' style="padding-top:60px; color: white;">
	${newsBoardList }
	<div id="pageNum">
	
	</div>
	<br/>
	<div id="divOption">
		<select id="option" class="selectBox">
			<option value="newsBoardTitle">제목</option>
			<option value="newsBoardUserId">작성자</option>
		</select>
		<input type="text" name="search" class="textBox"/>
		<input type="button" value="검색" onClick="searchNews()" class="searchButton"/>
	</div>
	</div>
</body>
</html>