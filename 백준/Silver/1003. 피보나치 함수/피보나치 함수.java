import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		/* 초기화 */
		dp = new int[41];
		dp[0] = 1; dp[1] = 1;
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			fibo(n);
			if(n == 0) sb.append(1).append(" ").append(0).append("\n");
			else if(n == 1) sb.append(0).append(" ").append(1).append("\n");
			else sb.append(dp[n-2]).append(" ").append(dp[n-1]).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}


	static int fibo(int n) {
		if(n == 0) {
			return dp[0];
		}else if(n == 1) {
			return dp[1];
		}
		
		if(dp[n] != 0) {
			return dp[n];
		}
		
		return dp[n] = fibo(n-1)+fibo(n-2);
	}

}
