import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int[] A; //규영이 카드
	static int[] B; //인영이 카드
	static int awin, bwin;
	static boolean[] visited;
	static boolean[] card;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			/* 입력 */
			/* 초기화 */
			st = new StringTokenizer(br.readLine());
			card = new boolean[19];
			A = new int[9];
			for (int i = 0; i < 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				card[A[i]] = true;
			}
			B = new int[9];
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if(!card[i]) B[idx++] = i;
			}
			visited = new boolean[9];
			awin = 0; bwin = 0;
			
			/* 로직 */
			//인영이가 카드 내는 모든 순서
			perm(0, 0, 0);
			
			/* 출력 */
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(awin).append(" ").append(bwin).append("\n");
			System.out.print(sb);
		}
		
	}


	private static void perm(int cnt, int as, int bs) {
		if(cnt == 9) {
			if(as > bs) awin++;
			else if(as < bs) bwin++;
			
			return;
		}
		for (int i = 0; i < 9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			if(A[cnt] > B[i]) {
				as += (A[cnt]+B[i]);
			}
			else {
				bs += (A[cnt]+B[i]);
			}
			perm(cnt+1, as, bs);
			visited[i] = false;
			if(A[cnt] > B[i]) {
				as -= (A[cnt]+B[i]);
			}
			else {
				bs -= (A[cnt]+B[i]);
			}
		}
	}

}
