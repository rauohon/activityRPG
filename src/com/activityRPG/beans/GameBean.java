package com.activityRPG.beans;

import org.apache.ibatis.type.Alias;

/**
 * @클래스명 : GameBean
 * @작성일 : 2017. 10. 14.
 * @설명 : 
 */
@Alias("gameBean")
public class GameBean {	
	/*↓ 신태휘*/
	private String moveValue;
	private String chName;
	private String id;
	private String chGender;
	private int chLevel;
	private int chExp;
	private int chHp;
	private int chMp;
	private int chStr;
	private int chDex;
	private int chInt;
	private int chAttack;
	private int chDefense;
	private int chGold;
	private int chGuCode;
	private int chGlCode;
	private String itname;
	private String itcode;
	private int requiAbility;
	private int buyPrice;
	private int sellPirce;
	private int ability;
	private int amount;
	private int enlevel;
	private int eqWeapon;
	private int eqArmor;
	private int eqGlove;
	private int eqShoe;
	private int eqRing;
	private int eqNecklace;
	/*↑ 신태휘*/
	
	
	public int getRequiAbility() {
		return requiAbility;
	}

	public void setRequiAbility(int requiAbility) {
		this.requiAbility = requiAbility;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getSellPirce() {
		return sellPirce;
	}

	public void setSellPirce(int sellPirce) {
		this.sellPirce = sellPirce;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getItname() {
		return itname;
	}

	public void setItname(String itname) {
		this.itname = itname;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChGender() {
		return chGender;
	}

	public void setChGender(String chGender) {
		this.chGender = chGender;
	}

	public int getChLevel() {
		return chLevel;
	}

	public void setChLevel(int chLevel) {
		this.chLevel = chLevel;
	}

	public int getChExp() {
		return chExp;
	}

	public void setChExp(int chExp) {
		this.chExp = chExp;
	}

	public int getChHp() {
		return chHp;
	}

	public void setChHp(int chHp) {
		this.chHp = chHp;
	}

	public int getChMp() {
		return chMp;
	}

	public void setChMp(int chMp) {
		this.chMp = chMp;
	}

	public int getChStr() {
		return chStr;
	}

	public void setChStr(int chStr) {
		this.chStr = chStr;
	}

	public int getChDex() {
		return chDex;
	}

	public void setChDex(int chDex) {
		this.chDex = chDex;
	}

	public int getChInt() {
		return chInt;
	}

	public void setChInt(int chInt) {
		this.chInt = chInt;
	}

	public int getChAttack() {
		return chAttack;
	}

	public void setChAttack(int chAttack) {
		this.chAttack = chAttack;
	}

	public int getChDefense() {
		return chDefense;
	}

	public void setChDefense(int chDefense) {
		this.chDefense = chDefense;
	}

	public int getChGold() {
		return chGold;
	}

	public void setChGold(int chGold) {
		this.chGold = chGold;
	}

	public int getChGuCode() {
		return chGuCode;
	}

	public void setChGuCode(int chGuCode) {
		this.chGuCode = chGuCode;
	}

	public int getChGlCode() {
		return chGlCode;
	}

	public void setChGlCode(int chGlCode) {
		this.chGlCode = chGlCode;
	}

	public String getMoveValue() {
		return moveValue;
	}

	public void setMoveValue(String moveValue) {
		this.moveValue = moveValue;
	}

	public int getAbility() {
		return ability;
	}

	public void setAbility(int ability) {
		this.ability = ability;
	}

	public int getEnlevel() {
		return enlevel;
	}

	public void setEnlevel(int enlevel) {
		this.enlevel = enlevel;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public int getEqWeapon() {
		return eqWeapon;
	}

	public void setEqWeapon(int eqWeapon) {
		this.eqWeapon = eqWeapon;
	}

	public int getEqArmor() {
		return eqArmor;
	}

	public void setEqArmor(int eqArmor) {
		this.eqArmor = eqArmor;
	}

	public int getEqGlove() {
		return eqGlove;
	}

	public void setEqGlove(int eqGlove) {
		this.eqGlove = eqGlove;
	}

	public int getEqShoe() {
		return eqShoe;
	}

	public void setEqShoe(int eqShoe) {
		this.eqShoe = eqShoe;
	}

	public int getEqRing() {
		return eqRing;
	}

	public void setEqRing(int eqRing) {
		this.eqRing = eqRing;
	}

	public int getEqNecklace() {
		return eqNecklace;
	}

	public void setEqNecklace(int eqNecklace) {
		this.eqNecklace = eqNecklace;
	}

}
