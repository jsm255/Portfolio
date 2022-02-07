package models;

import java.util.ArrayList;

//create table users(

//code integer primary key auto_increment,
//id varchar(10) not null primary key,  -- 사용자 아이디
//pw varchar(10) not null,  -- 사용자 비밀번호
//username varchar(20) not null -- 사용자 이름
//tel varchar(30) not null -- 사용자 전화번호
//);

public class UserDTO {
	private int code;
	private String id;
	private String pw;
	private String userName;
	private String tel;

	public UserDTO(int code, String id, String pw, String userName, String tel) {
		super();
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.userName = userName;
		this.tel = tel;
	}

	public UserDTO(String id, String pw, String userName, String tel) {
		super();
		this.id = id;
		this.pw = pw;
		this.userName = userName;
		this.tel = tel;
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

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "UserDTO [code=" + code + ", id=" + id + ", pw=" + pw + ", userName=" + userName + ", tel=" + tel + "]";
	}
}
