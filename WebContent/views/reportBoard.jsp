<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activityRPG.utils.ProjectUtils" %>
<%! ProjectUtils pju; %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/backGround.css"/>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Report Board</title>
</head>
<script>
function init(){
	var userType = "${userType}";
    if(userType == ""){
       
    }else{
        var input = document.createElement("input");
        input.type = "button";
        input.value = "게시글 작성";
        input.setAttribute("onClick", "reportBoardMakeFormMove()");
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
	
		var i;
		var listSize = ${listSize };
		for(i = 0; i <= listSize; i++){
			var input = document.createElement("input");
			input.type = "button";
			input.value = i + 1;
			input.setAttribute("class", "listNum");
			input.setAttribute("onClick", "listPrint("+ i +")");
			
			document.body.appendChild(input);
		}
	}
	
	//공지사항 작성 폼으로 이동
	function reportBoardMakeFormMove(){
		createForm("reportBoardMakeFormMoveForm", "ReportBoardMakeFormMove", "post");
		var form = document.getElementsByName("reportBoardMakeFormMoveForm")[0];
		form.submit();
	}
	
	//공지사항 내용 보기
	function reportBoardContents(ReportBoardCode, ReportBoardHit){
		createForm("reportBoardContentsForm", "ReportBoardContentsView", "post");
		var form = document.getElementsByName("reportBoardContentsForm")[0];
		var code = document.createElement("input");
		code.type = "hidden";
		code.name = "reportBoardCode";
		code.value = ReportBoardCode;
		
		var hit = document.createElement("input");
		hit.type = "hidden";
		hit.name = "reportBoardHit";
		hit.value = ReportBoardHit;
		
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
		createForm("searchForm", "ReportSearch", "post");
		var form = document.getElementsByName("searchForm")[0];
		
		if(option.value == "reportBoardUserId"){
			search.name = "reportBoardUserId";
		}else if(option.value == "reportBoardTitle"){
			search.name = "reportBoardTitle";
		}

		form.appendChild(search);
		form.submit();
	}
	
	//리스트 출력
	function listPrint(listNum){
		var divBlock = document.getElementById("div"+listNum);
		divBlock.style.display = "block";
		var i;
		for(i = 0; i <= ${listSize }; i++){
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
	.listNum{
		background-color:white;
		outline:none;
	}
	body {
	background-color: #2c3338;
}

div#div0 {
	display: block;
}

table.boardClass {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	
	margin: 10px 20px;
}

table.boardClass th {
	width: 147px;
	padding: 5px 10px;
	font-weight: bold;
	vertical-align: top;
	color: white;
	background: linear-gradient(to right, #2c3338, #660033);
}

table.boardClass td {
	width: 349px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	padding: 10px;
	vertical-align: top;
	
	color: white;
}
	.number{
		margin-left:50px;
	
	}
</style>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
	<div id='wraper' style="padding-top: 60px;">
	<div>
		${reportBoardList }
	</div>
	<br/>
	<div id="divOption">
		<select id="option">
			<option value="reportBoardTitle">제목</option>
			<option value="reportBoardUserId">작성자</option>
		</select>
		<input class="textbox" type="text" name="search"/>
		<input class="search" type="button" value="검색" onClick="search()" />
	</div>
	</div>
</body>
</html>