import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] A;
	static long time;
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		
		int min_time = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if(A[i] < min_time) min_time = A[i];
		}
		
		/* 로직 */
		//이분 탐색을 써서 풀어야 함
		long low = 1;
		long high = (long)min_time*M;
		
		while(low <= high) {
			long mid = (low+high)/2;
			
			long balloon = count_balloon(mid);

			if(balloon >= M) {
				time = mid;
				high = mid-1;
			}
			else low = mid+1;
		}
		
		
		
		/* 출력 */
		System.out.print(time);
	}

	static long count_balloon(long t) {
		long count = 0;
		for(int i=0; i<N; i++) {
			count += t/A[i];
			if(count >= M) break;
		}
		return count;
	}

}
