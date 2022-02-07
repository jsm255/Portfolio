package controller;

public class Main {

	public static void main(String[] args) {
		
		// 리셋 버튼하나, 제목 레이블
		// 맵
		// 플레이어, 박스, 목표지점, 벽, 빈공간
		
		// 0 0 1 1 1 1 1 0 = 8칸
		// 1 1 1 0 0 0 1 0
		// 1 3 7 5 0 0 1 0
		// 1 1 1 0 5 3 1 0
		// 1 3 1 1 5 0 1 0
		// 1 0 1 0 3 0 1 1
		// 1 5 0 8 5 5 3 1
		// 1 0 0 0 3 0 0 1
		// 1 1 1 1 1 1 1 1
		
		// 플레이어가 지나간 자리 업데이트 시켜줘야함
		new GameFrame();
		
	}

}
