/**
 * 
 */
package com.activityRPG.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		case 6:
			mav = itemInfo((GameBean)bean);
			break;
		}


		return mav;


	}

	/**
	 * 처리내용 : 6. 아이템 정보를 리턴
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : itemInfo
	 * @return type : ModelAndView
	 */
	private ModelAndView itemInfo(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", bean.getItcode());
		bean = dao.getItemInfo(map);
		sb.append("<table><tr><th>이름</th><th>향상 능력치</th><th>필요 능력치</th></tr><tr><td>");
		sb.append(bean.getItname());
		sb.append("</td><td>");
		sb.append(bean.getAbility());
		sb.append("</td><td>");
		sb.append(bean.getRequiAbility());
		sb.append("</td></tr></table>");
		mav.addObject("itemInfo", sb.toString());
		
		mav.setViewName("itemInfo");
		
		return mav;
	}

	/**
	 * 처리내용 : 5-6 소지한 강화석을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : ehanceItemList
	 * @return type : String
	 */
	private String ehanceItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", bean.getChName());
		map.put("min", "8001");
		map.put("max", "9000");
		List<GameBean> invenList = dao.getIvenList(map);
		sb.append("<table><tr><th>강화석</th><th>보유 갯수</th></tr>");
		for(int i = 0 ; i < invenList.size() ; i++) {
			sb.append("<tr><td>");
			sb.append(invenList.get(i).getItname());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAmount());
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 5-5 소지한 포션을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : potionItemList
	 * @return type : String
	 */
	private String potionItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", bean.getChName());
		map.put("min", "7001");
		map.put("max", "8000");
		List<GameBean> invenList = dao.getIvenList(map);
		sb.append("<table><tr><th>포션 이름</th><th>회복 능력치</th><th>보유 갯수</th></tr>");
		for(int i = 0 ; i < invenList.size() ; i++) {
			sb.append("<tr onClick=\'startAjax(\""+invenList.get(i).getItcode()+"\")\'><td>");
			sb.append(invenList.get(i).getItname());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAbility());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAmount());
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 5-4 소지한 방어구 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : armorItemList
	 * @return type : String
	 */
	private String armorItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", bean.getChName());
		map.put("min", "2001");
		map.put("max", "7000");
		List<GameBean> invenList = dao.getIvenList(map);
		sb.append("<table><tr><th>+</th><th>방어구 이름</th><th>추가 능력치</th><th>보유 갯수</th></tr>");
		for(int i = 0 ; i < invenList.size() ; i++) {
			sb.append("<tr onClick=\'startAjax(\""+invenList.get(i).getItcode()+"\")\'><td>");
			sb.append(invenList.get(i).getEnlevel());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getItname());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAbility());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAmount());
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 5-3 소지한 무기 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : weaponItemList
	 * @return type : String
	 */
	private String weaponItemList(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", bean.getChName());
		map.put("min", "1001");
		map.put("max", "2000");
		List<GameBean> invenList = dao.getIvenList(map);
		sb.append("<table><tr><th>+</th><th>무기 이름</th><th>추가 능력치</th><th>보유 갯수</th></tr>");
		for(int i = 0 ; i < invenList.size() ; i++) {
			sb.append("<tr onClick=\'startAjax(\""+invenList.get(i).getItcode()+"\")\'><td>");
			sb.append(invenList.get(i).getEnlevel());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getItname());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAbility());
			sb.append("</td><td>");
			sb.append(invenList.get(i).getAmount());
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 5-2 착용한 아이템을 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : equipedList
	 * @return type : String
	 */
	private Map<String, String> equipedMap(GameBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("content",bean.getChName());
		List<GameBean> equipList = dao.getEquipList(map);
		map.put("weapon", "<h4 onmouseout=\'hideDiv()\' onClick=\'startAjax(\""+equipList.get(0).getItcode()+"\")\'>"+equipList.get(0).getItname()+"</h4>");
		if(equipList.get(0).getEnlevel() != 0) {
			map.put("weaponEn", "+"+String.valueOf(equipList.get(0).getEnlevel()));
		}
		map.put("armor", "<h4 onClick=\'startAjax(\""+equipList.get(1).getItcode()+"\")\'>"+equipList.get(1).getItname());
		if(equipList.get(1).getEnlevel() != 0) {
			map.put("armorEn", "+"+String.valueOf(equipList.get(1).getEnlevel()));
		}
		map.put("glove", "<h4 onClick=\'startAjax(\""+equipList.get(2).getItcode()+"\")\'>"+equipList.get(2).getItname());
		if(equipList.get(2).getEnlevel() != 0) {
			map.put("gloveEn", "+"+String.valueOf(equipList.get(2).getEnlevel()));
		}
		map.put("shoe", "<h4 onClick=\'startAjax(\""+equipList.get(3).getItcode()+"\")\'>"+equipList.get(3).getItname());
		if(equipList.get(3).getEnlevel() != 0) {
			map.put("shoeEn", "+"+String.valueOf(equipList.get(3).getEnlevel()));
		}
		map.put("ring", "<h4 onClick=\'startAjax(\""+equipList.get(4).getItcode()+"\")\'>"+equipList.get(4).getItname());
		if(equipList.get(4).getEnlevel() != 0) {
			map.put("ringEn", "+"+String.valueOf(equipList.get(4).getEnlevel()));
		}
		map.put("necklace", "<h4 onClick=\'startAjax(\""+equipList.get(5).getItcode()+"\")\'>"+equipList.get(5).getItname());
		if(equipList.get(5).getEnlevel() != 0) {
			map.put("necklaceEn", "+"+String.valueOf(equipList.get(5).getEnlevel()));
		}
		
		return map;
	}

	/**
	 * 처리내용 : 5-1 캐릭터의 능력치를 불러와 출력
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterStatus
	 * @return type : String
	 */
	private GameBean characterStatus(GameBean bean) {
		StringBuffer sb = new StringBuffer();
		
		bean = dao.getCharacterStatus(bean);

		return bean;
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
				
//		System.out.println(map.get("weapon") + "메소드 테스트");
		bean = characterStatus(bean);
		Map<String, String> map = equipedMap(bean);
		mav.addObject("chName", bean.getChName());
		mav.addObject("chLevel", bean.getChLevel());
		mav.addObject("chExp", bean.getChExp());
		mav.addObject("chHp", bean.getChHp());
		mav.addObject("chMp", bean.getChMp());
		mav.addObject("chStr", bean.getChStr());
		mav.addObject("chDex", bean.getChDex());
		mav.addObject("chInt", bean.getChInt());
		mav.addObject("chAttack", bean.getChAttack());
		mav.addObject("chDefense", bean.getChDefense());
		mav.addObject("chGold", bean.getChGold());
		mav.addObject("weapon",map.get("weapon"));
		mav.addObject("weaponEn",map.get("weaponEn"));
		mav.addObject("armor",map.get("armor"));
		mav.addObject("armorEn",map.get("armorEn"));
		mav.addObject("glove",map.get("glove"));
		mav.addObject("gloveEn",map.get("gloveEn"));
		mav.addObject("shoe",map.get("shoe"));
		mav.addObject("shoeEn",map.get("shoeEn"));
		mav.addObject("ring",map.get("ring"));
		mav.addObject("ringEn",map.get("ringEn"));
		mav.addObject("necklace",map.get("necklace"));
		mav.addObject("necklaceEn",map.get("necklaceEn"));
		mav.addObject("weaponItemList", weaponItemList(bean));
		mav.addObject("armorItemList", armorItemList(bean));
		mav.addObject("potionItemList", potionItemList(bean));
		mav.addObject("ehanceItemList", ehanceItemList(bean));
		mav.addObject("characterImage", characterImage(bean));
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
		// 로그인 여부 확인
		try {
			if(session.getAttribute("id") != null) {
				mav.setViewName("village");
				session.setAttribute("page", "village");
			}else {
				mav.setViewName("home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
