
/*input객체 생성  --> hidden으로 만들거임*/
function createObj(objType, objName, objValue, objPlaceholder){
	var input = document.createElement("input");
	input.type = objType;
	input.name = objName;
	input.value = objValue;
	if(objType != "hidden"){
		input.placeholder = objPlaceholder;
	}
	document.body.appendChild(input);
}

/*form과 요소개체 연결*/
function relationObj(formName, objName){
	var form = document.getElementsByName(formName)[0];
	var obj = document.getElementsByName(objName)[0];
	var result = false;
	if(form != null){
		form.appendChild(obj);
		result = true;
	}
	return result;
}

/*form과 요소개체 연결*/
function relationform(formName){
	var form = document.getElementsByName(formName)[0];
	var result = false;
	if(form != null){
		form.appendChild(obj);
		result = true;
	}
	return result;
}

//input 생성
function createinput(itype, iname, ivalue) {
   var input = document.createElement("input");
   input.type = itype;
   input.name = iname;
   input.value = ivalue;

   document.body.appendChild(input);
}
//공략 작성 폼으로 이동
function attackBoardMakeFormMove(){
	createForm("attackBoardMakeFormMoveForm", "AttackBoardMakeFormMove", "post");
	var form = document.getElementsByName("attackBoardMakeFormMoveForm")[0];
	form.submit();
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

/*폼 생성*/
function createForm(formname, action, method){
	var form = document.createElement("form")
	form.name = formname;
	form.action = action;
	form.method = method;
	document.body.appendChild(form); 
	return form;
}

//공지사항 검색
function searchNews(){
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

//검색
function searchAttack(){
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
	var listSize = '${listSize }';
	for(i = 0; i <= listSize/15; i++){
		if(i != listNum){
			var divNone = document.getElementById("div"+i);
			divNone.style.display = "none";
		} 
	}
}