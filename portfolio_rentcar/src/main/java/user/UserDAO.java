package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import car.CarDTO;
import util.DBManager;


public class UserDAO {
	
	public static int log = -1;
	
	private ArrayList<UserDTO> users = null;
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	private UserDAO() {}
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	public ArrayList<UserDTO> getUsers(){
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("Select* from `user`");
			
			rs = pstmt.executeQuery();
			
			users = new ArrayList<>();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String userName = rs.getString(3);
				int age = rs.getInt(4);
				int rentCnt = rs.getInt(5);
				int totalPrice = rs.getInt(6);
				
				UserDTO getData = new UserDTO(id, pw, userName, age, rentCnt, totalPrice);
				
				users.add(getData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean login(String id, String pw) {
		for(int i = 0; i<users.size(); i++) {
			if(users.get(i).getId().equals(id) && users.get(i).getPw().equals(pw)) {
				UserDAO.log = i;
				return true;
			}
		}
		
		return false;
	}
	
	public int join(UserDTO user) {
		final int DUPLICATE = -1;
		final int ERROR = -2;
		final int NP = 0;
		try {
			if(checkDuplicate(user.getId())) {
				return DUPLICATE;
			}
			else {
				con = DBManager.getConnection();
				
				pstmt = con.prepareStatement("insert `user`(id, pw, userName, age) values(?,?,?,?)");
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPw());
				pstmt.setString(3, user.getUserName());
				pstmt.setInt(4, user.getAge());
				
				pstmt.executeUpdate();
				
				return NP;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private boolean checkDuplicate(String id) {
		try {
			ArrayList<UserDTO> temp = getUsers();
			for(UserDTO checker : temp) {
				if(checker.getId().equals(id)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public UserDTO getUser(int log) {
		getUsers();
		return users.get(log);
	}
	
	public boolean userRented(UserDTO user, int pay) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("update `user` set rentCnt=?, totalPrice=? where id=?");
			pstmt.setInt(1, user.getRentCnt()+1);
			pstmt.setInt(2, user.getTotalPrice()+pay);
			pstmt.setString(3, user.getId());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String printPrice(UserDTO user) {	// 가격에 쉼표 적어주기
		String price = String.valueOf(user.getTotalPrice());
		String newPrice = "";
		if(price.length() > 3) {
			for(int i = 0; i<price.length(); i++) {
				newPrice += price.charAt(i);
				// 마지막 숫자가 아니고 뒤에 숫자가 3의 배수로 남았다면 ,를 찍어줌
				if((price.length()-1 -i) % 3 == 0 && (price.length()-1) != i) newPrice += ",";
				
			}
		}
		else {
			for(int i = 0; i<price.length(); i++) {
				newPrice += price.charAt(i);
			}
		}
		newPrice += " 원";
		return newPrice;
	}
}
