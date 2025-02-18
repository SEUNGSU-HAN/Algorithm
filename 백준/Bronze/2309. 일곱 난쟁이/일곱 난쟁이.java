import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N = 9, R = 7;
	static int[] height;
	static int[] hobit;
	static int[] result;
	static boolean[] visited;
	static boolean flag;

	public static void main(String[] args) throws Exception{
		/* 입력+초기화*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		height = new int[N];
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(br.readLine().trim());
		}
		hobit = new int[R];
		result = new int[R];
		visited = new boolean[N];
		
		/* 로직 */
		combi(0, 0, 0);
		Arrays.sort(result);
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.print(sb);
		
	}

	private static void combi(int cnt, int start, int tot) {
		if(flag) return;
		
		if(cnt == R) {
			if(tot == 100) System.arraycopy(hobit, 0, result, 0, hobit.length);
			return;
		}
		for (int i = start; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			hobit[cnt] = height[i];
			combi(cnt+1, i+1, tot+height[i]);
			hobit[cnt] = 0;
			visited[i] = false;
		}		
	}

}
