/**
 * 
 */
package com.activityRPG.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.Email;
import com.activityRPG.beans.MemberBean;
import com.activityRPG.controller.MemberController;
import com.activityRPG.services.EmailSender;
import com.activityRPG.services.MemberManagement;

/**
 * @클래스명 : MemberController
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Controller
public class MemberController {

	@Autowired
	private MemberManagement mm;
	@Autowired
	private EmailSender mail;
	private ModelAndView mav = null;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);	

	//메인 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		mav = new ModelAndView();
		System.out.println("MemberController :: home");
		mav.setViewName("home");
		return mav;
	}
	//메인 페이지 - 데이터 o --> post
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView home(@ModelAttribute MemberBean member) {
		mav = new ModelAndView();
		System.out.println("MemberController :: home(post)");
		mav.setViewName("home");
		return mav;
	}

	//로그인 페이지 이동
	@RequestMapping(value = "/LoginForm", method = RequestMethod.GET)
	public ModelAndView login() {
		mav = new ModelAndView();
		System.out.println("MemberController :: login page");
		mav.setViewName("login");
		return mav;  
	}

	//로그인 확인 후 home.jsp
	@RequestMapping(value = "/Access", method = RequestMethod.POST)
	public ModelAndView access(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(1, member);
		System.out.println("MemberController :: login check");
		return mav;  
	}

	//회원가입 페이지 이동
	@RequestMapping(value = "/JoinForm", method = RequestMethod.GET)
	public ModelAndView join() {
		mav = new ModelAndView();
		System.out.println("MemberController :: join page");
		mav.setViewName("join");
		return mav;  
	}

	//회원가입 확인 후 home.jsp
	@RequestMapping(value = "/Join", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(2, member);
		System.out.println("MemberController :: login check");
		return mav;  
	}
	
	//아이디찾기 페이지 이동
	@RequestMapping(value = "/IdFind", method = RequestMethod.GET)
	public ModelAndView idFind() {
		mav = new ModelAndView();
		System.out.println("MemberController :: idFind page");
		mav.setViewName("idFind");
		return mav;  
	}
	
	//아이디찾기 페이지 이동
	@RequestMapping(value = "/PwdFind", method = RequestMethod.GET)
	public ModelAndView pwdFind() {
		mav = new ModelAndView();
		System.out.println("MemberController :: pwdFind page");
		mav.setViewName("pwdFind");
		return mav;  
	}
	
	@RequestMapping(value="/Mail", method = RequestMethod.POST)
	   public ModelAndView mailSender(@ModelAttribute Email bean) {
	   
	      mav=mail.entrance(0, bean);
	      
	      return mav;
	   }
}
