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
				System.out.print("KBO���׸� �����մϴ�.");
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
				System.out.println("Ÿ���÷��̸� �����մϴ�(����)");
				ServerController start = new ServerController();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			case 4:{
				System.out.println("�����÷��̸� �����մϴ�(Ŭ���̾�Ʈ)");
				ClientController start = new ClientController();
				map.put("dto1", accountdto);
				start.execute(map);
				break;
			}
			
			default:
				System.out.println("�߸� �����ϼ̽��ϴ�.");
				break;
				
			} //switch
			
		} //while
		
		
	}
	
}
