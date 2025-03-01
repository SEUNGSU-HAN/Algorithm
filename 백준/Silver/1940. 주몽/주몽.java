import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, count;
	static int[] nums; 

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		
		/* 초기화 */
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		//투포인터 문제인듯
		Arrays.sort(nums);
		int i = 0, j = N-1;
		while(i < j) {
			if(nums[i]+nums[j] == M) {
				i++;
				j--;
				count++;
			}else if(nums[i]+nums[j] > M) j--;
			else i++;
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
