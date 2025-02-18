import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] temper;
	static int conSum;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		temper = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			temper[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		int s = 0, e = 0;
		while(e < K) conSum+=temper[e++];
		
		max = conSum;
		while(e < N) {
			conSum = conSum-temper[s++]+temper[e++];
			max = Math.max(max, conSum);
		}
		
		/* 출력 */
		System.out.print(max);
	}

}
