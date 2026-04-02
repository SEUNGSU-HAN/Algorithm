import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class Edge {
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static ArrayList<Edge>[] outDegree; //진출
	static int[] inDegree; //진입 차수
	static int[][] basicPart; //최종 기본 부품 개수(dp)
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		outDegree = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			outDegree[i] = new ArrayList<>();
		}
		inDegree = new int[N+1];
		basicPart = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			outDegree[y].add(new Edge(x, k));
			inDegree[x]++;
		}
		
		/* 로직 */
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		//진입차수 0인 애들부터 시작
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				dq.offer(i);
				basicPart[i][i] = 1; //기본 부품 표기
			}
		}
		
		//위상정렬 시작
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			for(Edge next : outDegree[cur]) {
				for(int i=1; i<=N; i++) {
					if(basicPart[cur][i] > 0) {
						basicPart[next.v][i] += basicPart[cur][i] * next.w;
					}
				}
				
				if(--inDegree[next.v] == 0) {
					dq.offer(next.v);
				}
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(basicPart[N][i] > 0)
				sb.append(i).append(" ").append(basicPart[N][i]).append("\n");
		}
		System.out.print(sb.toString());
	}

}
