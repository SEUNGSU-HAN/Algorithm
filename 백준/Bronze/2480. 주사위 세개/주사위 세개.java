import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주사위 세개
public class Main {
	static int[] dice;
	static int prize;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 초기화 */
		dice = new int[3];
		for (int i = 0; i < 3; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		if(dice[0] == dice[2] && dice[0] == dice[2] && dice[1] == dice[2]) {
			prize = 10000+dice[0]*1000;
		}else if(dice[0] == dice[1] || dice[0] == dice[2] || dice[1] == dice[2]) {
			int same = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = i+1; j < 3; j++) {
					if(dice[i] == dice[j]) {
						same = dice[i];
						break;
					}
				}
			}
			prize = 1000+same*100;
		}else {
			int max = 0;
			for (int i = 0; i < 3; i++) {
				max = Math.max(max, dice[i]);
			}
			prize = max*100;
		}
		
		/* 출력 */
		System.out.println(prize);
	}

}
