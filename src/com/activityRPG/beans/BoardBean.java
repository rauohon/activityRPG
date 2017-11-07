package com.activityRPG.beans;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

/**
 * @클래스명 : BoardBean
 * @작성일 : 2017. 10. 16.
 * @설명 : 
 */
@Alias("boardBean")
public class BoardBean {
	/*↓ 전지원*/
	public int code;
	public String title;
	public String content;
	public Date date;
	public String comment;
	/*↑ 전지원*/
	
	/*↓ 신태휘*/
	private int gbCode;		//길드 게시판 글번호
	private String id;				//길드 게시판 작성자 아이디
	private String chName;	//길드 게시판 작성자 이름
	private String gbTitle;	//길드 게시판 글제목
	private String gbContent;	//길드 게시판 글내용
	private Date gbWDate;		//길드 게시판 글 작성일
	private Date gbMDate;		//길드 게시판 글 수정일
	private int gbHit;				//길드 게시판 조회수
	private int gbGroup;		//길드 게시판 글 그룹
	private int gbStep;			//길드 게시판 step
	private int gbIndent;		//길드 게시판 들여쓰기
	private String gbReplyContent ; // 길드 게시판 댓글 달기
	private int grCode; // 길드 게시판 댓글 번호
	private MultipartFile gBoardFile;		// 길드 게시판 파일
	/*↑ 신태휘*/
	
	//******************김훈****************************
	private int newsBoardCode;			//공지사항 게시판 글 번호
	private String newsBoardUserId;		//공지사항 게시판 작성자 아이디
	private String newsBoardTitle;		//공지사항 게시판 제목
	private String newsBoardContents;	//공지사항 게시판 내용
	private Date newsBoardDate;			//공지사항 게시판 작성 날짜
	private int newsBoardHit;			//공지사항 게시판 조회 수
	private int newsBoardGroup;			//사용안함
	private int newsBoardStep;			//사용안함
	private int newsBoardIndent;		//사용안함
	//******************김훈****************************
	
	public int getNewsBoardCode() {
		return newsBoardCode;
	}
	public void setNewsBoardCode(int newsBoardCode) {
		this.newsBoardCode = newsBoardCode;
	}
	public String getNewsBoardUserId() {
		return newsBoardUserId;
	}
	public void setNewsBoardUserId(String newsBoardUserId) {
		this.newsBoardUserId = newsBoardUserId;
	}
	public String getNewsBoardTitle() {
		return newsBoardTitle;
	}
	public void setNewsBoardTitle(String newsBoardTitle) {
		this.newsBoardTitle = newsBoardTitle;
	}
	public String getNewsBoardContents() {
		return newsBoardContents;
	}
	public void setNewsBoardContents(String newsBoardContents) {
		this.newsBoardContents = newsBoardContents;
	}
	public Date getNewsBoardDate() {
		return newsBoardDate;
	}
	public void setNewsBoardDate(Date newsBoardDate) {
		this.newsBoardDate = newsBoardDate;
	}
	public int getNewsBoardHit() {
		return newsBoardHit;
	}
	public void setNewsBoardHit(int newsBoardHit) {
		this.newsBoardHit = newsBoardHit;
	}
	public int getNewsBoardGroup() {
		return newsBoardGroup;
	}
	public void setNewsBoardGroup(int newsBoardGroup) {
		this.newsBoardGroup = newsBoardGroup;
	}
	public int getNewsBoardStep() {
		return newsBoardStep;
	}
	public void setNewsBoardStep(int newsBoardStep) {
		this.newsBoardStep = newsBoardStep;
	}
	public int getNewsBoardIndent() {
		return newsBoardIndent;
	}
	public void setNewsBoardIndent(int newsBoardIndent) {
		this.newsBoardIndent = newsBoardIndent;
	}
	public int getGbCode() {
		return gbCode;
	}
	public void setGbCode(int gbCode) {
		this.gbCode = gbCode;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getGbTitle() {
		return gbTitle;
	}
	public void setGbTitle(String gbTitle) {
		this.gbTitle = gbTitle;
	}
	public String getGbContent() {
		return gbContent;
	}
	public void setGbContent(String gbContent) {
		this.gbContent = gbContent;
	}
	public Date getGbWDate() {
		return gbWDate;
	}
	public void setGbWDate(Date gbWDate) {
		this.gbWDate = gbWDate;
	}
	public Date getGbMDate() {
		return gbMDate;
	}
	public void setGbMDate(Date gbMDate) {
		this.gbMDate = gbMDate;
	}
	public int getGbHit() {
		return gbHit;
	}
	public void setGbHit(int gbHit) {
		this.gbHit = gbHit;
	}
	public int getGbGroup() {
		return gbGroup;
	}
	public void setGbGroup(int gbGroup) {
		this.gbGroup = gbGroup;
	}
	public int getGbStep() {
		return gbStep;
	}
	public void setGbStep(int gbStep) {
		this.gbStep = gbStep;
	}
	public int getGbIndent() {
		return gbIndent;
	}
	public void setGbIndent(int gbIndent) {
		this.gbIndent = gbIndent;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGbReplyContent() {
		return gbReplyContent;
	}
	public void setGbReplyContent(String gbReplyContent) {
		this.gbReplyContent = gbReplyContent;
	}
	public int getGrCode() {
		return grCode;
	}
	public void setGrCode(int grCode) {
		this.grCode = grCode;
	}
	public MultipartFile getgBoardFile() {
		return gBoardFile;
	}
	public void setgBoardFile(MultipartFile gBoardFile) {
		this.gBoardFile = gBoardFile;
	}
}
