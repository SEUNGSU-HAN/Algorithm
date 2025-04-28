import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[] isPrime;
	static int[] prime;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		/* 로직 */
		if(N <= 3) {
			if(N == 1) System.out.print(0);
			else System.out.print(1);
			return;
		}
		
		//1.단 소수를 구해
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if(isPrime[i]) {
				for (int j = i*2; j <= N; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		//소수만 따로 파악해서 배열로 모아두자
		int pCount = 0;
		for (int i = 2; i <= N; i++) if(isPrime[i]) pCount++;
		prime = new int[pCount];
		int idx = 0;
		for (int i = 2; i <= N; i++) if(isPrime[i]) prime[idx++]=i;
		
		//2.제 투포인터를 써
		int s = 0, e = 0, sum = prime[0], ans = 0;
		while(s < pCount) {
			if(sum == N) ans++;
			if(sum < N && e+1 < pCount) sum += prime[++e];
			else sum -= prime[s++];
		}
		
		/* 츨력 */
		System.out.print(ans);
		
	}

}
