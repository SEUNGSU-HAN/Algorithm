import java.io.*;
import java.util.*;

public class Main {
	static int N, K, count;
	static int[] kit;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		kit = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		
		perm(0, 500, 0);
		
		/* 출력 */
		System.out.println(count);
	}

	static void perm(int cnt, int sum, int flag) {
		if(sum < 500) return;
		
		if(cnt == N) {
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1<<i) != 0) continue;
			
			//키트 사용
			perm(cnt+1, sum+kit[i]-K, flag|1<<i);

		}
	}

}
