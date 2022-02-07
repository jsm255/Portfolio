package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

import models.LikeDTO;
import utils.DBManager;

public class LikeDAO {
	private ArrayList<LikeDTO> like = null;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private LikeDAO() {};
	private static LikeDAO instance = new LikeDAO();
	public static LikeDAO getInstance() {
		return instance;
	}
	
	public ArrayList<LikeDTO> getLike(){
		like = new ArrayList<LikeDTO>();
		try {
			conn = DBManager.getConnection();
			String sql = "select * from `like`";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt(1);
				String id = rs.getString(2);
				String countryName = rs.getString(3);
				LikeDTO heart = new LikeDTO(code,id,countryName);
				like.add(heart);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return like;
	}
	
	public void addLike(LikeDTO heart) {
		try {
			conn = DBManager.getConnection();
			String sql = "insert into `like`(id, countryName) value(?, ?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, heart.getId());
			pstmt.setString(2, heart.getCountryName());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteLike(LikeDTO heart) {
		try {
			conn = DBManager.getConnection();
			
			String id = heart.getId();
			String countryName = heart.getCountryName();
			
			pstmt = conn.prepareStatement("select code from `like` where id=? and countryName=?");
			pstmt.setString(1, id);
			pstmt.setString(2, countryName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int code = rs.getInt(1);
				
				String sql = "delete from `like` where code = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, code);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<LikeDTO> getLikeId(String id){
		like = new ArrayList<LikeDTO>();
		
		try {
			conn = DBManager.getConnection();
			String sql ="select * from `like` where id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt(1);
				String getId = rs.getString(2);
				String countryName = rs.getString(3);
				
				LikeDTO heart = new LikeDTO(code,getId,countryName);
				like.add(heart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return like;
	}
	
	public int findIdCountryName(String id, String countryName) {
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement("select code from `like` where id=? and countryName=?");
			pstmt.setString(1, id);
			pstmt.setString(2, countryName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
