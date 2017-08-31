package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import co.kr.mingilab.dto.HitterInformationDTO;
import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.util.DBUtil;

public class HitterInformationDAO {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	static HitterInformationDTO dto;
	
	static public HitterInformationDTO Print(int playerid){
		
		conn = DBUtil.getConnect();
		String sql = "select PLAYERID,NAME,CLUB,GRADE,AVG,OPS"
				+ " from HITTER_INFORMATION"
				+ " where PLAYERID=?";
		
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, playerid);
			rs = st.executeQuery();
			if(rs.next()){
				dto = new HitterInformationDTO();
				dto.setPlayerid(rs.getInt(1)+"");
				dto.setName(rs.getString(2));
				dto.setClub(rs.getString(3));
				dto.setGrade(rs.getString(4).charAt(0));
				dto.setAvg(rs.getDouble(5));
				dto.setOps(rs.getDouble(6));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}		
		return dto;
		
	}//StaringlistPrint
	
}//class
