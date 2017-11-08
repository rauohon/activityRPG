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
	
	//*****************************김훈********************************
	private String characterName; 	//캐릭터 이름
	private String userId;			//유저 아이디
	private int sex;				//유저 성별
	private String characterImage;	//캐릭터 이미지
	private String imagePath;		//캐릭터 이미지 경로
	private int level;				//캐릭터 레벨
	private int exp;				//캐릭터 경험치
	private int totalHp;			//캐릭터 전체 체력
	private int hp;					//캐릭터 체력
	private int totalMp;			//캐릭터 전체 마력
	private int mp;					//캐릭터 마력
	private int str;				//캐릭터 힘
	private int dex;				//캐릭터 민첩
	private int intelligent;		//캐릭터 지능
	private int attack;				//캐릭터 공격력
	private int defense;			//캐릭터 방어력
	private int gold;				//캐릭터 금화
	private String skillCode;		//스킬 코드
	private String skillName;		//스킬 이름
	private int skillDamage;		//스킬 공격력
	private int skillConsume;		//스킬 소비량
	private int guildCode;			//길드 코드
	private String guildName;		//길드 이름
	private int guildTotalNum;		//길드 가입 총 인원
	private String guildLevelCode;	//길드 레벨 코드
	private String guildLevelName;	//길드 레벨 이름
	private int itemCode;			//아이템 코드
	private String itemName;		//아이템 이름
	private int itemAmount;			//아이템 수량
	private int requireAbility;		//요구 능력치
	private int upAbility;			//증가 능력치
	private int itemBuy;			//아이템 사는 가격
	private int itemSell;			//아이템 파는 가격
	private String itemGradeCode;	//아이템 등급 코드
	private String itemGradeName;	//아이템 등급 이름
	private int enhanceLevel;		//강화 레벨
	private int material;			//강화 재료 개수
	private double enHanceChance;	//강화 확률
	private double increase;		//강화 증가률
	private String storeCode;		//상점 코드
	private String storeName;		//상점 이름
	private int questCode;			//퀘스트 코드
	//*****************************김훈********************************
	
	
	
	
	
	public int getRequiAbility() {
		return requiAbility;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCharacterImage() {
		return characterImage;
	}

	public void setCharacterImage(String characterImage) {
		this.characterImage = characterImage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getTotalHp() {
		return totalHp;
	}

	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getTotalMp() {
		return totalMp;
	}

	public void setTotalMp(int totalMp) {
		this.totalMp = totalMp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getIntelligent() {
		return intelligent;
	}

	public void setIntelligent(int intelligent) {
		this.intelligent = intelligent;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getSkillDamage() {
		return skillDamage;
	}

	public void setSkillDamage(int skillDamage) {
		this.skillDamage = skillDamage;
	}

	public int getSkillConsume() {
		return skillConsume;
	}

	public void setSkillConsume(int skillConsume) {
		this.skillConsume = skillConsume;
	}

	public int getGuildCode() {
		return guildCode;
	}

	public void setGuildCode(int guildCode) {
		this.guildCode = guildCode;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public int getGuildTotalNum() {
		return guildTotalNum;
	}

	public void setGuildTotalNum(int guildTotalNum) {
		this.guildTotalNum = guildTotalNum;
	}

	public String getGuildLevelCode() {
		return guildLevelCode;
	}

	public void setGuildLevelCode(String guildLevelCode) {
		this.guildLevelCode = guildLevelCode;
	}

	public String getGuildLevelName() {
		return guildLevelName;
	}

	public void setGuildLevelName(String guildLevelName) {
		this.guildLevelName = guildLevelName;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}

	public int getRequireAbility() {
		return requireAbility;
	}

	public void setRequireAbility(int requireAbility) {
		this.requireAbility = requireAbility;
	}

	public int getUpAbility() {
		return upAbility;
	}

	public void setUpAbility(int upAbility) {
		this.upAbility = upAbility;
	}

	public int getItemBuy() {
		return itemBuy;
	}

	public void setItemBuy(int itemBuy) {
		this.itemBuy = itemBuy;
	}

	public int getItemSell() {
		return itemSell;
	}

	public void setItemSell(int itemSell) {
		this.itemSell = itemSell;
	}

	public String getItemGradeCode() {
		return itemGradeCode;
	}

	public void setItemGradeCode(String itemGradeCode) {
		this.itemGradeCode = itemGradeCode;
	}

	public String getItemGradeName() {
		return itemGradeName;
	}

	public void setItemGradeName(String itemGradeName) {
		this.itemGradeName = itemGradeName;
	}

	public int getEnhanceLevel() {
		return enhanceLevel;
	}

	public void setEnhanceLevel(int enhanceLevel) {
		this.enhanceLevel = enhanceLevel;
	}

	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public double getEnHanceChance() {
		return enHanceChance;
	}

	public void setEnHanceChance(double enHanceChance) {
		this.enHanceChance = enHanceChance;
	}

	public double getIncrease() {
		return increase;
	}

	public void setIncrease(double increase) {
		this.increase = increase;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getQuestCode() {
		return questCode;
	}

	public void setQuestCode(int questCode) {
		this.questCode = questCode;
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
