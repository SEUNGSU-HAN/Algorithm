import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] time, dp, count; //건설 시간, 건물 시간 갱신테이블, 진입차수
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue; //건물 빌딩 큐
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		time = new int[N+1];
		dp = new int[N+1];
		count = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				if(n == -1) break;
				if(idx == 0) {
					time[i] = n;
					dp[i] = n;
				}
				else {
					graph[n].add(i);
					count[i]++;
				}
				idx++;
			}
		}
		queue = new ArrayDeque<>();
		
		/* 로직 */
		//위상 정렬 사용
		//1. 집이 차수 0인 노드 찾기
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) queue.offer(i);
		}
		
		//2. 위상 정렬 스타트
		while(!queue.isEmpty()) {
			int cur = queue.poll(); //현재 노드
			
			for (int next : graph[cur]) {
				dp[next] = Math.max(dp[next], dp[cur]+time[next]);
				
				if(--count[next] == 0) queue.offer(next);
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append("\n");
		}
		System.out.print(sb);
	}

}
