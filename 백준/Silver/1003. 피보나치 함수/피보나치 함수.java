import java.io.*;
import java.util.*;

public class Main {
	static int T;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		
		//반복문 ver
		int[] dp0 = new int[41];
		int[] dp1 = new int[41];
		dp0[0] = 1; dp1[0] = 0;
		dp0[1] = 0; dp1[1] = 1;
		for (int i = 2; i < 41; i++) {
			dp0[i] = dp0[i-2]+dp0[i-1];
			dp1[i] = dp1[i-2]+dp1[i-1];			
		}
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp0[n]).append(" ").append(dp1[n]).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}
}
