package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.dto.HitterListDTO;
import co.kr.mingilab.util.DBUtil;

public class HitterListDAO {

	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	/*
	static public void StaringlistPrint(String userid){
		
		String position[] = new String[15];
		position[1] = "D";
		position[2] = "2";
		position[3] = "3";
		position[4] = "4";
		position[5] = "5";
		position[6] = "6";
		position[7] = "7";
		position[8] = "8";
		position[9] = "9";
		position[10] = "h1";
		position[11] = "h2";
		position[13] = "h3";
		position[14] = "h4";
		
		String sql = "select HITTERSEQ,PLAYERID,HITTERLV,HITTEREXP,POSITION,BATTINGORDER,NAME,GRADE,AVG,OPS"
				+ " from HITTER_STORAGE"
				+ " join HITTER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and POSITION = ?"
				+ " order by USERID, POSITION";	 
		conn = DBUtil.getConnect();
		System.out.printf("선수고유번호/t 타자ID/t 타자LV/t 보유경험치/t 선발여부/t 타순/t 이름/t 등급/t 타율/t ops/t");
		try {
			for(int i = 1 ; i<=14 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setString(2, position[i]);
				rs = st.executeQuery();
				System.out.printf("%d/t %d/t %d/t %d/t");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	} */
	
	static public HitterListDTO[] StaringOrderGet(String userid){
		
		HitterListDTO[] dtoarr = new HitterListDTO[15];
		
		HitterListDTO dto;
		
		String position[] = new String[14];
		position[1] = "D";
		position[2] = "2";
		position[3] = "3";
		position[4] = "4";
		position[5] = "5";
		position[6] = "6";
		position[7] = "7";
		position[8] = "8";
		position[9] = "9";
		position[10] = "h1";
		position[11] = "h2";
		position[12] = "h3";
		position[13] = "h4";
		
		String sql = "select HITTERSEQ,PLAYERID,HITTERLV,HITTEREXP,POSITION,BATTINGORDER,NAME,GRADE,AVG,OPS"
				+ " from HITTER_STORAGE"
				+ " join HITTER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and POSITION = ?"
				+ " order by USERID, POSITION";	 
		conn = DBUtil.getConnect();
		
		try {
			for(int i = 1 ; i<=13 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setString(2, position[i]);
				rs = st.executeQuery();
				dto = new HitterListDTO();
				if(rs.next()){
					dto.setHitterseq(rs.getInt(1));
					dto.setPlayerid(rs.getInt(2));
					dto.setHitterlv(rs.getInt(3));
					dto.setHitterexp(rs.getInt(4));
					dto.setPosition(rs.getString(5));
					dto.setBattingorder(rs.getInt(6));
					dto.setName(rs.getString(7));
					dto.setGrade((rs.getString(8)).charAt(0));
					dto.setAvg(rs.getDouble(9));
					dto.setOps(rs.getDouble(10));
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
	
}
