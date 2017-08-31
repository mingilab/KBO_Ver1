package co.kr.mingilab.dto;

public class HitterListDTO3 {
	char grade;

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}
	

	public HitterListDTO3() {
	} 

	public HitterListDTO3(char grade) {
		super();
		this.grade = grade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[µî±Þ:").append(grade).append("]");
		return builder.toString();
	}
	
	
	
	
}
