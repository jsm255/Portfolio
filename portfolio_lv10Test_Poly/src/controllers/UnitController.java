package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Enemy;
import models.EnemyBandit;
import models.EnemyGolem;
import models.EnemySlime;
import models.Player;
import models.PlayerRifle;
import models.PlayerSword;

public class UnitController {
	
	private static UnitController instance = new UnitController();
	private UnitController() {}
	public static UnitController getInstance() {
		return instance;
	}
	
	private ArrayList<Player> players = new ArrayList<>();
	private Map<String, Enemy> enemys = new HashMap<>();
	private String[] encounter = {"도적", "슬라임", "골렘"};
	
	public void generateUnits() {
		this.players.add(new PlayerSword("검사", 200, 20, 0));
		this.players.add(new PlayerRifle("사수", 200, 24, 0));	
		
		this.enemys.put("슬라임", new EnemySlime("슬라임", 180, 10, 1));
		this.enemys.put("도적", new EnemyBandit("도적", 180, 15, 3));
		this.enemys.put("골렘", new EnemyGolem("골렘", 180, 20, 7));
	}
	
	public void shuffleEnemys() {
		for(int i = 0; i<100; i++) {
			int rn = GameController.ran.nextInt(this.encounter.length);
			
			String temp = this.encounter[0];
			this.encounter[0] = this.encounter[rn];
			this.encounter[rn] = temp;
		}
	}
	
	public Enemy getEnemy() {
		return this.enemys.get(this.encounter[GameController.battleRound-1]);
	}
	
	public Player getPlayer(int idx) {
		return this.players.get(idx);
	}
	
	public int getPlayerSize() {
		return this.players.size();
	}
}
