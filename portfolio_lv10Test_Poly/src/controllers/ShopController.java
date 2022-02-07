package controllers;

import java.util.ArrayList;

import models.PlayerRifle;
import models.PlayerSword;
import models.Rifle;
import models.Sword;
import models.Weapon;

public class ShopController {
	
	private static ShopController instance = new ShopController();
	private ShopController() {}
	public static ShopController getInstance() {
		return instance;
	}
	
	private ArrayList<Weapon> weapons = new ArrayList<>();
	private ArrayList<Weapon> weaponsTemp = new ArrayList<>();
	
	public void makeBasicEquips() {
		this.weapons.add(new Sword("단검", 2, 0, 1500));
		this.weapons.add(new Sword("장검", 4, 0, 3000));
		this.weapons.add(new Sword("대검", 8, 1, 6000));
		this.weapons.add(new Rifle("권총", 2, 0, 1500));
		this.weapons.add(new Rifle("소총", 4, 0, 3000));
		this.weapons.add(new Rifle("대포", 8, 1, 6000));
	}
	
	public Weapon getWeapon(int idx) {
		return this.weapons.get(idx);
	}
	
	public Weapon getWeapon(String name) {
		int idx = -1;
		for(int i = 0; i<this.weapons.size(); i++) {
			if(name.equals(this.weapons.get(i).getName())) idx = i;
		}
		
		return this.weapons.get(idx);
	}
	
	public void printShop() {
		while(true) {
			System.out.println(" ======= 상 점 ======= ");
			System.out.println("현재 골드 : "+GameController.gold+"골드");
			System.out.println("1. 검  2. 총기  0. 나가기");
			int sel = returnSelect(0, 3);
			
			if(sel == 0) break;
			else if(sel >= 1 && sel <= 2) printCat(sel);
			
		}
		
	}
	
	private int returnSelect(int start, int end) {
		String input = GameController.scan.next();
		
		try {
			int sel = -1;
			
			if(start == 0) sel = Integer.parseInt(input);
			else if(start == 1) sel = Integer.parseInt(input)-1;
			
			if(sel >= 0 && sel < end) return sel;
			else{
				System.out.println("입력 값이 이상합니다.");
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println("대충 오류");
			return -1;
		}
	}
	
	private void printCat(int cat) {
		this.weaponsTemp = new ArrayList<>();
		
		for(Weapon temp : this.weapons) {
			if(temp.getCat() == cat && cat == 1) {
				this.weaponsTemp.add(new Sword(temp.getName(), temp.getAtk(), temp.getDef(),
						temp.getPrice(),temp.getHave(),temp.getEquip()));
			}
			else if(temp.getCat() == cat && cat == 2) {
				this.weaponsTemp.add(new Rifle(temp.getName(), temp.getAtk(), temp.getDef(),
						temp.getPrice(),temp.getHave(),temp.getEquip()));
			}
		}
		
		for(int i = 0; i<this.weaponsTemp.size(); i++) {
			System.out.printf("%d. %s Atk %d Def %d %d Gold %d개 보유중\n",(i+1),
					this.weaponsTemp.get(i).getName(),this.weaponsTemp.get(i).getAtk(),
					this.weaponsTemp.get(i).getDef(),this.weaponsTemp.get(i).getPrice(),
					this.weaponsTemp.get(i).getHave());
		}
		
		System.out.print("구매할 무기의 번호를 입력 : ");
		int sel = returnSelect(1, this.weaponsTemp.size());
		
		if(GameController.gold > this.weaponsTemp.get(sel).getPrice()) {
			getWeapon(this.weaponsTemp.get(sel).getName()).changeHave(1);
			GameController.gold -= this.weaponsTemp.get(sel).getPrice();
			System.out.println("구매 완료!");
		}
		else System.out.println("골드가 부족합니다!");
	}
	
	public void printInventory() {
		while(true) {
			System.out.println("1. 장비 장착  2. 장비 해제  0. 나가기");
			System.out.print("메뉴 선택 : ");
			int sel = returnSelect(0, 3);
			
			if(sel == 1) {
				this.weaponsTemp = new ArrayList<>();
				
				for(Weapon temp : this.weapons) {
					if(temp.getHave() >= 1) {
						
						if(temp.getCat() == 1) {
							this.weaponsTemp.add(new Sword(temp.getName(), temp.getAtk(), temp.getDef(),
									temp.getPrice(),temp.getHave(),temp.getEquip()));
						}
						else if(temp.getCat() == 2) {
							this.weaponsTemp.add(new Rifle(temp.getName(), temp.getAtk(), temp.getDef(),
									temp.getPrice(),temp.getHave(),temp.getEquip()));
						}
					}
				}
				
				for(int i = 0; i<this.weaponsTemp.size(); i++) {
					System.out.printf("%d. %s Atk %d Def %d %d Gold %d개 보유중 %d개 장비중\n",(i+1),
							this.weaponsTemp.get(i).getName(),this.weaponsTemp.get(i).getAtk(),
							this.weaponsTemp.get(i).getDef(),this.weaponsTemp.get(i).getPrice(),
							this.weaponsTemp.get(i).getHave(),this.weaponsTemp.get(i).getEquip());
				}
				
				int sel2 = returnSelect(1, this.weaponsTemp.size());
				
				if(this.weaponsTemp.get(sel2).getHave() <= this.weaponsTemp.get(sel2).getEquip()) 
					System.out.println("여분의 장비가 없습니다!");
				else {
					UnitController uc = UnitController.getInstance();
					
					for(int i = 0; i<uc.getPlayerSize(); i++) {
						System.out.println((i+1)+" "+uc.getPlayer(i));
					}
					
					int sel3 = returnSelect(1, 3);
					
					if(this.weaponsTemp.get(sel2).getCat() == 1 &&
							uc.getPlayer(sel3) instanceof PlayerSword) {
						if(uc.getPlayer(sel3).getEquip().equals("x")) {
							uc.getPlayer(sel3).changeStats(this.weaponsTemp.get(sel2));
							getWeapon(this.weaponsTemp.get(sel2).getName()).changeEquip(1);
							System.out.println("장착 완료.");
						}
						else {
							getWeapon(uc.getPlayer(sel3).getEquip()).changeEquip(-1);
							uc.getPlayer(sel3).removeStats();
							uc.getPlayer(sel3).changeStats(this.weaponsTemp.get(sel2));
							getWeapon(this.weaponsTemp.get(sel2).getName()).changeEquip(1);
							System.out.println("장착 완료.");
						}
					}
					
					else if(this.weaponsTemp.get(sel2).getCat() == 2 &&
							uc.getPlayer(sel3) instanceof PlayerRifle) {
						if(uc.getPlayer(sel3).getEquip().equals("x")) {
							uc.getPlayer(sel3).changeStats(this.weaponsTemp.get(sel2));
							getWeapon(this.weaponsTemp.get(sel2).getName()).changeEquip(1);
							System.out.println("장착 완료.");
						}
						else {
							getWeapon(uc.getPlayer(sel3).getEquip()).changeEquip(-1);
							uc.getPlayer(sel3).removeStats();
							uc.getPlayer(sel3).changeStats(this.weaponsTemp.get(sel2));
							getWeapon(this.weaponsTemp.get(sel2).getName()).changeEquip(1);
							System.out.println("장착 완료.");
						}
					}
					
					else System.out.println("직업과 무기 종류가 일치하지 않습니다.");
					
				}
			
			}
			else if(sel == 2) {
				UnitController uc = UnitController.getInstance();
				
				for(int i = 0; i<uc.getPlayerSize(); i++) {
					System.out.println((i+1)+" "+uc.getPlayer(i));
				}
				System.out.print("장비 해제할 플레이어를 선택 : ");
				int sel2 = returnSelect(1, 3);
				
				if(uc.getPlayer(sel2).getEquip().equals("x")) 
					System.out.println("장착한 장비가 없습니다.");
				else {
					uc.getPlayer(sel2).removeStats();
					System.out.println("장착 해제 완료.");
				}
			}
			else if(sel == 0) break;
			
			
		}
	}
}
