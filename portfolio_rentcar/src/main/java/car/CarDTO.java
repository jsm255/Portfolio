package car;

public class CarDTO {
	private String carCode;
	private String carName;
	private String imgPath;
	private int price;
	private int stock;
	private int rented;
	
	public CarDTO(String carCode, String carName, String imgPath, int price, int stock, int rented) {
		this.carCode = carCode;
		this.carName = carName;
		this.imgPath = imgPath;
		this.price = price;
		this.stock = stock;
		this.rented = rented;
	}

	public String getCarCode() {
		return carCode;
	}

	public String getCarName() {
		return carName;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setRent(int stock) {
		this.stock = stock;
	}
	
	public int getRented() {
		return rented;
	}
	
	public void setRented(int rented) {
		this.rented = rented;
	}
	
	
}
