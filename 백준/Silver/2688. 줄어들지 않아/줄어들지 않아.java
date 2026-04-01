import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[] testCase;
	static long[][] dp;
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		testCase = new int[T];
		
		int max = 0;
		for(int i=0; i<T; i++) {
			testCase[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, testCase[i]);
		}
		
		dp = new long[max+1][11];
		
		/* 로직 */
		Arrays.fill(dp[0], 1);
		dp[0][10] = 0;
		
		for(int i=1; i<=max; i++) {
			for(int j=9; j>=0; j--) {
				dp[i][j] = dp[i-1][j]+dp[i][j+1];
			}
		}
		
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int t : testCase) {
			sb.append(dp[t][0]).append("\n");
		}
		System.out.print(sb.toString());
	}

}
