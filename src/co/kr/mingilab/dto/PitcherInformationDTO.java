package co.kr.mingilab.dto;

public class PitcherInformationDTO {
	
	/*
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
	
	String playerid;
	String name;
	String club;
	char grade;
	double era;
	int g;
	int w;
	int l;
	int sv;
	int hld;
	double ip;
	int h;
	int hr;
	int bb;
	int hbp;
	int so;
	
	public PitcherInformationDTO(){
		
	}
	
	public PitcherInformationDTO(String playerid, String name, String club, char grade, double era, int g, int w, int l,
			int sv, int hld, double ip, int h, int hr, int bb, int hbp, int so) {
		super();
		this.playerid = playerid;
		this.name = name;
		this.club = club;
		this.grade = grade;
		this.era = era;
		this.g = g;
		this.w = w;
		this.l = l;
		this.sv = sv;
		this.hld = hld;
		this.ip = ip;
		this.h = h;
		this.hr = hr;
		this.bb = bb;
		this.hbp = hbp;
		this.so = so;
	}
	
	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
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
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	public int getSv() {
		return sv;
	}
	public void setSv(int sv) {
		this.sv = sv;
	}
	public int getHld() {
		return hld;
	}
	public void setHld(int hld) {
		this.hld = hld;
	}
	public double getIp() {
		return ip;
	}
	public void setIp(double ip) {
		this.ip = ip;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getBb() {
		return bb;
	}
	public void setBb(int bb) {
		this.bb = bb;
	}
	public int getHbp() {
		return hbp;
	}
	public void setHbp(int hbp) {
		this.hbp = hbp;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PitcherInformationDTO [playerid=").append(playerid).append(", name=").append(name)
				.append(", club=").append(club).append(", grade=").append(grade).append(", era=").append(era)
				.append(", g=").append(g).append(", w=").append(w).append(", l=").append(l).append(", sv=").append(sv)
				.append(", hld=").append(hld).append(", ip=").append(ip).append(", h=").append(h).append(", hr=")
				.append(hr).append(", bb=").append(bb).append(", hbp=").append(hbp).append(", so=").append(so)
				.append("]");
		return builder.toString();
	}
	
}	
