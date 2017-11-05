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

	
}
