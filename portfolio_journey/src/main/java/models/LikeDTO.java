package models;

public class LikeDTO {
	private int code;
	private String id;
	private String countryName;
	
	public LikeDTO(int code, String id, String countryName) {
		this.code = code;
		this.id = id;
		this.countryName = countryName;
	}
	
	public LikeDTO(String id, String countryName) {
		this.id = id;
		this.countryName = countryName;
	}

	public int getCode() {
		return code;
	}


	public String getId() {
		return id;
	}


	public String getCountryName() {
		return countryName;
	}
}
