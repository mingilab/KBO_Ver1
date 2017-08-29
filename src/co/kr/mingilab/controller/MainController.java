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
				System.out.print("소녀전선을 종료합니다.");
				System.exit(0);
			
			case 1:{
				System.out.println("KBO마켓에 오신것을 환영합니다.");
				break;
			}
			case 2:{
				System.out.println("구단관리를 시작합니다.");
				break;
			}
			case 3:{
				System.out.println("경매장에 접속합니다.");
				break;
			}
			case 4:{
				System.out.println("플레이서버에 접속합니다.");
				break;
			}
		
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
				
			} //switch
			
		} //while
		
		
	}
	
}
