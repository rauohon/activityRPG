package com.activityRPG.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;
import com.activityRPG.beans.MemberBean;

/**
 * @클래스명 : MemberManagement
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */

@Service
public class MemberManagement extends TranEx {
	
	@Autowired
	private Encryption enc;
	@Autowired
	private ProjectUtils session;
	@Autowired
	private IMBatisDao dao;
	
	public ModelAndView entrance(int code, Object...object) throws Exception {
		ModelAndView mav = null;
		session.setAttribute("page", ((MemberBean)object[0]).getPage());
		switch(code) {
		//logOut
		case -1:
			mav = logout((MemberBean)object[0]);
			break;
		//logIn check
		case 1:
			mav = access((MemberBean)object[0]);
			break;
		//회원가입
		case 2:
			mav = join((MemberBean)object[0]);
			System.out.println(mav.getViewName() + " : 서비스 스위치 직후 mav 네임");
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}
	
	//logIn check
	public ModelAndView access(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		String message = null;
		boolean transaction = false;
		
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		
		System.out.println("service :: login");
		MemberBean bean = dao.PwdCheck(mb);
		try {
			if(dao.IdCheck(mb) != 0) {
				System.out.println(mb.getId());
				System.out.println(mb.getPwd());
				System.out.println("암호화" + dao.PwdCheck(mb));
				if(enc.matches(mb.getPwd(), bean.getPwd())) {
					System.out.println("login successful");
					
					//로그인 한 상태 저장
					mb.setType(1);
					if(dao.AccessHistory(mb) != 0) {
						System.out.println("로그인 함 :: 1");
						
						session.setAttribute("id", mb.getId());
						mav.setViewName("home");
						transaction = true;
					}
				}else {
					message = "password error";
				}
			}else {
				message = "id error";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}
	
	//logOut
	public ModelAndView logout(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		RedirectView rv = null;
		boolean transaction = false;
		
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		System.out.println("service :: logout");
		try {
			mb.setId(session.getAttribute("id").toString());
			mb.setType(-1);
			if(dao.AccessHistory(mb) != 0) {
				System.out.println("logout successful");

				session.removeAttribute("id");
				rv = new RedirectView("/");
				rv.setExposeModelAttributes(false);
				mav.setView(rv);
				transaction = true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}
	
	//회원가입
	public ModelAndView join(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		
		try {
			System.out.println("try 밑에");	
			mb.setPwd(enc.encode(mb.getPwd()));
			if(dao.joinSuccess(mb) != 0) {
				System.out.println("join successful");				
				mav.setViewName("home");		
				System.out.println(mav.getViewName() + " : 서비스 if 안 mav 네임");
				transaction = true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);		
		System.out.println(mav.getViewName() + " : 서비스 if 밖 mav 네임");
		return mav;
	}
	
}
