/**
 * 
 */
package com.activityRPG.beans;

import java.util.Date;

/**
 * @클래스명 : BoardBean
 * @작성자 : 신태휘
 * @작성일 : 2017. 10. 16.
 * @설명 : 
 * @수정이력 - 수정일, 수정자, 수정내용
 */
public class BoardBean {
	public int code;
	public String title;
	public String content;
	public Date date;
	public int hit;
	public int group;
	public int step;
	public int indent;
	public String id;
	
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
