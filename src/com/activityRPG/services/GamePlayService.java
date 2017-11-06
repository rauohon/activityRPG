package com.activityRPG.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	
	/**
	 * 처리내용 : 게임 플레이 서비스 분기
	 * 작성일 : 2017. 10. 23.
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {
		
		switch(i) {
			case 0:
				mav = movement((GameBean)bean);
				break;
			case 1:
				mav = itemUse((GameBean)bean);
				break;
			case 2:
				mav = itemDisArm((GameBean)bean);
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

}
