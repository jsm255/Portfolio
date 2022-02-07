package models;

import controllers.ShopController;

public class Player extends Unit {
	
	protected String equip;
	protected String skill;
	protected int skillCnt;

	public Player(String name, int hp, int atk, int def, String skill) {
		super(name, hp, atk, def);
		this.equip = "x";
		this.skill = skill;
		this.skillCnt = 5;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += super.name + "\n";
		str += " └─ Hp " + super.nowHp + "/" + super.maxHp + "\n";
		str += " └─ Atk " + super.atk + " Def " + super.def + "\n";
		if(this.equip.compareTo("x") != 0) {
			str += " └─ 무기 : " + this.equip + "\n";
		}
		str += " └─ ★ " + this.skill + " ";
		for(int i = 1; i<=5; i++) {
			if(i <= skillCnt) str += "◆ ";
			else str += "◇ ";
		}
		
		return str;
	}
	
	public String getEquip() {
		return this.equip;
	}
	
	public String getSkillName() {
		return this.skill;
	}
	
	public int getSkillCnt() {
		return this.skillCnt;
	}
	
	public void changeSkillCnt(int change) {
		this.skillCnt += change;
	}
	
	public void removeStats() {
		ShopController sc = ShopController.getInstance();
		
		super.changeAtk(-sc.getWeapon(this.equip).getAtk());
		super.changeDef(-sc.getWeapon(this.equip).getDef());
		this.equip = "x";
	}
	
	public void changeStats(Weapon weapon) {
		super.changeAtk(weapon.getAtk());
		super.changeDef(weapon.getDef());
		this.equip = weapon.getName();
	}
	
}
