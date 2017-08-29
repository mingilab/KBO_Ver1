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
			System.out.print("�۾������ϼ���>>");
			select = sc.nextInt();
			switch (select){
			case 0:
				System.out.print("�ҳ������� �����մϴ�.");
				System.exit(0);
			
			case 1:
				sc.nextLine();
				System.out.print("ID�� �Է��� �ּ���");
				String user_id = sc.nextLine();
				System.out.println("PW�� �Է��� �ּ���");
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
				System.out.println("�߸� �����ϼ̽��ϴ�.");
				break;
				
			} //switch
		}// while		
	} // execute
} // class
