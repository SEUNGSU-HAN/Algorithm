import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static class Thing {
		int v, w;
		
		public Thing(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static Thing[] things;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		things = new Thing[N+1];
		
		for(int i=1; i<=N; i++) { 
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			things[i] = new Thing(v, w);
		}
		
		/* 로직 */
		//Knapsack을 해보자
		//가방의 1~K까지의 무게를 나눈 배열선언
		int[][] bag = new int[N+1][K+1];
		
		//넣을 물건들을 하나씩 늘려감
		for(int i=1; i<=N; i++) {
			//모든 무게에 대하여 넣으려는 물건을 검증
			for(int j=1; j<=K; j++) {
				//현재 가방 수용 무게(j)에 새롭게 넣으려는 물건(i)가 들어갈 수 있느냐? (뭐가 들어있든지 상관없이)
				if(things[i].w <= j) {
					//가장 직전에 넣은 경우의 가치와 새롭게 넣을 놈+기존에 있던놈가치 중 더 높은놈으로교체
					bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j-things[i].w]+things[i].v);
				}else
					bag[i][j] = bag[i-1][j];
			}
		}
		
		
		/* 출력 */
		System.out.print(bag[N][K]);
	}

}
