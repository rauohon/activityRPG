<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activityRPG.utils.ProjectUtils" %>
<%! ProjectUtils pju; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attack Board</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/attackBoard.css"/>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
</script>
<style>
</style><script>
function init(){
	var userType = "${userType}";
    if(userType == ""){
       
    }else{
        var input = document.createElement("input");
        input.type = "button";
        input.value = "게시글 작성";
        input.setAttribute("onClick", "attackBoardMakeFormMove()");
        input.setAttribute("class", "writeButton");
        document.getElementById("divOption").appendChild(input);
    }
	if(userType==2){
		 $("#nomar_user").css("display","none");
		 $("#admin_user").css("display","");
	}else{
		$("#nomar_user").css("display","");
		$("#admin_user").css("display","none");
	}
	
	var listSize = ${listSize };
	for(var i = 0; i <= listSize/15; i++){
		var input = document.createElement("input");
		input.type = "button";
		input.value = "[" + (i + 1) + "]";
		input.setAttribute("class", "listNum");
		input.setAttribute("onClick", "listPrint("+ i +")");
	
		document.getElementById("pageNum").appendChild(input);
	}
	
	$(function() {	//마우스 오버 시 밑줄 처리
		var listSize = ${listSize };
		for(var i = 0; i < listSize; i++){
			$("#underline" + i).hover(function() {
				$(this).css("text-decoration", "underline")
			}, function(){
				$(this).css("text-decoration", "none")
			})
		}
	});
	
}
//공략 작성 폼으로 이동
function attackBoardMakeFormMove(){
	createForm("attackBoardMakeFormMoveForm", "AttackBoardMakeFormMove", "post");
	var form = document.getElementsByName("attackBoardMakeFormMoveForm")[0];
	form.submit();
}
//공략 내용 보기
function attackBoardContents(AttackBoardCode, AttackBoardHit){
	createForm("attackBoardContentsForm", "AttackBoardContentsView", "post");
	var form = document.getElementsByName("attackBoardContentsForm")[0];
	var code = document.createElement("input");
	code.type = "hidden";
	code.name = "attackBoardCode";
	code.value = AttackBoardCode;
	
	var hit = document.createElement("input");
	hit.type = "hidden";
	hit.name = "attackBoardHit";
	hit.value = AttackBoardHit;
	
	form.appendChild(hit);
	form.appendChild(code);
	form.submit();
}
//검색
function search(){
	var option = document.getElementById("option");
	var search = document.getElementsByName("search")[0];
	createForm("searchForm", "AttackSearch", "post");
	var form = document.getElementsByName("searchForm")[0];
	
	if(option.value == "attackBoardUserId"){
		search.name = "attackBoardUserId";
	}else if(option.value == "attackBoardTitle"){
		search.name = "attackBoardTitle";
	}

	form.appendChild(search);
	form.submit();
}
//리스트 출력
function listPrint(listNum){
	var divBlock = document.getElementById("div"+listNum);
	divBlock.style.display = "block";
	var i;
	var listSize = "${listSize }";
	for(i = 0; i <= listSize/15; i++){
		if(i != listNum){
			var divNone = document.getElementById("div"+i);
			divNone.style.display = "none";
		} 
	}
}
</script>
</head>
	<body onLoad='init()'>
		<%@ include file="nav.jsp"%>
		<div id='wraper' style="padding-top: 60px;">
		${attackBoardList }
	<div id="pageNum">
	
	</div>
	<br/>
	<div id="divOption">
		<select id="option" class="selectBox">
			<option value="attackBoardTitle">제목</option>
			<option value="attackBoardUserId">작성자</option>
		</select>
		<input type="text" name="search" class="textBox"/>
		<input type="button" value="검색" onClick="search()" class="searchButton"/>
	</div>
	</div>
</body>
</html>