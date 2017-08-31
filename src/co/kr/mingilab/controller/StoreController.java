package co.kr.mingilab.controller;

import java.util.Map;
import java.util.Scanner;
import java.util.Random;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.HitterInformationDAO;
import co.kr.mingilab.dao.HitterStorageDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.HitterInformationDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.services.Buy;
import co.kr.mingilab.ui.UI;
import co.kr.mingilab.util.DBUtil;
import co.kr.mingilab.util.Probability;

public class StoreController implements ControllerInterface {
	
	AccountDTO accountdto;
	static Random rand;
	static int ykey;

	
	@Override
	public void execute(Map<String, Object> map) {
		Scanner sc = new Scanner(System.in);
		//Scanner sc1 = new Scanner(System.in);
		int select;

		accountdto = (AccountDTO) map.get("dto1");
		
		loof : while(true){
			
			UI.StoreUI();
			select = sc.nextInt();
			
			switch (select){
				case 0:
					System.out.println("메인으로 돌아갑니다.");
					break loof;
					
				case 1:
					Buy buy1 = new Buy();
					buy1.Hitter(accountdto);
					break;
					
				case 2:
					Buy buy2 = new Buy();
					buy2.Pitcher(accountdto);
					break;
					
				default :
					break;
					
			}//switch
			
		} //while
		
	} //execute
	
} //storeController
