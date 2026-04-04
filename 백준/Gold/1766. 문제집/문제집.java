import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] outDegree;
	static int[] inDegree;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		outDegree = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			outDegree[i] = new ArrayList<>();
		}
		inDegree = new int[N+1];
				
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			outDegree[s].add(e);
			inDegree[e]++;
		}

		/* 로직 */
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
		
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) pq.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			
			sb.append(cur).append(" ");
			
			for(int next : outDegree[cur]) {
				if(--inDegree[next] == 0) pq.offer(next);
			}
		}
		
		/* 출력 */
		System.out.print(sb.toString());
	}

}
