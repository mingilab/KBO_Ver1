package co.kr.mingilab.dto;

public class PitcherListDTO1 {
	
	/*
	 * PITCHERSEQ                                 NOT NULL NUMBER
	 * USERID                                    NOT NULL VARCHAR2(30)
	 * PLAYERID                                  NOT NULL NUMBER
	 * PITCHERLV                                           NUMBER(3)
	 * PITCHEREXP                                          NUMBER(10)
	 * POSITION                                           VARCHAR2(3)
	 * PLAYERID                                  NOT NULL NUMBER
	 * NAME                                      NOT NULL VARCHAR2(30)
	 * CLUB                                               VARCHAR2(30)
	 * GRADE                                              VARCHAR2(1)
	 * ERA                                       NOT NULL NUMBER(6,3)
	 * G                                         NOT NULL NUMBER(3)
	 * W                                         NOT NULL NUMBER(3)
	 * L                                         NOT NULL NUMBER(3)
	 * SV                                        NOT NULL NUMBER(3)
	 * HLD                                       NOT NULL NUMBER(3)
	 * IP                                        NOT NULL NUMBER(3)
	 * H                                         NOT NULL NUMBER(4)
	 * HR                                        NOT NULL NUMBER(3)
	 * BB                                        NOT NULL NUMBER(3)
	 * HBP                                       NOT NULL NUMBER(3)
	 * SO                                        NOT NULL NUMBER(4)
	 */
	
	int pitcherseq;
	String userid;
	int playerid;
	int pitcherlv;
	int pitcherexp;
	String position;
	String name;
	String club;
	char grade;
	double era;
	int ip;
		
	public PitcherListDTO1() {
		super();
	}

	public PitcherListDTO1(int pitcherseq, String userid, int playerid, int pitcherlv, int pitcherexp, String position,
			String name, String club, char grade, double era, int ip) {
		super();
		this.pitcherseq = pitcherseq;
		this.userid = userid;
		this.playerid = playerid;
		this.pitcherlv = pitcherlv;
		this.pitcherexp = pitcherexp;
		this.position = position;
		this.name = name;
		this.club = club;
		this.grade = grade;
		this.era = era;
		this.ip = ip;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public double getEra() {
		return era;
	}
	public void setEra(double era) {
		this.era = era;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PitcherListDTO [pitcherseq=").append(pitcherseq).append(", userid=").append(userid)
				.append(", playerid=").append(playerid).append(", pitcherlv=").append(pitcherlv).append(", pitcherexp=")
				.append(pitcherexp).append(", position=").append(position).append(", name=").append(name)
				.append(", club=").append(club).append(", grade=").append(grade).append(", era=").append(era)
				.append(", ip=").append(ip).append("]");
		return builder.toString();
	}
	
	
}
