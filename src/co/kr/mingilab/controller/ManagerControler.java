package co.kr.mingilab.controller;

import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.DefenseListDAO;
import co.kr.mingilab.dao.HitterListDAO;
import co.kr.mingilab.dao.PitcherListDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.ui.UI;

public class ManagerControler implements ControllerInterface {
	
	AccountDTO accountdto;
	
	@Override
	public void execute(Map<String, Object> map) {
		
		Scanner sc = new Scanner(System.in);
		int select;
		accountdto = (AccountDTO) map.get("dto1");
		
		loof : while(true){
			
			UI.MangerUI();
			select = sc.nextInt();
			
			switch (select){
				case 0:
					System.out.print("메인화면으로 돌아갑니다.");
					break loof;
					
				case 1:	
					HitterListDAO.StaringlistPrint(accountdto.getUserid());
					break;
				
				case 2:	
					PitcherListDAO.StaringlistPrint(accountdto.getUserid());
					break;
									
				case 3:
					DefenseListDAO.listPrint(accountdto.getUserid());
					break;
					
				case 4:
					DefenseListDAO.StaringlistPrint(accountdto.getUserid());
					break;
					
				default :
					System.out.println("잘못입력하였습니다.");
					break;
					
			} //switch;
			
		}//while
		
	}//excute
		
} //class
