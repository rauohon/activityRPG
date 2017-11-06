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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function init(){
		var userType = "${userType}";
		if(userType==2){
			 var input = document.createElement("input");
			 input.type = "button";
			 input.value = "게시글 작성";
			 input.setAttribute("onClick", "newsBoardMakeFormMove()");
			 input.setAttribute("class", "writeButton");
			 document.getElementById("divOption").appendChild(input);
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
	
	//공지사항 작성 폼으로 이동
	function newsBoardMakeFormMove(){
		createForm("newsBoardMakeFormMoveForm", "NewsBoardMakeFormMove", "post");
		var form = document.getElementsByName("newsBoardMakeFormMoveForm")[0];
		form.submit();
	}
	
	//공지사항 내용 보기
	function newsBoardContents(NewsBoardCode, NewsBoardHit){
		createForm("newsBoardContentsForm", "NewsBoardContentsView", "post");
		var form = document.getElementsByName("newsBoardContentsForm")[0];
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "newsBoardCode";
		code.value = NewsBoardCode;
		
		var hit = document.createElement("input");
		hit.type = "hidden";
		hit.name = "newsBoardHit";
		hit.value = NewsBoardHit;
		
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
		
		if(option.value == "newsBoardUserId"){
			search.name = "newsBoardUserId";
		}else if(option.value == "newsBoardTitle"){
			search.name = "newsBoardTitle";
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
	.divClass{
		display:none;
	} 
	div#div0{
		display:block;
	}
	
</style>
<body onload="init()">
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
		<input type="button" value="검색" onClick="search()" class="searchButton"/>
	</div>
</body>
</html>