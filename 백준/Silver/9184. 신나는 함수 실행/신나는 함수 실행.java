import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp = new int[21][21][21];
		
		for(int i=0; i<21; i++) {
			for(int j=0; j<21; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		/* 로직 */
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1 && c == -1) break;
									
			sb.append("w(")
			.append(a).append(", ")
			.append(b).append(", ")
			.append(c).append(") = ")
			.append(w(a, b, c))
			.append("\n");
		}
		
		/* 출력 */
		System.out.print(sb.toString());		
	}
	
	static int w(int a, int b, int c) {		
		if(a <= 0 || b <= 0 || c <= 0) return 1;
		if(a > 20 || b > 20 || c > 20) return w(20, 20, 20);
		if(dp[a][b][c] >= 0) return dp[a][b][c];
		
		if(a < b && b < c)
			return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
		
		return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
	}

}
