import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K;
	static int[] scores;
	public static void main(String[] args) throws  Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			scores = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 로직 */
			Arrays.sort(scores);
			int sum = 0;
			for (int i = 1; i <= K; i++) {
				sum += scores[N-i];
			}
			
			/* 출력 */
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.print(sb);
	}

}
