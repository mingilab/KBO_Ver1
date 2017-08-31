package co.kr.mingilab.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.DefenseListDAO;
import co.kr.mingilab.dao.HitterListDAO;
import co.kr.mingilab.dao.HitterStorageDAO;
import co.kr.mingilab.dao.PitcherListDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.DefenseListDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.ui.UI;

public class ManagerControler implements ControllerInterface {
	
	AccountDTO accountdto;
	
	@Override
	public void execute(Map<String, Object> map) {
		
		Scanner sc = new Scanner(System.in);
		int select;
		int count = 0;
		int temp = 0;
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
					int ykey;
					ArrayList<DefenseListDTO> ddtoalist = new ArrayList<DefenseListDTO>();
					DefenseListDTO[] ddtolist = new DefenseListDTO[10];
					ddtolist = DefenseListDAO.StaringlistPrint(accountdto.getUserid());
					System.out.printf("번호      타자ID\t 이름\t 구단\t 포지션\t 등급\t 타순\t \n");
					for(int i=1 ; i<=9 ; i++){
						//교체대상 선수 ddtolist[i]값 기억변수
						
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
					System.out.println("교체할 선수를 선택해 주세요");
					select = sc.nextInt();
					temp = select;
					if(1<=select && select <=9){
						
						System.out.println(ddtolist[select].getName()+"선수를 교체합니다.");
						ddtoalist = DefenseListDAO.backuplistPrint(accountdto.getUserid(), ddtolist[select].getPosition());
						System.out.printf("번호      타자ID\t 이름\t 구단\t 포지션\t 등급\t 타순\t \n");
						
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
						
						System.out.println("투입할 선수를 선택해 주세요.");
												
						select = sc.nextInt();
						if(0<=select && select <= ddtoalist.size()){
							System.out.println(ddtoalist.get(select).getName()+"선수를 투입합니다.");
							
							int player1seq = ddtolist[temp].getHitterseq();
							String player1pos = ddtolist[temp].getSposition();
							int player1order = ddtolist[temp].getBattingorder();
							
							int player2seq = ddtoalist.get(select).getHitterseq();
							String player2pos = ddtoalist.get(select).getSposition();
							int player2order = ddtoalist.get(select).getBattingorder();
							
							System.out.println(player1seq + "/" + player1pos + "/" + player1order);
							System.out.println(player2seq +"/"+ player2pos +"/" + player2order);
							
							count += HitterStorageDAO.changePlayer(ddtolist[temp], ddtoalist.get(select));
							count += HitterStorageDAO.changePlayer(ddtoalist.get(select), ddtolist[temp]);
						}
						
						System.out.println(count + "선수교체가 완료되었습니다.");
						break;
					}else{
						System.out.println("잘못 입력하였습니다. 처음으로 돌아갑니다.");
						break;
					}
					
				default :
					System.out.println("잘못입력하였습니다.");
					break;
					
			} //switch;
			
		}//while
		
	}//excute
		
} //class
