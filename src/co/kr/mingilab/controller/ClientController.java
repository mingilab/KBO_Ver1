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
	static int[] Baseh = new int[3]; //����, Ÿ�� ���� java file���� ����ȭó�� ����
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
				System.out.print("KBO�� �����մϴ�.");
				System.exit(0);
			
			case 1:{
				System.out.println("KBO���Ͽ� ���Ű��� ȯ���մϴ�.");
				break;
			}
			case 2:{
				System.out.println("���ܰ����� �����մϴ�.");
				break;
			}
			case 3:{
				System.out.println("����忡 �����մϴ�.");
				break;
			}
			
			case 4:{
				
				System.out.println("������ �÷��̸� �����մϴ�.");
				
				System.out.println("******** KBO �߱����� *******");
				System.out.println("********   ���� ����   *******");
				System.out.printf("\n[%d]inning ����!\n\n", inning);
				System.out.println("\n[�¸�����] 5�� �̸� �����ϱ�\n");
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
				System.out.println("���� ��� ī�带 �����Ͻÿ�. A/B/C");
				
				
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
					System.out.println("�ش� ������ �����ϴ�.");
					break;

				}

				while (true) {
					try {
						InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
						client = new Socket();

						client.connect(ipep);
						System.out.println("=========================================");
						System.out.printf("���Ÿ�� : [%d]�� Ÿ��\n\n", bat_order);
						if(bat_order == 4){
							System.out.println("4��Ÿ���Դϴ�. ��� strike�� swing�� Ȩ���� ���� �� �ֽ��ϴ�.");
						}
						System.out.println();
						System.out.println();
						System.out.println("���� �����Ͽ� ��������.");
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
							System.out.println("�ش� ������ �����ϴ�.");
							continue;
						}


						OutputStream sender = client.getOutputStream();
						InputStream receiver = client.getInputStream();
						// �����κ��� ������ �ޱ�
						byte[] data = new byte[256];
						int SIZE = 256;
						receiver.read(data, 0, SIZE);

						// ���Ÿ޽��� ���
						String message_recv = new String(data);
						System.out.println("=========================================");
						String out = String.format("[��� ����]: %s", message_recv);
						System.out.println(out);


						// ������ ������ ������
						String message_send = choice;
						data = message_send.getBytes();
						sender.write(data, 0, data.length);
						sender.flush();

						if (message_recv.trim().equals("swing") && message_send.trim().equals("strike")) {
							if(bat_order == 4){
								System.out.println("[Ÿ��]4�� Ÿ�ڰ� �� ������ �ߴ�!! ->Ȩ��!!");
								homeRun(Base);
							}
							else{
								System.out.println("[����]strike�� �����µ� swing�¾Ҵ�. -> ��� ���!");
								baseRun(Base);
							}
							showBaseplate(Base);
							clear_SB(SBO);
							show_SBO(SBO);
							plus_bat_order();

						} 

						else if (message_recv.trim().equals("swing") && message_send.trim().equals("ball")) {
							System.out.println("[����]ball�� �����µ� swing�¾Ҵ�. -> ��Ʈ����ũ!!");
							stk_plus(SBO);
							if( check_end(SBO) == true ){
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***�¸��ϼ̽��ϴ�!!*******");
									System.out.printf("[%d]�� ����, �¸�!\n", score_yours);
									System.out.println("[��+700,����ġ+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******�й��ϼ̽��ϴ�...********");
									System.out.printf("[%d]�� ����, �й�!\n", score_yours);
									System.out.println("[��-500,����ġ-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("���� ����");
								break;
							}
							show_SBO(SBO);
						} 

						else if (message_recv.trim().equals("noswing") && message_send.trim().equals("strike")) {
							System.out.println("[����]strike�� �����µ� noswing�ߴ�. -> ��Ʈ����ũ!!");
							stk_plus(SBO);
							if( check_end(SBO) == true ){
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***�¸��ϼ̽��ϴ�!!*******");
									System.out.printf("[%d]�� ����, �¸�!\n", score_yours);
									System.out.println("[��+700,����ġ+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******�й��ϼ̽��ϴ�...********");
									System.out.printf("[%d]�� ����, �й�!\n", score_yours);
									System.out.println("[��-500,����ġ-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("���� ����");
								break;
							}
								
							show_SBO(SBO);
						} 

						else if (message_recv.trim().equals("noswing") && message_send.trim().equals("ball")) {
							System.out.println("[����]ball�� �����µ� noswing�ߴ�. -> ��~~~");
							ball_plus(SBO);
							check_end(SBO);
							show_SBO(SBO);
							showBaseplate(Base);
						} 

						else if (message_recv.trim().equals("bunt") && message_send.trim().equals("strike")) {
							System.out.println("[����]strike �����µ� bunt�¾Ҵ�. -> ��� �ʵ��÷���!!");
							strike_bunt_fieldplay(Base);
							clear_SB(SBO);
							if( check_end(SBO) == true ){ //fieldplay������ str�ö��Ͼ���
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***�¸��ϼ̽��ϴ�!!*******");
									System.out.printf("[%d]�� ����, �¸�!\n", score_yours);
									System.out.println("[��+700,����ġ+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******�й��ϼ̽��ϴ�...********");
									System.out.printf("[%d]�� ����, �й�!\n", score_yours);
									System.out.println("[��-500,����ġ-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("���� ����");
								break;
							}
							show_SBO(SBO);
							showBaseplate(Base);
						} 

						else if (message_recv.trim().equals("bunt") && message_send.trim().equals("ball")) {
							System.out.println("[����]ball�� �����µ� bunt�¾Ҵ�. -> ��� �� ��Ȳ �ʵ��÷���!!");
							ball_bunt_fieldplay(Base);
							clear_SB(SBO);
							if( check_end(SBO) == true ){ //fieldplay������ str�ö��Ͼ���
								win_lose();
								if(win == 1){
									System.out.println();

									System.out.println("***�¸��ϼ̽��ϴ�!!*******");
									System.out.printf("[%d]�� ����, �¸�!\n", score_yours);
									System.out.println("[��+700,����ġ+100]");
									AccountDAO.WinUpdate(userId);
								}
								else if(win==0){
									System.out.println();

									System.out.println("*******�й��ϼ̽��ϴ�...********");
									System.out.printf("[%d]�� ����, �й�!\n", score_yours);
									System.out.println("[��-500,����ġ-10]");
									AccountDAO.LossUpdate(userId);
								}
								System.out.println("���� ����");
								break;
							}
							show_SBO(SBO);
							showBaseplate(Base);

						}
						
						else if (message_send.trim().equals("sagu")) {
							System.out.println("[����]�籸 ����! �Ǽ��� �ع��ȴ�... -> ��� ���!");
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
								System.out.println("***�¸��ϼ̽��ϴ�!!*******");
								System.out.println("[��+700,����ġ+100]");
								AccountDAO.WinUpdate();}//�¸���� �Է�(��+1,��+700,����ġ+100)
								else if(result==0){
								System.out.println("*******�й��ϼ̽��ϴ�...********");
								System.out.println("[��-500,����ġ-10]");
								AccountDAO.LossUpdate();//�й��� �Է�(��+1,��-500,����ġ-10)
						
								}
							} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								
							}
						
				
				
				*/
				
				
				
			
 //switch
	
		//while
		

	
	
	
static void win_lose(){
		
		if(score_yours < 5) //5�� �̸������ߴٸ�[����]
			win = 1;
		
	}

	static void clear_SB(int[] SBO){ //���� SB�ʱ�ȭ
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
	static void print_inning(){ //��� ����ÿ��� ȣ���ؾ� ��
		System.out.printf("[%d] inning ����\n", inning);
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
	
	static void ball_bunt_fieldplay(int[] Base) { //bunt ��Ȳ�� ���л�Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			out_plus(SBO); 
			plus_bat_order();
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,2�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,3�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1��, Ȩ ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[0] = 0; //������ ����
			Base[1] = 0; //1->2�� ���� ���� ����
			Base[2] = 1;
			System.out.println("1,2�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 1; 
			Base[1] = 1; 
			Base[2] = 0;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_plus(SBO);
			plus_bat_order();
			

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ -> 1,3��
			Base[0] = 1; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//���� -> Ȩ, 1�� ����
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("Ȩ , 1�� out");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();
		}

	}
	
	static void strike_bunt_fieldplay(int[] Base) { //bunt ��Ȳ�� ������Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //1�� ����
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_order();
			
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 1; //2��� ����, Ÿ�ڴ� out
			Base[2] = 0;
			System.out.println("2�� ����, Ÿ��out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3�� ����, Ÿ��out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("Ȩ��, Ÿ�� out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1; //2,3�� ����, Ÿ��out
			System.out.println("2,3�� ����, Ÿ�� out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 0; 
			Base[1] = 1; //2������, Ȩ��, Ÿ��out
			Base[2] = 0;
			System.out.println("Ȩ��, 2�� ����, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();
			

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ 
			Base[0] = 0; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("Ȩ��, 3������, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//���� -> 2,3�� + 1����, ��� �������
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("Ȩ��, 2,3������, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();
		}

	}

	static boolean check_end(int[] SBO){
		//check������ �߿��ϴ�

		boolean is_change_turn = false; //���Ͻ��Ѽ� true�� ��쿡 while brake�� �������� �� �ٲ۴�
		if(SBO[0] >= 3){ //stk count = 3
			SBO[0]  = SBO[0] - 3; //stk count �ʱ�ȭ
			System.out.println("3stk -> out count ����");
			plus_bat_order(); //out�Ǹ� ����Ÿ��
			SBO[2]++; //out count ����
		}

		if(SBO[2] >= 3){ //end�ϴ� ���
			SBO[2] = 0; //out count�ʱ�ȭ
			print_inning();
			all_clear();
			change_turn();
			is_change_turn = true;
		}

		if(SBO[1] >= 4){
			System.out.println("���� ��� !");
			clear_SB(SBO);
			baseRun(Base); //���
			showBaseplate(Base);
		}
		
		return is_change_turn;
	}

	static void showBaseplate(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1�� : " + b[0] + "  2�� : " + b[1] + "  3�� : " + b[2] + ", �� ���� : " 
				+ score_mine + ", ������� : "+ score_yours + "]");
	}

	static void showCard(char card, int[] Hit) {
		System.out.println();
		System.out.println("-���õ� ī��:" + card);
		System.out.println("-��Ʈ����ũ : " + Hit[0] + " �� : " + Hit[1] + " �籸 : " + Hit[2]);
	}

	static void pickCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-���� ��Ʈ����ũ : " + Hit[0] + " ���� �� : " + Hit[1] + " ���� �籸 : " + Hit[2]);
	}

	static void pickCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-���� ��Ʈ����ũ : " + Hit[0] + " ���� �� : " + Hit[1] + " ���� �籸 : " + Hit[2]);
	}

	static void pickCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-���� ��Ʈ����ũ : " + Hit[0] + " ���� �� : " + Hit[1] + " ���� �籸 : " + Hit[2]);
	}

	static void baseRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //���� ������� 1���

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//���� 1�翡�� �ִ°�� 1������ -> 2�� // ������ -> 1��
			Base[1] = 1;//���� 1������
			Base[0] = 1;//������

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 1; //������ -> 1��
			Base[2] = 0; // 3������ -> Ȩ
			score_yours++; // 1����


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[2] = 1; // 2������ -> 3��
			Base[1] = 1; // 1������ -> 2��
			Base[0] = 1; // ������ -> 1��

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 1; // 1�� -> 2��
			Base[2] = 0; // 3��� ��Ե�
			score_yours++; // 3������ -> 1����

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��
			score_yours++; // 3������ -> 1����

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//���� -> 2,3�� + 1����
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 1;
			score_yours++;
		}

	}

	static void homeRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine++;

		} else if (Base[0] != 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=2;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=2;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=3;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=3;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_mine +=4;
		}

	}

	
	
	
	
	
	
	

	static void win_loseh(){
		if(score_mineh >= 5) //5�� �̻� �����ߴٸ�[Ÿ��]
			winh = 1;
	}

	static void clear_hSB(int[] SBO){ //���� SB�ʱ�ȭ
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
	static void print_hinning(){ //��� ����ÿ��� ȣ���ؾ� ��
		System.out.printf("[%d] inning ����\n", inningh);
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
		//check������ �߿��ϴ�
		boolean is_change_turn = false;
		if(SBO[0] >= 3){ //stk count = 3
			SBO[0]  = SBO[0] - 3; //stk count �ʱ�ȭ
			System.out.println("3stk -> out count ����");
			plus_bat_horder();
			SBO[2]++; //out count ����
		}

		if(SBO[2] >= 3){
			SBO[2] = 0; //out count�ʱ�ȭ
			print_hinning();
			all_hclear();
			change_hturn();
			is_change_turn = true;
		}

		if(SBO[1] >= 4){
			System.out.println("���� ��� !");
			clear_hSB(SBO);
			basehRun(Baseh); //���
			showBaseplateh(Baseh);
		}

		return is_change_turn;
	}

	static void showBaseplateh(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1�� : " + b[0] + "  2�� : " + b[1] + "  3�� : " + b[2] + ", �� ���� : " 
				+ score_mineh + ", ������� : "+ score_yoursh + "]");
	}

	static void showCardh(char card, int[] Hit) {
		System.out.println();
		System.out.println("-���õ� ī��:" + card);
		System.out.println("-�ֵθ���:" + Hit[0] + ",�� �ֵθ���:" + Hit[1] + ",��Ʈ:" + Hit[2]);
	}

	static void pickhCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-���� �ֵθ���:" + Hit[0] + ",���� �� �ֵθ���:" + Hit[1] + ",���� ��Ʈ:" + Hit[2]);
	}

	static void pickhCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-���� �ֵθ���:" + Hit[0] + ",���� �� �ֵθ���:" + Hit[1] + ",���� ��Ʈ:" + Hit[2]);
	}

	static void pickhCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-���� �ֵθ���:" + Hit[0] + ",���� �� �ֵθ���:" + Hit[1] + ",���� ��Ʈ:" + Hit[2]);
	}

	static void basehRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //���� ������� 1���

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//���� 1�翡�� �ִ°�� 1������ -> 2�� // ������ -> 1��
			Base[1] = 1;//���� 1������
			Base[0] = 1;//������

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 1; //������ -> 1��
			Base[2] = 0; // 3������ -> Ȩ
			score_mineh++; // 1����


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[2] = 1; // 2������ -> 3��
			Base[1] = 1; // 1������ -> 2��
			Base[0] = 1; // ������ -> 1��

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 1; // 1�� -> 2��
			Base[2] = 0; // 3��� ��Ե�
			score_mineh++; // 3������ -> 1����

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��
			score_mineh++; // 3������ -> 1����

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//���� -> 2,3�� + 1����
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
			//���� 1�翡�� �ִ°��
			Base[0] = 0;
			System.out.println("1,2�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[1] = 0;
			System.out.println("1,3�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[2] = 0;
			System.out.println("1��, Ȩ ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[0] = 0; //������ ����
			Base[1] = 0; //1->2�� ���� ���� ����
			Base[2] = 1;
			System.out.println("1,2�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 1; 
			Base[1] = 1; 
			Base[2] = 0;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_hplus(SBOh);

			plus_bat_horder();
			
		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ  / 1,3��
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("Ȩin , ������ ����, out count 1����, �����ڴ� ����");
			score_mineh++;
			out_hplus(SBOh);
			plus_bat_horder();
		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_hplus(SBOh);
			plus_bat_horder();
		}

	}

	static void strike_bunt_hfieldplay(int[] Base) { //bunt ��Ȳ�� ������Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; //1�� ����
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_horder();
			
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			//���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 1; //2��� ����, Ÿ�ڴ� out
			Base[2] = 0;
			System.out.println("2�� ����, Ÿ��out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			//���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3�� ����, Ÿ��out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			//���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("Ȩ��, Ÿ�� out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();


		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			//���� 1,2�� ��Ȳ
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1; //2,3�� ����, Ÿ��out
			System.out.println("2,3�� ����, Ÿ�� out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			//���� 1,3�� ��Ȳ
			Base[0] = 0; 
			Base[1] = 1; //2������, Ȩ��, Ÿ��out
			Base[2] = 0;
			System.out.println("Ȩ��, 2�� ����, Ÿ��out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();


		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			//���� 2,3�� ��Ȳ 
			Base[0] = 0; 
			Base[1] = 0; 
			Base[2] = 1;
			System.out.println("Ȩ��, 3������, Ÿ��out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			//���� -> 2,3�� + 1����, ��� �������
			Base[0] = 0; 
			Base[1] = 1; 
			Base[2] = 1;
			System.out.println("Ȩ��, 2,3������, Ÿ��out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();
		}

	}

	static void homeRunh(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh++;

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=2;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=2;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=3;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=3;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; //�÷���Ʈ �ʱ�ȭ
			score_yoursh +=4;
		}

	}
	
	
	
}
