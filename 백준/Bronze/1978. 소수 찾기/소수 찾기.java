import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, result;
	static int[] nums;
	static boolean[] prime;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		int max = nums[nums.length-1];
		prime = new boolean[max+1];
		for (int i = 2; i < max+1; i++) {
			prime[i] = true;
		}
		
		for (int i = 2; i <= (int)Math.sqrt(max); i++) {
			for (int j = i+1; j <= max; j++) {
				if(!prime[j]) continue;
				if(j%i == 0) prime[j] = false;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(prime[nums[i]]) result++;
		}
		
		
		System.out.print(result);
	}

}
