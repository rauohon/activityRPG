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
	//     String id		//자유 게시글 작성자
	public int code;		//자유 게시글 번호
	public String title;	//자유 게시글 제목
	public String content;	//자유 게시글 내용
	public Date date;		//자유게시판, 댓글 날짜
	public String comment;	//자유 게시판 댓글 내용
	public String type;		//자유 게시판 검색
	public int frcode;		//자유 게시판 댓글 번호
	public String text;		//자유 게시판 검색 내용
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

	//종
	private int attackBoardCode;			//공략 게시판 글 번호
	private String attackBoardUserId;		//공략 게시판 작성자 아이디
	private String attackBoardTitle;		//공략 게시판 제목
	private String attackBoardContents;	//공략 게시판 내용
	private Date attackBoardDate;			//공략 게시판 작성 날짜
	private int attackBoardHit;			//공략 게시판 조회 수
	private int attackBoardGroup;			//사용안함
	private int attackBoardStep;			//사용안함
	private int attackBoardIndent;		//사용안함
	//종

	public int getNewsBoardCode() {
		return newsBoardCode;
	}
	public int getAttackBoardCode() {
		return attackBoardCode;
	}
	public void setAttackBoardCode(int attackBoardCode) {
		this.attackBoardCode = attackBoardCode;
	}
	public String getAttackBoardUserId() {
		return attackBoardUserId;
	}
	public void setAttackBoardUserId(String attackBoardUserId) {
		this.attackBoardUserId = attackBoardUserId;
	}
	public String getAttackBoardTitle() {
		return attackBoardTitle;
	}
	public void setAttackBoardTitle(String attackBoardTitle) {
		this.attackBoardTitle = attackBoardTitle;
	}
	public String getAttackBoardContents() {
		return attackBoardContents;
	}
	public void setAttackBoardContents(String attackBoardContents) {
		this.attackBoardContents = attackBoardContents;
	}
	public Date getAttackBoardDate() {
		return attackBoardDate;
	}
	public void setAttackBoardDate(Date attackBoardDate) {
		this.attackBoardDate = attackBoardDate;
	}
	public int getAttackBoardHit() {
		return attackBoardHit;
	}
	public void setAttackBoardHit(int attackBoardHit) {
		this.attackBoardHit = attackBoardHit;
	}
	public int getAttackBoardGroup() {
		return attackBoardGroup;
	}
	public void setAttackBoardGroup(int attackBoardGroup) {
		this.attackBoardGroup = attackBoardGroup;
	}
	public int getAttackBoardStep() {
		return attackBoardStep;
	}
	public void setAttackBoardStep(int attackBoardStep) {
		this.attackBoardStep = attackBoardStep;
	}
	public int getAttackBoardIndent() {
		return attackBoardIndent;
	}
	public void setAttackBoardIndent(int attackBoardIndent) {
		this.attackBoardIndent = attackBoardIndent;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFrcode() {
		return frcode;
	}
	public void setFrcode(int frcode) {
		this.frcode = frcode;
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
