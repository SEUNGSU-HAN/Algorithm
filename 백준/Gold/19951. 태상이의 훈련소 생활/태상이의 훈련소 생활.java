import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] ground;
	static int[] diff;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ground = new int[N+1];
		diff = new int[N+1];
		
		/* 초기화 */
		st = new StringTokenizer(br.readLine());
		for(int i=1; i <= N; i++) {
			ground[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			diff[a] += k;
			if(b+1 <= N) diff[b+1] -= k;
		}
		
		int sum = 0;
		for(int i=1; i <= N; i++) {
			sum += diff[i];
			ground[i] += sum;
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <=N; i++) {
			sb.append(ground[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
