import java.io.*;
import java.util.*;

public class Main {
  static final int INF = 987654321;
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
        Arrays.fill(dp, INF);
		dp[0] = 0; dp[1] = 1;
		
		/* 로직 */
		for(int i=2; i<=N; i++) {
			for(int j=1; j*j<=i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
			}
		}
			
		/* 출력 */
		System.out.print(dp[N]);
	}

}
