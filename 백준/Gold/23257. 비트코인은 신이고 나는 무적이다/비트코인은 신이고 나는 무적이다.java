import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] A;
	static boolean[][] dp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Math.abs(Integer.parseInt(st.nextToken()));
		}
		
		dp = new boolean[M+1][1024];
		
		/* 로직 */		
		dp[0][0] = true;
		
		for(int i=1; i<=M; i++) {			
			for(int j=0; j<1024; j++) {
				if(dp[i-1][j]) {
					for(int n : A) {
						dp[i][j ^ n] = true;
					}
				}
			}
		}
		
		int m = 0;
		for(int j=1023; j>=0; j--) {
			if(dp[M][j]) m = Math.max(m, j);
		}
		
		/* 출력 */
		System.out.print(m);
	}

}
