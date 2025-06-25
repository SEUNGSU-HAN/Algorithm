import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		nums = new int[M];
		
		perm(0, 0);
		
		System.out.println(sb.toString());
	}

	static void perm(int cnt, int flag) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if((flag & 1<<i) != 0) continue;
			nums[cnt] = i;
			perm(cnt+1, flag | 1<<i);
		}
	}

}
