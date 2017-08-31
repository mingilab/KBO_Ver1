package co.kr.mingilab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.util.DBUtil;
	
public class AccountDAO {
	
	static Connection conn;
	static PreparedStatement st;
	static ResultSet rs;
	//int needexp[] = UserEXPDAO.User_exptable();
	
	/*
	 *  USERID                                    NOT NULL VARCHAR2(30)
	 *  USERPW                                    NOT NULL VARCHAR2(30)
 	 *  USERLV                                             NUMBER(3)
 	 *  USEREXP                                            NUMBER(10)
 	 *  CREATIONDATE                                       DATE
 	 *  WIN                                                NUMBER
 	 *  LOSS                                               NUMBER
 	 *  KRW                                                NUMBER
	 */	
	
	// ������ ���Ǵ� DTO pw������ �������
	public static AccountDTO userload(AccountDTO dto) {
		String uid = dto.getUserid();
		String sql ="select * from Account where USERID = ?";	 
		conn = DBUtil.getConnect();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, uid);
			rs = st.executeQuery();
			if(rs.next()){
				dto = new AccountDTO();
				dto.setUserid(rs.getNString(1));
				dto.setUserpw("");
				dto.setUserlv(rs.getInt(3));
				dto.setUserexp(rs.getInt(4));
				dto.setCreationdate(rs.getDate(5));
				dto.setWin(rs.getInt(6));
				dto.setLoss(rs.getInt(7));
				dto.setKrw(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		return dto;
		
	} // userlogin
	
	// ������ ���� Account DTO����
	// pw������ �������
	public static AccountDTO userlogin(String uid, String upw) {
		AccountDTO dto=null;
		String sql ="select * from Account where USERID = ?";	 
		conn = DBUtil.getConnect();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, uid);
			rs = st.executeQuery();
			if(rs.next()){
				dto = new AccountDTO();
				dto.setUserid(rs.getNString(1));
				dto.setUserpw(rs.getString(2));
				dto.setUserlv(rs.getInt(3));
				dto.setUserexp(rs.getInt(4));
				dto.setCreationdate(rs.getDate(5));
				dto.setWin(rs.getInt(6));
				dto.setLoss(rs.getInt(7));
				dto.setKrw(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		
		if(upw.equals(dto.getUserpw())){
			System.out.println("���ӿ� �����Ͽ����ϴ�.");
			return dto;
		} else {
			System.out.println("���ӿ� �����Ͽ����ϴ�.");
			return null;
		}
		
	} // userlogin
		
	//����ڷκ��� �Է��� �޾� ��������
	public void makeUser(){
		
		Scanner sc = new Scanner(System.in);
		
		String userid = null;                                
	 	String userpw = null;  
	 	String scuid = null; //��ĳ�ʷ� �Է¹��� id
	 	String upw1 = null; //��ĳ�ʷ� �Է¹��� pw1
	 	String upw2 = null; //��ĳ�ʷ� �Է¹��� pw2
	 	char ykey = ' ';
	 	boolean loof = true;;
	 	int loofcount = 0;
	 	String sql;
	 		 	
	 	System.out.println("ID�� �Է��� �ּ���");
	 	scuid = sc.nextLine();	
	 	scuid.trim();
	 	
	 	while(loof){
	 		System.out.println("PW�� �Է��� �ּ���");
		 	upw1 = sc.nextLine();
		 	System.out.println("PW�� �ٽ� �Է��� �ּ���");
		 	upw2 = sc.nextLine();
		 	if(upw1.equals(upw2)){
		 		userid = scuid;
		 		userpw = upw1;
		 		System.out.println("�Է��Ͻ� ID�� �н����带 Ȯ�����ּ���");
		 		System.out.printf("ID : %s PW : %s %n",userid,userpw);
		 		System.out.println("������ y�Ǵ� Y�� �����ּ���. �ٸ�Ű�� ������ ����ȭ������ ���ư��ϴ�.");
		 		ykey = sc.nextLine().charAt(0);
		 		if(ykey == 'y' || ykey == 'Y'){
		 			loof = false;
		 		}else{
		 			System.out.println("����ȭ������ ���ư��ϴ�.");
		 			return;
		 		}
		 	}else{
		 		System.out.println("�������� ���� ��й�ȣ�Դϴ�. �ٽ� �Է��� �ּ���.");
		 		loofcount ++;	
		 	}//if
		 	if(loofcount==3){
		 		System.out.println("�Է��Ͻ� ������ 3ȸ Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");
		 		return;
		 	}
	 	}//while
	 	
	 	sql = "insert into ACCOUNT(USERID,USERPW) values(?,?)";
	 	conn = DBUtil.getConnect();
	 	int count = 0;
	 	try {
			st = conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setString(2, userpw);
			count = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("������ ���������� �ֽ��ϴ�. �ٽ� �õ����ּ���.");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		} finally{
			DBUtil.dbClose(conn, st, rs);
		}
	 	System.out.println(count);
	 	
	 	if(count==0){
	 		System.out.println("���������� �����Ͽ����ϴ�.");
	 	}else{
		 	HitterStorageDAO.makeHitterNC(userid);
		 	PitcherStorageDAO.makePitcherNC(userid);
	 		System.out.println("���������� �Ϸ�Ǿ����ϴ�.");
	 	}
	 	
	} //makeUser
	
	static public int KrwUse(AccountDTO dto, char grade){
		
		int krw=0;
		if(grade == 'A'){
			krw = 10000;
		}else if(grade == 'B'){
			krw = 3000;
		}else{
			krw = 1000;
		}
		
		String sql = "update account set krw = krw-"
				+ krw
				+ " where userid=?";
		conn = DBUtil.getConnect();
		int count=0;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, dto.getUserid());
			count = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;	
	}
}
