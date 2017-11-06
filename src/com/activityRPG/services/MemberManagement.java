package com.activityRPG.services;

import java.text.SimpleDateFormat;
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
 * @작성일 : 2017. 10. 14.
 * @설명 : 
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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

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
			//나의 정보 수정 >> 이름
		case 7:
			mav = nameupdate((MemberBean)object[0]);
			break;
			//나의 정보 수정 >> 이메일
		case 12:
			mav = mailupdate((MemberBean)object[0]);
			break;
			//나의 정보 수정 >> 핸드폰 번호
		case 13:
			mav = phoneupdate((MemberBean)object[0]);
			break;
			//나의 정보 수정 버튼
		case 14:
			mav = infoupdateName((MemberBean)object[0]);
			break;
			//나의 정보 수정 버튼
		case 15:
			mav = infoupdatePhone((MemberBean)object[0]);
			break;
			//나의 정보 수정 버튼
		case 16:
			mav = infoupdateMail((MemberBean)object[0]);
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
			//(받은)메시지 삭제
		case 17:
			mav = messagedelete((MemberBean)object[0]);
			break;
			//(보낸)메시지 삭제
		case 18:
			mav = sendMessagedelete((MemberBean)object[0]);
			break;
			//패스워드 변경 페이지
		case 19:
			mav = passwordChangePage((MemberBean)object[0]);
			break;
			//패스워드 변경
		case 20:
			mav = changePwd((MemberBean)object[0]);
			break;
			//패스워드 수정 >> 되돌리기
		case 21:
			mav = infoback((MemberBean)object[0]);
			break;
		}
		System.out.println(mav.getViewName() + " : 스위치 mav 네임");
		return mav;
	}

	//로그인
	public ModelAndView access(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
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
				}else {
					mav.setViewName("login");
					mav.addObject("message", "ADMIN password error");
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
						mav.addObject("message", "password error");
						mav.addObject("id", mb.getId());
					}
				}else {
					mav.setViewName("login");
					mav.addObject("message", "id error");
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
		System.out.println("아이디파인드");
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
		//System.out.println(mb.getId());
		try {
			if(dao.IdCheck(mb) != 0) {

				mav.addObject("message","존재하는 아이디가 맞습니다. 이메일을 입력해주세요.");
				System.out.println("아이디 확인");
				mav.setViewName("mailSend");
				mav.addObject("id", mb.getId());
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
	public ModelAndView mailsend(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		Boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

		// 일반 텍스트 이메일
		SimpleMailMessage smm = new SimpleMailMessage();

		String password = UUID.randomUUID().toString().replaceAll("-", "");
		password = password.substring(0, 10);
		
		mb.setPwd(password);
		System.out.println("service :: 패스워드 찾기 >> 이메일 발송하자");
		try {
			if(dao.mailcheck(mb) != 0) {
				System.out.println("들어왔니?");
				System.out.println(mb.getId());
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
				transaction = true;
			}else {
				mav.setViewName("mailSend");
				mav.addObject("message", "이메일이 일치하지 않습니다.");
				System.out.println("이메일 존재 하지 않음");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}

	//나의 정보
	public ModelAndView info(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보");
		mb.setId(session.getAttribute("id").toString());
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
		sb.append("<td>" + "핸드폰 번호" + "</td>");
		sb.append("<td>" + "이메일" + "</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>" + "<button id=\'user\' onClick=\"nameUpdate(\'"+ bean.getName() +"\')\">" + bean.getName() + "</button>" + "</td>");
		sb.append("<td>" + "<button id=\'user\' onClick=\"phoneUpdate(\'"+ bean.getPhone() +"\')\">" + bean.getPhone() + "</button>" + "</td>");
		sb.append("<td>" + "<button id=\'user\' onClick=\"mailUpdate(\'"+ bean.getEmail() +"\')\">" + bean.getEmail() + "</button>" + "</td>");
		sb.append("</tr>");
		sb.append("</table>" + "</br>");
		sb.append("<button id=\'infocheck\' onClick=\"total(\'activityDayLogForm \', \'ActivityDayLogPage\', \'post\')\" />"+"운동 정보 확인"+"</button>");
		sb.append("<button id=\'ras\' onClick=\"total(\'enrollRaspberryPiForm\', \'EnrollRaspberryPiPage\', \'post\')\" />"+"내 운동량 측정 등록하기"+"</button>");
		sb.append("<button id=\'infocheck\' onClick=\"total(\'passwordChangeForm\', \'passwordChangePage\', \'post\')\" />"+"비밀번호 변경"+"</button>");
		return sb.toString();
	}

	//패스워드 변경 페이지
	public ModelAndView passwordChangePage(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 패스워드 변경 페이지");
		try {
			System.out.println(session.getAttribute("id"));
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println("들어왔니");
				mav.addObject("id", mb.getId());
				mav.setViewName("passwordChange");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//패스워드 변경
	public ModelAndView changePwd(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 패스워드 변경");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println("패스워드 변경해주란");
				mav.setViewName("info");
				mav.addObject("id", mb.getId());
				mav.addObject("userInfo", getInformation(mb));
				
				mb.setPwd(enc.encode(mb.getPwd()));
				dao.pwdChange(mb);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	
	//나의 정보 수정 >> 이름 변경 페이지
	public ModelAndView nameupdate(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정 >> 이름");
		mb.setId(session.getAttribute("id").toString());
		try {
			if(dao.IdCheck(mb) != 0) {
				if(dao.nameUpdate(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("userInfo", getNameUpdate(mb));
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//이름 수정 >> 정보 보여줄 값
	private String getNameUpdate(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		MemberBean bean = dao.info(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "이름" + "</td>");
		sb.append("<td>" + "핸드폰 번호" + "</td>");
		sb.append("<td>" + "이메일" + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>" + "<input type=\'text\' value=\'"+bean.getName()+"\' name=\'name\' />" + "</td>");
		sb.append("<td>" + bean.getPhone() + "</button>" + "</td>");
		sb.append("<td>" + bean.getEmail() + "</button>" + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<button id=\'infocheck\' onClick=\'infoUpdateName(\"name\",\""+bean.getId()+"\")\'>" + "정보 수정" + "</button>");
		return sb.toString();
	}

	//나의 정보 수정 >> 핸드폰 번호 변경 페이지
	public ModelAndView phoneupdate(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정 >> 핸드폰 번호");
		mb.setId(session.getAttribute("id").toString());
		try {
			if(dao.IdCheck(mb) != 0) {
				if(dao.phoneUpdate(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("userInfo", getPhoneUpdate(mb));
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//핸드폰 번호 수정 >> 정보 보여줄 값
	private String getPhoneUpdate(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		MemberBean bean = dao.info(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "이름" + "</td>");
		sb.append("<td>" + "핸드폰 번호" + "</td>");
		sb.append("<td>" + "이메일" + "</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>" + bean.getName() + "</button>" + "</td>");
		sb.append("<td>" + "<input type=\'text\' value=\'"+bean.getPhone()+"\' name=\'phone\' />" + "</td>");
		sb.append("<td>" + bean.getEmail() + "</button>" + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<button id=\'infocheck\' onClick=\'infoUpdatePhone(\"phone\",\""+bean.getId()+"\")\'>" + "정보 수정" + "</button>");
		return sb.toString();
	}

	//나의 정보 수정 >> 이메일 변경 페이지
	public ModelAndView mailupdate(MemberBean mb) throws Exception {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정 >> 이메일");
		mb.setId(session.getAttribute("id").toString());
		try {
			if(dao.IdCheck(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("userInfo", getMailUpdate(mb));
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
	//이메일 수정 >> 정보 보여줄 값
	private String getMailUpdate(MemberBean mb) {
		StringBuffer sb = new StringBuffer();
		MemberBean bean = dao.info(mb);
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + "이름" + "</td>");
		sb.append("<td>" + "핸드폰 번호" + "</td>");
		sb.append("<td>" + "이메일" + "</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td>" + bean.getName() + "</button>" + "</td>");
		sb.append("<td>" + bean.getPhone() + "</button>" + "</td>");
		sb.append("<td>" + "<input type=\'text\' value=\'"+bean.getEmail()+"\' name=\'email\' />" + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<button id=\'infocheck\' onClick=\'infoUpdateMail(\"email\",\""+bean.getId()+"\")\'>" + "정보 수정" + "</button>");
		return sb.toString();
	}
	
	//나의 정보 수정 버튼 >> 이름
	public ModelAndView infoupdateName(MemberBean mb) { 
		ModelAndView mav = new ModelAndView();
		
		System.out.println("service :: 나의 정보 수정 버튼");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				if(dao.nameUpdate(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("name", mb.getName());
					mav.addObject("userInfo", getInformation(mb));
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//나의 정보 수정 버튼 >> 핸드폰 번호
	public ModelAndView infoupdatePhone(MemberBean mb) { 
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정 버튼");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				if(dao.phoneUpdate(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("phone", mb.getPhone());
					mav.addObject("userInfo", getInformation(mb));
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message", "로그인 후 이용해주세요.");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//나의 정보 수정 버튼 >> 이메일
	public ModelAndView infoupdateMail(MemberBean mb) { 
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 나의 정보 수정 버튼");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				if(dao.mailUpdate(mb) != 0) {
					mav.setViewName("info");
					mav.addObject("Mail", mb.getEmail());
					mav.addObject("userInfo", getInformation(mb));
				}
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
			mb.setId(session.getAttribute("id").toString());
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

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		
		System.out.println("service :: 메시지 작성한 거 보내기");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				System.out.println(mb.getId());
				if(dao.writingMessage(mb) != 0) {
					mav.addObject("id", mb.getId());
					mav.addObject("messagelist", getlist(mb));
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

		System.out.println("service :: 받은 메시지 리스트");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				mb.setId(session.getAttribute("id").toString());
				mav.addObject("id", mb.getId());
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
			sb.append("<td>" + sdf.format(bean.get(i).getDate()) + "</td>");
			sb.append("<td>" + "<button id=\'del\' onClick=\"messageDelete(\'"+ bean.get(i).getMbid() +"\', \'"+ bean.get(i).getMsgText() +"\')\">" + "삭제" + "</button>" + "</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}

	//받은 메시지 삭제
	public ModelAndView messagedelete(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 받은 메시지 삭제");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(mb.getId().equals(session.getAttribute("id"))) {
				System.out.println("ddd");
				System.out.println(mb.getMbid());
				if(dao.messageDelete(mb) != 0) {
					System.out.println("eee");
					mav.addObject("messagelist", getlist(mb));
					mav.setViewName("getMessage");
				}
			}else {
				mav.addObject("messagelist", getlist(mb));
				mav.setViewName("getMessage");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//보낸 메시지 리스트
	public ModelAndView sendmessagelist(MemberBean mb) {
		ModelAndView mav = new ModelAndView();

		System.out.println("service :: 보낸 메시지 리스트");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(dao.IdCheck(mb) != 0) {
				mb.setId(session.getAttribute("id").toString());
				mav.addObject("id", mb.getId());
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
			sb.append("<td>" + sdf.format(bean.get(i).getDate()) + "</td>");
			sb.append("<td>" + "<button id=\'del\' onClick=\"sendMessageDelete(\'"+ bean.get(i).getMbid() +"\', \'"+ bean.get(i).getMsgText() +"\')\">" + "삭제" + "</button>" + "</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	
	//보낸 메시지 삭제
	public ModelAndView sendMessagedelete(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 보낸 메시지 삭제");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(mb.getId().equals(session.getAttribute("id"))) {
				System.out.println(mb.getMbid());
				if(dao.sendMessageDelete(mb) != 0) {
					mav.addObject("messagelist", sendlist(mb));
					mav.setViewName("sendMessage");
				}
			}else {
				mav.addObject("messagelist", sendlist(mb));
				mav.setViewName("sendMessage");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}

	//패스워드 수정 >> 되돌리기
	public ModelAndView infoback(MemberBean mb) {
		ModelAndView mav = new ModelAndView();
		System.out.println("service :: 패스워드 수정 >> 되돌리기");
		try {
			mb.setId(session.getAttribute("id").toString());
			if(mb.getId().equals(session.getAttribute("id"))) {
				mav.addObject("id", mb.getId());
					mav.setViewName("info");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mav;
	}
}
