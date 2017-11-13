package com.activityRPG.services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.GameBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : QuestService
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class QuestService extends TranEx{
	@Autowired
	private ProjectUtils pju;
	@Autowired
	private IMBatisDao dao;
	/**
	 * 처리내용 : 게임 내 서비스 분기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 한광수
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int questCode, Object... object) {
		ModelAndView mav = null;

		switch(questCode) {

		case 61://퀘스트 리스트 출력
			mav = questAll();
			break;
		case 62://퀘스트 받기
			mav = questAdd((GameBean)object[0]);
			break;
		case 63://퀘스트 확인
			mav = questCheck((GameBean)object[0]);
			break;
		case 64://퀘스트 보상
			mav = questPresent((GameBean)object[0]);
			break;
		case 65://내 퀘스트 리스트 출력
			mav = myQuestList();
			break;
		}

		return mav;
	}
	//퀘스트 보상
	private ModelAndView questPresent(GameBean gameBean) {
		ModelAndView mav= new ModelAndView();
		List<GameBean> listGameBean = null;
		boolean transaction = false;
		DecimalFormat format = new DecimalFormat(); //자리수 지정
		format.applyLocalizedPattern("0.0");	//자리수 패턴

		try {
			
			
			gameBean.setUserId((String)pju.getAttribute("id"));//유저 아이디 불러오기
			gameBean.setCharacterName((String)pju.getAttribute("characterName")); //빈에 캐릭터 이름 저장


			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.questCheck(gameBean); //퀘스트 완료 커밋
			
			transaction = true;
			setTransactionResult(transaction);
			
			int characterGold = dao.userGold(gameBean); // 유저 골드
			System.out.println(characterGold);

			int characterExp = dao.userExp(gameBean);	// 유저 경험치
			System.out.println(characterExp);

			gameBean.setQuestPrizeCode(gameBean.getMyquestCode());	//mycode 저장
			int PizCode = gameBean.getQuestPrizeCode();
			System.out.println(PizCode);

			int PizGold = dao.questGold(gameBean);	//보상으로 받을 골드
			System.out.println(PizGold);				//퀘스트보상골드 출력

			int PizExp = dao.questExp(gameBean);		//퀘스트보상경험치 출력
			System.out.println(PizExp);


			StringBuffer sb = new StringBuffer();
			switch(PizCode) {
			case 1:
				System.out.println("캐릭터 소유한 골드 : " + characterGold);
				System.out.println("보상 골드 : " + PizGold);

				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1) {	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "1000골드와 경험치 50 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("1000골드 획득");
				break;
			case 2:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1) {	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "2000골드와 경험치 100 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("2000골드 획득");
				break;
			case 3:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1) {	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "3000골드와 경험치 150 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("3000골드 획득");
				break;
			case 4:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1) {	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "4000골드와 경험치 200 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("4000골드 획득");
				break;
			case 5:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1) {	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "5000골드와 경험치 250 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("5000골드 획득");
				break;
			case 6:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1){	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "6000골드와 경험치 300 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("6000골드 획득");
				break;
			case 7:
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
				gameBean.setUpdateGold(characterGold + PizGold);
				dao.updateExp(gameBean);
				gameBean.setUpdateExp(characterExp + PizExp);
				dao.updateGold(gameBean);
				transaction = true;
				setTransactionResult(transaction);
				if(dao.updateExp(gameBean) == 1 && dao.updateGold(gameBean) == 1){	//업데이트 골드
					System.out.println("골드가 업데이트 됬습니다.");
					mav.addObject("massage", "6000골드와 경험치 350 획득!");
				}else {
					System.out.println("골드 업데이트 실패");
				}
				System.out.println("6000골드 획득");
				break;
			}

			mav.addObject("myQuestlist", sb.toString());

		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("questAll");
		return mav;
	}

	//퀘스트 완료 
	private ModelAndView questCheck(GameBean gameBean) {
		ModelAndView mav= new ModelAndView();
		List<GameBean> listMyQuest = null;
		String myQuestList = null;
		boolean transaction = false;
		try {
			System.out.println("넘긴 퀘스트 코드 : " + gameBean.getMyquestCode());
			
			
			gameBean.setUserId((String)pju.getAttribute("id"));//유저 아이디 불러오기
			gameBean.setCharacterName((String)pju.getAttribute("characterName")); //빈에 캐릭터 이름 저장


			listMyQuest = dao.myQuestList(gameBean);
			StringBuffer sb = new StringBuffer();
			sb.append("<table class=\"questTable\">");
			for(int i = 0; i < listMyQuest.size(); i++) {
				sb.append("<tr>");
				sb.append("<td>" + listMyQuest.get(i).getMyquestCode() + "</td>");
				sb.append("<td>" + listMyQuest.get(i).getCharacterName() + "</td>");
				sb.append("<td>" + listMyQuest.get(i).getQuestTitle() + "</td>");
				sb.append("<td>" + listMyQuest.get(i).getQuestContents() + "</td>");
				sb.append("<td><input type = \"button\" , value=\"완료\" onClick=\"QuestChecking("+ listMyQuest.get(i).getMyquestCode() +" ," + listMyQuest.get(i).getSussess() + ")\"></td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.setViewName("questSuccess");
		return mav;
	}
	//내 퀘스트 리스트 출력
	private ModelAndView myQuestList() {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		List<GameBean> listMyQuest = null;
		List<GameBean> ListGameBean = null;
		String myQuestList = null;
		GameBean gameBean = new GameBean();
		try {
			gameBean.setUserId((String)pju.getAttribute("id"));//유저 아이디 불러오기
			gameBean.setCharacterName((String)pju.getAttribute("characterName")); //빈에 캐릭터 이름 저장

				listMyQuest = dao.myQuestList(gameBean);
				StringBuffer sb = new StringBuffer();
				sb.append("<table class=\"questTable\">");
				for(int i = 0; i < listMyQuest.size(); i++) {
					sb.append("<tr>");
					sb.append("<td>" + listMyQuest.get(i).getMyquestCode() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getCharacterName() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getQuestTitle() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getQuestContents() + "</td>");
					if(listMyQuest.get(i).getSussess().equals("1 ")) {
						sb.append("<td>완료</td>");
					}else {
						sb.append("<td>미완료</td>");
					}
					sb.append("<td><input type = \"button\" , value=\"완료\" onClick=\"QuestChecking("+ listMyQuest.get(i).getMyquestCode() + ", "+ listMyQuest.get(i).getSussess()+")\"></td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("myQuestlist", sb.toString());
				mav.setViewName("myQuest");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}


	//퀘스트 받기
	private ModelAndView questAdd(GameBean gameBean){
		ModelAndView mav= new ModelAndView();
		List<GameBean> listMyQuest = null;
		List<GameBean> ListGameBean = null;
		String myQuestList = null;
		boolean transaction = false;
		try {
			
			gameBean.setUserId((String)pju.getAttribute("id"));//유저 아이디 불러오기
			gameBean.setCharacterName((String)pju.getAttribute("characterName")); //빈에 캐릭터 이름 저장
			
			//System.out.println("수락한 퀘스트 코드 : " + gameBean.getMyquestCode());
			System.out.println(dao.questCodeCheck(gameBean));
			if(dao.questCodeCheck(gameBean) == 0) { //퀘스트가 존재하지 않음
				
				
				setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

				dao.questAdded(gameBean); //퀘스트받기
				System.out.println("수락한 퀘스트 코드 : " + gameBean.getMyquestCode());
				
				transaction = true;
				setTransactionResult(transaction);

				listMyQuest = dao.myQuestList(gameBean);
				StringBuffer sb = new StringBuffer();
				sb.append("<table class=\"questTable\">");
				for(int i = 0; i < listMyQuest.size(); i++) {
					sb.append("<tr>");
					sb.append("<td>" + listMyQuest.get(i).getMyquestCode() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getCharacterName() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getQuestTitle() + "</td>");
					sb.append("<td>" + listMyQuest.get(i).getQuestContents() + "</td>");
					if(listMyQuest.get(i).getSussess().equals("1 ")) {
						sb.append("<td>완료</td>");
					}else {
						sb.append("<td>미완료</td>");
					}
					sb.append("<td><input type = \"button\" , value=\"완료\" onClick=\"QuestChecking("+ listMyQuest.get(i).getMyquestCode() + ", "+ listMyQuest.get(i).getSussess()+")\"></td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("myQuestlist", sb.toString());
				mav.setViewName("myQuest");
			}else {
				System.out.println("수락 실패");
				
				StringBuffer sb = new StringBuffer();
				ListGameBean = dao.questList();
				sb.append("<table class=\"questTable\">");
				for(int i = 0; i < ListGameBean.size(); i++) {
					gameBean = ListGameBean.get(i);
					sb.append("<tr>");
					sb.append("<td>" + gameBean.getQuestCode() + "</td>");
					sb.append("<td>" + gameBean.getQuestTitle() + "　퇴치</td>");
					sb.append("<td>" + gameBean.getQuestContents() + "골드</td>");
					sb.append("<td><input class=\"questButton\" type = \"button\" value=\"받기\" onClick=\"Add("+ gameBean.getQuestCode() +")\"></td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
				mav.addObject("questList", sb.toString());
				mav.setViewName("questAll");
				mav.addObject("message", "이미 수락하신 퀘스트 입니다.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}

	//전체 퀘스트 목록
	private ModelAndView questAll() {
		ModelAndView mav = new ModelAndView();
		List<GameBean> ListGameBean = null;
		GameBean gameBean = new GameBean();
		String questList = null;
		StringBuffer sb = new StringBuffer();
		try{
			gameBean.setUserId((String)pju.getAttribute("id"));//유저 아이디 불러오기
			gameBean.setCharacterName((String)pju.getAttribute("characterName")); //빈에 캐릭터 이름 저장
			
			ListGameBean = dao.questList();
			sb.append("<table class=\"questTable\">");
			for(int i = 0; i < ListGameBean.size(); i++) {
				gameBean = ListGameBean.get(i);
				sb.append("<tr>");
				sb.append("<td>" + gameBean.getQuestCode() + "</td>");
				sb.append("<td>" + gameBean.getQuestTitle() + "　퇴치</td>");
				sb.append("<td>" + gameBean.getQuestContents() + "골드</td>");
				sb.append("<td><input class=\"questButton\" type = \"button\" value=\"받기\" onClick=\"Add("+ gameBean.getQuestCode() +")\"></td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
		}catch(Exception e) {
			e.printStackTrace();
		}
		questList = sb.toString();
		mav.addObject("questList", questList);
		mav.addObject("listSize", ListGameBean.size());
		mav.setViewName("questAll");
		return mav;
	}
}
