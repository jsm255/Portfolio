package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class BoardDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private ArrayList<BoardDTO> board = null;
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO (){};
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public ArrayList<BoardDTO> getBoard(){
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("Select* from board");
			
			rs = pstmt.executeQuery();
			
			board = new ArrayList<>();
			
			while(rs.next()) {
				int num = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String userName = rs.getString(4);
				String pw = rs.getString(5);
				int view = rs.getInt(6);
				int like = rs.getInt(7);
				Timestamp time = rs.getTimestamp(8);
				
				BoardDTO getter = new BoardDTO(num, title, content, userName, pw, view, like, time);
				
				board.add(getter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	public BoardDTO getArticle(int articleNum) {
		BoardDTO getter = null;
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("Select* from board where num=?");
			pstmt.setInt(1, articleNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String userName = rs.getString(4);
				String pw = rs.getString(5);
				int view = rs.getInt(6);
				int like = rs.getInt(7);
				Timestamp time = rs.getTimestamp(8);
				
				getter = new BoardDTO(num, title, content, userName, pw, view, like, time);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getter;
	}
	
	public boolean writeArticle(BoardDTO article) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("insert board(title, content, userName, pw) values(?, ?, ?, ?)");
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getUserName());
			pstmt.setString(4, article.getPw());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int increaseView(BoardDTO article) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("update board set `view`=? where num=?");
			pstmt.setInt(1, article.getView()+1);
			pstmt.setInt(2, article.getNum());
			
			pstmt.executeUpdate();
			
			pstmt = null;
			
			pstmt = con.prepareStatement("Select `view` from board where num=?");
			pstmt.setInt(1, article.getNum());
			
			rs = pstmt.executeQuery();
			
			int view = -1;
			if(rs.next()) view = rs.getInt(1);
			
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int increaseLike(BoardDTO article) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("update board set `like`=? where num=?");
			pstmt.setInt(1, article.getLike()+1);
			pstmt.setInt(2, article.getNum());
			
			pstmt.executeUpdate();
			
			pstmt = null;
			
			pstmt = con.prepareStatement("Select `like` from board where num=?");
			pstmt.setInt(1, article.getNum());
			
			rs = pstmt.executeQuery();
			
			int like = -1;
			if(rs.next()) like = rs.getInt(1);
			
			return like;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean removeArticle(int articleNum) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("delete from board where num=?");
			
			pstmt.setInt(1, articleNum);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modifyArticle(BoardDTO modify, int articleNum) {
		try {
			con = DBManager.getConnection();
			
			pstmt = con.prepareStatement("update board set title=?, content=?, userName=?, pw=? where num=?");
			pstmt.setString(1, modify.getTitle());
			pstmt.setString(2, modify.getContent());
			pstmt.setString(3, modify.getUserName());
			pstmt.setString(4, modify.getPw());
			pstmt.setInt(5, articleNum);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
