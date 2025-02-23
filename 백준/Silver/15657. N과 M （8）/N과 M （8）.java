import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//N과 M(8)
//중복 조합
public class Main {
	static int N, M;
	static int[] p, nums;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		p = new int[N];
		nums = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(p);
		StringBuilder sb = new StringBuilder();
		nhr(0, 0, sb);
		
		/* 출력 */
		System.out.print(sb);
	}

	private static void nhr(int cnt, int start, StringBuilder sb) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			nums[cnt] = p[i];
			nhr(cnt+1, i, sb);
			nums[cnt] = 0;
		}
	}

}
