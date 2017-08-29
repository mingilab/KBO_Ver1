package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kr.mingilab.util.DBUtil;

public class HitterStorageDAO {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	
	public static void makePlayer(String userid, int playerid){
		
		String sql;
	 	sql = "insert into HITTER_STORAGE(USERID,PLAYERID,HITTERSEQ) values(?,?,HITTER_STORAGE_SEQUNCE.nextval)";
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

	public static void makeHitterNC(String userid){
		//makePlayer(userid, playerid, position, battingorder);
		
		String sql[] = new String[14];
		sql[1] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,356,'8',1,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[2] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,174,'4',2,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[3] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,130,'9',3,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[4] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,493,'3',4,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[5] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,177,'5',5,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[6] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,534,'D',6,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[7] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,237,'6',7,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[8] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,117,'2',8,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[9] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,72,'7',9,HITTER_STORAGE_SEQUNCE.nextval)";
		sql[10] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,HITTERSEQ) values(?,287,'h1',HITTER_STORAGE_SEQUNCE.nextval)";
		sql[11] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,HITTERSEQ) values(?,443,'h2',HITTER_STORAGE_SEQUNCE.nextval)";
		sql[12] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,HITTERSEQ) values(?,108,'h3',HITTER_STORAGE_SEQUNCE.nextval)";
		sql[13] = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,HITTERSEQ) values(?,287,'h4',HITTER_STORAGE_SEQUNCE.nextval)";
		
		conn = DBUtil.getConnect();
	 	int count = 0;
	 	
		try {
			for(int i=1 ; i<=13 ; i++){
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
		
		System.out.println(count + "�߼��� �����Ǿ����ϴ�.");
		
		
		/*
		makePlayer(userid, 356,"8",1); //������ 1�� �߰�
		makePlayer(userid, 174,"4",2); //�ڹο� 2�� 2��
		makePlayer(userid, 130,"9",3); //������ 3�� ����
		makePlayer(userid, 493,"3",4); //������ 4�� 1��
		makePlayer(userid, 177,"5",5); //�ڼ��� 5�� 3��
		makePlayer(userid, 534,"D",6); //��ȣ�� 6�� ����
		makePlayer(userid, 237,"6",7); //�ս��� 7�� ����
		makePlayer(userid, 117,"2",8); //���±� 8�� ����
		makePlayer(userid, 72,"7",9); //�輺�� 9�� ����
		makePlayer(userid, 287,"h1"); //����� ��ġ1
		makePlayer(userid, 443,"h2"); //������ ��ġ2
		makePlayer(userid, 108,"h3"); //���ؿ� ��ġ3
		makePlayer(userid, 156,"h4"); //��â�� ��ġ4
		*/
	} //makeHitterNC
	
	/*
	public static void makePlayer(String userid, int playerid, String position){
		
		String sql;
	 	sql = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,HITTERSEQ) values(?,?,?,HITTER_STORAGE_SEQUNCE.nextval)";
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
	}
	
	public static void makePlayer(String userid, int playerid, String position, int battingorder){
		
		String sql;
	 	sql = "insert into HITTER_STORAGE(USERID,PLAYERID,POSITION,BATTINGORDER,HITTERSEQ) values(?,?,?,?,HITTER_STORAGE_SEQUNCE.nextval)";
	 	conn = DBUtil.getConnect();
	 	int count = 0;
	 	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setInt(2, playerid);
			st.setString(3, position);
			st.setInt(4, battingorder);
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
	*/
	
}  //class


