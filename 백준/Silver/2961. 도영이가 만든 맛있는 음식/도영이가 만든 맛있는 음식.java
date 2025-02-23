import java.io.*;
import java.util.*;

public class Main {
	static int N, S, B;
	static int[][] foods;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		foods = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				foods[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		
		/* 로직 */
		for (int i = 1; i <= N; i++) {
			combi(0, 0, i, 1, 0);			
		}
	
		
		/* 출력 */
		System.out.print(min);
	}

	private static void combi(int cnt, int start, int R, int Smul, int Bsum) {
		if(cnt == R) {
			min = Math.min(min, Math.abs(Smul-Bsum));
			return;
		}
		for (int i = start; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combi(cnt+1, i+1, R, Smul*foods[i][0], Bsum+foods[i][1]);
			visited[i] = false;
		}
	}

}
