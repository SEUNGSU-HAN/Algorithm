import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//수열 -> nPr

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[M];
		visited = new boolean[N];
		
		perm(0);
		
	}
	
	public static void perm(int cnt) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(nums[i] + " ");
			}
			System.out.println(sb.toString());
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			nums[cnt] = i+1;
			perm(cnt+1);
			nums[cnt] = 0;
			visited[i] = false;
		}
	}

}
