package co.kr.mingilab.dto;

public class HitterInformationDTO {
	
	/*
	 * PLAYERID                                  NOT NULL NUMBER
	 * NAME                                      NOT NULL VARCHAR2(30)
	 * CLUB                                               VARCHAR2(30)
	 * GRADE                                              VARCHAR2(1)
	 * AVG                                       NOT NULL NUMBER(4,3)
	 * G                                         NOT NULL NUMBER(3)
	 * PA                                        NOT NULL NUMBER(4)
	 * R                                         NOT NULL NUMBER(3)
	 * H                                         NOT NULL NUMBER(3)
	 * B2                                        NOT NULL NUMBER(3)
	 * B3                                        NOT NULL NUMBER(3)
	 * HR                                        NOT NULL NUMBER(3)
	 * RBI                                       NOT NULL NUMBER(3)
	 * SAC                                       NOT NULL NUMBER(3)
	 * BB                                        NOT NULL NUMBER(3)
	 * HBP                                       NOT NULL NUMBER(3)
	 * SLG                                       NOT NULL NUMBER(4,3)
	 * OBP                                       NOT NULL NUMBER(4,3)
	 * OPS                                       NOT NULL NUMBER(4,3)
	 * RISP                                      NOT NULL NUMBER(4,3)
	 */
	
	String playerid;
	String name;
	String club;
	char grade;
	double avg;
	int g;
	int pa;
	int r;
	int h;
	int b2;
	int b3;
	int hr;
	int rbi;
	int sac;
	int bb;
	int hbp;
	double slg;
	double obp;
	double ops;
	double risp;
	
	public HitterInformationDTO(){
		
	}
	
	public HitterInformationDTO(String playerid, String name, String club, char grade, double avg, int g, int pa, int r,
			int h, int b2, int b3, int hr, int rbi, int sac, int bb, int hbp, double slg, double obp, double ops,
			double risp) {
		super();
		this.playerid = playerid;
		this.name = name;
		this.club = club;
		this.grade = grade;
		this.avg = avg;
		this.g = g;
		this.pa = pa;
		this.r = r;
		this.h = h;
		this.b2 = b2;
		this.b3 = b3;
		this.hr = hr;
		this.rbi = rbi;
		this.sac = sac;
		this.bb = bb;
		this.hbp = hbp;
		this.slg = slg;
		this.obp = obp;
		this.ops = ops;
		this.risp = risp;
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
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getPa() {
		return pa;
	}
	public void setPa(int pa) {
		this.pa = pa;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getB2() {
		return b2;
	}
	public void setB2(int b2) {
		this.b2 = b2;
	}
	public int getB3() {
		return b3;
	}
	public void setB3(int b3) {
		this.b3 = b3;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getRbi() {
		return rbi;
	}
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}
	public int getSac() {
		return sac;
	}
	public void setSac(int sac) {
		this.sac = sac;
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
	public double getSlg() {
		return slg;
	}
	public void setSlg(double slg) {
		this.slg = slg;
	}
	public double getObp() {
		return obp;
	}
	public void setObp(double obp) {
		this.obp = obp;
	}
	public double getOps() {
		return ops;
	}
	public void setOps(double ops) {
		this.ops = ops;
	}
	public double getRisp() {
		return risp;
	}
	public void setRisp(double risp) {
		this.risp = risp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HitterInformationDTO [playerid=").append(playerid).append(", name=").append(name)
				.append(", club=").append(club).append(", grade=").append(grade).append(", avg=").append(avg)
				.append(", g=").append(g).append(", pa=").append(pa).append(", r=").append(r).append(", h=").append(h)
				.append(", b2=").append(b2).append(", b3=").append(b3).append(", hr=").append(hr).append(", rbi=")
				.append(rbi).append(", sac=").append(sac).append(", bb=").append(bb).append(", hbp=").append(hbp)
				.append(", slg=").append(slg).append(", obp=").append(obp).append(", ops=").append(ops)
				.append(", risp=").append(risp).append("]");
		return builder.toString();
	}
	
}
