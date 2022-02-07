package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	/*
	 * 할 일	- CSS는 나중에 하자
	 * 1. 유저 테이블	+ 더미
	 * 2. 차 테이블	+ 더미
	 * 
	 * 3. 차 기능		- 끝
	 * 	3-1. 차 리스트 보여주기(사진, 이름, 가격)	----> 렌트가 완료된 차는 보여주지 않는다
	 * 	3-2. 차 선택시 해당 차량 렌트 화면으로 리다이렉트
	 * 	3-3. 렌트 화면에서는 차량의 정보, 렌트 기간, 기간에 따른 가격 등을 볼 수 있음
	 * 	3-4. 렌트를 끝내면 유저의 계약 건수, 소비 금액이 갱신, 렌트된 차는 렌트가 완료된 것으로 표시
	 * 
	 * 	추가1. 렌트 화면 기간 설정 화면에서 확정을 했을 때 로그인이 되어있지 않으면 로그인 화면으로 이동
	 *  추가2. 렌트 설정화면에서 다른 곳으로 나왔을때 세션값때문에 차가 고정되어버리는 문제가 있음
	 * 
	 * 4. 유저 기능
	 * 	4-1. 자신의 id와 pw를 입력해서 로그인		- 끝
	 * 	4-2. 마이페이지에서 본인이 렌트 계약을 했는지, 했다면 어떤 차를 렌트해놨는지, 
	 * 
	 * Ex. 피드백
	 * 	-- sql에 넣어둔 더미 데이터는 잘 추가되었음. 다만 그 데이터를 jsp에서 가져오지 못하는 문제가 있다고한다...?
	 * 			-- sql은 문제가 없다 -> java에서 가져오는 과정에 문제가 있다(size() = 0)
	 * 			-- java가 인식을 못한다?
	 * 	-- car만 안되는게 아니라 user도 안됨(1234 더미로 로그인이 되지 않았음)
	 * 	-- 한글 인코딩이 깨졌다. (join에서 joinPro로 데이터를 넘기는 과정에서 encoding(utf-8)을 추가하였음)
	 * 
	 * 	-- board 추가(자유게시판 느낌)	-> 게시판 기능은 얼추 끝
	 * 	-- 차량에 수량을 설정하고, 표시는 하되 만약 수량이 없다면 렌트가 불가능하게 디벨롭
	 * 		-- 차량이 스톡이 없으면 재고가 없으니 예약 불가 문구가 뜨도록 설정
	 * 		-- ☆★☆★☆★☆★☆★차량을 렌트했다면 회원에게 렌트차량의 정보를 달아줄것 (<<<<< 이거 해야됨 안했음)
	 * 	-- 좋아요를 누르면 -> 좋아요를 반영(실시간 ajax json을 활용해볼것) -> 좋아요는 중복이 되지 않도록 할것.
	 * 		-- 좋아요를 누른 유저의 id를 저장해놓으면 어떨까
	 * 		-- 버튼을 눌렀을 때 새로고침을 하지 않고 좋아요 수가 갱신되도록 (js를 사용)
	 * 	-- 왜 게시판으로는 가지 못하는가 (---- 오타 좀 더 신경쓸 것)
	 * 
	 */
	
	private DBManager() {}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/rentcar?serverTimeZone=UTC";
			String id = "root";
			String pw = "root";
			con = DriverManager.getConnection(url, id, pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
}
