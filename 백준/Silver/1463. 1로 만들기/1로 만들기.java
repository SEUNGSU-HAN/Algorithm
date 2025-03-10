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
//		dp = new int[N+1];
		
		/* 로직 */
		/* 반복문 ver
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1]+1;
			
			if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
			if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
		}
		*/
		
		//재귀 ver
		System.out.print(recur(N, 0));
		
		/* 출력 */
//		System.out.print(dp[N]);
	}

	static int recur(int n, int count) {
		if(n < 2) return count;
		return Math.min(recur(n/2, count+1+(n%2)), recur(n/3, count+1+(n%3)));
	}

}
