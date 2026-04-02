import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] nums;
	static boolean[] goodNums;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		goodNums = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(nums);
		
		int count = 0;
		if(N > 2) {
			for(int i=0; i<N; i++) {
				int l=0, r=N-1;
				
				//투포인터 실시
				while(l < r) { 
					//포인터의 위치가 현재 index의 위치인지 판단
					if(l == i || r == i) {
						if(l == i) l++;
						else r--;
						continue;
					}
					
					//포인터들 위치의 값 합이 타겟 값인지 확인
					int cur = nums[l] + nums[r];
					
					if(cur < nums[i]) l++;
					else if(cur > nums[i]) r--;
					else {
						count++;
						break;
					}
				}
			}
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
