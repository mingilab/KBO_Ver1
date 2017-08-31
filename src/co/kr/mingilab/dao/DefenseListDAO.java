package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kr.mingilab.dto.DefenseListDTO;
import co.kr.mingilab.util.DBUtil;

public class DefenseListDAO {
	
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	static DefenseListDTO[] ddtolist;
	static ArrayList<DefenseListDTO> ddtoalist;
	static DefenseListDTO ddto;
	
	static public void listPrint(String userid){
		
		String sql =  "select hitterseq, playerid, name, club, D.position, grade"
				+ " from HITTER_STORAGE H"
				+ " join DEFENSE_INFORMATION D using(PLAYERID)"
				+ " where userid = ?"
				+ " and D.position!='1'"
				+ " order by D.position";
		
		conn = DBUtil.getConnect();
		System.out.printf("고유번호  타자ID\t 이름\t 구단\t 포지션\t 등급\t");
		System.out.println();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			rs = st.executeQuery();
			while(rs.next()){
				System.out.printf("%d\t",rs.getInt(1));
				System.out.printf("%d\t",rs.getInt(2));
				System.out.printf("%s\t",rs.getString(3));
				System.out.printf("%s\t",rs.getString(4));
				System.out.printf("%s\t",rs.getString(5));
				System.out.printf("%s\t",rs.getString(6));
				System.out.println();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	}

	public static DefenseListDTO[] StaringlistPrint(String userid) {
		
		String sql = "select H.HITTERSEQ, H.playerid, name, club, H.POSITION, D.grade, H.BATTINGORDER"
				+" from HITTER_STORAGE H, DEFENSE_INFORMATION D"
				+" where H.userid = ?"
				+" and H.POSITION in('2','3','4','5','6','7','8','9','D')"
				+" and H.playerid=D.playerid"
				+" and H.POSITION=D.POSITION"	
				+" order by D.grade";
		
		conn = DBUtil.getConnect();
		System.out.println();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			rs = st.executeQuery();
			
			
			ddtolist = new DefenseListDTO[10];
			for(int i=1 ; i<=9 ; i++){
				if(rs.next()){
					ddto = new DefenseListDTO();
					ddto.setHitterseq(rs.getInt(1));
					ddto.setPlayerid(rs.getInt(2));
					ddto.setName(rs.getString(3));
					ddto.setClub(rs.getString(4));
					ddto.setSposition(rs.getString(5));
					ddto.setGrade(rs.getString(6));
					ddto.setBattingorder(rs.getInt(7));
					ddtolist[i] = ddto;
				}
			}
			
			/*
			while(rs.next()){
				System.out.printf("%d\t",rs.getInt(1));
				System.out.printf("%d\t",rs.getInt(2));
				System.out.printf("%s\t",rs.getString(3));
				System.out.printf("%s\t",rs.getString(4));
				System.out.printf("%s\t",rs.getString(5));
				System.out.printf("%s\t",rs.getString(6));
				System.out.println();
			} */
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		return ddtolist;
		
	} //StaringlistPrint
	
	public static ArrayList<DefenseListDTO> backuplistPrint(String userid, String sposition){
		
		ddtoalist = new ArrayList<DefenseListDTO>();
		System.out.println(sposition);
		String sql ="";
		
		sql = "select H.HITTERSEQ, H.playerid, name, club, D.POSITION, D.grade, H.BATTINGORDER, H.POSITION"
				+ " from HITTER_STORAGE H, DEFENSE_INFORMATION D"
				+ " where H.userid = ?"
				+ " and D.POSITION= ?"
				+ " and H.POSITION is null"
				+ " and H.playerid=D.playerid";
				
		conn = DBUtil.getConnect();
		
		if(sposition.equals("D")){
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setString(2, sposition);
				rs = st.executeQuery();
				while(rs.next()){
					ddto = new DefenseListDTO();
					ddto.setHitterseq(rs.getInt(1));
					ddto.setPlayerid(rs.getInt(2));
					ddto.setName(rs.getString(3));
					ddto.setClub(rs.getString(4));
					ddto.setPosition(rs.getString(5));
					ddto.setGrade(rs.getString(6));
					ddto.setIp(rs.getInt(7));
					ddto.setSposition(rs.getString(8));
					ddtoalist.add(ddto);
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			
		}else{
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, userid);
				st.setString(2, sposition);
				rs = st.executeQuery();
				while(rs.next()){
					ddto = new DefenseListDTO();
					ddto.setHitterseq(rs.getInt(1));
					ddto.setPlayerid(rs.getInt(2));
					ddto.setName(rs.getString(3));
					ddto.setClub(rs.getString(4));
					ddto.setPosition(rs.getString(5));
					ddto.setGrade(rs.getString(6));
					ddto.setIp(rs.getInt(7));
					ddto.setSposition(rs.getString(8));
					ddtoalist.add(ddto);
				}
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}		
		}
		
		return ddtoalist;
	}
	
}//class
