package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;
import com.activityRPG.utils.ProjectUtils;

@Service
public class AttackBoard extends TranEx {
	@Autowired
	private ProjectUtils pju;
	@Autowired
	private IMBatisDao dao;
	
	public ModelAndView entrance(int serviceCode, Object... object) {
		ModelAndView mav = null;

		switch(serviceCode) {
		case 1: //공략 리스트 출력
			mav = attackBoardList();	
			break;
		case 2: //공략 게시글 작성
			mav = attackBoardMake((BoardBean)object[0]);	
			break;
		case 3: //공략 게시글 내용보기
			mav = attackBoardContents((BoardBean)object[0]);	
			break;
		case 4: //공략 게시글 수정 폼 이동
			mav = attackBoardModifyForm((BoardBean)object[0]);	
			break;
		case 5: //공략 게시글 수정
			mav = attackBoardModify((BoardBean)object[0]);	
			break;
		case 6: //공략 게시글 삭제
			mav = attackBoardDelete((BoardBean)object[0]);	
			break;
		case 7: //공략 게시글 검색
			mav = attackBoardSearch((BoardBean)object[0]);	
			break;
		}

		return mav;
	}

	
	//공지사항 게시글 검색
		private ModelAndView attackBoardSearch(BoardBean boardBean) {
			ModelAndView mav = new ModelAndView();
			List<BoardBean> listBoardBean = null;

			try {
				StringBuffer sb = new StringBuffer();
				if(boardBean.getAttackBoardUserId() != null) {
					boardBean.setAttackBoardUserId("%" + boardBean.getAttackBoardUserId() + "%");
					listBoardBean = dao.attackBoardSearchId(boardBean);
					int z = 0;
					for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
						sb.append("<div id=div" + i + " class=divClass>");
						sb.append("<h1>공략게시판</h1>");
						sb.append("<table class=\"list\">");
						sb.append("<tr>");
						sb.append("<th>글번호</th>");
						sb.append("<th>제목</th>");
						sb.append("<th>작성자</th>");
						sb.append("<th>작성일</th>");
						sb.append("<th>조회수</th>");
						sb.append("</tr>");
						for(int j = 0 + z; j < 15*(i+1); j++) {
							if(j < listBoardBean.size()) {
								boardBean = listBoardBean.get(j);
								sb.append("<tr>");
								sb.append("<td>" + boardBean.getAttackBoardCode() + "</td>");
								sb.append("<td id=\"underline" + z + "\" onClick=\"attackBoardContents(" + boardBean.getAttackBoardCode() + ", " + boardBean.getAttackBoardHit() + ")\">" + boardBean.getAttackBoardTitle() + "</td>");
								sb.append("<td>" + boardBean.getAttackBoardUserId() + "</td>");
								sb.append("<td>" + getStringDate(boardBean.getAttackBoardDate()) + "</td>");
								sb.append("<td>" + boardBean.getAttackBoardHit() + "</td>");
								sb.append("</tr>");
							}else {
								break;
							}
							z = j + 1;
						}
						sb.append("</table>");
						sb.append("</div>");
					}
					
				}else if(boardBean.getAttackBoardTitle() != null) {
					boardBean.setAttackBoardTitle("%" + boardBean.getAttackBoardTitle() + "%");
					listBoardBean = dao.attackBoardSearchTitle(boardBean);
					int z = 0;
					for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
						sb.append("<div id=div" + i + " class=divClass>");
						sb.append("<h1>공략게시판</h1>");
						sb.append("<table class=\"list\">");
						sb.append("<tr>");
						sb.append("<th>글번호</th>");
						sb.append("<th>제목</th>");
						sb.append("<th>작성자</th>");
						sb.append("<th>작성일</th>");
						sb.append("<th>조회수</th>");
						sb.append("</tr>");
						for(int j = 0 + z; j < 15*(i+1); j++) {
							if(j < listBoardBean.size()) {
								boardBean = listBoardBean.get(j);
								sb.append("<tr>");
								sb.append("<td>" + boardBean.getAttackBoardCode() + "</td>");
								sb.append("<td id=\"underline" + z + "\" onClick=\"attackBoardContents(" + boardBean.getAttackBoardCode() + ", " + boardBean.getAttackBoardHit() + ")\">" + boardBean.getAttackBoardTitle() + "</td>");
								sb.append("<td>" + boardBean.getAttackBoardUserId() + "</td>");
								sb.append("<td>" + getStringDate(boardBean.getAttackBoardDate()) + "</td>");
								sb.append("<td>" + boardBean.getAttackBoardHit() + "</td>");
								sb.append("</tr>");
							}else {
								break;
							}
							z = j + 1;
						}
						sb.append("</table>");
						sb.append("</div>");
					}
				}
				mav.addObject("attackBoardList", sb.toString());
				mav.addObject("listSize", listBoardBean.size());
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("attackBoard");
			return mav;
		}

	//공략 게시글 삭제
	private ModelAndView attackBoardDelete(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.attackBoardDelete(boardBean);

			transaction = true;
			setTransactionResult(transaction);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = attackBoardList(); //공략 리스트 출력
		return mav;
	}

	//공략 게시글 수정
	private ModelAndView attackBoardModify(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.attackBoardModify(boardBean);

			transaction = true;
			setTransactionResult(transaction);

		}catch(Exception e) {
			e.printStackTrace();
		}
		mav = attackBoardList(); //공략 리스트 출력
		return mav;
	}

	//공략 게시글 수정 폼 이동
	private ModelAndView attackBoardModifyForm(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		try {
			boardBean = dao.attackBoardContents(boardBean);

			mav.addObject("attackBoardCode", boardBean.getAttackBoardCode());
			mav.addObject("attackBoardTitle", boardBean.getAttackBoardTitle());
			mav.addObject("attackBoardContents", boardBean.getAttackBoardContents());
			mav.addObject("attackBoardUserId", boardBean.getAttackBoardUserId());
			mav.addObject("attackBoardDate", boardBean.getAttackBoardDate());
			mav.addObject("attackBoardHit", boardBean.getAttackBoardHit());

			//mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("attackBoardModifyForm");
		return mav;
	}

	//공략 게시글 내용보기
	private ModelAndView attackBoardContents(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
	
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setAttackBoardHit(boardBean.getAttackBoardHit() + 1);
			dao.attackBoardHitUp(boardBean);

			transaction = true;
			setTransactionResult(transaction);

			boardBean = dao.attackBoardContents(boardBean);
					
			StringBuffer sb = new StringBuffer();
			sb.append("<h1>공략게시판</h1>");
			sb.append("<table class=\"type\">");
			sb.append("<tr>");
			sb.append("<th class=\"code\">" + boardBean.getAttackBoardCode() + "</th>");
			sb.append("<th class=\"title\" colspan=\"2\">" + boardBean.getAttackBoardTitle() + "</th>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"id\">작성자 " + boardBean.getAttackBoardUserId() + "</td>");
			sb.append("<td class=\"date\">작성일 " + getStringDate(boardBean.getAttackBoardDate()) + "</td>");
			sb.append("<td class=\"hit\">조회수 " + boardBean.getAttackBoardHit() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"contents\" colspan=\"3\">" + boardBean.getAttackBoardContents() + "</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			mav.addObject("attackBoardContentsView", sb.toString());
			mav.addObject("attackBoardCode", boardBean.getAttackBoardCode());
			mav.addObject("AttackBoardUserId", boardBean.getAttackBoardUserId());
		}catch(Exception e) {
			e.printStackTrace();
		}

		mav.setViewName("attackBoardContents");

		return mav;
	}

	//공략 게시글 작성
	private ModelAndView attackBoardMake(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setAttackBoardUserId((String)pju.getAttribute("id"));
			dao.attackBoardMake(boardBean);

			transaction = true;
			setTransactionResult(transaction);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = attackBoardList(); //공략 리스트 출력
		return mav;
	}

	//공략 리스트 출력
	private ModelAndView attackBoardList() {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> listBoardBean = null;
		BoardBean boardBean = new BoardBean();
		
		
		try {
			StringBuffer sb = new StringBuffer();
			listBoardBean = dao.attackBoardList();
			int z = 0;
			for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
				sb.append("<div id=div" + i + " class=divClass>");
				sb.append("<h1>공략게시판</h1>");
				sb.append("<table class=\"list\">");
				sb.append("<tr>");
				sb.append("<th>글번호</th>");
				sb.append("<th>제목</th>");
				sb.append("<th>작성자</th>");
				sb.append("<th>작성일</th>");
				sb.append("<th>조회수</th>");
				sb.append("</tr>");
				for(int j = 0 + z; j < 15*(i+1); j++) {
					if(j < listBoardBean.size()) {
						boardBean = listBoardBean.get(j);
						sb.append("<tr>");
						sb.append("<td>" + boardBean.getAttackBoardCode() + "</td>");
						sb.append("<td id=\"underline" + z + "\" onClick=\"attackBoardContents(" + boardBean.getAttackBoardCode() + ", " + boardBean.getAttackBoardHit() + ")\">" + boardBean.getAttackBoardTitle() + "</td>");
						sb.append("<td>" + boardBean.getAttackBoardUserId() + "</td>");
						sb.append("<td>" + getStringDate(boardBean.getAttackBoardDate()) + "</td>");
						sb.append("<td>" + boardBean.getAttackBoardHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			mav.addObject("attackBoardList", sb.toString());
			mav.addObject("listSize", listBoardBean.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("attackBoard");
		return mav;
	}

	private String getStringDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
}
