import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] outDegree; //진출
	static int[] inDegree; //진입 차수
	static int[][] reqPart; //필요 부품 개수
	static int[][] basicPart; //최종 기본 부품 개수(dp)
	static boolean[] isBasic; //기본 부품 여부
	
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
		reqPart = new int[N+1][N+1];
		basicPart = new int[N+1][N+1];
		isBasic = new boolean[N+1];
		
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			reqPart[x][y] += k;
			outDegree[y].add(x);
			inDegree[x]++;
		}
		
		/* 로직 */
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		//진입차수 0인 애들부터 시작
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				dq.offer(i);
				isBasic[i] = true;
			}
		}
		
		//위상정렬 시작
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			for(int next : outDegree[cur]) {
				if(isBasic[cur]) 
					basicPart[next][cur] = 1*reqPart[next][cur];
				else {
					 for(int i=1; i<=N; i++) {
						 if(basicPart[cur][i] != 0) 
							 basicPart[next][i] += basicPart[cur][i]*reqPart[next][cur];
					 }
				}
				
				if(--inDegree[next] == 0) {
					dq.offer(next);
				}
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(isBasic[i]) sb.append(i).append(" ").append(basicPart[N][i]).append("\n");
		}
		System.out.print(sb.toString());
	}

}
