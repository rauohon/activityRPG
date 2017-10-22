/**
 * 
 */
package com.activityRPG.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @클래스명 : MemberController
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class MemberController {
	
private ModelAndView mav = null;
	
	@RequestMapping(value = "/")
	public ModelAndView home() {
		mav = new ModelAndView();
		mav.setViewName("home");
		
		return mav;
	}

}
