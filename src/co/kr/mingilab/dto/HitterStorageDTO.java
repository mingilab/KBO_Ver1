package co.kr.mingilab.dto;

public class HitterStorageDTO {
	
	/*
	 * HITTERSEQ                                 NOT NULL NUMBER
	 * USERID                                    NOT NULL VARCHAR2(30)
	 * PLAYERID                                  NOT NULL NUMBER
	 * HITTERLV                                           NUMBER(3)
	 * HITTEREXP                                          NUMBER(10)
	 * POSITION                                           VARCHAR2(3)
	 * BATTINGORDER                                       NUMBER(1)
	 */
	
	int hitterseq;
	String userid;
	int playerid;
	int hitterlv;
	int hitterexp;
	String position;
	int battingorder;
	
	public HitterStorageDTO(){
		
	}
	
	public HitterStorageDTO(int hitterseq, String userid, int playerid, int hitterlv, int hitterexp, String position,
			int battingorder) {
		super();
		this.hitterseq = hitterseq;
		this.userid = userid;
		this.playerid = playerid;
		this.hitterlv = hitterlv;
		this.hitterexp = hitterexp;
		this.position = position;
		this.battingorder = battingorder;
	}
	public int getHitterseq() {
		return hitterseq;
	}
	public void setHitterseq(int hitterseq) {
		this.hitterseq = hitterseq;
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
	public int getHitterlv() {
		return hitterlv;
	}
	public void setHitterlv(int hitterlv) {
		this.hitterlv = hitterlv;
	}
	public int getHitterexp() {
		return hitterexp;
	}
	public void setHitterexp(int hitterexp) {
		this.hitterexp = hitterexp;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getBattingorder() {
		return battingorder;
	}
	public void setBattingorder(int battingorder) {
		this.battingorder = battingorder;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HitterStorageDTO [hitterseq=").append(hitterseq).append(", userid=").append(userid)
				.append(", playerid=").append(playerid).append(", hitterlv=").append(hitterlv).append(", hitterexp=")
				.append(hitterexp).append(", position=").append(position).append(", battingorder=").append(battingorder)
				.append("]");
		return builder.toString();
	}
	
}
