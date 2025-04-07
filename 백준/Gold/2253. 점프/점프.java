import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE/1000;
	static int N, M, result = Integer.MAX_VALUE;
	static int[][] jump;
	static boolean[] isJump;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		jump = new int[10001][150];
		for (int i = 0; i < 10001; i++) {
			Arrays.fill(jump[i], INF);
		}
		isJump = new boolean[10001];
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			isJump[n] = true;
		}

		
		/* 로직 */
		if(isJump[2]) {
			System.out.print(-1);
			return;
		}
		jump[2][1] = 1;
		for (int i = 3; i <= N; i++) {
			for (int x = 1; x < 142; x++) {
				if(isJump[i]) break;
                int slow = get_num(i-x, x+1) + 1;
                int normal = get_num(i-x, x) + 1;
                int fast = get_num(i-x, x-1) + 1;
                jump[i][x] = Math.min(Math.min(Math.min(INF, slow), normal), fast);
			}
		}
		
		for (int i = 1; i < 142; i++) {
			result = Math.min(result, jump[N][i]);
		}
		
		
		/* 출력 */
		System.out.print(result >= INF ? -1 : result);
	}
	static int get_num(int n, int x){
	    if (x < 1) return INF;
	    if (x > 142) return INF;
	    if (n <= 0) return INF;
	    if (n > N) return INF;

	    return jump[n][x];
	}

}
