import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		dp = new int[N+1];
		
		/* 로직 */
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1]+1;
			
			if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
			if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
		}
		
		/* 출력 */
		System.out.print(dp[N]);
	}

}
