import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, P;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		/* 초기화 */
		dp = new long[101][101];
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */	
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			
			/* 로직 */
			sb.append("#").append(test_case).append(" ").append(candy(N, P)).append("\n");
			
		}
		/* 출력 */
		System.out.print(sb.toString());
	}

	static long candy(int n, int p) {
		if(dp[n][p] != 0) return dp[n][p];
		
		if(p == 1) {
			return dp[n][p] = n;
		}
		
		int t = n/p;
		return dp[n][p] = t*candy(n-t, p-1);
	}

}
