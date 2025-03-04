import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result;
	static int[] p;
	

	public static void main(String[] args) throws Exception{
		//--------------솔루션 코드를 작성하세요.--------------------------------
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		p = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		findSum(0, 0, 0, 0);
		
		/* 출력 */
		System.out.print(result);
	}


	static void findSum(int cnt, int start, int tot, int flag) {
		if(tot > M) return;
		
		if(cnt == 3) {
			result = Math.max(result, tot);
			return;
		}
		
		for (int i = start; i < N; i++) {
			if((flag & 1<<i) != 0) continue;
			findSum(cnt+1, i, tot+p[i], (flag | 1<<i));
		}
	}

}

