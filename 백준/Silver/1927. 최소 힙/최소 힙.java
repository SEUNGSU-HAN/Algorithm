import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		q = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		/* 로직 */
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			if(n == 0) {
				Integer num = q.poll();
				sb.append(num == null ? 0 : num).append("\n");
			}else q.offer(n);
		}
		
		/* 출력 */
		System.out.print(sb);
		
	}

}
