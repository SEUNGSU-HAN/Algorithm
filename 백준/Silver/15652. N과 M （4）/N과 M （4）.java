import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//중복 순열(nHr)
public class Main {
	static int N, M;
	static int[] nums;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		sb = new StringBuilder();
		
		perm(0, 0);
		
		System.out.println(sb.toString());
	}

	private static void perm(int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			nums[cnt] = i+1;
			perm(cnt+1, i);
			nums[cnt] = 0;
		}
	}

}
