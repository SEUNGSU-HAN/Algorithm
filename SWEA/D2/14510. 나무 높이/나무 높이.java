import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, maxTree;
	static int[] tree;
	static int[] diff;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			st = new StringTokenizer(br.readLine());
			tree = new int[N];
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(tree);
			maxTree = tree[N-1];
			diff = new int[N-1];
			
			/* 로직 */
			int odd = 0;
			int even = 0;
			for (int i = 0; i < N-1; i++) {
				diff[i] = maxTree-tree[i];
				odd += diff[i]%2;
				even += diff[i]/2;
			}
			int result = 0;
			if(odd == even) result = 2*odd;
			else if(odd > even) {
				result = 2*odd-1;
			}else {
				result += 2*odd;
				even -= odd;
				int remain = even*2;
				result = result + (remain/3)*2+(remain%3);
			}
			
			/* 출력 */
			System.out.println("#"+test_case+" "+result);
		}
		
		
	}

}
