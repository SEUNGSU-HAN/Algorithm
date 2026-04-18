import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long min = Long.MAX_VALUE;
	static long[] nums, result;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new long[N];
		result = new long[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(nums);
		
		long sum = 0;
		
		loop:
		for(int i=0; i<N-2; i++) {			
			int s = i+1, e = N-1;
			
			while(s < e) {			
				sum = nums[i]+nums[s]+nums[e];
				
				if(Math.abs(sum) < min) {
					min = Math.abs(sum);
					result[0] = nums[i];
					result[1] = nums[s];
					result[2] = nums[e];
				}
				
				if(sum == 0) break loop;
				else if(sum < 0) s++;
				else e--;
				
			}
		}
		
		/* 출력 */
		Arrays.sort(result);
		StringBuilder sb = new StringBuilder();
		for(long x : result) {
			sb.append(x).append(" ");
		}
		
		System.out.print(sb);
	}

}
