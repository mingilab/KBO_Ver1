package co.kr.mingilab.dto;

public class PitcherStorage {
	
	/*
	 *  PITCHERSEQ                                NOT NULL NUMBER
	 *  USERID                                    NOT NULL VARCHAR2(30)
 	 *  PITCHERID                                 NOT NULL NUMBER(4)
 	 *  PITCHERLV                                          NUMBER(3)
 	 *  PITCHEREXP                                         NUMBER(10)
 	 *  POSITION                                           VARCHAR2(3)
	 */
	
	int pitcherseq;
	String userid;
	int pitcherid; 
	int pitcherlv;
	int pitcherexp;
	String position;
	
	public PitcherStorage(){
		
	}
	
	public PitcherStorage(int pitcherseq, String userid, int pitcherid, int pitcherlv, int pitcherexp,
			String position) {
		super();
		this.pitcherseq = pitcherseq;
		this.userid = userid;
		this.pitcherid = pitcherid;
		this.pitcherlv = pitcherlv;
		this.pitcherexp = pitcherexp;
		this.position = position;
	}
	
	public int getPitcherseq() {
		return pitcherseq;
	}
	public void setPitcherseq(int pitcherseq) {
		this.pitcherseq = pitcherseq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPitcherid() {
		return pitcherid;
	}
	public void setPitcherid(int pitcherid) {
		this.pitcherid = pitcherid;
	}
	public int getPitcherlv() {
		return pitcherlv;
	}
	public void setPitcherlv(int pitcherlv) {
		this.pitcherlv = pitcherlv;
	}
	public int getPitcherexp() {
		return pitcherexp;
	}
	public void setPitcherexp(int pitcherexp) {
		this.pitcherexp = pitcherexp;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PitcherStorage [pitcherseq=").append(pitcherseq).append(", userid=").append(userid)
				.append(", pitcherid=").append(pitcherid).append(", pitcherlv=").append(pitcherlv)
				.append(", pitcherexp=").append(pitcherexp).append(", position=").append(position).append("]");
		return builder.toString();
	}
	
}
