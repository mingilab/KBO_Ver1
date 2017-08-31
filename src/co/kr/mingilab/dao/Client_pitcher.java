package co.kr.mingilab.dao;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class Client_pitcher {
	public static void main(String[] args) {
		System.out.println("******** KBO �߱����� *******");
		System.out.println("********   ���� ����   *******");
		int[] pitchA = { 70, 27, 3 };
		int[] pitchB = { 60, 35, 5 };
		int[] pitchC = { 55, 38, 7 };
		int[] Base = new int[4];
		int[] count = { 0, 0, 0 };
		int i = 0;

		showBaseplate(Base);
		showOutplate(count);

		Scanner input = new Scanner(System.in);

		System.out.println("���� ��� ī�带 �����Ͻÿ�. A/B/C");
		char card;
		card = input.next().charAt(0);

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
				Socket client = new Socket();

				client.connect(ipep);
				System.out.println("=========================");
				System.out.println("���� �����Ͽ� ��������.");
				System.out.print("strike/ball/sagu>>");
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
				}

				
				OutputStream sender = (OutputStream) client.getOutputStream();
				InputStream receiver = (InputStream) client.getInputStream();
				// �����κ��� ������ �ޱ�
				byte[] data = new byte[256];
				int SIZE = 256;
				receiver.read(data, 0, SIZE);

				// ���Ÿ޽��� ���
				String message_recv = new String(data);
				String out = String.format("��� ���� - %s", message_recv);
				System.out.println(out);

				/*ObjectInputStream ois = new ObjectInputStream(receiver);
				HashMap<String, Object> map =
						(HashMap<String, Object>) ois.readObject();
				������ aa = (������)map.get("aa");
				byte[] b = (byte[])map.get("message_send");
				String s = new String(b);
				System.out.println(aa);
				System.out.println(s);*/

				// ������ ������ ������
				String message_send = choice;
				data = message_send.getBytes();
				sender.write(data, 0, data.length);
				sender.flush();
				System.out.println(message_recv);
				System.out.println(message_send);
				if (message_recv.trim().equals("swing") && message_send.trim().equals("strike")) {
					System.out.println("[����]strike �����µ� swing���� -> ��� ���!");
				} else if (message_recv.trim().equals("swing") && message_send.trim().equals("ball")) {
					System.out.println("[����]ball �����µ� swing�� -> stk count����!");
				} else if (message_recv.trim().equals("noswing") && message_send.trim().equals("strike")) {
					System.out.println("[����]strike �����µ� noswing�� -> stk count ����!");
				} else if (message_recv.trim().equals("noswing") && message_send.trim().equals("ball")) {
					System.out.println("[����]ball �����µ� noswing�� -> ball count����!");
				} else if (message_recv.trim().equals("bunt") && message_send.trim().equals("strike")) {
					System.out.println("[����]strike �����µ� bunt���� -> ��� ���!(swing�� ����)");
				} else if (message_recv.trim().equals("bunt") && message_send.trim().equals("ball")) {
					System.out.println("[����]ball �����µ� bunt�� -> stk count����!(swing�� ����)");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	static void showBaseplate(int[] b) {
		System.out.println("================================");
		System.out.println("������Ȳ");
		System.out.println("[1��:" + b[0] + ",2��:" + b[1] + ",3��:" + b[2] + ",����:" + b[3] + "]");
	}

	static void showOutplate(int[] b) {

		System.out.println("<Strike:" + b[0] + ",Ball:" + b[1] + ",Out:" + b[2] + ">");
	}

	static void showCard(char card, int[] Hit) {
		System.out.println("-���õ� ī��:" + card);
		System.out.println("-��Ʈ����ũ:" + Hit[0] + ",��:" + Hit[1] + ",�籸:" + Hit[2]);
	}

	static void pickCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-���� ��Ʈ����ũ:" + Hit[0] + ",���� ��:" + Hit[1] + ",���� �籸:" + Hit[2]);
	}

	static void pickCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-���� ��Ʈ����ũ:" + Hit[0] + ",���� ��:" + Hit[1] + ",���� �籸:" + Hit[2]);
	}

	static void pickCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-���� ��Ʈ����ũ:" + Hit[0] + ",���� ��:" + Hit[1] + ",���� �籸:" + Hit[2]);
	}

	static void baseRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {
			Base[0]++;
		} else if (Base[0] != 0 && Base[1] == 0 && Base[2] == 0) {
			Base[1]++;
		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] == 0) {

			Base[2]++;
		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] != 0) {

			Base[3]++;
		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] == 0) {

			Base[2]++;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0]++;
			Base[3]++;
		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] != 0) {
			Base[3]++;
		}

	}

	static void homeRun(int[] Base) {
		if (Base[0] == 0 && Base[1] == 0 && Base[1] == 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
		} else if (Base[0] != 0 && Base[1] == 0 && Base[2] == 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;
		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;
		} else if (Base[0] == 0 && Base[1] == 0 && Base[2] != 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] == 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;
			Base[3]++;

		} else if (Base[0] == 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;
			Base[3]++;

		} else if (Base[0] != 0 && Base[1] != 0 && Base[2] != 0) {
			Base[0] = 0;
			Base[1] = 0;
			Base[2] = 0;
			Base[3]++;
			Base[3]++;
			Base[3]++;
			Base[3]++;
		}

	}

	static void randomResult() {
		int[] result = { 1, 2, 3, 4 }; // 1�����迭 �� 10���� ����ϴ�.
		int ran = 0; // �������� ���� ������ ����ϴ�.
		boolean cheak; // ���ϱ� ���� boolean�� ������ ����ϴ�.
		Random r = new Random(); // Random�� ��ü�� ����ϴ�.

		for (int i = 0; i < result.length; i++) { // �迭�� ũ�⸸ŭ for���� �����ϴ�.
			ran = r.nextInt(4) + 1; // nextInt(10)�ϸ� 0-9���� �����Ƿ� +1�� ���� 1-10������
									// ����ϴ�.
			cheak = true; // i���� �� ������ cheak�� true�� ����ϴ�.
			for (int j = 0; j < i; j++) { // if�� ������ j�� i����ŭ �����ϴ�.
				if (result[j] == ran) {
					// arr�迭�� ���� �� ����ִ� �����̰� ������ nextInt�� �ؾ� �ϳ��� ���ϴ�.
					// �׷��Ƿ� i�� ����ŭ �迭�� ���ִ� ���Դϴ�.
					// for���� �����鼭 ��� ���� random���� �迭�� ����ִ� ������ ���Ͽ� ������ ������
					i--; // i�� ���� �ϳ� �ٿ� �� �� �� ���� �մϴ�.
					cheak = false; // �������� �ٸ��� ���� ���� �������Ƿ� cheak�� false�� ����ϴ�.
				}
			}
			if (cheak) // ���� if���� ������ �������� �ʾ����� �ڵ����� cheak�� true�Ƿ� ������ �˴ϴ�.
				result[i] = ran; // ran�� ���� ���� arr[i]�濡 �ֽ��ϴ�.
		}

		for (int i = 0; i < result.length; i++) // ����ϱ� ���� for��
			System.out.println(result[i]); // arr�迭�� ����մϴ�.
	}
}
