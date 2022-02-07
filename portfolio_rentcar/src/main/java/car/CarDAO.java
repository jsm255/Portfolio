package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class CarDAO {
	
	private ArrayList<CarDTO> cars = null;
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	private CarDAO() {}
	private static CarDAO instance = new CarDAO();
	public static CarDAO getInstance() {
		return instance;
	}
	
	public ArrayList<CarDTO> getCars(){
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("Select* from car order by price asc");	// 가격 기준 오름차순으로 가져옴
			
			rs = pstmt.executeQuery();
			
			cars = new ArrayList<CarDTO>();
			
			while(rs.next()) {
				
				String carCode = rs.getString(1);
				String carName = rs.getString(2);
				String imgPath = rs.getString(3);
				int price = rs.getInt(4);
				int stock = rs.getInt(5);
				int rented = rs.getInt(6);
				
				CarDTO getData = new CarDTO(carCode, carName, imgPath, price, stock, rented);
				
				cars.add(getData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cars;
	}
	
	public String printPrice(CarDTO car) {	// 가격에 쉼표 적어주기
		String price = String.valueOf(car.getPrice());
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
	
	public CarDTO getCar(String carCode) {
		ArrayList<CarDTO> getter = getCars();
		int index = -1;
		for(int i = 0; i<getter.size(); i++) {
			if(carCode.equals(getter.get(i).getCarCode())) index = i;
		}
		return getter.get(index);
	}
	
	public boolean carRented(CarDTO car) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("update car set stock=?, rented=? where carCode=?");
			pstmt.setInt(1, car.getStock()-1);
			pstmt.setInt(2, car.getRented()+1);
			pstmt.setString(3, car.getCarCode());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
