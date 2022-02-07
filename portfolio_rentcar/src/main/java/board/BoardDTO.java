package board;

import java.sql.Timestamp;

public class BoardDTO {
	private int num;
	private String title;
	private String content;
	private String userName;
	private String pw;
	private int view;
	private int like;
	private Timestamp time;
	
	// sql에서 가져오는 용도
	public BoardDTO(int num, String title, String content, String userName, String pw, int view, int like, Timestamp time) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.pw = pw;
		this.view = view;
		this.like = like;
		this.time = time;
	}
	
	// 등록하는 용도
	public BoardDTO(String title, String content, String userName, String pw) {
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.pw = pw;
	}

	public int getNum() {
		return num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
