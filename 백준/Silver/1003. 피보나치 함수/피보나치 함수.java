import java.io.*;
import java.util.*;

public class Main {
	static int T;
//	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		/* 초기화 */
//		dp = new int[41];
//		dp[0] = 1; dp[1] = 1;
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		// 재귀 ver
		/*
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			fibo(n);
			if(n == 0) sb.append(1).append(" ").append(0).append("\n");
			else if(n == 1) sb.append(0).append(" ").append(1).append("\n");
			else sb.append(dp[n-2]).append(" ").append(dp[n-1]).append("\n");
		}
		*/
		
		//반복문 ver
		int[] dp0 = new int[41];
		int[] dp1 = new int[41];
		dp0[0] = 1; dp1[0] = 0;
		dp0[1] = 0; dp1[1] = 1;
		for (int i = 2; i < 41; i++) {
			dp0[i] = dp0[i-2]+dp0[i-1];
			dp1[i] = dp1[i-2]+dp1[i-1];			
		}
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp0[n]).append(" ").append(dp1[n]).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}


//	static int fibo(int n) {
//		if(n == 0) {
//			return dp[0];
//		}else if(n == 1) {
//			return dp[1];
//		}
//		
//		if(dp[n] != 0) {
//			return dp[n];
//		}
//		
//		return dp[n] = fibo(n-1)+fibo(n-2);
//	}

}
