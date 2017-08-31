package co.kr.mingilab.services;

import java.util.ArrayList;
import java.util.Scanner;

import co.kr.mingilab.dao.DefenseListDAO;
import co.kr.mingilab.dao.HitterStorageDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.DefenseListDTO;
	
public class Change {
	
	static int select;
	static int count;
	static int temp;
	
	public static void Hitter(AccountDTO accountdto){
		
		Scanner sc = new Scanner(System.in);
		int ykey;
		count = 0;
		temp = 0;
		ArrayList<DefenseListDTO> ddtoalist = new ArrayList<DefenseListDTO>();
		DefenseListDTO[] ddtolist = new DefenseListDTO[10];
		ddtolist = DefenseListDAO.StaringlistPrint(accountdto.getUserid());
		System.out.printf("��ȣ      Ÿ��ID\t �̸�\t ����\t ������\t ���\t Ÿ��\t \n");
		for(int i=1 ; i<=9 ; i++){
			//��ü��� ���� ddtolist[i]�� ��ﺯ��
			
			System.out.printf("%d\t",i);
			System.out.printf("%d\t",ddtolist[i].getPlayerid());
			System.out.printf("%s\t",ddtolist[i].getName());
			System.out.printf("%s\t",ddtolist[i].getClub());
			System.out.printf("%s\t",ddtolist[i].getSposition());
			System.out.printf("%s\t",ddtolist[i].getGrade());
			System.out.printf("%s\t",ddtolist[i].getBattingorder());
			System.out.println();
		}
		
		System.out.println();
		System.out.println("��ü�� ������ ������ �ּ���");
		select = sc.nextInt();
		temp = select;
		if(1<=select && select <=9){
			
			System.out.println(ddtolist[select].getName()+"������ ��ü�մϴ�.");
			
			ddtoalist = DefenseListDAO.backuplistPrint(accountdto.getUserid(), ddtolist[select].getSposition());
			System.out.println(ddtoalist.size());
			System.out.printf("��ȣ      Ÿ��ID\t �̸�\t ����\t ������\t ���\t Ÿ��\t \n");
			
			for(int i=0 ; i<ddtoalist.size() ; i++){
				System.out.printf("%d\t",i);
				System.out.printf("%d\t",ddtoalist.get(i).getPlayerid());
				System.out.printf("%s\t",ddtoalist.get(i).getName());
				System.out.printf("%s\t",ddtoalist.get(i).getClub());
				System.out.printf("%s\t",ddtoalist.get(i).getPosition());
				System.out.printf("%s\t",ddtoalist.get(i).getGrade());
				System.out.printf("%d",ddtoalist.get(i).getBattingorder());
				System.out.println();
			}
			
			System.out.println("������ ������ ������ �ּ���.");
									
			select = sc.nextInt();
			if(0<=select && select <= ddtoalist.size()){
				System.out.println(ddtoalist.get(select).getName()+"������ �����մϴ�.");
				/*
				int player1seq = ddtolist[temp].getHitterseq();
				String player1pos = ddtolist[temp].getSposition();
				int player1order = ddtolist[temp].getBattingorder();
				
				int player2seq = ddtoalist.get(select).getHitterseq();
				String player2pos = ddtoalist.get(select).getSposition();
				int player2order = ddtoalist.get(select).getBattingorder();
				*/													
				count += HitterStorageDAO.changePlayer(ddtolist[temp], ddtoalist.get(select));
				count += HitterStorageDAO.changePlayer(ddtoalist.get(select), ddtolist[temp]);									
			}
			
			System.out.println(count + "������ü�� �Ϸ�Ǿ����ϴ�.");
			return;
			
		}else{
			System.out.println("�߸� �Է��Ͽ����ϴ�. ó������ ���ư��ϴ�.");
			return;
		}
		
	} //hitter
	
} //class
