import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//조합 문제 nCr (단, 중복 x -> 방문 체크)
public class Main {
	static int N;
	static int M;
	static int[] p;
	static int[] nums;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[M];
		visited = new boolean[N];
		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(p);
		
		combi(0, 0);
		
		System.out.println(sb.toString());
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = p[i];
			combi(cnt+1, i);
			nums[cnt] = 0;
			visited[i] = false;
		}
	}

}
