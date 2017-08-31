package co.kr.mingilab.services;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.HitterInformationDAO;
import co.kr.mingilab.dao.HitterStorageDAO;
import co.kr.mingilab.dao.PitcherInformationDAO;
import co.kr.mingilab.dao.PitcherStorageDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.HitterInformationDTO;
import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.util.Probability;

public class Buy {
	
	Map<String, Double> map0 = Probability.MapRandom("HITTERID","HITTERCHOICE0", "hitter");
	Map<String, Double> map1 = Probability.MapRandom("PITCHERID","PITCHERCHOICE0", "pitcher");
	
	public void Hitter(AccountDTO accountdto){
		
		Scanner sc = new Scanner(System.in);
		int ykey;
		int select;
		String playerid;
		HitterInformationDTO hdtoarr[];
		Random rand;
		
		System.out.printf("번호      타자ID\t 이름\t 구단\t 등급\t 타율\t ops\n");
		hdtoarr = new HitterInformationDTO[10];
		for(int i=0 ; i<=9 ; i++){
			rand = new Random();
			playerid = Probability.getWeightedRandom(map0, rand);
			hdtoarr[i] = HitterInformationDAO.Print(Integer.parseInt(playerid));
			System.out.printf("%d\t",i);
			System.out.printf("%s\t",hdtoarr[i].getPlayerid());
			System.out.printf("%s\t",hdtoarr[i].getName());
			System.out.printf("%s\t",hdtoarr[i].getClub());
			System.out.printf("%c\t",hdtoarr[i].getGrade());
			System.out.printf("%4.3f\t",hdtoarr[i].getAvg());
			System.out.printf("%4.3f\t",hdtoarr[i].getOps());
			System.out.println();
		}
		
		System.out.println();
		System.out.println("구입할 선수를 선택해 주세요.");
		select = sc.nextInt();
		
		if(0<=select && select <=9){
			System.out.println(hdtoarr[select].getName()+"선수를 구입하시겠습니까?");
			System.out.println("1번을 눌러주세요. 다른키를 누르면 메인화면으로 돌아갑니다.");
	 		ykey = sc.nextInt();
	 	
	 		if(ykey == 1){
	 			int num = AccountDAO.KrwUse(accountdto, hdtoarr[select].getGrade());
	 			System.out.println(num+"구입가 완료되었습니다.");
	 			
	 			if(num==1) HitterStorageDAO.makePlayer(accountdto.getUserid(), Integer.parseInt(hdtoarr[select].getPlayerid()));		
	 			
	 		}else System.out.println("잘못 선택하였습니다. 처음으로 돌아갑니다.");	
	 	
		}else System.out.println("잘못 선택하였습니다. 처음으로 돌아갑니다.");
			
	}
	
	public void Pitcher(AccountDTO accountdto){
		
		Scanner sc = new Scanner(System.in);
		int ykey;
		int select;
		String playerid;
		PitcherInformationDTO pdtoarr[];
		Random rand2;
				
		System.out.printf("번호      투수ID\t 이름\t 구단\t 등급\t 방어율\t 이닝\n");
		pdtoarr = new PitcherInformationDTO[10];
		for(int i=0 ; i<=9 ; i++){
			rand2 = new Random();
			playerid = Probability.getWeightedRandom(map1, rand2);
			pdtoarr[i] = PitcherInformationDAO.Print(Integer.parseInt(playerid));
			System.out.printf("%d\t",i);
			System.out.printf("%s\t",pdtoarr[i].getPlayerid());
			System.out.printf("%s\t",pdtoarr[i].getName());
			System.out.printf("%s\t",pdtoarr[i].getClub());
			System.out.printf("%c\t",pdtoarr[i].getGrade());
			System.out.printf("%3.2f\t",pdtoarr[i].getEra());
			System.out.printf("%6.3f\t",pdtoarr[i].getIp());
			System.out.println();
		}
		System.out.println();
		System.out.println("구입할 선수를 선택해 주세요.");
		select = sc.nextInt();
		
		if(0<=select && select <=9){
			System.out.println(pdtoarr[select].getName()+"선수를 구입하시겠습니까?");
			System.out.println("1번을 눌러주세요. 다른키를 누르면 메인화면으로 돌아갑니다.");
	 		ykey = sc.nextInt();
	 	
	 		if(ykey == 1){
	 			int num = AccountDAO.KrwUse(accountdto, pdtoarr[select].getGrade());
	 			System.out.println(num+"구입가 완료되었습니다.");
	 			
	 			if(num==1) PitcherStorageDAO.makePlayer(accountdto.getUserid(), Integer.parseInt(pdtoarr[select].getPlayerid()));		
	 			
	 		}else System.out.println("잘못 선택하였습니다. 처음으로 돌아갑니다.");	
	 	
		}else System.out.println("잘못 선택하였습니다. 처음으로 돌아갑니다.");
			
	}
	
} //class
