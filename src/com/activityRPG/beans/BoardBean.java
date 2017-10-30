/**
 * 
 */
package com.activityRPG.beans;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @클래스명 : BoardBean
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 16.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
@Alias("boardBean")
public class BoardBean {
	
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

	public int code;
	public String title;
	public String content;
	public Date date;
	public int hit;
	public int group;
	public int step;
	public int indent;
	
	
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
