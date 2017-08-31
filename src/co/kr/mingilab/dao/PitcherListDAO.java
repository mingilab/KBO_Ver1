package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.dto.PitcherListDTO;
import co.kr.mingilab.util.DBUtil;

public class PitcherListDAO {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	
	static public void StaringlistPrint(String userid){
		
		String sql = "select pitcherseq,playerid,pitcherlv,pitcherexp,position,NAME,GRADE,era,ip"
				+ " from PITCHER_STORAGE"
				+ " join PITCHER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " order by POSITION";	
		
		conn = DBUtil.getConnect();
		System.out.printf("고유번호  투수ID\t 투수LV\t 보유경험치\t 선발여부\t 이름\t 등급\t 방어율\t 이닝");
		System.out.println();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			rs = st.executeQuery();
			while(rs.next()){
				System.out.printf("%d\t",rs.getInt(1));
				System.out.printf("%d\t",rs.getInt(2));
				System.out.printf("%d\t",rs.getInt(3));
				System.out.printf("%d\t",rs.getInt(4));
				System.out.printf("%s\t",rs.getString(5));
				System.out.printf("%s\t",rs.getString(6));
				System.out.printf("%c\t",(rs.getString(7)).charAt(0));
				System.out.printf("%3.2f\t",rs.getDouble(8));
				System.out.printf("%d\t",rs.getInt(9));
				System.out.println();
				
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	} // 
	

static public PitcherListDTO thisPitch(String userid){
				
		PitcherListDTO dto = null;
		
	
		String sql = "select PITCHERSEQ,PLAYERID,PITCHERLV,PITCHEREXP,POSITION,NAME,GRADE"
				+ " from PITCHER_STORAGE"
				+ " join PITCHER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and POSITION = 'st1'"
				;	 
		conn = DBUtil.getConnect();
		
		
		try {
			
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			
			rs = st.executeQuery();
			dto = new PitcherListDTO();
			if(rs.next()){
				dto.setPitcherseq(rs.getInt(1));
				dto.setPlayerid(rs.getInt(2));
				dto.setPitcherlv(rs.getInt(3));
				dto.setPitcherexp(rs.getInt(4));
				dto.setPosition(rs.getString(5));
				dto.setName(rs.getString(6));
				dto.setGrade((rs.getString(7)).charAt(0));
								
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		return dto;
		
	} //print




	static public PitcherListDTO[] thisPitcherGrade(String userid,int pitchSeq){
		
		PitcherListDTO[] dtoarr = new PitcherListDTO[14] ;
		
		PitcherListDTO dto;
		
	
		String sql = "select GRADE"
				+ " from PITCHER_STORAGE"
				+ " join PITCHER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and PITCHERSEQ = ?"
				;	 
		conn = DBUtil.getConnect();
		
		
		try {
			for(int i = 1 ; i<=1 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setInt(2, pitchSeq);
				rs = st.executeQuery();
				dto = new PitcherListDTO();
				if(rs.next()){
					
					dto.setGrade((rs.getString(1)).charAt(0));
					
					dtoarr[i] = dto;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		return dtoarr;
		
	} //print
	
	static public PitcherListDTO[] StaringOrderpGet(String userid){
		
		PitcherListDTO[] dtoarr = new PitcherListDTO[14];
		
		PitcherListDTO dto;
		
		String position[] = new String[14];
		position[1] = "st1";
		position[2] = "st2";
		position[3] = "st3";
		position[4] = "pb1";
		position[5] = "pb2";
		position[6] = "pb3";
		position[7] = "pb4";


		
		String sql = "select PITCHERSEQ,PLAYERID,PITCHERLV,PITCHEREXP,POSITION,NAME,GRADE"
				+ " from PITCHER_STORAGE"
				+ " join PITCHER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and POSITION = ?"
				+ " order by POSITION";	 
		conn = DBUtil.getConnect();
		
		try {
			for(int i = 1 ; i<=7 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setString(2, position[i]);
				rs = st.executeQuery();
				dto = new PitcherListDTO();
				if(rs.next()){
					dto.setPitcherseq(rs.getInt(1));
					dto.setPlayerid(rs.getInt(2));
					dto.setPitcherlv(rs.getInt(3));
					dto.setPitcherexp(rs.getInt(4));
					dto.setPosition(rs.getString(5));
					dto.setName(rs.getString(6));
					dto.setGrade((rs.getString(7)).charAt(0));
	
					dtoarr[i] = dto;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		return dtoarr;
	}
}
