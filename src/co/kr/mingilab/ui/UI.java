package co.kr.mingilab.ui;

import java.util.HashMap;
import java.util.Map;

import co.kr.mingilab.dto.AccountDTO;

public class UI {
	
	public static void loginUI(){
		
		System.out.println("2016 KBO�� ���ſ������� ȯ���մϴ�.");
		System.out.println("1.����");
		System.out.println("2.��������");
		System.out.println("0.���α׷�����");
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
		
		System.out.printf("%s �����ִ� ȯ���մϴ�.%n", uid);
		System.out.printf("LV:%d, ����ġ:%d/%d, ������ȭ:%d��%n",lv,exp,needexp,krw);
		System.out.printf("��:%d, ��:%d, �·�:%f%n", win, loss, winaverage);
		System.out.println("1.����");
		System.out.println("2.���ܰ���");
		System.out.println("3.�����");
		System.out.println("4.��������");
		System.out.println("0.���α׷�����");
		
	}
	
} //class
