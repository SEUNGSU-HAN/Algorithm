import java.io.*;
import java.util.*;

public class Main {
	static int N, R, Q;
	static ArrayList<Integer>[] graph;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		dp = new int[N+1];
		
		/* 로직 */
		
		dfs(R, 0); //루트 노드를 시작점
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		while(Q-- > 0) {
			int q = Integer.parseInt(br.readLine());
			
			sb.append(dp[q]).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void dfs(int cur, int parent) {
		dp[cur] = 1; //자기 자신 1 체크
		
		for(int next : graph[cur]) {
			if(next == parent) continue; //역으로 올라가는거 방지
			
			dfs(next, cur); //끝까지 내려감
			
			//끝에서부터 역으로 올라가면서 계산
			//자기 자신 기준으로 노드 개수는 자식이 센거 + 자기 자신
			dp[cur] += dp[next];
		}
	}

}
