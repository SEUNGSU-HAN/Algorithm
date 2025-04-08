import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] W, V;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		dp = new int[K+1];
		W = new int[N+1];
		V = new int[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			W[i] = w;
			V[i] = v;
		}
		
		/* 로직 */
		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= W[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-W[i]]+V[i]);
			}
		}
		
		/* 출력 */
		System.out.print(dp[K]);
	}

}
