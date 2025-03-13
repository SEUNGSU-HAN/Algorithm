import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static boolean[] visited;
	static int[] move, count;
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		move = new int[100_001];
		count = new int[100_001];
		Arrays.fill(move, -1);
		q = new ArrayDeque<>();
		
		/* 로직 */
		q.offer(N);
		move[N] = 0;
		count[N] = 1;
		while(!q.isEmpty()) {
			int v = q.poll();

			int[] next = {v-1, v+1, v*2};
			
			for (int nv : next) {
			    if (nv >= 0 && nv <= 100000) {
			    	if(move[nv] == -1) {
			    		move[nv] = move[v]+1;
			    		count[nv] = count[v];
			    		q.offer(nv);
			    	}
			    	else if(move[nv] == move[v]+1) count[nv] += count[v];
			    }
			}
			
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(move[K]).append("\n").append(count[K]);
		System.out.print(sb);
	}
}
