package com.activityRPG.beans;

import org.apache.ibatis.type.Alias;

/**
 * @클래스명 : ActivityBean
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Alias("actBean")
public class ActivityBean {
	
	private String riId;			// 라즈베리파이 아이디
	private String id;				// 회원 아이디
	private int step;				// 걸음 수
	private int floor;				// 오른 층 수
	private int exp;				// 경험치
	private String date;			// 날짜
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRiId() {
		return riId;
	}
	public void setRiId(String riId) {
		this.riId = riId;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
