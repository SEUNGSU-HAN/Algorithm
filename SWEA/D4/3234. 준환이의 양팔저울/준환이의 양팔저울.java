import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int Test;
	static int N;
	static int[] weight;
	static boolean[] visited;
	static int[] lscale, rscale;
	static long result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Test = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= Test; test_case++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			weight = new int[N];
			visited = new boolean[N];
			
			lscale = new int[N];
			rscale = new int[N];
			
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 로직 */
			perm(0, 0, 0);
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(result);
			System.out.println(sb);
		}
	}
    
	private static void perm(int cnt, long Ltot, long Rtot) {
		if(cnt == N) {
			//subset 나누기
			subset(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			lscale[cnt] = weight[i];
			perm(cnt+1, Ltot+weight[i], Rtot);
			lscale[cnt] = 0;
			visited[i] = false;
		}
	}

    private static void subset(int cnt, int Ltot, int Rtot) {
        if(cnt == N) {
            result++;
            return;
        }
        subset(cnt+1, Ltot+lscale[cnt], Rtot);
        if(Ltot < (Rtot+lscale[cnt])) return;
        subset(cnt+1, Ltot, Rtot+lscale[cnt]);
    }

}
