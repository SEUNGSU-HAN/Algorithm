import java.io.*;
import java.util.*;

public class Main {
	static int N, maxHigh;
	static int[] peoples;
	static int[][] dp;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		peoples = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[s].add(e);
			graph[e].add(s);
		}
		
		dp = new int[N+1][2]; //각마을의 일반/우수일 때의 최대 주민 수
		
		/* 로직 */
		
		dfs(1, 0);
		
		/* 출력 */
		System.out.print(Math.max(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int c, int p) { //현재, 부모
		dp[c][0] = 0; //일반 마을일 때
		dp[c][1] = peoples[c]; //우수마을 일때 내 인원 포함
		
		for(int next : graph[c]) {
			if(next == p) continue; //방문 처리
			
			dfs(next, c); //끝까지 파고 내려감
			
			//가장 최 하단에서 계산 시작
			//일반 마을 일때는 내꺼에다가 자식의 일반/우수 경우 중 더 값이 큰 값을 먹음
			//이렇게 하면 계속 일반이 겹치면서 조건3을 어길 걱정없음
			dp[c][0] += Math.max(dp[next][0], dp[next][1]);
			//우수 마을 일때는 자식은 반드시 일반 마을이어야하니 일반 마을 값을 더함
			dp[c][1] += dp[next][0];
		}
	}

}
