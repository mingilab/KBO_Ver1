package kbo.View;

import java.util.List;

import co.kr.mingilab.dto.HitterListDTO;
import co.kr.mingilab.dto.PitcherListDTO;
import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.dto.PitcherStorageDTO;

public class kboView {
	public static void printHitter(HitterListDTO[] list){
		System.out.println("--------------------타자 목록 ---------------");
		
		if(list.length ==0){
			System.out.println("조회된 데이터 없음");
			return;
		}
		
		for(int i=1 ; i<=13 ; i++){
			System.out.println(list[i]);
		}
		/*
		for(HitterListDTO2 st: list){
			System.out.println(st);
			
			*/
		
	}
	
	public static void printPitcher(PitcherListDTO[] list){
		System.out.println("--------------------투수 목록 ---------------");
		
		if(list.length ==0){
			System.out.println("조회된 데이터 없음");
			return;
		}
		
		for(int i=1 ; i<=7 ; i++){
			System.out.println(list[i]);
		}
		/*
		for(HitterListDTO2 st: list){
			System.out.println(st);
			
			*/
		
	}
	
		public static void printHitterChoice(HitterListDTO[] list){
			System.out.println("--------------------선택 타자 ---------------");
			
			if(list.length ==0){
				System.out.println("조회된 데이터 없음");
				return;
			}
			
			for(int i=1 ; i<=1 ; i++){
				System.out.println(list[i]);
			}
				
				
			}
		
		
		public static void printPitcherChoice(PitcherListDTO dto){
			System.out.println("--------------------선발 투수 ---------------");
			
			
				System.out.println(dto);
		
			}
	
	
	public static void print(List<PitcherStorageDTO> list){
		System.out.println("----------------------------------------------------------------");
	
		if(list.size() ==0){
			System.out.println("조회된 데이터 없음");
			return;
		}
		
		for(PitcherStorageDTO st: list){
			System.out.println(st);}
			
			
		}
		
		
	public static void print2(List<PitcherInformationDTO> list){
		System.out.println("----------------------------------------------------------------");
		System.out.println();
		
		if(list.size() ==0){
			System.out.println("조회된 데이터 없음");
			return;
		}
		
		for(PitcherInformationDTO st: list){
			System.out.println(st);}
			
			
		}
		

	
	

	public static void print(PitcherStorageDTO st){
		System.out.println("---------------------------------------KBO <선택된 투수> 정보-----------------------------------------");
		System.out.println();
		
			System.out.println(st);	
		}
	
	
	
	public static void print(String msg){
		System.out.println("알림:"+msg);
		
		}


	public static char printHitterGrade(HitterListDTO[] list) {
		
		
		System.out.println("----------------선택 타자의 등급 ---------------");
		
		if(list.length ==0){
			System.out.println("조회된 데이터 없음");
		}
		
		for(int i=1 ; i<=1 ; i++){
			System.out.println(list[i].getGrade());
			char Card = list[i].getGrade();
			return Card;
		}
		
		return 0;
		
	
		
	}


public static char printPitcherGrade(PitcherListDTO[] list) {
		
		
		System.out.println("----------------선택 투수의 등급 ---------------");
		
		if(list.length ==0){
			System.out.println("조회된 데이터 없음");
		}
		
		for(int i=1 ; i<=1 ; i++){
			System.out.println(list[i].getGrade());
			char Card = list[i].getGrade();
			return Card;
		}
		
		return 0;
		
	
		
	}


	
}
	