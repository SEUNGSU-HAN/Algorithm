import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[] kids;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			kids = new int[N+1];
			
			/* 로직 */
			int ans = 0;
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				kids[n] = kids[n-1]+1;
				if(ans < kids[n]) ans = kids[n];
			}
			
			/* 출력 */
			sb.append("#").append(test_case).append(" ").append(N-ans).append("\n");
		}
		
		System.out.print(sb);
	}

}
