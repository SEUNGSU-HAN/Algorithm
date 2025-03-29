import java.io.*;
import java.util.*;

public class Main {
	static int K, size;
	static int[] city;
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = (int)Math.pow(2, K)-1;
		city = new int[size];
		for (int i = 0; i < size; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		q = new ArrayDeque<>();
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		int d = K-2;
		q.offer(size/2);
		while(!q.isEmpty()) {
			int n = q.size();
			while(n-- > 0) {
				int idx = q.poll();
				sb.append(city[idx]+" ");
				if(d >= 0) {
					int nd = (int)Math.pow(2, d);
					q.offer(idx-nd);
					q.offer(idx+nd);
				}
			}
			sb.append("\n");
			d--;
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
