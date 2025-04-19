import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, result;
	static int[] tree;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			tree = new int[N];
			int maxTree = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxTree = Math.max(maxTree, tree[i]);
			}
			result = 0;
			
			/* 로직 */
			int odd = 0, even = 0;
			for (int t : tree) {
				int diff = maxTree-t;
				odd += diff%2;
				even += diff/2;
			}
			
			while(even-odd >= 2) {
				even--;
				odd += 2;
			}
			if(even >= odd) result = 2*even;
			else result = 2*odd - 1;
			
			/* 출력 */
			sb.append(String.format("#%d %d\n", test_case, result));
		}
		System.out.print(sb);
	}

}
