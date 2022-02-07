package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import models.ReReviewDTO;
import models.ReviewDTO;
import utils.DBManager;

public class ReReviewDAO {
	
	private ArrayList<ReReviewDTO> reReviews = new ArrayList<>();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static ReReviewDAO instance = new ReReviewDAO();
	private ReReviewDAO() {}
	public static ReReviewDAO getInstance() {
		return instance;
	}
	
	public ArrayList<ReReviewDTO> getReReviews(int attachCode) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select * from reReview where attachCode=?");
			
			pstmt.setInt(1, attachCode);
			
			rs = pstmt.executeQuery();
			
			reReviews = new ArrayList<>();
			
			while(rs.next()) {
				ReReviewDTO rrview = null;
				
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String content = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				String pw = "";
				int attachCodeTemp = rs.getInt(6);
				if(id.equals("Guest")) {
					pw = rs.getString(5);
					rrview = new ReReviewDTO(code, content, date, pw, attachCodeTemp);
				}
				else {
					int userCode = rs.getInt(7);
					rrview = new ReReviewDTO(code, id, content, date, attachCodeTemp, userCode);
				}
				
				reReviews.add(rrview);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return reReviews;
	}
	
	public ReReviewDTO getReReview(int code) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select * from reReview where code=?");
			
			pstmt.setInt(1, code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ReReviewDTO rrview = null;
				
				int rrviewCode = rs.getInt(1);
				String id = rs.getString(2);
				String content = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				String pw = "";
				int attachCodeTemp = rs.getInt(6);
				if(id.equals("Guest")) {
					pw = rs.getString(5);
					rrview = new ReReviewDTO(rrviewCode, content, date, pw, attachCodeTemp);
				}
				else {
					int userCode = rs.getInt(7);
					rrview = new ReReviewDTO(rrviewCode, id, content, date, attachCodeTemp, userCode);
				}
				
				return rrview;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
	
	public boolean removeReReview(ReReviewDTO rrview) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("delete from reReview where code=?");
			
			pstmt.setInt(1, rrview.getCode());
			
			pstmt.executeUpdate();
			
			// 지웠으면 원래 리뷰에 있던 attachCnt도 하나 줄여줘야 한다.
			ReviewDAO rDao = ReviewDAO.getInstance();
			rDao.changeAttachCnt(rrview.getAttachCode(), -1);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean removeReReviewsAttached(ReviewDTO review) {
		try {
			int attach = review.getAttachCnt();
			if(attach != 0) {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement("select * from reReview where attachCode=?");
				
				pstmt.setInt(1, review.getCode());
				
				rs = pstmt.executeQuery();
				
				reReviews = new ArrayList<>();
				
				while(rs.next()) {
					ReReviewDTO rrview = null;
					
					int code = rs.getInt(1);
					String id = rs.getString(2);
					String content = rs.getString(3);
					Timestamp date = rs.getTimestamp(4);
					String pw = "";
					int attachCodeTemp = rs.getInt(6);
					if(id.equals("Guest")) {
						pw = rs.getString(5);
						rrview = new ReReviewDTO(code, content, date, pw, attachCodeTemp);
					}
					else {
						int userCode = rs.getInt(7);
						rrview = new ReReviewDTO(code, id, content, date, attachCodeTemp, userCode);
					}
					
					reReviews.add(rrview);
				}
				
				for(ReReviewDTO rrview : reReviews) {
					pstmt = conn.prepareStatement("delete from reReview where code=?");
					
					pstmt.setInt(1, rrview.getCode());
					
					pstmt.executeUpdate();
				}
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean writeReReview(ReReviewDTO rrview) {
		
		try {
			conn = DBManager.getConnection();
			
			if(rrview.getId().equals("Guest")) {
				pstmt = conn.prepareStatement("insert reReview(content, pw, attachCode) values(?,?,?)");
				
				pstmt.setString(1, rrview.getContent());
				pstmt.setString(2, rrview.getPw());
				pstmt.setInt(3, rrview.getAttachCode());
				
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement("insert reReview(id, content, attachCode, userCode) values(?,?,?,?)");
				
				pstmt.setString(1, rrview.getId());
				pstmt.setString(2, rrview.getContent());
				pstmt.setInt(3, rrview.getAttachCode());
				pstmt.setInt(4, rrview.getUserCode());
				
				pstmt.executeUpdate();
			}
			
			ReviewDAO rDao = ReviewDAO.getInstance();
			rDao.changeAttachCnt(rrview.getAttachCode(), 1);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean modifyReReview(ReReviewDTO rrview) {
		
		try {
			conn = DBManager.getConnection();
			
			if(rrview.getId().equals("Guest")) {
				pstmt = conn.prepareStatement("update reReview set content=?, pw=? where code=?");
				pstmt.setString(1, rrview.getContent());
				pstmt.setString(2, rrview.getPw());
				pstmt.setInt(3, rrview.getCode());
				
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement("update reReview set content=? where code=?");
				pstmt.setString(1, rrview.getContent());
				pstmt.setInt(2, rrview.getCode());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}
}
