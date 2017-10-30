package com.activityRPG.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	public ModelAndView entrance(int code, Object...object) throws Exception{
		ModelAndView mav = null;
		session.setAttribute("page",((MemberBean)object[0]).getPage());
		switch(code) {
		//logOut
		case -1:
			mav = logout((MemberBean)object[0]);
			break;
			//아이디 중복확인
		case 0:
			System.out.println("case");
			mav = idCheck((MemberBean)object[0]);
			break;
			//로그인
		case 1:
			mav = access((MemberBean)object[0]);
			break;
			//회원가입
		case 2:
			mav = join((MemberBean)object[0]);
			System.out.println(mav.getViewName() + " : 서비스 스위치 직후 mav 네임");
			break;
			//아이디 찾기
		case 3:
			mav = idfind((MemberBean)object[0]);
			break;
			//패스워드 찾기
		case 4:
			mav = pwdfind((MemberBean)object[0]);
			break;
			//패스워드 찾기 > 이메일 발송
		case 5:
			mav = mailsend((MemberBean)object[0]);
			break;
			//나의정보
		case 6:
			mav = info((MemberBean)object[0]);
			break;
		//나의 정보 수정
		case 7:
			mav = infoupdate((MemberBean)object[0]);
			break;
		//(받은)메시지 리스트
		case 8:
			mav = getmessagelist((MemberBean)object[0]);
			break;
		//메시지 글 쓰기 창
		case 9:
			mav = writingmessage((MemberBean)object[0]);
			break;
		//메시지 작성 >> 보내기
		case 10:
			mav = setmessage((MemberBean)object[0]);
			break;
		//(받은)메시지 리스트
		case 11:
			mav = sendmessagelist((MemberBean)object[0]);
			break;
		}
	
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//로그인
	public ModelAndView access(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		String message = null;
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		System.out.println("service :: login");
		
		try {
			if(dao.adminId(mb) != 0) {
				MemberBean bean = dao.PwdCheck(mb);
				if(enc.matches(mb.getPwd(), bean.getPwd())) {
					System.out.println("admin login successful");

					//로그인 한 상태 저장
					mb.setType(1);
					if(dao.AccessHistory(mb) != 0) {
						System.out.println("로그인 함 :: 1");

						session.setAttribute("id", mb.getId());
						mav.setViewName("adminmain");
						transaction = true;
					}
				}
			}else {
				if(dao.IdCheck(mb) != 0) {
					MemberBean bean = dao.PwdCheck(mb);
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
						mav.setViewName("login");
						message = "password error";
					}
				}else {
					mav.setViewName("login");
					message = "id error";
				}
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
			}else {
				mav.setViewName("home");
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

		System.out.println("service :: join start");
		try {
			if(dao.IdCheck(mb) == 0) {
				mb.setPwd(enc.encode(mb.getPwd()));
				if(dao.joinSuccess(mb) != 0) {
					System.out.println("join successful");

					mav.setViewName("home");
					transaction = true;
				}
			}else {
				mav.setViewName("join");
				mav.addObject("message", "회원가입이 제대로 되지 않았습니다.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);		
		System.out.println(mav.getViewName() + " : 서비스 if 밖 mav 네임");
		return mav;
	}

	//아이디 중복확인
	public ModelAndView idCheck(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		String idchecked = "사용가능한 아이디 입니다.";

		System.out.println(mb.getId());
		System.out.println("service :: idCheck");
		try {
			if(dao.IdCheck(mb) == 0) {
				System.out.println("중복되는 아이디 없음");
				mav.addObject("idchecked", idchecked);
				mav.addObject("userid", mb.getId());
				mav.setViewName("join");
			}else {
				System.out.println("아이디 중복");
				mav.addObject("idchecked", "다른 아이디를 입력해주세요.");
				mav.setViewName("join");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//아이디 찾기
	public ModelAndView idfind(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		String ms = "찾으시는 아이디는 ";
		String msg = " 입니다.";
		System.out.println("service :: idFind >> mailCheck");
		try {
			if(dao.idFind(mb) != 0) {
				MemberBean id = dao.idSend(mb);
				mav.setViewName("pwdFind");
				mav.addObject("message", ms + id.getId() + msg);
				System.out.println("핸드폰번호 확인");
			}else {
				mav.setViewName("idFind");
				mav.addObject("message", "핸드폰번호가 일치하지 않습니다. 다시 입력해주세요.");
				System.out.println("핸드폰번호 존재 하지 않음");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//패스워드 찾기
	public ModelAndView pwdfind(MemberBean mb) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: pwdFind >> idCheck");
		try {
			if(dao.IdCheck(mb) != 0) {

				mav.addObject("message","존재하는 아이디가 맞습니다. 이메일을 입력해주세요.");
				System.out.println("아이디 확인");
				mav.setViewName("mailSend");
			}else {
				mav.setViewName("pwdFind");
				mav.addObject("message", "아이디가 일치하지 않습니다. 다시 입력해주세요.");
				System.out.println("아이디 존재 하지 않음");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//패스워드 찾기_이메일 발송
	public ModelAndView mailsend(MemberBean mb) {
		ModelAndView mav = new ModelAndView();

		// 일반 텍스트 이메일
		SimpleMailMessage smm = new SimpleMailMessage();

		String password = UUID.randomUUID().toString().replaceAll("-", "");
		password = password.substring(0, 10);

		mb.setPwd(password);
		System.out.println("service :: 패스워드 찾기 >> 이메일 발송하자");
		try {
			if(dao.mailcheck(mb) != 0) {
				MemberBean id = dao.mailSend(mb);
				
				System.out.println("이메일 발송 함.");
				System.out.println("임시 비밀번호 : " + password);

				smm.setFrom("wldnjs08@gmail.com");
				smm.setTo(id.getEmail().toString());
				smm.setSubject("TEXT-RPG의 임시비밀번호입니다.");
				smm.setText("안녕하세요. TEXT-RPG입니다. \n 임시 비밀번호 : " + password);

				javaMailSenderImpl.send(smm);
				mav.setViewName("login");
				mav.addObject("message", "임시 비밀번호를 발송했습니다.");

				mb.setPwd(enc.encode(mb.getPwd()));
				dao.pwdUpdate(mb);
			}else {
				mav.setViewName("mailSend");
				mav.addObject("message", "이메일이 일치하지 않습니다.");
				System.out.println("이메일 존재 하지 않음");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//나의 정보
	public ModelAndView info(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 나의 정보");
		try {
			if(dao.IdCheck(mb) != 0) {
				
				mav.setViewName("info");
				mav.addObject("userInfo", getInformation(mb));
				
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//정보 보여줄 값
	private String getInformation(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		MemberBean bean = dao.info(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "이름" + "</td>");
		sb.append("<td>" + "비밀번호" + "</td>");
		sb.append("<td>" + "핸드폰 번호" + "</td>");
		sb.append("<td>" + "이메일" + "</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>" + bean.getName() + "</td>");
		sb.append("<td>" + bean.getPwd() + "</td>");
		sb.append("<td>" + bean.getPhone() + "</td>");
		sb.append("<td>" + bean.getEmail() + "</td>");
		sb.append("</tr>");
		sb.append("<table>");
		return sb.toString();
	}

	//나의 정보 수정
	public ModelAndView infoupdate(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정");
		try {
			if(dao.IdCheck(mb) != 0) {
				mav.setViewName("infoUpdate");
				mav.addObject("userInfo", getInformation(mb));
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//메시지 글 쓰기 창
	public ModelAndView writingmessage(MemberBean mb) { 
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 메시지 글 쓰기 창");
		try {
			if(dao.IdCheck(mb) != 0) {
				mav.setViewName("writingMessage");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//메시지 작성 >> 보내기
	public ModelAndView setmessage(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		System.out.println("service :: 메시지 작성한 거 보내기");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println(mb.getId());
				if(dao.writingMessage(mb) != 0) {
					
					mav.setViewName("getMessage");
					transaction = true;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}

	//받은 메시지 리스트
	public ModelAndView getmessagelist(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		StringBuffer sb = new StringBuffer();

		System.out.println("service :: 받은 메시지 리스트");
		try {
			if(dao.IdCheck(mb) != 0) {
				mb.setId(session.getAttribute("id").toString());
				mav.addObject("messagelist", getlist(mb));
				mav.setViewName("getMessage");
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//받은 메시지 리스트 보여줄 값
	private String getlist(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		List<MemberBean> bean = dao.getMessageList(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "보낸 사람" + "</td>");
		sb.append("<td>" + "내용" + "</td>");
		sb.append("<td>" + "날짜" + "</td>");
		sb.append("</tr>");

		for(int i=0; i<bean.size(); i++) {
            sb.append("<tr>");
            sb.append("<td>" + bean.get(i).getMbid() + "</td>");
            sb.append("<td>" + bean.get(i).getMsgText() + "</td>");
            sb.append("<td>" + bean.get(i).getDate() + "</td>");
            sb.append("</tr>");
         }
		return sb.toString();
	}

	//보낸 메시지 리스트
	public ModelAndView sendmessagelist(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 보낸 메시지 리스트");
		try {
			if(dao.IdCheck(mb) != 0) {
				mb.setId(session.getAttribute("id").toString());
				mav.addObject("messagelist", sendlist(mb));
				mav.setViewName("sendMessage");
			}else {
				mav.setViewName("home");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//보낸 메시지 리스트 보여줄 값
	private String sendlist(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		List<MemberBean> bean = dao.sendMessageList(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "보낸 사람" + "</td>");
		sb.append("<td>" + "내용" + "</td>");
		sb.append("<td>" + "날짜" + "</td>");
		sb.append("</tr>");

		for(int i=0; i<bean.size(); i++) {
            sb.append("<tr>");
            sb.append("<td>" + bean.get(i).getMbid() + "</td>");
            sb.append("<td>" + bean.get(i).getMsgText() + "</td>");
            sb.append("<td>" + bean.get(i).getDate() + "</td>");
            sb.append("</tr>");
         }
		return sb.toString();
	}
	
	/*public Map<String, String> idCheck(MemberBean mb) {
		Map<String, String> json = new HashMap<String, String>();
		String user = "N";
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		System.out.println("service :: idCheck");
		try {
			if(dao.IdCheck(mb) == 0) {
				user = "Y";
				System.out.println("중복되는 아이디 없음");
				transaction = true;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		json.put("user", user);
		setTransactionResult(transaction);
		return json;
	}*/
}
