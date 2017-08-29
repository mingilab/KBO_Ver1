package co.kr.mingilab.dto;

import java.sql.Date;

public class AccountDTO {
	
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
	
	String userid;
	String userpw;
	int userlv;
	int userexp;
	Date creationdate;
	int win;
	int loss;
	int krw;
	
	public AccountDTO(){
		
	}

	public AccountDTO(String userid, String userpw, int userlv, int userexp, Date creationdate, int win, int loss,
			int krw) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.userlv = userlv;
		this.userexp = userexp;
		this.creationdate = creationdate;
		this.win = win;
		this.loss = loss;
		this.krw = krw;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public int getUserlv() {
		return userlv;
	}

	public void setUserlv(int userlv) {
		this.userlv = userlv;
	}

	public int getUserexp() {
		return userexp;
	}

	public void setUserexp(int userexp) {
		this.userexp = userexp;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public int getKrw() {
		return krw;
	}

	public void setKrw(int krw) {
		this.krw = krw;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDTO [userid=").append(userid).append(", userpw=").append(userpw).append(", userlv=")
				.append(userlv).append(", userexp=").append(userexp).append(", creationdate=").append(creationdate)
				.append(", win=").append(win).append(", loss=").append(loss).append(", krw=").append(krw).append("]");
		return builder.toString();
	}
	
}
