<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1{
  font-size: 30px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}
table{
  width:100%;
  table-layout: fixed;
}
.tbl-header{
  background-color: rgba(255,255,255,0.3);
 }
.tbl-content{
  height:300px;
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
.tbl-header th{
  padding: 15px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 12px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 50px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 12px;
  color: #fff;
  border-bottom: solid 1px rgba(255,255,255,0.1);
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
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function gBoardForm(formName, actionName, method) {
	var f = $("#fixForm");
	var gbCode = $("<input />");
	var chName = $("<input />");
	var gbTitle = $("<input />");
	var gbReplyContent = $("<input />")
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	gbCode.attr("type","hidden");
	chName.attr("type", "hidden");
	gbTitle.attr("type", "hidden");
	gbReplyContent.attr("type", "hidden");
	gbCode.attr("name","gbCode");
	chName.attr("name", "chName");
	gbTitle.attr("name", "gbTitle");
	gbReplyContent.attr("name", "gbReplyContent");
	gbCode.attr("value",'${gbCode}');
	chName.attr("value",'${writer}');
	gbTitle.attr("value",'${title}');
	gbReplyContent.attr("value",$("input[name=gbReplyContent]").val());
	$("#fixForm").append(gbCode);
	$("#fixForm").append(chName);
	$("#fixForm").append(gbTitle);
	$("#fixForm").append(gbReplyContent);
	
	f.submit();
}
function replyForm(formName, actionName, method, grCode){
	var f = $("#fixForm");
	var grcode = $("<input />");
	var gbCode = $("<input />");
	f.attr("name",formName);
	f.attr("action",actionName);
	f.attr("method",method);
	grcode.attr("type","hidden");
	gbCode.attr("type","hidden");
	grcode.attr("name","grCode");
	gbCode.attr("name","gbCode");
	grcode.attr("value",grCode);
	gbCode.attr("value",'${gbCode}');
	$("#fixForm").append(grcode);
	$("#fixForm").append(gbCode);
	
	f.submit();
}
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
$(window).on("load resize ", function() {
	  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
	  $('.tbl-header').css({'padding-right':scrollWidth});
	}).resize();
</script>
</head>
<body onLoad='init()'>
<%@ include file="nav.jsp"%>
<div id='wraper' style="padding-top: 60px;">
	<table style='border:1px solid black; color:white; width: 90%; margin-left: 5%; margin-top:5%;'>
	<thead class='tbl-header'>
		<tr>
			<th>
				작성자 : ${writer }
			</th>
			<td>
				작성일 : ${wdate }
			</td>
			<td>
				조회수 : ${hit }
			</td>
		</tr>
		<tr>
			<th colspan="3"  style='border:1px solid black;'>
				${title }
			</th>
		</tr>
		</thead>
		<tbody class='tbl-content'>
		<tr>
			<td colspan="3" style="padding-bottom: 20%">
				${content }
			</td>
		</tr>
		</tbody>
	</table>
	<div id='ajax_div'  style='color:white; margin-top:2%; margin-left: 20%'>${reply }</div>
	<div id='replyWrite' style='color:white; text-align: center; margin-top: 2%;'>
			${chName } : <input type='text' placeholder='댓글을 달아주세요.' name='gbReplyContent' size='100px;' class='button'/>
			<input type='button' onClick='gBoardForm("replygboard","ReplyGBoard","POST")' class="button"  value='댓글 등록' />
	</div>
	<div style="text-align: center; margin-top: 2%;">
	<a href='GuildBoardPage' class="button">전체 목록</a>
	<input type='button' class="button" onClick='gBoardForm("deletegboard", "DeleteGBoard", "POST")' value='삭제' />
	<input type='button' class="button" onClick='gBoardForm("modifygboardpage", "ModifyGBoardPage", "POST")' value='수정' />
	<input type='button' class="button" onClick='gBoardForm("writegboardpage", "WriteGBoardPage", "GET")' value='글쓰기' />
	</div>
	<form id="fixForm"></form>
</div>
</body>
</html>