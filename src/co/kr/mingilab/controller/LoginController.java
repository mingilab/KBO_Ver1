package co.kr.mingilab.controller;

import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.ui.UI;


public class LoginController implements ControllerInterface {
	
	AccountDTO accountdto= new AccountDTO();
	
	@Override
	public void execute(Map<String, Object> map) {
			
		int select;
		Scanner sc = new Scanner(System.in);
		while(true){
			UI.loginUI();
			System.out.print("작업선택하세요>>");
			select = sc.nextInt();
			switch (select){
			case 0:
				System.out.print("소녀전선을 종료합니다.");
				System.exit(0);
			
			case 1:
				sc.nextLine();
				System.out.print("ID를 입력해 주세요");
				String user_id = sc.nextLine();
				System.out.println("PW를 입력해 주세요");
				String user_pw = sc.nextLine();
				
				accountdto = AccountDAO.userlogin(user_id, user_pw);
				map.put("dto1", accountdto);
				
				if(accountdto!=null){
					MainController start = new MainController();
					start.execute(map);
				//	if(sc!=null) sc.close();
				//	return;
				}
				break;
				
			case 2:
				AccountDAO usermake = new AccountDAO();
				usermake.makeUser();
				usermake = null;
				break;
				
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
				
			} //switch
		}// while		
	} // execute
} // class
