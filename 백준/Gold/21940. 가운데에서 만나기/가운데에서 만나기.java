import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 9999999;
	static int N, M, K;
	static int[][] dist;	
	static int[] people;

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
		
		int min = INF;
		int[] countries = new int[N+1];
		for(int i=1; i<=N; i++) {
			int max = 0;
			boolean can_gather = true;// 왕복이 불가능한 경우를 걸러내기 위한 플래그
			
			for(int j=0; j<K; j++) {
				int p = people[j];
				
				//갈 수 없거나 올 수 없으면 패스
				if (dist[p][i] == INF || dist[i][p] == INF) {
					can_gather = false;
                    break;
                }
				max = Math.max(max, dist[p][i] + dist[i][p]);
			}
			
			if (can_gather) {
				countries[i] = max;
				min = Math.min(min, max); // 가장 작은 왕복 시간 갱신
            } else countries[i] = INF; // 모일 수 없는 도시는 INF 처리
            
		}

		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(min == countries[i]) sb.append(i).append(" ");
		}
		
		System.out.print(sb.toString());
		
	}

}
