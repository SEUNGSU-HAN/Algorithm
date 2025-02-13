import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int Test;
	static int N, L;
	static int[] T;
	static int[] K;
	static int maxT;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Test = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= Test; test_case++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			T = new int[N];
			K = new int[N];

			/* 초기화 */
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				T[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 로직 */
			maxT = 0;
			subSet(0, 0, 0);
			
			/* 출력 */
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(maxT).append("\n");
			System.out.print(sb);
		}
	}

	private static void subSet(int cnt, int Ttot, int Ktot) {
		if(Ktot > L) return;
		if(cnt == N) {
			maxT = Math.max(maxT, Ttot);
			return;
		}
		subSet(cnt+1, Ttot+T[cnt], Ktot+ K[cnt]);
		subSet(cnt+1, Ttot, Ktot);
	}

}
