import java.io.*;
import java.util.*;

//도영이가 만든 맛있는 음식
//단순 조합 문제 (N이 10이하 여서 가능함)
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
//		for (int i = 1; i <= N; i++) {
//			combi(0, 0, i, 1, 0);			
//		}
		
		//BackTracking 활용
		backTracking(0, 0, 1, 0);
	
		
		/* 출력 */
		System.out.print(min);
	}

	//cnt: depth(깊이), R: 고른 재료 개수, Smul: 신맛 곱, Bsum: 쓴맛 합
	private static void backTracking(int cnt, int R, int Smul, int Bsum) {
		if(cnt == N) {
			//고른 재료가 최소 1개 이상일 때
			if(R > 0) min = Math.min(min, Math.abs(Smul-Bsum));
			return;
		}
		backTracking(cnt+1, R+1, Smul*foods[cnt][0], Bsum+foods[cnt][1]); //새로운 재료 뽑기
		backTracking(cnt+1, R, Smul, Bsum); //재료 안뽑기 (재료는 그대로 두기에 깊이만 증가하고 나머진 그대로)
	}
	


//	private static void combi(int cnt, int start, int R, int Smul, int Bsum) {
//		if(cnt == R) {
//			min = Math.min(min, Math.abs(Smul-Bsum));
//			return;
//		}
//		for (int i = start; i < N; i++) {
//			if(visited[i]) continue;
//			visited[i] = true;
//			combi(cnt+1, i+1, R, Smul*foods[i][0], Bsum+foods[i][1]);
//			visited[i] = false;
//		}
//	}

}
