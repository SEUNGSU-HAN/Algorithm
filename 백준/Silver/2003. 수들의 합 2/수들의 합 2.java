import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		
		int s = 0, e = 0, sum = 0, ans = 0;
		while(e < N) {
			if(sum < M) {
				sum += nums[e++];
			}
			
			if (sum > M){
				while(sum > M) {
					sum -= nums[s++];
				}	
			}
			
			if(sum == M) {
				ans++;
				sum -= nums[s++];
			}
			
		}
		
		/* 출력 */
		System.out.println(ans);
	}

}
