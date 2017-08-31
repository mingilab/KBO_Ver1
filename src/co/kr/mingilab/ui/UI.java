package co.kr.mingilab.ui;

import co.kr.mingilab.dto.AccountDTO;

public class UI {
	
	public static void loginUI(){
		
		System.out.println("2016 KBO에 오신여러분을 환영합니다.");
		System.out.println("1.접속");
		System.out.println("2.계정생성");
		System.out.println("0.프로그램종료");
		System.out.println();
		
	} // loginUI
	
	
	public static void mainUI(AccountDTO accountdto, int[] exptable){
		
		String uid = accountdto.getUserid();
		int lv = accountdto.getUserlv();
		int exp = accountdto.getUserexp();
		int needexp = exptable[lv];
		int krw = accountdto.getKrw();
		int win = accountdto.getWin();
		int loss = accountdto.getLoss();
		double winaverage;
		
		if(win+loss == 0){
			winaverage = 0;
		}else{
			winaverage = win/(win+loss);
		}
		
		System.out.printf("%s 구단주님 환영합니다.%n", uid);
		System.out.printf("LV:%d, 경험치:%d/%d, 보유원화:%d원%n",lv,exp,needexp,krw);
		System.out.printf("승:%d, 패:%d, 승률:%4.3f%n", win, loss, winaverage);
		System.out.println("1.상점");
		System.out.println("2.구단관리");
		System.out.println("3.경매장");
		System.out.println("4.서버접속");
		System.out.println("0.프로그램종료");
		
	}
	
	public static void MangerUI(){
		
		System.out.println("구단관리를 시작합니다.");
		System.out.println("0.메인으로 돌아갑니다.");
		System.out.println("1.야수리스트 출력");
		System.out.println("2.투수리스트 출력");
		System.out.println("3.수비리스트 출력");
		System.out.println("4.야수교체");
		System.out.println("5.타순교체");
		System.out.println("6.투수교체");
		System.out.println();
		
	} // loginUI
	
	public static void StoreUI(){
		
		System.out.println("KBO상점에 오신걸 환영합니다.");
		System.out.println("선수구매시 아래와 같은 비용이 소모됩니다.");
		System.out.println("A등급 : 10000원, B등급 : 3000원, C등급 : 1000원");
		System.out.println("0.메인으로 돌아갑니다.");
		System.out.println("1.야수구매");
		System.out.println("2.투수구매");
		System.out.println();
		
	} // loginUI
	
} //class
