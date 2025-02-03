import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		combi(0, 0);
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(nums[i] + " ");
			}
			System.out.println(sb.toString());
		}
		for (int i = start; i < N; i++) {
			nums[cnt] = i+1;
			combi(cnt+1, i+1);
			nums[cnt] = 0;
		}
		
	}

}
