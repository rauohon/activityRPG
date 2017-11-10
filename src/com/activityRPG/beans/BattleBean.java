package com.activityRPG.beans;

/**
 * @클래스명 : BattleBean
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
public class BattleBean {
	//*****************김훈************************
	private int monsterCode;		//몬스터 코드
	private String monsterName;		//몬스터 이름
	private int monsterTotalHp;		//몬스터 전체 체력
	private int monsterHp;			//몬스터 체력
	private int monsterDex;			//몬스터 민첩
	private int monsterAttack;		//몬스터 공격력
	private int monsterDefense;		//몬스터 방어력
	private int getExp;				//획득 경험치
	private int getGold;			//획득 골드
	private int monsterImage;		//몬스터 이미지
	private String monsterSkill;	//몬스터 스킬
	private int monsterDamage;		//몬스터 스킬 데미지
	private int fieldCode;			//필드 코드
	private String fieldName;		//필드 이름
	//*****************김훈************************
	
	
	
	public int getMonsterTotalHp() {
		return monsterTotalHp;
	}
	public void setMonsterTotalHp(int monsterTotalHp) {
		this.monsterTotalHp = monsterTotalHp;
	}
	public int getMonsterCode() {
		return monsterCode;
	}
	public void setMonsterCode(int monsterCode) {
		this.monsterCode = monsterCode;
	}
	public String getMonsterName() {
		return monsterName;
	}
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	public int getMonsterHp() {
		return monsterHp;
	}
	public void setMonsterHp(int monsterHp) {
		this.monsterHp = monsterHp;
	}
	public int getMonsterDex() {
		return monsterDex;
	}
	public void setMonsterDex(int monsterDex) {
		this.monsterDex = monsterDex;
	}
	public int getMonsterAttack() {
		return monsterAttack;
	}
	public void setMonsterAttack(int monsterAttack) {
		this.monsterAttack = monsterAttack;
	}
	public int getMonsterDefense() {
		return monsterDefense;
	}
	public void setMonsterDefense(int monsterDefense) {
		this.monsterDefense = monsterDefense;
	}
	public int getGetExp() {
		return getExp;
	}
	public void setGetExp(int getExp) {
		this.getExp = getExp;
	}
	public int getGetGold() {
		return getGold;
	}
	public void setGetGold(int getGold) {
		this.getGold = getGold;
	}
	public int getMonsterImage() {
		return monsterImage;
	}
	public void setMonsterImage(int monsterImage) {
		this.monsterImage = monsterImage;
	}
	public String getMonsterSkill() {
		return monsterSkill;
	}
	public void setMonsterSkill(String monsterSkill) {
		this.monsterSkill = monsterSkill;
	}
	public int getMonsterDamage() {
		return monsterDamage;
	}
	public void setMonsterDamage(int monsterDamage) {
		this.monsterDamage = monsterDamage;
	}
	public int getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(int fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
