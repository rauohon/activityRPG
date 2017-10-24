/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.GameBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : GameNomalService
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class GameNomalService {
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	/**
	 * 처리내용 : 게임 일반 서비스 분기
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {		

		switch(i) {

		case 0:
			mav = backPage((GameBean)bean);
			break;
		case 1:
			mav = villagePage((GameBean)bean);
			break;
		case 2:
			mav = questPage((GameBean)bean);
			break;
		case 3:
			mav = guildPage((GameBean)bean);
			break;
		case 4:
			mav = dungeonPage((GameBean)bean);
			break;
		case 5:
			mav = characterInfoPage((GameBean)bean);
			break;
		}


		return mav;


	}

	/**
	 * 처리내용 : 5-7 소지한 강화석을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : ehanceItemList
	 * @return type : String
	 */
	private String ehanceItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("강화석 : 90");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-6 소지한 포션을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : potionItemList
	 * @return type : String
	 */
	private String potionItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("작은 체력 포션 : 90\t");
		sb.append("작은 마나 포션 : 90");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-5 소지한 방어구 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : armorItemList
	 * @return type : String
	 */
	private String armorItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("천갑옷\t");
		sb.append("천장갑\n");
		sb.append("천장화\t");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-4 소지한 무기 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : weaponItemList
	 * @return type : String
	 */
	private String weaponItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("대검\t");
		sb.append("엑스칼리버");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-3 착용한 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : equipedList
	 * @return type : String
	 */
	private String equipedList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("머리 : 999\t");
		sb.append("몸 : 999\n");
		sb.append("장갑 : 999\t");
		sb.append("발 : 999\n");
		sb.append("반지 : 999\t");
		sb.append("목걸이 : 999\t");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-2 경험치를 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterExp
	 * @return type : String
	 */
	private String characterExp(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("현재 : 999\t");
		sb.append("필요 : 999\n");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-1 캐릭터의 능력치를 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterStatus
	 * @return type : String
	 */
	private String characterStatus(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("힘 : 999\t");
		sb.append("민첩 : 999\n");
		sb.append("지능 : 999\t");
		sb.append("공격력 : 999\n");
		sb.append("방어력 : 999\t");
		sb.append("체력 : 999\t");
		sb.append("마법력 : 999");	

		return sb.toString();
	}
	
	/**
	 * 처리내용 : 5-0 캐릭터의 이미지를 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterImage
	 * @return type : String
	 */
	private String characterImage(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("사진이 출력 됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5. characterInfoPage
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterInfoPage
	 * @return type : ModelAndView
	 */
	private ModelAndView characterInfoPage(GameBean bean) {
		
		mav.addObject("characterImage", characterImage(bean));
		mav.addObject("characterStatus", characterStatus(bean));
		mav.addObject("characterExp", characterExp(bean));
		mav.addObject("equipedList", equipedList(bean));
		mav.addObject("weaponItemList", weaponItemList(bean));
		mav.addObject("armorItemList", armorItemList(bean));
		mav.addObject("potionItemList", potionItemList(bean));
		mav.addObject("ehanceItemList", ehanceItemList(bean));
		mav.setViewName("characterInfo");

		return mav;
	}
	
	/**
	 * 처리내용 : 던전의 이미지를 가지고 와서 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : dungeonImage
	 * @return type : String
	 */
	private String dungeonImage(GameBean bean) {
		
		return "던전 image가 출력됩니다.";
	}

	/**
	 * 처리내용 : 4. dungeonPage
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : DungeonPage
	 * @return type : ModelAndView
	 */
	private ModelAndView dungeonPage(GameBean bean) {
		
		mav.addObject("dungeonImage", dungeonImage(bean));
		mav.setViewName("dungeon");
		try {
			session.setAttribute("page", "dungeon");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 처리내용 : 3. guildPage
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : guildPage
	 * @return type : ModelAndView
	 */
	private ModelAndView guildPage(GameBean bean) {
		//if("길드 가입 여부 확인"){
		// 가입 안했으면
		mav.setViewName("guildCreate");
		//}else{
		// 가입 했으면
		//}
		return mav;
	}

	/**
	 * 처리내용 : 2. questPage 호출
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : QuestPage
	 * @return type : ModelAndView
	 */
	private ModelAndView questPage(GameBean bean) {

		mav.setViewName("quest");

		return mav;
	}

	/**
	 * 처리내용 : 1. villagePage 호출
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : villagePage
	 * @return type : ModelAndView
	 */
	private ModelAndView villagePage(GameBean bean) {
		//if(캐릭터 존재여부 확인)
		mav.setViewName("village");
//		try {
//			session.setAttribute("page", "village");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return mav;
	}

	/**
	 * 처리내용 : 0. 호출 한 Page 호출
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : backPage
	 * @return type : ModelAndView
	 */
	private ModelAndView backPage(GameBean bean) {
		String callPage = null;
		try {
			if(session.getAttribute("page") != null) {
				callPage = session.getAttribute("page").toString();
				mav.setViewName(callPage);
				session.removeAttribute("page");
			}else {
				mav.setViewName("village");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
