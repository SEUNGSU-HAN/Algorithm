import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 9999999;
	static int N, M, K;
	static class Country {
		int n, max_time; //도시번호, 해당도시의 왕복시간들 중 최대 값
		
		public Country(int n, int max_time) {
			this.n = n;
			this.max_time = max_time;
		}
	}
	static int[][] dist;	
	static int[] people;
	static PriorityQueue<Country> countries;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			dist[s][e] = v;
		}
		
		K = Integer.parseInt(br.readLine());
		
		people = new int[K];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int c = Integer.parseInt(st.nextToken());
			people[i] = c;
		}
		
		countries = new PriorityQueue<>((o1, o2) -> o1.max_time == o2.max_time ? o1.n - o2.n : o1.max_time - o2.max_time);
		
		/* 로직 */
		//플로이드 워샬 알고리즘 사용
		for(int k=1; k<=N; k++) {			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
			
		}
		for(int i=1; i<=N; i++) {
			int max = 0;
			for(int j=0; j<K; j++) {
				max = Math.max(max, dist[i][people[j]]+dist[people[j]][i]);
			}
			
			
			countries.offer(new Country(i, max));
		}

		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		int pre = countries.peek().max_time;
		while(!countries.isEmpty()) {
			Country c = countries.poll();
			
			if(pre != c.max_time)break;
			
			sb.append(c.n).append(" "); 
			pre = c.max_time;
		}
		
		System.out.print(sb.toString());
		
	}

}
