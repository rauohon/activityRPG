package com.activityRPG.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.GameBean;
import com.activityRPG.beans.MemberBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : GameNomalService
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class GameNomalService extends TranEx {
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private GamePlayService gp;

	ModelAndView mav = new ModelAndView();
	/**
	 * 처리내용 : 게임 일반 서비스 분기
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object... bean) {		

		switch(i) {

		case 0:
			mav = backPage((GameBean)bean[0]);
			break;
		case 1:
			mav = villagePage((GameBean)bean[0]);
			break;
		case 2:
			mav = questPage((GameBean)bean[0]);
			break;
		case 3:
			mav = guildPage((GameBean)bean[0]);
			break;
		case 4:
			mav = dungeonPage((GameBean)bean[0]);
			break;
		case 5:
			mav = characterInfoPage((GameBean)bean[0]);
			break;			
		case 6:
			mav = itemInfo((GameBean)bean[0]);
			break;
		case 7:
			mav = charaImageFileUpload((GameBean)bean[0], (HttpServletRequest)bean[1],  (HttpSession)bean[2]);
			break;
		case 41: //캐릭터 생성 폼 이동
			mav = characterCreateFormMove((GameBean)bean[0]);	
			break;
		case 42: //캐릭터 생성
			mav = characterCreate((GameBean)bean[0]);	
			break;
		case 66: //랭킹 출력
			mav = ranking();
			break;
		case 67: //길드 리스트 출력
			mav = guild();
			break;
		case 68: //길드 생성
			mav = guildCreate((GameBean)bean[0]);
			break;
		case 69: //길드 가입
			mav = guildJoinMove((GameBean)bean[0]);
			break;
		case 70: //길드 탈퇴
			mav = guildOut();
			break;
		case 71: //길드 멤버리스트
			mav = guildMemberMove((GameBean)bean[0]);
			break;
		case 72: 
			mav = characterCreates((GameBean)bean[0]);
			break;
		}
		return mav;
	}
	
		private ModelAndView characterCreates(GameBean bean) {
		
		try {
				if(dao.characterIdCheck(bean) == 1) { // 캐릭터 존재 유무 확인
					System.out.println("캐릭터 존재");
					BoardBean boardBean = new BoardBean();
					boardBean.setId(bean.getId());
					if(dao.getUserIsGuildCheck(boardBean) != 0) {
						mav.addObject("guildCode",String.valueOf(dao.getGuildCode(boardBean)));
						mav.addObject("guildChat", guildChat(bean, String.valueOf(dao.getGuildCode(boardBean))));
					}
					session.setAttribute("characterName", dao.getCharacterName(session.getAttribute("id").toString()));
					session.setAttribute("page", "village");
					mav.setViewName("village");
				}else {
					mav.addObject("message", "※ 먼저 캐릭터를 생성해 주세요.");
					System.out.println("캐릭터 존재하지 않음");
					mav = characterCreateFormMove(bean);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 처리내용 : 이미지 파일 업로드
	 * 작성일 : 2017. 11. 12.
	 * 작성자 : 신태휘
	 * @Method Name : charaImageFileUpload
	 * @return type : ModelAndView
	 */
	private ModelAndView charaImageFileUpload(GameBean bean, HttpServletRequest request,
			HttpSession httpsession) {
		mav.setViewName("home");
		httpsession =	request.getSession();	
		String fname = "";
		MultipartFile uploadfile = bean.getUpLoadFile();
		try {
		bean.setId(session.getAttribute("id").toString());
		fname = bean.getId() + uploadfile.getOriginalFilename();
		
		if(fname.equals("")) {
			bean.setFileName(null);
		}else {
			bean.setFileName(fname);			
			
				uploadfile.transferTo(new File("C:\\Users\\pc\\Documents\\activityRPG\\WebContent\\views\\images\\" + fname));
																			//파일 경로 변경 필요!!
				if(dao.setCharaImageUdate(bean) != 0) {
					System.out.println("/images/"+fname);
					mav = characterInfoPage(bean);					
				}
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return mav;
	}

	/**
	 * 처리내용 : 아이템 사용여부 판단해서 버튼 만들어서 리턴
	 * 작성일 : 2017. 11. 3.
	 * 작성자 : 신태휘
	 * @Method Name : itemIsUsed
	 * @return type : String
	 */
	private String itemIsUsed(GameBean bean) {
		String result = "";
		String disArm = "<input type='button' class=\'button\' value='해제하기' onClick='itemdisarm(\""+bean.getItcode() +"\")'";
		String arm = "<input type='button' class=\'button\' value='사용하기' onClick='itemuse(\""+bean.getItcode() +"\")'";
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("content",session.getAttribute("chName").toString());
		} catch (Exception e) {}
		int jobcode = Integer.parseInt(bean.getItcode());
		map.put("useItem", bean.getItcode());
		if(jobcode <= 2000) {
			map.put("weapon", "무기");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 3000) {
			map.put("armor", "갑옷");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 4000) {
			map.put("glove", "장갑");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 5000) {
			map.put("shoe", "신발");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 6000) {
			map.put("ring", "반지");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 7000) {
			map.put("necklace", "목걸이");
			if(dao.getIsEquip(map) != 0) {
				result = disArm;
			}else {
				result = arm;
			}
		}else if(jobcode <= 7500) {
			System.out.println("체력포션류 입니다.");
			result = arm;
		}else{
			System.out.println("마나포션류 입니다.");
			result = arm;
		}

		return result;
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
		bean = dao.getItemInfo(bean);
		sb.append("<table><tr><th>이름</th><th>향상 능력치</th><th>필요 능력치</th></tr><tr><td>");
		sb.append(bean.getItname());
		sb.append("</td><td>");
		sb.append(bean.getAbility());
		sb.append("</td><td>");
		sb.append(bean.getRequiAbility());
		sb.append("</td></tr><tr><td colspan=\"3\" style=\"text-align: right;\">");
		sb.append(itemIsUsed(bean));
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
		System.out.println(bean.getChName() + "착용 아이템 확인");
		map.put("content",bean.getChName());
		GameBean equipBean = new GameBean();
		GameBean enBean = new GameBean();
		equipBean = dao.getEquipList(map);	// 착용 아이템 코드 가져오기
		int armorAbility = 0;
		if(equipBean != null) {
			int weaponCode = equipBean.getEqWeapon();
			if(equipBean.getEqWeapon() != 0) {
				map.put("itcode", String.valueOf(weaponCode));			
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("weapon", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				map.put("weaponAbility", String.valueOf(bean.getAbility()));
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("weaponEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}else {
				map.put("weaponAbility", "0");
			}
			if(equipBean.getEqArmor() != 0) {
				int armorCode = equipBean.getEqArmor();
				map.put("itcode", String.valueOf(armorCode));
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("armor", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				armorAbility += bean.getAbility();
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("armorEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}
			if(equipBean.getEqGlove() != 0) {
				int gloveCode = equipBean.getEqGlove();
				map.put("itcode", String.valueOf(gloveCode));
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("glove", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				armorAbility += bean.getAbility();
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("gloveEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}
			if(equipBean.getEqShoe() != 0) {
				int shoeCode = equipBean.getEqShoe();
				map.put("itcode", String.valueOf(shoeCode));
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("shoe", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				armorAbility += bean.getAbility();
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("shoeEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}
			if(equipBean.getEqRing() != 0) {
				int ringCode = equipBean.getEqRing();
				map.put("itcode", String.valueOf(ringCode));
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("ring", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				map.put("manaAbility", String.valueOf(bean.getAbility()));
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("ringEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}
			System.out.println(equipBean.getEqNecklace());
			if(equipBean.getEqNecklace() != 0) {
				int necklaceCode = equipBean.getEqNecklace();
				map.put("itcode", String.valueOf(necklaceCode));
				bean = dao.getItemName(map);	// 착용 아이템의 정보 가져오기
				map.put("necklace", "<h4 onClick=\'startAjax(\""+bean.getItcode()+"\")\'>"+bean.getItname());
				map.put("hpAbility", String.valueOf(bean.getAbility()));
				enBean = dao.getEnLevel(map);	// 착용 아이템 강화 레벨 가져오기
				if(enBean.getEnlevel() != 0) {
					map.put("necklaceEn", "+"+String.valueOf(enBean.getEnlevel())+"</h4>");			
				}
			}
		}
		map.put("armorAbility", String.valueOf(armorAbility));
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
		String sb = "";
		bean = dao.getCharaImage(bean);
		
		sb = bean.getChImagePath()+bean.getChImageName();
		
		return sb;
	}

	/**
	 * 처리내용 : 5. characterInfoPage
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : characterInfoPage
	 * @return type : ModelAndView
	 */
	private ModelAndView characterInfoPage(GameBean bean) {
		try {
			bean.setId(session.getAttribute("id").toString());
			bean = characterStatus(bean);
			session.setAttribute("chName", bean.getChName());
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
			mav.addObject("weaponAbility",map.get("weaponAbility"));
			mav.addObject("armor",map.get("armor"));
			mav.addObject("armorEn",map.get("armorEn"));
			mav.addObject("armorAbility",map.get("armorAbility"));
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
		}catch(Exception e) {
			e.printStackTrace();
		}
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
	
	private String guildChat(GameBean bean, String guildCode) {
		String sb = "<div id=\'" + guildCode + "\'></div>"; 
		return sb;
	}

	/**
	 * 처리내용 : 1. villagePage 호출
	 * 작성일 : 2017. 10. 21.
	 * 작성자 : 신태휘
	 * @Method Name : villagePage
	 * @return type : ModelAndView
	 */
	private ModelAndView villagePage(GameBean bean) {
		
		try {
			if(session.getAttribute("id") != null) { // 로그인 여부 확인
				bean.setId(session.getAttribute("id").toString());
				if(dao.characterIdCheck(bean) == 1) { // 캐릭터 존재 유무 확인
					System.out.println("캐릭터 존재");
					BoardBean boardBean = new BoardBean();
					boardBean.setId(bean.getId());
					if(dao.getUserIsGuildCheck(boardBean) != 0) {
						mav.addObject("guildCode",String.valueOf(dao.getGuildCode(boardBean)));
						mav.addObject("guildChat", guildChat(bean, String.valueOf(dao.getGuildCode(boardBean))));
					}
					session.setAttribute("characterName", dao.getCharacterName(session.getAttribute("id").toString()));
					session.setAttribute("page", "village");
					mav.setViewName("village");
				}else {
					mav.addObject("message", "※ 먼저 캐릭터를 생성해 주세요.");
					System.out.println("캐릭터 존재하지 않음");
					mav.setViewName("index");;
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
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
				if(session.getAttribute("page").toString().equals("battlePage")) {
					mav = gp.entrance(45, bean);
				}else {
					callPage = session.getAttribute("page").toString();
					mav.setViewName(callPage);
					session.removeAttribute("page");
				}
			}else {
				mav.setViewName("village");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}


	//***************************김훈*********************************
	//캐릭터 생성
	private ModelAndView characterCreate(GameBean gameBean) {
		ModelAndView mav = new ModelAndView();

		try {
			randomAbility(gameBean); //랜덤 능력치 설정
			gameBean.setId((String)session.getAttribute("id"));	//아이디를 빈에 저장
			gameBean.setSex(dao.characterSex(gameBean));
			//트랜잭션 설정 
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
			if(dao.characterNameCkeck(gameBean) == 0) {	//캐릭터 이름 확인
				if(dao.characterCreate(gameBean) == 1) { //캐릭터 생성 성공
					if(dao.inventoryInsertMaterial(gameBean) == 1) { //인벤토리에 강화석 추가
						if(dao.characterSkill1(gameBean) == 1) { //캐릭터 스킬1 생성
							if(dao.characterSkill2(gameBean) == 1){ //캐릭터 스킬2 생성
								if(dao.characterSkill3(gameBean) == 1) { //캐릭터 스킬3 생성
									if(dao.setCharaImage(gameBean) != 0) {// 캐릭터 이미지 삽입(신태휘)
										if(dao.setEquipFirst(gameBean) != 0) {// 캐릭터 장비창 생성(신태휘)
											mav.setViewName("home");
											mav.addObject("message", "*캐릭터를 생성했습니다. 게임시작 버튼을 눌러주세요.");
											//트랜잭션 커밋
											setTransactionResult(true);		
										}																		
									}
								}
							}
						}
					}
				}else {	//캐릭터 생성 실패
					mav.setViewName("home");
					mav.addObject("message", "*캐릭터 생성에 실패했습니다. 잠시 후 다시 시도해 주세요");
				}
			}else {	//해당 캐릭터 이름 이미 존재
				mav = characterCreateFormMove(gameBean);
				mav.addObject("message", "*캐릭터 이름이 이미 존재합니다.");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	//캐릭터 생성 폼 이동
	private ModelAndView characterCreateFormMove(GameBean gameBean) {
		ModelAndView mav = new ModelAndView();
		try{				
			gameBean.setId(session.getAttribute("id").toString());
				
			mav.addObject("userSex", dao.characterSex(gameBean));
			mav.setViewName("characterCreateForm");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	//랜덤 능력치 설정
	private void randomAbility(GameBean gameBean) {
		Random randomAbility = new Random();

		gameBean.setStr((randomAbility.nextInt(10) + 1));
		gameBean.setDex((randomAbility.nextInt(10) + 1)); 
		gameBean.setIntelligent((randomAbility.nextInt(10) + 1)); 
	}
	//******************************김훈******************************************
	//*****************************한광수***************************************
	//길드멤버리스트
		private ModelAndView guildMemberMove(GameBean gameBean) {
			ModelAndView mav = new ModelAndView();
			List<GameBean> guildMember = null;
			try{
				gameBean.setCharacterName(session.getAttribute("characterName").toString()); //캐릭터 이름 가져오기
				
				//System.out.println(gameBean.getGuildName());
				int code = dao.guildCodeGet(gameBean);
				//System.out.println(code);
				
				gameBean.setGuildCode(code);
				
				
				
				guildMember = dao.guildMemberListView(gameBean);
				StringBuffer sb = new StringBuffer();
				
				sb.append("<table class=\"memberList\">");
				sb.append("<tr>");
				sb.append("<th>캐릭터이름</th><th>캐릭터레벨</th><th>길드 계급</th>");
				sb.append("</tr>");
				for(int i =0; i < guildMember.size(); i++) {
					sb.append("<tr>");
					sb.append("<td>"+guildMember.get(i).getCharacterName()+"</td>");
					sb.append("<td>"+guildMember.get(i).getLevel()+"</td>");
					if(guildMember.get(i).getGuildMenLevel()==1) {
						sb.append("<td>길드 마스터</td>");
					}else {
						sb.append("<td>길드원</td>");
					}
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("guildMember", sb.toString());
				mav.addObject("guildName", gameBean.getGuildName());
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("guildMember");
			return mav;
		}
		//길드 탈퇴
		private ModelAndView guildOut() {
			ModelAndView mav = new ModelAndView();
			boolean transaction = false;
			try {
				GameBean gameBean = new GameBean();
				gameBean.setCharacterName(session.getAttribute("characterName").toString()); //캐릭터 이름 가져오기
				
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				
				
				dao.guildOut(gameBean);	//길드 탈퇴 
				
				transaction = true;
				setTransactionResult(transaction);
				
				mav = guild();	//길드 리스트 
				mav.addObject("message", "길드 탈퇴 되었습니다.");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
		//길드 가입
		private ModelAndView guildJoinMove(GameBean gameBean) {
			ModelAndView mav = new ModelAndView();
			boolean transaction = false;
			try {
				
				gameBean.setCharacterName(session.getAttribute("characterName").toString());
				
				String userGuild = dao.userGuildCheck(gameBean); //유저 길드 유무 판단
				System.out.println(userGuild);
				
				if(userGuild.equals("0 ")) {//"0 "일경우 가입
					int guildCode = dao.guildCodeGet(gameBean); //길드 코드 가져오기
					gameBean.setGuildCode(guildCode); //길드코드 빈에 저장
					
					setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
					
					dao.userGuildJoin(gameBean); //해당하는 유저 길드코드 업데이트
					
					transaction = true;
					setTransactionResult(transaction);
					
					mav = guildMemberMove(gameBean);
				}else {
					mav = guild();
					mav.addObject("message", "길드가 이미 존재합니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return mav;
		}



		//길드 생성
		private ModelAndView guildCreate(GameBean gameBean) {
			ModelAndView mav = new ModelAndView();
			boolean transaction = false;
		
			try {		
				System.out.println(session.getAttribute("characterName"));
				
				gameBean.setCharacterName(session.getAttribute("characterName").toString());
				
				//캐릭터 길드 유무 판단
				String userGuild = dao.userGuildCheck(gameBean);
				System.out.println(userGuild);
				
				if(userGuild.equals("0 ")) {	//길드 코드 이름이 "0 " 인 경우 (길드 없음)
					
					//길드 이름 중복 확인
					if(dao.guildNameCheck(gameBean) == 0) { //길드 이름 사용 가능
						
						//캐릭터의 골드 확인
						int userGold = dao.userGold(gameBean);
						if(userGold - 10000 >= 0) { //골드가 10000원보다 많다면
							//캐릭터의 골드 감소
							gameBean.setUpdateGold(userGold - 10000);
							
							setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
							
							dao.guildDec(gameBean);	//캐릭터의 골드 감소
							
							
							//길드 생성
							dao.guildCreate(gameBean);
							
							//길드 코드 가져오기
							gameBean.setGuildCode(dao.guildCodeGet(gameBean));
							
							
							//캐릭터 길드 추가
							dao.userGuildupdate(gameBean);
							
							transaction = true;
							setTransactionResult(transaction);
							
							mav = guild();
							
						}else { //10000원 이하
							mav.addObject("message", "길드 생성에는 10000골드가 필요합니다.");
							mav.setViewName("guildCreate");
						}
					}else {	//길드 이름이 이미 존재하는 경우
						mav.addObject("message", "이미 존재하는 길드 이름입니다.");
						mav.setViewName("guildCreate");
					}
				}else {	//캐릭터 길드가 있는 경우
					mav.addObject("message", "이미 길드에 가입하셨습니다.");
					mav.setViewName("guildCreate");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return mav;
		}
		//길드 이동
		private ModelAndView guild() {
			ModelAndView mav = new ModelAndView();
			List<GameBean> listGuildView = null;
			try {
				listGuildView = dao.guildList();
				StringBuffer sb = new StringBuffer();
				sb.append("<table class=\"guildList\">");
				sb.append("<tr>");
				sb.append("<th>길드명</th><th>제한인원수</th><th>길드 가입</th>");
				sb.append("</tr>");
				for(int i =0; i< listGuildView.size(); i++) {
					sb.append("<tr>");
					sb.append("<td onClick=\"guildMemberList(\'"+ listGuildView.get(i).getGuildName() +"\')\">" + listGuildView.get(i).getGuildName()+ "</td>");
					sb.append("<td>" + listGuildView.get(i).getGuildTotalNum()+"</td>");
					sb.append("<td><input class=\"guildjoinButton\" type=\"button\" value=\"가입\" onClick=\"guildJoin(\'"+ listGuildView.get(i).getGuildName() +"\')\">");
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("listGuildView", sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("GuildPage");
			return mav;
		}

		//랭킹 리스트 출력
		private ModelAndView ranking() {
			ModelAndView mav = new ModelAndView();
			List<GameBean> rankingList = null;
			try {
				
				rankingList = dao.rankingListView();
				StringBuffer sb = new StringBuffer();
				sb.append("<table class=\"rankingList\">");
				sb.append("<thead><tr>");
				sb.append("<th>순위</th><th>이름</th><th>레벨</th><th>경험치</th><th>힘</th><th>민첩</th><th>지능</th></thead>");
				sb.append("</tr>");
				for(int i=0; i < rankingList.size(); i++) {
					sb.append("<tr>");
					sb.append("<td>"+ (i+1) +"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingName()+"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingLevel()+"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingExp()+"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingStr()+"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingDex()+"</td>");
					sb.append("<td>" + rankingList.get(i).getRankingInt()+"</td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("rankingList", sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("ranking");
			return mav;
		}
		//****************************한광수************************************
}
