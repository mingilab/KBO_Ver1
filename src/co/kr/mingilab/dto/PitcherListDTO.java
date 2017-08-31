package co.kr.mingilab.dto;

public class PitcherListDTO {
	
	int pitcherseq;
	int playerid;
	int pitcherlv;
	int pitcherexp;
	String position;
	String name;
	char grade;
	
	
	public PitcherListDTO() {
		super();
		this.pitcherseq = pitcherseq;
		this.playerid = playerid;
		this.pitcherlv = pitcherlv;
		this.pitcherexp = pitcherexp;
		this.position = position;
		this.name = name;
		this.grade = grade;
	}
	
	
		

	
	public int getPitcherseq() {
		return pitcherseq;
	}





	public void setPitcherseq(int pitcherseq) {
		this.pitcherseq = pitcherseq;
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





	public char getGrade() {
		return grade;
	}





	public void setGrade(char grade) {
		this.grade = grade;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(pitcherseq).append(", 플레이어아이디:").append(playerid)
				.append(", 투수레벨:").append(pitcherlv).append(", 투수경험치:").append(pitcherexp)
				.append(", 선발현황:").append(position).append(", 이름:").append(name).append(", 등급:").append(grade)
				.append("]");
		return builder.toString();
	}








	








	



}