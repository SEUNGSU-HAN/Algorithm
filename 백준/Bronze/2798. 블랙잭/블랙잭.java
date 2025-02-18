import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int N,M;
	static int[] nums;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		getAns(0,0,0);
		System.out.println(max);
	}
	private static void getAns(int depth,int start,int sum) {
		if(sum>M) return;
		if(depth==3) {
			max = Math.max(max,sum);
			return;
		}
		for (int i = start; i < N; i++) {
			getAns(depth+1,i+1,sum+nums[i]);
		}
	}
}
