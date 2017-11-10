package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.ActivityBean;
import com.activityRPG.services.ActivityService;

/**
 * @클래스명 : ActivityController
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService as;
	private ModelAndView mav = new ModelAndView();
	
	/**
	 * 처리내용 : activityDayLogPage 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityDayLogPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ActivityDayLogPage", method = RequestMethod.POST)
	private ModelAndView activityDayLogPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(1, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : activityWeekLogPage 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : activityWeekLogPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/ActivityWeekLogPage", method = RequestMethod.POST)
	private ModelAndView activityWeekLogPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(2, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : adminActivityLogPage 연결
	 * 작성일 : 2017. 10. 23.
	 * 작성자 : 신태휘
	 * @Method Name : adminActivityLogPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/AdminActivityLogPage", method = RequestMethod.POST)
	private ModelAndView adminActivityLogPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(3, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : adminActivityAgeLogPage 연결
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : AdminActivityAgeLogPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/AdminActivityAgeLogPage", method = RequestMethod.POST)
	private ModelAndView adminActivityAgeLogPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(4, bean);
		
		return mav;
	}	

	/**
	 * 처리내용 : AdminActivitySexLogPage 연결
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : AdminActivitySexLogPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/AdminActivitySexLogPage", method = RequestMethod.POST)
	private ModelAndView adminActivitySexLogPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(5, bean);		
		
		return mav;
	}
	
	/**
	 * 처리내용 : EnrollRaspberryPiPage 연결
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : EnrollRaspberryPiPage
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/EnrollRaspberryPiPage", method = RequestMethod.POST)
	private ModelAndView enrollRaspberryPiPage(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(6, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : EnrollRaspberryPi 등록
	 * 작성일 : 2017. 10. 24.
	 * 작성자 : 신태휘
	 * @Method Name : EnrollRaspberryPi
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/EnrollRaspberry", method = RequestMethod.GET)
	private ModelAndView enrollRaspberryPi(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(7, bean);
		
		return mav;
	}
	
	/**
	 * 처리내용 : 운동량을 경험치로 전환 하기
	 * 작성일 : 2017. 11. 8.
	 * 작성자 : 신태휘
	 * @Method Name : setExp
	 * @return type : ModelAndView
	 */
	@RequestMapping(value="/SetExp", method = RequestMethod.POST)
	private ModelAndView setExp(@ModelAttribute ActivityBean bean) {
		
		mav = as.entrance(8, bean);
		
		return mav;
	}
	
}
