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
		
		//동전을 기준으로 탐색
		for(int i=0; i<N; i++) {
			int c = coin[i];
			
			// 현재 동전의 가치부터 목표 금액 K까지 누적
			for(int k=c; k<=K; k++) {
				dp[k] = dp[k] + dp[k-c];
			}
		}
		
		/* 출력 */
		System.out.print(dp[K]);
	}

}
