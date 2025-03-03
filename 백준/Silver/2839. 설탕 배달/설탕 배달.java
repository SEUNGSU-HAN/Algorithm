import java.util.*;

public class Main {
	static int N, result = -1;
	static int[] bag;
	static int[][] dp;

	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		/* 초기화 */
		bag = new int[] {5, 3};
		dp = new int[2001][2001];
		for (int i = 0; i < 1001; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		/* 로직 */
		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 1001; j++) {
				dp[i][j] = 5*i+3*j;
				if(dp[i][j] == N) {
					if(result == -1) result = i+j;
					else result = Math.min(result, i+j);
				}
			}
		}
		
		/* 출력 */
		System.out.print(result);
	}


}
