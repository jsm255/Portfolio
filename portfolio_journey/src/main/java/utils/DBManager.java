package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	private DBManager() {}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://mydb.cnwvoqqkipqe.ap-northeast-2.rds.amazonaws.com:3306/journey?serverTimeZone=UTC";
			String user = "admin";
			String pw = "WsRFpra4fEw2kKH3";
			
			conn = DriverManager.getConnection(url, user, pw);
			
			if(conn != null) return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
