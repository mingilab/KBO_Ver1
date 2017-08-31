package co.kr.mingilab.dto;

public class HitterListDTO {
	
	int hitterseq;
	int playerid;
	int hitterlv;
	int hitterexp;
	String position;
	int hittingOrder;
	String name;
	char grade;
	double avg;
	double ops;
	
	
	
	public int getHitterseq() {
		return hitterseq;
	}



	public void setHitterseq(int hitterseq) {
		this.hitterseq = hitterseq;
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



	public int getHittingOrder() {
		return hittingOrder;
	}



	public void setHittingOrder(int hittingOrder) {
		this.hittingOrder = hittingOrder;
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



	public double getAvg() {
		return avg;
	}



	public void setAvg(double avg) {
		this.avg = avg;
	}



	public double getOps() {
		return ops;
	}



	public void setOps(double ops) {
		this.ops = ops;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(hitterseq).append(", Ÿ�ھ��̵�:").append(playerid)
				.append(", Ÿ�ڷ���:").append(hitterlv).append(", Ÿ�ڰ���ġ:").append(hitterexp).append(", position=")
				.append(position).append(", Ÿ��:").append(hittingOrder).append(", �̸�:").append(name)
				.append(", ���:").append(grade).append(", avg=").append(avg).append(", ops=").append(ops).append("]");
		return builder.toString();
	}




}
