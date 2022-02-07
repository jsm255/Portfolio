package models;

public class Unit {
	protected String name;
	protected int maxHp;
	protected int nowHp;
	protected int atk;
	protected int def;
	
	public Unit(String name, int hp, int atk, int def) {
		this.name = name;
		this.maxHp = hp;
		this.nowHp = hp;
		this.atk = atk;
		this.def = def;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getNowHp() {
		return this.nowHp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public void changeNowHp(int change) {
		this.nowHp += change;
	}
	
	public void changeAtk(int change) {
		this.atk += change;
	}
	
	public void changeDef(int change) {
		this.def += change;
	}
}
