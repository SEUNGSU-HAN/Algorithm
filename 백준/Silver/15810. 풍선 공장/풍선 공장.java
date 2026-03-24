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
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		//이분 탐색을 써서 풀어야 함
		Arrays.sort(A);
		
		long low = 1;
		long high = (long)A[0]*M;
		
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
		}
		return count;
	}

}
