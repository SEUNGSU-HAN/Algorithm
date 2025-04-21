import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] talls, cutPoint;
	static class Diff implements Comparable<Diff>{
		int idx, diff;

		public Diff(int idx, int diff) {
			this.idx = idx;
			this.diff = diff;
		}

		@Override
		public String toString() {
			return "Diff [idx=" + idx + ", diff=" + diff + "]";
		}

		@Override
		public int compareTo(Diff o) {
			return -Integer.compare(this.diff, o.diff);
		}
	}
	static Diff[] diff;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		/* 초기화 */
		talls = new int[N];
		diff = new Diff[N-1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			talls[i] = Integer.parseInt(st.nextToken());
		}
		cutPoint = new int[K-1];
		
		/* 로직 */
		if(N == 1 || K == N) {
			System.out.print(0);
			return;
		}
		int i = 0, j = 1;
		while(j < N && i < j) {
			diff[i] = new Diff(i, Math.abs(talls[i]-talls[j]));
			i++; j++;
		}
		Arrays.sort(diff);
		for (int k = 0; k < K-1; k++) {
			cutPoint[k] = diff[k].idx;
		}
		Arrays.sort(cutPoint);
		
		int ans = 0;
		i = 0; j = 0;
		for (int k = 0; k < K-1; k++) {
			j = cutPoint[k];
			ans += Math.abs(talls[i]-talls[j]);
//			System.out.println("i: " + i + ", j: " + j );
			i = j+1;
		}
		ans += Math.abs(talls[i]-talls[N-1]);
		
		/* 출력 */
		System.out.print(ans);
	}

}
