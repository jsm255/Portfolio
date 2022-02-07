package controllers;

public class Main {

	public static void main(String[] args) {
		
		// Lv10 Test
		
		// 다형성rpg
		// A to Z 까지
		// 시간기록
		
		// 시작 18:06
		// 종료 22:22
		// 소요 4시간 16분
		
		// 프로젝트명 : Lv10Test_Poly_조성민
		// 패키지 : models, controller(Main포함)
		// 멤버 : 모두 캡슐화(private)
		
		// 빈 프로젝트, 패키지 생성 후 커밋
		// 보고후 진행
		
		// controller 전투컨트롤러, 유닛컨트롤러, 상점컨트롤러
		// models 플레이어, 적, 무기
		
		// 모두 디버프 스킬을 하나씩 가짐
		// 플레이어 2 적 3
		
		// 배틀시에 나올 몬스터를 처음에 배열에 집어넣음
		// 매 배틀 전에 보여줌
		// 그걸 보고 플레이어를 바꾸거나 장비를 바꿀 수 있음
		
		
		GameController gc = GameController.getInstance();
		gc.run();

	}

}
