package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.CountryDTO;
import models.ReviewDTO;
import utils.DBManager;

public class CountryDAO {
	
	private ArrayList<CountryDTO> countries = null;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static CountryDAO instance = new CountryDAO();
	private CountryDAO () {};
	public static CountryDAO getInstance() {
		return instance;
	}
	
	public ArrayList<CountryDTO> getCountries() {
		countries = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select* from country");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt(1);
				String flag = rs.getString(2);
				String countryName = rs.getString(3);
				String score = rs.getString(4);
				String content = rs.getString(5);
				int likecnt = rs.getInt(6);
				
				CountryDTO country = new CountryDTO(code, flag, countryName, score, content, likecnt);
				
				countries.add(country);
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
		
		return countries;
	}
	
	public CountryDTO getCountry(String countryName) {
		setCountryScore(countryName);
		countries = getCountries();
		
		for(CountryDTO temp : countries) {
			if(temp.getCountryName().equals(countryName)) return temp;
		}
		
		return null;
	}
	
	
	private boolean setCountryScore(String countryName) {
		
		Double average = 0.0;
		String score = "0.0";
		
		try {
			
			conn = DBManager.getConnection();
			
			ReviewDAO rDao = ReviewDAO.getInstance();
			ArrayList<ReviewDTO> reviews = rDao.getReviews(countryName);
			
			if(reviews.size() >= 1) {
				for(ReviewDTO temp : reviews) 
					average += temp.getScore();
				
				average /= reviews.size();
				score = String.format("%2.2f", average);
			}
			
			pstmt = conn.prepareStatement("update country set score=? where countryName=?");
			pstmt.setString(1, score);
			pstmt.setString(2, countryName);
			
			pstmt.executeUpdate();
			
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
	
	public CountryDTO searchCountry(String countryName) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select* from country where countryName=?");	// 국가 이름으로 찾아봄
			pstmt.setString(1, countryName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 결과가 있으면
				int code = rs.getInt(1);
				String flag = rs.getString(2);
				String getCountryName = rs.getString(3);
				String score = rs.getString(4);
				String content = rs.getString(5);
			
				CountryDTO country = new CountryDTO(code, flag, getCountryName, score, content);
				
				return country;
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
	
	public boolean generateCountry(CountryDTO country) {
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("insert country(countryName, content, flag) values(?, ?, ?)");
			pstmt.setString(1, country.getCountryName());
			pstmt.setString(2, country.getContent());
			pstmt.setString(3, country.getFlag());
			pstmt.executeUpdate();
			
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
	
	public ArrayList<String> getCountryNames(){
		ArrayList<String> countryNames = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select countryName from country");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String countryName = rs.getString(1);
				
				countryNames.add(countryName);
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
		
		return countryNames;
	}
	
	public void likeCnt(String countryName) {
		try {
			conn = DBManager.getConnection();
			
			CountryDTO country = getCountry(countryName);
			
			int likecnt = country.getLikecnt();
			
			String sql = "update country set likecnt=? where code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, likecnt+1);
			pstmt.setInt(2, country.getCode());
			
			pstmt.executeUpdate();
			
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
	}
	
	public void delCnt(String countryName) {
		try {
			conn = DBManager.getConnection();
			CountryDTO country = getCountry(countryName);
			
			int likecnt = country.getLikecnt();
			
			String sql = "update country set likecnt=? where code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, likecnt-1);
			pstmt.setInt(2, country.getCode());
			pstmt.executeUpdate();
			
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
	}
	
}
