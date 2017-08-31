package co.kr.mingilab.controller;

import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.HitterListDAO;
import co.kr.mingilab.dao.HitterStorageDAO;
import co.kr.mingilab.dao.UserExpDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.HitterListDTO;
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
				System.out.print("KBO리그를 종료합니다.");
				System.exit(0);
			
			case 1:{
				StoreController start = new StoreController();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			case 2:{
				ManagerControler start = new ManagerControler();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			
			case 3:{
				System.out.println("타자플레이를 시작합니다(서버)");
				ServerController start = new ServerController();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			case 4:{
				System.out.println("투수플레이를 시작합니다(클라이언트)");
				ClientController start = new ClientController();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
				
			} //switch
			
		} //while
		
		
	}
	
}
