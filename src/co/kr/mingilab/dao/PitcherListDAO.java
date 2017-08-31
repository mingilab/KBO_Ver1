package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		System.out.printf("������ȣ  ����ID\t ����LV\t ��������ġ\t ���߿���\t �̸�\t ���\t �����\t �̴�");
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
	} 
	
}
