package controllers;

import java.util.Random;
import java.util.Scanner;

public class GameController {
	
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();
	public static int gold = 20000;
	public static int battleRound = 1;
	public static int playerNum = -1;
	public static boolean playing = true;
	
	private static GameController instance = new GameController();
	private GameController() {}
	public static GameController getInstance() {
		return instance;
	}
	
	public void run() {
		UnitController uc = UnitController.getInstance();
		BattleController bc = BattleController.getInstance();
		ShopController sc = ShopController.getInstance();
		
		uc.generateUnits();
		uc.shuffleEnemys();
		
		sc.makeBasicEquips();
		
		while(battleRound <= 3 && playing) {
			bc.standbyPhase();
		}
		
		ifEnd();
	}
	
	private void ifEnd() {
		if(battleRound == 99 && !playing) System.out.println("사용자가 게임을 종료했습니다.");
		else if(battleRound >= 4 && playing) System.out.println("클리어!");
		else if(battleRound <= 3 && !playing) System.out.println("플레이어가 패배했습니다.");
	}

}
