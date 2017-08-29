package co.kr.mingilab.controller;

import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.UserExpDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.ui.UI;

public class MainController implements ControllerInterface {
	
	AccountDTO accountdto;
	int exptable[] = UserExpDAO.User_exptable();
	
	@Override
	public void execute(Map<String, Object> map) {
				
		Scanner sc = new Scanner(System.in);
		int select;
		
		while(true){
			
			accountdto = AccountDAO.userload((AccountDTO) map.get("dto1"));
			UI.mainUI(accountdto, exptable);
			select = sc.nextInt();
			
			switch (select){
			case 0:
				System.out.print("�ҳ������� �����մϴ�.");
				System.exit(0);
			
			case 1:{
				System.out.println("KBO���Ͽ� ���Ű��� ȯ���մϴ�.");
				break;
			}
			case 2:{
				System.out.println("���ܰ����� �����մϴ�.");
				break;
			}
			case 3:{
				System.out.println("����忡 �����մϴ�.");
				break;
			}
			case 4:{
				System.out.println("�÷��̼����� �����մϴ�.");
				break;
			}
		
			default:
				System.out.println("�߸� �����ϼ̽��ϴ�.");
				break;
				
			} //switch
			
		} //while
		
		
	}
	
}
