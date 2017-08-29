package co.kr.mingilab.dto;

public class PitcherStorageDTO {
	
	/*
	 * PITCHERSEQ                                NOT NULL NUMBER
	 * USERID                                    NOT NULL VARCHAR2(30)
	 * PLAYERID                                  NOT NULL NUMBER
	 * PITCHERLV                                          NUMBER(3)
	 * PITCHEREXP                                         NUMBER(10)
	 * POSITION                                           VARCHAR2(3)
	 */
	
	int pitcherseq;
	String userid;
	int playerid;
	int pitcherlv;
	int pitcherexp;
	String position;
	
	public PitcherStorageDTO(){
		
	}
	
	public PitcherStorageDTO(int pitcherseq, String userid, int playerid, int pitcherlv, int pitcherexp,
			String position) {
		super();
		this.pitcherseq = pitcherseq;
		this.userid = userid;
		this.playerid = playerid;
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
	public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
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
		builder.append("PitcherStorageDTO [pitcherseq=").append(pitcherseq).append(", userid=").append(userid)
				.append(", playerid=").append(playerid).append(", pitcherlv=").append(pitcherlv).append(", pitcherexp=")
				.append(pitcherexp).append(", position=").append(position).append("]");
		return builder.toString();
	}
	
}
