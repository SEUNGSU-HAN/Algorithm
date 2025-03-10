import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] count; //집입 차수
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		count = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < n-1; j++) {
				for (int k = j+1; k < n; k++) {
					graph[arr[j]].add(arr[k]);
					count[arr[k]]++;
				}
			}
		}
		queue = new ArrayDeque<>();
		
		/* 로직 */
		//위상 정렬 사용
		//1. 진입 차수 0인 애들 찾기
		for (int i = 1; i <= N; i++) if(count[i] == 0) queue.offer(i);
		
		//2. 위상 정렬 스타트
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			cnt++;
			sb.append(cur).append("\n");
			
			//진출 가지 지우기
			for (int next : graph[cur]) {
				if(--count[next] == 0) queue.offer(next);
			}
		}
		
		/* 출력 */
		if(cnt != N) System.out.print(0);
		else System.out.print(sb);
	}

}
