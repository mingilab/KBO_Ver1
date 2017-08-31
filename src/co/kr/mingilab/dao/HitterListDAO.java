package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.dto.HitterListDTO;
import co.kr.mingilab.dto.HitterListDTO1;
import co.kr.mingilab.dto.PitcherListDTO;
import co.kr.mingilab.util.DBUtil;

public class HitterListDAO {

	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	
	static public void StaringlistPrint(String userid){
		
		String sql = "select HITTERSEQ,PLAYERID,HITTERLV,HITTEREXP,POSITION,BATTINGORDER,NAME,GRADE,AVG,OPS"
				+ " from HITTER_STORAGE"
				+ " join HITTER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " order by POSITION";	 
		conn = DBUtil.getConnect();
		System.out.printf("고유번호  타자ID\t 타자LV\t 보유경험치\t 선발여부\t 타순\t 이름\t 등급\t 타율\t ops");
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
				System.out.printf("%d\t",rs.getInt(6));
				System.out.printf("%s\t",rs.getString(7));
				System.out.printf("%c\t",(rs.getString(8)).charAt(0));
				System.out.printf("%4.3f\t",rs.getDouble(9));
				System.out.printf("%4.3f\t",rs.getDouble(10));
				System.out.println();
				
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	} 
	

	static public HitterListDTO[] thisHitter(String userid,int batOrder){
		
		HitterListDTO[] dtoarr = new HitterListDTO[14] ;
		
		HitterListDTO dto;
		
	
		String sql = "select HITTERSEQ,PLAYERID,HITTERLV,HITTEREXP,POSITION,BATTINGORDER,NAME,GRADE,AVG,OPS"
				+ " from HITTER_STORAGE"
				+ " join HITTER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and BATTINGORDER = ?"
				;	 
		conn = DBUtil.getConnect();
		
		
		try {
			for(int i = 1 ; i<=1 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setInt(2, batOrder);
				rs = st.executeQuery();
				dto = new HitterListDTO();
				if(rs.next()){
					dto.setHitterseq(rs.getInt(1));
					dto.setPlayerid(rs.getInt(2));
					dto.setHitterlv(rs.getInt(3));
					dto.setHitterexp(rs.getInt(4));
					dto.setPosition(rs.getString(5));
					dto.setHittingOrder(rs.getInt(6));
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

	static public HitterListDTO[] thisHitterGrade(String userid,int batOrder){
		
		HitterListDTO[] dtoarr = new HitterListDTO[14] ;
		
		HitterListDTO dto;
		
	
		String sql = "select GRADE"
				+ " from HITTER_STORAGE"
				+ " join HITTER_INFORMATION using(PLAYERID)"
				+ " where USERID = ?"
				+ " and BATTINGORDER = ?"
				;	 
		conn = DBUtil.getConnect();
		
		
		try {
			for(int i = 1 ; i<=1 ; i++){
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setInt(2, batOrder);
				rs = st.executeQuery();
				dto = new HitterListDTO();
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
	

	static public HitterListDTO[] StaringOrderGet(String userid){
		
		HitterListDTO[] dtoarr = new HitterListDTO[14];
		
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
				+ " order by BATTINGORDER, POSITION";	 
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
					dto.setHittingOrder(rs.getInt(6));
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
