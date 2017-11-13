package com.activityRPG.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import com.activityRPG.beans.BoardBean;
import com.activityRPG.beans.MemberBean;
import com.activityRPG.dao.IMBatisDao;
import com.activityRPG.dao.TranEx;

import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : QuestionBoard
 * @작성자 : 
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class QuestionBoard extends TranEx {
	@Autowired
	private ProjectUtils session;
	@Autowired
	private IMBatisDao dao;
 
	public ModelAndView entrance(int serviceCode, Object... object) {
		ModelAndView mav = null;
 
		switch(serviceCode) {
		case 21: //1:1문의 리스트 출력
			mav = questionBoardList();	
			break;
		case 22: //1:1문의 게시글 작성
			mav = questionBoardMake((BoardBean)object[0]);	
			break;
		case 23: //1:1문의 게시글 내용보기
			mav = questionBoardContents((BoardBean)object[0]);	
			break;
		case 24: //1:1문의 게시글 수정 폼 이동
			mav = questionBoardModifyForm((BoardBean)object[0]);	
			break;
		case 25: //1:1문의 게시글 수정
			mav = questionBoardModify((BoardBean)object[0]);	
			break;
		case 26: //1:1문의 게시글 삭제
			mav = questionBoardDelete((BoardBean)object[0]);	
			break;
		case 27: //1:1문의 게시글 검색
			mav = questionBoardSearch((BoardBean)object[0]);	
			break;
		}
 
		return mav;
	}
 
	//1:1문의 게시글 검색
	private ModelAndView questionBoardSearch(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> listBoardBean = null;
 
		try {
			StringBuffer sb = new StringBuffer();
			if(boardBean.getQbMbid() != null) {
				boardBean.setQbMbid("%" + boardBean.getQbMbid() + "%");
				listBoardBean = dao.questionBoardSearchId(boardBean);
				int z = 0;
				for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
					sb.append("<div id=div" + i + " class=divClass>");
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
							sb.append("<td>" + boardBean.getQbCode() + "</td>");
							sb.append("<td id=\"underline" + z + "\" onClick=\"questionBoardContents(" + boardBean.getQbCode() + ", " + boardBean.getQbHit() + ")\">" + boardBean.getQbTitle() + "</td>");
							sb.append("<td>" + boardBean.getQbMbid() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getQbDate()) + "</td>");
							sb.append("<td>" + boardBean.getQbHit() + "</td>");
							sb.append("</tr>");
						}else {
							break;
						}
						z = j + 1;
					}
					sb.append("</table>");
					sb.append("</div>");
				}
				
			}else if(boardBean.getQbTitle() != null) {
				boardBean.setQbTitle("%" + boardBean.getQbTitle() + "%");
				listBoardBean = dao.questionBoardSearchTitle(boardBean);
				int z = 0;
				for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
					sb.append("<div id=div" + i + " class=divClass>");
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
							sb.append("<td>" + boardBean.getQbCode() + "</td>");
							sb.append("<td id=\"underline" + z + "\" onClick=\"questionBoardContents(" + boardBean.getQbCode() + ", " + boardBean.getQbHit() + ")\">" + boardBean.getQbTitle() + "</td>");
							sb.append("<td>" + boardBean.getQbMbid() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getQbDate()) + "</td>");
							sb.append("<td>" + boardBean.getQbHit() + "</td>");
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
			mav.addObject("questionBoardList", sb.toString());
			mav.addObject("listSize", listBoardBean.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("questionBoard");
		return mav;
	}
 
	//1:1문의 게시글 삭제
	private ModelAndView questionBoardDelete(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
 
			dao.questionBoardDelete(boardBean);
 
			transaction = true;
			setTransactionResult(transaction);
 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = questionBoardList(); //1:1문의 리스트 출력
		return mav;
	}
 
	//1:1문의 게시글 수정
	private ModelAndView questionBoardModify(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
 
			dao.questionBoardModify(boardBean);
 
			transaction = true;
			setTransactionResult(transaction);
 
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav = questionBoardList(); //1:1문의 리스트 출력
		return mav;
	}
 
	//1:1문의 게시글 수정 폼 이동
	private ModelAndView questionBoardModifyForm(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		try {
			boardBean = dao.questionBoardContents(boardBean);
 
			mav.addObject("qbCode", boardBean.getQbCode());
			mav.addObject("qbTitle", boardBean.getQbTitle());
			mav.addObject("qbContent", boardBean.getQbContent());
			mav.addObject("qbMbid", boardBean.getQbMbid());
			mav.addObject("qbDate", boardBean.getQbDate());
			mav.addObject("qbHit", boardBean.getQbHit());
 
			//mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("questionBoardModifyForm");
		return mav;
	}
 
	//1:1문의 게시글 내용보기
	private ModelAndView questionBoardContents(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
	
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
 
			boardBean.setQbHit(boardBean.getQbHit() + 1);
			dao.questionBoardHitUp(boardBean);
 
			transaction = true;
			setTransactionResult(transaction);
 
 
			boardBean = dao.questionBoardContents(boardBean);
			StringBuffer sb = new StringBuffer();
			sb.append("<table class=\"type\">");
			sb.append("<tr>");
			sb.append("<th class=\"code\">" + boardBean.getQbCode() + "</th>");
			sb.append("<th class=\"title\" colspan=\"2\">" + boardBean.getQbTitle() + "</th>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"id\">작성자 " + boardBean.getQbMbid() + "</td>");
			sb.append("<td class=\"date\">작성일 " + getStringDate(boardBean.getQbDate()) + "</td>");
			sb.append("<td class=\"hit\">조회수 " + boardBean.getQbHit() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"contents\" colspan=\"3\">" + boardBean.getQbContent() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("</tr>");
			sb.append("</table>");
			
			mav.addObject("questionBoardContentsView", sb.toString());
			mav.addObject("qbCode", boardBean.getQbCode());
		}catch(Exception e) {
			e.printStackTrace();
		}
 
		
		mav.setViewName("questionBoardContents");
 
		return mav;
	}
 
	//1:1문의 게시글 작성
	private ModelAndView questionBoardMake(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);
 
			boardBean.setQbMbid((String)session.getAttribute("id"));
			dao.questionBoardMake(boardBean);
 
			transaction = true;
			setTransactionResult(transaction);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = questionBoardList(); //1:1문의 리스트 출력
		return mav;
	}
 
	//1:1문의 리스트 출력
	private ModelAndView questionBoardList() {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> listBoardBean = null;
		BoardBean boardBean = new BoardBean();
		
		
		try {
			StringBuffer sb = new StringBuffer();
			listBoardBean = dao.questionBoardList();
			int z = 0;
			for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
				sb.append("<div id=div" + i + " class=divClass>");
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
						sb.append("<td>" + boardBean.getQbCode() + "</td>");
						sb.append("<td id=\"underline" + z + "\" onClick=\"questionBoardContents(" + boardBean.getQbCode() + ", " + boardBean.getQbHit() + ")\">" + boardBean.getQbTitle() + "</td>");
						sb.append("<td>" + boardBean.getQbMbid() + "</td>");
						sb.append("<td>" + getStringDate(boardBean.getQbDate()) + "</td>");
						sb.append("<td>" + boardBean.getQbHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			mav.addObject("questionBoardList", sb.toString());
			mav.addObject("listSize", listBoardBean.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("questionBoard");
		return mav;
	}
 
	private String getStringDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
}