import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 999;
	static int N, A, B, M;
	static int[][] chonsu;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		chonsu = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(chonsu[i], INF);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			chonsu[s][e] = 1;
			chonsu[e][s] = 1;
		}
		
		/* 로직 */
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					chonsu[i][j] = Math.min(chonsu[i][j], chonsu[i][k]+chonsu[k][j]);
				}
			}
		}
		
		/* 출력 */
		System.out.print(chonsu[A][B] == INF ? -1 : chonsu[A][B]);
	}

}
