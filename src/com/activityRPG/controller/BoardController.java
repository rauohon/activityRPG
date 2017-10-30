/**
 * 
 */
package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : Board
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class BoardController {
	@Autowired
	private ProjectUtils session;
	ModelAndView mav = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView accessMain() {
		mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value = "/AttackBoard", method = RequestMethod.GET)
	public ModelAndView attackBoard()
	{
		mav = new ModelAndView();
		mav.setViewName("attackBoard");
		return mav;		    
	}
	
	
	
}
