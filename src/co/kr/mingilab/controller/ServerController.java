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

public class ServerController implements ControllerInterface {
	static int score_mine = 0;
	static int score_yours = 0;
	static int[] SBO = { 0, 0, 0 };
	static int[] Base = new int[3];
	static int inning = 1;
	static int turn = 1;
	static int bat_order = 1;
	static int win = 0;

	static int score_mineh = 0;
	static int score_yoursh = 0;
	static int[] SBOh = { 0, 0, 0 };
	static int[] Baseh = new int[3]; // ����, Ÿ�� ���� java file���� ����ȭó�� ����
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

		System.out.println("******** KBO �߱����� *******");
		System.out.println("******** Ÿ�ڹ��� *******");
		System.out.printf("\n[%d]inning ����!\n", inningh);
		System.out.println("\n[�¸�����] 5�� �̻� �����ϱ�\n");
		int[] HitA = { 30, 17, 3 };
		int[] HitB = { 28, 17, 5 };
		int[] HitC = { 26, 17, 7 };

		int[] Base = { 0, 0, 0, 0 };
		int[] count = { 0, 0, 0 };
		
		
		accountdto = (AccountDTO) map.get("dto1");
		String userId = accountdto.getUserid();
		st = daoHit.StaringOrderGet(userId);
		kboView.printHitter(st);

		st = daoHit.thisHitter(userId, bat_orderh);
		kboView.printHitterChoice(st);
		st = daoHit.thisHitterGrade(userId, bat_orderh);

		Scanner input = new Scanner(System.in);
		char card = kboView.printHitterGrade(st);
		// card = input.next().charAt(0);

		ServerSocket server = null;
		InetSocketAddress ipep = null;
		Socket client = null;

		try {
			server = new ServerSocket();
			// ���� �ʱ�ȭ
			ipep = new InetSocketAddress(9999);
			server.bind(ipep);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {

			try {

				// LISTEN ���
				client = server.accept();

				System.out.printf("\n\n����Ÿ�� : [%d]�� Ÿ��\n", bat_orderh);
				st = daoHit.thisHitter(userId, bat_orderh);
				kboView.printHitterChoice(st);
				st = daoHit.thisHitterGrade(userId, bat_orderh);
				card = kboView.printHitterGrade(st);

				if (bat_orderh == 4) {
					System.out.println("4��Ÿ���Դϴ�. strike�� swing�� Ȩ���� ĥ �� �ֽ��ϴ�.");
				}

				switch (card) {
				case 'A':
					showCardh(card, HitA);
					break;

				case 'B':
					showCardh(card, HitB);

					break;

				case 'C':
					showCardh(card, HitC);

					break;

				default:
					System.out.println("�ش� ������ �����ϴ�.");
					break;

				}

				System.out.println();
				System.out.println();
				System.out.println("�ൿ�� �����ϼ���.");
				System.out.print("swing / noswing / bunt >>");
				String choice;
				choice = input.next();

				switch (choice) {

				case "swing":
					if (card == 'A') {
						pickhCard1(HitA);

					} else if (card == 'B') {
						pickhCard1(HitB);

					} else if (card == 'C') {
						pickhCard1(HitC);

					}

					break;

				case "noswing":
					if (card == 'A') {
						pickhCard2(HitA);

					} else if (card == 'B') {
						pickhCard2(HitB);

					} else if (card == 'C') {
						pickhCard2(HitC);

					}
					break;

				case "bunt":
					if (card == 'A') {
						pickhCard3(HitA);

					} else if (card == 'B') {
						pickhCard3(HitB);

					} else if (card == 'C') {
						pickhCard3(HitC);

					}
					break;

				default:
					System.out.println("�ش� ������ �����ϴ�.");
					continue;
				// break;
				}

				// send,reciever ��Ʈ�� �޾ƿ���
				OutputStream sender;
				sender = client.getOutputStream();
				InputStream reciever = client.getInputStream();

				String message_send = choice;
				byte[] data = message_send.getBytes();
				sender.write(data, 0, data.length);
				sender.flush();

				// Ŭ���̾�Ʈ�κ��� �޽��� �ޱ�

				data = new byte[256];
				reciever.read(data, 0, data.length);

				// ���� �޽��� ���
				String message_recv = new String(data);
				System.out.println("=========================================");
				String out = String.format("[��뼱��] %s", message_recv);
				System.out.println(out);

				if (message_recv.trim().equals("strike") && message_send.trim().equals("swing")) {
					if (bat_orderh == 4) {
						System.out.println("[Ÿ��]4�� Ÿ�ڰ� �� ������ �ߴ�!! ->Ȩ��!!");
						homeRunh(Baseh);
					} else {
						System.out.println("[Ÿ��]strike�� swing�ߴ�. -> ���!");
						basehRun(Baseh);
					}
					showBaseplateh(Baseh);
					clear_hSB(SBOh);
					show_hSBO(SBOh);
					plus_bat_horder();
				}

				else if (message_recv.trim().equals("strike") && message_send.trim().equals("noswing")) {
					System.out.println("[Ÿ��]strike�� noswing�ߴ�. -> ��Ʈ����ũ!!");
					stk_hplus(SBOh);
					if (check_hend(SBOh) == true) {
						plus_bat_horder();
						win_loseh();
						if (winh == 1) {
							System.out.println();
							System.out.println("***�¸��ϼ̽��ϴ�!!*******");
							System.out.printf("[%d]�� ���� , �¸�!\n", score_mineh);
							System.out.println("[��+700,����ġ+100]");
							AccountDAO.WinUpdate(userId);
						} else if (winh == 0) {
							System.out.println();

							System.out.println("*******�й��ϼ̽��ϴ�...********");
							System.out.printf("[%d]�� ����, �й�!\n", score_mineh);
							System.out.println("[��-500,����ġ-10]");
							AccountDAO.LossUpdate(userId);
						}
						System.out.println("���� ����~");
						break;
					}
					show_hSBO(SBOh);
				}

				else if (message_recv.trim().equals("strike") && message_send.trim().equals("bunt")) {
					System.out.println("[Ÿ��]strike�� bunt�ߴ�. -> ���� �ʵ��÷���!!");
					strike_bunt_hfieldplay(Baseh);
					clear_hSB(SBOh);
					if (check_hend(SBOh) == true) { // fieldplay������ str�ö��Ͼ���
						win_loseh();
						if (winh == 1) {
							System.out.println();

							System.out.println("***�¸��ϼ̽��ϴ�!!*******");
							System.out.printf("[%d]�� ����, �¸�!\n", score_mineh);
							System.out.println("[��+700,����ġ+100]");
							AccountDAO.WinUpdate(userId);
						} else if (winh == 0) {
							System.out.println();

							System.out.println("*******�й��ϼ̽��ϴ�...********");
							System.out.printf("[%d]�� ����, �й�!\n", score_mineh);
							System.out.println("[��-500,����ġ-10]");
							AccountDAO.LossUpdate(userId);
						}
						System.out.println("���� ����");
						break;
					}
					show_hSBO(SBOh);
					showBaseplateh(Baseh);
				}

				else if (message_recv.trim().equals("ball") && message_send.trim().equals("swing")) {
					System.out.println("[Ÿ��]ball�� swing�ߴ�. -> ��Ʈ����ũ!!");
					stk_hplus(SBOh);
					if (check_hend(SBOh) == true) {
						plus_bat_horder();
						win_loseh();
						if (winh == 1) {
							System.out.println();

							System.out.println("***�¸��ϼ̽��ϴ�!!*******");
							System.out.printf("[%d]�� ���� , �¸�!\n", score_mineh);
							System.out.println("[��+700,����ġ+100]");
							AccountDAO.WinUpdate(userId);
						} else if (winh == 0) {
							System.out.println();

							System.out.println("*******�й��ϼ̽��ϴ�...********");
							System.out.printf("[%d]�� ����, �й�!\n", score_mineh);
							System.out.println("[��-500,����ġ-10]");
							AccountDAO.LossUpdate(userId);
						}
						System.out.println("���� ����!");
						break;
					}
					show_hSBO(SBOh);
					showBaseplateh(Baseh);
				}

				else if (message_recv.trim().equals("ball") && message_send.trim().equals("noswing")) {
					System.out.println("[Ÿ��]ball�� noswing�ߴ�. -> ��~~~");
					ball_hplus(SBOh);
					check_hend(SBOh);
					show_hSBO(SBOh);
					showBaseplateh(Baseh);
				}

				else if (message_recv.trim().equals("ball") && message_send.trim().equals("bunt")) {
					System.out.println("[Ÿ��]ball�� bunt�ߴ�. -> �ʵ��÷���!!");
					ball_bunt_hfieldplay(Baseh);
					clear_hSB(SBOh);
					if (check_hend(SBOh) == true) { // fieldplay������ str�ö��Ͼ���
						win_loseh();
						if (winh == 1) {
							System.out.println();

							System.out.println("***�¸��ϼ̽��ϴ�!!*******");
							System.out.printf("[%d]�� ����, �¸�!\n", score_mineh);
							System.out.println("[��+700,����ġ+100]");
							AccountDAO.WinUpdate(userId);
						} else if (winh == 0) {
							System.out.println();

							System.out.println("*******�й��ϼ̽��ϴ�...********");
							System.out.printf("[%d]�� ����, �й�!\n", score_mineh);
							System.out.println("[��-500,����ġ-10]");
							AccountDAO.LossUpdate(userId);// �й���
															// �Է�(��+1,��-500,����ġ-10)
						}
						System.out.println("���� ����");
						break;
					}
					show_hSBO(SBOh);
					showBaseplateh(Baseh);

				}

				else if (message_recv.trim().equals("sagu")) {
					System.out.println("[Ÿ��] ��! �籸�� �¾Ҵ�... -> ���!");
					basehRun(Baseh);
					clear_hSB(SBOh);
					show_hSBO(SBOh);
					showBaseplateh(Baseh);
					plus_bat_horder();

				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/*
	 * int result = 1; if(result==1){ System.out.println("***�¸��ϼ̽��ϴ�!!*******");
	 * System.out.println("[��+700,����ġ+100]"); AccountDAO.WinUpdate();}//�¸����
	 * �Է�(��+1,��+700,����ġ+100) else if(result==0){
	 * System.out.println("*******�й��ϼ̽��ϴ�...********");
	 * System.out.println("[��-500,����ġ-10]"); AccountDAO.LossUpdate();//�й���
	 * �Է�(��+1,��-500,����ġ-10)
	 * 
	 * } } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * 
	 * 
	 */

	// switch

	// while

	static void win_lose() {

		if (score_yours < 5) // 5�� �̸������ߴٸ�[����]
			win = 1;

	}

	static void clear_SB(int[] SBO) { // ���� SB�ʱ�ȭ
		SBO[0] = SBO[1] = 0;
	}

	static void show_SBO(int[] SBO) {
		System.out.println("[Strike : " + SBO[0] + " Ball : " + SBO[1] + " Out : " + SBO[2] + "]");
	}

	static void stk_plus(int[] SBO) {
		SBO[0]++;
	}

	static void ball_plus(int[] SBO) {
		SBO[1]++;
	}

	static void out_plus(int[] SBO) {
		SBO[2]++;
	}

	static void print_inning() { // ��� ����ÿ��� ȣ���ؾ� ��
		System.out.printf("[%d] inning ����\n", inning);
		inning++;
	}

	static void all_clear() {
		SBO[0] = SBO[1] = SBO[2] = 0;
		Base[0] = Base[1] = Base[2] = 0;
	}

	static void change_turn() {
		if (turn == 1)
			turn = 0;
		else
			turn = 1;
	}

	static void plus_bat_order() {
		if (bat_order < 9)
			bat_order++;
		else
			bat_order = 1;
	}

	static void ball_bunt_fieldplay(int[] Base) { // bunt ��Ȳ�� ���л�Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			// ���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,2�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1,3�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("1��, Ȩ ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[0] = 0; // ������ ����
			Base[1] = 0; // 1->2�� ���� ���� ����
			Base[2] = 1;
			System.out.println("1,2�� ����!");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 0;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ -> 1,3��
			Base[0] = 1;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			// ���� -> Ȩ, 1�� ����
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1;
			System.out.println("Ȩ , 1�� out");
			out_plus(SBO);
			out_plus(SBO);
			plus_bat_order();
		}

	}

	static void strike_bunt_fieldplay(int[] Base) { // bunt ��Ȳ�� ������Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; // 1�� ����
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			// ���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 1; // 2��� ����, Ÿ�ڴ� out
			Base[2] = 0;
			System.out.println("2�� ����, Ÿ��out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3�� ����, Ÿ��out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("Ȩ��, Ÿ�� out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1; // 2,3�� ����, Ÿ��out
			System.out.println("2,3�� ����, Ÿ�� out");
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 0;
			Base[1] = 1; // 2������, Ȩ��, Ÿ��out
			Base[2] = 0;
			System.out.println("Ȩ��, 2�� ����, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("Ȩ��, 3������, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			// ���� -> 2,3�� + 1����, ��� �������
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1;
			System.out.println("Ȩ��, 2,3������, Ÿ��out");
			score_yours++;
			out_plus(SBO);
			plus_bat_order();
		}

	}

	static boolean check_end(int[] SBO) {
		// check������ �߿��ϴ�

		boolean is_change_turn = false; // ���Ͻ��Ѽ� true�� ��쿡 while brake�� �������� ��
										// �ٲ۴�
		if (SBO[0] >= 3) { // stk count = 3
			SBO[0] = SBO[0] - 3; // stk count �ʱ�ȭ
			System.out.println("3stk -> out count ����");
			plus_bat_order(); // out�Ǹ� ����Ÿ��
			SBO[2]++; // out count ����
		}

		if (SBO[2] >= 3) { // end�ϴ� ���
			SBO[2] = 0; // out count�ʱ�ȭ
			print_inning();
			all_clear();
			change_turn();
			is_change_turn = true;
		}

		if (SBO[1] >= 4) {
			System.out.println("���� ��� !");
			clear_SB(SBO);
			baseRun(Base); // ���
			showBaseplate(Base);
		}

		return is_change_turn;
	}

	static void showBaseplate(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1�� : " + b[0] + "  2�� : " + b[1] + "  3�� : " + b[2] + ", �� ���� : " + score_mine
				+ ", ������� : " + score_yours + "]");
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

			Base[0] = 1; // ���� ������� 1���

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			// ���� 1�翡�� �ִ°�� 1������ -> 2�� // ������ -> 1��
			Base[1] = 1;// ���� 1������
			Base[0] = 1;// ������

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[2] = 0; // 3������ -> Ȩ
			score_yours++; // 1����

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[2] = 1; // 2������ -> 3��
			Base[1] = 1; // 1������ -> 2��
			Base[0] = 1; // ������ -> 1��

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 1; // 1�� -> 2��
			Base[2] = 0; // 3��� ��Ե�
			score_yours++; // 3������ -> 1����

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��
			score_yours++; // 3������ -> 1����

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			// ���� -> 2,3�� + 1����
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 1;
			score_yours++;
		}

	}

	static void homeRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine++;

		} else if (Base[0] != 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 2;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 2;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 3;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 3;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_mine += 4;
		}

	}

	static void win_loseh() {
		if (score_mineh >= 5) // 5�� �̻� �����ߴٸ�[Ÿ��]
			winh = 1;
	}

	static void clear_hSB(int[] SBO) { // ���� SB�ʱ�ȭ
		SBO[0] = SBO[1] = 0;
	}

	static void show_hSBO(int[] SBO) {
		System.out.println("[Strike : " + SBO[0] + " Ball : " + SBO[1] + " Out : " + SBO[2] + "]");
	}

	static void stk_hplus(int[] SBO) {
		SBO[0]++;
	}

	static void ball_hplus(int[] SBO) {
		SBO[1]++;
	}

	static void out_hplus(int[] SBO) {
		SBO[2]++;
	}

	static void print_hinning() { // ��� ����ÿ��� ȣ���ؾ� ��
		System.out.printf("[%d] inning ����\n", inningh);
		inningh++;
	}

	static void plus_bat_horder() {
		if (bat_orderh < 9)
			bat_orderh++;
		else
			bat_orderh = 1;
	}

	static void all_hclear() {
		SBOh[0] = SBOh[1] = SBOh[2] = 0;
		Baseh[0] = Baseh[1] = Baseh[2] = 0;
	}

	static void change_hturn() {
		if (turnh == 1)
			turnh = 0;
		else
			turnh = 1;
	}

	static boolean check_hend(int[] SBO) {
		// check������ �߿��ϴ�
		boolean is_change_turn = false;
		if (SBO[0] >= 3) { // stk count = 3
			SBO[0] = SBO[0] - 3; // stk count �ʱ�ȭ
			System.out.println("3stk -> out count ����");
			plus_bat_horder();
			SBO[2]++; // out count ����
		}

		if (SBO[2] >= 3) {
			SBO[2] = 0; // out count�ʱ�ȭ
			print_hinning();
			all_hclear();
			change_hturn();
			is_change_turn = true;
		}

		if (SBO[1] >= 4) {
			System.out.println("���� ��� !");
			clear_hSB(SBO);
			basehRun(Baseh); // ���
			showBaseplateh(Baseh);
		}

		return is_change_turn;
	}

	static void showBaseplateh(int[] b) {
		System.out.println("=========================================");
		System.out.println("[1�� : " + b[0] + "  2�� : " + b[1] + "  3�� : " + b[2] + ", �� ���� : " + score_mineh
				+ ", ������� : " + score_yoursh + "]");
	}

	static void showCardh(char card, int[] Hit) {
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

			Base[0] = 1; // ���� ������� 1���

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			// ���� 1�翡�� �ִ°�� 1������ -> 2�� // ������ -> 1��
			Base[1] = 1;// ���� 1������
			Base[0] = 1;// ������

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 1; // ������ -> 1��
			Base[2] = 0; // 3������ -> Ȩ
			score_mineh++; // 1����

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[2] = 1; // 2������ -> 3��
			Base[1] = 1; // 1������ -> 2��
			Base[0] = 1; // ������ -> 1��

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 1; // 1�� -> 2��
			Base[2] = 0; // 3��� ��Ե�
			score_mineh++; // 3������ -> 1����

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ
			Base[0] = 1; // ������ -> 1��
			Base[1] = 0; // 2��� ��Ե�
			Base[2] = 1; // 2������ -> 3��
			score_mineh++; // 3������ -> 1����

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			// ���� -> 2,3�� + 1����
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
			// ���� 1�翡�� �ִ°��
			Base[0] = 0;
			System.out.println("1,2�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[1] = 0;
			System.out.println("1,3�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[2] = 0;
			System.out.println("1��, Ȩ ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[0] = 0; // ������ ����
			Base[1] = 0; // 1->2�� ���� ���� ����
			Base[2] = 1;
			System.out.println("1,2�� ����!");
			out_hplus(SBOh);
			out_hplus(SBOh);
			plus_bat_horder();
		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 1;
			Base[1] = 1;
			Base[2] = 0;
			System.out.println("Ȩ���� ����, ������ ����, out count 1����");
			out_hplus(SBOh);

			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ / 1,3��
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

	static void strike_bunt_hfieldplay(int[] Base) { // bunt ��Ȳ�� ������Ȳ !
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {

			Base[0] = 1; // 1�� ����
			Base[1] = 0;
			Base[2] = 0;
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			// ���� 1�翡�� �ִ°��
			Base[0] = 0;
			Base[1] = 1; // 2��� ����, Ÿ�ڴ� out
			Base[2] = 0;
			System.out.println("2�� ����, Ÿ��out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			// ���� 2�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("3�� ����, Ÿ��out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			// ���� 3�翡 �Ѹ� �ִ°��
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			System.out.println("Ȩ��, Ÿ�� out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			// ���� 1,2�� ��Ȳ
			Base[0] = 0;
			Base[1] = 1;
			Base[2] = 1; // 2,3�� ����, Ÿ��out
			System.out.println("2,3�� ����, Ÿ�� out");
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 1) {
			// ���� 1,3�� ��Ȳ
			Base[0] = 0;
			Base[1] = 1; // 2������, Ȩ��, Ÿ��out
			Base[2] = 0;
			System.out.println("Ȩ��, 2�� ����, Ÿ��out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			// ���� 2,3�� ��Ȳ
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 1;
			System.out.println("Ȩ��, 3������, Ÿ��out");
			score_yoursh++;
			out_hplus(SBOh);
			plus_bat_horder();

		}

		else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			// ���� -> 2,3�� + 1����, ��� �������
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
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh++;

		} else if (Base[0] == 1 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 2;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 2;

		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 2;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 0) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 3;

		} else if (Base[0] == 0 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 3;

		} else if (Base[0] == 1 && Base[1] == 1 && Base[2] == 1) {
			Base[0] = Base[1] = Base[2] = 0; // �÷���Ʈ �ʱ�ȭ
			score_yoursh += 4;
		}

	}

}
