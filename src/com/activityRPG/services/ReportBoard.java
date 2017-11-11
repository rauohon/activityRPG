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
import com.activityRPG.utils.Encryption;
import com.activityRPG.utils.ProjectUtils;

/**
 * @클래스명 : ReportBoard
 * @작성자 : 
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Service
public class ReportBoard extends TranEx{
	@Autowired
	private Encryption enc;
	@Autowired
	private ProjectUtils pju;
	@Autowired
	private IMBatisDao dao;

	public ModelAndView entrance(int serviceCode, Object... object) {
		ModelAndView mav = null;

		switch(serviceCode) {
		case 1: //공지사항 리스트 출력
			mav = reportBoardList();	
			break;
		case 2: //공지사항 게시글 작성
			mav = reportBoardMake((BoardBean)object[0]);	
			break;
		case 3: //공지사항 게시글 내용보기
			mav = reportBoardContents((BoardBean)object[0]);	
			break;
		case 4: //공지사항 게시글 수정 폼 이동
			mav = reportBoardModifyForm((BoardBean)object[0]);	
			break;
		case 5: //공지사항 게시글 수정
			mav = reportBoardModify((BoardBean)object[0]);	
			break;
		case 6: //공지사항 게시글 삭제
			mav = reportBoardDelete((BoardBean)object[0]);	
			break;
		case 7: //공지사항 게시글 검색
			mav = reportBoardSearch((BoardBean)object[0]);	
			break;
		}

		return mav;
	}

	//공지사항 게시글 검색
	private ModelAndView reportBoardSearch(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> ListBoardBean = null;
		String reportBoardList = null;

		try {
			StringBuffer sb = new StringBuffer();
			if(boardBean.getReportBoardUserId() != null) {
				boardBean.setReportBoardUserId("%" + boardBean.getReportBoardUserId() + "%");
				ListBoardBean = dao.reportBoardSearchId(boardBean);
				int z = 0;
				for(int i = 0 ; i <= ListBoardBean.size()/15; i++) {
					sb.append("<div id=div" + i + " class=divClass>");
					sb.append("<table class=\"boardClass\">");
					sb.append("<tr>");
					sb.append("<th id=\"number\">글번호</th>");
					sb.append("<th id=\"subject\">제목</th>");
					sb.append("<th id=\"editor\">작성자</th>");
					sb.append("<th id=\"report\">작성일</th>");
					sb.append("<th id=\"hit\">조회수</th>");
					sb.append("</tr>");
					for(int j = 0 + z; j < 15*(i+1); j++) {
						if(j < ListBoardBean.size()) {
							boardBean = ListBoardBean.get(j);
							sb.append("<tr>");
							sb.append("<td>" + boardBean.getReportBoardCode() + "</td>");
							sb.append("<td onClick=\"reportBoardContents(" + boardBean.getReportBoardCode() + ", " + boardBean.getReportBoardHit() + ")\">" + boardBean.getReportBoardTitle() + "</td>");
							sb.append("<td>" + boardBean.getReportBoardUserId() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getReportBoardDate()) + "</td>");
							sb.append("<td>" + boardBean.getReportBoardHit() + "</td>");
							sb.append("</tr>");
						}else {
							break;
						}
						z = j + 1;
					}
					sb.append("</table>");
					sb.append("</div>");
				}
				
			}else if(boardBean.getReportBoardTitle() != null) {
				boardBean.setReportBoardTitle("%" + boardBean.getReportBoardTitle() + "%");
				ListBoardBean = dao.reportBoardSearchTitle(boardBean);
				int z = 0;
				for(int i = 0 ; i <= ListBoardBean.size()/15; i++) {
					sb.append("<div id=div" + i + " class=divClass>");
					sb.append("<table class=\"boardClass\">");
					sb.append("<tr>");
					sb.append("<th id=\"number\">글번호</th>");
					sb.append("<th id=\"subject\">제목</th>");
					sb.append("<th id=\"editor\">작성자</th>");
					sb.append("<th id=\"report\">작성일</th>");
					sb.append("<th id=\"hit\">조회수</th>");
					sb.append("</tr>");
					for(int j = 0 + z; j < 15*(i+1); j++) {
						if(j < ListBoardBean.size()) {
							boardBean = ListBoardBean.get(j);
							sb.append("<tr>");
							sb.append("<td>" + boardBean.getReportBoardCode() + "</td>");
							sb.append("<td onClick=\"reportBoardContents(" + boardBean.getReportBoardCode() + ", " + boardBean.getReportBoardHit() + ")\">" + boardBean.getReportBoardTitle() + "</td>");
							sb.append("<td>" + boardBean.getReportBoardUserId() + "</td>");
							sb.append("<td>" + getStringDate(boardBean.getReportBoardDate()) + "</td>");
							sb.append("<td>" + boardBean.getReportBoardHit() + "</td>");
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
			reportBoardList = sb.toString();
			mav.addObject("listSize", ListBoardBean.size()/15);
			mav.addObject("reportBoardList", reportBoardList);
			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {

		}
		mav.setViewName("reportBoard");
		return mav;
	}

	//공지사항 게시글 삭제
	private ModelAndView reportBoardDelete(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> ListBoardBean = null;
		String reportBoardList = null;
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.reportBoardDelete(boardBean);

			transaction = true;
			setTransactionResult(transaction);

			StringBuffer sb = new StringBuffer();
			ListBoardBean = dao.reportBoardList();
			int z = 0;
			for(int i = 0 ; i <= ListBoardBean.size()/15; i++) {
				sb.append("<div id=div" + i + " class=divClass>");
				sb.append("<table class=\"boardClass\">");
				sb.append("<tr>");
				sb.append("<th id=\"number\">글번호</th>");
				sb.append("<th id=\"subject\">제목</th>");
				sb.append("<th id=\"editor\">작성자</th>");
				sb.append("<th id=\"report\">작성일</th>");
				sb.append("<th id=\"hit\">조회수</th>");
				sb.append("</tr>");
				for(int j = 0 + z; j < 15*(i+1); j++) {
					if(j < ListBoardBean.size()) {
						boardBean = ListBoardBean.get(j);
						sb.append("<tr>");
						sb.append("<td>" + boardBean.getReportBoardCode() + "</td>");
						sb.append("<td onClick=\"reportBoardContents(" + boardBean.getReportBoardCode() + ", " + boardBean.getReportBoardHit() + ")\">" + boardBean.getReportBoardTitle() + "</td>");
						sb.append("<td>" + boardBean.getReportBoardUserId() + "</td>");
						sb.append("<td>" + getStringDate(boardBean.getReportBoardDate()) + "</td>");
						sb.append("<td>" + boardBean.getReportBoardHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			reportBoardList = sb.toString();
			mav.addObject("listSize", ListBoardBean.size()/15);
			mav.addObject("reportBoardList", reportBoardList);
			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("reportBoard");
		return mav;
	}

	//공지사항 게시글 수정
	private ModelAndView reportBoardModify(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			dao.reportBoardModify(boardBean);

			transaction = true;
			setTransactionResult(transaction);

			mav = reportBoardList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}

	//공지사항 게시글 수정 폼 이동
	private ModelAndView reportBoardModifyForm(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		try {
			boardBean = dao.reportBoardContents(boardBean);


			mav.addObject("reportBoardCode", boardBean.getReportBoardCode());
			mav.addObject("reportBoardTitle", boardBean.getReportBoardTitle());
			mav.addObject("reportBoardContents", boardBean.getReportBoardContents());
			mav.addObject("reportBoardUserId", boardBean.getReportBoardUserId());
			mav.addObject("reportBoardDate", boardBean.getReportBoardDate());
			mav.addObject("reportBoardHit", boardBean.getReportBoardHit());

			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("reportBoardModifyForm");
		return mav;
	}

	//공지사항 게시글 내용보기
	private ModelAndView reportBoardContents(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		String reportBoardContentsView = null;
		boolean transaction = false;
		try {
			
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setReportBoardHit(boardBean.getReportBoardHit() + 1);
			dao.reportBoardHitUp(boardBean);

			transaction = true;
			setTransactionResult(transaction);
			
			boardBean = dao.reportBoardContents(boardBean);
			
			StringBuffer sb = new StringBuffer();
			sb.append("<table class=\"type\">");
			sb.append("<tr>");
			sb.append("<th class=\"code\">" + boardBean.getReportBoardCode() + "</th>");
			sb.append("<th class=\"title\" colspan=\"2\">" + boardBean.getReportBoardTitle() + "</th>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"id\">작성자 " + boardBean.getReportBoardUserId() + "</td>");
			sb.append("<td class=\"date\">작성일 " + getStringDate(boardBean.getReportBoardDate()) + "</td>");
			sb.append("<td class=\"hit\">조회수 " + boardBean.getReportBoardHit() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"contents\" colspan=\"3\">" + boardBean.getReportBoardContents() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("</tr>");
			sb.append("</table>");
			reportBoardContentsView = sb.toString();
			
			mav.addObject("ReportBoardUserId", boardBean.getReportBoardUserId());
			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}

		mav.addObject("reportBoardCode", boardBean.getReportBoardCode());
		mav.addObject("reportBoardContentsView", reportBoardContentsView);
		mav.addObject("reportBoardUserId", boardBean.getReportBoardUserId());
		mav.setViewName("reportBoardContents");

		return mav;
	}

	//공지사항 게시글 작성
	private ModelAndView reportBoardMake(BoardBean boardBean) {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> ListBoardBean = null;
		String reportBoardList = null;
		boolean transaction = false;
		
		try {
			setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_READ_COMMITTED, false);

			boardBean.setReportBoardUserId((String)pju.getAttribute("id"));
			dao.reportBoardMake(boardBean);
			
			transaction = true;
			setTransactionResult(transaction);


			StringBuffer sb = new StringBuffer();
			ListBoardBean = dao.reportBoardList();
			int z = 0;
			for(int i = 0 ; i <= ListBoardBean.size()/15; i++) {
				sb.append("<div id=div" + i + " class=divClass>");
				sb.append("<table class=\"boardClass\">");
				sb.append("<tr>");
				sb.append("<th>글번호</th>");
				sb.append("<th>제목</th>");
				sb.append("<th>작성자</th>");
				sb.append("<th>작성일</th>");
				sb.append("<th>조회수</th>");
				sb.append("</tr>");
				for(int j = 0 + z; j < 15*(i+1); j++) {
					if(j < ListBoardBean.size()) {
						boardBean = ListBoardBean.get(j);
						sb.append("<tr>");
						sb.append("<td>" + boardBean.getReportBoardCode() + "</td>");
						sb.append("<td onClick=\"reportBoardContents(" + boardBean.getReportBoardCode() + ", " + boardBean.getReportBoardHit() + ")\">" + boardBean.getReportBoardTitle() + "</td>");
						sb.append("<td>" + boardBean.getReportBoardUserId() + "</td>");
						sb.append("<td>" + getStringDate(boardBean.getReportBoardDate()) + "</td>");
						sb.append("<td>" + boardBean.getReportBoardHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			reportBoardList = sb.toString();
			mav.addObject("listSize", ListBoardBean.size()/15);
			mav.addObject("reportBoardList", reportBoardList);
			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav = reportBoardList();
		mav.setViewName("reportBoard");
		return mav;
	}

	//공지사항 리스트 출력
	private ModelAndView reportBoardList() {
		ModelAndView mav = new ModelAndView();
		List<BoardBean> ListBoardBean = null;
		BoardBean boardBean = new BoardBean();
		String reportBoardList = null;
		
		try {
			StringBuffer sb = new StringBuffer();
			ListBoardBean = dao.reportBoardList();
			int z = 0;
			for(int i = 0 ; i <= ListBoardBean.size()/15; i++) {
				sb.append("<div id=div" + i + " class=divClass>");
				sb.append("<table class=\"boardClass\">");
				sb.append("<tr>");
				sb.append("<th id=\"number\">글번호</th>");
				sb.append("<th id=\"subject\">제목</th>");
				sb.append("<th id=\"editor\">작성자</th>");
				sb.append("<th id=\"report\">작성일</th>");
				sb.append("<th id=\"hit\">조회수</th>");
				sb.append("</tr>");
				for(int j = 0 + z; j < 15*(i+1); j++) {
					if(j < ListBoardBean.size()) {
						boardBean = ListBoardBean.get(j);
						sb.append("<tr>");
						sb.append("<td id=\"number1\">" + boardBean.getReportBoardCode() + "</td>");
						sb.append("<td id=\"subject1\" onClick=\"reportBoardContents(" + boardBean.getReportBoardCode() + ", " + boardBean.getReportBoardHit() + ")\">" + boardBean.getReportBoardTitle() + "</td>");
						sb.append("<td id=\"editor1\">" + boardBean.getReportBoardUserId() + "</td>");
						sb.append("<td id=\"report1\">" + getStringDate(boardBean.getReportBoardDate()) + "</td>");
						sb.append("<td id=\"hit1\">" + boardBean.getReportBoardHit() + "</td>");
						sb.append("</tr>");
					}else {
						break;
					}
					z = j + 1;
				}
				sb.append("</table>");
				sb.append("</div>");
			}
			reportBoardList = sb.toString();
			mav.addObject("listSize", ListBoardBean.size()/15);
			mav.addObject("reportBoardList", reportBoardList);
			mav.addObject("userType", pju.getAttribute("userType"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("reportBoard");
		return mav;
	}

	private String getStringDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
}
