<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>battle Page</title>
</head>
<link rel="stylesheet" type="text/css" href="css/battlePage.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/common.js"></script>
<script>
	//그래프를 위한 내용 
	var beforeHp = "${beforeHp}";
	var beforeMp = "${beforeMp}";
	var beforeMonsterHp = "${beforeMonsterHp}";

	//피해 수치
	var characterDamage = "${characterDamage}";
	var monsterDamage = "${monsterDamage}";
	
	//캐릭터 정보
	var characterName = "${characterName}"
	var userId = "${characterId}"
	var sex = ${characterSex}
	var level = ${characterLevel}
	var exp = ${characterExp}
	var totalHp = ${characterTotalHp}
	var hp = ${characterHp}
	var totalMp = ${characterTotalMp}
	var mp = ${characterMp}
	var str = ${characterStr}
	var dex = ${characterDex}
	var intelligent = ${characterInt}
	var attack = ${characterAttack}
	var defense = ${characterDefense}
	var gold = ${characterGold}

	//몬스터 정보
	var monsterCode = ${monsterCode}
	var monsterName = "${monsterName}"
	var monsterTotalHp = ${monsterTotalHp}
	var monsterHp = ${monsterHp}
	var monsterDex = ${monsterDex}
	var monsterAttack = ${monsterAttack}
	var monsterDefense = ${monsterDefense}
	var getExp = ${getExp}
	var getGold = ${getGold}
	var fieldCode = ${fieldCode}
	
	
	$(document).ready(function(){
		
		//몬스터 스킬에 따른 로그 변경
		var monsterSkillName = "${monsterSkillName }";
		
		if(monsterSkillName != ""){
			//HP, MP 막대기
			var z = parseInt(100 * beforeMonsterHp / monsterTotalHp);
			$(".monsterHp").css({"width" : z + "%"});
			var z = parseInt(100 * beforeHp / totalHp);
			$(".characterHp").css({"width" : z + "%"});
			var z = parseInt(100 * beforeMp / totalMp);
			$(".characterMp").css({"width" : z + "%"});
			
			if(dex > monsterDex){	//캐릭터가 더 빠른 경우
				$("#character").animate({left: "+=70px"}, "fast");
				$("#character").animate({left: "-=70px"});
				
				$("#battleLog").append("${characterName }" + "는(은) " + "${characterSkillName }" + "를(을) 사용했다.<br/>");
				$("#battleLog").append("${characterDamage }" + "의 피해를 주었다.<br/><br/>");
				
				var z = parseInt(100 * monsterHp / monsterTotalHp);	
				$(".monsterHp").css({"width" : z + "%"});	//몬스터 체력 변경
				var z = parseInt(100 * mp / totalMp);
				$(".characterMp").css({"width" : z + "%"}); //캐릭터 마력 변경
		
				$("#monster").animate({opacity: "0.1"}, "fast");
				$("#monster").animate({opacity: "1"}, "fast");
				$("#monster").animate({opacity: "0.1"}, "fast");
				$("#monster").animate({opacity: "1"}, "fast");
				$("#monster").animate({opacity: "0.1"}, "fast");
				$("#monster").animate({opacity: "1"}, "fast");
				
				setTimeout(function(){
					$("#monster").animate({left: "-=70px"}, "fast");
					$("#monster").animate({left: "+=70px"});
					
					$("#battleLog").append("${monsterName }" + "는(은) " + "${monsterSkillName }" + "를(을) 사용했다.<br/>");
					$("#battleLog").append("${monsterDamage }" + "의 피해를 주었다.<br/>");
					
					var z = parseInt(100 * hp / totalHp);
					$(".characterHp").css({"width" : z + "%"});	//캐릭터 체력 변경
					
					$("#character").animate({opacity: "0.1"}, "fast");
					$("#character").animate({opacity: "1"}, "fast");
					$("#character").animate({opacity: "0.1"}, "fast");
					$("#character").animate({opacity: "1"}, "fast");
					$("#character").animate({opacity: "0.1"}, "fast");
					$("#character").animate({opacity: "1"}, "fast");
				}, 2000);
			}else if(dex <= monsterDex){	//몬스터가 더 빠른 경우
				$("#monster").animate({left: "-=70px"}, "fast"); //몬스터 사진 이동
				$("#monster").animate({left: "+=70px"});
				
				$("#battleLog").append("${monsterName }" + "는(은) " + "${monsterSkillName }" + "를(을) 사용했다.<br/>");
				$("#battleLog").append("${monsterDamage }" + "의 피해를 주었다.<br/><br/>");
				
				var z = parseInt(100 * hp / totalHp);
				$(".characterHp").css({"width" : z + "%"});	//캐릭터 체력 변경
				
				$("#character").animate({opacity: "0.1"}, "fast"); //캐릭터 사진 깜박임
				$("#character").animate({opacity: "1"}, "fast");
				$("#character").animate({opacity: "0.1"}, "fast");
				$("#character").animate({opacity: "1"}, "fast");
				$("#character").animate({opacity: "0.1"}, "fast");
				$("#character").animate({opacity: "1"}, "fast");
				
				setTimeout(function(){
					$("#character").animate({left: "+=70px"}, "fast");	//캐릭터 사진 이동
					$("#character").animate({left: "-=70px"});
					
					$("#battleLog").append("${characterName }" + "는(은) " + "${characterSkillName }" + "를(을) 사용했다.<br/>");
					$("#battleLog").append("${characterDamage }" + "의 피해를 주었다.<br/>");
					
					var z = parseInt(100 * monsterHp / monsterTotalHp);	
					$(".monsterHp").css({"width" : z + "%"});	//몬스터 체력 변경
					var z = parseInt(100 * mp / totalMp);
					$(".characterMp").css({"width" : z + "%"}); //캐릭터 마력 변경
					
					$("#monster").animate({opacity: "0.1"}, "fast");	//몬스터 사진 깜박임
					$("#monster").animate({opacity: "1"}, "fast");
					$("#monster").animate({opacity: "0.1"}, "fast");
					$("#monster").animate({opacity: "1"}, "fast");
					$("#monster").animate({opacity: "0.1"}, "fast");
					$("#monster").animate({opacity: "1"}, "fast");
				}, 2000);
				
			}else if(dex == monsterDex){	//몬스터와 캐릭터의 속도가 같은 경우
				
			}
		}else{
			//HP, MP 막대기
			var z = parseInt(100 * monsterHp / monsterTotalHp);
			$(".monsterHp").css({"width" : z + "%"});
			var z = parseInt(100 * hp / totalHp);
			$(".characterHp").css({"width" : z + "%"});
			var z = parseInt(100 * mp / totalMp);
			$(".characterMp").css({"width" : z + "%"});
		}
		
		//배경화면
		if(fieldCode == 1){	//초원
			$("body").css("background-image", "url(images/patagonia.png)")
			$("body").css("background-size", "cover")
			$("body").css("background-repeat", "no-repeat")
			$("body").css("background-attachment", "fixed")
		}else if(fieldCode == 2){	//정글
			$("body").css("background-image", "url(images/jungle.png)")
			$("body").css("background-size", "cover")
			$("body").css("background-repeat", "no-repeat")
			$("body").css("background-attachment", "fixed")
		}else if(fieldCode == 3){	//산
			$("body").css("background-image", "url(images/mountain.png)")
			$("body").css("background-size", "cover")
			$("body").css("background-repeat", "no-repeat")
			$("body").css("background-attachment", "fixed")
		}
		
		//몬스터 사진
		if(monsterCode == 1){	//표범
			$("#monster").css("background-image", "url(images/cat.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 2){	//사자
			$("#monster").css("background-image", "url(images/lion.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 3){	//암사자
			$("#monster").css("background-image", "url(images/mountain-lion.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 4){	//악어
			$("#monster").css("background-image", "url(images/cayman.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 5){	//아나콘다
			$("#monster").css("background-image", "url(images/anaconda.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 6){	//돌악마
			$("#monster").css("background-image", "url(images/stone-woman.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}else if(monsterCode == 7){	//아나콘다
			$("#monster").css("background-image", "url(images/elf.png)")
			$("#monster").css("background-size", "100% 100%")
			$("#monster").css("background-repeat", "no-repeat")
		}
		
		//캐릭터 사진
		if(sex == 0){	//남자
			$("#character").css("background-image", "url(images/man.png)")
			$("#character").css("background-size", "100% 100%")
			$("#character").css("background-repeat", "no-repeat")
		}else{	//여자
			$("#character").css("background-image", "url(images/woman.png)")
			$("#character").css("background-size", "100% 100%")
			$("#character").css("background-repeat", "no-repeat")
		}
	});
	
	function doAttack(){
		$.ajax({
			type:"post",
			url:"SkillMenu",
			data:{"characterName" : characterName, "userId" : userId, "sex" : sex, "level" : level, "exp" : exp, "totalHp" : totalHp, "hp" : hp, "totalMp" : totalMp, "mp" : mp, "str" : str, "dex" : dex, "intelligent" : intelligent, "attack" : attack, "defense" : defense, "gold" : gold, "monsterCode" : monsterCode, "monsterName" : monsterName, "monsterTotalHp" : monsterTotalHp, "monsterHp" : monsterHp, "monsterDex" : monsterDex, "monsterAttack" : monsterAttack, "monsterDefense" : monsterDefense, "getExp" : getExp, "getGold" : getGold, "fieldCode" : fieldCode}, //요청 값
			dataType:"text", //json, xml, text(html), jsonp (기본값은 html)
			timeout:"5000", //요청 타임 아웃 (5초안에 실행이 안되면 오류처리)
			success:function(data){ //성공 시 실행
				//alert(data);
				console.log(data);
				$("#command").html(data);
			},
			error:function(error){ //에러 시 실행
				alert("error");
				console.log(error);
			}
		});
	}
	function openBag(){
		$(function(){
			$("div").effect("fade", "slow");
		});
		setTimeout(function(){
			createForm("characterInfo", "CharacterInfo", "get");
			var form = document.getElementsByName("characterInfo")[0];
			form.submit();
		}, 500);
	}
	function run(){
		$(function(){
			$("div").effect("drop", "slow");
		});
		setTimeout(function(){
			createForm("runForm", "Run", "post");
			var form = document.getElementsByName("runForm")[0];
			form.submit();
		}, 550);
	}
</script>
<style>
	#monster{
		width:340px;
		height:319px;
		border-style:dotted;
		border-width:4px;
		border-color:#9D9014;
		position:absolute;
		left:900px;
		background:rgba(0, 0, 0, 0.5);
		margin-top:100px;
	}
	#battleLog{
		width:800px;
		height:300px;
		border-style:dotted;
		border-width:4px;
		border-color:#9D9014;
		position:absolute;
		left:40px;
		padding:10px;
		font-weight: 700;
		font-size:30px;
		color:white;
		background:rgba(0, 0, 0, 0.5);
		margin-top:100px;
	}
	#character{
		width:340px;
		height:319px;
		border-style:dotted;
		border-width:4px;
		border-color:#9D9014;
		position:absolute;
		top:500px;
		left:40px;
		background:rgba(0, 0, 0, 0.5);
		
	}
	#command{
		width:811px;
		height:319px;
		border-style:dotted;
		border-width:4px;
		border-color:#9D9014;
		position:absolute;
		top:500px;
		left:430px;
		background:rgba(0, 0, 0, 0.5);
	}
	.button{
		width:790px;
		height:91px;
		background: #CC723D;
		color: #fff;
		border: none;
		font-size: 20px;
		outline: none;
		margin:8px 10px;	
	}
	.button:hover {
		background: #fff;
		color: #CC723D;
	}
</style>
<body>
	<div id="monster" class="hp">
		<span class="totalHp"><span class="monsterHp"></span></span>
	</div>
	
	<div id="battleLog">
		
	</div>
	
	<div id="character" class="hp">
		<span class="totalHp"><span class="characterHp"></span></span><br/>
		<span class="totalMp"><span class="characterMp"></span></span>
	</div>
	
	<div id="command">
		<button class="button" onClick="doAttack()"><img src="images/swords.png" width="25px" height="25px" /> 공격한다</button><br/>
		<button class="button" onClick="openBag()"><img src="images/bag.png" width="25px" height="25px" /> 가방을 연다</button><br/>
		<button class="button" onClick="run()"><img src="images/pace.png" width="25px" height="25px" /> 도망간다</button>
	</div>
</body>
</html>