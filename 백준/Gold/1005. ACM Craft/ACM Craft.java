import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, N, K, W;
	static int[] time;
	static int[] dp; //각 건물이 완성되기까지 걸리는 시간 (최소 시간)
	static ArrayList<Integer>[] graph;
	static int[] count; //각 노드의 진입 차수;
	static Queue<Integer> queue; //집입차수 0인 노드들을 담아둘 큐

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			time = new int[N+1];
			dp = new int[N+1]; // 초기값 자기 자신
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				dp[i] = time[i];
			}
			graph = new ArrayList[N+1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			count = new int[N+1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				graph[s].add(e);
				count[e]++;
			}
			W = Integer.parseInt(br.readLine().trim());
			queue = new ArrayDeque<>();
			
			/* 로직 */
			//위상 정렬 사용
			//1. 진입 차수가 0인 애들을 큐에 추가 (시작시에 한번만 별도로 찾음)
			for (int i = 1; i <= N; i++) {
				if(count[i] == 0) {
					queue.offer(i);
				}
			}
			
			//2. 위상 정렬 스타트
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				//현재 노드가 가리키는 다음 노드들의 dp값 갱신
				//현재노드의 dp값과 다음 노드의 dp값을 합한 값과, 다음 노드의 dp값 비교
				//그 중 큰 값으로 갱신
				//이전 건물이 오래 걸린다? -> 다음 건물은 이전 건물 시간으로 갱신
				//이전 건물이 빨리 끝난다? -> 이전 건물 시간 + 다음 건물 시간으로 갱신
				for (int next : graph[cur]) {
					dp[next] = Math.max(dp[next], dp[cur]+time[next]);
					
					//진입 차수 감소
					count[next]--;
					
					//집입 차수 0이면 큐에 추가
					if(count[next] == 0) queue.offer(next);
				}
				
				
			}
			
			sb.append(dp[W]).append("\n");
		}
		/* 출력 */
		System.out.print(sb);		
	}


}
