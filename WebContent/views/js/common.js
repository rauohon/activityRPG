/*폼 생성*/
function createForm(formname, action, method){
	var form = document.createElement("form")
	form.name = formname;
	form.action = action;
	form.method = method;
	document.body.appendChild(form); 
	return form;
}

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