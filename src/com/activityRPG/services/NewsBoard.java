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
public class NewsBoard extends TranEx {
	@Autowired
	private ProjectUtils pju;
	@Autowired
	private IMBatisDao dao;
	
	public ModelAndView entrance(int serviceCode, Object... object) {
		ModelAndView mav = null;

		switch(serviceCode) {
		case 41: //공지사항 리스트 출력
			mav = newsBoardList();	
			break;
		case 42: //공지사항 게시글 작성
			mav = newsBoardMake((BoardBean)object[0]);	
			break;
		case 43: //공지사항 게시글 내용보기
			mav = newsBoardContents((BoardBean)object[0]);	
			break;
		case 44: //공지사항 게시글 수정 폼 이동
			mav = newsBoardModifyForm((BoardBean)object[0]);	
			break;
		case 45: //공지사항 게시글 수정
			mav = newsBoardModify((BoardBean)object[0]);	
			break;
		case 46: //공지사항 게시글 삭제
			mav = newsBoardDelete((BoardBean)object[0]);	
			break;
		case 47: //공지사항 게시글 검색
			mav = newsBoardSearch((BoardBean)object[0]);	
			break;
		}

		return mav;
	}

	//공지사항 게시글 검색
	private ModelAndView newsBoardSearch(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> listBoardBean = null;

		try {
			StringBuffer sb = new StringBuffer();
			if(boardBean.getNewsBoardUserId() != null) {
				boardBean.setNewsBoardUserId("%" + boardBean.getNewsBoardUserId() + "%");
				listBoardBean = dao.newsBoardSearchId(boardBean);
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
							sb.append("<td>" + boardBean.getNewsBoardCode() + "</td>");
							sb.append("<td id=\"underline" + z + "\" onClick=\"newsBoardContents(" + boardBean.getNewsBoardCode() + ", " + boardBean.getNewsBoardHit() + ")\">" + boardBean.getNewsBoardTitle() + "</td>");
							sb.append("<td>" + boardBean.getNewsBoardUserId() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getNewsBoardDate()) + "</td>");
							sb.append("<td>" + boardBean.getNewsBoardHit() + "</td>");
							sb.append("</tr>");
						}else {
							break;
						}
						z = j + 1;
					}
					sb.append("</table>");
					sb.append("</div>");
				}
				
			}else if(boardBean.getNewsBoardTitle() != null) {
				boardBean.setNewsBoardTitle("%" + boardBean.getNewsBoardTitle() + "%");
				listBoardBean = dao.newsBoardSearchTitle(boardBean);
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
							sb.append("<td>" + boardBean.getNewsBoardCode() + "</td>");
							sb.append("<td id=\"underline" + z + "\" onClick=\"newsBoardContents(" + boardBean.getNewsBoardCode() + ", " + boardBean.getNewsBoardHit() + ")\">" + boardBean.getNewsBoardTitle() + "</td>");
							sb.append("<td>" + boardBean.getNewsBoardUserId() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getNewsBoardDate()) + "</td>");
							sb.append("<td>" + boardBean.getNewsBoardHit() + "</td>");
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
			mav.addObject("newsBoardList", sb.toString());
			mav.addObject("listSize", listBoardBean.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("newsBoard");
		return mav;
	}

	//공지사항 게시글 삭제
	private ModelAndView newsBoardDelete(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.newsBoardDelete(boardBean);

			transaction = true;
			setTransactionResult(transaction);

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = newsBoardList(); //공지사항 리스트 출력
		return mav;
	}

	//공지사항 게시글 수정
	private ModelAndView newsBoardModify(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.newsBoardModify(boardBean);

			transaction = true;
			setTransactionResult(transaction);

		}catch(Exception e) {
			e.printStackTrace();
		}
		mav = newsBoardList(); //공지사항 리스트 출력
		return mav;
	}

	//공지사항 게시글 수정 폼 이동
	private ModelAndView newsBoardModifyForm(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		try {
			boardBean = dao.newsBoardContents(boardBean);

			mav.addObject("newsBoardCode", boardBean.getNewsBoardCode());
			mav.addObject("newsBoardTitle", boardBean.getNewsBoardTitle());
			mav.addObject("newsBoardContents", boardBean.getNewsBoardContents());
			mav.addObject("newsBoardUserId", boardBean.getNewsBoardUserId());
			mav.addObject("newsBoardDate", boardBean.getNewsBoardDate());
			mav.addObject("newsBoardHit", boardBean.getNewsBoardHit());

			//mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("newsBoardModifyForm");
		return mav;
	}

	//공지사항 게시글 내용보기
	private ModelAndView newsBoardContents(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
	
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setNewsBoardHit(boardBean.getNewsBoardHit() + 1);
			dao.newsBoardHitUp(boardBean);

			transaction = true;
			setTransactionResult(transaction);


			boardBean = dao.newsBoardContents(boardBean);
			StringBuffer sb = new StringBuffer();
			sb.append("<table class=\"type\">");
			sb.append("<tr>");
			sb.append("<th class=\"code\">" + boardBean.getNewsBoardCode() + "</th>");
			sb.append("<th class=\"title\" colspan=\"2\">" + boardBean.getNewsBoardTitle() + "</th>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"id\">작성자 " + boardBean.getNewsBoardUserId() + "</td>");
			sb.append("<td class=\"date\">작성일 " + getStringDate(boardBean.getNewsBoardDate()) + "</td>");
			sb.append("<td class=\"hit\">조회수 " + boardBean.getNewsBoardHit() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"contents\" colspan=\"3\">" + boardBean.getNewsBoardContents() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("</tr>");
			sb.append("</table>");
			
			mav.addObject("newsBoardContentsView", sb.toString());
			mav.addObject("newsBoardCode", boardBean.getNewsBoardCode());
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		mav.setViewName("newsBoardContents");

		return mav;
	}

	//공지사항 게시글 작성
	private ModelAndView newsBoardMake(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setNewsBoardUserId((String)pju.getAttribute("id"));
			dao.newsBoardMake(boardBean);

			transaction = true;
			setTransactionResult(transaction);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav = newsBoardList(); //공지사항 리스트 출력
		return mav;
	}

	//공지사항 리스트 출력
	private ModelAndView newsBoardList() {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> listBoardBean = null;
		BoardBean boardBean = new BoardBean();
		
		
		try {
			StringBuffer sb = new StringBuffer();
			listBoardBean = dao.newsBoardList();
			int z = 0;
			for(int i = 0 ; i <= listBoardBean.size()/15; i++) {
				sb.append("<div id=\"div" + i + "\" class=\"divClass\">");
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
						sb.append("<td>" + boardBean.getNewsBoardCode() + "</td>");
						sb.append("<td id=\"underline" + z + "\" onClick=\"newsBoardContents(" + boardBean.getNewsBoardCode() + ", " + boardBean.getNewsBoardHit() + ")\">" + boardBean.getNewsBoardTitle() + "</td>");
						sb.append("<td>" + boardBean.getNewsBoardUserId() + "</td>");
						sb.append("<td>" + getStringDate(boardBean.getNewsBoardDate()) + "</td>");
						sb.append("<td>" + boardBean.getNewsBoardHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			mav.addObject("newsBoardList", sb.toString());
			mav.addObject("listSize", listBoardBean.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("newsBoard");
		return mav;
	}

	private String getStringDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
}
