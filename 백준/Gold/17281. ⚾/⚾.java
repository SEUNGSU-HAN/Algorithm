import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] inning;
	static int[] order; //타석 순서
	static int score;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		inning = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[9];
		
		/* 로직 */
		perm(0, 0);
		
		/* 출력 */
		System.out.print(score);
	}

	private static void perm(int cnt, int flag) {
		if(cnt == 9) {
			int idx = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				int outCount = 0;
				int base = 0;
				while(outCount != 3) {
					int s = inning[i][order[idx++%9]];
					if(s == 0) {
						outCount++;
					}else {
						for (int j = 0; j < s; j++) {
							//루수들 한칸씩 이동
							base = base<<1;
							
							if(j == 0) base = base | 1<<0; //타자 진출
							
							//홈에 들어왔는지체크
							if((base & 1<<3) != 0) { //홈에 들어왔다면
								sum++; //점수 증가
								//홈 비우기
								base = base ^ 1<<3; //XOR 연산
							}
							
						}
					}
				}
			}
			score = Math.max(score, sum);	
			return;
		}
		for (int i = 0; i < 9; i++) {
			if((flag & 1<<i) != 0) continue;
			if(cnt == 3 && i != 0) continue;
			order[cnt] = i;
			perm(cnt+1, flag | 1<<i);
		}
	}

}
