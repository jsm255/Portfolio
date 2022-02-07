package models;

public class PlayerSword extends Player implements Special{
	
	// 출혈 - 매 턴 추가 고정 데미지
	
	public PlayerSword(String name, int hp, int atk, int def) {
		super(name, hp, atk, def, "상처내기");
	}
	
	@Override
	public String specialty() {
		if(super.skillCnt > 0) return super.skill;
		else return "x";
	}
	
}
