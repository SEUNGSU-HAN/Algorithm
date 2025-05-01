import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static long[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 0; test_case < T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			if(N <= 3) {
				sb.append(1).append("\n");
				continue;
			}
			dp = new long[N+1];
			dp[1] = dp[2] = dp[3] = 1;
			
			/* 로직 */
			for (int i = 4; i <= N; i++) {
				dp[i] = dp[i-3]+dp[i-2];
			}
			
			/* 출력 */
			sb.append(dp[N]).append("\n");
		}
		
		System.out.print(sb);
	}

}
