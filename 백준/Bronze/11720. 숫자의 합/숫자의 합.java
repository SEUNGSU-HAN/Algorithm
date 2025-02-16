import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, sum;
	static char[] nums;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		nums = br.readLine().toCharArray();
		
		for (int i = 0; i < N; i++) {
			sum += nums[i] -'0';
		}
		
		System.out.print(sum);

	}

}
