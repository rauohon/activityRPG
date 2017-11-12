package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.ProjectUtils;


/**
 * @클래스명 : GuildBoard
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class GuildBoard extends TranEx  {
	
	ModelAndView mav = new ModelAndView();
	@Autowired
	private IMBatisDao dao;
	@Autowired
	private ProjectUtils session;
	boolean transaction = false;
	
	/**
	 * 처리내용 : 길드보드 서비스 분기
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : entrance
	 * @return type : ModelAndView
	 */
	public ModelAndView entrance(int i, Object bean) {
		switch(i) {
		
			case 0:
				mav = guildBoardPage((BoardBean)bean);
				break;
			
			case 1:
				mav = writeGBoardPage((BoardBean)bean);
				break;
				
			case 2:
				mav = writeGBoard((BoardBean)bean);
				break;
				
			case 3:
				mav = readGBoardPage((BoardBean)bean);
				break;
			
			case 4:
				mav = modifyGBoardPage((BoardBean)bean);
				break;
			
			case 5:
				mav = modifyGBoard((BoardBean)bean);
				break;
				
			case 6:
				mav = deleteGBoard((BoardBean)bean);
				break;
			
			case 7:
				mav = replyGBoard((BoardBean)bean);
				break;
				
			case 8:
				mav = deleteReplyGBoard((BoardBean)bean);
				break;
			
			case 9:
				mav = fileUploadGBoard((BoardBean)bean);
				break;
		}		
		
		return mav;
	}

	/**
	 * 처리내용 : 
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : fileUploadGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView fileUploadGBoard(BoardBean bean) {
		
		// 하고 싶지만 보류 하는 걸로
		
		return null;
	}

	/**
	 * 처리내용 : 8. 댓글 삭제
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : deleteReplyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView deleteReplyGBoard(BoardBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		System.out.println(bean.getGbCode() + " :: 댓글 삭제 글 번호");
		System.out.println(bean.getGrCode() + " :: 댓글 삭제 댓글 번호");
		if(dao.setGuildBoardReplyDelete(bean) != 0) {
			transaction = true;
			mav = readGBoardPage(bean);
			setTransactionResult(transaction);
		}		
		return mav;
	}

	/**
	 * 처리내용 : 7. 댓글 등록
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView replyGBoard(BoardBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		if(dao.setGuildBoardReply(bean) != 0) {
			transaction = true;
			mav = readGBoardPage(bean);
		}
		setTransactionResult(transaction);
		return mav;
	}

	/**
	 * 처리내용 : 6. 게시글 삭제
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : deleteGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView deleteGBoard(BoardBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		try {
			if(bean.getChName().equals(session.getAttribute("chName").toString())) {
				if(dao.setGuildBoardRemove(bean) != 0) {
					transaction = true;
					RedirectView rv = null;
					rv = new RedirectView("/GuildBoardPage");
					rv.setExposeModelAttributes(false);
					mav.setView(rv);
				}				
			}else {
				mav.addObject("msg", "타인의 글은 삭제할 수 없어요.");
			}
		}catch(Exception e) {
			mav.setViewName("home");
			e.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}	
	
	/**
	 * 처리내용 : 5. 게시글 수정하기
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : modifyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView modifyGBoard(BoardBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		try {
			System.out.println("안오냐?");
			bean.setId(session.getAttribute("id").toString());
			System.out.println(bean.getGbCode());
			System.out.println(bean.getGbTitle());
			System.out.println(bean.getGbContent());
			System.out.println(dao.setGuildBoardModify(bean));
			if(dao.setGuildBoardModify(bean) !=0) {
				System.out.println("보드 수정확인");
				transaction = true;
				RedirectView rv = null;
				rv = new RedirectView("/GuildBoardPage");
				rv.setExposeModelAttributes(false);
				mav.setView(rv);
			}else {
				System.out.println("보드 수정 실패 확인");
				mav.addObject("msg","system error");
				mav.setViewName("guildBoard");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		setTransactionResult(transaction);		
		return mav;
	}
	
	/**
	 * 처리내용 : 4-1. 글 수정에서 원글 내용 불러오기
	 * 작성일 : 2017. 10. 27.
	 * 작성자 : 신태휘
	 * @Method Name : replyGBoardRead
	 * @return type : Map<String,String>
	 */
	private Map<String, String> modifyGBoardRead(BoardBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		bean = dao.getGuildBoardContent(bean);
		
		try {
			map.put("writer", session.getAttribute("chName").toString());
			map.put("title", bean.getGbTitle());
			map.put("reply", "-----------------------------\n");
			map.put("content", bean.getGbContent());
			map.put("wdate", sdf.format(bean.getGbWDate()));
			map.put("gbGroup",String.valueOf(bean.getGbGroup()));
			map.put("gbStep", String.valueOf(bean.getGbStep()));
			map.put("gbIndent", String.valueOf(bean.getGbIndent()));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return map;
	}

	/**
	 * 처리내용 : 4. modifyGBoardPage연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : modifyGBoard
	 * @return type : ModelAndView
	 */
	private ModelAndView modifyGBoardPage(BoardBean bean) {

		System.out.println(bean.getChName() + "캐릭터 이름 가져오기");
		try {
			if(bean.getChName().equals(session.getAttribute("chName"))) {
				Map<String, String> map = modifyGBoardRead(bean);
				mav.addObject("action","ModifyGBoard");
				mav.addObject("writer", map.get("writer"));
				mav.addObject("gbTitle",map.get("title"));
				mav.addObject("reply",map.get("reply"));
				mav.addObject("content",map.get("content"));
				mav.addObject("hit",map.get("hit"));
				mav.addObject("gbGroup",map.get("gbGroup"));
				mav.addObject("gbStep",map.get("gbStep"));
				mav.addObject("gbIndent",map.get("gbIndent"));				
			}else {
				mav.addObject("msg", "타인의 글은 수정하실수 없어요");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("gBoardWriteModify");

		return mav;
	}
	
	/**
	 * 처리내용 : 3-2. guildReplyList 출력
	 * 작성일 : 2017. 10. 31.
	 * 작성자 : 신태휘
	 * @Method Name : guildReplyList
	 * @return type : String
	 */
	private String guildReplyList(BoardBean bean) {
		StringBuffer sb = new StringBuffer();
		List<BoardBean> replyList = dao.getGuildBoardReply(bean);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Collections.sort(replyList, new Comparator<BoardBean>(){
			@Override
			public int compare(BoardBean r2, BoardBean r1){
				return r1.getGbWDate().compareTo(r2.getGbWDate());
			}
		});
		
		sb.append("<table><tr><th>작성자</th><th>댓글 내용</th><th>댓글 작성일</th><td></td><td></td></tr>");
		for(int i = 0 ; i < replyList.size() ; i++) {
			sb.append("<tr><td>");
			sb.append(replyList.get(i).getChName());
			sb.append("</td><td>");
			sb.append(replyList.get(i).getGbReplyContent());
			sb.append("</td><td>");
			sb.append(sdf.format((replyList.get(i).getGbWDate())));
			sb.append("</td><td>");
			sb.append("<input type='button' value='삭제' onclick='replyForm(\"replydelete\",\"ReplyDelete\",\"POST\",\"" + replyList.get(i).getGrCode()  + "\")' />");
			sb.append("</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	
	/**
	 * 처리내용 : 3-1. 게시글 내용 출력
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	private Map<String, String> readGBoard(BoardBean bean) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		bean = dao.getGuildBoardContent(bean);
		
		map.put("writer", bean.getChName());
		map.put("title", bean.getGbTitle());
		map.put("content", bean.getGbContent());
		map.put("wdate", sdf.format(bean.getGbWDate()));
		map.put("hit", String.valueOf(bean.getGbHit()));
		map.put("gbCode",String.valueOf(bean.getGbCode()));
		
		return map;
	}

	/**
	 * 처리내용 : 3. 게시글 읽기 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : readGBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView readGBoardPage(BoardBean bean) {
		Map<String, String> map = readGBoard(bean);
		if(dao.setGuildBoardUpHit(bean) != 0) {
			
			mav.addObject("writer", map.get("writer"));
			mav.addObject("title", map.get("title"));
			mav.addObject("content", map.get("content"));
			mav.addObject("wdate", map.get("wdate"));
			mav.addObject("hit", map.get("hit"));
			mav.addObject("gbCode",map.get("gbCode"));
			mav.addObject("reply",guildReplyList(bean));

			mav.setViewName("gBoardRead");
		}else {
			RedirectView rv = null;
			rv = new RedirectView("/GuildBoardPage");
			rv.setExposeModelAttributes(false);
			mav.setView(rv);
		}
		return mav;
	}
	
	/**
	 * 처리내용 : 2. 게시글 작성
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView writeGBoard(BoardBean bean) {
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
		try {
			System.out.println("안오냐?");
			bean.setId(session.getAttribute("id").toString());
			if(dao.setGuildBoardWrite(bean) !=0) {
				transaction = true;
				RedirectView rv = null;
				rv = new RedirectView("/GuildBoardPage");
				rv.setExposeModelAttributes(false);
				mav.setView(rv);
			}else {
				mav.addObject("msg","system error");
				mav.setViewName("guildBoard");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		setTransactionResult(transaction);
		return mav;
	}

	/**
	 * 처리내용 : 1. 게시글 작성 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : writeBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView writeGBoardPage(BoardBean bean) {
			
			mav.addObject("action","WriteGBoard");
			mav.setViewName("gBoardWrite");
				
		return mav;
	}
	
	/**
	 * 처리내용 : 0-1 게시글 리스트 출력
	 * 작성일 : 2017. 10. 26.
	 * 작성자 : 신태휘
	 * @Method Name : guildBoardList
	 * @return type : String
	 */
	private String guildBoardList(BoardBean bean) {
		StringBuffer sb = new StringBuffer();
		if(bean.getGbStep() <= 1) {
			bean.setGbStep(1);
			bean.setGbIndent(10);
		}else if(bean.getGbStep() >= 2){
			bean.setGbStep(bean.getGbStep()+((bean.getGbStep()-1)*9));
			bean.setGbIndent(bean.getGbStep()+9);
		}
		List<BoardBean> gBoardList = dao.getGuildBoardList(bean);
		sb.append("<table><thead><tr><th>글 번호</th><th>작성자</th><th style=\'width: 50%;\'>글 제목</th><th>작성일</th><th>조회수</th></tr></thead><tbody>");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		int totalCount = dao.getGuildBoardCount(bean);
		int countList = 10;
		int totalPage = totalCount / countList;
		if(totalCount / countList >0) {
			totalPage++;
		}
		for(int i=0 ; i< gBoardList.size();i++) {
			sb.append("<tr><td>");
			sb.append(gBoardList.get(i).getGbCode());
			sb.append("</td><td>");
			sb.append(gBoardList.get(i).getChName());
			sb.append("</td><td onClick='readGboard(\""+ gBoardList.get(i).getGbCode() +"\")'>");
			sb.append(gBoardList.get(i).getGbTitle());
			sb.append("</td><td>");
			sb.append(sdf.format((gBoardList.get(i).getGbWDate())));
			sb.append("</td><td>");
			sb.append(gBoardList.get(i).getGbHit());
			sb.append("</td></tr>");
		}

		sb.append("</tbody></table>");
		sb.append("<div class=\'pageNum\'>");
		for(int i=1 ; i<=totalPage ; i++) {
			sb.append("<button onClick=\"guildboardpage2(\'"+ i +"\')\">"+ i +"</button>");
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * 처리내용 : 0. 보드 페이지 연결
	 * 작성일 : 2017. 10. 26.
	 * @Method Name : guildBoardPage
	 * @return type : ModelAndView
	 */
	private ModelAndView guildBoardPage(BoardBean bean) {
		// 로그인 여부 확인
		try {
			if(session.getAttribute("id") != null) {
				bean.setId(session.getAttribute("id").toString());
				bean.setChName(dao.getCharaName(bean));
				System.out.println(bean.getGbStep() + "모델엔뷰");
				session.setAttribute("chName", bean.getChName());
				mav.addObject("boards", guildBoardList(bean));
				mav.setViewName("guildBoard");
				session.setAttribute("page", "guildBoard");
			}else {
				mav.setViewName("home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg","system error");
			mav.setViewName("home");
		}
		return mav;
	}

}
