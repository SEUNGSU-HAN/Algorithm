import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] dp;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */ 
		dp = new long[N+1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		dp[1] = 1;
		
		/* 로직+출력 */ 
		System.out.print(fibo(N-1));	
	}

	static long fibo(int n) {
		if(dp[n] == -1) return dp[n] = fibo(n-1)+fibo(n-2);
		return dp[n];
	}

}
