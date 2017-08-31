package co.kr.mingilab.ui;

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
		System.out.printf("��:%d, ��:%d, �·�:%4.3f%n", win, loss, winaverage);
		System.out.println("1.����");
		System.out.println("2.���ܰ���");
		System.out.println("3.�����");
		System.out.println("4.��������");
		System.out.println("0.���α׷�����");
		
	}
	
	public static void MangerUI(){
		
		System.out.println("���ܰ����� �����մϴ�.");
		System.out.println("0.�������� ���ư��ϴ�.");
		System.out.println("1.�߼�����Ʈ ���");
		System.out.println("2.��������Ʈ ���");
		System.out.println("3.���񸮽�Ʈ ���");
		System.out.println("4.�߼���ü");
		System.out.println("5.Ÿ����ü");
		System.out.println("6.������ü");
		System.out.println();
		
	} // loginUI
	
	public static void StoreUI(){
		
		System.out.println("KBO������ ���Ű� ȯ���մϴ�.");
		System.out.println("�������Ž� �Ʒ��� ���� ����� �Ҹ�˴ϴ�.");
		System.out.println("A��� : 10000��, B��� : 3000��, C��� : 1000��");
		System.out.println("0.�������� ���ư��ϴ�.");
		System.out.println("1.�߼�����");
		System.out.println("2.��������");
		System.out.println();
		
	} // loginUI
	
} //class
