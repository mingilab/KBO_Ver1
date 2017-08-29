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
		System.out.println(count + "������ �����Ǿ����ϴ�.");
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
		
		System.out.println(count + "������ �����Ǿ����ϴ�.");
		
		/*
		makePlayer(userid, 506, "st1"); //1���� ��Ŀ
		makePlayer(userid, 248, "st2"); //2���� ��Ʃ��Ʈ
		makePlayer(userid, 351, "st3"); //3���� ������
		makePlayer(userid, 387, "pb1"); //1���� ��â��
		makePlayer(userid, 290, "pb2"); //2���� ������
		makePlayer(userid, 402, "pb3"); //3���� ������
		makePlayer(userid, 115, "pb4"); //4���� ������
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
		System.out.println(count + "������ �����Ǿ����ϴ�.");
	} */
	
}
