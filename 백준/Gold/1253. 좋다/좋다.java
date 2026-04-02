import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(nums);
		
		int count = 0;
		
		for(int i=0; i<N; i++) {
			int l=0, r=N-1;
			
			//투포인터 실시
			while(l < r) { 
				//포인터의 위치가 현재 index의 위치인지 판단
				if(l == i) {
					l++;
					continue;
				}
				if(r == i) {
					r--;
					continue;
				}
				
				//포인터들 위치의 값 합이 타겟 값인지 확인
				int sum = nums[l] + nums[r];
				
				if(sum < nums[i])
					l++;
				else if(sum > nums[i])
					r--;
				else {
					count++;
					break;
				}
			}
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
