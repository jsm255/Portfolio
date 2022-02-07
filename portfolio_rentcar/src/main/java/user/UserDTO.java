package user;

public class UserDTO {
	private String id;
	private String userName;
	private String pw;
	private int age;
	private int rentCnt;
	private int totalPrice;
	
	// 신규 가입시 사용
	public UserDTO(String id, String pw, String userName, int age) {
		this.id = id;
		this.userName = userName;
		this.pw = pw;
		this.age = age;
	}
	
	// 데이터를 가져올 때 사용
	public UserDTO(String id, String pw, String userName, int age, int rentCnt, int totalPrice) {
		this.id = id;
		this.userName = userName;
		this.pw = pw;
		this.age = age;
		this.rentCnt = rentCnt;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRentCnt() {
		return rentCnt;
	}

	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
