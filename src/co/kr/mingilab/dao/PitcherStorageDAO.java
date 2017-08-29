package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.util.DBUtil;

public class PitcherStorageDAO {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	
	public static void makePlayer(String userid, int playerid){
		
		String sql;
	 	sql = "insert into PITCHER_STORAGE(USERID,PLAYERID,PITCHERSEQ) values(?,?,PITCHER_STORAGE_SEQUNCE.nextval)";
	 	conn = DBUtil.getConnect();
	 	int count = 0;
	 	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setInt(2, playerid);
			count = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		System.out.println(count + "선수가 생성되었습니다.");
	}
	
	public static void makePitcherNC(String userid){
		
		String sql[] = new String[8];
		sql[1] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,506,'st1',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[2] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,248,'st2',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[3] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,351,'st3',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[4] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,387,'pb1',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[5] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,290,'pb2',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[6] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,402,'pb3',PITCHER_STORAGE_SEQUNCE.nextval)";
		sql[7] = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,115,'pb4',PITCHER_STORAGE_SEQUNCE.nextval)";
		
		conn = DBUtil.getConnect();
	 	int count = 0;
	 	
		try {
			for(int i=1 ; i<=7 ; i++){
				st = conn.prepareStatement(sql[i]);
				st.setString(1, userid);
				count += st.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		System.out.println(count + "투수가 생성되었습니다.");
		
		/*
		makePlayer(userid, 506, "st1"); //1선발 해커
		makePlayer(userid, 248, "st2"); //2선발 스튜어트
		makePlayer(userid, 351, "st3"); //3선발 이재학
		makePlayer(userid, 387, "pb1"); //1불펜 임창민
		makePlayer(userid, 290, "pb2"); //2불펜 원종현
		makePlayer(userid, 402, "pb3"); //3불펜 장현식
		makePlayer(userid, 115, "pb4"); //4불펜 김진성
		*/
	} //makePitcherNC
	
	/*
	public static void makePlayer(String userid, int playerid, String position){
		
		String sql;
	 	sql = "insert into PITCHER_STORAGE(USERID,PLAYERID,POSITION,PITCHERSEQ) values(?,?,?,PITCHER_STORAGE_SEQUNCE.nextval)";
	 	conn = DBUtil.getConnect();
	 	int count = 0;
	 	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setInt(2, playerid);
			st.setString(3, position);
			count = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		System.out.println(count + "선수가 생성되었습니다.");
	} */
	
}
