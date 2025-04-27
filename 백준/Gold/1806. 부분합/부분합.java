import java.io.*;
import java.util.*;

public class Main {
	static int N, S, minLen;
	static int[] nums;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		minLen = N+1;
		
		/* 로직 */
		int s = 0, e = 0, curLen = 1;
		int sum = nums[0];
		while(s < N) {
			if(sum >= S) {
				if(curLen < minLen) {
					//최소 길이 갱신
					minLen = curLen;
				}
				//값을 충족하니 길이를 줄여
				sum -= nums[s++];
				curLen--;
				
			}else {
				//일단 값부터 만족해야하니 합 증가 + 길이 증가
				if(e+1 < N) {
					sum += nums[++e];
					curLen++;					
				}else {
					sum -= nums[s++];
					curLen--;
				}
			}
		}
		
		/* 출력 */
		System.out.print(minLen == N+1 ? 0 : minLen);
	}

}
