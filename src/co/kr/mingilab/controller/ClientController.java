package co.kr.mingilab.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import co.kr.mingilab.dao.AccountDAO;
import co.kr.mingilab.dao.HitterListDAO;
import co.kr.mingilab.dao.PitcherListDAO;
import co.kr.mingilab.dao.UserExpDAO;
import co.kr.mingilab.dao.pitcherGameDAO;
import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.dto.HitterListDTO;
import co.kr.mingilab.dto.PitcherListDTO;
import co.kr.mingilab.dto.PitcherInformationDTO;
import co.kr.mingilab.dto.PitcherStorageDTO;
import co.kr.mingilab.interfaces.ControllerInterface;
import co.kr.mingilab.ui.UI;
import kbo.View.kboView;

public class ClientController implements ControllerInterface {
	static int score_mine = 0;
	static int score_yours = 0;
	static int[] SBO = {0,0,0};
	static int[] Base = new int[3];
	static int inning = 1;
	static int turn = 1;
	static int bat_order = 1;
	static int win = 0;
	
	static int score_mineh = 0;
	static int score_yoursh = 0;
	static int[] SBOh = {0,0,0};
	static int[] Baseh = new int[3]; //투수, 타자 각자 java file에서 동기화처럼 구현
	static int inningh = 1;
	static int turnh = 1;
	static int bat_orderh = 1;
	static int winh = 0;
	
	
	
	static AccountDTO accountdto;
	int exptable[] = UserExpDAO.User_exptable();
	Socket client = null;
	
	@Override
	public void execute(Map<String, Object> map) {
				
		Scanner sc = new Scanner(System.in);
		int select;
		PitcherListDTO[] st2 = null;
		HitterListDTO[] st = null;
		AccountDTO[] st3 = null;
		
		
		HitterListDAO daoHit = new HitterListDAO();
	
		PitcherListDAO daoPit = new PitcherListDAO();
		AccountDAO daoAcct = new AccountDAO();
		
		while(true){
			
			accountdto = AccountDAO.userload((AccountDTO) map.get("dto1"));
			UI.mainUI(accountdto, exptable);
			select = sc.nextInt();
			
			switch (select){
			case 0:
				System.out.print("KBO를 종료합니다.");
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
				
				System.out.println("투수로 플레이를 시작합니다.");
				
				System.out.println("******** KBO 야구게임 *******");
				System.out.println("********   투수 버전   *******");
				System.out.printf("\n[%d]inning 시작!\n\n", inning);
				System.out.println("\n[승리조건] 5점 미만 실점하기\n");
				int[] pitchA = { 70, 27, 3 };
				int[] pitchB = { 60, 35, 5 };
				int[] pitchC = { 55, 38, 7 };
			
			//	int i = 0;

				
				
				String userId =accountdto.getUserid();
				st2 = daoPit.StaringOrderpGet(userId);
				kboView.printPitcher(st2);
				
				
				//int pitchSeq=104;
				PitcherListDTO st1dto=null;
				st1dto =daoPit.thisPitch(userId);
				kboView.printPitcherChoice(st1dto);
				
				
				//st2 =daoPit.thisPitcherGrade(userId, pitchSeq);
				
				Scanner input = new Scanner(System.in);
				//int i = 0;

				
				
				char card= st1dto.getGrade();
				
				
				System.out.println("=========================================");
				System.out.println("투수 등급 카드를 선택하시오. A/B/C");
				
				
				switch (card) {
				case 'A': // String A = Arrays.toString(pitchA);
					showCard(card, pitchA);

					break;

				case 'B':
					// String B = Arrays.toString(pitchB);
					showCard(card, pitchB);
					break;

				case 'C':
					// String C = Arrays.toString(pitchC);
					showCard(card, pitchC);

				default:
					System.out.println("해당 종류는 없습니다.");
					break;

				}

				while (true) {
					try {
						InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
						client = new Socket();

						client.connect(ipep);
						System.out.println("=========================================");
						System.out.printf("상대타자 : [%d]번 타자\n\n", bat_order);
						if(bat_order == 4){
							System.out.println("4번타자입니다. 상대 strike를 swing시 홈런을 맞을 수 있습니다.");
						}
						System.out.println();
						System.out.println();
						System.out.println("공을 선택하여 던지세요.");
						System.out.print("strike / ball / sagu >>");
						String choice;
						choice = input.next();

						switch (choice) {

						case "strike":
							if (card == 'A') {
								pickCard1(pitchA);
							} else if (card == 'B') {
								pickCard1(pitchB);
							} else if (card == 'C') {
								pickCard1(pitchC);
							}
							break;

						case "ball":
							if (card == 'A') {
								pickCard2(pitchA);
							} else if (card == 'B') {
								pickCard2(pitchB);
							} else if (card == 'C') {
								pickCard2(pitchC);

							}
							break;

						case "sagu":
							if (card == 'A') {
								pickCard3(pitchA);
							} else if (card == 'B') {
								pickCard3(pitchB);
							} else if (card == 'C') {
								pickCard3(pitchC);
							}
							break;

						default:
							System.out.println("해당 종류는 없습니다.");
							continue;
						}


						OutputStream sender = client.getOutputStream();
						InputStream receiver = client.getInputStream();
						// 서버로부터 데이터 받기
						byte[] data = new byte[256];
						int SIZE = 256;
						receiver.read(data, 0, SIZE);

						// 수신메시지 출력
						String message_recv = new String(data);
						System.out.println("=========================================");
						String out = String.format("[상대 선택]: %s", message_recv);
						System.out.println(out);


						// 서버로 데이터 보내기
						String message_send = choice;
						data = message_send.getBytes();
						sender.write(data, 0, data.length);
						sender.flush();

						if (message_recv.trim().equals("swing") && message_send.trim().equals("strike")) {
							if(bat_order == 4){
								System.out.println("[타자]4번 타자가 제 역할을 했다!! ->홈런!!");
								homeRun(Base);
							}
							else{
								System.out.println("[투수]strike를 던졌는데 swing맞았다. -> 상대 출루!");
								baseRun(Base);
							}
							showBaseplate(Base);
							clear_SB(SBO);
							show_SBO(SBO);
							plus_bat_order();

						} 

						else if (message_recv.trim().equals("swing") && message_send.trim().equals("ball")) {
							System.out.println("[투수]ball을 던졌는데 swing맞았다. -> 스트라이크!!");
							stk_plus(SBO);
							if( check_end(SBO) == true ){
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***승리하셨습니다!!*******");
									System.out.printf("[%d]점 실점, 승리!\n", score_yours);
									System.out.println("[돈+700,경험치+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******패배하셨습니다...********");
									System.out.printf("[%d]점 실점, 패배!\n", score_yours);
									System.out.println("[돈-500,경험치-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("게임 종료");
								break;
							}
							show_SBO(SBO);
						} 

						else if (message_recv.trim().equals("noswing") && message_send.trim().equals("strike")) {
							System.out.println("[투수]strike를 던졌는데 noswing했다. -> 스트라이크!!");
							stk_plus(SBO);
							if( check_end(SBO) == true ){
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***승리하셨습니다!!*******");
									System.out.printf("[%d]점 실점, 승리!\n", score_yours);
									System.out.println("[돈+700,경험치+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******패배하셨습니다...********");
									System.out.printf("[%d]점 실점, 패배!\n", score_yours);
									System.out.println("[돈-500,경험치-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("게임 종료");
								break;
							}
								
							show_SBO(SBO);
						} 

						else if (message_recv.trim().equals("noswing") && message_send.trim().equals("ball")) {
							System.out.println("[투수]ball을 던졌는데 noswing했다. -> 볼~~~");
							ball_plus(SBO);
							check_end(SBO);
							show_SBO(SBO);
							showBaseplate(Base);
						} 

						else if (message_recv.trim().equals("bunt") && message_send.trim().equals("strike")) {
							System.out.println("[투수]strike 던졌는데 bunt맞았다. -> 상대 필드플레이!!");
							strike_bunt_fieldplay(Base);
							clear_SB(SBO);
							if( check_end(SBO) == true ){ //fieldplay갔으면 str올라갈일없음
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***승리하셨습니다!!*******");
									System.out.printf("[%d]점 실점, 승리!\n", score_yours);
									System.out.println("[돈+700,경험치+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******패배하셨습니다...********");
									System.out.printf("[%d]점 실점, 패배!\n", score_yours);
									System.out.println("[돈-500,경험치-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("게임 종료");
								break;
							}
							show_SBO(SBO);
							showBaseplate(Base);
						} 

						else if (message_recv.trim().equals("bunt") && message_send.trim().equals("ball")) {
							System.out.println("[투수]ball을 던졌는데 bunt맞았다. -> 상대 볼 상황 필드플레이!!");
							ball_bunt_fieldplay(Base);
							clear_SB(SBO);
							if( check_end(SBO) == true ){ //fieldplay갔으면 str올라갈일없음
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***승리하셨습니다!!*******");
									System.out.printf("[%d]점 실점, 승리!\n", score_yours);
									System.out.println("[돈+700,경험치+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******패배하셨습니다...********");
									System.out.printf("[%d]점 실점, 패배!\n", score_yours);
									System.out.println("[돈-500,경험치-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("게임 종료");
								break;
							}
							show_SBO(SBO);
							showBaseplate(Base);

						}
						
						else if (message_send.trim().equals("sagu")) {
							System.out.println("[투수]사구 던짐! 실수를 해버렸다... -> 상대 출루!");
							baseRun(Base);
							clear_SB(SBO);
							show_SBO(SBO);
							showBaseplate(Base);
							plus_bat_order();

						}

					} catch (IOException e) {
						e.printStackTrace();
					}/*finally{
						try {
							client.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/

				}

			}
			
			
			
			}}}
		/*
								int result = 1;
							if(result==1){
								System.out.println("***승리하셨습니다!!*******");
								System.out.println("[돈+700,경험치+100]");
								AccountDAO.WinUpdate();}//승리결과 입력(승+1,돈+700,경험치+100)
								else if(result==0){
								System.out.println("*******패배하셨습니다...********");
								System.out.println("[돈-500,경험치-10]");
								AccountDAO.LossUpdate();//패배결과 입력(패+1,돈-500,경험치-10)
						
								}
							} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								
							}
						
				
				
				*/
				
				
				
			
 //switch
	
		//while
		

	
	
	
static void win_lose(){
		
		if(score_yours < 5) //5점 미만실점했다면[투수]
			win = 1;
		
	}

	static void clear_SB(int[] SBO){ //출루시 SB초기화
		SBO[0] = SBO[1] = 0;
	}

	static void show_SBO(int[] SBO){
		System.out.println("[Strike : " + SBO[0] +  " Ball : " + SBO[1] + " Out : " + SBO[2]+"]");
	}
	static void stk_plus(int[] SBO){
		SBO[0]++;
	}
	static void ball_plus(int[] SBO){
		SBO[1]++;
	}
	static void out_plus(int[] SBO){
		SBO[2]++;
	}
	static void print_inning(){ //경기 종료시에만 호출해야 됨
		System.out.printf("[%d] inning 종료\n", inning);
		inning++;
	}

	static void all_clear(){
		SBO[0] = SBO[1] = SBO[2] = 0;
		Base[0] = Base[1] = Base[2] = 0;
	}

	static void change_turn(){
		if(turn == 1)
			turn = 0;
		else
			turn = 1;
	}
	
	static void plus_bat_order(){
		if(bat_order<9)
			bat_order++;
		else
			bat_order = 1;
	}
	
	static void ball_bunt_fieldplay(int[] Base) { //bunt 상황중 실패상황 !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			out_plus(SBO); 
			plus_bat_order();
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,2루 병살!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,3루 병살!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1루, 홈 병살!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[0] = 0; //새주자 잡음
			Base[1] = 0; //1->2루 가는 주자 잡음
			Base[2] = 1;
			System.out.println("1,2루 병살!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 1; 
			Base[1] = 1; 
			Base[2] = 0;
			System.out.println("홈주자 잡음, 병살은 실패, out count 1증가");
			out_plus(SBO);
			plus_bat_order();
			

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황 -> 1,3루
			Base[0] = 1; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("홈주자 잡음, 병살은 실패, out count 1증가");
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//만루 -> 홈, 1루 병살
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("홈 , 1루 out");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();
		}

	}
	
	static void strike_bunt_fieldplay(int[] Base) { //bunt 상황중 성공상황 !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //1루 진루
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_order();
			
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우
			Base[0] = 0;
			Base[1] = 1; //2루로 진루, 타자는 out
			Base[2] = 0;
			System.out.println("2루 진루, 타자out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3루 진루, 타자out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("홈인, 타자 out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1; //2,3루 진루, 타자out
			System.out.println("2,3루 진루, 타자 out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 0; 
			Base[1] = 1; //2루진루, 홈인, 타자out
			Base[2] = 0;
			System.out.println("홈인, 2루 진루, 타자out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();
			

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황 
			Base[0] = 0; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("홈인, 3루진루, 타자out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//만루 -> 2,3루 + 1득점, 얘는 성공경우
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("홈인, 2,3루진루, 타자out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();
		}

	}

	static boolean check_end(int[] SBO){
		//check순서가 중요하다

		boolean is_change_turn = false; //리턴시켜서 true일 경우에 while brake로 빠져나가 턴 바꾼다
		if(SBO[0] >= 3){ //stk count = 3
			SBO[0]  = SBO[0] - 3; //stk count 초기화
			System.out.println("3stk -> out count 증가");
			plus_bat_order(); //out되면 다음타자
			SBO[2]++; //out count 증가
		}

		if(SBO[2] >= 3){ //end하는 경우
			SBO[2] = 0; //out count초기화
			print_inning();
			all_clear();
			change_turn();
			is_change_turn = true;
		}

		if(SBO[1] >= 4){
			System.out.println("볼넷 출루 !");
			clear_SB(SBO);
			baseRun(Base); //출루
			showBaseplate(Base);
		}
		
		return is_change_turn;
	}

	static void showBaseplate(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1루 : " + b[0] + "  2루 : " + b[1] + "  3루 : " + b[2] + ", 내 점수 : " 
				+ score_mine + ", 상대점수 : "+ score_yours + "]");
	}

	static void showCard(char card, int[] Hit) {
		System.out.println();
		System.out.println("-선택된 카드:" + card);
		System.out.println("-스트라이크 : " + Hit[0] + " 볼 : " + Hit[1] + " 사구 : " + Hit[2]);
	}

	static void pickCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-남은 스트라이크 : " + Hit[0] + " 남은 볼 : " + Hit[1] + " 남은 사구 : " + Hit[2]);
	}

	static void pickCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-남은 스트라이크 : " + Hit[0] + " 남은 볼 : " + Hit[1] + " 남은 사구 : " + Hit[2]);
	}

	static void pickCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-남은 스트라이크 : " + Hit[0] + " 남은 볼 : " + Hit[1] + " 남은 사구 : " + Hit[2]);
	}

	static void baseRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //주자 없을경우 1루로

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우 1루주자 -> 2루 // 새주자 -> 1루
			Base[1] = 1;//기존 1루주자
			Base[0] = 1;//새주자

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 0; // 2루는 비게됨
			Base[2] = 1; // 2루주자 -> 3루

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[0] = 1; //새주자 -> 1루
			Base[2] = 0; // 3루주자 -> 홈
			score_yours++; // 1득점


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[2] = 1; // 2루주자 -> 3루
			Base[1] = 1; // 1루주자 -> 2루
			Base[0] = 1; // 새주자 -> 1루

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 1; // 1루 -> 2루
			Base[2] = 0; // 3루는 비게됨
			score_yours++; // 3루주자 -> 1득점

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 0; // 2루는 비게됨
			Base[2] = 1; // 2루주자 -> 3루
			score_yours++; // 3루주자 -> 1득점

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//만루 -> 2,3루 + 1득점
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 1;
			score_yours++;
		}

	}

	static void homeRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine++;

		} else if (Base[0] != 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=2;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=2;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=3;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=3;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_mine +=4;
		}

	}

	
	
	
	
	
	
	

	static void win_loseh(){
		if(score_mineh >= 5) //5점 이상 득점했다면[타자]
			winh = 1;
	}

	static void clear_hSB(int[] SBO){ //출루시 SB초기화
		SBO[0] = SBO[1] = 0;
	}

	static void show_hSBO(int[] SBO){
		System.out.println("[Strike : " + SBO[0] +  " Ball : " + SBO[1] + " Out : " + SBO[2]+"]");
	}
	static void stk_hplus(int[] SBO){
		SBO[0]++;
	}
	static void ball_hplus(int[] SBO){
		SBO[1]++;
	}
	static void out_hplus(int[] SBO){
		SBO[2]++;
	}
	static void print_hinning(){ //경기 종료시에만 호출해야 됨
		System.out.printf("[%d] inning 종료\n", inningh);
		inningh++;
	}

	static void plus_bat_horder(){
		if(bat_orderh<9)
			bat_orderh++;
		else
			bat_orderh = 1;
	}

	static void all_hclear(){
		SBOh[0] = SBOh[1] = SBOh[2] = 0;
		Baseh[0] = Baseh[1] = Baseh[2] = 0;
	}

	static void change_hturn(){
		if(turnh == 1)
			turnh = 0;
		else
			turnh = 1;
	}
	static boolean check_hend(int[] SBO){
		//check순서가 중요하다
		boolean is_change_turn = false;
		if(SBO[0] >= 3){ //stk count = 3
			SBO[0]  = SBO[0] - 3; //stk count 초기화
			System.out.println("3stk -> out count 증가");
			plus_bat_horder();
			SBO[2]++; //out count 증가
		}

		if(SBO[2] >= 3){
			SBO[2] = 0; //out count초기화
			print_hinning();
			all_hclear();
			change_hturn();
			is_change_turn = true;
		}

		if(SBO[1] >= 4){
			System.out.println("볼넷 출루 !");
			clear_hSB(SBO);
			basehRun(Baseh); //출루
			showBaseplateh(Baseh);
		}

		return is_change_turn;
	}

	static void showBaseplateh(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1루 : " + b[0] + "  2루 : " + b[1] + "  3루 : " + b[2] + ", 내 점수 : " 
				+ score_mineh + ", 상대점수 : "+ score_yoursh + "]");
	}

	static void showCardh(char card, int[] Hit) {
		System.out.println();
		System.out.println("-선택된 카드:" + card);
		System.out.println("-휘두른다:" + Hit[0] + ",안 휘두른다:" + Hit[1] + ",번트:" + Hit[2]);
	}

	static void pickhCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-남은 휘두른다:" + Hit[0] + ",남은 안 휘두른다:" + Hit[1] + ",남은 번트:" + Hit[2]);
	}

	static void pickhCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-남은 휘두른다:" + Hit[0] + ",남은 안 휘두른다:" + Hit[1] + ",남은 번트:" + Hit[2]);
	}

	static void pickhCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-남은 휘두른다:" + Hit[0] + ",남은 안 휘두른다:" + Hit[1] + ",남은 번트:" + Hit[2]);
	}

	static void basehRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //주자 없을경우 1루로

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우 1루주자 -> 2루 // 새주자 -> 1루
			Base[1] = 1;//기존 1루주자
			Base[0] = 1;//새주자

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 0; // 2루는 비게됨
			Base[2] = 1; // 2루주자 -> 3루

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[0] = 1; //새주자 -> 1루
			Base[2] = 0; // 3루주자 -> 홈
			score_mineh++; // 1득점


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[2] = 1; // 2루주자 -> 3루
			Base[1] = 1; // 1루주자 -> 2루
			Base[0] = 1; // 새주자 -> 1루

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 1; // 1루 -> 2루
			Base[2] = 0; // 3루는 비게됨
			score_mineh++; // 3루주자 -> 1득점

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황
			Base[0] = 1; // 새주자 -> 1루
			Base[1] = 0; // 2루는 비게됨
			Base[2] = 1; // 2루주자 -> 3루
			score_mineh++; // 3루주자 -> 1득점

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//만루 -> 2,3루 + 1득점
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 1;
			score_mineh++;
		}

	}

	static void ball_bunt_hfieldplay(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			out_hplus(SBOh); 
			plus_bat_horder();
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우
			Base[0] = 0;
			System.out.println("1,2루 병살!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[1] = 0;
			System.out.println("1,3루 병살!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[2] = 0;
			System.out.println("1루, 홈 병살!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[0] = 0; //새주자 잡음
			Base[1] = 0; //1->2루 가는 주자 잡음
			Base[2] = 1;
			System.out.println("1,2루 병살!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 1; 
			Base[1] = 1; 
			Base[2] = 0;
			System.out.println("홈주자 잡음, 병살은 실패, out count 1증가");
			out_hplus(SBOh);

			plus_bat_horder();
			
		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황  / 1,3루
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("홈in , 병살은 실패, out count 1증가, 새주자는 잡힘");
			score_mineh++;
			out_hplus(SBOh);
			plus_bat_horder();
		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1;
			System.out.println("홈주자 잡음, 병살은 실패, out count 1증가");
			out_hplus(SBOh);
			plus_bat_horder();
		}

	}

	static void strike_bunt_hfieldplay(int[] Base) { //bunt 상황중 성공상황 !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //1루 진루
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_horder();
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//주자 1루에만 있는경우
			Base[0] = 0;
			Base[1] = 1; //2루로 진루, 타자는 out
			Base[2] = 0;
			System.out.println("2루 진루, 타자out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//주자 2루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3루 진루, 타자out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//주자 3루에 한명 있는경우
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("홈인, 타자 out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//주자 1,2루 상황
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1; //2,3루 진루, 타자out
			System.out.println("2,3루 진루, 타자 out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//주자 1,3루 상황
			Base[0] = 0; 
			Base[1] = 1; //2루진루, 홈인, 타자out
			Base[2] = 0;
			System.out.println("홈인, 2루 진루, 타자out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();


		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//주자 2,3루 상황 
			Base[0] = 0; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("홈인, 3루진루, 타자out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//만루 -> 2,3루 + 1득점, 얘는 성공경우
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("홈인, 2,3루진루, 타자out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();
		}

	}

	static void homeRunh(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh++;

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=2;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=2;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=3;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=3;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //플레이트 초기화
			score_yoursh +=4;
		}

	}
	
	
	
}
