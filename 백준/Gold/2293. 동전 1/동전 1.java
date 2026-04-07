import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		dp = new int[K+1];
		
		for(int i=0; i<N; i++) {
			int c = Integer.parseInt(br.readLine());
			coin[i] = c;
		}
		
		dp[0] = 1; //자기 자신일 경우가 dp[0]일 때
		
		/* 로직 */
		Arrays.sort(coin);
		
		//동전을 기준으로 탐색
		for(int c=0; c<N; c++) {
			//동전을 기준으로 k원을 맞추기 위한 경우를 dp함
			for(int k=coin[c]; k<=K; k++) {
				if(k >= c) dp[k] = dp[k] + dp[k-coin[c]];
			}
		}
		
		/* 출력 */
		System.out.print(dp[K]);
	}

}
