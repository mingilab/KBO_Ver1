package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.dto.PitcherStorageDTO;
import co.kr.mingilab.util.DBUtil;

public class pitcherGameDAO {

	
		Connection conn;
		PreparedStatement st;
		ResultSet rs;
		int count; 
		PitcherStorageDTO dto = null;
		PitcherInformationDTO dto2 = null;
	
		public PitcherStorageDTO selectByPosition(AccountDTO adto ,String position){
			
			String sql;
		 	sql = "select * from PITCHER_STORAGE where POSITION=? and userid =?";
		 	conn = DBUtil.getConnect();
		 	
		 	try {
				st = conn.prepareStatement(sql);
				st.setString(1, position);
				st.setString(2, adto.getUserid());
				
				rs = st.executeQuery();
								
				dto =makeKbo(rs);		
				
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
				
			}
			finally{
				DBUtil.dbClose(conn, st, rs);
		
			}
			
			return dto;
		}

		
		
	
public PitcherInformationDTO selectByName(String name){
			
			String sql;
		 	sql = "select * from PITCHER_INFORMATION where NAME=?";
		 	
		 	
		 	try {
				st = conn.prepareStatement(sql);
				st.setString(1, name);
				
				rs = st.executeQuery();
				
				
				while(rs.next()){
					dto2 =makeKbo2(rs);		
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
				
			}
			finally{
				DBUtil.dbClose(conn, st, rs);
		
			}
			
			return dto2;
		}
		
	

private static PitcherStorageDTO makeKbo(ResultSet rs) throws SQLException {
	
		int pitcherseq = rs.getInt(1);
		String userid = rs.getString(2);
		int playerid = rs.getInt(3);
		int pitcherlv = rs.getInt(4);
		int pitcherexp = rs.getInt(5);
		String position = rs.getString(6);
		
		return new PitcherStorageDTO(pitcherseq, userid, playerid, pitcherlv, pitcherexp, position);
	}

private static  PitcherInformationDTO  makeKbo2(ResultSet rs) throws SQLException {
	String playerid = rs.getString(1);
	String name=rs.getString(2);
	String club=rs.getString(3);
	char grade=(rs.getString(4)).charAt(0);
	double era=rs.getDouble(5);
	int g=rs.getInt(6);
	int w=rs.getInt(7);
	int l=rs.getInt(8);
	int sv=rs.getInt(9);
	int hld=rs.getInt(10);
	double ip=rs.getDouble(11);
	int h=rs.getInt(12);
	int hr=rs.getInt(13);
	int bb=rs.getInt(14);
	int hbp=rs.getInt(15);
	int so=rs.getInt(16);
	

	
	return new PitcherInformationDTO(playerid, name, club, grade, era, g, w, l, sv, hld, ip, h, hr, bb, hbp, so);
}

}
