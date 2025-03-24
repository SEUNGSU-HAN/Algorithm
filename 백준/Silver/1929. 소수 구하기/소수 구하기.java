import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static boolean[] prime;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		prime = new boolean[N+1];
		Arrays.fill(prime, true);
		
		/* 로직 */
		for (int i = 2; i <= (int)Math.sqrt(N); i++) {
			if(!prime[i]) continue;
			int idx = 2;
			while(i*idx <= N) {
				prime[i*idx++] = false;
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = M; i <= N; i++) {
			if(i != 1 && prime[i]) sb.append(i).append("\n");
		}
		System.out.print(sb);
	}

}
