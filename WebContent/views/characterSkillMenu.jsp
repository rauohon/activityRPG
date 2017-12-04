<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Character Skill Menu</title>
</head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <!-- alert창 변경 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	$(document).ready(function(){
		var characterMp = ${characterMp};
		if(characterMp < 10){
			$("#skillButton1").attr("onClick", "").unbind("click");	
			$("#skillButton1").bind("click", function(){mpLack();});
			$("#skillButton2").attr("onClick", "").unbind("click");	
			$("#skillButton2").bind("click", function(){mpLack();});
			$("#skillButton3").attr("onClick", "").unbind("click");	
			$("#skillButton3").bind("click", function(){mpLack();});
		}else if(characterMp < 20){
			$("#skillButton2").attr("onClick", "").unbind("click");	
			$("#skillButton2").bind("click", function(){mpLack();});
			$("#skillButton3").attr("onClick", "").unbind("click");	
			$("#skillButton3").bind("click", function(){mpLack();});
		}else if(characterMp < 50){
			$("#skillButton3").attr("onClick", "").unbind("click");	
			$("#skillButton3").bind("click", function(){mpLack();});
		}
	});
	
	function mpLack(){
		swal({
			  text: "Mp가 부족합니다.",
			  icon: "warning",
			  buttons: false,
			  timer: 3000,
			});
	}


	function useSkill(SkillCode){
		//폼 생성
		createForm("useSkillForm", "UseSkill", "post");
		var useSkillForm = document.getElementsByName("useSkillForm")[0];
		
		//스킬 코드 가져오기
		createInput("skillCode", "hidden", SkillCode);
		var skillCodeInput = document.getElementsByName("skillCode")[0];
		useSkillForm.appendChild(skillCodeInput);
		
		//캐릭터 정보 가져오기
		createInput("characterName", "hidden", "${characterName}");
		var characterNameInput = document.getElementsByName("characterName")[0];
		useSkillForm.appendChild(characterNameInput);
		
		createInput("userId", "hidden", "${characterId}");
		var userIdInput = document.getElementsByName("userId")[0];
		useSkillForm.appendChild(userIdInput);
		
		createInput("sex", "hidden", "${characterSex}");
		var sexInput = document.getElementsByName("sex")[0];
		useSkillForm.appendChild(sexInput);
		
		createInput("level", "hidden", "${characterLevel}");
		var levelInput = document.getElementsByName("level")[0];
		useSkillForm.appendChild(levelInput);
		
		createInput("exp", "hidden", "${characterExp}");
		var expInput = document.getElementsByName("exp")[0];
		useSkillForm.appendChild(expInput);
		
		createInput("totalHp", "hidden", "${characterTotalHp}");
		var totalHpInput = document.getElementsByName("totalHp")[0];
		useSkillForm.appendChild(totalHpInput);
		
		createInput("hp", "hidden", "${characterHp}");
		var hpInput = document.getElementsByName("hp")[0];
		useSkillForm.appendChild(hpInput);
		
		createInput("totalMp", "hidden", "${characterTotalMp}");
		var totalMpInput = document.getElementsByName("totalMp")[0];
		useSkillForm.appendChild(totalMpInput);
		
		createInput("mp", "hidden", "${characterMp}");
		var mpInput = document.getElementsByName("mp")[0];
		useSkillForm.appendChild(mpInput);
		
		createInput("str", "hidden", "${characterStr}");
		var strInput = document.getElementsByName("str")[0];
		useSkillForm.appendChild(strInput);
		
		createInput("dex", "hidden", "${characterDex}");
		var dexInput = document.getElementsByName("dex")[0];
		useSkillForm.appendChild(dexInput);
		
		createInput("intelligent", "hidden", "${characterInt}");
		var intelligentInput = document.getElementsByName("intelligent")[0];
		useSkillForm.appendChild(intelligentInput);
		
		createInput("attack", "hidden", "${characterAttack}");
		var attackInput = document.getElementsByName("attack")[0];
		useSkillForm.appendChild(attackInput);
		
		createInput("defense", "hidden", "${characterDefense}");
		var defenseInput = document.getElementsByName("defense")[0];
		useSkillForm.appendChild(defenseInput);
		
		createInput("gold", "hidden", "${characterGold}");
		var goldInput = document.getElementsByName("gold")[0];
		useSkillForm.appendChild(goldInput);
		
		//몬스터 정보 가져오기
		createInput("monsterCode", "hidden", "${monsterCode}");
		var monsterCodeInput = document.getElementsByName("monsterCode")[0];
		useSkillForm.appendChild(monsterCodeInput);
		
		createInput("monsterName", "hidden", "${monsterName}");
		var monsterNameInput = document.getElementsByName("monsterName")[0];
		useSkillForm.appendChild(monsterNameInput);
		
		createInput("monsterTotalHp", "hidden", "${monsterTotalHp}");
		var monsterTotalHpInput = document.getElementsByName("monsterTotalHp")[0];
		useSkillForm.appendChild(monsterTotalHpInput);
		
		createInput("monsterHp", "hidden", "${monsterHp}");
		var monsterHpInput = document.getElementsByName("monsterHp")[0];
		useSkillForm.appendChild(monsterHpInput);
		
		createInput("monsterDex", "hidden", "${monsterDex}");
		var monsterDexInput = document.getElementsByName("monsterDex")[0];
		useSkillForm.appendChild(monsterDexInput);
		
		createInput("monsterAttack", "hidden", "${monsterAttack}");
		var monsterAttackInput = document.getElementsByName("monsterAttack")[0];
		useSkillForm.appendChild(monsterAttackInput);
		
		createInput("monsterDefense", "hidden", "${monsterDefense}");
		var monsterDefenseInput = document.getElementsByName("monsterDefense")[0];
		useSkillForm.appendChild(monsterDefenseInput);
		
		createInput("getExp", "hidden", "${getExp}");
		var getExpInput = document.getElementsByName("getExp")[0];
		useSkillForm.appendChild(getExpInput);
		
		createInput("getGold", "hidden", "${getGold}");
		var getGoldInput = document.getElementsByName("getGold")[0];
		useSkillForm.appendChild(getGoldInput);
		
		createInput("fieldCode", "hidden", "${fieldCode}");
		var fieldCodeInput = document.getElementsByName("fieldCode")[0];
		useSkillForm.appendChild(fieldCodeInput);
		
		useSkillForm.submit();
	}
	
	//인풋 생성
	function createInput(inputName, inputType, inputValue){
		var input = document.createElement("input");
		input.name = inputName;
		input.type = inputType;
		input.value = inputValue;
		document.body.appendChild(input);
	}
</script>
<style>
	button{
		width:385px;
		height:132px;
		background: #CC723D;
		color: #fff;
		border: none;
		font-size: 20px;
		outline: none;
		margin:15px 10px;
	}
	button:hover {
		background: #fff;
		color: #CC723D;
	}
</style>
<body>
	${characterSkillMenu }
</body>
</html>