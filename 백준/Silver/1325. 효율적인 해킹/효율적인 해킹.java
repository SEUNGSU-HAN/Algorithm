import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] com;
	static boolean[] visited;
	static int max;
	static int[] maxIdx;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		com = new ArrayList[N+1]; //타입은 안정하지 않지만 코테에선 쓸만할 듯?
		for (int i = 0; i <= N; i++) {
			com[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			com[a].add(b);
		}
		visited = new boolean[N+1];
		maxIdx = new int[N+1];
		
		/* 로직 */
		for (int i = 1; i <= N; i++) {
			if(com[i].isEmpty()) continue;
			//bfs
			Queue<Integer> q = new LinkedList<>();
			q.offer(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				for (int j = 0; j < com[cur].size(); j++) {
					int ni = com[cur].get(j);
					if(visited[ni]) continue;
					q.offer(ni);
					visited[ni] = true;
					maxIdx[ni]++;
				}
			}
			
			visited = new boolean[N+1];
		}
		
		//최댓값만 추려내기 (index순이라 순서는 자동임)
		for (int d : maxIdx) {
			if(d != 0) max = Math.max(max, d);
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= N; i++) {
			if(maxIdx[i] == max) sb.append(i + " ");
		}
		System.out.println(sb.toString());
	}

}
