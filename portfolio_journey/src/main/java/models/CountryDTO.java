package models;

public class CountryDTO {
	
	private int code;
	private String flag;
	private String countryName;
	private String score;
	private String content;
	private int likecnt;
	public CountryDTO(String flag, String countryName, String score, String content) {
		this.flag = flag;
		this.countryName = countryName;
		this.score = score;
		this.content = content; 
	}
	
	public CountryDTO(int code, String flag, String countryName, String score, String content) {
		this.code = code;
		this.flag = flag;
		this.countryName = countryName;
		this.score = score;
		this.content = content; 
		
	}
	
	public CountryDTO(String countryName, String content, String flag) {
		this.countryName = countryName;
		this.content = content;
		this.flag = flag;
	}
	
	public CountryDTO(int code, String flag, String countryName, String score, String content, int likecnt) {
		this.code = code;
		this.flag = flag;
		this.countryName = countryName;
		this.score = score;
		this.content = content; 
		this.likecnt = likecnt;
	}

	public int getCode() {
		return code;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getLikecnt() {
		return likecnt;
	}
}
