import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M;
	static int[] count;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 0; test_case < T; test_case++) {
			/* 입력+초기화 */
			N = Integer.parseInt(br.readLine().trim());
			graph = new ArrayList[N+1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			count = new int[N+1];
			//작년 순서 기입
			String[] sl = br.readLine().split(" ");
			for (int j = 0; j < N-1; j++) {
				int s = Integer.parseInt(sl[j]);
				for (int k = j+1; k < N; k++) {
					int e = Integer.parseInt(sl[k]);
					graph[s].add(e);
					count[e]++;
				}
			}
			
			M = Integer.parseInt(br.readLine().trim());
			//올해 순서 업뎃
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int t1 = Integer.parseInt(st.nextToken());
				int t2 = Integer.parseInt(st.nextToken());
				//작년 순위 그래프에서 업뎃
				if(graph[t2].contains(t1)) {
					graph[t2].remove((Integer)t1);
					count[t1]--;
					graph[t1].add(t2);
					count[t2]++;
				}else {
					graph[t1].remove((Integer)t2);
					count[t2]--;
					graph[t2].add(t1);
					count[t1]++;
				}
				
			}
			q = new ArrayDeque<>();
			
			/* 로직 */
			//진입 차수 0 찾기
			for (int i = 1; i <= N; i++) if(count[i] == 0) q.offer(i);
			
			//위상 정렬 스타트
			StringBuilder sb2 = new StringBuilder();
			int cnt = 0;
			boolean isCant = false;
			while(!q.isEmpty()) {
				//큐에 2개 이상 들어있으면 나올 수 있는 경우의 수가 여러개
				// -> 순위는 일정하게 하나여야만함
				//나올 수 있는 수의 경우가 2개일 순 없음
				//그래서 큐에 2개 이상 들어있으면 ? 출려
				if(q.size() > 1) {
					isCant = true;
					break;
				}
				
				int cur = q.poll();
				cnt++;
				sb2.append(cur).append(" ");
				
				for (int next : graph[cur]) {
					if(--count[next] == 0) q.offer(next);
				}

			}
			sb2.append("\n");
			
			if(isCant) sb.append("?\n");
			else if(cnt != N) sb.append("IMPOSSIBLE\n");
			else sb.append(sb2.toString());
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
