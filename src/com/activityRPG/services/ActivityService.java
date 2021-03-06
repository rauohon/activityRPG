package com.activityRPG.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.beans.BoardBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.ProjectUtils;
import com.google.gson.Gson;

/**
 * @클래스명 : ActivityService
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class ActivityService extends TranEx {
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	boolean transaction = false;

	/**
	 * 처리내용 : 운동 서비스 분기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {

		switch(i) {
		case 1:
			mav = activityDayLogPage((ActivityBean)bean);
			break;
		case 2:
			mav = activityWeekLogPage((ActivityBean)bean);
			break;
		case 3:
			mav = adminActivityLogPage((ActivityBean)bean);
			break;
		case 4:
			mav = adminActivityAgeLogPage((ActivityBean)bean);
			break;
		case 5:
			mav = adminActivitySexLogPage((ActivityBean)bean);
			break;
		case 6:
			mav = enrollRaspberryPiPage((ActivityBean)bean);
			break;
		case 7:
			mav = enrollRaspberryPi((ActivityBean)bean);
			break;
		case 8:
			mav = setExp((ActivityBean)bean);
			break;

		}

		return mav;
	}

	/**
	 * 처리내용 : 8. 운동량 경험치로 전환 하기
	 * 작성일 : 2017. 11. 8.
	 * 작성자 : 신태휘
	 * @Method Name : setExp
	 * @return type : ModelAndView
	 */
	private ModelAndView setExp(ActivityBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		try {
			bean.setId(session.getAttribute("id").toString());
			if(dao.setActExp(bean) != 0) {
				if(dao.setActivity(bean) != 0) {
					if(dao.setActLog(bean) != 0) {
						mav = activityDayLogPage(bean);
						transaction = true;
						// 시연때는 true 해제
					}
				}
			}
		} catch (Exception e) {}
		setTransactionResult(transaction);
		return mav;
	}

	/**
	 * 처리내용 : 라즈베리파이 등록하기
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : EnrollRaspberryPi
	 * @return type : ModelAndView
	 */
	private ModelAndView enrollRaspberryPi(ActivityBean bean) {

		//라즈베리파이 등록하기
		try {
		if(dao.getRaspCodeCheck(bean) != 0) {
			if(dao.setRaspMem(bean) != 0) {
				mav.addObject("msg", "system error");
				mav.setViewName("home");
			}else {
				mav.addObject("msg", "system error");
				mav.setViewName("enrollRaspberryPi");
			}
		}else {
			mav.addObject("msg", "라즈베리 코드를 다시 확인해 주세요.");
			mav.setViewName("enrollRaspberryPi");
		}
		}catch(Exception e){
			e.printStackTrace();
			mav.addObject("msg", "라즈베리 코드를 다시 확인해 주세요.");
			mav.setViewName("enrollRaspberryPi");
		}
		return mav;
	}

	/**
	 * 처리내용 : EnrollRaspberryPiPage 출력
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : EnrollRaspberryPiPage
	 * @return type : ModelAndView
	 */
	private ModelAndView enrollRaspberryPiPage(ActivityBean bean) {
		try {
			if(session.getAttribute("id") != null) {
				mav.setViewName("enrollRaspberryPi");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 처리내용 :  5-1 성별 기준 하루 누적 걸음 수 불러오기
	 * 작성일 : 2017. 11. 10.
	 * 작성자 : 신태휘
	 * @Method Name : sexActivityDay
	 * @return type : String
	 */
	private String sexActivityDay(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("성별 기준 하루 누적 걸음 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5. adminActivitySexLogPage 출력
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : adminActivityAgeLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView adminActivitySexLogPage(ActivityBean bean) {

		mav.addObject("sexActivityDay",sexActivityDay(bean));

		mav.setViewName("adminActivitySexLog");

		return mav;
	}

	
	/**
	 * 처리내용 : 4-1 회원 연령별 누적 걸음 수 불러오기
	 * 작성일 : 2017. 11. 10.
	 * 작성자 : 신태휘
	 * @Method Name : ageActivityDay
	 * @return type : String
	 */
	private String ageActivityDay(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 누적 오른 층 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 4. adminActivityAgeLogPage 출력
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : adminActivityAgeLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView adminActivityAgeLogPage(ActivityBean bean) {

		mav.addObject("ageActivityDay",ageActivityDay(bean));

		mav.setViewName("adminActivityAgeLog");

		return mav;
	}
	
	/**
	 * 처리내용 : 3-2 회원 전체 걸음 / 층 수 불러오기
	 * 작성일 : 2017. 11. 10.
	 * 작성자 : 신태휘
	 * @Method Name : activityAllUser
	 * @return type : String
	 */
	private String activityAllUser(ActivityBean bean) {
		String sb = "";		
		List<ActivityBean> acti = dao.getActivityAllUser(bean);		
		Gson gson = new Gson();		
		sb = gson.toJson(acti);		
		return sb;
	}

	/**
	 * 처리내용 : 3-1 회원 전체 일일 평균 걸음 / 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : avgStepAllUser
	 * @return type : String
	 */
	private String avgActAllUser(ActivityBean bean) {
		String sb = "";		
		List<ActivityBean> acti = dao.getAvgActivityAllUser(bean);		
		Gson gson = new Gson();		
		sb = gson.toJson(acti);		
		return sb;
	}

	/**
	 * 처리내용 : 3. adminActivityLogPage 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : adminActivityLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView adminActivityLogPage(ActivityBean bean) {
		try {
		if(session.getAttribute("id") != null) {
			mav.addObject("avgActivityAllUser",avgActAllUser(bean));
			mav.addObject("activityAllUser",activityAllUser(bean));	
			mav.setViewName("adminActivityLog");
		}else {
			mav.setViewName("home");
		}
		}catch(Exception e) { }
		return mav;
	}

	/**
	 * 처리내용 : 2-4 전체 걸음/오른 층수/전환한 경험치 내역 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityAllData
	 * @return type : String
	 */
	private String activityAllData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("전체 걸음/오른 층수/전환한 경험치 내역입니다.");
		sb.append("<br/>");
		List<ActivityBean> acti = dao.getActivityAllData(bean);
		List<ActivityBean> acti2 = dao.getAppliedAllData(bean);
		double totalStepDistance = Math.round((acti.get(0).getStep()*0.0007)*100d)/100d;
		double totalFloorHeight = Math.round((acti.get(0).getFloor()*2.45)*100d)/100d;
		sb.append("<h1 class=\'act\' style=\'margin-top:2%;\'>※ " 
				+ acti.get(0).getStep() + " : 지금까지 이만큼 걸으셨어요.</h1>");
		sb.append("<h1 class=\'act\' style=\'margin-top:2%;\'>※ " 
				+ acti.get(0).getFloor() + " : 지금까지 이만큼 계단으로 오르내리셨어요.</h1>");
		sb.append("<h1 class=\'act\' style=\'margin-top:2%;\'>※ " 
				+acti2.get(0).getExp()+ " : 지금까지 이만큼 경험치로 전환 하셨어요.</h1>");
		sb.append("<div id=\'showDetail\' style=\'display:none;\'>"
				+ totalStepDistance +" km 정도를 걸으셨어요!<br>"
				+ totalFloorHeight +" m 정도를 오르셨어요!</div>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 2-3 일주일 간의 경험치 전환 내역 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekExpData
	 * @return type : String
	 */
	private String activityWeekExpData(ActivityBean bean) {
		String sb = "";		
		Gson gson = new Gson();		
		List<ActivityBean> acti = dao.getAppliedWeekExp(bean);		
		Collections.sort(acti, new Comparator<ActivityBean>(){
			@Override
			public int compare(ActivityBean r2, ActivityBean r1){
				return r1.getDate().compareTo(r2.getDate());
			}
		});
		sb = gson.toJson(acti);				
		return sb;
	}

	/**
	 * 처리내용 : 2-1 일주일 간의 걸음 / 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekStepData
	 * @return type : String
	 */
	private String activityWeekActData(ActivityBean bean) {
		String sb = "";		
		Gson gson = new Gson();
		List<ActivityBean> acti = dao.getWeekActivity(bean);		
		Collections.sort(acti, new Comparator<ActivityBean>(){
			@Override
			public int compare(ActivityBean r2, ActivityBean r1){
				return r1.getDate().compareTo(r2.getDate());
			}
		});		
		sb = gson.toJson(acti);
		return sb;
	}

	/**
	 * 처리내용 : 2. activityWeekLogPage 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView activityWeekLogPage(ActivityBean bean) {
		
		try {
			bean.setId(session.getAttribute("id").toString());
			mav.addObject("activityWeekActData",activityWeekActData(bean));
			mav.addObject("activityWeekExpData",activityWeekExpData(bean));
			mav.addObject("activityAllData",activityAllData(bean));
			mav.setViewName("activityWeekLog");
		}catch(Exception e) {e.printStackTrace();}

		return mav;
	}

	/**
	 * 처리내용 : 1-4 어제 오른 층 수 / 걸음 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : yesterDayStepData
	 * @return type : String
	 */
	private String yesterDayStepData(ActivityBean bean) {
		String sb = "";		
		Gson gson = new Gson();		
		List<ActivityBean> acti = dao.getYesterdayAct(bean);		
		sb = gson.toJson(acti);	
		return sb;
	}

	/**
	 * 처리내용 : 1-3 전환 했던 경험치 일주일량 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : appliedExpIndi
	 * @return type : String
	 */
	private String appliedExpIndi(ActivityBean bean) {
		String sb = "";			
			bean.setExp(1);
			bean = dao.getWeekAppliedExp(bean);
			
//			sb = String.valueOf(bean.getExp());			

		return sb;
	}

	/**
	 * 처리내용 : 1-2 전환 가능한 경험치 출력하기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : applicableExp
	 * @return type : String
	 */
	private String applicableExp(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		try {
			bean.setId(session.getAttribute("id").toString());
			List<ActivityBean> acti  = dao.getAvailableAct(bean);
			int floor = acti.get(0).getFloor();
			int step = acti.get(0).getStep();
			int applicableExp = (floor * 100) + (step / 10);
			sb.append(String.valueOf(applicableExp) + " 을(를) 경험치 전환이 가능 합니다. <br>");
			sb.append("<button class=\'button\' onClick='setexp(\""+ applicableExp + "\")' "
					+ "style=\'height: 35px; width: 10%; margin-top:2%\'>경험치로 바꾸기</button>");			
		}catch(Exception e) {
			
		}		
		return sb.toString();
	}

	/**
	 * 처리내용 : 1-1 호출시점 당일 걸음, 층수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : todayActivity
	 * @return type : String
	 */
	private String todayActivity(ActivityBean bean) {
		String sb = "";		
		Gson gson = new Gson();		
		List<ActivityBean> acti = dao.getTodayAct(bean);
		sb = gson.toJson(acti);	
		return sb;
	}

	/**
	 * 처리내용 : 1. activityDayLogPage 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityDayLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView activityDayLogPage(ActivityBean bean) {
		
		try {
			if(session.getAttribute("id") != null) {
				bean.setId(session.getAttribute("id").toString());
				if(dao.getIsRaspCheck(bean) != 0) {
					mav.addObject("todayActivity",todayActivity(bean));
					mav.addObject("applicableExp",applicableExp(bean));
//					mav.addObject("appliedExpIndi",appliedExpIndi(bean));
					mav.addObject("yesterdayActivity",yesterDayStepData(bean));
					mav.setViewName("activityDayLog");
				}else{
					mav.addObject("msg", "라즈베리의 코드를 등록해주세요");
					mav=enrollRaspberryPiPage(bean);
				}
			}else {
				mav.setViewName("home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
