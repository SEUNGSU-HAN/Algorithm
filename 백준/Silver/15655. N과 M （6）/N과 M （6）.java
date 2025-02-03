import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//순열 nPr -> 단, 중복 금지(visited 활용 + start 사용)
public class Main {
	static int N, M;
	static int[] p, nums;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N];
		nums = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		sb = new StringBuilder();
		Arrays.sort(p);
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
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = p[i];
			perm(cnt+1, i+1);
			nums[cnt] = 0;
			visited[i] = false;
			
		}
	}

}
