package models;

import java.sql.Timestamp;

public class ReReviewDTO {
	private int code;
	private String id;
	private String content;
	private Timestamp date;
	private String pw;
	private int attachCode;
	private int userCode;
	
	public ReReviewDTO(int code, String id, String content, Timestamp date, int attachCode, int userCode) {
		this.code = code;
		this.id = id;
		this.content = content;
		this.date = date;
		this.attachCode = attachCode;
		this.userCode = userCode;
	}
	
	public ReReviewDTO(int code, String content, Timestamp date, String pw, int attachCode) {
		this.code = code;
		this.id = "Guest";
		this.content = content;
		this.date = date;
		this.pw = pw;
		this.attachCode = attachCode;
	}
	
	public ReReviewDTO(String id, String content, int attachCode, int userCode) {
		this.id = id;
		this.content = content;
		this.attachCode = attachCode;
		this.userCode = userCode;
	}
	
	public ReReviewDTO(String content, int attachCode, String pw) {
		this.id = "Guest";
		this.content = content;
		this.pw = pw;
		this.attachCode = attachCode;
	}
	
	public ReReviewDTO(int code, String id, String content, int userCode) {
		this.code = code;
		this.id = id;
		this.content = content;
		this.userCode = userCode;
	}
	
	public ReReviewDTO(int code, String id, String content, String pw) {
		this.code = code;
		this.id = "Guest";
		this.content = content;
		this.pw = pw;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public int getAttachCode() {
		return attachCode;
	}
	
	public int getUserCode() {
		return this.userCode;
	}
	
	
}
