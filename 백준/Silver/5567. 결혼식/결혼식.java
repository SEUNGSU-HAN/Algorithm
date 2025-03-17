import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		//--------------솔루션 코드를 작성하세요.--------------------------------
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);			
		}
		visited = new boolean[N+1];
		
		/* 로직 */
		result = bfs(1);
		
		/* 출력 */
		System.out.print(result);
	}

	static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		int count = -1;
		int cnt = 0;
		while(!q.isEmpty()) {
			int n = q.size();
			if(cnt++ == 3) break;
			while(n-- > 0) {
				int cur = q.poll();
				count++;
				for (int next : graph[cur]) {
					if(!visited[next]) {
						q.offer(next);
						visited[next] = true;
					}
				}
				
			}
		}
		return count;
	}

}
