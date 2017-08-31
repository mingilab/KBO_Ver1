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
		System.out.println("******** KBO 야구게임 *******");
		System.out.println("********   투수 버전   *******");
		int[] pitchA = { 70, 27, 3 };
		int[] pitchB = { 60, 35, 5 };
		int[] pitchC = { 55, 38, 7 };
		int[] Base = new int[4];
		int[] count = { 0, 0, 0 };
		int i = 0;

		showBaseplate(Base);
		showOutplate(count);

		Scanner input = new Scanner(System.in);

		System.out.println("투수 등급 카드를 선택하시오. A/B/C");
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
			System.out.println("해당 종류는 없습니다.");
			break;

		}

		while (true) {
			try {
				InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
				Socket client = new Socket();

				client.connect(ipep);
				System.out.println("=========================");
				System.out.println("공을 선택하여 던지세요.");
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
					System.out.println("해당 종류는 없습니다.");
				}

				
				OutputStream sender = (OutputStream) client.getOutputStream();
				InputStream receiver = (InputStream) client.getInputStream();
				// 서버로부터 데이터 받기
				byte[] data = new byte[256];
				int SIZE = 256;
				receiver.read(data, 0, SIZE);

				// 수신메시지 출력
				String message_recv = new String(data);
				String out = String.format("상대 선택 - %s", message_recv);
				System.out.println(out);

				/*ObjectInputStream ois = new ObjectInputStream(receiver);
				HashMap<String, Object> map =
						(HashMap<String, Object>) ois.readObject();
				전광판 aa = (전광판)map.get("aa");
				byte[] b = (byte[])map.get("message_send");
				String s = new String(b);
				System.out.println(aa);
				System.out.println(s);*/

				// 서버로 데이터 보내기
				String message_send = choice;
				data = message_send.getBytes();
				sender.write(data, 0, data.length);
				sender.flush();
				System.out.println(message_recv);
				System.out.println(message_send);
				if (message_recv.trim().equals("swing") && message_send.trim().equals("strike")) {
					System.out.println("[투수]strike 던졌는데 swing맞음 -> 상대 출루!");
				} else if (message_recv.trim().equals("swing") && message_send.trim().equals("ball")) {
					System.out.println("[투수]ball 던졌는데 swing함 -> stk count증가!");
				} else if (message_recv.trim().equals("noswing") && message_send.trim().equals("strike")) {
					System.out.println("[투수]strike 던졌는데 noswing함 -> stk count 증가!");
				} else if (message_recv.trim().equals("noswing") && message_send.trim().equals("ball")) {
					System.out.println("[투수]ball 던졌는데 noswing함 -> ball count증가!");
				} else if (message_recv.trim().equals("bunt") && message_send.trim().equals("strike")) {
					System.out.println("[투수]strike 던졌는데 bunt맞음 -> 상대 출루!(swing과 동일)");
				} else if (message_recv.trim().equals("bunt") && message_send.trim().equals("ball")) {
					System.out.println("[투수]ball 던졌는데 bunt함 -> stk count증가!(swing과 동일)");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	static void showBaseplate(int[] b) {
		System.out.println("================================");
		System.out.println("점수현황");
		System.out.println("[1루:" + b[0] + ",2루:" + b[1] + ",3루:" + b[2] + ",점수:" + b[3] + "]");
	}

	static void showOutplate(int[] b) {

		System.out.println("<Strike:" + b[0] + ",Ball:" + b[1] + ",Out:" + b[2] + ">");
	}

	static void showCard(char card, int[] Hit) {
		System.out.println("-선택된 카드:" + card);
		System.out.println("-스트라이크:" + Hit[0] + ",볼:" + Hit[1] + ",사구:" + Hit[2]);
	}

	static void pickCard1(int[] Hit) {
		Hit[0]--;
		System.out.println("-남은 스트라이크:" + Hit[0] + ",남은 볼:" + Hit[1] + ",남은 사구:" + Hit[2]);
	}

	static void pickCard2(int[] Hit) {
		Hit[1]--;
		System.out.println("-남은 스트라이크:" + Hit[0] + ",남은 볼:" + Hit[1] + ",남은 사구:" + Hit[2]);
	}

	static void pickCard3(int[] Hit) {
		Hit[2]--;
		System.out.println("-남은 스트라이크:" + Hit[0] + ",남은 볼:" + Hit[1] + ",남은 사구:" + Hit[2]);
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
		int[] result = { 1, 2, 3, 4 }; // 1차원배열 방 10개를 만듭니다.
		int ran = 0; // 랜덤값을 받을 변수를 만듭니다.
		boolean cheak; // 비교하기 위해 boolean형 변수를 만듭니다.
		Random r = new Random(); // Random형 객체를 만듭니다.

		for (int i = 0; i < result.length; i++) { // 배열의 크기만큼 for문을 돌립니다.
			ran = r.nextInt(4) + 1; // nextInt(10)하면 0-9까지 나오므로 +1을 시켜 1-10까지로
									// 만듭니다.
			cheak = true; // i문이 돌 때마다 cheak를 true로 만듭니다.
			for (int j = 0; j < i; j++) { // if문 때문에 j를 i값만큼 돌립니다.
				if (result[j] == ran) {
					// arr배열의 방은 다 비어있는 상태이고 위에서 nextInt를 해야 하나씩 들어갑니다.
					// 그러므로 i의 값만큼 배열에 들어가있는 것입니다.
					// for문을 돌리면서 방금 받은 random값과 배열에 들어있는 값들을 비교하여 같은게 있으면
					i--; // i의 값을 하나 줄여 한 번 더 돌게 합니다.
					cheak = false; // 목적과는 다르게 같은 값이 나왔으므로 cheak를 false로 만듭니다.
				}
			}
			if (cheak) // 위의 if문의 조건을 만족하지 않았으면 자동으로 cheak는 true므로 실행이 됩니다.
				result[i] = ran; // ran에 받은 값을 arr[i]방에 넣습니다.
		}

		for (int i = 0; i < result.length; i++) // 출력하기 위한 for문
			System.out.println(result[i]); // arr배열을 출력합니다.
	}
}
