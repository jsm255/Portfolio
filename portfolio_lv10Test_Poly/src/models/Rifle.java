package models;

public class Rifle extends Weapon{

	public Rifle(String name, int atk, int def, int price) {
		super(2, name, atk, def, price);
		// TODO Auto-generated constructor stub
	}
	
	public Rifle(String name, int atk, int def, int price, int have, int equip) {
		super(2, name, atk, def, price, have, equip);
		// TODO Auto-generated constructor stub
	}

}
