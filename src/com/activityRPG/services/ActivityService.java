/**
 * 
 */
package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : ActivityService
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Service
public class ActivityService {
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;

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

		}

		return mav;
	}

	/**
	 * 처리내용 : EnrollRaspberryPi 출력
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : EnrollRaspberryPi
	 * @return type : ModelAndView
	 */
	private ModelAndView enrollRaspberryPi(ActivityBean bean) {

		//라즈베리파이 등록하기
		try {
		if(dao.getRaspCheck(bean) != 0) {
			System.out.println("조회 성공");
			if(dao.setRaspMem(bean) != 0) {
				System.out.println("등록 성공");
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
	 * 처리내용 : 5-2 성별 기준 하루 누적 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : sexFloorDay
	 * @return type : String
	 */
	private String sexFloorDay(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("성별 기준 하루 누적 걸음 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 5-1 성별 기준 하루 누적 걸음 수 불러오기
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : sexStepDay
	 * @return type : String
	 */
	private String sexStepDay(ActivityBean bean) {
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

		mav.addObject("sexStepDay",sexStepDay(bean));
		mav.addObject("sexFloorDay",sexFloorDay(bean));

		mav.setViewName("adminActivitySexLogPage");

		return mav;
	}

	/**
	 * 처리내용 : 4-2 회원 전체 누적 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : ageFloorDay
	 * @return type : String
	 */
	private String ageFloorDay(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 누적 오른 층 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 4-1 회원 전체 누적 걸음 수 불러오기
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : floorAllUser
	 * @return type : String
	 */
	private String ageStepDay(ActivityBean bean) {
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

		mav.addObject("ageStepDay",ageStepDay(bean));
		mav.addObject("ageFloorDay",ageFloorDay(bean));

		mav.setViewName("adminActivityAgeLog");

		return mav;
	}

	/**
	 * 처리내용 : 3-4 회원 전체 누적 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : floorAllUser
	 * @return type : String
	 */
	private String floorAllUser(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 누적 오른 층 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 3-3 회원 전체 누적 걸음 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : stepAllUser
	 * @return type : String
	 */
	private String stepAllUser(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 누적 걸음 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 3-2 회원 전체 일일 평균 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : avgFloorAllUse
	 * @return type : String
	 */
	private String avgFloorAllUse(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 일일 평균 오른 층 수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 3-1 회원 전체 일일 평균 걸음 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : avgStepAllUser
	 * @return type : String
	 */
	private String avgStepAllUser(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("회원 전체 일일 평균 걸음이 json 형태로 전달됩니다.");

		return sb.toString();
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
			mav.addObject("avgStepAllUser",avgStepAllUser(bean));
			mav.addObject("avgFloorAllUse",avgFloorAllUse(bean));
			mav.addObject("stepAllUser",stepAllUser(bean));
			mav.addObject("floorAllUser",floorAllUser(bean));
	
			mav.setViewName("adminActivityLog");
		}else {
			mav.setViewName("home");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
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
		sb.append("전체 걸음/오른 층수/전환한 경험치 내역이 전달됩니다.");

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
		StringBuffer sb = new StringBuffer();
		sb.append("조회 시점에서 일주일 간의 경험치 전환 내역이 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 2-2 일주일 간의 오른 층수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekFloorData
	 * @return type : String
	 */
	private String activityWeekFloorData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("조회 시점에서 일주일 간의 오른 층수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 2-1 일주일 간의 걸음 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekStepData
	 * @return type : String
	 */
	private String activityWeekStepData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("조회 시점에서 일주일 간의 걸음이 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 2. activityWeekLogPage 이동
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekLogPage
	 * @return type : ModelAndView
	 */
	private ModelAndView activityWeekLogPage(ActivityBean bean) {

		mav.addObject("activityWeekStepData",activityWeekStepData(bean));
		mav.addObject("activityWeekFloorData",activityWeekFloorData(bean));
		mav.addObject("activityWeekExpData",activityWeekExpData(bean));
		mav.addObject("activityAllData",activityAllData(bean));
		mav.setViewName("activityWeekLog");

		return mav;
	}

	/**
	 * 처리내용 : 1-5-4 어제 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : encourageYesterFloorData
	 * @return type : String
	 */
	private String encourageYesterFloorData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("어제, 지금 오른 층수가 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 1-5-3 어제 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : encourageYesterStepData
	 * @return type : String
	 */
	private String encourageYesterStepData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("어제, 지금의 걸음이 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 1-5-2 오늘, 조회 시점의 오른 층 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : encourageTodayFloorData
	 * @return type : String
	 */
	private String encourageTodayFloorData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("오늘, 지금의 걸음이 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 1-5-1 오늘, 조회 시점의 걸음 수 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : encourageTodayStepData
	 * @return type : String
	 */
	private String encourageTodayStepData(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("오늘, 지금의 걸음이 json 형태로 전달됩니다.");

		return sb.toString();
	}

	/**
	 * 처리내용 : 1-3 전환 했던 경험치 총량 불러오기
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : appliedExpIndi
	 * @return type : String
	 */
	private String appliedExpIndi(ActivityBean bean) {
		StringBuffer sb = new StringBuffer();
		sb.append("지금까지 전환했던 경험치의 총량 입니다.");

		return sb.toString();
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
		sb.append("전환/획득 가능한 경험치가 출력됩니다..");

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
		StringBuffer sb = new StringBuffer();
		sb.append("오늘, 지금의 걸음, 오른 층수가 표시됩니다.");

		return sb.toString();
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
			mav.addObject("todayActivity",todayActivity(bean));
			mav.addObject("applicableExp",applicableExp(bean));
			mav.addObject("appliedExpIndi",appliedExpIndi(bean));
			mav.addObject("encourageTodayStepData",encourageTodayStepData(bean));
			mav.addObject("encourageTodayFloorData",encourageTodayFloorData(bean));
			mav.addObject("encourageYesterStepData",encourageYesterStepData(bean));
			mav.addObject("encourageYesterFloorData",encourageYesterFloorData(bean));
			mav.setViewName("activityDayLog");
			}else {
				mav.setViewName("home");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}


}
