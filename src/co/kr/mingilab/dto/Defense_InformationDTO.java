package co.kr.mingilab.dto;

public class Defense_InformationDTO {
	
	/*
	 * PLAYERID                                  NOT NULL NUMBER
	 * NAME                                      NOT NULL VARCHAR2(30)
	 * CLUB                                      NOT NULL VARCHAR2(30)
	 * GRADE                                              VARCHAR2(3)
	 * POSITION                                           VARCHAR2(3)
	 * G                                         NOT NULL NUMBER(4)
	 * IP                                        NOT NULL NUMBER(10,4)
	 * E                                         NOT NULL NUMBER(4)
	 * FPCT                                      NOT NULL NUMBER(10,4)
	 * CS                                        NOT NULL NUMBER(10,4)
	 */
	
	int playerid;
	String name;
	String club;
	String grade;
	String position;
	int g;
	int ip;
	int e;
	double fpct;
	double cs;
	
	public Defense_InformationDTO(){
		
	}
	
	public Defense_InformationDTO(int playerid, String name, String club, String grade, String position, int g, int ip,
			int e, double fpct, double cs) {
		super();
		this.playerid = playerid;
		this.name = name;
		this.club = club;
		this.grade = grade;
		this.position = position;
		this.g = g;
		this.ip = ip;
		this.e = e;
		this.fpct = fpct;
		this.cs = cs;
	}
	
	public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	public double getFpct() {
		return fpct;
	}
	public void setFpct(double fpct) {
		this.fpct = fpct;
	}
	public double getCs() {
		return cs;
	}
	public void setCs(double cs) {
		this.cs = cs;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Defense_InformationDTO [playerid=").append(playerid).append(", name=").append(name)
				.append(", club=").append(club).append(", grade=").append(grade).append(", position=").append(position)
				.append(", g=").append(g).append(", ip=").append(ip).append(", e=").append(e).append(", fpct=")
				.append(fpct).append(", cs=").append(cs).append("]");
		return builder.toString();
	}
	
}
