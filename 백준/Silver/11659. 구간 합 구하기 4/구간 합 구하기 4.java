

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static int N,M;
	static int[] nums;
	static int[] ans;
	static int start,end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		ans = new int [N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		ans[0] = nums[0];
		for (int i = 1; i < N; i++) {
			ans[i] = ans[i-1] + nums[i];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()) -1;
			end = Integer.parseInt(st.nextToken()) -1;
			int sum = 0;
			if (start == 0) {
				sum = ans[end];
			} else {
				sum = ans[end]-ans[start-1];
			}
			sb.append(sum +"\n");
		}
		System.out.println(sb.toString());
	}

}
