import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static class Univ implements Comparable<Univ>{
		int n, d, p;
		
		public Univ(int n, int d, int p) {
			this.n = n;
			this.d = d;
			this.p = p;
		}

		@Override
		public int compareTo(Univ o) {
			return this.p == o.p ? Integer.compare(this.d, o.d) : Integer.compare(o.p, this.p);
		}
		
	}
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		
		/* 로직 */
		PriorityQueue<Univ> pq = new PriorityQueue<>();
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			pq.offer(new Univ(i, d, p));
		}
		
		int[] dayPay = new int[10001];
		
		while(!pq.isEmpty()) {
			Univ cur = pq.poll();
						
			for(int i=cur.d; i>=1; i--) {
				if(dayPay[i] == 0) {
					dayPay[i] = cur.p;
					break;
				}
			}
						
		}
		
		/* 출력 */
		int sum = 0;
		for(int d : dayPay) {
			sum += d;
		}
		System.out.print(sum);
	}

}
