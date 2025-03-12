import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] nums, dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		nums = new int[N+1];
		dp = new int[N+1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine().trim());
		}
		dp[1] = nums[1];
		if(N >= 2) dp[2] = nums[1]+nums[2];
		
		/* 로직 */
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+nums[i-1])+nums[i];
		}
		
		/* 출력 */
		System.out.print(dp[N]);
	}

}
