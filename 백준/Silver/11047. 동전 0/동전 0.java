import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] coin;
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		coin = new int[N];
		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		/* 로직 */
		int count = 0;
		for (int i = N-1; i >= 0; i--) {
			if(K == 0) break;
			count += K/coin[i];
			K = K%coin[i];
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
