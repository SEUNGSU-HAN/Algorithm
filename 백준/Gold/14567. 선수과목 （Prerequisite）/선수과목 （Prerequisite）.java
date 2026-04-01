import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] inDegree;
	static ArrayList<Integer>[] outDegree;
	static int[] result;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N+1];
		outDegree = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			outDegree[i] = new ArrayList<>();
		}
		result = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			outDegree[a].add(b); //진출 노드 기록
			inDegree[b]++; //진입차수 증가
		}
		
		/* 로직 */
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				dq.offer(i);
				result[i] = 1;
			}
		}
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			//현재 노드가 진출하는 노드의 진입차수 제거하면서 체크
			for(int next : outDegree[cur]) {
				if(--inDegree[next] == 0) {
					dq.offer(next);
					result[next] = result[cur]+1;
				}
			}
		}
		
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.print(sb.toString());
	}

}
