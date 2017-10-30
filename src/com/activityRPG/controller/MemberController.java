package com.activityRPG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*import com.activityRPG.beans.Email;*/
import com.activityRPG.beans.MemberBean;
import com.activityRPG.controller.MemberController;
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
	private ModelAndView mav = null;


	//메인 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		mav = new ModelAndView();
		System.out.println("MemberController :: 메인페이지(get)");
		mav.setViewName("home");
		return mav;
	}
	//메인 페이지 - 데이터 o --> post
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView home(@ModelAttribute MemberBean member) {
		mav = new ModelAndView();
		System.out.println("MemberController :: 메인페이지(post)");
		mav.setViewName("home");
		return mav;
	}

	//로그인 페이지 이동
	@RequestMapping(value = "/LoginForm", method = RequestMethod.GET)
	public ModelAndView login() {
		mav = new ModelAndView();
		System.out.println("MemberController :: 로그인 페이지");
		mav.setViewName("login");
		return mav;  
	}

	//로그인 확인 후 home.jsp
	@RequestMapping(value = "/Access", method = RequestMethod.POST)
	public ModelAndView access(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(1, member);
		System.out.println("MemberController :: 로그인 하기");
		return mav;  
	}
	
	//아이디 중복확인
	@RequestMapping(value = "/IdCheck", method = RequestMethod.POST)
	public ModelAndView idcheck(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(0, member);
		System.out.println("MemberController :: 아이디 중복확인");
		return mav;  
	}
	
	//로그아웃
	@RequestMapping(value = "/AccessOut", method = RequestMethod.POST)
	public ModelAndView logout(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(-1, member);
		System.out.println("MemberController :: 로그아웃");
		return mav;  
	}

	//회원가입 페이지 이동
	@RequestMapping(value = "/JoinForm", method = RequestMethod.GET)
	public ModelAndView join() {
		mav = new ModelAndView();
		System.out.println("MemberController :: 회원가입 페이지");
		mav.setViewName("join");
		return mav;  
	}

	//회원가입 확인 후 home.jsp
	@RequestMapping(value = "/Join", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: join check");
		mav = mm.entrance(2, member);
		System.out.println(mav.getViewName() + " : 서비스 직후 mav 네임");
		return mav;  
	}
	
	//아이디찾기 페이지 이동
	@RequestMapping(value = "/IdFind", method = RequestMethod.GET)
	public ModelAndView idFind() {
		mav = new ModelAndView();
		System.out.println("MemberController :: 아이디 찾기 페이지");
		mav.setViewName("idFind");
		return mav;  
	}
	
	//아이디 찾기
		@RequestMapping(value = "/IdFind", method = RequestMethod.POST)
		public ModelAndView idFind(@ModelAttribute MemberBean member) throws Exception {
			mav = mm.entrance(3, member);
			System.out.println("MemberController :: idFind >> 핸드폰 번호 확인");
			return mav;  
		}
	
	//비밀번호 찾기 페이지 이동
	@RequestMapping(value = "/PwdFind", method = RequestMethod.GET)
	public ModelAndView pwdFind() {
		mav = new ModelAndView();
		System.out.println("MemberController :: 비밀번호 찾기 페이지");
		mav.setViewName("pwdFind");
		return mav;  
	}
	
	//비밀번호 찾기
	@RequestMapping(value = "/PwdFind", method = RequestMethod.POST)
	public ModelAndView pwdFind(@ModelAttribute MemberBean member) throws Exception {
		mav = mm.entrance(4, member);
		System.out.println("MemberController :: pwdFind >> 아이디 확인");
		return mav;  
	}
	
	//비밀번호 찾기_이메일 발송
	@RequestMapping(value="/MailSend", method = RequestMethod.POST)
	public ModelAndView mailSender(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: pwdFind >> 메일 발송");
		mav = mm.entrance(5, member);
		return mav;
	}

	//나의 정보
	@RequestMapping(value="/Info", method = RequestMethod.POST)
	public ModelAndView info(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 나의 정보");
		mav = mm.entrance(6, member);
		return mav;
	}
	
	//나의 정보 수정
	@RequestMapping(value="/InfoUpdate", method = RequestMethod.POST)
	public ModelAndView infoUpdate(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 나의 정보 수정");
		mav = mm.entrance(7, member);
		return mav;
	}
		
	//받은메시지함(메인 메시지창)
	@RequestMapping(value="/getMessageList", method = RequestMethod.POST)
	public ModelAndView getMessage(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 받은 메시지창(메인)");
		mav = mm.entrance(8, member);
		return mav;
	}
	
	//글 쓰기
	@RequestMapping(value="/writingMessage", method = RequestMethod.POST)
	public ModelAndView writingMessage(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 메시지 쓰기 페이지");
		mav = mm.entrance(9, member);
		return mav;
	}
	
	//쓴 글 보내기
	@RequestMapping(value="/setMessage", method = RequestMethod.POST)
	public ModelAndView setMessage(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 메시지 쓴 글 보내기");
		mav = mm.entrance(10, member);
		return mav;
	}
	
	//보낸메시지함
	@RequestMapping(value="/sendmessage", method = RequestMethod.POST)
	public ModelAndView sendMessage(@ModelAttribute MemberBean member) throws Exception {
		System.out.println("MemberController :: 보낸 메시지창");
		mav = mm.entrance(11, member);
		return mav;
	}
}
