import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long T;
	static final long MOD = 1_000_000_007;
	static long[] nums;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nums = new long[N+1];
		nums[0] = 1;
//		long x = factorial(N-K)*factorial(K);
		for (int i = 1; i <= N; i++) {
			nums[i] = (nums[i-1]*i)%MOD;
		}
		
		System.out.println(((nums[N])*ferma((nums[N-K]*nums[K])%MOD, MOD-2))%MOD);
	}

	private static long ferma(long x, long y) {
		if(y == 1) return x;
		else if(y%2 == 0) {
			T = ferma(x, y/2)%MOD;
			return (T*T)%MOD;
		}
		else {
			T = ferma(x, (y-1)/2)%MOD;
			return ((x*T)%MOD*T)%MOD;
		}
	}

}
