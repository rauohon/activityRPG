package com.activityRPG.services;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.activityRPG.beans.BattleBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : GamePlayService
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class GamePlayService  extends TranEx {
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	
	boolean transaction = false;
	RedirectView rv = null;
	//***********************김훈******************
	private int goldDisappear;	//소실된 금화
	private int getExp; 	//획득한 경험치
	private int getGold; 	//획득한 금화
	private String characterSkillName; //사용한 스킬 이름
	private int characterDamage; //캐릭터가 준 피해
	private int changeHp; //캐릭터의 Hp 변화
	private int changeMp; //캐릭터의 Mp 변화
	private int changeMonsterHp; //몬스터의 Hp 변화
	private String monsterSkillName; //몬스터가 사용한 스킬 이름
	private int monsterDamage; //몬스터가 준 피해
	//************************김훈**********************
	/**
	 * 처리내용 : 게임 플레이 서비스 분기
	 * 작성일 : 2017. 10. 23.
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object... bean) {
		
		switch(i) {
			case 0:
				mav = movement((GameBean)bean[0]);
				break;
			case 1:
				mav = itemUse((GameBean)bean[0]);
				break;
			case 2:
				mav = itemDisArm((GameBean)bean[0]);
				break;
			case 41: //게임 시작
				mav = gameStart();	
				break;
			case 42: //강화 상점 이동
				mav = enhanceShopEnter();
				break;
			case 43: //강화 상점 아이템 정보 출력
				mav = getItemInformation((GameBean)bean[0]);
				break;
			case 44: //강화 시작
				mav = enhance((GameBean)bean[0]);
				break;
			case 45: //전투 페이지
				mav = battlePage();
				break;
			case 46: //스킬 목록
				mav = skillMenu((GameBean)bean[0], (BattleBean)bean[1]);
				break;
			case 47: //스킬 사용
				mav = useSkill((GameBean)bean[0], (BattleBean)bean[1]);
				break;
			case 48: //상자 열기
				mav = boxOpen();
				break;
				
		}
		
		return mav;
	}
	
	/**
	 * 처리내용 : 장착된 아이템 해제
	 * 작성일 : 2017. 11. 3.
	 * 작성자 : 신태휘
	 * @Method Name : itemDisArm
	 * @return type : ModelAndView
	 */
	private ModelAndView itemDisArm(GameBean bean) {
		GameBean bean2 = new GameBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		Map<String, String> map = new HashMap<String, String>();
		rv = new RedirectView("/CharacterInfo");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		int jobcode = Integer.parseInt(bean.getItcode());
		bean2 = dao.getItemInfo(bean);
		int applyStatus = bean2.getAbility();
		map.put("chName", bean.getChName());
		map.put("idx", "0");
		try {
			bean.setId(session.getAttribute("id").toString());
			bean = dao.getCharacterStatus(bean);
			bean.setChAttack(0);
			bean.setChDefense(0);
			bean.setChHp(0);
			bean.setChMp(0);
			if(jobcode <= 2000) {
				map.put("weapon", "무기");
				if(dao.setItemDisArm(map) != 0) {
					bean.setChAttack(applyStatus);
					if(dao.setItemDisArmStatus(bean) != 0) {
						transaction = true;
					}
				}
			}else if(jobcode <= 3000) {
				map.put("armor", "갑옷");
				bean.setChDefense(applyStatus);
				if(dao.setItemDisArm(map) != 0) {
					if(dao.setItemDisArmStatus(bean) != 0) {
						transaction = true;
					}
				}
			}else if(jobcode <= 4000) {
				map.put("glove", "장갑");
				bean.setChDefense(applyStatus);
				if(dao.setItemDisArm(map) != 0) {
					if(dao.setItemDisArmStatus(bean) != 0) {
						if(dao.setItemDisArmStatus(bean) != 0) {
							transaction = true;
						}
					}
				}
			}else if(jobcode <= 5000) {
				map.put("shoe", "신발");
				bean.setChDefense(applyStatus);
				if(dao.setItemDisArm(map) != 0) {
					if(dao.setItemDisArmStatus(bean) != 0) {
						transaction = true;
					}
				}
			}else if(jobcode <= 6000) {
				map.put("ring", "반지");
				bean.setChHp(applyStatus);
				if(dao.setItemDisArm(map) != 0) {
					if(dao.setItemDisArmStatus(bean) != 0) {
						transaction = true;
					}
				}
			}else if(jobcode <= 7000) {
				map.put("necklace", "목걸이");
				bean.setChMp(applyStatus);
				if(dao.setItemDisArm(map) != 0) {
					if(dao.setItemDisArmStatus(bean) != 0) {
						transaction = true;
					}
				}
			}
			setTransactionResult(transaction);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 처리내용 : 캐릭터 정보 창에서 아이템 장착/사용을 위한 메소드
	 * 작성일 : 2017. 11. 3.
	 * 작성자 : 신태휘
	 * @Method Name : setEquipment
	 * @return type : void
	 */
	private void setEquipment(GameBean bean, int jobCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("content",bean.getChName());
		int requiAbility = 0;			// 장비 필요 능력치
		int chStatus = 0;						// 캐릭터의 해당 능력치
		int applyStatus = 0;			// 캐릭터에 적용할 능력치
		
		if(chStatus >= requiAbility) {
			System.out.println("능력치 적합 여부 확인");
			bean = dao.getItemInfo(bean);
			map.put("useItem", bean.getItcode());
			requiAbility = bean.getRequiAbility();
			applyStatus = bean.getAbility();
			bean = dao.getEquipList(map);
			System.out.println(bean.getEqWeapon() + " :: 2");
			map.put("equipItem", String.valueOf(bean.getEqWeapon()));
			try { bean.setId(session.getAttribute("id").toString());}catch(Exception e) {}
			bean = dao.getCharacterStatus(bean);
			System.out.println(bean.getChName() + "캐릭터 이름 확인");
			switch(jobCode) {
				case 0:
					// 무기 변경
					map.put("weapon", "무기");
					bean.setChAttack(applyStatus);
					bean.setChDefense(0);
					bean.setChHp(0);
					bean.setChMp(0);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}					
					break;
				case 1:
					// 갑옷 변경
					map.put("armor", "갑옷");
					bean.setChAttack(0);
					bean.setChDefense(applyStatus);
					bean.setChHp(0);
					bean.setChMp(0);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}
					break;
				case 2:
					// 장갑 변경
					map.put("glove", "장갑");
					bean.setChAttack(0);
					bean.setChDefense(applyStatus);
					bean.setChHp(0);
					bean.setChMp(0);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}
					break;
				case 3:
					// 신발 변경
					map.put("shoe", "신발");
					bean.setChAttack(0);
					bean.setChDefense(applyStatus);
					bean.setChHp(0);
					bean.setChMp(0);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							System.out.println("chAttack : " + bean.getChAttack());
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}
					break;
				case 4:
					// 반지 변경
					map.put("ring", "반지");
					bean.setChAttack(0);
					bean.setChDefense(0);
					bean.setChHp(applyStatus);
					bean.setChMp(0);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}
					break;
				case 5:
					// 목걸이 변경
					map.put("necklace", "목걸이");
					bean.setChAttack(0);
					bean.setChDefense(0);
					bean.setChHp(0);
					bean.setChMp(applyStatus);
					if(dao.getIsEquip(map) != 0) {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}else {
						if(dao.setEquipItemUpdate(map) != 0) {
							if(dao.setItemApplyStatus(bean) != 0) {
								transaction = true;
							}
						}
					}
					break;
				case 6:
					// 체력 포션 사용
					bean.setChAttack(0);
					bean.setChDefense(0);
					bean.setChHp(applyStatus);
					bean.setChMp(0);
					if(dao.setItemApplyStatus(bean) != 0) {
						if(dao.setAfterItemUse(map) != 0) {
							transaction = true;							
						}
					}
					break;
				case 7:
					// 마나 포션 사용
					bean.setChAttack(0);
					bean.setChDefense(0);
					bean.setChHp(0);
					bean.setChMp(applyStatus);
					if(dao.setItemApplyStatus(bean) != 0) {
						if(dao.setAfterItemUse(map) != 0) {
							transaction = true;							
						}
					}
					break;
			}
			chStatus = bean.getChStr();			
		}
	}

	/**
	 * 처리내용 : 1. 아이템 사용
	 * 작성일 : 2017. 11. 1.
	 * 작성자 : 신태휘
	 * @Method Name : itemUse
	 * @return type : ModelAndView
	 */
	private ModelAndView itemUse(GameBean bean) {
		
		rv = new RedirectView("/CharacterInfo");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		int jobcode = Integer.parseInt(bean.getItcode());
		System.out.println(jobcode + "jobcode");
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		try {
			System.out.println(session.getAttribute("id") + "itemUse");
		if(jobcode <= 2000) {
			System.out.println("무기 입니다.");
			setEquipment(bean, 0);
		}else if(jobcode <= 3000) {
			System.out.println("갑옷류 입니다.");
			setEquipment(bean, 1);
		}else if(jobcode <= 4000) {
			System.out.println("장갑류 입니다.");
			setEquipment(bean, 2);
		}else if(jobcode <= 5000) {
			System.out.println("신발류 입니다.");
			setEquipment(bean, 3);
		}else if(jobcode <= 6000) {
			System.out.println("반지류 입니다.");
			setEquipment(bean, 4);
		}else if(jobcode <= 7000) {
			System.out.println("목걸이류 입니다.");
			setEquipment(bean, 5);
		}else if(jobcode <= 7500) {
			System.out.println("체력포션류 입니다.");
			setEquipment(bean, 6);
		}else{
			System.out.println("마나포션류 입니다.");
			setEquipment(bean, 7);
		}
		setTransactionResult(transaction);
		} catch (Exception e) {
				e.printStackTrace();
			}
		return mav;
	}

	/**
	 * 처리내용 : 0. 던전내부 이동 처리
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : movement
	 * @return type : ModelAndView
	 */
	private ModelAndView movement(GameBean bean) {
		RedirectView rv = null;
		int random = 18; 	//(int) (Math.random()*10)+3;
		int moveValue = Integer.parseInt(bean.getMoveValue());
		if((random%moveValue) == 0) {
			System.out.println("일반 전투");
			// 배틀페이지 이동 RequestMethod = get
			rv = new RedirectView("/GameForm");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}else if((random%moveValue) == 2) {
			System.out.println("보스 스테이지");
			rv = new RedirectView("/GameForm");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}else {
			System.out.println("보물");
			rv = new RedirectView("/GameForm");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}		
		return mav;
	}

	
	//*********************************김훈***********************************
	//상자 열기
		private ModelAndView boxOpen() {
			ModelAndView mav = new ModelAndView();
			boolean transaction = false;
			try {
				
				GameBean gameBean = new GameBean();
				gameBean.setCharacterName((String)session.getAttribute("characterName"));
				gameBean.setUserId((String)session.getAttribute("userId"));
				gameBean.setItemCode(1001); //아이템 코드(단검)
				
				//트랜젝션 설정
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				
				//아이템 획득
				int itemAmount = dao.inventoryItemCheck(gameBean);
				if(itemAmount >= 1) {	//인벤토리에 이미 아이템이 존재한다면
					gameBean.setItemAmount(itemAmount + 1);	//1개 추가
					dao.itemAmountInsert(gameBean);
				}else if(itemAmount == 0) {	//아이템이 존재하지 않는다면
					dao.itemInsert(gameBean);	//아이템 삽입
				}
				//트랜젝션 처리
				transaction = true;
				setTransactionResult(transaction);
				
				mav.addObject("getItem", "단검을 획득하셨습니다.");
			
			}catch(Exception e){
				e.printStackTrace();
			}
			mav.setViewName("dungeon"); //던전 이동
			return mav;
		}


		//캐릭터 다운
		private String characterDown(GameBean gameBean) {
			String viewPage = null;	//리턴할 뷰 페이지
			boolean transaction = false;
			
			System.out.println("당신은 쓰러졌습니다. 마을로 돌아갑니다.");
			
			//금화 소실
			goldDisappear = (int)(gameBean.getGold() * 0.5); //감소할 금화
			gameBean.setGold(goldDisappear);
			
			//트랜젝션 설정
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
			
			dao.goldChange(gameBean); //금화 감소
			
			//트랜젝션 처리
			transaction = true;
			setTransactionResult(transaction);
			
			viewPage = "village"; //마을 이동
			return viewPage;
		}
		
		//몬스터 다운
		private String monsterDown(GameBean gameBean, BattleBean battleBean) {
			String viewPage = null; //리턴할 뷰 페이지
			boolean transaction = false;
			
			System.out.println("몬스터를 죽였습니다.");
			
			
			//트랜젝션 설정
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
			
			//경험치 획득
			getExp = battleBean.getGetExp(); //획득한 경험치
			gameBean.setExp(gameBean.getExp() + getExp);
			dao.characterExp(gameBean);	//경험치 증가
			
			//레벨 업
			if(gameBean.getExp() >= 100 && gameBean.getExp() < 200) {
				gameBean.setLevel(gameBean.getLevel() + 1); //레벨 증가
				gameBean.setHp(gameBean.getTotalHp() + 50); //체력 증가
				gameBean.setMp(gameBean.getTotalMp() + 25); //마력 증가
				gameBean.setStr(gameBean.getStr() + 10); //힘 증가
				gameBean.setDex(gameBean.getDex() + 10); //민첩 증가
				gameBean.setIntelligent(gameBean.getIntelligent() + 10); //지능 증가
				dao.levelUp(gameBean);
				dao.characterSkill4(gameBean); //새로운 스킬 생성
			}
			
			//금화 획득
			getGold = battleBean.getGetGold(); //획득한 금화
			gameBean.setGold(gameBean.getGold() + getGold);
			dao.goldChange(gameBean); //골드 증가
			
			//퀘스트 완료
			gameBean.setQuestCode(battleBean.getMonsterCode());	//퀘스트 코드 저장
			if(dao.myQuestCheck(gameBean) == 1) {	//퀘스트 존재
				dao.myQuestSuccess(gameBean);	//퀘스트 성공
				
			}else {	//받은 퀘스트 없음
				
				
			}
			
			//트랜젝션 처리
			transaction = true;
			setTransactionResult(transaction);
			
			viewPage = "dungeon"; //던전 이동
			return viewPage;
		}
		
		//캐릭터의 공격
		private void characterAttack(GameBean gameBean, BattleBean battleBean) {
			List<GameBean> skillInformation = null;	//캐릭터 스킬 정보
		
			//스킬 사용
			skillInformation = dao.skillInformation(gameBean);	//스킬 정보
			characterSkillName = skillInformation.get(0).getSkillName(); //캐릭터 스킬 이름 저장
			
			//캐릭터의 Mp 소모량 (현재 Mp - 스킬 Mp 소비량)
			changeMp = gameBean.getMp() - skillInformation.get(0).getSkillConsume();

			//몬스터에게 주는 피해 (몬스터 체력 - (캐릭터 힘 + 캐릭터 공격력 + 캐릭터 스킬 공격력) - 몬스터 방어력))
			characterDamage = gameBean.getStr() + gameBean.getAttack() + skillInformation.get(0).getSkillDamage() - battleBean.getMonsterDefense();
			if(characterDamage < 0) {	
				//몬스터의 방어력이 캐릭터가 주는 피해보다 높은 경우
				characterDamage = 0;
			}else {
				//체력 감소
				changeMonsterHp = battleBean.getMonsterHp() - characterDamage;
			}
		}
		
		//일반 몬스터의 공격
		private void monsterAttack(GameBean gameBean, BattleBean battleBean) {
			List<BattleBean> monsterSkillInformation = null;	//몬스터 스킬 정보
			
			//몬스터 스킬에 대한 정보
			monsterSkillInformation = dao.monsterSkill(battleBean);
			
			if(battleBean.getMonsterCode() == 3) {	//암사자(몬스터)
				int index = (int)((Math.random() * 2) + 1);	//사용하는 스킬 랜덤
				if(index == 1) {	//후려치기
					monsterSkillName = monsterSkillInformation.get(0).getMonsterSkill(); //몬스터 스킬 이름 저장
					
					//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
					monsterDamage = battleBean.getMonsterAttack() + monsterSkillInformation.get(0).getMonsterDamage() - gameBean.getDefense();
					if(monsterDamage < 0 ) {
						//캐릭터의 방어력이 몬스터가 주는 피해보다 높은 경우
						monsterDamage = 0;
					}else {
						//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
						changeHp = gameBean.getHp() - monsterDamage;
					}
				}else if(index == 2) {	//물어뜯기(추가공격력 10)
					monsterSkillName = monsterSkillInformation.get(1).getMonsterSkill(); //몬스터 스킬 이름 저장

					//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
					monsterDamage = battleBean.getMonsterAttack() + monsterSkillInformation.get(1).getMonsterDamage() - gameBean.getDefense();
					if(monsterDamage < 0 ) {
						//캐릭터의 방어력이 몬스터가 주는 피해보다 높은 경우
						monsterDamage = 0;
					}else {
						//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
						changeHp = gameBean.getHp() - monsterDamage;
					}
				}
			}else {	//암사자가 아닌 다른 몬스터
				monsterSkillName = monsterSkillInformation.get(0).getMonsterSkill(); //몬스터 스킬 이름 저장

				//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
				monsterDamage = battleBean.getMonsterAttack() + monsterSkillInformation.get(0).getMonsterDamage() - gameBean.getDefense();
				if(monsterDamage < 0 ) {
					//캐릭터의 방어력이 몬스터가 주는 피해보다 높은 경우
					monsterDamage = 0;
				}else {
					//캐릭터에게 주는 피해 (캐릭터 체력 - (몬스터 공격력 + 몬스터 스킬 공격력) - 캐릭터 방어력))
					changeHp = gameBean.getHp() - monsterDamage;
				}
			}
		}

		//스킬 사용
		private ModelAndView useSkill(GameBean gameBean, BattleBean battleBean) {
			ModelAndView mav = new ModelAndView();
			String viewPage = "battlePage";	//뷰 페이지
			try {
				//체력 확인
				if(gameBean.getHp() <= 0) {	//유저의 체력이 0 이하
					viewPage = characterDown(gameBean); //캐릭터 다운
				}else if(battleBean.getMonsterHp() <= 0) {	//몬스터의 체력이 0 이하
					viewPage = monsterDown(gameBean, battleBean); //몬스터 다운
				}else {	//서로의 민첩 비교 (선 후공 결정)

					//*****캐릭터 선공******
					if(gameBean.getDex() > battleBean.getMonsterDex()) {	
						
						//***캐릭터의 공격***
						characterAttack(gameBean, battleBean);
						gameBean.setMp(changeMp); //변경된 캐릭터의 Mp 저장
						battleBean.setMonsterHp(changeMonsterHp); //변경된 몬스터의 체력 저장

						//유저의 공격을 받고 몬스터의 체력이 0이하가 된 경우
						if(battleBean.getMonsterHp() <= 0) {	
							viewPage = monsterDown(gameBean, battleBean); //몬스터 다운
						}else {
							//***몬스터 공격***
							monsterAttack(gameBean, battleBean);
							gameBean.setHp(changeHp); //변경된 캐릭터의 Hp 저장
							
						}
						
						//몬스터의 공격을 받고 캐릭터의 체력이 0이 될 경우
						if(gameBean.getHp() <= 0) {
							viewPage = characterDown(gameBean); //캐릭터 다운
						}
					//*****몬스터 선공******
					}else if(gameBean.getDex() < battleBean.getMonsterDex()) {	
						
						//***몬스터 공격***
						monsterAttack(gameBean, battleBean);
						gameBean.setHp(changeHp); //변경된 캐릭터의 Hp 저장
						
						
						//몬스터의 공격을 받고 캐릭터의 체력이 0이 될 경우
						if(gameBean.getHp() <= 0) {
							viewPage = characterDown(gameBean); //캐릭터 다운
						}else {
							
							//***캐릭터의 공격***
							characterAttack(gameBean, battleBean);
							gameBean.setMp(changeMp); //변경된 캐릭터의 Mp 저장
							battleBean.setMonsterHp(changeMonsterHp); //변경된 몬스터의 체력 저장
						}
						
						//유저의 공격을 받고 몬스터의 체력이 0이하가 된 경우
						if(battleBean.getMonsterHp() <= 0) {	
							viewPage = monsterDown(gameBean, battleBean); //몬스터 다운
						}
					//*****민첩이 같을 경우*****
					}else if(gameBean.getDex() == battleBean.getMonsterDex()) {	
						//선공 랜덤으로 결정
						int first = (int)((Math.random() * 2) + 1);	
							
						if(first == 1) {	//유저 선공
							
							//***캐릭터의 공격***
							characterAttack(gameBean, battleBean);
							gameBean.setMp(changeMp); //변경된 캐릭터의 Mp 저장
							battleBean.setMonsterHp(changeMonsterHp); //변경된 몬스터의 체력 저장

							//유저의 공격을 받고 몬스터의 체력이 0이하가 된 경우
							if(battleBean.getMonsterHp() <= 0) {	
								viewPage = monsterDown(gameBean, battleBean); //몬스터 다운
							}else {
								
								//***몬스터 공격***
								monsterAttack(gameBean, battleBean);
								gameBean.setHp(changeHp); //변경된 캐릭터의 Hp 저장
								
							}
							
							//몬스터의 공격을 받고 캐릭터의 체력이 0이 될 경우
							if(gameBean.getHp() <= 0) {
								viewPage = characterDown(gameBean); //캐릭터 다운
							}
						}else if(first == 2) {	//몬스터 선공
							
							//***몬스터 공격***
							monsterAttack(gameBean, battleBean);
							gameBean.setHp(changeHp); //변경된 캐릭터의 Hp 저장
							
							//몬스터의 공격을 받고 캐릭터의 체력이 0이 될 경우
							if(gameBean.getHp() <= 0) {
								viewPage = characterDown(gameBean); //캐릭터 다운
							}else {
								
								//***캐릭터의 공격***
								characterAttack(gameBean, battleBean);
								gameBean.setMp(changeMp); //변경된 캐릭터의 Mp 저장
								battleBean.setMonsterHp(changeMonsterHp); //변경된 몬스터의 체력 저장
							}
							
							//유저의 공격을 받고 몬스터의 체력이 0이하가 된 경우
							if(battleBean.getMonsterHp() <= 0) {	
								viewPage = monsterDown(gameBean, battleBean); //몬스터 다운
							}
						}
					}
				}

				//사용자에게 보여줄 정보
				mav.addObject("characterSkillName", characterSkillName);	//사용한 스킬 이름
				mav.addObject("monsterSkillName", monsterSkillName);	//몬스터가 사용한 스킬 이름
				mav.addObject("characterDamage", characterDamage);	//캐릭터가 준 피해
				mav.addObject("monsterDamage", monsterDamage);	//몬스터가 준 피해
				
				//캐릭터 정보 넘기기
				mav.addObject("characterName", gameBean.getCharacterName());
				mav.addObject("characterId", gameBean.getUserId());
				mav.addObject("characterSex", gameBean.getSex());
				mav.addObject("characterLevel", gameBean.getLevel());
				mav.addObject("characterExp", gameBean.getExp());
				mav.addObject("characterTotalHp", gameBean.getTotalHp());
				mav.addObject("characterHp", gameBean.getHp());
				mav.addObject("characterTotalMp", gameBean.getTotalMp());
				mav.addObject("characterMp", gameBean.getMp());
				mav.addObject("characterStr", gameBean.getStr());
				mav.addObject("characterDex", gameBean.getDex());
				mav.addObject("characterInt", gameBean.getIntelligent());
				mav.addObject("characterAttack", gameBean.getAttack());
				mav.addObject("characterDefense", gameBean.getDefense());
				mav.addObject("characterGold", gameBean.getGold());

				//몬스터 정보 넘기기
				mav.addObject("monsterCode", battleBean.getMonsterCode());
				mav.addObject("monsterName", battleBean.getMonsterName());
				mav.addObject("monsterTotalHp", battleBean.getMonsterTotalHp());
				mav.addObject("monsterHp", battleBean.getMonsterHp());
				mav.addObject("monsterDex", battleBean.getMonsterDex());
				mav.addObject("monsterAttack", battleBean.getMonsterAttack());
				mav.addObject("monsterDefense", battleBean.getMonsterDefense());
				mav.addObject("getExp", battleBean.getGetExp());
				mav.addObject("getGold", battleBean.getGetGold());
				mav.addObject("fieldCode", battleBean.getFieldCode());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName(viewPage);
			return mav;
		}

		//스킬 목록
		private ModelAndView skillMenu(GameBean gameBean, BattleBean battleBean) {
			ModelAndView mav = new ModelAndView();
			List<GameBean> characterSkillMenu = null;
			try {
				String characterName = (String)session.getAttribute("characterName");


				//캐릭터 스킬 가져오기
				characterSkillMenu = dao.characterSkillMenu(characterName);	
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < characterSkillMenu.size(); i++) {
					sb.append("<button id=\"skillButton" + i + "\" onClick=\"useSkill(\'" + characterSkillMenu.get(i).getSkillCode() + "\')\">" + characterSkillMenu.get(i).getSkillName() + "</button>");
				}
				mav.addObject("characterSkillMenu", sb.toString());
				
				//캐릭터 정보 넘기기
				mav.addObject("characterName", gameBean.getCharacterName());
				mav.addObject("characterId", gameBean.getUserId());
				mav.addObject("characterSex", gameBean.getSex());
				mav.addObject("characterLevel", gameBean.getLevel());
				mav.addObject("characterExp", gameBean.getExp());
				mav.addObject("characterTotalHp", gameBean.getTotalHp());
				mav.addObject("characterHp", gameBean.getHp());
				mav.addObject("characterTotalMp", gameBean.getTotalMp());
				mav.addObject("characterMp", gameBean.getMp());
				mav.addObject("characterStr", gameBean.getStr());
				mav.addObject("characterDex", gameBean.getDex());
				mav.addObject("characterInt", gameBean.getIntelligent());
				mav.addObject("characterAttack", gameBean.getAttack());
				mav.addObject("characterDefense", gameBean.getDefense());
				mav.addObject("characterGold", gameBean.getGold());

				//몬스터 정보 넘기기
				mav.addObject("monsterCode", battleBean.getMonsterCode());
				mav.addObject("monsterName", battleBean.getMonsterName());
				mav.addObject("monsterTotalHp", battleBean.getMonsterTotalHp());
				mav.addObject("monsterHp", battleBean.getMonsterHp());
				mav.addObject("monsterDex", battleBean.getMonsterDex());
				mav.addObject("monsterAttack", battleBean.getMonsterAttack());
				mav.addObject("monsterDefense", battleBean.getMonsterDefense());
				mav.addObject("getExp", battleBean.getGetExp());
				mav.addObject("getGold", battleBean.getGetGold());
				mav.addObject("fieldCode", battleBean.getFieldCode());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("characterSkillMenu");
			return mav;
		}

		//전투 페이지 이동
		private ModelAndView battlePage() {
			ModelAndView mav = new ModelAndView();
			List<GameBean> characterInformation = null;
			List<BattleBean> monsterInformation = null;
			try {
				String characterName = (String)session.getAttribute("characterName");

				//캐릭터 정보 가져오기
				characterInformation = dao.characterInformation(characterName);

				mav.addObject("characterName", characterInformation.get(0).getCharacterName());
				mav.addObject("characterId", characterInformation.get(0).getUserId());
				mav.addObject("characterSex", characterInformation.get(0).getSex());
				mav.addObject("characterLevel", characterInformation.get(0).getLevel());
				mav.addObject("characterExp", characterInformation.get(0).getExp());
				mav.addObject("characterHp", characterInformation.get(0).getHp());
				mav.addObject("characterMp", characterInformation.get(0).getMp());
				mav.addObject("characterStr", characterInformation.get(0).getStr());
				mav.addObject("characterDex", characterInformation.get(0).getDex());
				mav.addObject("characterInt", characterInformation.get(0).getIntelligent());
				mav.addObject("characterAttack", characterInformation.get(0).getAttack());
				mav.addObject("characterDefense", characterInformation.get(0).getDefense());
				mav.addObject("characterGold", characterInformation.get(0).getGold());
				//mav.addObject("characterGuildCode", characterInformation.get(0).getGuildCode());
				//mav.addObject("characterGuildLevelCode", characterInformation.get(0).getGuildLevelCode());

				//몬스터 정보 가져오기
				int monsterCode = 0;
				if(characterInformation.get(0).getLevel() == 1) {
					monsterCode = (int)((Math.random() * 5) + 1); // 1번 ~ 5번
					//System.out.println(monsterCode);
				}else {
					monsterCode = (int)((Math.random() * 2) + 6);	// 6번 ~ 7번
					//System.out.println(monsterCode);
				}
				
				monsterInformation = dao.monsterInformation(monsterCode);

				mav.addObject("monsterCode", monsterInformation.get(0).getMonsterCode());
				mav.addObject("monsterName", monsterInformation.get(0).getMonsterName());
				mav.addObject("monsterHp", monsterInformation.get(0).getMonsterHp());
				mav.addObject("monsterDex", monsterInformation.get(0).getMonsterDex());
				mav.addObject("monsterAttack", monsterInformation.get(0).getMonsterAttack());
				mav.addObject("monsterDefense", monsterInformation.get(0).getMonsterDefense());
				mav.addObject("getExp", monsterInformation.get(0).getGetExp());
				mav.addObject("getGold", monsterInformation.get(0).getGetGold());
				mav.addObject("fieldCode", monsterInformation.get(0).getFieldCode());

				//캐릭터 전체 체력, 전체 마력, 몬스터 전체 체력
				mav.addObject("characterTotalHp", characterInformation.get(0).getHp());
				mav.addObject("characterTotalMp", characterInformation.get(0).getMp());
				mav.addObject("monsterTotalHp", monsterInformation.get(0).getMonsterHp());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("battlePage");
			return mav;
		}

		//강화 시작
		private ModelAndView enhance(GameBean gameBean) {
			ModelAndView mav = new ModelAndView();
			boolean transaction = false;
			List<GameBean> listGameBean = null;
			DecimalFormat format = new DecimalFormat(); //자리수 지정
			format.applyLocalizedPattern("0.0");	//자리수 패턴
			try {
				String characterName = (String)session.getAttribute("characterName");
				
				//강화석 존재 유무 확인
				if(dao.meterialCheck(characterName) != 0) {
					//소지한 강화석 개수 가져오기
					int possessMaterial = dao.getMaterial(characterName);
					//System.out.println(possessMaterial);

					//트랜젝션 설정
					setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

					//현재 강화석 = 소유한 강화석  - 필요한 강화석 
					int resultMaterial = possessMaterial - gameBean.getMaterial();
					
					//소유한 강화석  - 필요한 강화석 = 0이라면
					if(resultMaterial == 0) {
						gameBean.setCharacterName(characterName);
						gameBean.setItemCode(8001);
						if(dao.deleteItem(gameBean) == 1) {
							//System.out.println("삭제");
						}else {
							//System.out.println("실패");
						}
					//강화석 소모	
					}else if(resultMaterial > 0) {
						System.out.println("강화를 시작합니다.");

						gameBean.setItemAmount(resultMaterial);	//인벤토리 수정을 위해 빈에 강화 후 강화석 개수 저장
						gameBean.setCharacterName(characterName);	//인벤토리 수정을 위한 캐릭터 이름 빈에 저장

						if(dao.updateMaterial(gameBean) == 1) {	//인벤토리 강화석 수정
							if(enhanceSuccess(gameBean)) {	//강화 성공 확률 계산 메소드
								System.out.println("강화 성공!!!!");
								dao.updateEnhanceLevel(gameBean);	//인벤토리 아이템 강화레벨 수정
								mav.addObject("message", "*강화에 성공했습니다!");
							}else {	//강화 실패
								System.out.println("강화 실패!!!!");
								int chooseItemAmount = dao.getInventoryItemAmount(gameBean); //강화한 아이템의 개수
								if(chooseItemAmount > 1) {	//강화 한 아이템의 개수가 1보다 많으면 아이템 개수 감소
									gameBean.setItemAmount(chooseItemAmount - 1);
									dao.decreaseItem(gameBean);
								}else {	//1이하라면 아이템 삭제
									dao.deleteItem(gameBean);
								}
								mav.addObject("message", "*강화에 실패했습니다!");
							}
						}else {
							System.out.println("강화석 감소 메소드 실행 오류");
						}
					}else {
						System.out.println("강화석이 부족합니다.");
						mav.addObject("message", "*강화석이 부족합니다.");
					}

					//트랜젝션 처리
					transaction = true;
					setTransactionResult(transaction);
				}else {
					System.out.println("강화석이 존재하지 않습니다.");
					mav.addObject("message", "*강화석이 존재하지 않습니다.");
				}
					
				listGameBean = dao.getInventory(characterName);	//캐릭터의 인벤토리 가져오기

				//소지한 아이템 목록
				StringBuffer sb = new StringBuffer();
				sb.append("<br/><br/>");
				for(int i = 0; i < listGameBean.size(); i++) {
					gameBean = listGameBean.get(i);
					if(gameBean.getItemName().equals("0")) {
						
					}else {
						sb.append("<a onClick=\"choose(\'" + gameBean.getItemCode() + "\', \'"  + gameBean.getEnhanceLevel() +  "\')\">· " + gameBean.getItemName() + " (" + gameBean.getItemAmount() + "개)</a><br/>");
					}
				}
				mav.addObject("userInventory", sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("enhanceShop");
			return mav;
		}

		//강화 상점 아이템 정보 출력
		private ModelAndView getItemInformation(GameBean gameBean) {
			ModelAndView mav = new ModelAndView();
			List<GameBean> listGameBean = null;
			List<GameBean> listEnhanceLevel = null;
			DecimalFormat format = new DecimalFormat(); //자리수 지정
			format.applyLocalizedPattern("0.0");	//자리수 패턴
			try {
				listGameBean = dao.getChooseItem(gameBean);	//선택된 아이템 가져오기

				int enhanceLevel = listGameBean.get(0).getEnhanceLevel() + 1;	//다음 강화 레벨 정보

				listEnhanceLevel = dao.getEnhanceLevel(enhanceLevel);	//강화 레벨에 해당하는 강화석, 증가량, 확률 가져오기

				StringBuffer sb = new StringBuffer();
				sb.append("<div id=\"currentItemDiv\">");
				sb.append("<table>");
				sb.append("<br/>");
				sb.append("<tr><td id=\"enhanceLevel\" data-db=" + listGameBean.get(0).getEnhanceLevel() + ">· 현재 강화 레벨 : " + listGameBean.get(0).getEnhanceLevel() + "</td></tr>");
				sb.append("<tr><td id=\"itemCode\" data-db=" + listGameBean.get(0).getItemCode() + ">· 아이템 이름 : " + listGameBean.get(0).getItemName() + "</td></tr>");
				sb.append("<tr><td id=\"requireAbility\" data-db=" + listGameBean.get(0).getRequireAbility() + ">· 요구 능력치 : " + listGameBean.get(0).getRequireAbility() + "</td></tr>");
				sb.append("<tr><td id=\"upAbility\" data-db=" + listGameBean.get(0).getUpAbility() + ">· 현재 능력치 : " + format.format(listGameBean.get(0).getUpAbility() * (listEnhanceLevel.get(0).getIncrease() - 0.1)) + "</td></tr>");
				sb.append("</table>");
				sb.append("<br/>");
				sb.append("<button onClick=\"enhance()\" class=\"button\">강화</button>");
				sb.append("</div>");
				sb.append("<div id=\"enhanceItemDiv\">");
				sb.append("<table>");
				sb.append("<br/>");
				sb.append("<tr><td id=\"itemAmount\" data-db=" + listGameBean.get(0).getItemAmount() + ">· 증가 강화 레벨 : " + listEnhanceLevel.get(0).getEnhanceLevel() + "</td></tr>");
				sb.append("<tr><td id=\"material\" data-db=" + listEnhanceLevel.get(0).getMaterial() + ">· 요구 강화석 : " + listEnhanceLevel.get(0).getMaterial() + "개</td></tr>");
				sb.append("<tr><td id=\"enHanceChance\" data-db=" + listEnhanceLevel.get(0).getEnHanceChance() + ">· 성공 확률 : " + (int)(listEnhanceLevel.get(0).getEnHanceChance() * 100) + "%</td></tr>");
				sb.append("<tr><td id=\"increase\" data-db=" + listEnhanceLevel.get(0).getIncrease() + ">· 증가 능력치 : " + format.format(listGameBean.get(0).getUpAbility() * listEnhanceLevel.get(0).getIncrease()) + "</td></tr>");
				sb.append("</table>");
				sb.append("</div>");
				mav.addObject("itemInformation", sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("itemInformation");
			return mav;
		}

		//강화 상점 이동
		private ModelAndView enhanceShopEnter() {
			ModelAndView mav = new ModelAndView();
			List<GameBean> listGameBean = null;
			GameBean gameBean = null;
			try {
				String characterName = (String)session.getAttribute("characterName");	//세션의 캐릭터 이름 가져오기
				listGameBean = dao.getInventory(characterName);	//캐릭터의 인벤토리 가져오기

				//소지한 아이템 목록
				StringBuffer sb = new StringBuffer();
				sb.append("<br/><br/>");
				for(int i = 0; i < listGameBean.size(); i++) {
					gameBean = listGameBean.get(i);
					if(gameBean.getItemName().equals("0")) {
						
					}else {
						sb.append("<a onClick=\"choose(\'" + gameBean.getItemCode() + "\', \'"  + gameBean.getEnhanceLevel() +  "\')\">· " + gameBean.getItemName() + " (" + gameBean.getItemAmount() + "개)</a><br/>");
					}
				}
				mav.addObject("userInventory", sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("enhanceShop");
			return mav;
		}

		//게임 시작
		private ModelAndView gameStart() {
			ModelAndView mav = new ModelAndView();
			try {
				String userId = (String)session.getAttribute("id");
				
				GameBean gameBean = new GameBean();
				gameBean.setUserId(userId);
				
				if(dao.characterIdCheck(gameBean) == 1) {	//캐릭터 아이디 존재
					//세션에 캐릭터 이름 저장
					session.setAttribute("characterName", dao.getCharacterName(userId));

					mav.setViewName("village");
				}else {
					mav.setViewName("home");
					mav.addObject("message", "*먼저 캐릭터를 생성해 주세요.");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return mav;
		}

		//강화 성공 여부
		private Boolean enhanceSuccess(GameBean gameBean) {
			Boolean result = false;
			int chance = (int)(gameBean.getEnHanceChance() * 10);
			int random = (int)((Math.random() * 10) + 1);
			if(random <= chance) {
				gameBean.setEnhanceLevel(gameBean.getEnhanceLevel() + 1); //아이템 강화 레벨 증가
				System.out.println("chance : " + chance);
				System.out.println("random : " + random);
				result = true;
			}else {
				System.out.println("chance : " + chance);
				System.out.println("random : " + random);
			}
			return result;
		}
		
		//*********************************김훈***********************************
}
