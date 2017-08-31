package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.util.DBUtil;

public class PitcherInformationDAO {
	
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	static PitcherInformationDTO dto;
	
	static public PitcherInformationDTO Print(int playerid){
		
		conn = DBUtil.getConnect();
		String sql = "select PLAYERID,NAME,CLUB,GRADE,ERA,IP"
				+ " from PITCHER_INFORMATION"
				+ " where playerid=?";
		
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, playerid);
			rs = st.executeQuery();
			if(rs.next()){
				dto = new PitcherInformationDTO();
				dto.setPlayerid(rs.getInt(1)+"");
				dto.setName(rs.getString(2));
				dto.setClub(rs.getString(3));
				dto.setGrade(rs.getString(4).charAt(0));
				dto.setEra(rs.getDouble(5));
				dto.setIp(rs.getDouble(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}		
		return dto;
		
	}//StaringlistPrint
	
	
}
