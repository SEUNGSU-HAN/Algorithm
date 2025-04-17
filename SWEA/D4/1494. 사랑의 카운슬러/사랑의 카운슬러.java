import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static long result;
	static class Point {
		long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	static Point[] warm;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			result = Long.MAX_VALUE;
			warm = new Point[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				warm[i] = new Point(x, y);
			}
			visited = new boolean[N];
			
			/* 로직 */
			loveline(0, 0);
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

	static void loveline(int cnt, int flag) {
		if(Integer.bitCount(flag) > N/2) return;
		if(cnt == N) {
			if(Integer.bitCount(flag) != N/2) return;
			
			long sx=0, sy=0, ex=0, ey=0;
			for (int i = 0; i < N; i++) {
				if((flag & 1 << i) > 0) {
					sx += warm[i].x;
					sy += warm[i].y;
				}else {
					ex += warm[i].x;
					ey += warm[i].y;
				}
			}
			
			long vx = ex-sx;
			long vy = ey-sy;
			long v = vx*vx + vy*vy;
			result = Math.min(result, v);
			return;
		}
		loveline(cnt+1, flag | 1 << cnt);
		loveline(cnt+1, flag);
	}
}
