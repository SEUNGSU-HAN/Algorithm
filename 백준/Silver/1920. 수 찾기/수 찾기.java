import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(nums);
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			sb.append(Arrays.binarySearch(nums, n) < 0 ? 0 : 1).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
