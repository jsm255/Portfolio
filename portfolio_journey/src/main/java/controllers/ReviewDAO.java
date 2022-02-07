package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.ReviewDTO;
import utils.DBManager;

public class ReviewDAO {
	
	private ArrayList<ReviewDTO> reviews = null;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static ReviewDAO instance = new ReviewDAO();
	private ReviewDAO () {}
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	public ArrayList<ReviewDTO> getReviews(String countryName){
		reviews = new ArrayList<>();
		
		try {	
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select * from review where countryName=? order by attachCnt desc");
			pstmt.setString(1, countryName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				 
				ReviewDTO review = null;
				
				int code = rs.getInt(1);
				String getCountryName = rs.getString(2);
				String id = rs.getString(3);
				String content = rs.getString(4);
				int score = rs.getInt(5);
				Timestamp date = rs.getTimestamp(6);
				String pw = "";
				int attachCnt = rs.getInt(8);
				if(id.equals("Guest")) {		// 비 회원 용
					pw = rs.getString(7);
					review = new ReviewDTO(code, getCountryName, content, score, date, pw, attachCnt);
				}
				else {		// 회원 용
					int userCode = rs.getInt(9);
					review = new ReviewDTO(code, getCountryName, id, content, score, date, attachCnt, userCode);
				}
				
				reviews.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviews;
	}	
	
	public boolean writeReview(ReviewDTO review) {
		try {
			conn = DBManager.getConnection();
			
			if(review.getId().equals("Guest")) {
				pstmt = conn.prepareStatement("insert review(countryName, content, score, pw) values(?, ?, ?, ?)");
				pstmt.setString(1, review.getCountryName());
				pstmt.setString(2, review.getContent());
				pstmt.setInt(3, review.getScore());
				pstmt.setString(4, review.getPw());
				
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement("insert review(countryName, id, content, score, userCode) values(?, ?, ?, ?, ?)");
				pstmt.setString(1, review.getCountryName());
				pstmt.setString(2, review.getId());
				pstmt.setString(3, review.getContent());
				pstmt.setInt(4, review.getScore());
				pstmt.setInt(5, review.getUserCode());
				
				pstmt.executeUpdate();
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ReviewDTO getReview(int code) {
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select * from review where code=?");
			pstmt.setInt(1, code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ReviewDTO review = null;
				
				int reviewCode = rs.getInt(1);
				String getCountryName = rs.getString(2);
				String id = rs.getString(3);
				String content = rs.getString(4);
				int score = rs.getInt(5);
				Timestamp date = rs.getTimestamp(6);
				String pw = "";
				int attachCnt = rs.getInt(8);
				if(id.equals("Guest")) {		// 비 회원 용
					pw = rs.getString(7);
					review = new ReviewDTO(reviewCode, getCountryName, content, score, date, pw, attachCnt);
				}
				else {		// 회원 용
					int userCode = rs.getInt(9);
					review = new ReviewDTO(reviewCode, getCountryName, id, content, score, date, attachCnt, userCode);
				}
				
				return review;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean modifyReview(ReviewDTO review) {
		try {
			conn = DBManager.getConnection();
			
			if(review.getId().equals("Guest")) {
				pstmt = conn.prepareStatement("update review set content=?, score=?, pw=? where code=?");
				pstmt.setString(1, review.getContent());
				pstmt.setInt(2, review.getScore());
				pstmt.setString(3, review.getPw());
				pstmt.setInt(4, review.getCode());
				
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement("update review set content=?, score=? where code=?");
				pstmt.setString(1, review.getContent());
				pstmt.setInt(2, review.getScore());
				pstmt.setInt(3, review.getCode());
				
				pstmt.executeUpdate();
			}
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeReview(ReviewDTO review) {
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("delete from review where code=?");
			pstmt.setInt(1, review.getCode());
			
			pstmt.executeUpdate();
			
			ReReviewDAO rrDao = ReReviewDAO.getInstance();
			
			rrDao.removeReReviewsAttached(review);
			
//			// 만약 답글이 있으면 그것도 지워줘야함
//			// 이건 reReviewDAO를 통해 처리하도록 하자
//			pstmt = conn.prepareStatement("delete from reReview where attachCode=?");
//			pstmt.setInt(1, review.getCode());
//			
//			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean changeAttachCnt(int code, int change) {
		
		try {
			
			ReviewDTO review = getReview(code);
			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("update review set attachCnt=? where code=?");
			
			pstmt.setInt(1, review.getAttachCnt()+change);
			pstmt.setInt(2, code);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<ReviewDTO> getMyBoardList(String reviewId){
		reviews = new ArrayList<ReviewDTO>();
		try {
			conn = DBManager.getConnection();
			
			UserDAO uDao = UserDAO.getInstance();
			
			String sql = "select * from review where userCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uDao.getUserCodeById(reviewId));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt(1);
				String countryName = rs.getString(2);
				String id = rs.getString(3);
				String content = rs.getString(4);
				Timestamp date = rs.getTimestamp(6);
				int userCode = rs.getInt(9);
				
				ReviewDTO review = new ReviewDTO(code,countryName,id,content, date, userCode);
				reviews.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}
	
}
