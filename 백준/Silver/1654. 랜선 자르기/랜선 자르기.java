import java.io.*;
import java.util.*;

public class Main {
	static int N, K, maxLen;
	static long result;
	static int[] nums;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine().trim());
			maxLen = Math.max(maxLen, nums[i]);
		}
		
		/* 로직 */
		//1. 가장 작은 값을 기준으로 이분 탐색(자를 길이를 찾음)

		//1-1 반복 구현
		long s = 0;
		long e = maxLen;
		while(s <= e) {
			int count = 0;
			long mid = (s+e)/2 + (s+e)%2;
			for (int j = 0; j < N; j++) {
				count += nums[j]/mid;
			}
			if(count >= K) {
				//최대 길이 값 갱신
				result = Math.max(result, mid);
				//오른쪽을 선택
				s = mid+1;
			}else {
				//왼쪽을 선택
				e = mid-1;
			}
		}
		
		/* 출력 */
		System.out.print(result);
	}

}
