import java.io.*;
import java.util.*;

public class Main {
	static int n, x, count;
	static int[] nums;
	static int i, j;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		x = Integer.parseInt(br.readLine().trim());
		i = 0; j = n-1;
		
		/* 로직 */
		//1.단 정렬을 해 (오름차순)
		Arrays.sort(nums);
		//2.투 포인터 ㄱㄱ
		while(i < j) {
			if((nums[i]+nums[j]) > x) j--;
			else if((nums[i]+nums[j]) < x) i++;
			else {
				i++;
				j--;
				count++;
			}
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
