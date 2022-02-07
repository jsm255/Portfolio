package models;

public class PlayerRifle extends Player implements Special{
	
	// 관통 - 방어력 감소
	
	public PlayerRifle(String name, int hp, int atk, int def) {
		super(name, hp, atk, def, "꿰뚫기");
	}

	@Override
	public String specialty() {
		if(super.skillCnt > 0) return super.skill;
		else return "x";
	}
	
}
