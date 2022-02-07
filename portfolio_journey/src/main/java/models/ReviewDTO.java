package models;

import java.sql.Timestamp;

public class ReviewDTO {
	private int code;
	private String countryName;
	private String id;
	private String content;
	private int score;
	private Timestamp date;
	private String pw;
	private int attachCnt;
	private int userCode;
	
	// 회원이 쓴 걸 가져올 때
	public ReviewDTO(int code, String countryName, String id, String content, int score, Timestamp date, int attachCnt, int userCode) {
		this.code = code;
		this.countryName = countryName;
		this.id = id;
		this.content = content;
		this.score = score;
		this.date = date;
		this.attachCnt = attachCnt;
		this.userCode = userCode;
	}
	
	// 게스트가 쓴 걸 가져올 때
	public ReviewDTO(int code, String countryName, String content, int score, Timestamp date, String pw, int attachCnt) {
		this.code = code;
		this.countryName = countryName;
		this.id = "Guest";
		this.content = content;
		this.score = score;
		this.date = date;
		this.pw = pw;
		this.attachCnt = attachCnt;
	}
	
	// 회원이 작성한 리뷰
	public ReviewDTO(String countryName, String id, String content, int score, int userCode) {
		this.countryName = countryName;
		this.id = id;
		this.content = content;
		this.score = score;
		this.userCode = userCode;
	}
	
	// 게스트가 작성한 리뷰
	public ReviewDTO(String countryName, String content, int score, String pw) {
		this.countryName = countryName;
		this.id = "Guest";
		this.content = content;
		this.score = score;
		this.pw = pw;
	}
	
	// 회원이 작성한 리뷰 수정시에 사용
	public ReviewDTO(int code, String countryName, String id, String content, int score, int userCode) {
		this.code = code;
		this.countryName = countryName;
		this.id = id;
		this.content = content;
		this.score = score;
		this.userCode = userCode;
	}
	
	// 게스트가 작성한 리뷰 수정시에 사용
	public ReviewDTO(int code, String countryName, String content, int score, String pw) {
		this.code = code;
		this.countryName = countryName;
		this.id = "Guest";
		this.content = content;
		this.score = score;
		this.pw = pw;
	}
	
	// 회원이 작성한 리뷰보기
	public ReviewDTO(int code, String countryName, String id,String content, Timestamp date, int userCode) {
		this.code = code;
		this.countryName = countryName;
		this.id = id;
		this.content = content;
		this.date = date;
		this.userCode = userCode;
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

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public int getAttachCnt() {
		return this.attachCnt;
	}
	
	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}
	
	public int getUserCode() {
		return this.userCode;
	}
}
