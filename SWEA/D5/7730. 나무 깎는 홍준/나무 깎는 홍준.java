import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M;
	static long[] tree;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			tree = new long[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 로직 */
			Arrays.sort(tree);
			long s = 0;
			long e = tree[N-1];
			while(s < e) {
				long H = (s+e)/2;
				
				long sum = 0;
				for (int i = N-1; i >= 0; i--) {
					if(tree[i]-H <= 0) break;
					sum += (tree[i]-H);
				}
				
				if(sum >= M) s = H+1;
				else e = H;
			}
			
			sb.append("#").append(test_case).append(" ").append(e-1).append("\n");
		}
		
		/* 츨력 */
		System.out.print(sb);
	}

}
