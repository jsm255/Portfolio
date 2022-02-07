package models;

public class Enemy extends Unit {
	
	protected String debuffName;
	protected int debuffTurn;

	public Enemy(String name, int hp, int atk, int def) {
		super(name, hp, atk, def);
		this.debuffName = "x";
		this.debuffTurn = 0;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		str += "\t\t\t\t" + super.name + "\n";
		str += "\t\t\t\t └─ Hp " + super.nowHp + "/" + super.maxHp + "\n";
		str += "\t\t\t\t └─ Atk " + super.atk + " Def " + super.def + "\n";
		if(this.debuffTurn != 0) {
			str += "\t\t\t\t └─ ☆ " + this.debuffName + " " + this.debuffTurn + "턴 남음";
		}
		return str;
	}
	
	public void changeDebuffName(String name) {
		this.debuffName = name;
	}
	
	public String getDebuffName() {
		return this.debuffName;
	}
	
	public void changeDebuffTurn(int change) {
		this.debuffTurn += change;
	}
	
	public int getDebuffTurn() {
		return this.debuffTurn;
	}
}
