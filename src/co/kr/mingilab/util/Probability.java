package co.kr.mingilab.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Probability {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	
	public static <E> E getWeightedRandom(Map<E, Double> weights, Random random) {
		  E result = null;
		  double bestValue = Double.MAX_VALUE;
		 
		  for (E element : weights.keySet()) {
		    double value = -Math.log(random.nextDouble()) / weights.get(element);
		    if (value < bestValue) {
		      bestValue = value;
		      result = element;
		    }
		  }
		  return result;
	}	

	public static Map<String, Double> MapRandom(String mapname){
		
		Map<String, Double> w = new HashMap<String, Double>();
		String sql ="SELECT DOLL_ID, " + mapname + " FROM DOLLS_CHOICE";	 
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				w.put(rs.getInt(1)+"", rs.getDouble(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.dbClose(conn, st, rs);
		}
		return w;
	}
}
