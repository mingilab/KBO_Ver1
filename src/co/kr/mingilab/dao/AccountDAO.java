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
	
	// 실제로 사용되는 DTO pw정보가 비어있음
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
	
	// 접속을 위한 Account DTO생성
	// pw정보가 살아있음
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
			System.out.println("접속에 성공하였습니다.");
			return dto;
		} else {
			System.out.println("접속에 실패하였습니다.");
			return null;
		}
		
	} // userlogin
		
	//사용자로부터 입력을 받아 계정생성
	public void makeUser(){
		
		Scanner sc = new Scanner(System.in);
		
		String userid = null;                                
	 	String userpw = null;  
	 	String scuid = null; //스캐너로 입력받은 id
	 	String upw1 = null; //스캐너로 입력받은 pw1
	 	String upw2 = null; //스캐너로 입력받은 pw2
	 	char ykey = ' ';
	 	boolean loof = true;;
	 	int loofcount = 0;
	 	String sql;
	 		 	
	 	System.out.println("ID를 입력해 주세요");
	 	scuid = sc.nextLine();	
	 	scuid.trim();
	 	
	 	while(loof){
	 		System.out.println("PW를 입력해 주세요");
		 	upw1 = sc.nextLine();
		 	System.out.println("PW를 다시 입력해 주세요");
		 	upw2 = sc.nextLine();
		 	if(upw1.equals(upw2)){
		 		userid = scuid;
		 		userpw = upw1;
		 		System.out.println("입력하신 ID와 패스워드를 확인해주세요");
		 		System.out.printf("ID : %s PW : %s %n",userid,userpw);
		 		System.out.println("맞으면 y또는 Y를 눌러주세요. 다른키를 누르면 메인화면으로 돌아갑니다.");
		 		ykey = sc.nextLine().charAt(0);
		 		if(ykey == 'y' || ykey == 'Y'){
		 			loof = false;
		 		}else{
		 			System.out.println("메인화면으로 돌아갑니다.");
		 			return;
		 		}
		 	}else{
		 		System.out.println("동일하지 않은 비밀번호입니다. 다시 입력해 주세요.");
		 		loofcount ++;	
		 	}//if
		 	if(loofcount==3){
		 		System.out.println("입력하신 정보가 3회 틀렸습니다. 다시 시도해주세요.");
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
			System.out.println("동일한 계정정보가 있습니다. 다시 시도해주세요.");
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
	 		System.out.println("계정생성이 실패하였습니다.");
	 	}else{
		 	HitterStorageDAO.makeHitterNC(userid);
		 	PitcherStorageDAO.makePitcherNC(userid);
	 		System.out.println("계정생성이 완료되었습니다.");
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
