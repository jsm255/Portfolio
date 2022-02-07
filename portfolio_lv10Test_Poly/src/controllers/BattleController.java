package controllers;

import models.Debuffable;
import models.Enemy;
import models.EnemyBandit;
import models.EnemySlime;
import models.Player;
import models.PlayerRifle;
import models.PlayerSword;

public class BattleController {
	
	private static BattleController instance = new BattleController();
	private BattleController() {}
	public static BattleController getInstance() {
		return instance;
	}
	
	private Enemy enemy;
	private Player player;
	
	public void standbyPhase() {
		UnitController uc = UnitController.getInstance();
		ShopController sc = ShopController.getInstance();
		
		this.enemy = uc.getEnemy();
		
		System.out.println("다음 상대할 적은 " + this.enemy.getName() + "이다!");
		System.out.println(uc.getEnemy());
		
		try {
			this.player = uc.getPlayer(GameController.playerNum);
			System.out.println("현재 선택된 플레이어는 " + this.player.getName() + "다.");
			System.out.println(this.player);
			
			System.out.println("1. 전투 시작  2. 플레이어 변경");
			System.out.println("3. 상점  4. 인벤토리  0. 종료");
			int sel = returnSelect(0, 5);
			
			if(sel == 1) battlePhase();
			else if(sel == 2) selectPlayer();
			else if(sel == 3) sc.printShop();
			else if(sel == 4) sc.printInventory();
			else if(sel == 0) {
				GameController.playing = false;
				GameController.battleRound = 99;
			}
			
		} catch (Exception e) {
			selectPlayer();
		}
	}
	
	private void selectPlayer() {
		UnitController uc = UnitController.getInstance();
		
		System.out.println("플레이어를 선택하세요.");
		
		for(int i = 0; i<uc.getPlayerSize(); i++) 
			System.out.println((i+1) + " " + uc.getPlayer(i));
		
		GameController.playerNum = returnSelect(1, uc.getPlayerSize());
		
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
	
	private void battlePhase() {
		while(this.enemy.getNowHp() > 0 && this.player.getNowHp() > 0) {
			System.out.println(" ======= Round " +GameController.battleRound + " ======= ");
			System.out.println(this.enemy);
			System.out.println(this.player);
			
			try {
				System.out.println("1. 공격  2. 스킬  3. 회복");
				System.out.print("행동 선택 : ");
				int sel = returnSelect(1, 4) + 1;
				
				
				if(sel == 1) {
					int n = GameController.ran.nextInt(4);
					
					int dmg = this.player.getAtk() - this.enemy.getDef();
					
					if(n == 3) {
						System.out.println("치명타!");
						dmg *= 2;
					}
					
					this.enemy.changeNowHp(-dmg);
					System.out.println(this.enemy.getName() + "에게 "+ dmg + "의 데미지!");
				}
				
				else if(sel == 2) {
					String skill = "";
					if(this.player instanceof PlayerSword) 
						skill = ((PlayerSword) this.player).specialty();
					else skill = ((PlayerRifle) this.player).specialty();
					
					if(skill.equals("x")) System.out.println("스킬을 너무 많이 사용했다!");
					else {
						this.player.changeSkillCnt(-1);
						
						int n = GameController.ran.nextInt(6);
						
						int dmg = this.player.getAtk() - this.enemy.getDef() - 2;
						
						if(n == 3) {
							System.out.println("치명타!");
							dmg *= 2;
						}
						
						this.enemy.changeNowHp(-dmg);
						System.out.println(this.enemy.getName() + "에게 "+ dmg + "의 데미지!");
						
						if(this.enemy instanceof Debuffable) {
							this.enemy.changeDebuffName(skill);
							this.enemy.changeDebuffTurn(3);
							if(skill.equals("꿰뚫기")) this.enemy.changeDef(-5);
						}
						else System.out.println("효과가 없다!");
					}
				}
				else if(sel == 3) {
					if(this.player.getNowHp() == this.player.getMaxHp()) 
						System.out.println("체력이 이미 최대치이다!");
					else {
						if(this.player.getNowHp() + 30 >= this.player.getMaxHp()) {
							this.player.changeNowHp(-this.player.getNowHp());
							this.player.changeNowHp(this.player.getMaxHp());
							
							System.out.println("체력이 모두 회복되었다!");
						}
						else {
							this.player.changeNowHp(30);
							
							System.out.println("체력이 30 회복되었다!");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("대충 오류 2");
			} finally {
				int n = GameController.ran.nextInt(5);
				
				int dmg = this.enemy.getAtk() - this.player.getDef();
				
				if(n == 3) {
					System.out.println("치명타!");
					dmg *= 2;
				}
				
				this.player.changeNowHp(-dmg);
				System.out.println(this.player.getName() + "에게 "+ dmg + "의 데미지!");
				
				if(this.enemy instanceof Debuffable) {
					if(this.enemy.getDebuffTurn() >= 1) {
						if(this.enemy.getDebuffName().equals("상처내기")) {
							this.enemy.changeNowHp(-5);
							System.out.println(this.enemy.getName() + "은 출혈 피해를 받고 있다!");
						}
						this.enemy.changeDebuffTurn(-1);
						if(this.enemy.getDebuffTurn() == 0) {
							System.out.println(this.enemy.getName() +
									"의 상태 이상 지속 시간이 끝났다!");
							if(this.enemy.getDebuffName().equals("꿰뚫기"))
								this.enemy.changeDef(5);
							this.enemy.changeDebuffName("x");
						}
					}
				}
			}
			
		}
		
		System.out.println("전투 종료!");
		
		if(this.player.getNowHp() <= 0) {
			System.out.println("플레이어가 전투 불능이 되었다!");
			GameController.playing = false;
		}
		else {
			System.out.println("플레이어의 승리!");
			GameController.battleRound ++;
		}
	}
}
