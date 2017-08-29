package co.kr.mingilab.ui;

import java.util.HashMap;
import java.util.Map;

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
		System.out.printf("승:%d, 패:%d, 승률:%f%n", win, loss, winaverage);
		System.out.println("1.상점");
		System.out.println("2.구단관리");
		System.out.println("3.경매장");
		System.out.println("4.서버접속");
		System.out.println("0.프로그램종료");
		
	}
	
} //class
