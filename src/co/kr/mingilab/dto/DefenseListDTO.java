package co.kr.mingilab.dto;

public class DefenseListDTO {

	int hitterseq;
	String userid;
	int playerid;
	int hitterlv;
	int hitterexp;
	String name;
	String club;
	String grade;
	String position;
	int g;
	int ip;
	int e;
	double fpct;
	double cs;
	
	public DefenseListDTO(){
		
	}
	
	public DefenseListDTO(int hitterseq, String userid, int playerid, int hitterlv, int hitterexp, String name,
			String club, String grade, String position, int g, int ip, int e, double fpct, double cs) {
		super();
		this.hitterseq = hitterseq;
		this.userid = userid;
		this.playerid = playerid;
		this.hitterlv = hitterlv;
		this.hitterexp = hitterexp;
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
		builder.append("DefenseListDTO [hitterseq=").append(hitterseq).append(", userid=").append(userid)
				.append(", playerid=").append(playerid).append(", hitterlv=").append(hitterlv).append(", hitterexp=")
				.append(hitterexp).append(", name=").append(name).append(", club=").append(club).append(", grade=")
				.append(grade).append(", position=").append(position).append(", g=").append(g).append(", ip=")
				.append(ip).append(", e=").append(e).append(", fpct=").append(fpct).append(", cs=").append(cs)
				.append("]");
		return builder.toString();
	}
		
}	
