package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import models.UserDTO;
import utils.DBManager;


public class UserDAO {
	
//	// 싱글톤 패턴으로 만들기
	private UserDAO() {};
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
//	// 데이터 연동준비
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private ArrayList<UserDTO> users = null;
	
	// 연동된 DB에서 데이터 불러오기
	public ArrayList<UserDTO> getUsers(){
		users = new ArrayList<>();
		
		try {
			 conn = DBManager.getConnection();
			 String sql = "select * from users";
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			
			 while(rs.next()) {
				 
				 int code = rs.getInt(1);
				 String id = rs.getString(2);
				 String pw = rs.getString(3);
				 String name = rs.getString(4);
				 String tel = rs.getString(5);
				 
				 UserDTO user = new UserDTO(code,id,pw,name,tel);
				 users.add(user);
			 }
			 
			 System.out.println("complete");
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return users;
	}
	
	public UserDTO insertUser(UserDTO dto) {
		String sql = "insert into users(id,pw,userName,tel) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getTel());
			pstmt.executeUpdate();
			
			System.out.println("입력완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public UserDTO searchUser (String id) {
		users = getUsers();
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public boolean UsercheckLogin(String id, String pw) {
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId()) && pw.equals(users.get(i).getPw())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String setUserPw(String id, String pw, String setPw) {
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id) && users.get(i).getPw().equals(pw)) {
				users.get(i).setPw(setPw);
				return users.get(i).getPw();
			}
		}
		return null;
	}
	
	public String setUserName(String id, String pw, String setUserName) {
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id) && users.get(i).getPw().equals(pw)) {
				users.get(i).setUserName(setUserName);
				return users.get(i).getUserName();
			}
		}
		return null;
	}
	
	// addUser
//	public void addUser(UserDTO user) {
//		if(checkDup(user.getJoinId())) {
//			try {
//				conn = DBManager.getConnection();
//				
//				String sql = "insert into users(id,pw,username,tel) values(?,?,?,?)";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1,user.getJoinId());
//				pstmt.setString(2, user.getJoinPw());
//				pstmt.setString(3, user.getUserName());
//				pstmt.setString(4, user.getTel());
//				
//				pstmt.executeUpdate();
//				
//				System.out.println("회원가입 성공");
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	// 중복아이디 check
	
	public boolean checkDup(String id) {
		ArrayList<UserDTO> users = getUsers();
		
		for(UserDTO u : users) {
			if(id.equals(u.getId())) {
				return false;
			}
		}
		return true;
	}
	//해당 아이디 불러오기
	
	public UserDTO getId(String id) {
		users = getUsers();
		for(UserDTO u: users) {
			if(id.equals(u.getId())) {
				System.out.println("ids:" + u.getId());
				return u;
			}
		}
		return null;
	}
	
	// update User
	public void updateUser(UserDTO user) {
		try {
			conn = DBManager.getConnection();
			String sql = "update users set pw=? , tel =? where code = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPw());
			pstmt.setString(2, user.getTel());
			pstmt.setInt(3, user.getCode());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// delete user
	public void deleteUser(UserDTO user) {
		
		try {
			conn = DBManager.getConnection();
			String sql = "delete from users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			
		    pstmt.executeUpdate();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int getUserCodeById(String id) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select * from users where id=?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return -1;
	}
}
