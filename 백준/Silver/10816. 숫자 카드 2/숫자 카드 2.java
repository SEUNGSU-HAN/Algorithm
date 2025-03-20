import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(nums);
		M = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			int lb = lowerBound(n);
			int ub = upperBound(n);
			sb.append(ub-lb).append(" ");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

	static int upperBound(int target) {
		int s = 0, e = N, m;
		while(s < e) {
			m = (s+e)/2;
			if(target >= nums[m]) s = m+1;
			else e = m;
		}
		return e;
	}

	static int lowerBound(int target) {
		int s = 0, e = N, m;
		while(s < e) {
			m = (s+e)/2;
			if(target <= nums[m]) e = m;
			else s = m+1;
		}
		return s;
	}

}
