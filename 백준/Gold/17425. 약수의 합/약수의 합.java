import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static long[] fdp, gdp;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		fdp = new long[1000001];
		gdp = new long[1000001];
		gdp[1] = 1;
		
		/* 로직 */
		for(int i=1; i<=1000000; i++) {
			for(int j=i; j<=1000000; j+=i) {
				fdp[j] += i;
			}
		}
		
		for(int i=2; i<=1000000; i++) {
			gdp[i] = gdp[i-1] + fdp[i];
		}
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			sb.append(gdp[N]).append("\n");
		}
		
		/* 출력 */
		System.out.println(sb);
	}
}
