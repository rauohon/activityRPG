<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.activityRPG.utils.ProjectUtils"%>
<%! ProjectUtils session; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Board</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<link rel="stylesheet" type="text/css" href="css/questionBoard.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function init(){
	var userType = ${userType};
	if(userType == ""){
		
	}else{
		 var input = document.createElement("input");
		 input.type = "button";
		 input.value = "게시글 작성";
		 input.setAttribute("onClick", "questionBoardMakeFormMove()");
		 input.setAttribute("class", "writeButton");
		 document.getElementById("divOption").appendChild(input);
	}
	if(userType == 1){
	      $("#nomar_user").css("display","");
	      $("#admin_user").css("display","none");
	   }else{
			 $("#nomar_user").css("display","none");
		     $("#admin_user").css("display","");
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
	
	//1:1 문의 작성 폼으로 이동
	function questionBoardMakeFormMove(){
		createForm("questionBoardMakeFormMoveForm", "QuestionBoardMakeFormMove", "post");
		var form = document.getElementsByName("questionBoardMakeFormMoveForm")[0];
		form.submit();
	}
	
	//1:1 문의 내용 보기
	function questionBoardContents(QbCode, QbHit){
		createForm("questionBoardContentsForm", "QuestionBoardContentsView", "post");
		var form = document.getElementsByName("questionBoardContentsForm")[0];
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "qbCode";
		code.value = QbCode;
		
		var hit = document.createElement("input");
		hit.type = "hidden";
		hit.name = "qbHit";
		hit.value = QbHit;
		
		form.appendChild(hit);
		form.appendChild(code);
		form.submit();
	}
	
	//폼 생성
	function createForm(formName, action, method){
		var form = document.createElement("form");
		form.name = formName;
		form.action = action;
		form.method = method;
		document.body.appendChild(form);
	}
	
	//검색
	function search(){
		var option = document.getElementById("option");
		var search = document.getElementsByName("search")[0];
		createForm("searchForm", "Search", "post");
		var form = document.getElementsByName("searchForm")[0];
		
		if(option.value == "qbMbid"){
			search.name = "qbMbid";
		}else if(option.value == "qbTitle"){
			search.name = "qbTitle";
		}
		form.appendChild(search);
		form.submit();
	}
	
	//리스트 출력
	function listPrint(listNum){
		var divBlock = document.getElementById("div"+listNum);
		divBlock.style.display = "block";
		var i;
		var listSize = ${listSize };
		for(i = 0; i <= listSize/15; i++){
			if(i != listNum){
				var divNone = document.getElementById("div"+i);
				divNone.style.display = "none";
			} 
		}
	}
</script>
<style>
.divClass {
	display: none;
}

div#div0 {
	display: block;
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
</head>
<body onLoad='init()'>
	<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<h1><a href='BackPage' class='button'>뒤로가기</a></h1>
	${questionBoardList}
	<div id="pageNum"></div>
	<br />
	<div id="divOption">
		<select id="option" class="selectBox">
			<option value="qbTitle">제목</option>
			<option value="qbMbid">작성자</option>
		</select> <input type="text" name="search" class="textBox" /> <input
			type="button" value="검색" onClick="search()" class="searchButton" />
	</div>
	</div>
</body>
</html>